package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.management.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
	
	List<Booking> findByRoomId(Long roomId);
	
	List<Booking> findByBookingConfirmationCode(String confirmatationCode);
	
	List<Booking> findByUserId(Long userid);

}
