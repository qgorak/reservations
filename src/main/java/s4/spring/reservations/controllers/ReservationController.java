package s4.spring.reservations.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import io.github.jeemv.springboot.vuejs.VueJS;
import io.github.jeemv.springboot.vuejs.utilities.Http;
import s4.spring.reservations.services.MyUserDetails;

@Controller
public class ReservationController {
	
	@Autowired
	private VueJS vue;
	
	@ModelAttribute(name = "vue")
	private VueJS getVue() {
		this.vue.addDataRaw("user", "{id:'',login:''}");
		return this.vue;
	}

	@RequestMapping("/reservation")
    public String lodgementPage( ModelMap model,@AuthenticationPrincipal MyUserDetails user) {
		vue.addDataRaw("reservations","''");
		vue.onBeforeMount("let self=this;" + Http.get("/rest/reservations/my", "self.reservations=response.data"));
        return "reservation";
       }
	

}