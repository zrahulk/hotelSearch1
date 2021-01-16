package com.test.hotelsearch.controller;

import com.test.hotelsearch.model.Hotel;
import com.test.hotelsearch.model.HotelRoomAmenity;
import com.test.hotelsearch.repositories.HotelRoomAmenityRepository;
import com.test.hotelsearch.repositories.RoomTypeRepository;
import com.test.hotelsearch.requests.DeleteHotelRoomAmenity;
import com.test.hotelsearch.services.AmenityService;
import com.test.hotelsearch.services.HotelRoomAmenityService;
import com.test.hotelsearch.services.HotelService;
import com.test.hotelsearch.services.RoomTypeService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("hotel")
public class HelloController {

	@Autowired
	private HotelService hotelService;
	@Autowired
	private RoomTypeRepository roomTypeRepository;
	@Autowired
	private RoomTypeService roomTypeService;
	@Autowired
	private HotelRoomAmenityService hraService;
	@Autowired
	private AmenityService aService;
	@Value("${hello.message}")
	private String helloMessage;
	
	@GetMapping("hello")
	public ResponseEntity<String> hello() {
		return ResponseEntity.ok(helloMessage);
	}

	@PostMapping("add")
	public ResponseEntity<?>addHotel()
	{
		Hotel hotel = hotelService.addHotel("jai veeru","delux");
		return new ResponseEntity<>(hotel, HttpStatus.CREATED);
	}

	@PostMapping("addRoomType")
	public ResponseEntity<?>addRoomType() {
		return new ResponseEntity<>(roomTypeService.addRoomType(),HttpStatus.CREATED);

	}

	@GetMapping("getHotelRoomType/{id}")
	public ResponseEntity<?> getHotelRoomTypeInfo(@PathVariable int id) {
		return new ResponseEntity<>(hotelService.getHotelAllInfo(id),HttpStatus.ACCEPTED);
	}

	@PostMapping("addHotelAmenities")
	public ResponseEntity<?> addHotelRoomAmenity()
	{
		Set<HotelRoomAmenity> hra = new HashSet<>();
		hra = hraService.addHotelRoomAmenity();

		return new ResponseEntity<>(hra,
				HttpStatus.CREATED);


	}

	@GetMapping("gethotel/{id}")
	public ResponseEntity<?>getHotelById(@PathVariable int id)
	{
		return new ResponseEntity<>(hotelService.getHotelById(id),
				HttpStatus.OK);
	}

	@DeleteMapping("deleteHotelRoomAmenity")
	public ResponseEntity<Object> deleteHotelRoomAmenity(
			@RequestBody DeleteHotelRoomAmenity dHRA)
	{
		hraService.deleteHotelRoomAmenity(dHRA);
		return ResponseEntity.ok(hraService.deleteHotelRoomAmenity(dHRA));
	}

	@DeleteMapping("delAmenityById/{id}")
	public ResponseEntity<?> delAmenityById(@PathVariable int id)
	{
		aService.deleteById(id);
		return ResponseEntity.ok("Success");
	}



}
