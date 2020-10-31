package s4.spring.reservations.components;

import java.io.IOException;

import io.github.jeemv.springboot.vuejs.components.VueComponent;
import io.github.jeemv.springboot.vuejs.utilities.Http;

public class ButtonReservation {
    public static void main(String[]args) throws IOException {
        VueComponent compo=new VueComponent("reservation-button");
        compo.setProps("lodgement","images","dates");
        compo.addData("dialog",false);
        compo.addDataRaw("reservation","{start:'',end:'',lodgement:''}");
		compo.addMethod("postReservation", "this.reservation.start=this.dates[0];this.reservation.end=this.dates[1];this.reservation.lodgement=this.lodgement;let self=this;" + Http.post( "/rest/reservations/","self.reservation", "self.reservationModal=false;"));
        compo.setDefaultTemplateFile();
        compo.createFile(false);
    }
}
