package com.betrybe.agrix.controllers;

import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.service.CropService;
import com.betrybe.agrix.service.FarmService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;



/** Farm controller. */
@RestController
@RequestMapping("/farms")
public class FarmController {
  private final FarmService farmService;

  @Autowired
    public FarmController(FarmService farmService) {
    this.farmService = farmService;
  }
  /** mapeamento. */

  @PostMapping()
    public ResponseEntity<Farm> createFarm(@RequestBody Farm farm) {
    Farm newFarm = farmService.insert(farm);
    return ResponseEntity.status(HttpStatus.CREATED).body(newFarm);
  }
  /** get. */

  @GetMapping
  public List<Farm> getAllFarm() {
    return farmService.getAllFarms();
  }

  /** retorna a fazendo específica pelo @Id. */
  @GetMapping("/{id}")
  public ResponseEntity getByIdFarm(@PathVariable Long id) {
    Optional<Farm> farms = farmService.getByIdFarm(id);

    if (farms.isEmpty()) {
      String farmNotFound = "Fazenda não encontrada!";
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(farmNotFound);
    }
    return ResponseEntity.ok(farms);
  }

  @Autowired
  private CropService cropService;

  /** Cria uma nova plantação em uma fazenda específica. */
  @PostMapping("/{farmId}/crops")

  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity createCrop(@PathVariable Long farmId, @RequestBody Crop crop) {
    Optional<Farm> farm = farmService.getByIdFarm(farmId);

    if (farm.isEmpty()) {
      String farmNotFound = "Fazenda não encontrada!";
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(farmNotFound);
    }

    Crop newCrop = cropService.insertCrop(crop, farm.get());
    return  ResponseEntity.status(HttpStatus.CREATED).body(newCrop);
  }

  /**
   * Route Crop Get By id.
   */
  @GetMapping("/{farmId}/crops")
  public ResponseEntity<?> getCropsByIdFarm(@PathVariable Long farmId) {
    Optional<Farm> farm = farmService.getByIdFarm(farmId);
    if (farm.isEmpty()) {
      String responseEmpty = "Fazenda não encontrada!";
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseEmpty);
    }
    List<Crop> allCrops = cropService.getCropByIdFarm(farm.get());
    return ResponseEntity.ok(allCrops);
  }
}