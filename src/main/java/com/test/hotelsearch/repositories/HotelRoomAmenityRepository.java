package com.test.hotelsearch.repositories;

import com.test.hotelsearch.model.Amenity;
import com.test.hotelsearch.model.Hotel;
import com.test.hotelsearch.model.HotelRoomAmenity;
import com.test.hotelsearch.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRoomAmenityRepository extends JpaRepository<HotelRoomAmenity,Integer> {
    HotelRoomAmenity findById(int id);
    List<HotelRoomAmenity> findByHotelAndRoomTypeAndAmenity(Hotel hotel, RoomType roomType, Amenity amenity);
}

