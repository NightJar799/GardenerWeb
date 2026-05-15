package com.example.gardener.Repository;

import com.example.gardener.Entities.Favorite;
import com.example.gardener.Entities.User;
import io.lettuce.core.dynamic.annotation.Param;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    @Query(nativeQuery = true,
            value = "SELECT COUNT(*) FROM grd.favorite WHERE favorite.id_plant = :idPlant")
    Optional<User> PopularityOfSpecificPlant(@Param("idPlant") String login);

    @Query(nativeQuery = true, value = "SELECT f.id_usr, f.id_plant " +
            "FROM grd.favorite f " +
            "WHERE f.id_usr = :idUser")
    List<Favorite> getAllFavoriteOfUser(@Param("idUser") Integer idUser);

    @Modifying
    @Transactional
    @Query("DELETE FROM Favorite f WHERE f.plant.id = :plantId")
    void deleteAllByPlantId(@Param("plantId") Integer plantId);

    @Query("SELECT f FROM Favorite f WHERE f.plant.id = :plantId")
    List<Favorite> findAllByPlantId(@Param("plantId") Integer plantId);
}
