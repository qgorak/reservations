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
	
	@ModelAttribute(name = "vue")
	private VueJS getVue() {
		this.vue.addDataRaw("user", "{id:'',login:''}");
		return this.vue;
	}
	
	@RequestMapping("/lodgement/search")
	public String resultSearch(@AuthenticationPrincipal MyUserDetails user,ModelMap model,@RequestParam(name="nbr",defaultValue="null") String nbr,@RequestParam(name="start",defaultValue="null") String start,@RequestParam(name="end" ,defaultValue="null") String end,@RequestParam(name="lat") String lat,@RequestParam(name="lon") String lon) {
		vue.addData("result", null);
		vue.addData("nbr",nbr);
		vue.addData("start",start);
		vue.addData("end",end);
		vue.onBeforeMount(""
		+ "let self=this;"		
		+ Http.get("/rest/lodgements/search?lon="+lon+"&lat="+lat+"&start="+start+"&end="+end+"&nbr="+nbr,""
		+ "self.result=response.data;")
		);
		return "searchResult";
	}
	
	@RequestMapping("/lodgement")
	public String lodgementDashboard(ModelMap model,@AuthenticationPrincipal MyUserDetails user) {
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
	}	

	@RequestMapping("lodgement/{idLogement}")
    public String lodgementPage(@PathVariable int idLogement, ModelMap model,@AuthenticationPrincipal MyUserDetails user,@RequestParam(name="nbr" ,defaultValue="null") String nbr,@RequestParam(name="start" ,defaultValue="null") String start,@RequestParam(name="end" ,defaultValue="null") String end) {
		vue.addData("lodgement",null);
		vue.addDataRaw("images", "[]");
		vue.addDataRaw("nb","['1','2','3','4','5']");
		nbr=(!nbr.equals("null")) ? nbr : new String("");
		vue.addData("finalNbr",nbr);
		vue.addDataRaw("choosenDates","['"+start+"','"+end+"']");
		vue.addData("validReservation",false);
		vue.onBeforeMount("let self=this;" + Http.get("/rest/lodgements/"+idLogement, "self.lodgement=response.data;self.host=response.data.rent")
				+Http.get("/rest/image/lodgement/"+idLogement, ""
				+ "for(k=0;k<response.data.length-1;k++){"
				+ "self.images.push({src: '/user-photos/'+self.host.id+'/lodgement/'+self.lodgement.id+'/'+response.data[k]})"
				+ "};"));
        return "lodgement";
       }
}