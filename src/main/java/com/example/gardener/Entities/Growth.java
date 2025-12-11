package com.example.gardener.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "growth", schema = "grd")
public class Growth {
    @Id
    @Column(name = "id_plant")
    private Integer id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_plant", nullable = false, unique = true)
    private Plant plant;

    @Column(name = "ppfd", nullable = false, length = 9)
    private String ppfd;

    @Column(name = "humidity", nullable = false)
    private Double humidity;

    @Column(name = "ph", nullable = false)
    private Double ph;

    @Column(name = "space", nullable = false)
    private Integer space;

    @Column(name = "soil", nullable = false, length = 25)
    private String soil;

    @Column(name = "survivability", nullable = false)
    private String survivability;

    @Column(name = "growth_speed", nullable = false, length = 5)
    private String growthSpeed;

    @Column(name = "climate", length = 20)
    private String climate;

    @Column(name = "water", length = 20)
    private String water;

    @Column(name = "landscape", length = 20)
    private String landScape;

    public Growth() {}

    public Growth(Plant plant, String ppfd, Double humidity, Double ph, Integer space,
                  String soil, String survivability, String growthSpeed, String climate, String water, String landScape) {
        this.plant = plant;
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
    }

    public Plant getPlant() { return plant; }
    public void setPlant(Plant plant) { this.plant = plant; }

    public String getPpfd() { return ppfd; }
    public void setPpfd(String ppfd) { this.ppfd = ppfd; }

    public Double getHumidity() { return humidity; }
    public void setHumidity(Double humidity) { this.humidity = humidity; }

    public Double getPh() { return ph; }
    public void setPh(Double ph) { this.ph = ph; }

    public Integer getSpace() { return space; }
    public void setSpace(Integer space) { this.space = space; }

    public String getSoil() { return soil; }
    public void setSoil(String soil) { this.soil = soil; }

    public String getSurvivability() { return survivability; }
    public void setSurvivability(String survivability) { this.survivability = survivability; }

    public String getGrowthSpeed() { return growthSpeed; }
    public void setGrowthSpeed(String growthSpeed) { this.growthSpeed = growthSpeed; }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
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