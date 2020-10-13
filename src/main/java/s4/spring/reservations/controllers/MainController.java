package s4.spring.reservations.controllers;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import io.github.jeemv.springboot.vuejs.VueJS;
import io.github.jeemv.springboot.vuejs.utilities.Http;

@Controller
public class MainController {
	
	@Autowired
	private VueJS vue;
    
	@GetMapping("/")
    public String index(ModelMap model,Principal principal) {
		vue.addDataRaw("nb","[1,2,3,4,5]");
	
		//breadcrub menu
		addMenuRequiredData(principal);
		addDatePickerRequiredData();
	    
	    // date picker
	    model.put("vue", vue);
        return "index";
       }
	
	
	@RequestMapping("/lodgement/search/{lon}&{lat}&{start}&{end}&{nbr}")
	public String resultSearch(ModelMap model,Principal principal,@PathVariable String nbr,@PathVariable String start,@PathVariable String end,@PathVariable String lat,@PathVariable String lon) {
		vue.addDataRaw("result", "[]");
		vue.onBeforeMount("let self=this;" + Http.get("http://127.0.0.1:8080/rest/lodgement/search/"+lon+"&"+lat+"&"+start+"&"+end+"&"+nbr, "self.result=response.data;"));
		model.put("vue", vue);
		return "searchResult";
	}
	

	@RequestMapping("/lodgement/")
	public String showLodgment(ModelMap model,Principal principal) {
		String username;
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
		vue.addData("text","texxt");
		vue.addDataRaw("tabs","['Mes Logements', 'Mes Reservations']");
		vue.addData("drawer", false);
		vue.addData("tab",null);
		vue.addData("user", username);
		vue.addData("drawer", false);
		vue.addDataRaw("items", " [{\r\n" + 
				"                        \"title\": \"Settings\",\r\n" + 
				"                        \"icon\": \"mdi-cog-outline \",\r\n" + 
				"                        \"link\": \"/\",\r\n" + 
				"                    }]");
		model.put("vue", vue);
		return "lodgementDashboard";
	}

	
	@RequestMapping("lodgement/{idLogement}")
    public String lodgementPage(@PathVariable int idLogement, ModelMap model,Principal principal) {
		addMenuRequiredData(principal);
		addDatePickerRequiredData();
		vue.addData("lodgement");
		vue.onBeforeMount("let self=this;" + Http.get("http://localhost:8080/rest/lodgement/"+idLogement, "self.lodgement=response.data;"));
		vue.addData("message", "Hello Logement");
		
	    model.put("vue", vue);
        return "lodgement";
       }
	
	public void addMenuRequiredData(Principal principal) {
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
		vue.addDataRaw("items", " [{\r\n" + 
				"                        \"title\": \"Logement\",\r\n" + 
				"                        \"icon\": \"mdi-home-city\",\r\n" + 
				"                        \"link\": \"/lodgement/\",\r\n" + 
				"                    }, {\r\n" + 
				"                        \"title\": \"Reservation\",\r\n" + 
				"                        \"icon\": \"mdi-file-document-edit\",\r\n" + 
				"                        \"link\": \"/lodement/\",\r\n" + 
				"                    }, {\r\n" + 
				"                        \"title\": \"Settings\",\r\n" + 
				"                        \"icon\": \"mdi-cog-outline \",\r\n" + 
				"                        \"link\": \"/lodement/\",\r\n" + 
				"                    }]");
		
		//login modal
		vue.addData("loginModal",false);
		
		//register modal data
		vue.addData("registerModal",false);
		vue.addData("valid",true);
		vue.addData("passwordConfirm","");
		vue.addDataRaw("usernameRules","[ v => !!v || 'Name is required',\r\n"
				+ "      v => (v && v.length <= 10) || 'Name must be less than 10 characters',]");
		vue.addDataRaw("emailRules","[ v => !!v || 'E-mail is required',\r\n"
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
		vue.addMethod("suggestion","{\r\n"
		+ "	if(event.target.value.length!=0){\r\n"
		+ "		var items=event.target.value.split(' ');\r\n"
		+ "		let url = \"https://api-adresse.data.gouv.fr/search/?q=\"+items.join(\"+\")+\"&limit=8\";\r\n"
		+ "		fetch(url)\r\n"
		+ "		.then(res => res.json())\r\n"
		+ "		.then((out) => {\r\n"
		+ "		this.search=out.features;\r\n"
		+ "		})\r\n"
		+ "	}\r\n"
		+ " else{\r\n"
		+ " this.destination={name:null,loc:null};"
		+ "	this.search=[];\r\n"
		+ "	}\r\n"
		+ "}");
		vue.addMethod("recherche", "this.selected.loc = this.selected.geometry.coordinates.join('&');"
				+"window.location.replace('http://127.0.0.1:8080/lodgement/search/'+this.selected.loc+'&'+this.dates[0]+'&'+this.dates[1]+'&'+this.nbTravellers)");
		vue.addData("selected", "");
		
	}
	
	public void addDatePickerRequiredData() {
		vue.addData("menu1", false);
		vue.addData("date");
		vue.addData("endDate","2020-10-30");
		vue.addDataRaw("dates", "[]");
		vue.addDataRaw("allowedDates","['2020-10-08', '2020-10-09']");

		vue.addDataRaw("dateFormatted", "this.formatDate(new Date().toISOString().substr(0, 10))");
		vue.addComputed("dateRangeText", "return this.dates.join(' au ')");
		
		vue.addMethod("getAllowedDates", "for (var i = 0; i < this.allowedDates.length; i++) {\r\n" + 
				"         if (this.allowedDates[i] == val){\r\n" + 
				"            return val\r\n" + 
				"         } \r\n" + 
				"      }","val");
		
		
		vue.addMethod("formatDate", "if (!date) return null\r\n" + 
				"\r\n" + 
				"      const [year, month, day] = date.split('-')\r\n" + 
		
				"      return `${month}/${day}/${year}`","date");
		
		vue.addMethod("addDays", "var date = new Date(this.valueOf());\r\n" + 
				"        date.setDate(date.getDate() + days);\r\n" + 
				"        return date;","days");
		
		vue.addMethod("parseDate", "      if (!date) return null\r\n" + 
			 
				"      const [month, day, year] = date.split('/')\r\n" + 
				"      return `${year}-${month.padStart(2, '0')}-${day.padStart(2, '0')}`","date");
		
		vue.onBeforeMount("this.date = new Date().toLocaleDateString(\"fr-CA\"\r\n); ");
		vue.addWatcher("dates", "console.log(this.dates)");
	
	}
	
}