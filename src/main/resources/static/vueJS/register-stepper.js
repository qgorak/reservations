//Script generated with VueComponent at Mon Nov 09 18:34:42 CET 2020
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
		"test":function (){
			this.
			$emit('toggle-modal');
			}
		,"registerUser":function (){
			let self=this;
			this.
			$http['post']('/rest/users/', self.newUser).then(function(response){
				self.
				$emit('toggle-modal');
				}
			);
			}
		,"checkUserLogin":function (){
			let self = this;
			self.usernameValid=false;
			if(self.newUser.login.length>2){
					if(((event.keyCode >=65 && event.keyCode <= 90) || (event.keyCode >=48 && event.keyCode <= 57) || event.keyCode === 8 || event.keyCode === 27 || event.keyCode === 46 || event.keyCode === 32 )){
							clearTimeout(self.timeOut);
							this.timeOut =setTimeout(function(){
						self.
						$http['get']('/rest/users/user/'+self.newUser.login).then(function(response){
							if(response.data==''){
								self.usernameValid=true;
								}
							}
						)		
						}
					, 500);
					}
				}
			else{
					this.destination={
					name:null,loc:null
					}
				;
					this.search=[];
				}
			}
		}
	,"template":"         <v-stepper          class=\"elevation-0\"         v-model=\"e1\">            <v-stepper-header            class=\"elevation-0\">               <v-stepper-step                  :complete=\"e1 > 1\"                  step=\"1\"                  >                  Email               </v-stepper-step>               <v-divider></v-divider>               <v-stepper-step                  :complete=\"e1 > 2\"                  step=\"2\"                  >                  Nom d'utilisateur               </v-stepper-step>               <v-divider></v-divider>               <v-stepper-step step=\"3\">                  Détails               </v-stepper-step>            </v-stepper-header>            <v-stepper-items>               <v-stepper-content               step=\"1\"               class=\"elevation-0\">                  <v-card                     class=\"mb-12 elevation-0\"                     height=\"auto\"                     >                     <v-text-field                        v-model=\"newUser.mail\"                        label=\"E-mail\"                        :rules=\"emailRules\"                        required                        ></v-text-field>                  </v-card>                  <v-btn                     color=\"primary\"                     @click=\"e1 = 2\"                     >                     Suivant                  </v-btn>                  <v-btn                     text                     @click=\"test\"                     >                     Retour                  </v-btn>               </v-stepper-content>               <v-stepper-content               class=\"elevation-0\"               step=\"2\">                  <v-card                     class=\"mb-12 elevation-0\"                     height=\"auto\"                     >                     <div v-if=\"usernameValid==true\">                     Ce nom d'utilisateur est disponible                     </div>                     <div v-if=\"usernameValid==false\">                     Ce nom d'utilisateur est indisponible                     </div>                     <v-row      class=\"mb-6\"      no-gutters      >                     <v-text-field                        v-model=\"newUser.login\"                        v-on:keyup=\"checkUserLogin\"                        :counter=\"10\"                        label=\"Username\"                        required                        ></v-text-field>                        </v-row>                  </v-card>                  <v-btn                     color=\"primary\"                     @click=\"e1 = 3\"                     :disabled=\"!usernameValid\"                     >                     Suivant                  </v-btn>                  <v-btn                     color=\"primary\"                     @click=\"e1 = 1\"                     >                     Retour                  </v-btn>                                 </v-stepper-content>               <v-stepper-content               class=\"elevation-0\"               step=\"3\">                  <v-card                     class=\"mb-12 elevation-0\"                     height=\"auto\"                     >                                <v-text-field        v-model=\"newUser.password\"        label=\"Password\"        counter        :append-icon=\"showPassword ? 'mdi-eye' : 'mdi-eye-off'\"        :type=\"showPassword ? 'text' : 'password'\"        @click:append=\"showPassword = !showPassword\"        :rules=\"passwordRules\"        required      ></v-text-field>                                <v-text-field        v-model=\"passwordConfirm\"        label=\"confirm Password\"        counter        :append-icon=\"showPassword ? 'mdi-eye' : 'mdi-eye-off'\"        :type=\"showPassword ? 'text' : 'password'\"        @click:append=\"showPassword = !showPassword\"        :rules=\"passwordRules\"        required      ></v-text-field>                  </v-card>                  <v-btn                     color=\"primary\"                     :disabled=\"newUser.password!==passwordConfirm\"                     @click=\"registerUser()\"                     >                     Suivant                  </v-btn>                  <v-btn                     color=\"primary\"                     @click=\"e1=2\"                     >                     Retour                  </v-btn>                                 </v-stepper-content>            </v-stepper-items>         </v-stepper>"
	}
);