package com.test.hotelsearch.repositories;

import com.test.hotelsearch.model.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmenityRepository extends JpaRepository<Amenity,Integer> {
    Amenity findByAmenityId(int id);
}
