package s4.spring.reservations.components;

import java.io.IOException;

import io.github.jeemv.springboot.vuejs.components.VueComponent;
import io.github.jeemv.springboot.vuejs.utilities.JavascriptResource;

public class AdressAutocomplete {
    public static void main(String[]args) throws IOException {
        VueComponent compo=new VueComponent("adress-autocomplete");
		compo.addDataRaw("search","[]");
		compo.addDataRaw("destination","{name:null,loc:null}");
		compo.addDataRaw("error", "false");
		try{compo.addMethod("suggestion",JavascriptResource.create("suggestion").parseContent());}
		catch (IOException e) {e.printStackTrace();}
		compo.addData("selected", "");
        compo.setDefaultTemplateFile();
        compo.createFile(false);
    }
}
