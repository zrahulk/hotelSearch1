package com.test.hotelsearch.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="roomTypes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int roomTypeId;
    private String name;
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name="hotelId")
    private Hotel hotel;
    @JsonBackReference
    @OneToMany(mappedBy = "amenity", cascade = CascadeType.ALL)
    private List<HotelRoomAmenity> hotelRoomAmenity = new ArrayList<>();

    public RoomType(String name) {
        this.name = name;
    }

    public RoomType(String name, Hotel hotel) {
        this.name = name;
        this.hotel = hotel;
    }

    public void addHotel()
    {
        this.hotel.getRoomTypes().add(this);
    }
    public void removeHotel()
    {
        this.hotel.removeRoomType(this);
        this.hotel = null;
    }

    public void addHotelRoomAmenity(HotelRoomAmenity hotelRoomAmenity)
    {
        this.hotelRoomAmenity.add(hotelRoomAmenity);
        hotelRoomAmenity.setRoomType(this);

    }

    public void removeHotelRoomAmenity(HotelRoomAmenity hotelRoomAmenity)
    {
        this.hotelRoomAmenity.remove(hotelRoomAmenity);
        hotelRoomAmenity.setRoomType(null);
    }
}
