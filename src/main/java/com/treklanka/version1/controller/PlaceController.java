package com.treklanka.version1.controller;

import com.treklanka.version1.model.Place;
import com.treklanka.version1.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/places")
@CrossOrigin(origins = "http://localhost:5173")
public class PlaceController {
    @Autowired
    private PlaceRepository placeRepository;

    @GetMapping
    public List<Place> getAllPlaces(){
        return placeRepository.findAll();
    }
    @GetMapping("/district/{districtId}")
    public List<Place> getPlaceByDistrict(@PathVariable int districtId){
        return placeRepository.findByDistrictId(districtId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Place> getPlaceById(@PathVariable int id){
        Optional<Place> place = placeRepository.findById(id);
        return place.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }
    @PostMapping
    public Place createPlace(@RequestBody Place place){
        return placeRepository.save(place);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Place> updatePlace(@PathVariable int id, @RequestBody Place updatePlace){
        return placeRepository.findById(id).map(place ->{
            place.setName(updatePlace.getName());
            place.setDescription(updatePlace.getDescription());
            place.setImageUrl(updatePlace.getImageUrl());
            place.setDistrictId(updatePlace.getDistrictId());
            return ResponseEntity.ok(placeRepository.save(place));
        }).orElseGet(()-> ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlace(@PathVariable int id){
        if (placeRepository.existsById(id)){
            placeRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

