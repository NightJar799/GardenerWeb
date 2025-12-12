package com.example.gardener.service;

import com.example.gardener.DTO.PlantDTO;
import com.example.gardener.Repository.BioCharRepository;
import com.example.gardener.Repository.GrowthRepository;
import com.example.gardener.Repository.PlantRepository;
import org.springframework.stereotype.Controller;

@Controller
public class EditService {
    private final BioCharRepository bioCharRepository;
    private final PlantRepository plantRepository;
    private final GrowthRepository growthRepository;


    public EditService(BioCharRepository bioCharRepository, PlantRepository plantRepository, GrowthRepository growthRepository) {
        this.bioCharRepository = bioCharRepository;
        this.plantRepository = plantRepository;
        this.growthRepository = growthRepository;
    }

    public void updatePlantInfo(PlantDTO plantDTO) {
        Integer idPlant = plantDTO.getPlantId();
        bioCharRepository.changeBioChar(idPlant, plantDTO.getLeafType(), plantDTO.getRoot(), plantDTO.getFruit(), plantDTO.getAmmFruit(), plantDTO.getMorphology());
        plantRepository.changePlant(idPlant, plantDTO.getName(), plantDTO.getScienceName(), plantDTO.getDescription(), null);
        growthRepository.changeGrowth(idPlant, plantDTO.getPpfd(), plantDTO.getHumidity(), plantDTO.getPh(), plantDTO.getSpace(),
                plantDTO.getSoil(), plantDTO.getSurvivability(), plantDTO.getGrowthSpeed(), plantDTO.getClimate(),
                plantDTO.getWater(), plantDTO.getLandScape());
    }
}
