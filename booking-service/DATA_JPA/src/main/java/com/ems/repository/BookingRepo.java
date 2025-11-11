package com.ems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ems.model.Booking;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Integer>{

     
	@Query(value="select * FROM Booking where user_id=?1 ",nativeQuery=true)
    List<Booking> getBookingByUserId(Integer userId);
	
	@Query(value="select * FROM Booking where event_id=?1 ",nativeQuery=true)
	List<Booking> getBookingByEventId(Integer event_id);
	
     @Query(value="select * FROM Booking where user_id=?1 and event_id=?2",nativeQuery=true)
     Booking getBookingByUserAndEventId(Integer userId, Integer eventId);

	
}
