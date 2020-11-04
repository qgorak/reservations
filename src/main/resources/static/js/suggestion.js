let self=this;
if(event.target.value.length!=0){
	if(((event.keyCode >=65 && event.keyCode <= 90) || (event.keyCode >=48 && event.keyCode <= 57) || event.keyCode === 8 || event.keyCode === 27 || event.keyCode === 46 || event.keyCode === 32 )){			
		var items=event.target.value.split(' ');				
		let url = "https://api-adresse.data.gouv.fr/search/?q="+items.join("+")+"&limit=8";	
		clearTimeout(self.timeOut);
		this.timeOut =setTimeout(function(){
			fetch(url,{}).then(res => res.json()).then((out) => {self.search=out.features;});
		}, 500);
	}
}
else{
	this.destination={name:null,loc:null};
	this.search=[];
}