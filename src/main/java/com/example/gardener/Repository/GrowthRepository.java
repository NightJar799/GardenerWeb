package com.example.gardener.Repository;

import com.example.gardener.Entities.Growth;
import com.example.gardener.Entities.Plant;
import io.lettuce.core.dynamic.annotation.Param;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GrowthRepository extends JpaRepository<Growth, Integer> {
    @Query(value = """
        SELECT p.id_plant
        FROM grd.growth p
        WHERE (
            (CASE WHEN :userClimate IS NULL THEN 0 ELSE CASE WHEN p.climate = :userClimate THEN 1 ELSE 0 END END) +
            (CASE WHEN :userSoil IS NULL THEN 0 ELSE CASE WHEN p.soil = :userSoil THEN 1 ELSE 0 END END) +
            (CASE WHEN :userSpace IS NULL THEN 0 ELSE CASE WHEN p.space = :userSpace THEN 1 ELSE 0 END END) +
            (CASE WHEN :userWater IS NULL THEN 0 ELSE CASE WHEN p.water = :userWater THEN 1 ELSE 0 END END) +
            (CASE WHEN :userLandScape IS NULL THEN 0 ELSE CASE WHEN p.landscape = :userLandScape THEN 1 ELSE 0 END END)
        ) = (
            SELECT MAX(
                (CASE WHEN :userClimate IS NULL THEN 0 ELSE CASE WHEN p2.climate = :userClimate THEN 1 ELSE 0 END END) +
                (CASE WHEN :userSoil IS NULL THEN 0 ELSE CASE WHEN p2.soil = :userSoil THEN 1 ELSE 0 END END) +
                (CASE WHEN :userSpace IS NULL THEN 0 ELSE CASE WHEN p2.space = :userSpace THEN 1 ELSE 0 END END) +
                (CASE WHEN :userWater IS NULL THEN 0 ELSE CASE WHEN p2.water = :userWater THEN 1 ELSE 0 END END) +
                (CASE WHEN :userLandScape IS NULL THEN 0 ELSE CASE WHEN p2.landscape = :userLandScape THEN 1 ELSE 0 END END)
            )
            FROM grd.growth p2
        )
        LIMIT 1
        """, nativeQuery = true)
    Integer findPlantIdWithMaxMatchingPreferences(
            @Param("userClimate") String userClimate,
            @Param("userSoil") String userSoil,
            @Param("userSpace") Integer userSpace,
            @Param("userWater") String userWater,
            @Param("userLandScape") String userLandScape);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,
            value = "UPDATE grd.growth SET ppfd = :ppfd, humidity = :humidity, ph = :ph, space = :space, soil = :soil," +
                    " survivability = :survivability, growth_speed = :growth_speed, climate = :climate, " +
                    " water = :water, landscape = :landscape WHERE growth.id_plant = :idPlant")
    void changeGrowth(@Param("idPlant") Integer idPlant, @Param("ppfd") String ppfd, @Param("humidity") Double humidity,
                          @Param("ph") Double ph, @Param("space") Integer space, @Param("soil") String soil,
                      @Param("survivability") String survivability, @Param("growth_speed") String growth_speed, @Param("climate") String climate,
                      @Param("water") String water, @Param("landscape") String landscape);
}

