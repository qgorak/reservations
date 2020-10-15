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
	
	@RequestMapping("/user/")
	public @ResponseBody String user(ModelMap model,Principal principal) {
		
	    model.put("vue", vue);
        return "test";
       }
	
	@RequestMapping("/user/settings/{username}")
    public String userPage(@PathVariable String username, ModelMap model,Principal principal) {
		VueDataManager vuemanager = new VueDataManager();
		vue.addDataRaw("nb", "[]");
		vue = vuemanager.addDatePickerRequiredData(vue);
		vue = vuemanager.addMenuRequiredData(principal,vue);
		
		vue.addData("userData");
		// TODO : appeler la method "defaultValue" dans le before mount ?
		vue.onBeforeMount("let self=this;" + Http.get("http://127.0.0.1:8080/rest/user/"+username, "self.userData=response.data;defaultValue();"));
		vue.addData("message", "Hello User");
		
		// Form user settings
		vue.addData("valid", true);
		vue.addData("userName", "");
		vue.addData("userMail", "");
		vue.addData("userPassword", "");
		vue.addData("select", "null");
		
		vue.addMethod("validate", "this.$refs.form.validate()");
		vue.addMethod("reset", "this.$refs.form.reset()");
		vue.addMethod("defaultValue", ""
				+ "const instance = this;"
				+ "instance.userName = instance.userData.login;"
				+ "instance.userMail = instance.userData.mail;");
	    model.put("vue", vue);
        return "userSettings";
	}
}