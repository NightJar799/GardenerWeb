package com.example.gardener.service;

import com.example.gardener.Entities.Favorite;
import com.example.gardener.Entities.Plant;
import com.example.gardener.Entities.User;
import com.example.gardener.Repository.FavoriteRepository;
import com.example.gardener.Repository.PlantRepository;
import com.example.gardener.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class FavoriteService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlantRepository plantRepository;

    public boolean isPlantInFavorites(Integer userId, Integer plantId) {
        String sql = "SELECT COUNT(*) > 0 FROM grd.favorite WHERE id_usr = ? AND id_plant = ?";
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, Boolean.class, userId, plantId));
    }

    @Transactional
    public void addToFavorites(Integer userId, Integer plantId) {
        if (!isPlantInFavorites(userId, plantId)) {
            String sql = "INSERT INTO grd.favorite (id_usr, id_plant) VALUES (?, ?)";
            jdbcTemplate.update(sql, userId, plantId);
        }
    }

    @Transactional
    public void removeFromFavorites(Integer userId, Integer plantId) {
        String sql = "DELETE FROM grd.favorite WHERE id_usr = ? AND id_plant = ?";
        jdbcTemplate.update(sql, userId, plantId);
    }
}
