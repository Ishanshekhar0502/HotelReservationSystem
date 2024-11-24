package com.booking.reservationManagement.repos;

import com.booking.reservationManagement.entity.RoomAvailability;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface RoomAvailabilityRepository extends JpaRepository<RoomAvailability, Integer> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT ra FROM RoomAvailability ra WHERE ra.room.roomId = :roomId AND ra.date BETWEEN :startDate AND :endDate AND ra.status = true")
    List<RoomAvailability> findAvailableRoomsForDates(int roomId, LocalDate startDate, LocalDate endDate);
}
