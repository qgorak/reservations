//Script generated with VueComponent at Tue Nov 17 14:54:41 CET 2020
Vue.component('alert',{
	"props":[],"data":function() {
		 return {
			"type":"success","message":"","showAlert":false
			}
		;
		}
	,"methods":{
		"triggerAlert":function (type,message){
			this.message=message;
			this.type=type;
			this.showAlert=true;
			setTimeout(() => this.showAlert = false, 2500);
			}
		}
	,"template":"<v-alert :type=\"type\"        transition=\"slide-y-transition\"        v-show=\"showAlert\">{{message}}        </v-alert>"
	}
);