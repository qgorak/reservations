//Script generated with VueComponent at Mon Nov 02 16:22:33 CET 2020
Vue.component('auth-manager',{
	"props":["user"],"methods":{
		"isUserLogged":function (){
			var myCookie = this.getCookie('user');
			if(myCookie!==''){
				return true;
				}
			return false;
			}
		,"getCookie":function (cname){
			var name = cname + '=';
			var decodedCookie = decodeURIComponent(document.cookie);
			var ca = decodedCookie.split(';');
			for(var i = 0;
			 i <ca.length;
			 i++) {
				  var c = ca[i];
				  while (c.charAt(0) == ' ') {
					    c = c.substring(1);
					}
				 if (c.indexOf(name) == 0) {
					   return c.substring(name.length, c.length);
					}
				}
			return'';
			}
		}
	,"beforeMount":function(){
		if(this.isUserLogged()){
			var myCookie = this.getCookie('user');
			var obj = JSON.parse(myCookie);
			this.
			$root.user=obj;
			}
		}
	,"template":"<div></div>"
	}
);