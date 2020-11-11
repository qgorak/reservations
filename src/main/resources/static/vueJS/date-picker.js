//Script generated with VueComponent at Wed Nov 11 17:24:53 CET 2020
Vue.component('date-picker',{
	"props":[],"data":function() {
		 return {
			"date":"","datemin":"","endDate":"2020-10-30","dates":[],"menu1":false
			}
		;
		}
	,"watch":{
		"dates":function (val,oldVal){
			if(this.dates.length==2){
				this.
				$emit('dates-change');
				}
			}
		}
	,"beforeMount":function(){
		this.datemin=new Date().toLocaleDateString('fr-CA');
		}
	,"template":"      <v-date-picker          v-model=\"dates\"         range         :min=\"datemin\"         no-title         locale=\"fr-FR\"         ></v-date-picker>"
	}
);