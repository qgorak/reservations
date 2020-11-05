//Script generated with VueComponent at Thu Nov 05 08:26:01 CET 2020
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