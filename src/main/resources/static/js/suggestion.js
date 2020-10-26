if(event.target.value.length!=0){
	var items=event.target.value.split(' ');
	let url = "https://api-adresse.data.gouv.fr/search/?q="+items.join("+")+"&limit=8";
	fetch(url)
	.then(res => res.json())
	.then((out) => {
	this.search=out.features;
	})
}
else{
	this.destination={name:null,loc:null};
	this.search=[];
}