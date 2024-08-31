package dto;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
	
	private int  statusCode;
	private String message;
	private String token;
	private String role; 
	private int numOfAdults;
	private int totalNumOfGuest;
	private String expirationTime;
	private String bookingConfirmationCode;
	private UserDTO user;
	private RoomDTO room;
    private List<UserDTO> userList;
    private List<RoomDTO> roomList;
    private List<BookingDTO> bookingList;
}