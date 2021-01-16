package com.test.hotelsearch.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "hotelRoomAmenities")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HotelRoomAmenity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "hotelId",referencedColumnName = "hotelId")
    private Hotel hotel;
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "roomTypeId",referencedColumnName = "roomTypeId")
    private RoomType roomType;
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "amenityId",referencedColumnName = "amenityId")
    private Amenity amenity;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;
    public HotelRoomAmenity(Hotel hotel, RoomType roomType, Amenity amenity) {
        this.hotel = hotel;
        this.roomType = roomType;
        this.amenity = amenity;
    }

    public HotelRoomAmenity(Hotel hotel) {
        this.hotel = hotel;
    }

    public HotelRoomAmenity(Hotel hotel, RoomType roomType) {
        this.hotel = hotel;
        this.roomType = roomType;
    }

    public HotelRoomAmenity(RoomType roomType) {
        this.roomType = roomType;
    }

    public HotelRoomAmenity(Amenity amenity) {
        this.amenity = amenity;
    }

    public HotelRoomAmenity(Hotel hotel, Amenity amenity) {
        this.hotel = hotel;
        this.amenity = amenity;
    }

    public HotelRoomAmenity(RoomType roomType, Amenity amenity) {
        this.roomType = roomType;
        this.amenity = amenity;
    }

   public void addHotel()
   {
       this.hotel.getHotelRoomAmenity().add(this);

   }
    public void addAmenity()
    {
        this.amenity.getHotelRoomAmenity().add(this);

    }
    public void addRoomType()
    {
        this.roomType.getHotelRoomAmenity().add(this);

    }

    public void removeHotel() {
        this.hotel.removeHotelRoomAmenity(this);
        this.hotel = null;
    }

    public void removeAmenity() {
        this.amenity.removeHotelRoomAmenity(this);
        this.amenity = null;
    }

    public void removeRoomType() {
        this.roomType.removeHotelRoomAmenity(this);
        this.roomType = null;
    }
}
