package s4.spring.reservations.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.github.jeemv.springboot.vuejs.VueJS;
import io.github.jeemv.springboot.vuejs.utilities.Http;
import s4.spring.reservations.utilities.VueDataManager;

@Controller
public class UserController {
	
	@Autowired
	private VueJS vue;
	
	@RequestMapping("user/{userName}")
	 public String userPage(@PathVariable String userName, ModelMap model,Principal principal) {
		vue.addData("userData");
		vue.onBeforeMount("let self=this;" + Http.get("http://127.0.0.1:8080/rest/user/"+userName, "self.userData=response.data;"));
	    model.put("vue", vue);
        return "userDashboard";
       }
	
	@RequestMapping("/user/settings/{userName}")
    public String userSettings(@PathVariable String userName, ModelMap model,Principal principal) {
		VueDataManager vuemanager = new VueDataManager();
		vue.addDataRaw("nb", "[]");
		vue = vuemanager.addDatePickerRequiredData(vue);
		vue = vuemanager.addMenuRequiredData(principal,vue);
		
		// Form user settings
		vue.addData("userData");
		vue.addData("userName");
		vue.addData("userMail");
		vue.addData("userPassword");
		vue.addData("action", "http://127.0.0.1:8080/rest/user/update/" + userName);
		vue.onBeforeMount("let self=this;" + Http.get("http://127.0.0.1:8080/rest/user/"+userName, ""
				+ "self.userData=response.data;"
				+ "self.userName=self.userData.login;"
				+ "self.userMail=self.userData.mail;"));
		
		vue.addData("message", "Hello " + userName +" !");
		vue.addData("valid", true);
		vue.addData("select", "null");
		
		vue.addMethod("clear", ""
				+ "const instance = this;"
				+ "instance.userName = instance.userData.login;"
				+ "instance.userMail = instance.userData.mail;");
	    model.put("vue", vue);
        return "userSettings";
	}
}