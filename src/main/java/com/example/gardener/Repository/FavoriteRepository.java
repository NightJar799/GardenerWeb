package com.example.gardener.Repository;

import com.example.gardener.Entities.Favorite;
import com.example.gardener.Entities.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    @Query(nativeQuery = true,
            value = "SELECT COUNT(*) FROM grd.favorite WHERE favorite.id_plant = :idPlant")
    Optional<User> PopularityOfSpecificPlant(@Param("idPlant") String login);

    void deleteByUserIdAndPlantId(Integer userId, Integer plantId);

    boolean existsByUserIdAndPlantId(Integer userId, Integer plantId);

    @Query(nativeQuery = true, value = "SELECT id_usr, id_plant FROM grd.favorite WHERE favorite.id_usr = :idUser")
    List<Favorite> getAllFavoriteOfUser(@Param("idUser") Integer id);
}
