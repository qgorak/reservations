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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

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
	
	@GetMapping("/user/{userName}")
	public User read(@PathVariable String userName) {
		return repo.getUserByLogin(userName);
	}
	
	@PostMapping("/user/create/")
    public User create(@RequestBody User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setEnabled(true);
		user.setRole("ROLE_USER");

		repo.save(user);
		return user;
    }
	
	@DeleteMapping("/user/delete/{id}")
    public void delete(@PathVariable int id) {
		repo.deleteById(id);
    }
	
	@PostMapping("user/update/{userName}")
    public RedirectView update(@PathVariable String userName,@RequestParam String login,@RequestParam String mail) {
		User user = repo.getUserByLogin(userName);
		if((user!= null)) {
			user.setLogin(login);
			user.setMail(mail);
			repo.saveAndFlush(user);
		}
		return new RedirectView("http://127.0.0.1:8080/user/" + user.getLogin());
    }
	
}