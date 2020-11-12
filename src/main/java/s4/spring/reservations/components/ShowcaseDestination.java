package s4.spring.reservations.components;

import java.io.IOException;

import io.github.jeemv.springboot.vuejs.components.VueComponent;
import io.github.jeemv.springboot.vuejs.utilities.Http;

public class ShowcaseDestination {
    public static void main(String[]args) throws IOException {
        VueComponent compo=new VueComponent("showcase-destination");
        compo.addData("model",null);
        compo.addMethod("onCardClick", ""
        		+ "if(this.model!==n-1){"
        		+ "this.model = n - 1;"
        		+ "}else{alert('ok')}"
        		+ "","n");
        compo.setDefaultTemplateFile();
        compo.createFile(false);
    }
}





