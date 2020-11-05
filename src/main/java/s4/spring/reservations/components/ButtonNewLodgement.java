package s4.spring.reservations.components;

import java.io.IOException;

import io.github.jeemv.springboot.vuejs.components.VueComponent;
import io.github.jeemv.springboot.vuejs.utilities.Http;

public class ButtonNewLodgement {
    public static void main(String[]args) throws IOException {
        VueComponent compo=new VueComponent("new-lodgement-button");
        compo.setProps("draft");
        compo.addData("dialog",false);
        compo.addData("position",false);
		compo.addDataRaw("type","['Maison','Appartement','Chambre']");
		compo.addDataRaw("nbr","[1,2,3,4,5]");
		compo.addDataRaw("newLodgement","{title:null,nb_place:null,nb_room:null,description:null,price:null,type:null,lat:null,lon:null,status:null}");
        compo.addData("e1",1);
		compo.addMethod("postLodgement", "let self=this;this.newLodgement.status='DRAFT',this.newLodgement.lat=this.$refs.adress.selected.geometry.coordinates[1];this.newLodgement.lon=this.$refs.adress.selected.geometry.coordinates[0];" + Http.post( "/rest/lodgements/","self.newLodgement", "self.newLodgement = response.data;self.e1=6;"));
		compo.addMethod("submitLodgement", "let self=this;self.newLodgement.status='ONLINE';"
				+ Http.patch("'/rest/lodgements/'+self.newLodgement.id", "self.newLodgement", ""
						+ "let lodgement=response.data;"
						+ "self.$set(lodgement, 'images', self.$refs.images.images);"
						+ "self.$emit('addtolist', lodgement);"
						+ "self.newLodgement={title:null,nb_place:null,nb_room:null,description:null,price:null,type:null,lat:null,lon:null,status:null};"
						+ "self.$refs.images.images=[];"
						+ "self.$refs.adress.selected='';"
						+ "self.$refs.adress.search=[];console.log(self.$refs.adress.search);"
						+ "self.e1=1;","self.dialog=false;"));
		compo.addMethod("verifySelected","setInterval(() => {\r\n"
				+ "    	if(this.$refs.adress.selected!=''){this.position=true;}\r\n"
				+ "  		}, 500);");
        compo.setDefaultTemplateFile();
        compo.createFile(false);
        
    }
}
