package s4.spring.reservations.components;

import java.io.IOException;

import io.github.jeemv.springboot.vuejs.components.VueComponent;

public class LoginModal {
    public static void main(String[]args) throws IOException {
        VueComponent compo=new VueComponent("login-modal");
        compo.addData("loginModal",false);
        compo.setDefaultTemplateFile();
        compo.createFile(false);
    }
}
