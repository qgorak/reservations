package s4.spring.reservations.components;

import java.io.IOException;

import io.github.jeemv.springboot.vuejs.components.VueComponent;
import io.github.jeemv.springboot.vuejs.utilities.Http;

public class SearchResults {
    public static void main(String[]args) throws IOException {
        VueComponent compo=new VueComponent("search-results");
		compo.setProps("result");
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
					+ "self.$set(self.result[y], 'images', self.images)"
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
		compo.onCreated("this.getImages();");
        compo.setDefaultTemplateFile();
        compo.createFile(false);
    }
}
