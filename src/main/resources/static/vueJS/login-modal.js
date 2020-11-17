//Script generated with VueComponent at Tue Nov 17 14:54:19 CET 2020
Vue.component('login-modal',{
	"props":[],"data":function() {
		 return {
			"valid":false,"showProgressBar":false,"dialog":false,"password":"","usernameRules":[  v => !!v || 'Name is required',  v => v.length <= 10 || 'Name must be less than 10 characters',],"query":false,"passwordRules":[  v => !!v || 'Password is required',],"showPassword":false,"errorLogin":null,"interval":0,"value":0,"username":""
			}
		;
		}
	,"methods":{
		"queryAndIndeterminate":function (){
			this.query = true;
			this.showProgressBar = true;
			this.value = 0;
			setTimeout(() => {
				  this.query = false;
				  this.interval = setInterval(() => {
					    if (this.value === 100) {
						      clearInterval(this.interval);
						      this.showProgressBar = false;
						}
					   this.value += 25;
					}
				, 1000);
				}
			, 2500);
			}
		,"login":function (){
			let formData = new FormData();
			  formData.set('username', this.username);
			  formData.set('password', this.password);
			  let self = this;
			  this.queryAndIndeterminate();
			  this.
			$http.post('/login', formData, {
				headers: {
					'Content-Type': 'application/x-www-form-urlencoded'
					}
				}
			)   .then((result) => {
						 self.
				$set(self.
				$root.user,'id',result.headers.userid);
						 self.
				$set(self.
				$root.user,'login',result.headers.username);
						 self.
				$set(self.
				$root.user,'mail',result.headers.mail);
						 document.cookie ='user='+JSON.stringify({
					 id:result.headers.userid,login:result.headers.username , mail: result.headers.mail 
					}
				);
						 self.dialog=false;
							self.query=false;
							self.showProgressBar=false;
							self.
				$root.
				$refs.alert.triggerAlert('success','Successful Log in')     
				}
			)   .catch((error) => {
						 if(error.response!=null){
								self.errorLogin = error.response.data.exception;
								self.query=false;
								self.showProgressBar=false;
								self.
					$root.
					$refs.alert.triggerAlert('error','Log in failed')		  
					}
				}
			)
			}
		}
	,"beforeDestroy":function(){
		clearInterval(this.interval)
		}
	,"template":"<v-dialog   v-model=\"dialog\"   persistent   max-width=\"290\"   >     <v-form v-model=\"valid\">            <v-card>       <div style=\"min-height: 4px;\">      <v-progress-linear        v-model=\"value\"        :active=\"showProgressBar\"        :indeterminate=\"query\"      ></v-progress-linear>    </div>      <v-card-title class=\"headline\">         Connexion      </v-card-title>      <v-card-text>      <div v-if=\"errorLogin!==null\">{{errorLogin}}</div>                       <v-text-field        v-model=\"username\"        label=\"Nom d'utilisateur\"        :rules=\"usernameRules\"        required      ></v-text-field>                                <v-text-field        v-model=\"password\"        label=\"Mot de passe\"        counter        :append-icon=\"showPassword ? 'mdi-eye' : 'mdi-eye-off'\"        :type=\"showPassword ? 'text' : 'password'\"        @click:append=\"showPassword = !showPassword\"        :rules=\"passwordRules\"        required      ></v-text-field>        </v-card-text>      <v-card-actions>      <v-spacer></v-spacer>      <v-btn         color=\"green darken-1\"         text         @click=\"dialog = false\"         >      Retour      </v-btn>      <v-btn         color=\"green darken-1\"         text         :disabled=\"!valid\"         @click=\"login()\"         >      Connexion      </v-btn>      </v-card-actions>   </v-card>    </v-form>          </v-dialog>"
	}
);