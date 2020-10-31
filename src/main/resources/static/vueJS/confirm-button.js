//Script generated with VueComponent at Thu Oct 29 14:30:25 CET 2020
Vue.component('confirm-button',{
	"props":["lodgements"],"methods":{
		"redirectEdit":function (item){
			window.location.href = "http://127.0.0.1:8080/lodgement/"+item.id+"/edit";
			}
		,"getImages":function (){
			let self = this;
			for(i=0;
			i<this.lodgements.length;
			i++){
				this.
				$http['get']('/rest/image/lodgement/'+this.lodgements[i].id, {
					}
				).then(function(response,i) {
					for(y=0;
					y<self.lodgements.length;
					y++){
						id = response.data.slice(-1)[0];
						if(self.lodgements[y].id == id){
							self.images=[];
							for(k=0;
							k<response.data.length-1;
							k++){
								src={
									src: '/user-photos/'+self.lodgements[y].rent.id+'/lodgement/'+self.lodgements[y].id+'/'+response.data[k]
									}
								;
								self.images.push(src);
								}
							self.
							$set(self.lodgements[y], 'images', self.images)
							}
						}
					}
				)
				}
			}
		,"redirectView":function (item){
			window.location.href = "http://127.0.0.1:8080/lodgement/"+item.id;
			}
		}
	,"template":"class path resource [templates/vueJS/confirm-button.html] cannot be opened because it does not exist"
	}
);