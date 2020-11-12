package s4.spring.reservations.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import s4.spring.reservations.models.Reservation;
import s4.spring.reservations.models.User;
import s4.spring.reservations.repositories.ReservationRepository;
import s4.spring.reservations.repositories.UserRepository;
import s4.spring.reservations.services.MyUserDetails;



@RestController
@RequestMapping("/rest/users")
public class RestUserController extends AbstractRestController<User>{
	

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	public RestUserController(UserRepository repo) {
		super(repo);
	}

	@GetMapping("/user/{username}")
	public User read(@PathVariable String username) {
		
		return ((UserRepository) repo).getUserByLogin(username);
	}
	@GetMapping("/me")
	public User me(@AuthenticationPrincipal MyUserDetails user) {
		
		return ((UserRepository) repo).findById(user.getId());
	}
		
	@Override
	protected void addObject(User newuser,MyUserDetails user) {
		newuser.setPassword(passwordEncoder.encode(newuser.getPassword()));
		newuser.setEnabled(true);
		newuser.setRole("ROLE_USER");
		repo.saveAndFlush(newuser);
	}
	
	@Override
	protected void updateObject(User toUpdateObject, User originalObject) {
		toUpdateObject.setMail(originalObject.getMail());
		toUpdateObject.setLogin(originalObject.getLogin());
		toUpdateObject.setPassword(passwordEncoder.encode(originalObject.getPassword()));

	}
	

	
}