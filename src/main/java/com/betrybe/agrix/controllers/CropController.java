package com.betrybe.agrix.controllers;

import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.service.CropService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/** controller da rota /crops. */
@RestController
@RequestMapping("/crops")
public class CropController {
  private final CropService cropService;

  @Autowired
    public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  @GetMapping
    public List<Crop> getAllCrops() {
    return cropService.getAllCrops();
  }

  /** pesquisa pela plantação específica. */
  @GetMapping("/{id}")
    public ResponseEntity getByIdCrop(@PathVariable Long id) {
    Optional<Crop> crop = cropService.getByIdCrop(id);

    if (crop.isEmpty()) {
      String cropNotFound = "Plantação não encontrada!";
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cropNotFound);
    }
    return ResponseEntity.ok(crop);
  }
  /**
   * pesquisa pela data da plantação.
   */

  @GetMapping("/search")
  public ResponseEntity<List<Crop>> getSearchByHarvestDate(
          @RequestParam @DateTimeFormat(iso = ISO.DATE)LocalDate start,
          @RequestParam @DateTimeFormat(iso = ISO.DATE)LocalDate end
  ) {
    List<Crop> allCrops = cropService.getSearchByHarvestDate(start, end);
    return ResponseEntity.ok(allCrops);
  }
}