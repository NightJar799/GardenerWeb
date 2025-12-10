package com.example.gardener.DTO;

import com.example.gardener.Entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestDTO {
    private String climate;
    private String soil;
    private Integer space;
    private String water;
    private String landScape;

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
