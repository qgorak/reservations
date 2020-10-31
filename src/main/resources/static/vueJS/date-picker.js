//Script generated with VueComponent at Sat Oct 31 20:43:57 CET 2020
Vue.component('date-picker',{
	"props":[],"data":function() {
		 return {
			"date":"","datemin":"","endDate":"2020-10-30","allowedDates":['2020-10-08', '2020-10-09'],"dates":[],"dateFormatted":this.formatDate(new Date().toISOString().substr(0, 10)),"menu1":false
			}
		;
		}
	,"methods":{
		"formatDate":function (date){
			if (!date) return null;			
const [year, month, day] = date.split('-');			
return `
			${
				month
				}
			/
			${
				day
				}
			/
			${
				year
				}
			`;
			}
		,"getAllowedDates":function (val){
			for (var i = 0;
			 i < this.allowedDates.length;
			 i++){				
	if (this.allowedDates[i] == val){
					return val;
					}
				}
			}
		,"parseDate":function (date){
			if (!date) return null
const [month, day, year] = date.split('/')
`
			${
				year
				}
			-
			${
				month.padStart(2, '0')
				}
			-
			${
				day.padStart(2, '0')
				}
			`
			}
		,"addDays":function (days){
			var date = new Date(this.valueOf());			
date.setDate(date.getDate() + days);			
return date;
			}
		}
	,"computed":{
		"dateRangeText":function(){
			return this.dates.join(' au ')
			}
		}
	,"beforeMount":function(){
		this.datemin=new Date().toLocaleDateString('fr-CA');
		}
	,"template":"      <v-date-picker          v-model=\"dates\"         range         :min=\"datemin\"         no-title         ></v-date-picker>"
	}
);