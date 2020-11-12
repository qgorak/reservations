//Script generated with VueComponent at Thu Nov 12 20:40:45 CET 2020
Vue.component('search-results',{
	"props":["result","start","end","nbr"],"data":function() {
		 return {
			"background":{
				}
			,"markers":{
				}
			,"map":null
			}
		;
		}
	,"methods":{
		"redirect":function (id){
			if(this.nbr!=='null' && this.start!=='null' && this.end!=='null'){
				window.location.href='/lodgement/'+id+'?start='+this.start+'&end='+this.end+'&nbr='+this.nbr;
				}			
else if(this.nbr!=='null'){
				window.location.href='/lodgement/'+id+'?nbr='+this.nbr;
				}
			else if(this.start!=='null' && this.end!=='null'){
				window.location.href='/lodgement/'+id+'?start='+this.start+'&end='+this.end;
				}
			else{
				window.location.href='/lodgement/'+id
				}
			}
		,"showPopup":function (i){
			let self=this;
			self.markers[i.id].openPopup();
			}
		,"closePopup":function (i){
			let self=this;
			self.markers[i.id].closePopup().addTo(self.map);
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
									src: '/user-photos/'+self.
									$root.result[y].rent.id+'/lodgement/'+self.
									$root.result[y].id+'/'+response.data[k]
									}
								;
								self.images.push(src);
								}
							self.
							$set(self.
							$root.result[y], 'images', self.images)
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
	,"beforeMount":function(){
		let self=this;
				var element = document.getElementById('osm-map');
				self.map = L.map(element);
				L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png').addTo(self.map);
				var params=new URLSearchParams(window.location.search);
				var center = L.latLng(params.get('lat'),params.get('lon'));
				self.map.setView(center, 13);
				for(i=0;
		i<self.result.length;
		i++){
						var text=self.result[i].title.toString()+'<br>prix: '+self.result[i].price.toString()+'€<br>Nombre de pièces: '+self.result[i].nbr_room+'<br>'+self.result[i].nbr_place+' Personne(s)<br>Description:<br>'+self.result[i].description+'<br><a href=/lodgement/'+self.result[i].id+'?start='+this.end+'&end='+this.start+'&nbr='+this.nbr+'>voir l\'annonce</a>';			center=L.latLng(self.result[i].lat,self.result[i].lon);						var marker=L.marker(center);						self.markers[self.result[i].id]=marker;						self.background[self.result[i].id]='white';
						marker.bindPopup(text).addTo(self.map);
			}
		}
	,"template":"<v-data-iterator               :items=\"result\"               hide-default-footer>   <v-row                     class=\"mb-6\"                     no-gutters>      <v-card                           v-for=\"i in result\" c:key=\"i.id\"                           class=\"mx-auto\"                           elevation=\"0\"                           style=\"margin:0!important;width:100%\"                      align=\"top\"                        @mouseenter.native=\"showPopup(i)\"                           @mouseleave.native=\"closePopup(i)\">                           <v-list-item two-line>         <v-col                                       cols=\"4\"                                       style=\"padding:0;\">            <v-list-item-content >               <v-carousel               style=\"border-radius:5px;width:300px;\"               hide-delimiters height=\"200\">                  <v-carousel-item                                                      v-for=\"item in i.images\"                                                      :src=\"item.src\"></v-carousel-item>               </v-carousel>            </v-list-item-content>         </v-col>         <v-col                                       cols=\"8\"                               @click=\"redirect(i.id)\"                                       style=\"cursor: pointer;\">                                        <v-list-item-content>            <v-list-item-title class=\"headline mb-1\">{{i.title}}</v-list-item-title>            <div class=\"overline mb-4\">{{i.type}}</div>            <v-list-item-subtitle>{{i.nbr_place}} personnes - {{i.nbr_room}} pièces</v-list-item-subtitle>         </v-list-item-content>         </v-col>                           </v-list-item>      <v-divider></v-divider>      </v-card>                  </v-row></v-data-iterator>"
	}
);