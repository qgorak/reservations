package s4.spring.reservations.components;

import java.io.IOException;

import io.github.jeemv.springboot.vuejs.components.VueComponent;
import io.github.jeemv.springboot.vuejs.utilities.Http;

public class SearchResults {
    public static void main(String[]args) throws IOException {
        VueComponent compo=new VueComponent("search-results");
		compo.setProps("result");
		compo.addData("map",null);
		compo.addDataRaw("markers","{}");
		compo.addDataRaw("background","{}");
		compo.addMethod("closePopup","let self=this;self.markers[i.id].closePopup().addTo(self.map);","i");
		compo.addMethod("showPopup","let self=this;\r\n"
				+ "			var text=i.title.toString()+'<br>prix: '+i.price.toString()+'€<br>Nombre de pièces: '+i.nbr_room+'<br>'+i.nbr_place+' Personne(s)<br>Description:<br>'+i.description;\r\n"
				+ "			self.markers[i.id].bindPopup(text,{closeButton:false,closeOnEscapeKey:false}).openPopup().addTo(self.map);","i");
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
		compo.addMethod("redirect", 
				"var url = new URL(window.location.href);"
				+ "let params = new URLSearchParams(url.search.slice(1));"
				+"window.location.href='/lodgement/'+id+'?'+params;"
				,"id");
		compo.onBeforeMount("let self=this;\r\n"
				+ "		var element = document.getElementById('osm-map');\r\n"
				+ "		self.map = L.map(element);\r\n"
				+ "		L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png').addTo(self.map);\r\n"
				+ "		var params=new URLSearchParams(window.location.search);\r\n"
				+ "		var center = L.latLng(params.get('lat'),params.get('lon'));\r\n"
				+ "		self.map.setView(center, 13);\r\n"
				+ "		for(i=0;i<self.result.length;i++){"
				+ "			var text=self.result[i].title.toString()+'<br>prix: '+self.result[i].price.toString()+'€<br>Nombre de pièces: '+self.result[i].nbr_room+'<br>'+self.result[i].nbr_place+' Personne(s)<br>Description:<br>'+self.result[i].description+'<br><a href=/lodgement/'+self.result[i].id+'>voir l\\'annonce</a>';	\r\n"
				+ "			center=L.latLng(self.result[i].lat,self.result[i].lon);			\r\n"
				+ "			var marker=L.marker(center);			\r\n"
				+ "			self.markers[self.result[i].id]=marker;			\r\n"
				+ "			self.background[self.result[i].id]='white';\r\n"
				+ "			marker.bindPopup(text).openPopup().addTo(self.map);\r\n"
				+ "			}");
		compo.onCreated("this.getImages();");
        compo.setDefaultTemplateFile();
        compo.createFile(false);
    }
}
