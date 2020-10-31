package s4.spring.reservations.components;

import java.io.IOException;

import io.github.jeemv.springboot.vuejs.components.VueComponent;

public class Bar {
    public static void main(String[]args) throws IOException {
        VueComponent compo=new VueComponent("bar");
        compo.setDefaultTemplateFile();
        compo.createFile(false);
    }

}
