//Script generated with VueComponent at Sun Nov 08 11:57:13 CET 2020
Vue.component('register-modal',{
	"props":[],"data":function() {
		 return {
			"registerModal":false
			}
		;
		}
	,"methods":{
		"reset":function (){
			this.registerModal = false;
			this.
			$refs.stepper.e1=1;
			this.
			$refs.stepper.newUser.mail='';
			this.
			$refs.stepper.newUser.password='';
			this.
			$refs.stepper.newUser.password=''
			}
		}
	,"template":"<v-dialog   v-model=\"registerModal\"   persistent   max-width=\"600\"   >   <v-card>      <v-card-title class=\"headline\">         Register      </v-card-title>      <v-card-text>      <register-stepper ref=\"stepper\" @toggle-modal=\"registerModal = false\"></register-stepper>      </v-card-text>                        <v-btn                      @click=\"reset\"                     text>                     Cancel                  </v-btn>   </v-card></v-dialog>"
	}
);