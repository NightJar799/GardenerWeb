package com.example.gardener.Repository;

import com.example.gardener.Entities.BioChar;
import io.lettuce.core.dynamic.annotation.Param;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BioCharRepository extends JpaRepository<BioChar, Integer> {
    @Modifying
    @Transactional
    @Query(nativeQuery = true,
            value = "UPDATE grd.bio_char SET leaf_type = :leaf, root = :root, fruit = :fruit, amm_fruit = :amm_fruit, morthology = :morthology WHERE bio_char.id_plant = :idPlant")
    void changeBioChar(@Param("idPlant") Integer idPlant, @Param("leaf") String leaf, @Param("root") String root,
                          @Param("fruit") String fruit, @Param("amm_fruit") String amm_fruit, @Param("morthology") String morthology);
}
