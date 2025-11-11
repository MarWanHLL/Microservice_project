package com.ems.controller;

import java.util.List;

import com.ems.DTO.EventDTO;
import com.ems.DTO.UserDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;

import com.ems.model.Booking;

import com.ems.service.BookingService;

import com.ems.utility.GeneralUtil;

@CrossOrigin("http://localhost:4200/")
@RestController
@RequestMapping("/booking")
public class BookingController {

	@Autowired
	BookingService bookingService;

	/**
	 * returns booking list by userId
	 * */
	@GetMapping("/user/{id}")
	public ResponseEntity<List<Booking>> getBookingsByUserId(@PathVariable Integer id){
		
			return new ResponseEntity<List<Booking>>(bookingService.getBookingsByUserId(id), HttpStatus.OK);
		
	}
	
	/**
	 * returns booking List by eventId
	 * */
	@GetMapping("/event/{id}")
	public ResponseEntity<List<Booking>> getBookingsByEventId(@PathVariable int id){
		return new ResponseEntity<List<Booking>>(bookingService.getBookingsByEventId(id), HttpStatus.OK);
	}

	@PostMapping("/addBooking")
	public ResponseEntity<String> addBooking(@RequestBody Booking booking) {
		try {
			// Check if booking already exists for this user and event
			if (bookingService.getBookingByEventAndUserId(booking.getUserId(), booking.getEventId()) != null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("Booking already exists for this user and event");
			}

			bookingService.addBooking(booking);
			return ResponseEntity.status(HttpStatus.CREATED).body("Booking successfully added");

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error while creating booking: " + e.getMessage());
		}
	}


	@PutMapping("/updateBookingStatus/{id}/{status}")
	public ResponseEntity<String> updateStatus(@PathVariable Integer id,@PathVariable String status){
		bookingService.updateStatus(id,status);
		return new ResponseEntity<String>(GeneralUtil.toJson("updated"),HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteBooking/{id}")
	public ResponseEntity<String> deleteBooking(@PathVariable Integer id){
		bookingService.deleteBookingById(id);
		return new ResponseEntity<String>(GeneralUtil.toJson("deleted"),HttpStatus.OK);
	}
	
	/**
	 * returns the all types of booking
	 * */
	@GetMapping("/getAll")
	public ResponseEntity<List<Booking>> getAllBookings(){
		return new ResponseEntity<List<Booking>>(bookingService.getAllBooking(),HttpStatus.OK);
	}
	
	/**
	 * returns event by evenId
	 * */
	@GetMapping("/getById/{id}")
	public ResponseEntity<Booking> getBooking(@PathVariable Integer id){
		return new ResponseEntity<Booking>(bookingService.getBookingById(id),HttpStatus.OK);
	}
	
	/**
	 * Call it when you need is event booked or not
	 * */
	@GetMapping("/getEventAndUser/{eventid}/{userid}")
	public ResponseEntity<Boolean> getEventAndUser(@PathVariable int eventId, @PathVariable int userId){
		Boolean answer =true;
		if(bookingService.getBookingByEventAndUserId( userId,eventId)==null) {
			answer = false;
		}
		return new ResponseEntity<Boolean>(answer,HttpStatus.OK);
	}
	
	@GetMapping("/getcount")
	public ResponseEntity<String> getBookingsCount(){
		return new ResponseEntity<>(GeneralUtil.toJson(bookingService.getCount().toString()),HttpStatus.OK);
	}



	@GetMapping("/events")
	public List<EventDTO> getAllEvents() {
		return bookingService.getAllEvents();
	}

	@GetMapping("/events/{id}")
	public EventDTO getEventById(@PathVariable Long id) {
		return bookingService.getEventById(id);
	}

	@GetMapping("/users/{id}")
	public UserDTO getUserById(@PathVariable Long id) {
		return bookingService.getUserById(id);
	}

	@Value("${welcome.message}") private String welcomeMessage;
	@GetMapping ("/welcome") public String welcome () { return welcomeMessage;
	}

}
