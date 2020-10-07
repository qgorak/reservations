package s4.spring.reservations.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import s4.spring.reservations.models.Lodgement;
import s4.spring.reservations.repositories.LodgementRepository;



@RestController
@RequestMapping("/rest/")
public class RestLodgementController {
	
	@Autowired
    private LodgementRepository repo;

	@GetMapping("/lodgements/")
	public List<Lodgement> read() {
		return repo.findAll();	
	}
	
	@GetMapping("/lodgement/{id}")
	public Lodgement read(@PathVariable int id) {
		return repo.findById(id);
	}
	
	@GetMapping("/lodgement/search/{loca}")
	public List<Lodgement> localisation(@PathVariable String loca) {
		return repo.search(loca);
	}
	

	@PostMapping("/lodgement/create")
    public Lodgement create(@RequestBody Lodgement Lodgement) {
		repo.save(Lodgement);
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