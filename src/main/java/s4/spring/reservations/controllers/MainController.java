package s4.spring.reservations.controllers;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import io.github.jeemv.springboot.vuejs.VueJS;
import io.github.jeemv.springboot.vuejs.utilities.Http;
import s4.spring.reservations.utilities.VueDataManager;

@Controller
public class MainController {
	
	@Autowired
	private VueJS vue;
	

    
	@GetMapping("/")
    public String index(ModelMap model,Principal principal) {
		vue.addDataRaw("nb","[1,2,3,4,5]");
		vue.onMounted("document.getElementById(\"application\").style.visibility = \"visible\";");
		//breadcrub menu
		VueDataManager vuemanager = new VueDataManager();
		vue = vuemanager.addDatePickerRequiredData(vue);
		vue = vuemanager.addMenuRequiredData(principal,vue);
	    // date picker
	    model.put("vue", vue);
        return "index";
       }
	
	

	

	

	

}