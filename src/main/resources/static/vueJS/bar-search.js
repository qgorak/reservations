//Script generated with VueComponent at Wed Nov 18 22:33:17 CET 2020
Vue.component('bar-search',{
	"props":[],"data":function() {
		 return {
			"nb":[1,2,3,4,5],"menuDate":false,"shrinkOnScroll":true,"datesText":"","shrinked":false,"nbTravellers":"null"
			}
		;
		}
	,"methods":{
		"datesTextMethod":function (){
			this.datesText=this.
			$refs.dates.dates.join(' au ');
			}
		,"changeColor":function (){
			if (document.body.scrollTop > 72 ||document.documentElement.scrollTop > 72) {
				this.shrinked = true;
				}
			 else {
				this.shrinked = false;
				}
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
	,"mounted":function(){
		window.onscroll = () => {
			this.changeColor();
			}
		;
		}
	,"template":"<v-app-bar  class=\"appbar\":elevation=\"0\" :extension-height=\"0\" extended :min-height=\"72\"  shrink-on-scroll style=\"font-size:1.25em!important;\"  :color=\"shrinked ? 'white' : 'transparent'\" app >   <v-app-bar-nav-icon :dark=\"shrinked ? false : true\" @click.stop=\"$emit('toggle-drawer')\"></v-app-bar-nav-icon>   <div class=\"v-toolbar-title\"><v-img height=\"32\" width=\"32\" src=\"/images/logo.png\"></v-img></div>   <span v-show=\"shrinked==false\" class=\"v-toolbar-title white--text\" style=\"font-size:1.25em!important;\" onclick=\"window.location.href='/'\">BleuesAndB</span>   <span v-show=\"shrinked==true\" class=\"v-toolbar-title\" style=\"font-size:1.25em!important;\" onclick=\"window.location.href='/'\">BleuesAndB</span>   <v-spacer></v-spacer>   <div style=\"position:absolute;justify-content:center;left: 50%;transform: translate(-50%, 20px);align-self: flex-end;\">      <v-toolbar-items style=\"background-color:white;padding-top:5px;;padding-bottom:8px;border-radius:500px;min-width:750px;\">   <adress-autocomplete style=\"margin-left:8px;max-width:200px;align-self: flex-end;\"ref=\"adress\"></adress-autocomplete>       <v-menu          v-model=\"menuDate\"      :close-on-content-click=\"false\"      transition=\"scale-transition\"      offset-y      max-width=\"290px\"      min-width=\"290px\"      style=\"align-self: flex-end;border-radius:30px;\"      >      <template v-slot:activator=\"{ on, attrs }\">              <v-hover          v-slot=\"{ hover }\"          open-delay=\"200\"        >         <v-text-field             style=\"align-self: flex-end;\"            outlined            rounded            :background-color=\"hover ? '#f7f7f7' : 'white'\"            color=\"white\"            placeholder=\"Ajouter des Dates\"            v-bind:value=\"datesText\"            hide-details            label=\"Regular\"            v-on=\"on\"            ></v-text-field>             </v-hover>      </template>                 <date-picker @dates-change=\"datesTextMethod\" ref=\"dates\"></date-picker>         </v-menu>   <v-select         :height=\"shrinked ? 10 : 12\"      :items=\"nb\"      v-model=\"nbTravellers\"      outlined      placeholder=\"Ajouter des voyageurs\"      hide-details      rounded      label=\"Voyageurs\"      append-icon=\"\"      style=\"align-self: flex-end;\"      ></v-select>   <v-btn        class=\"mx-2\"        depressed        style=\"min-height:58px;margin-top:2px;min-width:125px;border-radius:inherit;\"        fab        small        dark        color=\"primary\"        @click=\"recherche()\"      >      <v-icon>mdi-magnify</v-icon>       rechercher      </v-btn>   </v-toolbar-items>   </div></v-app-bar>"
	}
);