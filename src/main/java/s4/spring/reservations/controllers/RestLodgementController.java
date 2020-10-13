package s4.spring.reservations.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import s4.spring.reservations.models.Lodgement;
import s4.spring.reservations.models.Reservation;
import s4.spring.reservations.repositories.LodgementRepository;
import s4.spring.reservations.repositories.ReservationRepository;


@CrossOrigin
@RestController
@RequestMapping("/rest/")
public class RestLodgementController {
	
	@Autowired
    private LodgementRepository repo;
	
	@Autowired
    private ReservationRepository repoRes;

	@GetMapping("/lodgements/")
	public List<Lodgement> read() {
		return repo.findAll();	
	}
	
	@GetMapping("/lodgement/{id}")
	public Lodgement read(@PathVariable int id) {
		return repo.findById(id);
	}
	
	@GetMapping("/lodgement/search/{lon}&{lat}&{start}&{end}&{nbr}")
	public List<Lodgement> localisation(@PathVariable String nbr,@PathVariable String start,@PathVariable String end,@PathVariable String lat,@PathVariable String lon) throws ParseException{
		double radiusOfSearch=2000; //distance en km autour de laquelle on cherche des résultats 
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
				if(!start.equals("undefined") && !end.equals("undefined")) {
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
    public Lodgement create(@RequestBody Lodgement Lodgement) {
		repo.saveAndFlush(Lodgement);
		return Lodgement;
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