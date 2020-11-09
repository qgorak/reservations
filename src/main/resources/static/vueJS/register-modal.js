//Script generated with VueComponent at Mon Nov 09 18:21:51 CET 2020
Vue.component('register-modal',{
	"props":[],"data":function() {
		 return {
			"registerModal":false
			}
		;
		}
	,"template":"<v-dialog   v-model=\"registerModal\"   persistent   max-width=\"600\"   >   <v-card>      <v-card-title class=\"headline\">         Inscription      </v-card-title>      <v-card-text>      <register-stepper ref=\"stepper\" @toggle-modal=\"registerModal = false\"></register-stepper>      </v-card-text>   </v-card></v-dialog>"
	}
);