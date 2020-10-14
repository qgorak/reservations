this.selected.loc = this.selected.geometry.coordinates.join('&');
window.location.replace('http://127.0.0.1:8080/lodgement/search/'+this.selected.loc+'&'+this.dates[0]+'&'+this.dates[1]+'&'+this.nbTravellers);
		