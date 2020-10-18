package s4.spring.reservations.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import s4.spring.reservations.models.Lodgement;
import s4.spring.reservations.models.Reservation;
import s4.spring.reservations.models.User;
import s4.spring.reservations.repositories.LodgementRepository;
import s4.spring.reservations.repositories.ReservationRepository;
import s4.spring.reservations.services.MyUserDetails;


@CrossOrigin
@RestController
@RequestMapping("/rest/reservations")
public class RestReservationController extends AbstractRestController<Reservation>{
	
	@Autowired
	public RestReservationController(ReservationRepository repo) {
		super(repo);
	}
	@Autowired
    private LodgementRepository lrepo;
	
	
	@GetMapping("/my")
    public List<Reservation> read(@AuthenticationPrincipal MyUserDetails user) {
		List<Reservation> reservations = ((ReservationRepository) repo).findByRentId(user.getId());
		return reservations;
    }
	
	@Override
	protected void addObject(Reservation reservation,MyUserDetails user) {
		reservation.setRented(user.getUser());
		repo.saveAndFlush(reservation);
	}
	
	@Override
	protected void updateObject(Reservation toUpdateObject, Reservation originalObject) {


	}
	
}