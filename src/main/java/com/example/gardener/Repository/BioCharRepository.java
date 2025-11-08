package com.example.gardener.Repository;

import com.example.gardener.Entities.BioChar;
import com.example.gardener.Entities.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BioCharRepository extends JpaRepository<BioChar, Integer> {
}
