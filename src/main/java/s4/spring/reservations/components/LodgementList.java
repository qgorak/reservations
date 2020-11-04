package s4.spring.reservations.components;

import java.io.IOException;

import io.github.jeemv.springboot.vuejs.components.VueComponent;

public class LodgementList {
    public static void main(String[]args) throws IOException {
        VueComponent compo=new VueComponent("lodgement-list");
        compo.setProps("lodgements","title");
		compo.addMethod("getImages", ""
				+ "let self = this;"
				+ "for(i=0;i<this.lodgements.length;i++){"
				+ "this.$http['get']('/rest/image/lodgement/'+this.lodgements[i].id, {}).then(function(response,i) {"
				+ "for(y=0;y<self.lodgements.length;y++){"
					+ "id = response.data.slice(-1)[0];"
					+ "if(self.lodgements[y].id == id){"
					+ "self.images=[];"
					+ "for(k=0;k<response.data.length-1;k++){"
					+ "src={src: '/user-photos/'+self.lodgements[y].rent.id+'/lodgement/'+self.lodgements[y].id+'/'+response.data[k]};"
					+ "self.images.push(src);"
					+ "}"
					+ "self.$set(self.lodgements[y], 'images', self.images)"
					+ "}"
				+ "}"
				+ "})"
				+ "}"
				);
		compo.addMethod("redirectView", "window.location.pathname = \"/lodgement/\"+item.id;","item");
		compo.addMethod("redirectEdit", "window.location.pathname = \"/lodgement/\"+item.id+\"/edit\";","item");
		compo.onBeforeMount("this.getImages();");
        compo.setDefaultTemplateFile();
        compo.createFile(false);
    }
}
