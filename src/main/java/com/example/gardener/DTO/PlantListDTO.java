package com.example.gardener.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class PlantListDTO {
    private Integer plantId;
    private String name;
    private String scientificName;
    private String climate;
    private String soil;
    private Integer space;
    private String water;
    private String landScape;

    public PlantListDTO(){}

    public PlantListDTO(Integer plantId, String name, String scientificName, String climate, Integer space, String soil, String water, String landScape) {
        this.plantId = plantId;
        this.name = name;
        this.scientificName = scientificName;
        this.climate = climate;
        this.space = space;
        this.soil = soil;
        this.water = water;
        this.landScape = landScape;
    }

    public Integer getPlantId() {
        return plantId;
    }

    public void setPlantId(Integer plantId) {
        this.plantId = plantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getSoil() {
        return soil;
    }

    public void setSoil(String soil) {
        this.soil = soil;
    }

    public Integer getSpace() {
        return space;
    }

    public void setSpace(Integer space) {
        this.space = space;
    }

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water;
    }

    public String getLandScape() {
        return landScape;
    }

    public void setLandScape(String landScape) {
        this.landScape = landScape;
    }
}
