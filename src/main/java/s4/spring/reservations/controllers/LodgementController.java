package s4.spring.reservations.controllers;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import io.github.jeemv.springboot.vuejs.VueJS;
import io.github.jeemv.springboot.vuejs.utilities.Http;
import s4.spring.reservations.utilities.VueDataManager;

@Controller
public class LodgementController {
	
	@Autowired
	private VueJS vue;
	
	@RequestMapping("/lodgement/search/{lon}&{lat}&{start}&{end}&{nbr}")
	public String resultSearch(ModelMap model,Principal principal,@PathVariable String nbr,@PathVariable String start,@PathVariable String end,@PathVariable String lat,@PathVariable String lon) {
		vue.addDataRaw("result","[]");
		vue.onBeforeMount("let self=this;" + Http.get("http://127.0.0.1:8080/rest/lodgement/search/"+lon+"&"+lat+"&"+start+"&"+end+"&"+nbr,"self.result=response.data;console.log(JSON.parse(JSON.stringify(self.$root.result)));"
		+ "var element = document.getElementById('osm-map');"
		+ "var map = L.map(element);"
		+ "L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png').addTo(map);"
		+ "var center = L.latLng("+lat+","+lon+");"
		+ "map.setView(center, 9);"
		+ "var temp=(JSON.parse(JSON.stringify(self.$root.result)));"
		+ "for(i=0;i<temp.length;i++){"
		+ "console.log(temp[i].lat);"
		+ "center=L.latLng(temp[i].lat,temp[i].lon);"
		+ "L.marker(center).addTo(map);"
		+ "}"));
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
		VueDataManager vuemanager = new VueDataManager();
		vue = vuemanager.addDatePickerRequiredData(vue);
		vue = vuemanager.addMenuRequiredData(principal,vue);
		vue.addData("lodgement");
		vue.onBeforeMount("let self=this;" + Http.get("http://localhost:8080/rest/lodgement/"+idLogement, "self.lodgement=response.data;"));
		vue.addData("message", "Hello Logement");
		
	    model.put("vue", vue);
        return "lodgement";
       }
	


	
}