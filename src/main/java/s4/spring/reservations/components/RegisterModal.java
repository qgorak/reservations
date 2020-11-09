package s4.spring.reservations.components;

import java.io.IOException;

import io.github.jeemv.springboot.vuejs.components.VueComponent;

public class RegisterModal {
    public static void main(String[]args) throws IOException {
    VueComponent compo=new VueComponent("register-modal");
    compo.addData("registerModal",false);
    compo.setDefaultTemplateFile();
    compo.createFile(false);
    }
}
