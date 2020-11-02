package s4.spring.reservations.components;

import java.io.IOException;

import io.github.jeemv.springboot.vuejs.components.VueComponent;
import io.github.jeemv.springboot.vuejs.utilities.Http;

public class SearchResults {
    public static void main(String[]args) throws IOException {
        VueComponent compo=new VueComponent("search-results");
		compo.setProps("result");
		compo.addData("map",null);
		compo.addMethod("getImages", ""
				+ "let self = this;"
				+ "for(i=0;i<this.result.length;i++){"
				+ "this.$http['get']('/rest/image/lodgement/'+this.result[i].id, {}).then(function(response,i) {"
				+ "for(y=0;y<self.result.length;y++){"
					+ "id = response.data.slice(-1)[0];"
					+ "if(self.result[y].id == id){"
					+ "self.images=[];"
					+ "for(k=0;k<response.data.length-1;k++){"
					+ "src={src: '/user-photos/'+self.result[y].rent.id+'/lodgement/'+self.result[y].id+'/'+response.data[k]};"
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
		compo.addMethod("showPopup","let self=this;var center=L.latLng(i.lat,i.lon);"
				+ "var text=i.title.toString()+'<br>prix: '+i.price.toString()+'€';"
				+ "L.marker(center).bindPopup('ok').addTo(self.map);","i");
		compo.onBeforeMount("let self=this;"
				+ "			var element = document.getElementById('osm-map');"
				+ "			self.map = L.map(element);"
				+ "			L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png').addTo(self.map);"
				+ "			var params=new URLSearchParams(window.location.search);"
				+ "			var center = L.latLng(params.get('lat'),params.get('lon'));"
				+ "			self.map.setView(center, 13);"
				+ "			for(i=0;i<self.result.length;i++){"
				+ "				var text=self.result[i].title.toString()+'<br>prix: '+self.result[i].price.toString()+'€';"
				+ "				center=L.latLng(self.result[i].lat,self.result[i].lon);"
				+ "				L.marker(center).addTo(self.map);"
				+ "				}"
				+ "			console.log(self.map);");
		compo.onCreated("this.getImages();");
        compo.setDefaultTemplateFile();
        compo.createFile(false);
    }
}
