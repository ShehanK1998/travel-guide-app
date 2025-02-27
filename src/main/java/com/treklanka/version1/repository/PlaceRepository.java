package com.treklanka.version1.repository;

import com.treklanka.version1.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Integer> {
    List<Place> findByDistrictId(int districtId);
}
