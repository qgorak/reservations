//Script generated with VueComponent at Thu Nov 12 21:03:04 CET 2020
Vue.component('bar-search',{
	"props":[],"data":function() {
		 return {
			"nb":[1,2,3,4,5],"menuDate":false,"datesText":"","nbTravellers":"null"
			}
		;
		}
	,"methods":{
		"datesTextMethod":function (){
			this.datesText=this.
			$refs.dates.dates.join(' au ');
			}
		,"recherche":function (){
			selected = this.
			$refs.adress.selected.geometry.coordinates;			
if(this.
			$refs.dates!=null){				
dates = this.
				$refs.dates.dates;
				}
			else{				
dates=[];
				}			
var url=new URL(document.location.origin+"/lodgement/search");			
url.searchParams.append('lon',selected[0]);			
url.searchParams.append('lat',selected[1]);			

if(this.nbTravellers!="null" && dates.length!=0){				
	url.searchParams.append('start',dates[0]);				
	url.searchParams.append('end',dates[1]);				
	url.searchParams.append('nbr',this.nbTravellers);
				}			
else if(this.nbTravellers!="null"){				
	url.searchParams.append('nbr',this.nbTravellers);
				}			
else if(dates.length!=0){				
	url.searchParams.append('start',dates[0]);				
	url.searchParams.append('end',dates[1]);
				}			
document.location.replace(url);
			}
		}
	,"template":"<v-app-bar color=\"white\" app >   <v-app-bar-nav-icon @click.stop=\"$emit('toggle-drawer')\"></v-app-bar-nav-icon>   <v-toolbar-title class=\"v-toolbar-title\" onclick=\"window.location.href='/'\">AirBnB</v-toolbar-title>   <v-spacer></v-spacer>   <adress-autocomplete ref=\"adress\"></adress-autocomplete>       <v-menu          v-model=\"menuDate\"      :close-on-content-click=\"false\"      transition=\"scale-transition\"      offset-y      max-width=\"290px\"      min-width=\"290px\"      >      <template v-slot:activator=\"{ on, attrs }\">         <v-text-field             label=\"Dates\"            prepend-icon=\"mdi-calendar\"            v-bind:value=\"datesText\"            hide-details            v-on=\"on\"            ></v-text-field>       </template>                 <date-picker @dates-change=\"datesTextMethod\" ref=\"dates\"></date-picker>         </v-menu>   <v-select          :items=\"nb\"      v-model=\"nbTravellers\"      prepend-icon=\"mdi-account\"      dense      hide-details      label=\"Voyageurs\"      ></v-select>   <v-spacer></v-spacer>   <v-btn       @click=\"recherche()\">      <v-icon >mdi-magnify</v-icon>   </v-btn></v-app-bar>"
	}
);