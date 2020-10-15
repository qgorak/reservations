package s4.spring.reservations.controllers;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import io.github.jeemv.springboot.vuejs.VueJS;
import s4.spring.reservations.utilities.VueDataManager;

@Controller
public class MainController {
	
	@Autowired
	private VueJS vue;
    
	@GetMapping("/")
    public String index(ModelMap model,Principal principal) {
		
		vue.onMounted("document.getElementById(\"application\").style.visibility = \"visible\";");
		

		//breadcrub menu
		VueDataManager vuemanager = new VueDataManager();
		vue = vuemanager.addDatePickerRequiredData(vue);
		vue = vuemanager.addSearchMenuRequiredData(vue);
		vue = vuemanager.addDrawerRequiredData(principal, vue);
	    // date picker
	    model.put("vue", vue);
        return "index";
       }
}