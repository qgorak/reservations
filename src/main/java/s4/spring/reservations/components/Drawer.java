package s4.spring.reservations.components;

import java.io.IOException;

import io.github.jeemv.springboot.vuejs.components.VueComponent;

public class Drawer {
    public static void main(String[]args) throws IOException {
        VueComponent compo=new VueComponent("drawer");
        compo.setProps("user");
		compo.addMethod("getMenuItems", ""
				+ "if(this.user.id != 0){"
				+"this.items=[{'title':'Logout','icon':'mdi-logout','action':'3',},"
    			+"{'title': 'Mes Logements','icon': 'mdi-home-city', 'link': '/lodgement',},"
    			+"{'title':'Mes Reservations','icon':'mdi-file-document-edit','link': '/reservation',},"
    			+"{'title': 'Settings','icon': 'mdi-cog-outline','link': '/user/me',}]"
    			+ "}else{"
    			+"this.items=[{'title': 'Se Connecter','icon': 'mdi-login-variant', 'action': '1',},"
    			+"{'title':'Inscription','icon':'mdi-account-plus','action': '2',}]"
    			+ "}");

		compo.addData("drawer", false);
		compo.addMethod("triggerModal", "switch (number) {\r\n"
				+ "  case '1':\r\n"
				+ "    this.$refs.login.loginModal = !this.$refs.login.loginModal;\r\n"
				+ "    break;\r\n"
				+ "  case '2':\r\n"
				+ "this.$refs.register.registerModal = !this.$refs.register.registerModal;"
				+ "    break;\r\n"
				+ "  case '3':\r\n"
				+ "window.location.href='/logout';"
				+ "	   break;"
				+ "  default:}","number");
		compo.onBeforeMount("this.getMenuItems();");

        compo.setDefaultTemplateFile();
        compo.createFile(false);
    }
}
