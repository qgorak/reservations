package s4.spring.reservations.controllers;

import java.time.LocalDate;  
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import s4.spring.reservations.models.Reservation;
import s4.spring.reservations.repositories.ReservationRepository;


@CrossOrigin
@RestController
@RequestMapping("/rest/")
public class RestReservationController {
	
	@Autowired
    private ReservationRepository repo;

	@GetMapping("/reservation/{log_id}")
	public List<Reservation> readLogement(@PathVariable int log_id) {
		return repo.findByLodgement_id(log_id);	
	}
	
}