package s4.spring.reservations.components;

import java.io.IOException;

import io.github.jeemv.springboot.vuejs.components.VueComponent;
import io.github.jeemv.springboot.vuejs.utilities.Http;

public class ButtonReservation {
    public static void main(String[]args) throws IOException {
        VueComponent compo=new VueComponent("reservation-button");
        compo.setProps("lodgement","images","dates","nbr");
        compo.addData("dialog",false);
        compo.addDataRaw("reservation","{start:null,end:null,lodgement:null}");
		compo.addMethod("postReservation", "let self=this;this.reservation.start=this.dates[0];"
				+ "this.reservation.end=this.dates[1];this.reservation.lodgement=this.lodgement;"
				+ Http.post( "/rest/reservations/","self.reservation", "self.reservation.start='';self.reservation.end='';self.reservation.lodgement='';self.dialog=false;"));
        compo.setDefaultTemplateFile();
        compo.createFile(false);
    }
}
