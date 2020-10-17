package s4.spring.reservations.controllers;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import io.github.jeemv.springboot.vuejs.VueJS;
import s4.spring.reservations.services.MyUserDetails;
import s4.spring.reservations.utilities.VueDataManager;

@Controller
public class MainController {
	
	@Autowired
	private VueJS vue;
    
	@GetMapping(value = "/")
    public String index(ModelMap model,@AuthenticationPrincipal MyUserDetails user,Principal principal) {
		
		vue.onMounted("document.getElementById(\"application\").style.visibility = \"visible\";");
		

		//breadcrub menu
		VueDataManager vuemanager = new VueDataManager();
		vue = vuemanager.addDatePickerRequiredData(vue);
		vue = vuemanager.addSearchMenuRequiredData(vue);
		vue = vuemanager.addDrawerRequiredData(user, vue);
	    // date picker
	    model.put("vue", vue);
        return "index";
       }
}