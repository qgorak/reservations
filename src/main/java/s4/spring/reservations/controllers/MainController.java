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
		this.vue.addDataRaw("user", "{id:'',login:''}");
		return this.vue;
	}
    
	@GetMapping(value = "/")
    public String index(ModelMap model,@AuthenticationPrincipal MyUserDetails user) {
		this.vue.addMethod("explorer","fetch('https://jsonip.com', { mode: 'cors' })"
		+ "  .then((resp) => resp.json())"
		+ "  .then((response) => {"
		+ "		var url='http://ip-api.com/json/'+response.ip;"
		+ "		this.$http['get'](url).then(response => (window.location.replace('http://127.0.0.1:8080/lodgement/search?lon='+response.data.lon+'&lat='+response.data.lat)));"
		+ "  })");
        return "index";
       }
}