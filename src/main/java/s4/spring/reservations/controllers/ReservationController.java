package s4.spring.reservations.controllers;
import java.security.Principal;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.jeemv.springboot.vuejs.VueJS;
import io.github.jeemv.springboot.vuejs.utilities.Http;
import s4.spring.reservations.services.MyUserDetails;
import s4.spring.reservations.utilities.VueDataManager;

@Controller
public class ReservationController {
	
	@Autowired
	private VueJS vue;

	
	
	@RequestMapping("/reservation")
    public String lodgementPage( ModelMap model,@AuthenticationPrincipal MyUserDetails user) {
		VueDataManager vuemanager = new VueDataManager();
		vue.addDataRaw("reservations","[]");
		vue = vuemanager.addSimpleMenuRequiredData(vue);
		vue = vuemanager.addDrawerRequiredData(user, vue);
		vue.onBeforeMount(""
				+ "this.getMyAvatar();"
				+ "let self=this;" + Http.get("http://127.0.0.1:8080/rest/reservations/my", "self.reservations=response.data;"));
	    model.put("vue", vue);
        return "reservation";
       }
	

}