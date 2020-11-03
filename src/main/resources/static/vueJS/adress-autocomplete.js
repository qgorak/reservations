//Script generated with VueComponent at Tue Nov 03 18:48:10 CET 2020
Vue.component('adress-autocomplete',{
	"props":[],"data":function() {
		 return {
			"search":[],"destination":{
				name:null,loc:null
				}
			,"error":false,"selected":"","timeOut":null
			}
		;
		}
	,"methods":{
		"suggestion":function (){
			let self=this;			
if(event.target.value.length!=0){				
	if(((event.keyCode >=65 && event.keyCode <= 90) || (event.keyCode >=48 && event.keyCode <= 57) || event.keyCode === 8 || event.keyCode === 27 || event.keyCode === 46 || event.keyCode === 32 )){
								
		var items=event.target.value.split(' ');
									
		let url = "https://api-adresse.data.gouv.fr/search/?q="+items.join("+")+"&limit=8";
						
		clearTimeout(self.timeOut);					
		this.timeOut =setTimeout(function(){						
			fetch(url,{
							}
						).then(res => res.json()).then((out) => {
							self.search=out.features;
							}
						);
						}
					, 500);
					}
				}			
else{				
	this.destination={
					name:null,loc:null
					}
				;				
	this.search=[];
				}
			}
		,"remove":function (){
			this.destination={
				name:null,loc:null
				}
			;
						this.search=[];
						this.selected='';
			}
		}
	,"template":" <v-autocomplete      v-model=\"selected\"      :items=\"search\"      v-on:keyup=\"suggestion\"      label=\"Select\"      no-filter      hide-details      return-object      item-text=\"properties.name\"      item-value=\"properties.id\"      >      <template v-slot:selection=\"data\">         <v-chip            v-bind=\"data.attrs\"            close            @click=\"data.select\"            @click:close=\"remove(data.item)\"            >            {{ data.item.properties.name }}         </v-chip>      </template>      <template v-slot:item=\"data\">         <v-list-item-content>            <v-list-item-title v-html=\"data.item.properties.name\"></v-list-item-title>            <v-list-item-subtitle v-if=\"data.item.properties.label!=data.item.properties.city\" v-html=\"data.item.properties.city\"></v-list-item-subtitle>            <v-list-item-subtitle v-html=\"data.item.properties.postcode\"></v-list-item-subtitle>         </v-list-item-content>      </template>      </template>   </v-autocomplete>"
	}
);