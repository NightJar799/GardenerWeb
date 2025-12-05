package com.example.gardener.service;

import com.example.gardener.Entities.Preferences;
import com.example.gardener.Entities.User;
import com.example.gardener.Repository.PrefRepository;
import com.example.gardener.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;
    private  final PrefRepository prefRepository;

    public UserService(UserRepository userRepository, PrefRepository prefRepository) {
        this.userRepository = userRepository;
        this.prefRepository = prefRepository;
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
        return prefRepository.save(preferences);
    }

    public User getUserIdByLogin(String login) {
        return userRepository.findUserIdByLogin(login);
    }
}
