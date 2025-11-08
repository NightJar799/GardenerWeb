package com.example.gardener.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "plants", schema = "grd")
public class Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 30)
    private String name;

    @Column(name = "science_name", unique = true, nullable = false, length = 100)
    private String scienceName;

    @Column(name = "description", nullable = false, length = 8000)
    private String description;

    @Column(name = "photo", length = 300)
    private String photo;

    public Plant() {}

    public Plant(String name, String scienceName, String description, String photo) {
        this.name = name;
        this.scienceName = scienceName;
        this.description = description;
        this.photo = photo;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getScienceName() { return scienceName; }
    public void setScienceName(String scienceName) { this.scienceName = scienceName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getPhoto() { return photo; }
    public void setPhoto(String photo) { this.photo = photo; }


}
