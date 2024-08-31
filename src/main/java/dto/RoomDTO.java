package dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hotel.management.entity.Booking;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomDTO {

	private Long id;
	private String roomType;
	private String roomPrice;
	private String roomPhotoUrl;
	private String roomDescription;
	private List<Booking> bookings;

}
