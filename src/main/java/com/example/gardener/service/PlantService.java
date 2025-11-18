package com.example.gardener.service;

import com.example.gardener.Entities.*;
import com.example.gardener.Repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlantService {
    private final PlantRepository plantRepository;
    private final UserRepository userRepository;
    private final FavoriteRepository favoriteRepository;
    private final GrowthRepository growthRepository;
    private final BioCharRepository bioCharRepository;
    private final PrefRepository prefRepository;

    public PlantService(PlantRepository plantRepository, UserRepository userRepository,
                        FavoriteRepository favoriteRepository, GrowthRepository growthRepository,
                        BioCharRepository bioCharRepository, PrefRepository prefRepository) {
        this.plantRepository = plantRepository;
        this.userRepository = userRepository;
        this.favoriteRepository = favoriteRepository;
        this.growthRepository = growthRepository;
        this.bioCharRepository = bioCharRepository;
        this.prefRepository = prefRepository;
    }

    private Growth addGrowth (Growth growth) {return growthRepository.save(growth);}
    private BioChar addBioChar (BioChar bioChar) {return bioCharRepository.save(bioChar);}
    public Plant addPlant(Plant plant) {
        return plantRepository.save(plant);
    }
    public Preferences addPref(Preferences preferences) {
        return prefRepository.save(preferences);
    }
    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User findUserByID(Integer id) {
        return userRepository.getById(id);
    }


    public List<Plant> getAllPlants() {
        return plantRepository.findAll();
    }

}
