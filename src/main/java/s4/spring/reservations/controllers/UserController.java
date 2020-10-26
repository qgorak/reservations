package s4.spring.reservations.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.github.jeemv.springboot.vuejs.VueJS;
import io.github.jeemv.springboot.vuejs.utilities.Http;
import s4.spring.reservations.services.MyUserDetails;
import s4.spring.reservations.utilities.VueDataManager;

@Controller
public class UserController {
	
	@Autowired
	private VueJS vue;
	
	@RequestMapping("user/{id}")
	 public String userPage(@PathVariable int id, ModelMap model,Principal principal) {
		vue.addData("userData");
		vue.onBeforeMount("let self=this;" + Http.get("http://127.0.0.1:8080/rest/user/"+id, "self.userData=response.data;"));
	    model.put("vue", vue);
        return "user";
       }
	
	@RequestMapping("/user/me")
    public String userSettings(@AuthenticationPrincipal MyUserDetails user, ModelMap model,Principal principal) {
		VueDataManager vuemanager = new VueDataManager();
		vue = vuemanager.addDatePickerRequiredData(vue);
		vue = vuemanager.addSimpleMenuRequiredData(vue);
		vue = vuemanager.addDrawerRequiredData(user,vue);
		if(user != null) {
		vue.addData("file",null);
		vue.addMethod("postAvatar", "let self=this;let formData = new FormData();formData.append('file', this.file);"
				+"this.$http['post'](\"/rest/image/saveAvatar\", formData, {\r\n"
				+ "      headers: {\r\n"
				+ "        \"Content-Type\": \"multipart/form-data\"\r\n"
				+ "      }})");
		vue.addMethod("updateUser", "let self=this;" + Http.patch("'http://127.0.0.1:8080/rest/users/'+self.user.id", "self.user", "alert('votre compte a bien été mis a jour');", ""));
		vue.onBeforeMount("let self=this;this.getMyAvatar();" + Http.get("http://127.0.0.1:8080/rest/users/me", ""
				+ "self.user=response.data;"));
	    model.put("vue", vue);
        return "userSettings";
		}
		vue.addData("error_message","vous devez etre connecté pour accéder a ce contenu");
		model.put("vue", vue);
		return "error";
	}
}