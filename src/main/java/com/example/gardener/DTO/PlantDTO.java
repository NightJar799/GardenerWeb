package com.example.gardener.DTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class PlantDTO {
    private Integer plantId;
    private String leafType;
    private String root;
    private String fruit;
    private String ammFruit;
    private String morphology;
    private String ppfd;
    private Double humidity;
    private Double ph;
    private Integer space;
    private String soil;
    private String survivability;
    private String growthSpeed;
    private String climate;
    private String water;
    private String landScape;
    private String name;
    private String scienceName;
    private String description;

    public PlantDTO(){}

    public PlantDTO(Integer plantId, String leafType, String root, String fruit, String ammFruit, String morphology, String ppfd, Double humidity, Double ph, Integer space, String soil, String survivability, String growthSpeed, String climate, String water, String landScape, String name, String scienceName, String description) {
        this.plantId = plantId;
        this.leafType = leafType;
        this.root = root;
        this.fruit = fruit;
        this.ammFruit = ammFruit;
        this.morphology = morphology;
        this.ppfd = ppfd;
        this.humidity = humidity;
        this.ph = ph;
        this.space = space;
        this.soil = soil;
        this.survivability = survivability;
        this.growthSpeed = growthSpeed;
        this.climate = climate;
        this.water = water;
        this.landScape = landScape;
        this.name = name;
        this.scienceName = scienceName;
        this.description = description;
    }

    public Integer getPlantId() {
        return plantId;
    }

    public void setPlantId(Integer plantId) {
        this.plantId = plantId;
    }

    public String getLeafType() {
        return leafType;
    }

    public void setLeafType(String leafType) {
        this.leafType = leafType;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getFruit() {
        return fruit;
    }

    public void setFruit(String fruit) {
        this.fruit = fruit;
    }

    public String getAmmFruit() {
        return ammFruit;
    }

    public void setAmmFruit(String ammFruit) {
        this.ammFruit = ammFruit;
    }

    public String getMorphology() {
        return morphology;
    }

    public void setMorphology(String morphology) {
        this.morphology = morphology;
    }

    public String getPpfd() {
        return ppfd;
    }

    public void setPpfd(String ppfd) {
        this.ppfd = ppfd;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getPh() {
        return ph;
    }

    public void setPh(Double ph) {
        this.ph = ph;
    }

    public Integer getSpace() {
        return space;
    }

    public void setSpace(Integer space) {
        this.space = space;
    }

    public String getSoil() {
        return soil;
    }

    public void setSoil(String soil) {
        this.soil = soil;
    }

    public String getSurvivability() {
        return survivability;
    }

    public void setSurvivability(String survivability) {
        this.survivability = survivability;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getGrowthSpeed() {
        return growthSpeed;
    }

    public void setGrowthSpeed(String growthSpeed) {
        this.growthSpeed = growthSpeed;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScienceName() {
        return scienceName;
    }

    public void setScienceName(String scienceName) {
        this.scienceName = scienceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
