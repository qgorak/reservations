//Script generated with VueComponent at Tue Nov 17 14:34:07 CET 2020
Vue.component('avatar',{
	"props":["size","user"],"data":function() {
		 return {
			"avatar":{
				src:null,initials:null
				}
			}
		;
		}
	,"methods":{
		"getAvatar":function (){
			let self = this;
			if(this.user.id != 0){
				this.
				$http['get']('/rest/image/user/'+this.user.id, {
					}
				).then(function(response,i) {
					if(response.data[0]!=self.user.id){
						self.avatar.src='/user-photos/'+self.user.id+'/avatar/'+response.data[0];
						}
					else{
						self.avatar.initials=self.user.login.charAt(0).toUpperCase();
						}
					}
				);
				}
			}
		}
	,"beforeMount":function(){
		this.getAvatar();
		}
	,"template":"<div><v-avatar        v-if=\"avatar.src!=null\"          color=\"white\"          :size=\"size\">        <img          v-bind:src=\"avatar.src\"          alt=\"John\"        ></v-avatar>         <v-avatar                 v-if=\"avatar.initials!=null\"                color=\"brown\"                :size=\"size\"              >                <span class=\"white--text headline\">{{ avatar.initials}} </span>              </v-avatar>              </div>"
	}
);