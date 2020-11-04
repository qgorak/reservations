//Script generated with VueComponent at Wed Nov 04 12:08:00 CET 2020
Vue.component('bar-search',{
	"props":[],"data":function() {
		 return {
			"nb":[1,2,3,4,5],"menuDate":false,"nbTravellers":"null"
			}
		;
		}
	,"methods":{
		"recherche":function (){
			selected = this.
			$refs.adress.selected.geometry.coordinates.join('&lat=');			
if(this.
			$refs.dats!=null){				
dates = this.
				$refs.dates.dates;
				}
			else{				
dates=[];
				}			
if(this.nbTravellers!="null" && dates.length!=0){				
window.location.replace(window.location.host+'/lodgement/search?lon='+selected+'&start='+dates[0]+'&end='+dates[1]+'&nbr='+this.nbTravellers);
				}			
else if(this.nbTravellers!="null"){				
window.location.replace(window.location.host+'/lodgement/search?lon='+selected+'&nbr='+this.nbTravellers);
				}			
else if(dates.length!=0){				
window.location.replace(window.location.host+'/lodgement/search?lon='+selected+'&start='+dates[0]+'&end='+dates[1]);
				}			
else{				
window.location.replace('/lodgement/search?lon='+selected);
				}
			}
		}
	,"template":"<v-app-bar color=\"white\" app >   <v-app-bar-nav-icon @click.stop=\"$emit('toggle-drawer')\"></v-app-bar-nav-icon>   <v-toolbar-title class=\"v-toolbar-title\" onclick=\"window.location.href='/'\">AirBnB</v-toolbar-title>   <v-spacer></v-spacer>   <adress-autocomplete ref=\"adress\"></adress-autocomplete>       <v-menu          v-model=\"menuDate\"      :close-on-content-click=\"false\"      transition=\"scale-transition\"      offset-y      max-width=\"290px\"      min-width=\"290px\"      >      <template v-slot:activator=\"{ on, attrs }\">         <v-text-field             label=\"Dates\"            prepend-icon=\"mdi-calendar\"            v-bind=\"attrs\"            hide-details            v-on=\"on\"            ></v-text-field>       </template>                 <date-picker ref=\"dates\"></date-picker>         </v-menu>   <v-select          :items=\"nb\"      v-model=\"nbTravellers\"      prepend-icon=\"mdi-account\"      dense      hide-details      label=\"Voyageurs\"      ></v-select>   <v-spacer></v-spacer>   <v-btn       @click=\"recherche()\">      <v-icon >mdi-magnify</v-icon>   </v-btn></v-app-bar>"
	}
);