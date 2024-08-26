package com.hotel.management.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "bookings")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "check in date is required")
	private LocalDate checkInDate;

	@Future(message = "check out data must be in future")
	private LocalDate checkOutDate;

	@Min(value = 0, message = "count of the children must not be less than 0")
	private int numOfChildren;

	@Min(value = 1, message = "count of the adults must be minimum 1")
	private int numOfAdults;

	private int totalNumOfGuest;

	private String bookingConfirmationCode;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

	public void calculateTotalNumberOfGuest() {
		this.totalNumOfGuest = this.numOfAdults + this.numOfChildren;
	}

	public void setNumOfAdults(int numOfAdults) {
		this.numOfAdults = numOfAdults;
		calculateTotalNumberOfGuest();
	}

	public void setNumOfChildren(int numOfChildren) {
		this.numOfChildren = numOfChildren;
		calculateTotalNumberOfGuest();
	}

	@Override
	public String toString() {
		return "Booking [id=" + id + ", checkInDate=" + checkInDate + ", checkOutDate=" + checkOutDate
				+ ", numOfChildren=" + numOfChildren + ", numOfAdults=" + numOfAdults + ", totalNumOfGuest="
				+ totalNumOfGuest + ", bookingConfirmationCode=" + bookingConfirmationCode + "]";
	}

}
