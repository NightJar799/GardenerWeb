package com.example.gardener.service;

import com.example.gardener.DTO.PlantDTO;
import com.example.gardener.Entities.BioChar;
import com.example.gardener.Entities.Favorite;
import com.example.gardener.Entities.Growth;
import com.example.gardener.Entities.Plant;
import com.example.gardener.Repository.BioCharRepository;
import com.example.gardener.Repository.FavoriteRepository;
import com.example.gardener.Repository.GrowthRepository;
import com.example.gardener.Repository.PlantRepository;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class EditService {
    private final BioCharRepository bioCharRepository;
    private final PlantRepository plantRepository;
    private final GrowthRepository growthRepository;
    private final FavoriteRepository favoriteRepository;

    public EditService(BioCharRepository bioCharRepository, PlantRepository plantRepository, GrowthRepository growthRepository, FavoriteRepository favoriteRepository) {
        this.bioCharRepository = bioCharRepository;
        this.plantRepository = plantRepository;
        this.growthRepository = growthRepository;
        this.favoriteRepository = favoriteRepository;
    }

    @Caching(evict = {
            @CacheEvict(value = "plantDetails", key = "#plantDTO.plantId"),
            @CacheEvict(value = "allPlants", allEntries = true)
    })
    public void updatePlantInfo(PlantDTO plantDTO) {
        Integer idPlant = plantDTO.getPlantId();
        bioCharRepository.changeBioChar(idPlant, plantDTO.getLeafType(),
                plantDTO.getRoot(), plantDTO.getFruit(), plantDTO.getAmmFruit(),
                plantDTO.getMorphology());
        plantRepository.changePlant(idPlant, plantDTO.getName(),
                plantDTO.getScienceName(), plantDTO.getDescription(), null);
        growthRepository.changeGrowth(idPlant, plantDTO.getPpfd(),
                plantDTO.getHumidity(), plantDTO.getPh(), plantDTO.getSpace(),
                plantDTO.getSoil(), plantDTO.getSurvivability(),
                plantDTO.getGrowthSpeed(), plantDTO.getClimate(),
                plantDTO.getWater(), plantDTO.getLandScape());
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(value = "plantDetails", key = "#plantId"),
            @CacheEvict(value = "allPlants", allEntries = true),
            @CacheEvict(value = "userPage", allEntries = true) // Очищаем кэш пользователей т.к. меняются избранные
    })
    public boolean deletePlantById(Integer plantId) {
        try {

            Plant plant = plantRepository.findById(plantId).orElse(null);
            if (plant == null) {
                return false;
            }

            BioChar bioChar = bioCharRepository.findById(plantId).orElse(null);
            Growth growth = growthRepository.findById(plantId).orElse(null);
            List<Favorite> favorites = favoriteRepository.findAllByPlantId(plantId);

            if (!favorites.isEmpty()) {
                favoriteRepository.deleteAllByPlantId(plantId);
            }
            if (bioChar != null) {
                bioCharRepository.delete(bioChar);
            }
            if (growth != null) {
                growthRepository.delete(growth);
            }
            plantRepository.delete(plant);
            plantRepository.flush();

            return true;

        } catch (Exception e) {
            throw new RuntimeException("Не удалось удалить растение: " + e.getMessage(), e);
        }
    }
}