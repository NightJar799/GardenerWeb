package com.example.gardener.Repository;

import com.example.gardener.Entities.Preferences;
import com.example.gardener.Entities.User;
import io.lettuce.core.dynamic.annotation.Param;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrefRepository extends JpaRepository<Preferences, Integer> {
    @Modifying
    @Transactional
    @Query(nativeQuery = true,
            value = "UPDATE grd.preference SET climate = :climate, soil = :soil, space = :space, water = :water, landscape = :landscape WHERE preference.id_usr = :idUser")
    void changePreference(@Param("idUser") Integer idUser, @Param("climate") String climate, @Param("soil") String soil,
                                             @Param("space") Integer space, @Param("water") String water, @Param("landscape") String landscape);
}
