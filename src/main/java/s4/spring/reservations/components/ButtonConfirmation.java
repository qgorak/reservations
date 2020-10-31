package s4.spring.reservations.components;

import java.io.IOException;

import io.github.jeemv.springboot.vuejs.components.VueComponent;

public class ButtonConfirmation {
    public static void main(String[]args) throws IOException {
        VueComponent compo=new VueComponent("confirm-button");
        compo.setProps("title","message","validatecaption","type","size");
        compo.addProp("width", 500);
        compo.addData("dialog",false);
        compo.addMethod("validation", "this.$emit('validation');this.dialog=false;");
        compo.setDefaultTemplateFile();
        compo.createFile(false);
    }
}
