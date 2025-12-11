package com.example.gardener.service;

import com.example.gardener.DTO.RegisterDTO;
import com.example.gardener.DTO.UserPageDTO;
import com.example.gardener.Entities.Favorite;
import com.example.gardener.Entities.Preferences;
import com.example.gardener.Entities.User;
import com.example.gardener.Repository.FavoriteRepository;
import com.example.gardener.Repository.GrowthRepository;
import com.example.gardener.Repository.PrefRepository;
import com.example.gardener.Repository.UserRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;
    private  final PrefRepository prefRepository;
    private final EntityManager entityManager;
    private final FavoriteRepository favoriteRepository;
    private final GrowthRepository growthRepository;

    public UserService(UserRepository userRepository, PrefRepository prefRepository, EntityManager entityManager, FavoriteRepository favoriteRepository, GrowthRepository growthRepository) {
        this.userRepository = userRepository;
        this.prefRepository = prefRepository;
        this.entityManager = entityManager;
        this.favoriteRepository = favoriteRepository;
        this.growthRepository = growthRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Map<String, String> getAllPasswordsAndLogins() {
        Map<String, String> passwordsAndLogins = new HashMap<>();
        for (User user : getAllUsers()) {
            passwordsAndLogins.put(user.getLogin(), user.getPassword());
        }
        return passwordsAndLogins;
    }

    public User addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Preferences addNewPreference(Preferences preferences) {
        return entityManager.merge(preferences);
    }

    public User getUserIdByLogin(String login) {
        return userRepository.findUserIdByLogin(login);
    }
    public User getUserByNickname(String name) {
        return userRepository.findUserByName(name);
    }

    public User mapRegToEntity(RegisterDTO registerDTO) {
        return new User(registerDTO.getEmail(),
                registerDTO.getPassword(),
                registerDTO.getNickname(),
                registerDTO.getPhone());
    }

    private UserPageDTO mapUserPageDTO(Preferences preferences, User user, List<Favorite> favorites) {
        return  new UserPageDTO(user.getLogin(), user.getNickName(), favorites, growthRepository.findPlantIdWithMaxMatchingPreferences(
                preferences.getClimate(), preferences.getSoil(), preferences.getSpace(), preferences.getWater(), preferences.getLandScape()),
                preferences.getClimate(), preferences.getSoil(), preferences.getSpace(), preferences.getWater(), preferences.getLandScape());
    }
    public UserPageDTO getUserPageData(Integer id) {
        Preferences preferences = prefRepository.getReferenceById(id);
        User user = userRepository.getReferenceById(id);
        List<Favorite> favorites = favoriteRepository.getAllFavoriteOfUser(id);
        return mapUserPageDTO(preferences, user, favorites);
    }
}
