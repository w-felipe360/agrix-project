package com.betrybe.agrix.models.repositories;

import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Interface que define um repositório para entidades do tipo crops. */
@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {
  List<Crop> findByFarm(Farm farm);

  List<Crop> findByHarvestDateBetween(LocalDate start, LocalDate end);
}