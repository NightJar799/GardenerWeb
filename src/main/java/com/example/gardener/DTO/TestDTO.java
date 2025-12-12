package com.example.gardener.DTO;

import com.example.gardener.Entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;


public class TestDTO {
    private String climate;
    private String soil;
    private Integer space;
    private String water;
    private String landScape;

    public TestDTO() {}

    public TestDTO(String climate, String soil, Integer space, String water, String landScape) {
        this.climate = climate;
        this.soil = soil;
        this.space = space;
        this.water = water;
        this.landScape = landScape;
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

    public static List<String> getClimateOptions() {
        return Arrays.asList("tropical", "dry", "temperate", "continental", "polar");
    }

    public static List<String> getSoilOptions() {
        return Arrays.asList("sandy", "peaty", "loamy", "silty", "clay", "chalky");
    }

    public static List<Integer> getSpaceOptions() {
        return Arrays.asList(10, 25, 50, 100, 250);
    }

    public static List<String> getWaterOptions() {
        return Arrays.asList("none", "lake", "river", "sea/ocean");
    }

    public static List<String> getLandscapeOptions() {
        return Arrays.asList("mountain", "forest", "steppe", "tundra", "sandy", "hilly");
    }
}
