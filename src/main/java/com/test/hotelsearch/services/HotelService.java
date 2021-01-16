package com.test.hotelsearch.services;

import com.test.hotelsearch.model.Hotel;
import com.test.hotelsearch.model.RoomType;
import com.test.hotelsearch.repositories.HotelRepository;
import com.test.hotelsearch.repositories.HotelRoomAmenityRepository;
import com.test.hotelsearch.repositories.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepo;
    @Autowired
    private RoomTypeRepository roomTypeRepository;
    @Autowired
    private HotelRoomAmenityRepository hraRepo;

    @Transactional
    public Hotel addHotel(String hotelName, String roomTypeName)
    {
        Hotel hotel = new Hotel();
        hotel.setName(hotelName);
        RoomType roomType = new RoomType(roomTypeName);
        roomTypeRepository.save(roomType);
        hotel.addRoomType(roomType);
        hotelRepo.save(hotel);
        roomType.setHotel(hotel);

        //deleteRoomType();
        deleteHotel();
        return hotel;

    }





    @Transactional
    private void deleteRoomType()
    {
        RoomType roomType = roomTypeRepository.findByRoomTypeId(11);
        roomTypeRepository.delete(roomType);
        roomType.removeHotel();
    }

    @Transactional
    private void deleteHotel()
    {
        Hotel hotel = hotelRepo.findByHotelId(22);
        hotelRepo.delete(hotel);
       // hotel.removeRoomTypes();
    }

    public Set<RoomType> getHotelAllInfo(int hotelId){
        Hotel hotel = hotelRepo.findByHotelId(hotelId);
        return hotel.getRoomTypes();

    }

    public Map<String,Object> getHotelById(int hotelId)
    {
        Hotel hotel = hotelRepo.findByHotelId(hotelId);
        Map<String,Object> map = new HashMap<>();
        map.put("hotel",hotel);
        map.put("roomTypes",hotel.getRoomTypes());
        map.put("hotelRoomAmenities",hotel.getHotelRoomAmenity());
        return map;
    }



}
