//Script generated with VueComponent at Sat Oct 31 20:12:20 CET 2020
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
				$refs.login.loginModal = !this.
				$refs.login.loginModal;				
    break;				
  case '2':
this.
				$refs.register.registerModal = !this.
				$refs.register.registerModal;
				    break;				
  case '3':
window.location.href='/logout';
					   break;
				  default:
				}
			}
		,"getMenuItems":function (){
			if(this.user.id != 0){
				this.items=[{
					'title':'Logout','icon':'mdi-logout','action':'3',
					}
				,{
					'title': 'Mes Logements','icon': 'mdi-home-city', 'link': '/lodgement',
					}
				,{
					'title':'Mes Reservations','icon':'mdi-file-document-edit','link': '/reservation',
					}
				,{
					'title': 'Settings','icon': 'mdi-cog-outline','link': '/user/me',
					}
				]
				}
			else{
				this.items=[{
					'title': 'Se Connecter','icon': 'mdi-login-variant', 'action': '1',
					}
				,{
					'title':'Inscription','icon':'mdi-account-plus','action': '2',
					}
				]
				}
			}
		}
	,"beforeMount":function(){
		this.getMenuItems();
		}
	,"template":"<v-navigation-drawer   v-model=\"drawer\"   left   temporary   app>   <login-modal ref=\"login\"></login-modal>   <register-modal ref=\"register\"></register-modal>   <v-list-item>      <v-list-item-content>         <v-list-item-title class=\"title\">        <avatar :user=\"user\"></avatar>            {{user.login}}         </v-list-item-title>      </v-list-item-content>   </v-list-item>   <v-divider></v-divider>   <v-list      dense      nav      >      <v-list-item         v-for=\"item in items\"         :key=\"item.title\"         :href=\"item.link\"         @click=\"triggerModal(item.action)\"         link         >         <v-list-item-icon>            <v-icon>{{item.icon}}</v-icon>         </v-list-item-icon>         <v-list-item-content>            <v-list-item-title>{{item.title}}</v-list-item-title>         </v-list-item-content>      </v-list-item>   </v-list></v-navigation-drawer>"
	}
);