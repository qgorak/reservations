package s4.spring.reservations.controllers;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import s4.spring.reservations.models.Lodgement;
import s4.spring.reservations.models.Reservation;
import s4.spring.reservations.models.User;
import s4.spring.reservations.repositories.LodgementRepository;
import s4.spring.reservations.repositories.ReservationRepository;
import s4.spring.reservations.repositories.UserRepository;


@CrossOrigin
@RestController
@RequestMapping("/rest/")
public class RestLodgementController {
	
	@Autowired
    private LodgementRepository repo;
	
	@Autowired
    private ReservationRepository repoRes;
	
	@Autowired
    private UserRepository repoUs;

	@GetMapping("/lodgements/")
	public List<Lodgement> read() {
		return repo.findAll();	
	}
	
	@GetMapping("/lodgement/{id}")
	public Lodgement read(@PathVariable int id) {
		return repo.findById(id);
	}
	
	@GetMapping("/lodgement/search")
	public List<Lodgement> localisation(@RequestParam(name="nbr") String nbr,@RequestParam(name="start") String start,@RequestParam(name="end") String end,@RequestParam(name="lat") String lat,@RequestParam(name="lon") String lon) throws ParseException{
		double radiusOfSearch=20; //distance en km autour de laquelle on cherche des résultats 
		double radiusOfEarth=6371; //6371km, le rayon de la terre
		double r=(radiusOfSearch/radiusOfEarth)*180/Math.PI; 
		double latD=Double.parseDouble(lat);
		double lonD=Double.parseDouble(lon); 
		double latDMax=latD+r;
		double latDMin=latD-r;
		double lonDMax=lonD+r;
		double lonDMin=lonD-r;
		List<Lodgement> result=repo.findByParamater(latDMax,latDMin,lonDMax,lonDMin);
			for(Lodgement i : repo.findByParamater(latDMax,latDMin,lonDMax,lonDMin)) {
				List<Reservation> reserv=repoRes.findByLodgement_id(i.getId());
				if(!nbr.equals("null") && Integer.parseInt(nbr)>i.getNbr_place()){
					result.remove(i);
					continue;
				}
				if(!start.equals("null") && !end.equals("null")) {
					SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
					format.setTimeZone(TimeZone.getTimeZone("UTC+2"));
					Date startD=format.parse(start);
					Date endD=format.parse(end);
					for (Reservation y : reserv) {
						if(startD.equals(y.getEnd()) || endD.equals(y.getStart())) {
							continue;
						}
						if(startD.before(y.getStart()) && endD.after(y.getStart())) {
							result.remove(i);
							break;
						}
						if(startD.after(y.getStart()) && startD.before(y.getEnd())) {
							result.remove(i);
							break;
						}
					}
				}
			}
		return result;
	}

	@PostMapping("/lodgement/create")
    public Lodgement create(@RequestBody Lodgement lodgement,Principal principal,HttpServletRequest request) {
		User creator = new User();
		creator = repoUs.getUserByLogin(principal.getName());;
		System.out.print(creator.getRole());
        String test = creator.getRole();
		if (creator.getRole().equals("ROLE_USER"))
		{
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			List<GrantedAuthority> updatedAuthorities = new ArrayList<>(auth.getAuthorities());
			updatedAuthorities.add(new SimpleGrantedAuthority("ROLE_HOST"));
			Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);
			SecurityContextHolder.getContext().setAuthentication(newAuth);
			creator.setRole("ROLE_HOST");
		}
			lodgement.setRent(creator);
			repo.saveAndFlush(lodgement);
			
			
			return lodgement;
			
			
    }
	
	@DeleteMapping("/lodgement/delete/{id}")
    public void delete(@PathVariable int id) {
		repo.deleteById(id);
    }
	
	@PostMapping("lodgement/update/{id}")
    public Lodgement update(@PathVariable int id,@RequestBody Lodgement Lodgement) {

	
		return Lodgement;

    }
	
}