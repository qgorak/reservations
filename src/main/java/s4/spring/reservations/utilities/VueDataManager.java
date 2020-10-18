package s4.spring.reservations.utilities;

import java.io.IOException;

import io.github.jeemv.springboot.vuejs.VueJS;
import io.github.jeemv.springboot.vuejs.utilities.Http;
import io.github.jeemv.springboot.vuejs.utilities.JavascriptResource;
import s4.spring.reservations.services.MyUserDetails;

public class VueDataManager {
//		JavascripMultiModulesResource js a utiliser
	
	
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
	public VueJS addDrawerRequiredData(MyUserDetails user,VueJS vue) {
		vue.onMounted("document.getElementById(\"application\").style.visibility = \"visible\";");
		
		String username;
		vue.addMethod("triggerModal", "switch (number) {\r\n"
				+ "  case '1':\r\n"
				+ "    this.loginModal = !this.loginModal;\r\n"
				+ "    break;\r\n"
				+ "  case '2':\r\n"
				+ "this.registerModal = !this.registerModal;"
				+ "    break;\r\n"
				+ "  case '3':\r\n"
				+ "window.location.href='/logout';"
				+ "	   break;"
				+ "  default:}","number");
		
		//drawer datas
		if (user != null) {
			username = user.getUsername();
			if (user.getAuthorities().toString().equals("[ROLE_HOST]")){
			vue.addDataRaw("items", " [{     'title': 'Logout',        "
					+ "                       'icon': 'mdi-logout', "
					+ "                        'action': '3',"
					+ "                   }, {"
					+"                        'title': 'Mes Logements',"
					+"                        'icon': 'mdi-home-city',"
					+"                        'link': '/lodgement'," 
					+"                    }, {"
					+"                        'title': 'Mes Reservations',"
					+"                        'icon': 'mdi-file-document-edit'," 
					+"                        'link': '/reservation',"
					+"                    }, {"
					+"                        'title': 'Settings'," 
					+"                        'icon': 'mdi-cog-outline',"
					+"                        'link': '/lodement/'," 
					+"                    }]");
			}else {
				vue.addDataRaw("items", " [{     'title': 'Logout',        "
						+ "                       'icon': 'mdi-logout', "
						+ "                        'action': '3',"
						+ "                   }, {"
						+"                        'title': 'Devenir Hôte',"
						+"                        'icon': 'mdi-home-city',"
						+"                        'link': '/lodgement'," 
						+"                    }, {"
						+"                        'title': 'Mes Reservations',"
						+"                        'icon': 'mdi-file-document-edit'," 
						+"                        'action': '/reservation',"
						+"                    }, {"
						+"                        'title': 'Settings'," 
						+"                        'icon': 'mdi-cog-outline',"
						+"                        'link': '/lodement/'," 
						+"                    }]");
			}
		}
		else {
			username = "Anonyme";
			vue.addDataRaw("items", " [{"
					+"                        'title': 'Se Connecter',"
					+"                        'icon': 'mdi-login-variant',"
					+"                        'action': '1'," 
					+"                    }, {"
					+"                        'title': 'Inscription',"
					+"                        'icon': 'mdi-account-plus'," 
					+"                        'action': '2',"
					+"                    }]");
		}
		
		vue.addData("user", username);
		vue.addData("drawer", false);

		
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

		return vue;
		
	}
	
	public VueJS addSearchMenuRequiredData(VueJS vue) {

		vue.addDataRaw("nb","[1,2,3,4,5]");
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
	
	public VueJS addSimpleMenuRequiredData(VueJS vue) {

		
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
