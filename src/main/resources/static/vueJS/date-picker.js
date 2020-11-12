//Script generated with VueComponent at Thu Nov 12 09:31:45 CET 2020
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
		var urlParams = new URLSearchParams(window.location.search);
		var start = urlParams.get('start');
		this.dates[0]=start;
		var end = urlParams.get('end');
		this.dates[1]=end;
		}
	,"template":"      <v-date-picker          v-model=\"dates\"         range         :min=\"datemin\"         no-title         locale=\"fr-FR\"         ></v-date-picker>"
	}
);