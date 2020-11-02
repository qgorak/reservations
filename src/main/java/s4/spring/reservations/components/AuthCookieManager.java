package s4.spring.reservations.components;

import java.io.IOException;

import io.github.jeemv.springboot.vuejs.components.VueComponent;

public class AuthCookieManager {
    public static void main(String[]args) throws IOException {
    VueComponent compo=new VueComponent("auth-manager");
    compo.setProps("user");
	compo.addMethod("isUserLogged",""
			+ "var myCookie = this.getCookie('user');"
			+ "if(myCookie!==''){"
			+ "return true;"
			+ "}"
			+ "return false;");
	compo.addMethod("getCookie", ""
     +"var name = cname + '=';"
     +"var decodedCookie = decodeURIComponent(document.cookie);"
     +"var ca = decodedCookie.split(';');"
     +"for(var i = 0; i <ca.length; i++) {"
     +"  var c = ca[i];"
     +"  while (c.charAt(0) == ' ') {"
     +"    c = c.substring(1);"
     +"  }"
     +" if (c.indexOf(name) == 0) {"
     +"   return c.substring(name.length, c.length);"
     +"  }"
     +"}"
     +"return'';","cname");
	compo.onBeforeMount("if(this.isUserLogged()){"
			+ "var myCookie = this.getCookie('user');"
			+ "var obj = JSON.parse(myCookie);"
			+ "this.$root.user=obj;"
			+ "}");
    compo.setTemplate("<div></div>");
    compo.createFile(false);
    }
}
