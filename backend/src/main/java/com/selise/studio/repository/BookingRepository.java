package com.selise.studio.repository;

import com.selise.studio.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    boolean existsByStudioIdAndBookingDateAndTimeSlot(Long studioId, LocalDate bookingDate, String timeSlot);

    List<Booking> findByStudioIdAndBookingDate(Long studioId, LocalDate bookingDate);
}
