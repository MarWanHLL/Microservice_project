package com.ems.service;

import java.util.List;
import java.util.stream.Collectors;

import com.ems.Client.EventClient;
import com.ems.Client.UserClient;
import com.ems.DTO.EventDTO;
import com.ems.DTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.model.Booking;
import com.ems.repository.BookingRepo;

import javax.management.RuntimeErrorException;

@Service
public class BookingService {

	@Autowired
	BookingRepo bookingRepo;
	@Autowired
	 private EventClient EventClient;
	@Autowired
	 private UserClient UserClient;



	public boolean addBooking(Booking booking) {

		UserDTO user = UserClient.getUserById(Long.valueOf(booking.getUserId()));
		EventDTO event = EventClient.getEventById(Long.valueOf(booking.getEventId()));

		if (user == null || event == null) {
			throw new RuntimeException("Invalid user or event ID");
		}

		bookingRepo.save(booking);
		return true;
	}

	public boolean isEventBookedByUser(Integer userId, Integer eventId) {
		Booking booking = bookingRepo.getBookingByUserAndEventId(userId, eventId);
		return booking != null;
	}



	public boolean setBookingStatus(Integer id, String status) {
		Booking booking = bookingRepo.findById(id).orElseThrow(() -> new RuntimeException("Booking not found"));
		booking.setStatus(status);
		bookingRepo.save(booking);
		return true;
	}

	public boolean deleteBookingById(Integer booking_id) {
		bookingRepo.delete(bookingRepo.findById(booking_id).get());
		return true;
	}



	public Booking getBookingById(Integer id) {
		return bookingRepo.findById(id).orElse(null);
	}

	public Booking getBookingByEventAndUserId(Integer userId,Integer eventId) {
		return bookingRepo.getBookingByUserAndEventId(userId, eventId);
	}


	public List<Booking> getBookingsByUserId(Integer userId) {
		return bookingRepo.getBookingByUserId(userId);
	}

	public List<Booking> getBookingsByEventId(Integer eventId) {
		return bookingRepo.getBookingByEventId(eventId);
	}

	public List<Booking> getAllBooking(){
		return bookingRepo.findAll();
	}

	public void updateStatus(Integer id,String status) {
		Booking booking = bookingRepo.findById(id).orElseThrow(()-> new RuntimeException("Booking not found"));
		booking.setStatus(status);
		bookingRepo.save(booking);
	}

	public Long getCount() {

		return bookingRepo.count();
	}

	public List<EventDTO> getAllEvents() {
		return EventClient.getAllEvents();
	}

	public EventDTO getEventById(Long id) {
		return EventClient.getEventById(id);
	}

	public UserDTO getUserById(Long id) {
		return UserClient.getUserById(id);
	}



}
