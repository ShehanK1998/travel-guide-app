package com.treklanka.version1.controller;

import com.treklanka.version1.model.Driver;
import com.treklanka.version1.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/drivers")
@CrossOrigin(origins = "http://localhost:5173")
public class DriverController {

    @Autowired
    private DriverRepository driverRepository;

    @GetMapping
    public List<Driver> getAllDrivers(){
        return driverRepository.findAll();
    }
    @GetMapping("/district/{districtId}")
    public List<Driver> getDriversByDistricts(@PathVariable int districtId){
        return driverRepository.findByDistrictId(districtId);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Driver> getDriverById(@PathVariable int id){
        Optional<Driver> driver = driverRepository.findById(id);
        return driver.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping()
    public Driver createDriver(@RequestBody Driver driver){
        return driverRepository.save(driver);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Driver> updateDriver(@PathVariable int id, @RequestBody Driver updateDriver){
        return driverRepository.findById(id).map(driver -> {
            driver.setName(updateDriver.getName());
            driver.setPhoneNumber(updateDriver.getPhoneNumber());
            driver.setVehicleType(updateDriver.getVehicleType());
            driver.setDistrictId(updateDriver.getDistrictId());
            driver.setDriverImgUrl(updateDriver.getDriverImgUrl());
            driver.setVehicleImgUrl(updateDriver.getVehicleImgUrl());
            return ResponseEntity.ok(driverRepository.save(driver));
        }).orElseGet(()->ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDriver(@PathVariable int id){
        if(driverRepository.existsById(id)){
            driverRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
