//Script generated with VueComponent at Thu Nov 12 11:53:03 CET 2020
Vue.component('showcase-destination',{
	"props":[],"data":function() {
		 return {
			"model":null
			}
		;
		}
	,"methods":{
		"onCardClick":function (n){
			if(this.model!==n-1){
				this.model = n - 1;
				}
			else{
				alert('ok')
				}
			}
		}
	,"template":"<v-slide-group v-model=\"model\"   class=\"pa-4\"   center-active   show-arrows   >   <v-slide-item      v-for=\"n in 15\"      :key=\"n\"      v-slot=\"{ active, toggle }\"      >      <v-card         :color=\"active ? 'primary' : 'grey lighten-1'\"         class=\"ma-4\"         :height=\"active ? '440' : '400'\"         :width=\"300\"         @click=\"onCardClick(n)\"         >                  <v-img            lazy-src=\"https://picsum.photos/id/11/10/6\"            max-height=\"440\"            max-width=\"300\"            src=\"https://picsum.photos/id/11/500/300\"            ></v-img>         <v-card-title>Test</v-card-title>         <v-scale-transition>            <div>                <v-card-actions>                  <v-btn depressed tile width=\"300\" v-if=\"active\">                     Explorer cette destination                       <v-icon                        right                        dark                        >                        mdi-share                     </v-icon>                  </v-btn>                  </v-card-actions>            </div>         </v-scale-transition>      </v-card>   </v-slide-item></v-slide-group>"
	}
);