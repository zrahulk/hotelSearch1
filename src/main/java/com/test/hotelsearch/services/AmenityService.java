package com.test.hotelsearch.services;

import com.test.hotelsearch.model.Amenity;
import com.test.hotelsearch.repositories.AmenityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AmenityService {
    @Autowired
    private AmenityRepository aRepo;

    public void deleteById(int amenityId)
    {
        Amenity amenity = aRepo.findByAmenityId(amenityId);
        aRepo.delete(amenity);


    }
}
