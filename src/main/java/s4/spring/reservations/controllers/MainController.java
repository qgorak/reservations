package s4.spring.reservations.controllers;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import io.github.jeemv.springboot.vuejs.VueJS;
import io.github.jeemv.springboot.vuejs.utilities.Http;
import s4.spring.reservations.services.MyUserDetails;
import s4.spring.reservations.utilities.VueDataManager;

@Controller
public class MainController {
	
	@Autowired
	private VueJS vue;
    
	@GetMapping(value = "/")
    public String index(ModelMap model,@AuthenticationPrincipal MyUserDetails user,Principal principal) {


		
		vue.addData("file",null);
		vue.addMethod("postAvatar", "let self=this;let formData = new FormData();formData.append('file', this.file);"
				+"this.$http['post'](\"/rest/image/saveAvatar\", formData, {\r\n"
				+ "      headers: {\r\n"
				+ "        \"Content-Type\": \"multipart/form-data\"\r\n"
				+ "      }})");

		//breadcrub menu
		VueDataManager vuemanager = new VueDataManager();
		vue = vuemanager.addDatePickerRequiredData(vue);
		vue = vuemanager.addSearchMenuRequiredData(vue);
		vue = vuemanager.addDrawerRequiredData(user, vue);
		vue.onBeforeMount("this.getMyAvatar();");
	    model.put("vue", vue);
        return "index";
       }
}