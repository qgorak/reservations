package s4.spring.reservations.components;

import java.io.IOException;

import io.github.jeemv.springboot.vuejs.components.VueComponent;
import io.github.jeemv.springboot.vuejs.utilities.JavascriptResource;

public class DatePicker {
    public static void main(String[]args) throws IOException {
        VueComponent compo=new VueComponent("date-picker");
		compo.addData("menu1", false);
		compo.addData("date");
		compo.addData("endDate","2020-10-30");
		compo.addDataRaw("dates", "[]");
		compo.addData("datemin");
		compo.addDataRaw("allowedDates","['2020-10-08', '2020-10-09']");
		compo.addDataRaw("dateFormatted", "this.formatDate(new Date().toISOString().substr(0, 10))");
		compo.addComputed("dateRangeText", "return this.dates.join(' au ')");
		compo.addMethod("getAllowedDates",JavascriptResource.create("getAllowedDates").parseContent(),"val");
		try {compo.addMethod("formatDate",JavascriptResource.create("formatDate").parseContent(),"date");}
		catch (IOException e) {e.printStackTrace();}
		try {compo.addMethod("addDays",JavascriptResource.create("addDays").parseContent(),"days");}
		catch (IOException e) {e.printStackTrace();}
		try {compo.addMethod("parseDate",JavascriptResource.create("parseDate").parseContent(),"date");}
		catch (IOException e) {e.printStackTrace();}
		compo.onBeforeMount("this.datemin=new Date().toLocaleDateString('fr-FR');");
		compo.addWatcher("dates","if(this.dates.length==2){this.$emit('date-change');}");
        compo.setDefaultTemplateFile();
        compo.createFile(false);
    }
}

