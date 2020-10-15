package s4.spring.reservations.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import s4.spring.reservations.models.User;
import s4.spring.reservations.repositories.UserRepository;



@RestController
@RequestMapping("/rest/")
public class RestUserController {
	
	@Autowired
    private UserRepository repo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/users/")
	public List<User> read() {
		return repo.findAll();	
	}
	
	@GetMapping("/user/{username}")
	public User read(@PathVariable String username) {
		return repo.getUserByLogin(username);
	}
	
	@PostMapping("/user/create/")
    public User create(@RequestBody User user) {
	
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setEnabled(true);
		user.setRole("ROLE_USER");

		repo.save(user);
		return user;
    }
	
	@DeleteMapping("/users/delete/{id}")
    public void delete(@PathVariable int id) {
		repo.deleteById(id);
    }
	
	@PostMapping("users/update/{id}")
    public User update(@PathVariable int id,@RequestBody User User) {

	
		return User;

    }
	
}