package com.example.gardener.service;

import com.example.gardener.DTO.PlantDTO;
import com.example.gardener.DTO.PlantListDTO;
import com.example.gardener.Entities.*;
import com.example.gardener.Repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

    public PlantListDTO mapPlantListDTO(Growth growth, Plant plant) {
        return new PlantListDTO(plant.getId(), plant.getName(), plant.getScienceName(), growth.getClimate(), growth.getSoil(),
                growth.getSpace(), growth.getWater(), growth.getLandScape());
    }

    private Plant getPlantFromList(Integer id, List<Plant> plants) {
        for (Plant plant : plants) if (plant.getId() == id) return plant;
        return null;
    }

    private PlantDTO mapToPlantDTO(Plant plant, Growth growth, BioChar bioChar){
        return new PlantDTO(plant.getId(), bioChar.getLeafType(), bioChar.getRoot(), bioChar.getFruit(), bioChar.getAmmFruit(),
                bioChar.getMorphology(), growth.getPpfd(), growth.getHumidity(), growth.getPh(), growth.getSpace(),
                growth.getSoil(), growth.getSurvivability(), growth.getGrowthSpeed(), growth.getClimate(), growth.getWater(),
                growth.getLandScape(), plant.getName(), plant.getScienceName(), plant.getDescription());
    }

    public PlantDTO getPlantDTO(Integer id) {
    return mapToPlantDTO(plantRepository.getReferenceById(id) , growthRepository.getReferenceById(id), bioCharRepository.getReferenceById(id));
    }

    public List<PlantListDTO> getPlantAllForListDTO() {
        List<Growth> growthChar = growthRepository.findAll();
        List<Plant> plantList = plantRepository.findAll();
        List<PlantListDTO> plantListDTOS = new ArrayList<>();

        for (Growth growth : growthChar) {
            plantListDTOS.add(mapPlantListDTO(growth, Objects.requireNonNull(getPlantFromList(growth.getId(), plantList))));
        }
        return plantListDTOS;
    }

}
