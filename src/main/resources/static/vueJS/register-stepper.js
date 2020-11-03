//Script generated with VueComponent at Tue Nov 03 16:55:47 CET 2020
Vue.component('register-stepper',{
	"props":[],"data":function() {
		 return {
			"valid":true,"usernameRules":[ v => !!v || 'Name is required',      v => (v && v.length <= 10) || 'Name must be less than 10 characters',],"passwordConfirm":"","newUser":{
				login:'',password:'',mail:''
				}
			,"passwordRules":[],"emailRules":[
      v => !!v || 'E-mail is required',
      v => /.+@.+/.test(v) || 'E-mail must be valid',
    ],"e1":1
			}
		;
		}
	,"methods":{
		"registerUser":function (){
			let self=this;
			this.
			$http['post']('/rest/users/', self.newUser).then(function(response){
				self.
				$emit('toggle-modal');
				}
			);
			}
		}
	,"template":"         <v-stepper v-model=\"e1\">            <v-stepper-header>               <v-stepper-step                  :complete=\"e1 > 1\"                  step=\"1\"                  >                  e-mail               </v-stepper-step>               <v-divider></v-divider>               <v-stepper-step                  :complete=\"e1 > 2\"                  step=\"2\"                  >                  username               </v-stepper-step>               <v-divider></v-divider>               <v-stepper-step step=\"3\">                  details               </v-stepper-step>            </v-stepper-header>            <v-stepper-items>               <v-stepper-content step=\"1\">                  <v-card                     class=\"mb-12\"                     color=\"grey lighten-1\"                     height=\"200px\"                     >                     <v-text-field                        v-model=\"newUser.mail\"                        label=\"E-mail\"                        :rules=\"emailRules\"                        required                        ></v-text-field>                  </v-card>                  <v-btn                     color=\"primary\"                     @click=\"e1 = 2\"                     >                     Continue                  </v-btn>               </v-stepper-content>               <v-stepper-content step=\"2\">                  <v-card                     class=\"mb-12\"                     color=\"grey lighten-1\"                     height=\"200px\"                     >                     <v-text-field                        v-model=\"newUser.login\"                        :counter=\"10\"                        label=\"Username\"                        required                        ></v-text-field>                  </v-card>                  <v-btn                     color=\"primary\"                     @click=\"e1 = 3\"                     >                     Continue                  </v-btn>               </v-stepper-content>               <v-stepper-content step=\"3\">                  <v-card                     class=\"mb-12\"                     color=\"grey lighten-1\"                     height=\"200px\"                     >                     <v-text-field                        v-model=\"newUser.password\"                        :counter=\"10\"                        label=\"Password\"                        required                        ></v-text-field>                     <v-text-field                        v-model=\"passwordConfirm\"                        :counter=\"10\"                        label=\"Confirm password\"                        required                        ></v-text-field>                  </v-card>                  <v-btn                     color=\"primary\"                     @click=\"registerUser()\"                     >                     Continue                  </v-btn>               </v-stepper-content>            </v-stepper-items>         </v-stepper>"
	}
);