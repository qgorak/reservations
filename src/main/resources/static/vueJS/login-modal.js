//Script generated with VueComponent at Wed Oct 28 14:35:13 CET 2020
Vue.component('login-modal',{
	"props":[],"data":function() {
		 return {
			"loginModal":false
			}
		;
		}
	,"template":"<v-dialog   v-model=\"loginModal\"   persistent   max-width=\"290\"   >               <form  method=\"post\" action=\"/login\">   <v-card>      <v-card-title class=\"headline\">         Login      </v-card-title>      <v-card-text>            <p>               <label for=\"username\" class=\"sr-only\">Username</label>               <input type=\"text\" id=\"username\" name=\"username\" placeholder=\"Username\" required autofocus>            </p>            <p>               <label for=\"password\" class=\"sr-only\">Password</label>               <input type=\"password\" id=\"password\" name=\"password\"  placeholder=\"Password\" required>            </p>      </v-card-text>      <v-card-actions>      <v-spacer></v-spacer>      <v-btn         color=\"green darken-1\"         text         @click=\"loginModal = false\"         >      Cancel      </v-btn>      <v-btn         color=\"green darken-1\"         text         type=\"submit\"         @click=\"loginModal = false\"         >      Login      </v-btn>      </v-card-actions>   </v-card>          </form></v-dialog>"
	}
);