//Script generated with VueComponent at Mon Nov 09 20:09:37 CET 2020
Vue.component('images-manager',{
	"props":["lodgement"],"data":function() {
		 return {
			"images":[],"file":null
			}
		;
		}
	,"methods":{
		"postImage":function (){
			let self=this;
			let formData = new FormData();
			formData.append('file', this.file);
					this.
			$http['post']("/rest/image/saveLodgementPhoto/"+this.lodgement.id, formData, {				
     headers: {					
        "Content-Type": "multipart/form-data"
      
					}
				}
			).then(function(response, i) {
				self.images.push({
					src: '/user-photos/'+self.lodgement.rent.id+'/lodgement/'+self.lodgement.id+'/'+response.data, name: response.data
					}
				);
				self.file=null;
				}
			)
			}
		,"deleteImage":function (item){
			let self=this;
			this.
			$http['delete']('/rest/image/deleteLodgementPhoto/'+this.lodgement.id+'/'+item.name+'/', {
				}
			).then(function(response){
				for(k=0;
				k<self.images.length;
				k++){
					if(self.images[k].name==response.data){
						self.images.splice(k,1);
						}
					}
				}
			);
			}
		,"getImages":function (){
			self = this;
			this.
			$http['get']('/rest/image/lodgement/'+self.lodgement.id, {
				}
			).then(function(response) {
				for(k=0;
				k<response.data.length-1;
				k++){
					image={
						name:response.data[k],src: '/user-photos/'+self.lodgement.rent.id+'/lodgement/'+self.lodgement.id+'/'+response.data[k]
						}
					;
					self.images.push(image);
					}
				}
			)
			}
		}
	,"beforeMount":function(){
		if(this.lodgement.id!=null){
			this.getImages();
			}
		}
	,"template":"                  <v-data-iterator                       style=\"width:100%;\"                     :items=\"images\"                     hide-default-footer                     >                     <template v-slot:header>                        <v-toolbar                           color=\"primary\"                           class=\"mb-2 white--text\"                           dense                           >                           <v-toolbar-title>Photos de l'annonce</v-toolbar-title>                        </v-toolbar>                     </template>                     <template v-slot:default=\"props\">                        <v-row class=\"ma-0 pa-0\">                           <v-col                              v-for=\"item in props.items\"                              :key=\"item.name\"                              cols=\"12\"                              sm=\"6\"                              md=\"4\"                              lg=\"3\"                              >                              <v-card>                                 <v-img                                    max-height=\"auto\"                                    max-width=\"auto\"                                    :src=item.src                                    ></v-img>                                 <v-divider></v-divider>                                 <v-list dense>                                    <v-list-item>                                       {{item.name}}                                    </v-list-item>                                    <v-list-item>                                       <v-list-item-content class=\"align-end\">                                          <v-btn  small @click=\"deleteImage(item)\" depressed>Supprimer</v-btn>                                       </v-list-item-content>                                    </v-list-item>                                 </v-list>                              </v-card>                           </v-col>                        </v-row>                     </template>                     <template v-slot:footer>                           <v-file-input                              v-model=\"file\"                              accept=\"image/*\"                              class=\"mx-2\"                              label=\"Ajouter une image\"                              ></v-file-input>                           <v-btn v-if=\"file != null\" @click=\"postImage()\">Ajouter</v-btn>                     </template>                  </v-data-iterator>"
	}
);