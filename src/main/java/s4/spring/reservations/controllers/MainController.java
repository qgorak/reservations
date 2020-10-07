package s4.spring.reservations.controllers;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import io.github.jeemv.springboot.vuejs.VueJS;
import io.github.jeemv.springboot.vuejs.utilities.Http;






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
	public String displayNewOrga(ModelMap model) {
		model.put("vue", vue);
		return "register";
	}
	
	@RequestMapping("/search/")
	public String search(ModelMap model,@Param("loca") String loca) {
		vue.addDataRaw("lodgements","[]");
		vue.addData("lodgementsPerPage", 4);
		vue.addData("expand",false);
		vue.onBeforeMount("let self=this;" + Http.get("http://localhost:8080/rest/lodgement/search/"+loca, "self.lodgements=response.data;"));
		model.put("vue", vue);
		return "search";
	}
	

	
	@RequestMapping("lodgement/{idLogement}")
    public String lodgementPage(@PathVariable int idLogement, ModelMap model) {
		vue.addData("lodgement");
		vue.onBeforeMount("let self=this;" + Http.get("http://localhost:8080/rest/lodgement/"+idLogement, "self.lodgement=response.data;"));
		vue.addData("message", "Hello Logement");
		
	    model.put("vue", vue);
        return "lodgement";
        
       }
}