package com.example.gardener.DTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlantDTO {
    private Integer plantId;
    private String leafType;
    private String root;
    private String fruit;
    private Character ammFruit;
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
}
