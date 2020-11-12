package s4.spring.reservations.components;

import java.io.IOException;

import io.github.jeemv.springboot.vuejs.components.VueComponent;
import io.github.jeemv.springboot.vuejs.utilities.Http;

public class SearchResults {
    public static void main(String[]args) throws IOException {
        VueComponent compo=new VueComponent("search-results");
		compo.setProps("result","nbr","start","end");
		compo.addData("map",null);
		compo.addDataRaw("markers","{}");
		compo.addDataRaw("background","{}");
		compo.addMethod("closePopup","let self=this;self.markers[i.id].closePopup().addTo(self.map);","i");
		compo.addMethod("showPopup","let self=this;self.markers[i.id].openPopup();","i");
		compo.addMethod("getImages", ""
				+ "let self = this;"
				+ "for(i=0;i<this.result.length;i++){"
				+ "this.$http['get']('/rest/image/lodgement/'+this.result[i].id, {}).then(function(response,i) {"
				+ "for(y=0;y<self.result.length;y++){"
					+ "id = response.data.slice(-1)[0];"
					+ "if(self.result[y].id == id){"
					+ "self.images=[];"
					+ "for(k=0;k<response.data.length-1;k++){"
					+ "src={src: '/user-photos/'+self.$root.result[y].rent.id+'/lodgement/'+self.$root.result[y].id+'/'+response.data[k]};"
					+ "self.images.push(src);"
					+ "}"
					+ "self.$set(self.$root.result[y], 'images', self.images)"
					+ "}"
				+ "}"
				+ "})"
				+ "}"
				);
		compo.addMethod("redirect", ""
				+
				"if(this.nbr!=='null' && this.start!=='null' && this.end!=='null'){" + 
				"window.location.href='/lodgement/'+id+'?start='+this.start+'&end='+this.end+'&nbr='+this.nbr;" + 
				"}\r\n" + 
				"else if(this.nbr!=='null'){" + 
				"window.location.href='/lodgement/'+id+'?nbr='+this.nbr;"+
				"}" + 
				"else if(this.start!=='null' && this.end!=='null'){" + 
				"window.location.href='/lodgement/'+id+'?start='+this.start+'&end='+this.end;" + 
				"}" + 
				"else{" + 
				"window.location.href='/lodgement/'+id" + 
				"}"
				,"id");
		compo.onBeforeMount("let self=this;"
				+ "		var element = document.getElementById('osm-map');"
				+ "		self.map = L.map(element);"
				+ "		L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png').addTo(self.map);"
				+ "		var params=new URLSearchParams(window.location.search);"
				+ "		var center = L.latLng(params.get('lat'),params.get('lon'));"
				+ "		self.map.setView(center, 13);"
				+ "		for(i=0;i<self.result.length;i++){"
				+ "			var text=self.result[i].title.toString()+'<br>prix: '+self.result[i].price.toString()+'€<br>Nombre de pièces: '+self.result[i].nbr_room+'<br>'+self.result[i].nbr_place+' Personne(s)<br>Description:<br>'+self.result[i].description+'<br><a href=/lodgement/'+self.result[i].id+'?start='+this.end+'&end='+this.start+'&nbr='+this.nbr+'>voir l\\'annonce</a>';"
				+ "			center=L.latLng(self.result[i].lat,self.result[i].lon);			"
				+ "			var marker=L.marker(center);			"
				+ "			self.markers[self.result[i].id]=marker;			"
				+ "			self.background[self.result[i].id]='white';"
				+ "			marker.bindPopup(text).addTo(self.map);"
				+ "			}");
		compo.onCreated("this.getImages();");
        compo.setDefaultTemplateFile();
        compo.createFile(false);
    }
}
