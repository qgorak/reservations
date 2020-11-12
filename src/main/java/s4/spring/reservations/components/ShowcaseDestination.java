package s4.spring.reservations.components;

import java.io.IOException;

import javax.persistence.Entity;

import io.github.jeemv.springboot.vuejs.components.VueComponent;
import io.github.jeemv.springboot.vuejs.utilities.Http;

public class ShowcaseDestination {
    public static void main(String[]args) throws IOException {
        VueComponent compo=new VueComponent("showcase-destination");
        compo.addData("model",null);
        compo.addDataRaw("destinations", "[]");
        compo.addMethod("onCardClick", ""
        		+ "if(this.model!==n-1){"
        		+ "this.model = n - 1;"
        		+ "}else{"
        		+ "window.location.replace('/lodgement/search?lon='+item.lon+'&lat='+item.lat);"
        		+ "}"
        		+ "","n,item");
		compo.onBeforeMount("let self = this;"
				+ Http.get("/rest/destinations", "self.destinations = response.data;"));
        compo.setDefaultTemplateFile();
        compo.createFile(false);
    }
}





