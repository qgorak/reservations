package s4.spring.reservations.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.jeemv.springboot.vuejs.VueJS;
import io.github.jeemv.springboot.vuejs.utilities.Http;
import s4.spring.reservations.services.MyUserDetails;

@Controller
public class LodgementController {
	
	@Autowired
	private VueJS vue;
	
	private String restURL = "http://127.0.0.1:8080/";
	
	@ModelAttribute(name = "vue")
	private VueJS getVue() {
		return this.vue;
	}
	
	@RequestMapping("/lodgement/search")
	public String resultSearch(@AuthenticationPrincipal MyUserDetails user,ModelMap model,@RequestParam(name="nbr", defaultValue="null") String nbr,@RequestParam(name="start", defaultValue="null") String start,@RequestParam(name="end", defaultValue="null") String end,@RequestParam(name="lat") String lat,@RequestParam(name="lon") String lon) {
		if(user != null) {
		vue.addData("user",user.getUser());
		}else {
		vue.addDataRaw("user", "{id:0,login:''}");
		}
		vue.addData("result", null);
		vue.onBeforeMount(""
		+ "let self=this;"		
		+ Http.get(restURL+"rest/lodgements/search?lon="+lon+"&lat="+lat+"&start="+start+"&end="+end+"&nbr="+nbr,""
				+ "self.result=response.data;"
				+ "var element = document.getElementById('osm-map');"
				+ "var map = L.map(element);"
				+ "L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png').addTo(map);"
				+ "var center = L.latLng("+lat+","+lon+");"
				+ "map.setView(center, 13);"
				+ "var temp=(JSON.parse(JSON.stringify(self.$root.result)));"
				+ "for(i=0;i<temp.length;i++){"
					+ "console.log(temp[i].lat);"
					+ "center=L.latLng(temp[i].lat,temp[i].lon);"
					+ "L.marker(center).addTo(map);"
				+ "}")
		);
		return "searchResult";
	}
	

	@RequestMapping("/lodgement")
	
	public String lodgementDashboard(ModelMap model,@AuthenticationPrincipal MyUserDetails user) {
		if(user != null) {	
		vue.addData("user",user.getUser());
		vue.addDataRaw("lodgements", "[]");
		vue.addDataRaw("disabledLodgements", "[]");
		vue.addData("draft");
		vue.addMethod("addLodgementToList", "this.lodgements.push(lodgement);","lodgement");
		vue.onBeforeMount("let self=this;" + Http.get("/rest/lodgements/my",
					  "for(i=0;i<response.data.length;i++){"
							  + "switch(response.data[i].status){"
							  + "case 'ONLINE':"
							  + "self.lodgements.push(response.data[i]);"
							  + "break;"
							  + "case 'OFFLINE':"
							  + "self.disabledLodgements.push(response.data[i]);"
							  + "break;"
							  + "case 'DRAFT':"
							  + "self.draft=response.data[i];"
							  + "self.e1=5;"
							  + "break;"
							  + "default:"
							  + "break;"
							  + "}"
					+ "}"));
			model.put("vue", vue);
			return "lodgementDashboard";
		}else{
		vue.addDataRaw("user", "{id:0,login:''}");
		vue.addData("error_message","vous devez etre connecté pour accéder a ce contenu");
		return "error";
	}
	}	

	
	@RequestMapping("lodgement/{idLogement}")
    public String lodgementPage(@PathVariable int idLogement, ModelMap model,@AuthenticationPrincipal MyUserDetails user) {
		if(user != null) {
		vue.addData("user",user.getUser());
		}else {
		vue.addDataRaw("user", null);
		}
		vue.addData("lodgement",null);
		vue.addDataRaw("images", "[]");
		vue.onBeforeMount(""
				+"let self=this;this.date = new Date().toLocaleDateString('fr-CA');" + Http.get("/rest/lodgements/"+idLogement, "self.lodgement=response.data;self.host=response.data.rent,self.getHostAvatar();")
				+Http.get("/rest/image/lodgement/"+idLogement, ""
						+ "for(k=0;k<response.data.length-1;k++){"
						+ "self.images.push({src: '/user-photos/'+self.lodgement.rent.id+'/lodgement/'+self.lodgement.id+'/'+response.data[k]})"
						+ "};"));
        return "lodgement";
       }

}