package com.test.hotelsearch.services;

import com.test.hotelsearch.model.Amenity;
import com.test.hotelsearch.model.Hotel;
import com.test.hotelsearch.model.HotelRoomAmenity;
import com.test.hotelsearch.model.RoomType;
import com.test.hotelsearch.repositories.AmenityRepository;
import com.test.hotelsearch.repositories.HotelRepository;
import com.test.hotelsearch.repositories.HotelRoomAmenityRepository;
import com.test.hotelsearch.repositories.RoomTypeRepository;
import com.test.hotelsearch.requests.DeleteHotelRoomAmenity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class HotelRoomAmenityService {
    @Autowired
    private HotelRoomAmenityRepository hraRepo;
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private RoomTypeRepository roomTypeRepository;
    @Autowired
    private AmenityRepository amenityRepository;

    @Transactional
    public Set<HotelRoomAmenity> addHotelRoomAmenity()
    {
        Hotel hotel  = new Hotel("raja ka hotel");
        hotelRepository.save(hotel);
        RoomType deluxe_room = new RoomType("Deluxe room");
        RoomType semiDeluxe = new RoomType("Semi Deluxe");
        RoomType ordinaryRoom = new RoomType("Ordinary");
        RoomType[]rTypes = new RoomType[]{deluxe_room,semiDeluxe,ordinaryRoom};
        List<RoomType> list  = Arrays.asList(rTypes);
        Set<RoomType> set = new HashSet<>(list);
        roomTypeRepository.saveAll(set);
        hotel.addRoomTypes(set);

        Amenity wifi = new Amenity("Wi-fi");
        Amenity refrigerator = new Amenity("Refrigerator");
        Amenity valet = new Amenity("Valet");
        Amenity[]amenities = new Amenity[]{
                wifi,refrigerator,valet
        };

        Set<Amenity> amenitySet = new HashSet<>(Arrays.asList(amenities));
        amenityRepository.saveAll(amenitySet);
        Set<HotelRoomAmenity> hraSet = new HashSet<>();
        for (RoomType rt:set
             ) {

            for (Amenity amenity: amenitySet
                 ) {
                hraSet.add(new HotelRoomAmenity(hotel,rt,amenity));
            }
        }
        hraRepo.saveAll(hraSet);
        for (HotelRoomAmenity hra:hraSet){
            hotel.addHotelRoomAmenity(hra);
            if(hra.getRoomType().getName().equals(deluxe_room.getName()))
            {
                deluxe_room.addHotelRoomAmenity(hra);
            }
            else if((hra.getRoomType().getName().equals(ordinaryRoom.getName())))
            {
                ordinaryRoom.addHotelRoomAmenity(hra);
            }
            else if((hra.getRoomType().getName().equals(semiDeluxe.getName())))
            {
                ordinaryRoom.addHotelRoomAmenity(hra);
            }
            if(hra.getAmenity().getName().equals(wifi.getName()))
            {
                wifi.addHotelRoomAmenity(hra);
            }
            if(hra.getAmenity().getName().equals(refrigerator.getName()))
            {
                refrigerator.addHotelRoomAmenity(hra);
            }
            if(hra.getAmenity().getName().equals(valet.getName()))
            {
                valet.addHotelRoomAmenity(hra);
            }
        }

    return hraSet;
    }

    @Transactional
    public String deleteHotelRoomAmenity(DeleteHotelRoomAmenity dHRA)
    {
        int hotelId = dHRA.getHotelId();
        int amenityId = dHRA.getAmenityId();
        int roomTypeId = dHRA.getRoomTypeId();
        Hotel hotel = hotelRepository.findByHotelId(hotelId);
        Amenity amenity = amenityRepository.findByAmenityId(amenityId);
        RoomType roomType = roomTypeRepository.findByRoomTypeId(roomTypeId);
        List<HotelRoomAmenity> hotelRoomAmenity =
                hraRepo.findByHotelAndRoomTypeAndAmenity(hotel, roomType, amenity);
        hotelRoomAmenity.stream().forEach(hra->{
            hraRepo.delete(hra);
            hotel.removeHotelRoomAmenity(hra);
            roomType.removeHotelRoomAmenity(hra);
            amenity.removeHotelRoomAmenity(hra);
        });

        return "Success";
    }
}
