package s4.spring.reservations.controllers;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import s4.spring.reservations.models.Lodgement;
import s4.spring.reservations.models.Reservation;
import s4.spring.reservations.models.User;
import s4.spring.reservations.repositories.LodgementRepository;
import s4.spring.reservations.repositories.ReservationRepository;
import s4.spring.reservations.repositories.UserRepository;
import s4.spring.reservations.services.MyUserDetails;


@CrossOrigin
@RestController
@RequestMapping("/rest/lodgements")
public class RestLodgementController extends AbstractRestController<Lodgement>{
	
	@Autowired
	public RestLodgementController(LodgementRepository repo) {
		super(repo);
	}

	@Autowired
    private ReservationRepository repoRes;
	
	@Autowired
    private UserRepository repoUs;


	@GetMapping("/my")
	public List<Lodgement> getLodgementByIdUser(@AuthenticationPrincipal MyUserDetails user) {
		return ((LodgementRepository) repo).findByRentId(user.getId());	
	}
		
	@GetMapping("/search")
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
		List<Lodgement> result=((LodgementRepository) repo).findByParamater(latDMax,latDMin,lonDMax,lonDMin);
			for(Lodgement i : ((LodgementRepository) repo).findByParamater(latDMax,latDMin,lonDMax,lonDMin)) {
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


	
	

	@Override
	protected void addObject(Lodgement lodgement,MyUserDetails user) {
		if(user != null) {
		User creator = new User();
		creator = repoUs.findById(user.getId());;
		lodgement.setRent(creator);
		repo.saveAndFlush(lodgement);
		}
	}
	
	@Override
	protected void updateObject(Lodgement toUpdateObject, Lodgement originalObject) {
		toUpdateObject.setStatus(originalObject.getStatus());
		toUpdateObject.setDescription(originalObject.getDescription());
		toUpdateObject.setTitle(originalObject.getTitle());
		toUpdateObject.setPrice(originalObject.getPrice());
	}
	
}