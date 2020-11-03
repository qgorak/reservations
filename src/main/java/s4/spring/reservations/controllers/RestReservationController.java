package s4.spring.reservations.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import s4.spring.reservations.models.Reservation;
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
	
	@GetMapping("/lodgement/{id}")
    public List<Date> getFreeDate(@PathVariable int id) throws ParseException {
		List<Reservation> reservations = ((ReservationRepository) repo).findByLodgementId(id);
		List<Date> bookDate = new ArrayList<Date>();
		String strdate;
		Date start = new Date();
		for(Reservation resa : reservations) {
			bookDate.add(resa.getStart());
		}

		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
		System.out.print(start);
		Calendar c = Calendar.getInstance(); 
		c.setTime(start); 
		c.add(Calendar.DATE, 90);
		
		Date end = c.getTime();
		List<Date> totalDates = new ArrayList<Date>();
		while (!start.after(end)) {
			c.setTime(start);
		    strdate = formatter.format(c.getTime());
		    start = formatter.parse(strdate);
		    totalDates.add(start);
		    c.setTime(start);
		    c.add(Calendar.DATE, 1);
		}
		
		for(Date day : totalDates) {
			if(bookDate.contains(day)) {
				totalDates.remove(day);
			}
		}
		System.out.print(totalDates.get(0));
	

		return null;
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