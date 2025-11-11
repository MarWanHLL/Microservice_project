package com.ems.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "booking")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {


	@GeneratedValue
	@Id
	Integer bookingId;
	@Column(name="status")
	String status;
	@Column(name = "user_id")
	private Integer userId;
	@Column(name = "event_id")
	private Integer eventId;

/*dff*/



}
