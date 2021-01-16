package com.test.hotelsearch.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeleteHotelRoomAmenity {
    private int hotelId;
    private int roomTypeId;
    private int amenityId;
}

