package com.example.gardener.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "favorite", schema = "grd")
public class Favorite {
    @EmbeddedId
    private FavoriteId id;

    @ManyToOne
    @MapsId("idUser") // Maps to idUser field in FavoriteId
    @JoinColumn(name = "id_usr", nullable = false)
    private User user;

    @ManyToOne
    @MapsId("idPlant") // Maps to idPlant field in FavoriteId
    @JoinColumn(name = "id_plant", nullable = false)
    private Plant plant;

    public Favorite() {}

    public Favorite(User user, Plant plant) {
        this.user = user;
        this.plant = plant;
        this.id = new FavoriteId(user.getId(), plant.getId());
    }

    public FavoriteId getId() { return id; }
    public void setId(FavoriteId id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) {
        this.user = user;
        if (this.id == null) {
            this.id = new FavoriteId();
        }
        this.id.setIdUser(user.getId());
    }

    public Plant getPlant() { return plant; }
    public void setPlant(Plant plant) {
        this.plant = plant;
        if (this.id == null) {
            this.id = new FavoriteId();
        }
        this.id.setIdPlant(plant.getId());
    }
}