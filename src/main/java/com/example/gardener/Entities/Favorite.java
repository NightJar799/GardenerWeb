package com.example.gardener.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "favorite", schema = "grd")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_usr", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_plant", nullable = false)
    private Plant plant;

    public Favorite() {}

    public Favorite(User user, Plant plant) {
        this.user = user;
        this.plant = plant;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Plant getPlant() { return plant; }
    public void setPlant(Plant plant) { this.plant = plant; }
}