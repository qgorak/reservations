//Script generated with VueComponent at Thu Oct 29 04:29:47 CET 2020
Vue.component('search-results',{
	"props":["result"],"methods":{
		"redirect":function (id){
			var url = new URL(window.location.href);
			let params = new URLSearchParams(url.search.slice(1));
			window.location.href='/lodgement/'+id+'?'+params;
			}
		,"getImages":function (){
			let self = this;
			for(i=0;
			i<this.result.length;
			i++){
				this.
				$http['get']('/rest/image/lodgement/'+this.result[i].id, {
					}
				).then(function(response,i) {
					for(y=0;
					y<self.result.length;
					y++){
						id = response.data.slice(-1)[0];
						if(self.result[y].id == id){
							self.images=[];
							for(k=0;
							k<response.data.length-1;
							k++){
								src={
									src: '/user-photos/'+self.result[y].rent.id+'/lodgement/'+self.result[y].id+'/'+response.data[k]
									}
								;
								self.images.push(src);
								}
							self.
							$set(self.result[y], 'images', self.images)
							}
						}
					}
				)
				}
			}
		}
	,"created":function(){
		this.getImages();
		}
	,"template":"<v-data-iterator               :items=\"result\"               hide-default-footer               >               <v-row                  class=\"mb-6\"                  no-gutters                  >                  <v-card                     v-for=\"i in result\" :key=\"i.id\"                     class=\"mx-auto\"                     elevation=\"0\"                     style=\"margin:0!important;width:100%\"                     align=\"top\"                     >                     <v-list-item two-line>                           <v-col                              cols=\"4\"                              style=\"padding:0;\"                              >                            <v-list-item-content >                              <v-carousel style=\"border-radius:5px;width:300px;\"hide-delimiters height=\"200\" >                                 <v-carousel-item                                    v-for=\"item in i.images\"                                    :src=\"item.src\"                                    ></v-carousel-item>                              </v-carousel>                             </v-list-item-content>                           </v-col>                           <v-col                              cols=\"8\"                              @click=\"redirect(i.id)\"                              style=\"cursor: pointer;\"                                                            >                               <v-list-item-content>                              <v-list-item-title class=\"headline mb-1\">Description de la maison</v-list-item-title>                              <div class=\"overline mb-4\">{{i.type}} à {{i.city}}</div>                              <v-list-item-subtitle>Nbr de voyageurs - Nbr de pièces</v-list-item-subtitle>                              <v-list-item-subtitle>Liste des équipements</v-list-item-subtitle>                           </v-list-item-content>                           </v-col>                     </v-list-item>                     <v-divider></v-divider>                  </v-card>               </v-row>            </v-data-iterator>"
	}
);