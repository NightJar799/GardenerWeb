package com.example.gardener.service;

import com.example.gardener.DTO.RegisterDTO;
import com.example.gardener.Entities.Preferences;
import com.example.gardener.Entities.User;
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

    public UserService(UserRepository userRepository, PrefRepository prefRepository, EntityManager entityManager) {
        this.userRepository = userRepository;
        this.prefRepository = prefRepository;
        this.entityManager = entityManager;
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
}
