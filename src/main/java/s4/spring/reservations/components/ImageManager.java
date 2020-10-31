package s4.spring.reservations.components;

import java.io.IOException;

import io.github.jeemv.springboot.vuejs.components.VueComponent;
import io.github.jeemv.springboot.vuejs.utilities.Http;

public class ImageManager {
    public static void main(String[]args) throws IOException {
        VueComponent compo=new VueComponent("images-manager");
        compo.setProps("lodgement");
        compo.addDataRaw("images", "[]");
        compo.addData("file",null);
		compo.addMethod("getImages", ""
				+ "self = this;"
				+ "this.$http['get']('/rest/image/lodgement/'+self.lodgement.id, {}).then(function(response) {"
					+ "for(k=0;k<response.data.length-1;k++){"
					+ "image={name:response.data[k],src: '/user-photos/'+self.lodgement.rent.id+'/lodgement/'+self.lodgement.id+'/'+response.data[k]};"
					+ "self.images.push(image);"
					+ "}"
				+ "})");
		compo.addMethod("postImage", "let self=this;let formData = new FormData();formData.append('file', this.file);"
				+ "		this.$http['post'](\"/rest/image/saveLodgementPhoto/\"+this.lodgement.id, formData, {\r\n"
				+ "     headers: {\r\n"
				+ "        \"Content-Type\": \"multipart/form-data\"\r\n"
				+ "      }}).then(function(response, i) {"
				+ "self.images.push({src: '/user-photos/'+self.lodgement.rent.id+'/lodgement/'+self.lodgement.id+'/'+response.data, name: response.data});"
				+ "self.file=null;"
				+ "})");
		compo.addMethod("deleteImage", "let self=this;"
				+Http.delete("'/rest/image/deleteLodgementPhoto/'+this.lodgement.id+'/'+item.name+'/'", ""
				+ "for(k=0;k<self.images.length;k++){"
					+ "if(self.images[k].name==response.data){"
						+ "self.images.splice(k,1);"
					+ "}"
				+ "}"
				+ ""),"item");
		compo.onBeforeMount("if(this.lodgement.id!=null){this.getImages();}");
        compo.setDefaultTemplateFile();
        compo.createFile(false);
    }
}
