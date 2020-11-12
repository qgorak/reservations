package s4.spring.reservations.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import s4.spring.reservations.models.Destination;
import s4.spring.reservations.repositories.DestinationRepository;
import s4.spring.reservations.services.MyUserDetails;


@CrossOrigin
@RestController
@RequestMapping("/rest/destinations")
public class RestDestinationController extends AbstractRestController<Destination>{
	
	@Autowired
	public RestDestinationController(DestinationRepository repo) {
		super(repo);
	}




	@Override
	protected void addObject(Destination Destination,MyUserDetails user) {
	}
	
	@Override
	protected void updateObject(Destination toUpdateObject, Destination originalObject) {
	}
	
}