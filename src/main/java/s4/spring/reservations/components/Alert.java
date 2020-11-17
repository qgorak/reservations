package s4.spring.reservations.components;

import java.io.IOException;

import io.github.jeemv.springboot.vuejs.components.VueComponent;

public class Alert {
    public static void main(String[]args) throws IOException {
    VueComponent compo=new VueComponent("alert");
    compo.addData("type","success");
    compo.addData("message","");
    compo.addData("showAlert",false);
    compo.addMethod("triggerAlert", "this.message=message;this.type=type;this.showAlert=true;setTimeout(() => this.showAlert = false, 2500);","type,message");
    compo.setDefaultTemplateFile();
    compo.createFile(false);
    }
}
