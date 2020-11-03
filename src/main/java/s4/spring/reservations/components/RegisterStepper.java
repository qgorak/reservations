package s4.spring.reservations.components;

import java.io.IOException;

import io.github.jeemv.springboot.vuejs.components.VueComponent;
import io.github.jeemv.springboot.vuejs.utilities.Http;

public class RegisterStepper {
    public static void main(String[]args) throws IOException {
    VueComponent compo=new VueComponent("register-stepper");
	compo.addData("valid",true);
	compo.addData("passwordConfirm","");
	compo.addDataRaw("usernameRules","[ v => !!v || 'Name is required',"
	+ "      v => (v && v.length <= 10) || 'Name must be less than 10 characters',]");
	compo.addDataRaw("emailRules","[\r\n"
			+ "      v => !!v || 'E-mail is required',\r\n"
			+ "      v => /.+@.+/.test(v) || 'E-mail must be valid',\r\n"
			+ "    ]");
	compo.addDataRaw("passwordRules","[]");
	compo.addDataRaw("newUser", "{login:'',password:'',mail:''}");
	compo.addMethod("registerUser", "let self=this;" + Http.post( "/rest/users/","self.newUser", "self.$emit('toggle-modal');"));
	compo.addData("e1",1);
    compo.setDefaultTemplateFile();
    compo.createFile(false);
    }

}
