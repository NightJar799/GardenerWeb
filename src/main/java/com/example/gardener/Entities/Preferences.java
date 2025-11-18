package com.example.gardener.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "preference", schema = "grd")
public class Preferences {
    @Id
    @Column(name = "id_usr", unique = true, nullable = false)
    private Integer id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_usr", nullable = false)
    private User user;

    @Column(name = "climate", length = 20)
    private String climate;

    @Column(name = "soil", length = 20)
    private String soil;

    @Column(name = "space")
    private Integer space;

    @Column(name = "water", length = 20)
    private String water;

    @Column(name = "landscape", length = 20)
    private String landScape;

    public Preferences() {}

    public Preferences(User user, String climate, String soil, Integer space, String water, String landScape) {
        this.user = user;
        this.climate = climate;
        this.soil = soil;
        this.space = space;
        this.water = water;
        this.landScape = landScape;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
