package s4.spring.reservations.components;

import java.io.IOException;

import io.github.jeemv.springboot.vuejs.components.VueComponent;
import io.github.jeemv.springboot.vuejs.utilities.Http;

public class ButtonEditLodgement {
    public static void main(String[]args) throws IOException {
        VueComponent compo=new VueComponent("edit-lodgement-button");
        compo.setProps("lodgement");
        compo.addData("dialog",false);
		compo.addMethod("updateLodgement", "let self=this;" + Http.patch("'/rest/lodgements/'+self.lodgement.id", "self.lodgement", "alert(self.lodgement.title+' a bien été mis a jour');", ""), "lodgement");
        compo.setDefaultTemplateFile();
        compo.createFile(false);
        
        
    }
}
