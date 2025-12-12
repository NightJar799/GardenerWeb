package com.example.gardener.service;

import com.example.gardener.DTO.RegisterDTO;
import com.example.gardener.DTO.UserPageDTO;
import com.example.gardener.Entities.Favorite;
import com.example.gardener.Entities.Preferences;
import com.example.gardener.Entities.User;
import com.example.gardener.Repository.*;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;
    private final PrefRepository prefRepository;
    private final EntityManager entityManager;
    private final FavoriteRepository favoriteRepository;
    private final GrowthRepository growthRepository;
    private final PlantRepository plantRepository;

    public UserService(UserRepository userRepository, PrefRepository prefRepository, EntityManager entityManager, FavoriteRepository favoriteRepository, GrowthRepository growthRepository, PlantRepository plantRepository) {
        this.userRepository = userRepository;
        this.prefRepository = prefRepository;
        this.entityManager = entityManager;
        this.favoriteRepository = favoriteRepository;
        this.growthRepository = growthRepository;
        this.plantRepository = plantRepository;
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

    @Caching(
            evict = {
                    @CacheEvict(value = "users", allEntries = true),
                    @CacheEvict(value = "userByLogin", key = "#user.login")
            }
    )
    public User addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @CacheEvict(value = "userPreferences", key = "#preferences.user.id")
    public Preferences addNewPreference(Preferences preferences) {
        return entityManager.merge(preferences);
    }

    @Cacheable(value = "userByLogin", key = "#login", unless = "#result == null")
    public User getUserIdByLogin(String login) {
        return userRepository.findUserIdByLogin(login);
    }

    @Cacheable(value = "userByName", key = "#name", unless = "#result == null")
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
        List<Integer> favoritePlantIds = favorites.stream()
                .map(favorite -> favorite.getPlant().getId())
                .collect(Collectors.toList());

        Integer recommendedPlantId = growthRepository.findPlantIdWithMaxMatchingPreferences(
                preferences.getClimate(), preferences.getSoil(), preferences.getSpace(),
                preferences.getWater(), preferences.getLandScape());

        String recommendedPlantName = recommendedPlantId != null ?
                plantRepository.findById(recommendedPlantId)
                        .map(plant -> plant.getName())
                        .orElse("No recommendation") :
                "No recommendation";

        return new UserPageDTO(
                user.getLogin(),
                user.getNickName(),
                favoritePlantIds,
                recommendedPlantName,
                preferences.getClimate(),
                preferences.getSoil(),
                preferences.getSpace(),
                preferences.getWater(),
                preferences.getLandScape()
        );
    }

    @Cacheable(value = "userPage", key = "#id", unless = "#result == null")
    public UserPageDTO getUserPageData(Integer id) {
        Preferences preferences = prefRepository.getReferenceById(id);
        User user = userRepository.getReferenceById(id);
        List<Favorite> favorites = favoriteRepository.getAllFavoriteOfUser(id);
        return mapUserPageDTO(preferences, user, favorites);
    }

    @CacheEvict(value = {"userPage", "userPreferences"}, key = "#id")
    public void updateUserPreferences(Integer id, String climate, String soil, Integer space, String water, String landScape) {
        prefRepository.changePreference(id, climate, soil, space, water, landScape);
    }
}