//Script generated with VueComponent at Tue Nov 17 15:15:23 CET 2020
Vue.component('drawer',{
	"props":["user"],"data":function() {
		 return {
			"drawer":false
			}
		;
		}
	,"methods":{
		"triggerModal":function (number){
			switch (number) {				
  case '1':
    this.
				$refs.login.dialog = !this.
				$refs.login.dialog;				
    break;				
  case '2':
this.
				$refs.register.registerModal = !this.
				$refs.register.registerModal;
				    break;				
  case '3':
document.cookie = 'user=;expires=Thu, 01 Jan 1970 00:00:01 GMT;';
				this.
				$http['post']('/logout', {
					}
				).then(function(response){
					}
				);
				document.location.reload(true);
					   break;
				  default:
				}
			}
		,"getMenuItems":function (){
			this.items=[{
				'title':'Déconnexion','icon':'mdi-logout','action':'3',
				}
			,{
				'title': 'Mes Logements','icon': 'mdi-home-city', 'link': '/lodgement',
				}
			,{
				'title':'Mes Reservations','icon':'mdi-file-document-edit','link': '/reservation',
				}
			,{
				'title': 'Paramètres','icon': 'mdi-cog-outline','link': '/user/me',
				}
			]
			}
		}
	,"beforeMount":function(){
		this.getMenuItems();
		}
	,"template":"<v-navigation-drawer   v-model=\"drawer\"   left   temporary   app>   <login-modal ref=\"login\"></login-modal>   <register-modal @toggle-modal=\"$refs.register.registerModal = false\" ref=\"register\"></register-modal>          <v-list v-if=\"user.id!==''\">          <v-list-item>              <avatar v-if=\"user.id!==''\" :user=\"user\" :size=\"56\"></avatar>          </v-list-item>            <v-list-item link>            <v-list-item-content>              <v-list-item-title class=\"title\">                {{user.login}}              </v-list-item-title>              <v-list-item-subtitle>{{ user.mail }}</v-list-item-subtitle>            </v-list-item-content>           </v-list-item>           </v-list>   <v-divider></v-divider>      <v-list      v-if=\"user.id==''\"      dense      nav      >      <v-list-item @click=\"triggerModal('1')\">               <v-list-item-icon>            <v-icon>mdi-login-variant</v-icon>         </v-list-item-icon>         <v-list-item-content>            <v-list-item-title>Se Connecter</v-list-item-title>         </v-list-item-content>      </v-list-item>            <v-list-item @click=\"triggerModal('2')\">               <v-list-item-icon>            <v-icon>mdi-account-plus</v-icon>         </v-list-item-icon>         <v-list-item-content>            <v-list-item-title>Inscription</v-list-item-title>         </v-list-item-content>      </v-list-item>            </v-list>      <v-divider></v-divider>   <v-list      v-if=\"user.id!==''\"      dense      nav      >      <v-list-item         v-for=\"item in items\"         :key=\"item.title\"         :href=\"item.link\"         @click=\"triggerModal(item.action)\"         link         >         <v-list-item-icon>            <v-icon>{{item.icon}}</v-icon>         </v-list-item-icon>         <v-list-item-content>            <v-list-item-title>{{item.title}}</v-list-item-title>         </v-list-item-content>      </v-list-item>   </v-list></v-navigation-drawer>"
	}
);