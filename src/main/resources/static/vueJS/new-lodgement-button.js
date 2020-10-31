//Script generated with VueComponent at Sat Oct 31 19:40:44 CET 2020
Vue.component('new-lodgement-button',{
	"props":["draft"],"data":function() {
		 return {
			"dialog":false,"newLodgement":{
				title:null,nb_place:null,nb_room:null,descrisption:null,price:null,type:null,lat:null,lon:null,status:null
				}
			,"type":['Maison','Appartement','Chambre'],"e1":1,"nbr":[1,2,3,4,5]
			}
		;
		}
	,"methods":{
		"submitLodgement":function (){
			let self=this;
			self.newLodgement.status='ONLINE';
			this.
			$http['patch']('/rest/lodgements/'+self.newLodgement.id, self.newLodgement).then(function(response){
				let lodgement=response.data;
				self.
				$set(lodgement, 'images', self.
				$refs.images.images);
				self.
				$emit('addtolist', lodgement);
				self.newLodgement={
					title:null,nb_place:null,nb_room:null,descrisption:null,price:null,type:null,lat:null,lon:null,status:null
					}
				;
				self.
				$refs.images.images=[];
				self.
				$refs.adress.selected='';
				self.e1=1;
				}
			,function(response) {
				alert('votre annonce est maintenant en ligne');
				}
			);
			}
		,"postLodgement":function (){
			let self=this;
			this.newLodgement.status='DRAFT',this.newLodgement.lat=this.
			$refs.adress.selected.geometry.coordinates[1];
			this.newLodgement.lon=this.
			$refs.adress.selected.geometry.coordinates[0];
			this.
			$http['post']('/rest/lodgements/', self.newLodgement).then(function(response){
				self.newLodgement = response.data;
				self.e1=5;
				}
			);
			}
		}
	,"template":"   <v-dialog      v-model=\"dialog\"      fullscreen      hide-overlay      >        <template v-slot:activator=\"{ on }\">    <v-btn color=\"red lighten-2\" dark v-on=\"on\"><slot></slot></v-btn>  </template>  <v-card>      <v-card-title class=\"headline\">         Ajoutons ensemble un logement      </v-card-title>      <v-stepper v-model=\"e1\" >         <v-stepper-header>            <v-stepper-step               :complete=\"e1 > 1\"               step=\"1\"               >               Type de logement            </v-stepper-step>            <v-divider></v-divider>            <v-stepper-step               :complete=\"e1 > 2\"               step=\"2\"               >               Description            </v-stepper-step>            <v-divider></v-divider>            <v-stepper-step               :complete=\"e1 > 3\"               step=\"3\"               >               localisation            </v-stepper-step>            <v-divider></v-divider>            <v-stepper-step                :complete=\"e1 > 4\"               step=\"4\">               Capacité            </v-stepper-step>            <v-divider></v-divider>            <v-stepper-step step=\"5\">               Photos            </v-stepper-step>         </v-stepper-header>         <v-stepper-items>            <v-stepper-content step=\"1\">               <v-card                  class=\"mb-12\"                  color=\"grey lighten-1\"                  height=\"200px\"                  >                  <v-row justify=\"center\">                     <v-select                        :items=\"type\"                        v-model=\"newLodgement.type\"                        label=\"Type\"                        ></v-select>                  </v-row>               </v-card>               <v-btn                  color=\"primary\"                  @click=\"e1 = 2\"                  >                  Continue               </v-btn>               <v-btn  @click=\"dialog = false\" text>                  Cancel               </v-btn>            </v-stepper-content>            <v-stepper-content step=\"2\">               <v-card                  class=\"mb-12\"                  color=\"grey lighten-1\"                  height=\"400px\"                  >                  <v-row justify=\"center\">                     <v-text-field                        v-model=\"newLodgement.title\"                        :counter=\"10\"                        label=\"Title\"                        required                        ></v-text-field>                  </v-row>                  <v-divider></v-divider>                  <v-row justify=\"center\">                     <v-textarea                        v-model=\"newLodgement.description\"                        label=\"Description\"                        value=\"Parlez nous un peu du logement\"                        hint=\"Hint text\"                        ></v-textarea>                  </v-row>               </v-card>               <v-btn                  color=\"primary\"                  @click=\"e1 = 3\"                  >                  Continue               </v-btn>               <v-btn text>                  Cancel               </v-btn>            </v-stepper-content>            <v-stepper-content step=\"3\">               <v-card                  class=\"mb-12\"                  color=\"grey lighten-1\"                  height=\"200px\"                  >                  <v-row justify=\"center\">                     <adress-autocomplete ref=\"adress\"></adress-autocomplete>                  </v-row>               </v-card>               <v-btn                  color=\"primary\"                  @click=\"e1 = 4\"                  >                  Continue               </v-btn>               <v-btn text>                  Cancel               </v-btn>            </v-stepper-content>            <v-stepper-content step=\"4\">               <v-card                  class=\"mb-12\"                  color=\"grey lighten-1\"                  height=\"200px\"                  >                  <v-row justify=\"center\">                     <v-select                        :items=\"nbr\"                        v-model=\"newLodgement.nbr_room\"                        label=\"Nombre de chambre\"                        ></v-select>                  </v-row>                  <v-row justify=\"center\">                     <v-select                        :items=\"nbr\"                        v-model=\"newLodgement.nbr_place\"                        label=\"nombre de place\"                        ></v-select>                  </v-row>               </v-card>               <v-btn                  color=\"primary\"                  @click=\"postLodgement()\"                  >                  Suivant               </v-btn>               <v-btn text>                  Cancel               </v-btn>            </v-stepper-content>            <v-stepper-content step=\"5\">               <v-card                  class=\"mb-12\"                  color=\"grey lighten-1\"                  >            <images-manager ref=\"images\" :lodgement=\"newLodgement\"></images-manager>                           <v-btn                  color=\"primary\"                  @click=\"submitLodgement();dialog=false;\"                  >                  Post               </v-btn>               </v-card>            </v-stepper-content>         </v-stepper-items>      </v-stepper>   </v-card>   </v-dialog>"
	}
);