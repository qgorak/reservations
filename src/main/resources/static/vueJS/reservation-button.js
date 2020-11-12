//Script generated with VueComponent at Thu Nov 12 22:28:10 CET 2020
Vue.component('reservation-button',{
	"props":["images","lodgement","dates","nbr"],"data":function() {
		 return {
			"dialog":false,"reservation":{
				start:null,end:null,lodgement:null
				}
			}
		;
		}
	,"methods":{
		"postReservation":function (){
			let self=this;
			this.reservation.start=this.dates[0];
			this.reservation.end=this.dates[1];
			this.reservation.lodgement=this.lodgement;
			this.
			$http['post']('/rest/reservations/', self.reservation).then(function(response){
				self.reservation.start='';
				self.reservation.end='';
				self.reservation.lodgement='';
				self.dialog=false;
				}
			);
			}
		}
	,"template":"   <v-dialog      v-model=\"dialog\"      fullscreen      hide-overlay      >        <template v-slot:activator=\"{ on }\">    <v-btn color=\"primary\" dark v-on=\"on\"  class=\"mx-auto\"><slot></slot></v-btn>  </template>      <v-card>      <v-container style=\"background:white;\">         <v-btn            icon            @click=\"dialog = false\"            >            <v-icon>mdi-close</v-icon>         </v-btn>         <v-row            class=\"mb-6\"            no-gutters            >            <v-col               cols=\"6\"               >               <h1>Confirmer et payer</h1>            </v-col>         </v-row>         <v-row            class=\"mb-6\"            no-gutters            >            <v-col               cols=\"6\"               >               <h2 class=\"pb-5\">Votre voyage:</h2>               <div class=\"pb-5\">                   <h3>Dates:</h3>                {{dates[0]}} au {{dates[1]}}               </div>               <div v-if=\"nbr!='null'\" class=\"pb-5\">                   <h3>Nombre de voyageurs:</h3>                   {{nbr}} personnes               </div>               <v-divider class=\"pb-5\"></v-divider>               <h3 class=\"pb-5\">Requis:</h3>               <v-textarea                  rounded                  label=\"Envoyez un message à l'hôte\"                  filled                  full-width                  ></v-textarea>               <v-btn                  color=\"primary\"                  @click=\"postReservation\"                  >                  Payer               </v-btn>            </v-col>            <v-col class=\"mx-3\"               cols=\"3\"               >               <v-card rounded outlined elevation=\"2\" class=\"pa-3\">                  <v-list-item-content>Type de logement: {{lodgement.type}}</v-list-item-content>                  <v-list-item-content>{{lodgement.title}}</v-list-item-content>                  <v-carousel hide-delimiters height=\"200\">                     <v-carousel-item                        v-for=\"(item,i) in images\"                        :key=\"i\"                        :src=\"item.src\"                        ></v-carousel-item>                  </v-carousel>                  <v-divider></v-divider>                  <v-list-item-content>                     <h3>Détails du prix</h3>                  </v-list-item-content>                  <v-list-item-content>{{lodgement.price}} €</v-list-item-content>                  </v-card>            </v-col>         </v-row>      </v-container>      </v-card>   </v-dialog>"
	}
);