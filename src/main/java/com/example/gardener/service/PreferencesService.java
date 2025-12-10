package com.example.gardener.service;

import com.example.gardener.DTO.TestDTO;
import com.example.gardener.Entities.Preferences;
import com.example.gardener.Entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class PreferencesService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Preferences createNewPreferences(Integer userId, TestDTO dto) {
        User user = em.getReference(User.class, userId);

        Preferences preferences = new Preferences();

        preferences.setId(userId);

        preferences.setUser(user);

        preferences.setClimate(dto.getClimate());
        preferences.setSoil(dto.getSoil());
        preferences.setSpace(dto.getSpace());
        preferences.setWater(dto.getWater());
        preferences.setLandScape(dto.getLandScape());

        em.persist(preferences);

        return preferences;
    }
}
