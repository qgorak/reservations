package s4.spring.reservations.components;

import java.io.IOException;

import io.github.jeemv.springboot.vuejs.components.VueComponent;
import io.github.jeemv.springboot.vuejs.utilities.JavascriptResource;

public class SearchBar {
    public static void main(String[]args) throws IOException {
        VueComponent compo=new VueComponent("bar-search");
		compo.addDataRaw("nb","[1,2,3,4,5]");
		compo.addData("nbTravellers","null");
		compo.addData("menuDate",false);
		compo.addData("datesText");
		compo.addData("shrinkOnScroll",true);
		compo.addData("shrinked",false);
		compo.onMounted("window.onscroll = () => {this.changeColor();};");
		compo.addMethod("changeColor",
				"if (document.body.scrollTop > 72 ||document.documentElement.scrollTop > 72) {"
		        + "this.shrinked = true;"
		        +"} else {"
		        + "this.shrinked = false;"
		        +"}");
		try {compo.addMethod("recherche",JavascriptResource.create("recherche").parseContent());}
		catch (IOException e) {e.printStackTrace();}
		compo.addMethod("datesTextMethod","this.datesText=this.$refs.dates.dates.join(' au ');");
        compo.setDefaultTemplateFile();
        compo.createFile(false);
    }
}