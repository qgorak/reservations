package s4.spring.reservations.components;

import java.io.IOException;

import io.github.jeemv.springboot.vuejs.components.VueComponent;

public class DatePicker {
    public static void main(String[]args) throws IOException {
        VueComponent compo=new VueComponent("date-picker");
		compo.addData("menu1", false);
		compo.addData("date");
		compo.addData("endDate","2020-10-30");
		compo.addDataRaw("dates", "[]");
		compo.addData("datemin");
		compo.onBeforeMount("this.datemin=new Date().toLocaleDateString('fr-CA');"
				+ "var urlParams = new URLSearchParams(window.location.search);"
				+ "var start = urlParams.get('start');"
				+ "this.dates[0]=start;"
				+ "var end = urlParams.get('end');"
				+ "this.dates[1]=end;");
		compo.addWatcher("dates","if(this.dates.length==2){this.$emit('dates-change');}");
        compo.setDefaultTemplateFile();
        compo.createFile(false);
    }
}