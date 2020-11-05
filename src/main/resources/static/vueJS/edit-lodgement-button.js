//Script generated with VueComponent at Thu Nov 05 08:51:33 CET 2020
Vue.component('edit-lodgement-button',{
	"props":["lodgement"],"data":function() {
		 return {
			"dialog":false
			}
		;
		}
	,"methods":{
		"updateLodgement":function (lodgement){
			let self=this;
			this.
			$http['patch']('/rest/lodgements/'+self.lodgement.id, self.lodgement).then(function(response){
				alert(self.lodgement.title+' a bien été mis a jour');
				}
			);
			}
		}
	,"template":"   <v-dialog      v-model=\"dialog\"      fullscreen      hide-overlay      transition=\"dialog-bottom-transition\"      >        <template v-slot:activator=\"{ on }\">    <v-btn color=\"red lighten-2\" dark v-on=\"on\"><slot></slot></v-btn>  </template><v-card>         <v-btn            icon            @click=\"dialog = false\"            >            <v-icon>mdi-close</v-icon>         </v-btn>   <v-row class=\"ma-0 pa-0\">      <v-col cols=\"12\">         <v-card            class=\"mx-auto\"            >            <v-toolbar               flat               dense               >               <v-toolbar-title>                  <span class=\"subheading\">Type</span>               </v-toolbar-title>            </v-toolbar>            <v-card-text>               {{lodgement.type}}            </v-card-text>            <v-toolbar               flat               dense               >               <v-toolbar-title>                  <span class=\"subheading\">Caractéristiques</span>               </v-toolbar-title>            </v-toolbar>            <v-card-text>               {{lodgement.nbr_place}} voyageurs, {{lodgement.nbr_room}} pièce(s)            </v-card-text>         </v-card>      </v-col>   </v-row>   <v-row class=\"ma-0 pa-0\">      <v-col cols=\"12\">         <v-card            class=\"mx-auto\"            >            <v-toolbar               flat               dense               >               <v-toolbar-title>                  <span class=\"subheading\">Titre de l'annonce</span>               </v-toolbar-title>            </v-toolbar>            <v-card-text>               <v-text-field                  v-model=\"lodgement.title\"                  style=\"max-width:600px;\"                  solo                  ></v-text-field>            </v-card-text>         </v-card>      </v-col>   </v-row>   <v-row class=\"ma-0 pa-0\">      <v-col cols=\"12\">         <v-card            class=\"mx-auto\"            >            <v-toolbar               flat               dense               >               <v-toolbar-title>                  <span class=\"subheading\">Prix par nuit</span>               </v-toolbar-title>            </v-toolbar>            <v-card-text>               <v-row                  class=\"mb-4\"                  justify=\"space-between\"                  >                  <v-col class=\"text-left\">                     <span                        class=\"display-3 font-weight-light\"                        v-text=\"lodgement.price\"                        ></span>                     <span class=\"subheading font-weight-light mr-1\">€ par nuit</span>                  </v-col>               </v-row>               <v-slider                  style=\"max-width:600px;\"                  v-if=\"lodgement.price!=null\"                  v-model=\"lodgement.price\"                  max=\"400\"                  >                  <template v-slot:prepend>                     <v-icon                        >                        mdi-minus                     </v-icon>                  </template>                  <template v-slot:append>                     <v-icon                        >                        mdi-plus                     </v-icon>                  </template>               </v-slider>            </v-card-text>         </v-card>      </v-col>   </v-row>   <v-spacer></v-spacer>   <v-row class=\"ma-0 pa-0\">      <v-col cols=\"12\">         <v-card            class=\"mx-auto\"            >            <v-toolbar               flat               >               <v-toolbar-title>                  <span class=\"subheading\">Description du logement</span>               </v-toolbar-title>            </v-toolbar>            <v-card-text>               <v-textarea                  solo                  label=\"Description du logement\"                  style=\"max-width:600px;\"                  no-resize                  v-model=\"lodgement.description\"                  ></v-textarea>            </v-card-text>            <v-divider></v-divider>            <v-card-text>               <v-btn  color=\"green\" @click=\"updateLodgement(lodgement)\" >Mettre à jour l'annonce</v-btn>            </v-card-text>         </v-card>      </v-col>   </v-row>   <v-row class=\"ma-0 pa-0\">      <images-manager v-if=\"lodgement!==null\" :lodgement=\"lodgement\"></images-manager>   <v-btn v-if=\"lodgement.status=='ONLINE'\" color=\"green\" @click=\"lodgement.status='OFFLINE';updateLodgement(lodgement)\" >Desactiver l'annonce</v-btn>   <v-btn v-if=\"lodgement.status=='OFFLINE'\" color=\"green\" @click=\"lodgement.status='ONLINE';updateLodgement(lodgement)\" >Mettre l'annonce en ligne</v-btn>   </v-row></v-card>   </v-dialog>"
	}
);