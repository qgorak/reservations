//Script generated with VueComponent at Mon Nov 09 18:54:24 CET 2020
Vue.component('lodgement-list',{
	"props":["title","lodgements"],"methods":{
		"redirectEdit":function (item){
			window.location.pathname = "/lodgement/"+item.id+"/edit";
			}
		,"getImages":function (){
			let self = this;
			for(i=0;
			i<this.lodgements.length;
			i++){
				this.
				$http['get']('/rest/image/lodgement/'+this.lodgements[i].id, {
					}
				).then(function(response,i) {
					for(y=0;
					y<self.lodgements.length;
					y++){
						id = response.data.slice(-1)[0];
						if(self.lodgements[y].id == id){
							self.images=[];
							for(k=0;
							k<response.data.length-1;
							k++){
								src={
									src: '/user-photos/'+self.lodgements[y].rent.id+'/lodgement/'+self.lodgements[y].id+'/'+response.data[k]
									}
								;
								self.images.push(src);
								}
							self.
							$set(self.lodgements[y], 'images', self.images)
							}
						}
					}
				)
				}
			}
		,"redirectView":function (item){
			window.location.pathname = "/lodgement/"+item.id;
			}
		}
	,"beforeMount":function(){
		this.getImages();
		}
	,"template":"<v-data-iterator      :items=\"lodgements\"      hide-default-footer      >      <template v-slot:header>         <v-toolbar            class=\"mb-2\"            color=\"primary\"            dark            flat            >            <v-toolbar-title>{{title}}</v-toolbar-title>         </v-toolbar>      </template>      <template v-slot:default=\"props\">         <v-row>            <v-col               v-for=\"item in props.items\"               :key=\"item.name\"               cols=\"12\"               sm=\"6\"               md=\"4\"               lg=\"3\"               >               <v-card>                  <v-card-title class=\"subheading font-weight-bold\">                     {{ item.title }}                  </v-card-title>                  <v-divider></v-divider>                  <v-list dense>                     <v-list-item>                        <v-list-item-content>Type:</v-list-item-content>                        <v-list-item-content class=\"align-end\">                           {{ item.type }}                        </v-list-item-content>                     </v-list-item>                     <v-list-item>                        <v-list-item-content>Description:</v-list-item-content>                        <v-list-item-content class=\"align-end\">                           {{ item.description }}                        </v-list-item-content>                     </v-list-item>                     <v-list-item>                        <v-carousel hide-delimiters height=\"200\" width=\"auto\">                           <v-carousel-item                              v-for=\"item in item.images\"                              :src=\"item.src\"                              ></v-carousel-item>                        </v-carousel>                     </v-list-item>                     <v-list-item>                        <v-list-item-content>Prix</v-list-item-content>                        <v-list-item-content class=\"align-end\">                           {{ item.price }} €                        </v-list-item-content>                     </v-list-item>                     <v-list-item>                        <v-list-item-content>                           <v-btn  small @click=\"redirectView(item)\" depressed>Voir Annonce</v-btn>                        </v-list-item-content>                        <v-list-item-content class=\"align-end\">                           <edit-lodgement-button :lodgement=\"item\">Editer</edit-lodgement-button></v-list-item-content>                        </v-list-item-content>                     </v-list-item>                  </v-list>               </v-card>            </v-col>         </v-row>      </template>   </v-data-iterator>"
	}
);