//Script generated with VueComponent at Wed Oct 28 13:40:28 CET 2020
Vue.component('adress-autocomplete',{
	"props":[],"data":function() {
		 return {
			"search":[],"destination":{
				name:null,loc:null
				}
			,"error":false,"selected":""
			}
		;
		}
	,"methods":{
		"suggestion":function (){
			if(event.target.value.length!=0){				
	var items=event.target.value.split(' ');				
	let url = "https://api-adresse.data.gouv.fr/search/?q="+items.join("+")+"&limit=8";				
	fetch(url)
	.then(res => res.json())
	.then((out) => {					
	this.search=out.features;
					}
				)

				}			
else{				
	this.destination={
					name:null,loc:null
					}
				;				
	this.search=[];
				}
			}
		}
	,"template":" <v-autocomplete      v-model=\"selected\"      :items=\"search\"      v-on:keyup=\"suggestion\"      label=\"Select\"      no-filter      hide-details      return-object      item-text=\"properties.name\"      item-value=\"properties.id\"      >      <template v-slot:selection=\"data\">         <v-chip            v-bind=\"data.attrs\"            close            @click=\"data.select\"            @click:close=\"remove(data.item)\"            >            {{ data.item.properties.name }}         </v-chip>      </template>      <template v-slot:item=\"data\">         <v-list-item-content>            <v-list-item-title v-html=\"data.item.properties.name\"></v-list-item-title>            <v-list-item-subtitle v-if=\"data.item.properties.label!=data.item.properties.city\" v-html=\"data.item.properties.city\"></v-list-item-subtitle>            <v-list-item-subtitle v-html=\"data.item.properties.postcode\"></v-list-item-subtitle>         </v-list-item-content>      </template>      </template>   </v-autocomplete>"
	}
);