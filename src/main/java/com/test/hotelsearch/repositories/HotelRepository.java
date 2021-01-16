package com.test.hotelsearch.repositories;

import com.test.hotelsearch.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
    Hotel findByHotelId(int hotelId);

}
