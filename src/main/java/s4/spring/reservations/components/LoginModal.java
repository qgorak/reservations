package s4.spring.reservations.components;

import java.io.IOException;

import io.github.jeemv.springboot.vuejs.components.VueComponent;
import io.github.jeemv.springboot.vuejs.utilities.Http;

public class LoginModal {
    public static void main(String[]args) throws IOException {
        VueComponent compo=new VueComponent("login-modal");
        
        compo.addData("dialog",false);
        compo.addData("username");
        compo.addData("password");
        compo.addData("valid",false);
        compo.addData("showProgressBar",false);
        compo.addData("showPassword",false);
        compo.addData("errorLogin",null);
        compo.addData("query",false);
        compo.addData("interval",0);
        compo.addData("value",0);
        compo.addDataRaw("usernameRules","["
                                        +"  v => !!v || 'Name is required',"
                                        +"  v => v.length <= 10 || 'Name must be less than 10 characters',"
                                        +"]");
        compo.addDataRaw("passwordRules","["
                +"  v => !!v || 'Password is required',"
                +"]");
        compo.addMethod("login", "let formData = new FormData();"
        		+ "  formData.set('username', this.username);"
        		+ "  formData.set('password', this.password);"
        		+ "  let self = this;"
        		+ "  this.queryAndIndeterminate();"
        		+ "  this.$http.post('/login', formData, {headers: {'Content-Type': 'application/x-www-form-urlencoded'}})"
        	    + "   .then((result) => {"
        	    + "		 self.$set(self.$root.user,'id',result.headers.userid);"
        	    + "		 self.$set(self.$root.user,'login',result.headers.username);"
        	    + "		 self.$set(self.$root.user,'mail',result.headers.mail);"
        	    + "		 document.cookie ='user='+JSON.stringify({ id:result.headers.userid,login:result.headers.username , mail: result.headers.mail });"
        	    + "		 self.dialog=false;"
        	    + "			self.query=false;"
        	    + "			self.showProgressBar=false;"
        	    + "			self.$root.$refs.alert.triggerAlert('success','Successful Log in')"
        	    + "     })"
        	    + "   .catch((error) => {"
        	    + "		 if(error.response!=null){"
        	    + "			self.errorLogin = error.response.data.exception;"
        	    + "			self.query=false;"
        	    + "			self.showProgressBar=false;"
        	    + "			self.$root.$refs.alert.triggerAlert('error','Log in failed')"
        	    + "		  }"
        	    + "})");
        compo.onBeforeDestroy("clearInterval(this.interval)");
        compo.addMethod("queryAndIndeterminate",""
        		+ "this.query = true;"
        	    + "this.showProgressBar = true;"
        	    + "this.value = 0;"
        	    + "setTimeout(() => {"
        	    + "  this.query = false;"
        	    + "  this.interval = setInterval(() => {"
        	    + "    if (this.value === 100) {"
        	    + "      clearInterval(this.interval);"
        	    + "      this.showProgressBar = false;"
        	    + "    }"
        	    + "   this.value += 25;"
        	    + "  }, 1000);"
        	    + "}, 2500);");
        compo.setDefaultTemplateFile();
        compo.createFile(false);
    }
}
