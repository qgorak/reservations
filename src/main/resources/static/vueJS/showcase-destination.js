//Script generated with VueComponent at Mon Nov 16 14:10:00 CET 2020
Vue.component('showcase-destination',{
	"props":[],"data":function() {
		 return {
			"destinations":[],"model":null
			}
		;
		}
	,"methods":{
		"onCardClick":function (n,item){
			if(this.model!==n-1){
				this.model = n - 1;
				}
			else{
				window.location.replace('/lodgement/search?lon='+item.lon+'&lat='+item.lat);
				}
			}
		}
	,"beforeMount":function(){
		let self = this;
		this.
		$http['get']('/rest/destinations', {
			}
		).then(function(response){
			self.destinations = response.data;
			}
		);
		}
	,"template":"<v-slide-group style=\"padding:0!important\" v-model=\"model\"   class=\"pa-4\"   center-active   show-arrows   >   <v-slide-item      v-for=\"n in destinations.length\"      :key=\"n\"      v-slot=\"{ active, toggle }\"      >      <v-card         :color=\"active ? 'blue lighten-1' : 'white'\"         :elevation=\"active ?1 : 3\"         :dark=\"active ?true : false\"         class=\"ma-4\"         :height=\"400\"         :width=\"300\"         @click=\"onCardClick(n,destinations[n-1])\"         >         <v-img           :src=\"destinations[n-1].pathPhoto\"            height=\"250\"            max-width=\"300\"            ></v-img>         <v-card-title>{{destinations[n-1].label}}</v-card-title>         <v-card-text>         {{destinations[n-1].desc}}         </v-card-text>      </v-card>   </v-slide-item></v-slide-group>"
	}
);