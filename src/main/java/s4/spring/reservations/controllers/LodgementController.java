package s4.spring.reservations.controllers;
import java.security.Principal;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import io.github.jeemv.springboot.vuejs.VueJS;
import io.github.jeemv.springboot.vuejs.utilities.Http;
import s4.spring.reservations.services.MyUserDetails;
import s4.spring.reservations.utilities.VueDataManager;

@Controller
public class LodgementController {
	
	@Autowired
	private VueJS vue;

	
	@RequestMapping("/lodgement/search")
	public String resultSearch(@AuthenticationPrincipal MyUserDetails user,ModelMap model,Principal principal,@RequestParam(name="nbr", defaultValue="null") String nbr,@RequestParam(name="start", defaultValue="null") String start,@RequestParam(name="end", defaultValue="null") String end,@RequestParam(name="lat") String lat,@RequestParam(name="lon") String lon) {
		vue.addDataRaw("result","[]");
		
		VueDataManager vuemanager = new VueDataManager();
		vue = vuemanager.addSearchMenuRequiredData(vue);
		vue = vuemanager.addDrawerRequiredData(user, vue);
		vue = vuemanager.addDatePickerRequiredData(vue);
		vue.addDataRaw("images", "[]");
		vue.addMethod("getImages", ""
				+ "self = this;"
				+ "this.$http['get']('http://127.0.0.1:8080/rest/image/lodgement/'+this.result[i].id, {}).then(function(response,i) {"
				+ "for(y=0;y<self.result.length;y++){"
					+ "id = response.data.slice(-1)[0];"
					+ "if(self.result[y].id == id){"
					+ "self.images=[];"
					+ "for(k=0;k<response.data.length-1;k++){"
					+ "src={src: '/user-photos/'+self.result[y].rent.login+'/lodgement/'+self.result[y].id+'/'+response.data[k]};"
					+ "self.images.push(src);"
					+ "}"
					+ "self.$set(self.result[y], 'images', self.images)"
					+ "}"
				+ "}"
				+ "})"
				);
		vue.addMethod("redirect", 
				"var url = new URL(window.location.href);"
				+ "let params = new URLSearchParams(url.search.slice(1));"
				+"window.location.href='/lodgement/'+id+'?'+params;"
				,"id");
		vue.onBeforeMount("let self=this;"		
				+ "var urlParams = new URLSearchParams(window.location.search);"
				+ "var start = urlParams.get('start');"
				+ "self.dates[0]=start;"
				+ "var end = urlParams.get('end');"
				+ "self.dates[1]=end;"
				+ "var nbr = urlParams.get('nbr');"
				+ "self.nbTravellers=parseInt(nbr);"
				+ Http.get("http://127.0.0.1:8080/rest/lodgements/search?lon="+lon+"&lat="+lat+"&start="+start+"&end="+end+"&nbr="+nbr,"self.result=response.data;console.log(JSON.parse(JSON.stringify(self.$root.result)));"
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
		+ "self.getImages(i);"
		+ "}"
		));

		model.put("vue", vue);
		return "searchResult";
	}
	

	@RequestMapping("/lodgement")
	
	public String showLodgment(ModelMap model,@AuthenticationPrincipal MyUserDetails user) {
		VueDataManager vuemanager = new VueDataManager();
		vue = vuemanager.addSimpleMenuRequiredData(vue);
		vue = vuemanager.addDrawerRequiredData(user, vue);
		if(user != null) {
		vue.addDataRaw("nbr","[1,2,3,4,5]");
		vue.addDataRaw("newLodgement","{title:null,nb_place:null,nb_room:null,descrisption:null,price:null,type:null,lat:null,lon:null}");
		vue.addDataRaw("type","['Maison','Appartement','Chambre']");
		vue.addMethod("postLodgement", "let self=this; this.newLodgement.lat=this.selected.geometry.coordinates[1];this.newLodgement.lon=this.selected.geometry.coordinates[0];" + Http.post( "http://127.0.0.1:8080/rest/lodgements/","self.newLodgement", "self.modalNewHost=false; document.location.reload(true);"));
		vue.onMounted("document.getElementById(\"application\").style.visibility = \"visible\";");

		Collection<? extends GrantedAuthority> list =user.getAuthorities();
		System.out.print(list.toString());
		
		
		if (user.getAuthorities().toString().equals("[ROLE_HOST]")){
			vue.addMethod("redirect", "window.location.href = \"http://127.0.0.1:8080/lodgement/\"+item.id;","item");
			vue.addDataRaw("lodgements", "[]");
			vue.onBeforeMount("let self=this;" + Http.get("http://127.0.0.1:8080/rest/lodgements/my","self.lodgements=response.data"));
			model.put("vue", vue);
			return "lodgementDashboard";
		}else{
		vue.addData("modalNewHost",false);
		model.put("vue", vue);
		return "newHost";
		}
		
		}
		vue.addData("error_message","vous devez etre connecté pour accéder a ce contenu");
		model.put("vue", vue);
		return "error";
	}

	
	@RequestMapping("lodgement/{idLogement}")
    public String lodgementPage(@PathVariable int idLogement, ModelMap model,@AuthenticationPrincipal MyUserDetails user) {
		VueDataManager vuemanager = new VueDataManager();
		vue.addDataRaw("file","[]");
		vue.addData("host");
		vue.addMethod("postPhotoLodgement", "let self=this;let formData = new FormData();formData.append('file', this.file);"
				+ "		this.$http['post'](\"/rest/image/saveLodgementPhoto/\"+this.lodgement.id, formData, {\r\n"
				+ "     headers: {\r\n"
				+ "        \"Content-Type\": \"multipart/form-data\"\r\n"
				+ "      }})");
		vue.addDataRaw("images", "[]");
		vue.addData("reservationModal",false);
		vue.addDataRaw("reservation","{start:'',end:'',lodgement:''}");
		vue.addMethod("postReservation", "this.reservation.start=this.dates[0];this.reservation.end=this.dates[1];this.reservation.lodgement=this.lodgement;let self=this;" + Http.post( "http://127.0.0.1:8080/rest/reservations/","self.reservation", "self.reservationModal=false;"));
		vue = vuemanager.addDatePickerRequiredData(vue);
		vue = vuemanager.addSearchMenuRequiredData(vue);
		vue = vuemanager.addDrawerRequiredData(user, vue);
		vue.addData("lodgement");
		vue.onBeforeMount("var urlParams = new URLSearchParams(window.location.search);"
				+ "var start = urlParams.get('start');"
				+ "if(start!=null){"
				+ "this.dates[0]=start;"
				+ "var end = urlParams.get('end');"
				+ "this.dates[1]=end;"
				+ "}"
				+ "var nbr = urlParams.get('nbr');"
				+ "if(nbr!=null){"
				+ "this.nbTravellers=parseInt(nbr);"
				+ "}"
				+"let self=this;this.date = new Date().toLocaleDateString('fr-CA');" + Http.get("http://127.0.0.1:8080/rest/lodgements/"+idLogement, "self.lodgement=response.data;self.host=response.data.rent.login")
				+Http.get("http://127.0.0.1:8080/rest/image/lodgement/"+idLogement, ""
						+ "for(k=0;k<response.data.length-1;k++){"
						+ "self.images.push({src: '/user-photos/'+self.lodgement.rent.login+'/lodgement/'+self.lodgement.id+'/'+response.data[k]})};"));
	    model.put("vue", vue);
        return "lodgement";
       }
}