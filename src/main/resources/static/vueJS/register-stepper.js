//Script generated with VueComponent at Wed Nov 04 12:31:29 CET 2020
Vue.component('register-stepper',{
	"props":[],"data":function() {
		 return {
			"valid":true,"usernameRules":[ v => !!v || 'Name is required',      v => (v && v.length <= 10) || 'Name must be less than 10 characters',],"usernameValid":false,"passwordConfirm":"","newUser":{
				login:'',password:'',mail:''
				}
			,"passwordRules":[  v => !!v || 'Password is required',],"showPassword":false,"emailRules":[
      v => !!v || 'E-mail is required',
      v => /.+@.+/.test(v) || 'E-mail must be valid',
    ],"e1":1,"timeOut":null
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
	,"template":"         <v-stepper v-model=\"e1\">            <v-stepper-header>               <v-stepper-step                  :complete=\"e1 > 1\"                  step=\"1\"                  >                  e-mail               </v-stepper-step>               <v-divider></v-divider>               <v-stepper-step                  :complete=\"e1 > 2\"                  step=\"2\"                  >                  username               </v-stepper-step>               <v-divider></v-divider>               <v-stepper-step step=\"3\">                  details               </v-stepper-step>            </v-stepper-header>            <v-stepper-items>               <v-stepper-content step=\"1\">                  <v-card                     class=\"mb-12\"                     color=\"grey lighten-1\"                     height=\"200px\"                     >                     <v-text-field                        v-model=\"newUser.mail\"                        label=\"E-mail\"                        :rules=\"emailRules\"                        required                        ></v-text-field>                  </v-card>                  <v-btn                     color=\"primary\"                     @click=\"e1 = 2\"                     >                     Continue                  </v-btn>               </v-stepper-content>               <v-stepper-content step=\"2\">                  <v-card                     class=\"mb-12\"                     color=\"grey lighten-1\"                     height=\"200px\"                     >                     <v-text-field                        v-model=\"newUser.login\"                        :counter=\"10\"                        label=\"Username\"                        required                        ></v-text-field>                  </v-card>                  <v-btn                     color=\"primary\"                     @click=\"e1 = 3\"                     >                     Continue                  </v-btn>               </v-stepper-content>               <v-stepper-content step=\"3\">                  <v-card                     class=\"mb-12\"                     color=\"grey lighten-1\"                     height=\"200px\"                     >                                <v-text-field        v-model=\"newUser.password\"        label=\"Password\"        counter        :append-icon=\"showPassword ? 'mdi-eye' : 'mdi-eye-off'\"        :type=\"showPassword ? 'text' : 'password'\"        @click:append=\"showPassword = !showPassword\"        :rules=\"passwordRules\"        required      ></v-text-field>                                <v-text-field        v-model=\"passwordConfirm\"        label=\"confirm Password\"        counter        :append-icon=\"showPassword ? 'mdi-eye' : 'mdi-eye-off'\"        :type=\"showPassword ? 'text' : 'password'\"        @click:append=\"showPassword = !showPassword\"        :rules=\"passwordRules\"        required      ></v-text-field>                  </v-card>                  <v-btn                     color=\"primary\"                     :disabled=\"newUser.password!==passwordConfirm\"                     @click=\"registerUser()\"                     >                     Continue                  </v-btn>               </v-stepper-content>            </v-stepper-items>         </v-stepper>"
	}
);