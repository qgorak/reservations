package s4.spring.reservations.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import s4.spring.reservations.models.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer>{
	List<Reservation> findByLodgement_id(int id);
    @Query(value = "SELECT * FROM RESERVATION r WHERE r.RENTED_ID = ?1", nativeQuery = true)
    public List<Reservation> findByRentId(int id);
    @Query(value = "SELECT * FROM RESERVATION r WHERE r.LODGEMENT_ID = ?1", nativeQuery = true)
    public List<Reservation> findByLodgementId(int id);
}
