package com.booking.reservationManagement.repos;

import com.booking.reservationManagement.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
