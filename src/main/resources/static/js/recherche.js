this.selected.loc = this.selected.geometry.coordinates.join('&lat=');
if(this.nbTravellers!="null" && this.dates.length!=0){
window.location.replace('http://127.0.0.1:8080/lodgement/search?lon='+this.selected.loc+'&start='+this.dates[0]+'&end='+this.dates[1]+'&nbr='+this.nbTravellers);
}
else if(this.nbTravellers!="null"){
window.location.replace('http://127.0.0.1:8080/lodgement/search?lon='+this.selected.loc+'&nbr='+this.nbTravellers);
}
else if(this.dates.length!=0){
window.location.replace('http://127.0.0.1:8080/lodgement/search?lon='+this.selected.loc+'&start='+this.dates[0]+'&end='+this.dates[1]);
}
else{
window.location.replace('http://127.0.0.1:8080/lodgement/search?lon='+this.selected.loc);
}