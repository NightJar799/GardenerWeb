package com.example.gardener.Entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
class FavoriteId implements Serializable {
    @Column(name = "id_usr", nullable = false)
    private Integer idUser;
    @Column(name = "id_plant", nullable = false)
    private Integer idPlant;

    // constructors, getters, setters, equals, hashCode
    public FavoriteId() {}

    public FavoriteId(Integer idUser, Integer idPlant) {
        this.idUser = idUser;
        this.idPlant = idPlant;
    }

    public Integer getIdUser() { return idUser; }
    public void setIdUser(Integer idUser) { this.idUser = idUser; }

    public Integer getIdPlant() { return idPlant; }
    public void setIdPlant(Integer idPlant) { this.idPlant = idPlant; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoriteId that = (FavoriteId) o;
        return idUser.equals(that.idUser) && idPlant.equals(that.idPlant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idPlant);
    }
}