package com.example.gardener.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class UserPageDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String email;
    private String nickname;

    private List<Integer> favoritePlantIds;

    private String recommendedPlantName;
    private String climate;
    private String soil;
    private Integer space;
    private String water;
    private String landScape;

    private List<String> climateOptions;
    private List<String> soilOptions;
    private List<Integer> spaceOptions;
    private List<String> waterOptions;
    private List<String> landscapeOptions;

    public UserPageDTO(){}

    public UserPageDTO(String email, String nickname, List<Integer> favoritePlantIds,
                       String recommendedPlantName, String climate, String soil,
                       Integer space, String water, String landScape) {
        this.email = email;
        this.nickname = nickname;
        this.favoritePlantIds = favoritePlantIds;
        this.recommendedPlantName = recommendedPlantName;
        this.climate = climate;
        this.soil = soil;
        this.space = space;
        this.water = water;
        this.landScape = landScape;

        // Инициализируем опции в конструкторе
        this.climateOptions = Arrays.asList("tropical", "dry", "temperate", "continental", "polar");
        this.soilOptions = Arrays.asList("sandy", "peaty", "loamy", "silty", "clay", "chalky");
        this.spaceOptions = Arrays.asList(10, 25, 50, 100, 250);
        this.waterOptions = Arrays.asList("none", "lake", "river", "sea/ocean");
        this.landscapeOptions = Arrays.asList("mountain", "forest", "steppe", "tundra", "sandy", "hilly");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public List<Integer> getFavoritePlantIds() {
        return favoritePlantIds;
    }

    public void setFavoritePlantIds(List<Integer> favoritePlantIds) {
        this.favoritePlantIds = favoritePlantIds;
    }

    public String getRecommendedPlantName() {
        return recommendedPlantName;
    }

    public void setRecommendedPlantName(String recommendedPlantName) {
        this.recommendedPlantName = recommendedPlantName;
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

    public List<String> getClimateOptions() {
        return climateOptions;
    }

    public void setClimateOptions(List<String> climateOptions) {
        this.climateOptions = climateOptions;
    }

    public List<String> getSoilOptions() {
        return soilOptions;
    }

    public void setSoilOptions(List<String> soilOptions) {
        this.soilOptions = soilOptions;
    }

    public List<Integer> getSpaceOptions() {
        return spaceOptions;
    }

    public void setSpaceOptions(List<Integer> spaceOptions) {
        this.spaceOptions = spaceOptions;
    }

    public List<String> getWaterOptions() {
        return waterOptions;
    }

    public void setWaterOptions(List<String> waterOptions) {
        this.waterOptions = waterOptions;
    }

    public List<String> getLandscapeOptions() {
        return landscapeOptions;
    }

    public void setLandscapeOptions(List<String> landscapeOptions) {
        this.landscapeOptions = landscapeOptions;
    }
}