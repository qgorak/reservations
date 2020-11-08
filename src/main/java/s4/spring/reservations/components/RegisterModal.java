package s4.spring.reservations.components;

import java.io.IOException;

import io.github.jeemv.springboot.vuejs.components.VueComponent;

public class RegisterModal {
    public static void main(String[]args) throws IOException {
    VueComponent compo=new VueComponent("register-modal");
    compo.addData("registerModal",false);
    compo.addMethod("reset","this.registerModal = false;this.$refs.stepper.e1=1;this.$refs.stepper.newUser.mail='';this.$refs.stepper.newUser.password='';this.$refs.stepper.newUser.password=''");
    compo.setDefaultTemplateFile();
    compo.createFile(false);
    }
}
