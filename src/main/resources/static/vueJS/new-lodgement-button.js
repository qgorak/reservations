//Script generated with VueComponent at Mon Nov 09 21:23:03 CET 2020
Vue.component('new-lodgement-button',{
	"props":["draft"],"data":function() {
		 return {
			"dialog":false,"newLodgement":{
				title:null,nb_place:null,nb_room:null,description:null,price:null,type:null,lat:null,lon:null,status:null
				}
			,"position":false,"type":['Maison','Appartement','Chambre'],"e1":1,"nbr":[1,2,3,4,5]
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
					title:null,nb_place:null,nb_room:null,description:null,price:null,type:null,lat:null,lon:null,status:null
					}
				;
				self.
				$refs.images.images=[];
				self.
				$refs.adress.selected='';
				self.
				$refs.adress.search=[];
				console.log(self.
				$refs.adress.search);
				self.e1=1;
				}
			,function(response) {
				self.dialog=false;
				}
			);
			}
		,"verifySelected":function (){
			setInterval(() => {				
    	if(this.
				$refs.adress.selected!=''){
					this.position=true;
					}
				}
			, 500);
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
				self.e1=6;
				}
			);
			}
		}
	,"template":"   <v-dialog      v-model=\"dialog\"      fullscreen      hide-overlay      >        <template v-slot:activator=\"{ on }\">    <v-btn color=\"primary\" v-on=\"on\"><slot></slot></v-btn>  </template>  <v-card>      <v-card-title class=\"headline\">         Ajoutons ensemble un logement      </v-card-title>      <v-stepper       class=\"elevation-0\"      v-model=\"e1\" >         <v-stepper-header         class=\"elevation-0\">            <v-stepper-step               :complete=\"e1 > 1\"               step=\"1\"               >               Type de logement            </v-stepper-step>            <v-divider></v-divider>            <v-stepper-step               :complete=\"e1 > 2\"               step=\"2\"               >               Description            </v-stepper-step>            <v-divider></v-divider>            <v-stepper-step               :complete=\"e1 > 3\"               step=\"3\"               >               Localisation            </v-stepper-step>            <v-divider></v-divider>            <v-stepper-step                :complete=\"e1 > 4\"               step=\"4\">               Prix            </v-stepper-step>             <v-divider></v-divider>            <v-stepper-step                :complete=\"e1 > 5\"               step=\"5\">               Capacité            </v-stepper-step>            <v-divider></v-divider>            <v-stepper-step step=\"6\">               Photos            </v-stepper-step>         </v-stepper-header>         <v-stepper-items>            <v-stepper-content step=\"1\">               <v-card                  class=\"mb-12 elevation-0\"                  height=\"auto\"                  >                  <v-row justify=\"center\">                     <v-select                        :items=\"type\"                        v-model=\"newLodgement.type\"                        label=\"Type\"                        ></v-select>                  </v-row>               </v-card>               <v-btn                  color=\"primary\"                  @click=\"e1 = 2\"                  class=\"mx-auto\"                  >                  Suivant               </v-btn>               <v-btn  class=\"mx-auto\" @click=\"dialog = false\" text>                  Retour               </v-btn>            </v-stepper-content>            <v-stepper-content step=\"2\">               <v-card                  class=\"mb-12 elevation-0\"                  height=\"400px\"                  >                  <v-row justify=\"center\">                     <v-text-field                        v-model=\"newLodgement.title\"                        :counter=\"40\"                        label=\"Title\"                        required                        ></v-text-field>                  </v-row>                  <v-divider></v-divider>                  <v-row justify=\"center\">                     <v-textarea                        v-model=\"newLodgement.description\"                        label=\"Description\"                        value=\"Parlez nous un peu du logement\"                        hint=\"Hint text\"                        ></v-textarea>                  </v-row>               </v-card>               <v-btn                     v-if=\"(newLodgement.title != null && newLodgement.description != null && newLodgement.title != '' && newLodgement.description != '')\"                          color=\"primary\"                  v-on:click=\"e1 = 3;verifySelected()\"                  >                  Suivant               </v-btn>               <v-btn @click=\"e1 = 1\">                  Retour               </v-btn>            </v-stepper-content>            <v-stepper-content step=\"3\">               <v-card                  class=\"mb-12 elevation-0\"                  height=\"auto\"                  >                  <v-row justify=\"center\">                     <adress-autocomplete ref=\"adress\"></adress-autocomplete>                  </v-row>               </v-card>               <v-btn                     v-if=\"position\"                  color=\"primary\"                  @click=\"e1 = 4\"                  >                  Suivant               </v-btn>               <v-btn @click=\"e1 = 2\">                  Retour               </v-btn>            </v-stepper-content>            <v-stepper-content step=\"4\">               <v-card                  class=\"mb-12 elevation-0\"                  height=\"500px\"                  >                                    <v-row class=\"ma-0 pa-0\">      <v-col cols=\"12\">         <v-card            class=\"mx-auto\"            >            <v-toolbar               flat               dense               >               <v-toolbar-title>                  <span class=\"subheading\">Prix par nuit</span>               </v-toolbar-title>            </v-toolbar>            <v-card-text>               <v-row                  class=\"mb-4\"                  justify=\"space-between\"                  >                  <v-col class=\"text-left\">                     <span                        class=\"display-3 font-weight-light\"                        v-text=\"newLodgement.price\"                        ></span>                     <span class=\"subheading font-weight-light mr-1\">â‚¬ par nuit</span>                  </v-col>               </v-row>               <v-slider                  style=\"max-width:600px;\"                  v-model=\"newLodgement.price\"                  max=\"400\"                  >                  <template v-slot:prepend>                     <v-icon                        >                        mdi-minus                     </v-icon>                  </template>                  <template v-slot:append>                     <v-icon                        >                        mdi-plus                     </v-icon>                  </template>               </v-slider>            </v-card-text>         </v-card>      </v-col>   </v-row>               </v-card>               <v-btn                     :diabled=\"newLodgement.price>0\"                  color=\"primary\"                  @click=\"e1 = 5\"                  >                  Suivant               </v-btn>               <v-btn  @click=\"dialog = false\" text>                  Retour               </v-btn>            </v-stepper-content>            <v-stepper-content step=\"5\">               <v-card                  class=\"mb-12 elevation-0\"                  height=\"auto\"                  >                  <v-row justify=\"center\">                     <v-select                        :items=\"nbr\"                        v-model=\"newLodgement.nbr_room\"                        label=\"Nombre de chambre\"                        ></v-select>                  </v-row>                  <v-row justify=\"center\">                     <v-select                        :items=\"nbr\"                        v-model=\"newLodgement.nbr_place\"                        label=\"nombre de place\"                        ></v-select>                  </v-row>               </v-card>               <v-btn                     v-if=\"(newLodgement.nbr_room != null && newLodgement.nbr_place != null)\"                      color=\"primary\"                  @click=\"postLodgement()\"                  class=\"mx-auto\"                  >                  Suivant               </v-btn>               <v-btn @click=\"e1 = 4\">                  Retour               </v-btn>            </v-stepper-content>            <v-stepper-content step=\"6\">               <v-card                  class=\"mb-12 elevation-0\"                  >            <images-manager ref=\"images\" :lodgement=\"newLodgement\"></images-manager>                           <v-btn                  color=\"primary\"                  @click=\"submitLodgement();dialog=false;\"                  >                  Post               </v-btn>               <v-btn @click=\"e1 = 5\">                  Retour               </v-btn>               </v-card>            </v-stepper-content>         </v-stepper-items>      </v-stepper>   </v-card>   </v-dialog>"
	}
);