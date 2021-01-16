package com.test.hotelsearch.services;

import com.test.hotelsearch.model.Hotel;
import com.test.hotelsearch.model.RoomType;
import com.test.hotelsearch.repositories.HotelRepository;
import com.test.hotelsearch.repositories.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoomTypeService {

    @Autowired
    private RoomTypeRepository roomTypeRepository;
    @Autowired
    private HotelRepository hotelRepository;

    @Transactional
    public RoomType addRoomType() {
        String name = "semiDelux";
        RoomType roomType = new RoomType(name);
        Hotel hotel = hotelRepository.findByHotelId(20);
        roomTypeRepository.save(roomType);
        hotel.addRoomType(roomType);
        return roomType;

    }



}
