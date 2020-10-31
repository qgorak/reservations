package s4.spring.reservations.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import io.github.jeemv.springboot.vuejs.VueJS;
import s4.spring.reservations.services.MyUserDetails;

@Controller
public class MainController {
	
	@Autowired
	private VueJS vue;
	
	@ModelAttribute(name = "vue")
	private VueJS getVue() {
		return this.vue;
	}
    
	@GetMapping(value = "/")
    public String index(ModelMap model,@AuthenticationPrincipal MyUserDetails user) {
		if(user != null) {
		vue.addData("user",user.getUser());
		}else {
		vue.addDataRaw("user", "{id:0,login:''}");
		}
        return "index";
       }
}