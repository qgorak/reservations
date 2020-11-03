//Script generated with VueComponent at Sat Oct 31 19:51:37 CET 2020
Vue.component('reservation-button',{
	"props":["images","lodgement","dates"],"data":function() {
		 return {
			"dialog":false,"reservation":{
				start:'',end:'',lodgement:''
				}
			}
		;
		}
	,"methods":{
		"postReservation":function (){
			this.reservation.start=this.dates[0];
			this.reservation.end=this.dates[1];
			this.reservation.lodgement=this.lodgement;
			let self=this;
			this.
			$http['post']('/rest/reservations/', self.reservation).then(function(response){
				self.reservationModal=false;
				}
			);
			}
		}
	,"template":"   <v-dialog      v-model=\"dialog\"      fullscreen      hide-overlay      >        <template v-slot:activator=\"{ on }\">    <v-btn color=\"red lighten-2\" dark v-on=\"on\"  class=\"mx-auto\"><slot></slot></v-btn>  </template>      <v-card>      <v-container style=\"background:white;\">         <v-btn            icon            @click=\"dialog = false\"            >            <v-icon>mdi-close</v-icon>         </v-btn>         <v-row            class=\"mb-6\"            no-gutters            >            <v-col               cols=\"6\"               >               <h1>Confirmez et payer</h1>               <v-divider></v-divider>            </v-col>         </v-row>         <v-row            class=\"mb-6\"            no-gutters            >            <v-col               cols=\"6\"               >               <H2>Votre voyage:</H2>               <H3>Dates:</H3>                {{dates[0]}} au {{dates[1]}}               <H3>Voyageurs:</H3>               <%nbTravellers%>               <v-divider></v-divider>               <H3>Requis:</H3>               <v-textarea                  rounded                  label=\"Envoyez un message à l'hôte\"                  value=\"Communiquez votre heure d'arrivée et le motif de votre voyage à votre hôte.\"                  outlined                  ></v-textarea>               <v-btn                  color=\"primary\"                  @click=\"postReservation()\"                  >                  Payer               </v-btn>            </v-col>            <v-col               cols=\"5\"               >               <v-card rounded outlined>                  <v-list-item-content>{{lodgement.type}}</v-list-item-content>                  <v-list-item-content><{{lodgement.title}}</v-list-item-content>                  <v-carousel hide-delimiters height=\"200\">                     <v-carousel-item                        v-for=\"(item,i) in images\"                        :key=\"i\"                        :src=\"item.src\"                        ></v-carousel-item>                  </v-carousel>                  <v-divider></v-divider>                  <v-list-item-content>                     <H3>Details du prix</H3>                  </v-list-item-content>                  <v-list-item-content>{{lodgement.price}}</v-list-item-content>                  </v-card>            </v-col>         </v-row>      </v-container>      </v-card>   </v-dialog>"
	}
);