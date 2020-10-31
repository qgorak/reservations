//Script generated with VueComponent at Thu Oct 29 00:50:42 CET 2020
Vue.component('register-modal',{
	"props":[],"data":function() {
		 return {
			"registerModal":false
			}
		;
		}
	,"template":"<v-dialog   v-model=\"registerModal\"   persistent   max-width=\"600\"   >   <v-card>      <v-card-title class=\"headline\">         Register      </v-card-title>      <v-card-text>      <register-stepper @toggle-modal=\"registerModal = false\"></register-stepper>      </v-card-text>                        <v-btn                      @click=\"registerModal = false\"                     text>                     Cancel                  </v-btn>   </v-card></v-dialog>"
	}
);