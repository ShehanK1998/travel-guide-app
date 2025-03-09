package com.treklanka.version1.controller;

import com.treklanka.version1.model.Restaurant;
import com.treklanka.version1.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/restaurants")
@CrossOrigin(origins = "http://localhost:5173")
public class RestaurantController {

    @Autowired
    private RestaurantRepository resturantRepository;

    @GetMapping
    public List<Restaurant> getAllRestaurants(){
        return resturantRepository.findAll();
    }
    @GetMapping("/district/{districtId}")
    public List<Restaurant> getRestaurantsByDistrict(@PathVariable int districtId){
        return resturantRepository.findByDistrictId(districtId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable int id){
        Optional<Restaurant> restaurant = resturantRepository.findById(id);
        return restaurant.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping
    public Restaurant createRestaurant(@RequestBody Restaurant restaurant){
        return resturantRepository.save(restaurant);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable int id, @RequestBody Restaurant updateRestaurant){
        return resturantRepository.findById(id).map(restaurant->{
            restaurant.setName(updateRestaurant.getName());
            restaurant.setCuisineType(updateRestaurant.getCuisineType());
            restaurant.setContactInfo(updateRestaurant.getContactInfo());
            restaurant.setDistrictId(updateRestaurant.getDistrictId());
            restaurant.setImageUrl(updateRestaurant.getImageUrl());
            return ResponseEntity.ok(resturantRepository.save(restaurant));
        }).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable int id){
        if(resturantRepository.existsById(id)){
            resturantRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}
