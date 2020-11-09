package s4.spring.reservations.components;

import java.io.IOException;

import io.github.jeemv.springboot.vuejs.components.VueComponent;

public class Avatar {
    public static void main(String[]args) throws IOException {
    VueComponent compo=new VueComponent("avatar");
    compo.setProps("user","size");
    compo.addDataRaw("avatar","{src:null,initials:null}");
	compo.addMethod("getAvatar",""
			+ "let self = this;"
			+ "if(this.user.id != 0){"
			+ "this.$http['get']('/rest/image/user/'+this.user.id, {}).then(function(response,i) {"
			+ "if(response.data[0]!=self.user.id){"
			+ "self.avatar.src='/user-photos/'+self.user.id+'/avatar/'+response.data[0];"
			+ "}else{"
			+ "self.avatar.initials=self.user.login.charAt(0).toUpperCase();"
			+ "}"
			+ "});"
			+ "}");
	compo.onBeforeMount("this.getAvatar();");
    compo.setDefaultTemplateFile();
    compo.createFile(false);
    }
}
