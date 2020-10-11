package s4.spring.reservations.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import s4.spring.reservations.models.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer>{
	List<Reservation> findByLodgement_id(int id);
}
