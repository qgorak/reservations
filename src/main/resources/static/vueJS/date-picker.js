//Script generated with VueComponent at Thu Nov 12 21:51:55 CET 2020
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
		var end = urlParams.get('end');
		if(start!=null && end!=null){
			this.dates[0]=start;
			this.dates[1]=end;
			console.log(this.dates);
			this.
			$emit('dates-change');
			}
		}
	,"template":"      <v-date-picker          v-model=\"dates\"         range         :min=\"datemin\"         no-title         locale=\"fr-FR\"         ></v-date-picker>"
	}
);