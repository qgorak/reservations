package s4.spring.reservations.controllers;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.github.jeemv.springboot.vuejs.VueJS;
import s4.spring.reservations.models.User;
import s4.spring.reservations.repositories.UserRepository;






@Controller
public class MainController {
	

	@Autowired
	private VueJS vue;
    
	@GetMapping("/")
    public String index(ModelMap model,Principal principal) {

		
		String username = (principal != null ? principal.getName() : "ANONYMOUS");
		vue.addData("user", username);
	    model.put("vue", vue);
        return "index";
        
       }
	
	

	
	@RequestMapping("/login")
    public String login(ModelMap model) {

		vue.addData("message", "Hello reservations");
	    model.put("vue", vue);
        return "login";
        
       }
	@RequestMapping("/new")
	public String displayNewOrgaModelMap(ModelMap model) {
		model.put("vue", vue);
		return "register";
	}
	

	
	@RequestMapping("/{idLogement}")
    public String index(@PathVariable int idLogement, ModelMap model) {

		vue.addData("message", "Hello Logement");
	    model.put("vue", vue);
        return "logement";
        
       }
}