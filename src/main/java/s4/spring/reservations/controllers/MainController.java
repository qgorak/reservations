package s4.spring.reservations.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import io.github.jeemv.springboot.vuejs.VueJS;






@Controller
public class MainController {
	
	@Autowired
	private VueJS vue;
    
	@RequestMapping("/")
    public String index(ModelMap model) {

		vue.addData("message", "Hello reservations");
	    model.put("vue", vue);
        return "index";
        
       }
	@RequestMapping("/{idLogement}")
    public String index(@PathVariable int idLogement, ModelMap model) {

		vue.addData("message", "Hello Logement");
	    model.put("vue", vue);
        return "logement";
        
       }
}