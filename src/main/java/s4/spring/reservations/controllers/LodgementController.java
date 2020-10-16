package s4.spring.reservations.controllers;
import java.security.Principal;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.jeemv.springboot.vuejs.VueJS;
import io.github.jeemv.springboot.vuejs.utilities.Http;
import s4.spring.reservations.utilities.VueDataManager;

@Controller
public class LodgementController {
	
	@Autowired
	private VueJS vue;

	
	@RequestMapping("/lodgement/search")
	public String resultSearch(ModelMap model,Principal principal,@RequestParam(name="nbr", defaultValue="null") String nbr,@RequestParam(name="start", defaultValue="null") String start,@RequestParam(name="end", defaultValue="null") String end,@RequestParam(name="lat") String lat,@RequestParam(name="lon") String lon) {
		vue.addDataRaw("result","[]");
		vue.onBeforeMount("let self=this;" + Http.get("http://127.0.0.1:8080/rest/lodgement/search?lon="+lon+"&lat="+lat+"&start="+start+"&end="+end+"&nbr="+nbr,"self.result=response.data;console.log(JSON.parse(JSON.stringify(self.$root.result)));"
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
	
	public String showLodgment(ModelMap model,Principal principal,HttpServletRequest request) {
		vue.addDataRaw("nbr","[1,2,3,4,5]");
		vue.addDataRaw("newLodgement","{title:null,nb_place:null,nb_room:null,descrisption:null,price:null,type:null,lat:null,lon:null}");
		vue.addDataRaw("type","['Maison','Appartement','Chambre']");
		vue.addMethod("postLodgement", "let self=this; this.newLodgement.lat=this.selected.geometry.coordinates[0];this.newLodgement.lon=this.selected.geometry.coordinates[1];" + Http.post( "http://127.0.0.1:8080/rest/lodgement/create/","self.newLodgement", "self.modalNewHost=false; document.location.reload(true);"));
		VueDataManager vuemanager = new VueDataManager();
		vue.onMounted("document.getElementById(\"application\").style.visibility = \"visible\";");
		vue = vuemanager.addSimpleMenuRequiredData(vue);
		vue = vuemanager.addDrawerRequiredData(principal, vue);
		if (request.isUserInRole("ROLE_HOST")){
			vue.addDataRaw("Lodgements", "[]");
			vue.onBeforeMount("let self=this;" + Http.get("http://127.0.0.1:8080/rest/lodgements/6","self.lodgements=response.data"));
			model.put("vue", vue);
			return "lodgementDashboard";
		}else{
		vue.addData("modalNewHost",false);
		model.put("vue", vue);
		return "newHost";
		}
	}

	
	@RequestMapping("lodgement/{idLogement}")
    public String lodgementPage(@PathVariable int idLogement, ModelMap model,Principal principal) {
		VueDataManager vuemanager = new VueDataManager();
		vue = vuemanager.addDatePickerRequiredData(vue);
		vue = vuemanager.addSearchMenuRequiredData(vue);
		vue = vuemanager.addDrawerRequiredData(principal, vue);
		vue.addData("lodgement");
		vue.onBeforeMount("let self=this;" + Http.get("http://localhost:8080/rest/lodgement/"+idLogement, "self.lodgement=response.data;"));
		vue.addData("message", "Hello Logement");
		
	    model.put("vue", vue);
        return "lodgement";
       }
}