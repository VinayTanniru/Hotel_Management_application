package repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hotel.management.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
	
	@Query("SELECT DISTINCE r.roomType FROM Room r")
	List<String> findDistinctRoomTypes();
	
	@Query("SELECT r FROM Room r WHERE r.roomType LIKE %:roomType AND r.id NOT IN(SELECT bk.room.id FROM Booking bk WHERE ((bk.checkInDate<= :checkoutDate) AND (bk.checkoutDate >=:checkInDate))")
	List<Room> findAvailableRoomsByDatesAndTypes(LocalDate checkInDate,LocalDate checkoutDate,String roomType);
	
	@Query("SELECT r FROM Room r WHERE r.id NOT IN (SELECT b.room.id FROM Booking b)")
	List<Room> getAllAvailabeRooms();
}
