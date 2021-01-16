package com.test.hotelsearch.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="hotels")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int hotelId;
    private String name;
    @JsonBackReference
    @OneToMany(mappedBy = "hotel", cascade = {CascadeType.ALL})
    Set<RoomType> roomTypes = new HashSet<>();
    @JsonBackReference
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<HotelRoomAmenity> hotelRoomAmenity = new ArrayList<>();

    public Hotel(String name) {
        this.name = name;
    }

    public Hotel(String name, Set<RoomType> roomTypes) {
        this.name = name;
        this.roomTypes = roomTypes;
    }

    public void addHotelRoomAmenity(HotelRoomAmenity hotelRoomAmenity)
    {
        this.hotelRoomAmenity.add(hotelRoomAmenity);
        hotelRoomAmenity.setHotel(this);

    }

    public void removeHotelRoomAmenity(HotelRoomAmenity hotelRoomAmenity)
    {
        this.hotelRoomAmenity.remove(hotelRoomAmenity);
        hotelRoomAmenity.setHotel(null);
    }
    public void addRoomType(RoomType roomType)
    {
        this.roomTypes.add(roomType);
        roomType.setHotel(this);
    }



    public void removeRoomType(RoomType roomType)
    {

        this.roomTypes.remove(roomType);
        roomType.setHotel(null);
    }

    public void removeRoomTypes()
    {
        Iterator<RoomType> iterator = this.getRoomTypes().iterator();
        while (iterator.hasNext())
        {
            RoomType rt = iterator.next();
            removeRoomType(rt);
            rt.setHotel(null);
        }

    }

    public void addRoomTypes( Set<RoomType> set)
    {
        this.roomTypes.addAll(set);
        set.stream().forEach(roomType -> roomType.setHotel(this));
    }


}
