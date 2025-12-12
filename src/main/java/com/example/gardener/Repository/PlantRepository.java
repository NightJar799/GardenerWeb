package com.example.gardener.Repository;

import com.example.gardener.Entities.Plant;
import io.lettuce.core.dynamic.annotation.Param;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Integer> {
    @Modifying
    @Transactional
    @Query(nativeQuery = true,
            value = "UPDATE grd.plants SET name = :name, science_name = :science_name, description = :description, photo = :photo WHERE plants.id = :idPlant")
    void changePlant(@Param("idPlant") Integer idPlant, @Param("name") String name, @Param("science_name") String science_name,
                          @Param("description") String description, @Param("photo") String photo);
}
