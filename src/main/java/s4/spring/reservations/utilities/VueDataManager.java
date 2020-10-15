package s4.spring.reservations.utilities;

import java.io.IOException;
import java.security.Principal;

import io.github.jeemv.springboot.vuejs.VueJS;
import io.github.jeemv.springboot.vuejs.utilities.Http;
import io.github.jeemv.springboot.vuejs.utilities.JavascriptResource;

public class VueDataManager {
	
	public VueJS addDatePickerRequiredData(VueJS vue) {
		vue.addData("menu1", false);
		vue.addData("date");
		vue.addData("endDate","2020-10-30");
		vue.addDataRaw("dates", "[]");
		vue.addDataRaw("allowedDates","['2020-10-08', '2020-10-09']");
		vue.addDataRaw("dateFormatted", "this.formatDate(new Date().toISOString().substr(0, 10))");
		vue.addComputed("dateRangeText", "return this.dates.join(' au ')");
		
		try {vue.addMethod("getAllowedDates",JavascriptResource.create("getAllowedDates").parseContent(),"val");}
		catch (IOException e) {e.printStackTrace();}
		
		try {vue.addMethod("formatDate",JavascriptResource.create("formatDate").parseContent(),"date");}
		catch (IOException e) {e.printStackTrace();}
		
		try {vue.addMethod("addDays",JavascriptResource.create("addDays").parseContent(),"days");}
		catch (IOException e) {e.printStackTrace();}
		
		try {vue.addMethod("parseDate",JavascriptResource.create("parseDate").parseContent(),"date");}
		catch (IOException e) {e.printStackTrace();}
		
		vue.onBeforeMount("this.date = new Date().toLocaleDateString('fr-CA');");
		vue.addWatcher("dates", "console.log(this.dates)");
		
		return vue;
	
	}
	
	public VueJS addMenuRequiredData(Principal principal,VueJS vue) {
		String username;
		
		//drawer datas
		if (principal != null) {
			username = principal.getName();
			vue.addData("displayBtnLogin", "display:none");
			vue.addData("displayBtnLogout", "display:block");
		}
		else {
			username = "Anonyme";
			vue.addData("displayBtnLogin", "display:block");
			vue.addData("displayBtnLogout", "display:none");
		}
		vue.addData("user", username);
		vue.addData("drawer", false);
		vue.addDataRaw("items", " [{"
		+"                        'title': 'Logement',"
		+"                        'icon': 'mdi-home-city',"
		+"                        'link': '/lodgement/'," 
		+"                    }, {"
		+"                        'title': 'Reservation',"
		+"                        'icon': 'mdi-file-document-edit'," 
		+"                        'link': '/reservation/',"
		+"                    }, {"
		+"                        'title': 'Settings'," 
		+"                        'icon': 'mdi-cog-outline',"
		+"                        'link': '/user/settings/"+username+ "'," 
		+"                    }]");
		
		//login modal
		vue.addData("loginModal",false);
		
		//register modal data
		vue.addData("registerModal",false);
		vue.addData("valid",true);
		vue.addData("passwordConfirm","");
		vue.addDataRaw("usernameRules","[ v => !!v || 'Name is required',"
		+ "      v => (v && v.length <= 10) || 'Name must be less than 10 characters',]");
		vue.addDataRaw("emailRules","[ v => !!v || 'E-mail is required',"
		+ "      v => /.+@.+\\..+/.test(v) || 'E-mail must be valid',]");
		vue.addDataRaw("passwordRules","[]");
		vue.addDataRaw("newUser", "{login:'',password:'',mail:''}");

		vue.addMethod("registerUser", "let self=this;" + Http.post( "http://127.0.0.1:8080/rest/user/create/","self.newUser", "self.registerModal=false;"));

		vue.addData("e1",1);
		
		//Search autocomplete
		vue.addDataRaw("search","[]");
		vue.addDataRaw("destination","{name:null,loc:null}");
		vue.addData("nbTravellers","null");
		vue.addDataRaw("error", "false");
		try{vue.addMethod("suggestion",JavascriptResource.create("suggestion").parseContent());}
		catch (IOException e) {e.printStackTrace();}
		try {vue.addMethod("recherche",JavascriptResource.create("recherche").parseContent());}
		catch (IOException e) {e.printStackTrace();}
		vue.addData("selected", "");
		return vue;
	}
}
