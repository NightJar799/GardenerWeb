package com.example.gardener.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "bio_char", schema = "grd")
public class BioChar {
    @Id
    @Column(name = "id_plant")
    private Integer id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_plant", nullable = false)
    private Plant plant;

    @Column(name = "leaf_type", nullable = false, length = 25)
    private String leafType;

    @Column(name = "root", nullable = false, length = 20)
    private String root;

    @Column(name = "fruit", nullable = false, length = 20)
    private String fruit;

    @Column(name = "amm_fruit", length = 1)
    private Character ammFruit;

    @Column(name = "morthology", nullable = false, length = 30)
    private String morphology;

    public BioChar() {}

    public BioChar(Plant plant, String leafType, String root, String fruit,
                   Character ammFruit, String morphology) {
        this.plant = plant;
        this.leafType = leafType;
        this.root = root;
        this.fruit = fruit;
        this.ammFruit = ammFruit;
        this.morphology = morphology;
    }

    public Plant getPlant() { return plant; }
    public void setPlant(Plant plant) { this.plant = plant; }

    public String getLeafType() { return leafType; }
    public void setLeafType(String leafType) { this.leafType = leafType; }

    public String getRoot() { return root; }
    public void setRoot(String root) { this.root = root; }

    public String getFruit() { return fruit; }
    public void setFruit(String fruit) { this.fruit = fruit; }

    public Character getAmmFruit() { return ammFruit; }
    public void setAmmFruit(Character ammFruit) { this.ammFruit = ammFruit; }

    public String getMorphology() { return morphology; }
    public void setMorphology(String morphology) { this.morphology = morphology; }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
