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
		
		
		vue.addData("message", "Hello reservations");
		
		//breadcrub menu
		addMenuRequiredData(principal);
		addDatePickerRequiredData();
	    
	    // date picker

	    model.put("vue", vue);
        return "index";
        
       }
	
	

	
	@RequestMapping("/login")
    public String login(ModelMap model,Principal principal) {

		vue.addData("message", "Hello reservations");
	    model.put("vue", vue);
        return "login";
        
       }
	@RequestMapping("/new")
	public String displayNewOrga(ModelMap model,Principal principal) {
		model.put("vue", vue);
		return "register";
	}
	
	@RequestMapping("/search/")
	public String search(ModelMap model,@Param("loca") String loca,Principal principal) {
		vue.addDataRaw("lodgements","[]");
		vue.addData("lodgementsPerPage", 4);
		vue.addData("expand",false);
		vue.onBeforeMount("let self=this;" + Http.get("http://localhost:8080/rest/lodgement/search/"+loca, "self.lodgements=response.data;"));
		model.put("vue", vue);
		return "search";
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
				"                    }, {\r\n" + 
				"                        \"title\": \"Reservation\",\r\n" + 
				"                        \"icon\": \"mdi-file-document-edit\"\r\n" + 
				"                    }, {\r\n" + 
				"                        \"title\": \"Settings\",\r\n" + 
				"                        \"icon\": \"mdi-cog-outline \"\r\n" + 
				"                    }]");

		
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