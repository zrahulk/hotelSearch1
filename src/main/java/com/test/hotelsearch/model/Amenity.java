package com.test.hotelsearch.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="amenities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Amenity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int amenityId;
    private String name;

    @JsonBackReference
    @OneToMany(mappedBy = "amenity", cascade = CascadeType.ALL)
    private List<HotelRoomAmenity> hotelRoomAmenity = new ArrayList<>();

    public Amenity(String name) {
        this.name = name;
    }
    public void addHotelRoomAmenity(HotelRoomAmenity hotelRoomAmenity)
    {
        this.hotelRoomAmenity.add(hotelRoomAmenity);
        hotelRoomAmenity.setAmenity(this);

    }

    public void removeHotelRoomAmenity(HotelRoomAmenity hotelRoomAmenity)
    {
        this.hotelRoomAmenity.remove(hotelRoomAmenity);
        hotelRoomAmenity.setAmenity(null);
    }

}
