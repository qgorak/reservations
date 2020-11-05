package s4.spring.reservations.components;

import java.io.IOException;

import io.github.jeemv.springboot.vuejs.components.VueComponent;
import io.github.jeemv.springboot.vuejs.utilities.Http;

public class RegisterStepper {
    public static void main(String[]args) throws IOException {
    VueComponent compo=new VueComponent("register-stepper");
	compo.addData("valid",true);
	compo.addData("passwordConfirm","");
    compo.addData("showPassword",false);
	compo.addData("timeOut",null);
	compo.addData("usernameValid",false);
    compo.addDataRaw("passwordRules","["
            +"  v => !!v || 'Password is required',"
            +"]");
	compo.addDataRaw("usernameRules","[ v => !!v || 'Name is required',"
	+ "      v => (v && v.length <= 10) || 'Name must be less than 10 characters',]");
	compo.addDataRaw("emailRules","[\r\n"
			+ "      v => !!v || 'E-mail is required',\r\n"
			+ "      v => /.+@.+/.test(v) || 'E-mail must be valid',\r\n"
			+ "    ]");
	compo.addMethod("checkUserLogin", "let self = this;"
			+ "self.usernameValid=false;"
			+"if(self.newUser.login.length>2){"
			+"	if(((event.keyCode >=65 && event.keyCode <= 90) || (event.keyCode >=48 && event.keyCode <= 57) || event.keyCode === 8 || event.keyCode === 27 || event.keyCode === 46 || event.keyCode === 32 )){"				
			+"		clearTimeout(self.timeOut);"
			+"		this.timeOut =setTimeout(function(){"
			+"self.$http['get']('/rest/users/user/'+self.newUser.login).then(function(response){"
			+ "if(response.data==''){"
			+ "self.usernameValid=true;"
			+ "}"
			+ "})"
			+"		}, 500);"
			+"	}"
			+"}"
			+"else{"
			+"	this.destination={name:null,loc:null};"
			+"	this.search=[];"
			+"}");
	compo.addDataRaw("newUser", "{login:'',password:'',mail:''}");
	compo.addMethod("registerUser", "let self=this;" + Http.post( "/rest/users/","self.newUser", "self.$emit('toggle-modal');"));
	compo.addData("e1",1);
    compo.setDefaultTemplateFile();
    compo.createFile(false);
    }

}
