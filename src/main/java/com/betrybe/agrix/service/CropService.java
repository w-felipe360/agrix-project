package com.betrybe.agrix.service;

import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.models.repositories.CropRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** Esta classe fornece os serviços relacionados a plantações. */
@Service
public class CropService {
  private final CropRepository cropRepository;

  @Autowired
  public CropService(CropRepository cropRepo) {
    this.cropRepository = cropRepo;
  }

  public Crop insertCrop(Crop crop, Farm farms) {
    crop.setFarm(farms);
    return cropRepository.save(crop);
  }

  public List<Crop> getAllCrops() {
    return cropRepository.findAll();
  }

  public Optional<Crop> getByIdCrop(Long id) {
    return cropRepository.findById(id);
  }

  public List<Crop> getCropByIdFarm(Farm farm) {
    return cropRepository.findByFarm(farm);
  }

  public List<Crop>  getSearchByHarvestDate(LocalDate start, LocalDate end) {
    return cropRepository.findByHarvestDateBetween(start, end);
  }
}