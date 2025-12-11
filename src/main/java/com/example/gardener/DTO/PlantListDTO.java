package com.example.gardener.DTO;

import com.example.gardener.Entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlantListDTO {
    private Integer plantId;
    private String name;
    private String scientificName;
    private String climate;
    private String soil;
    private Integer space;
    private String water;
    private String landScape;
}
