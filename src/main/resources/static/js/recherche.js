selected = this.$refs.adress.selected.geometry.coordinates.join('&lat=');
if(this.$refs.dats!=null){
dates = this.$refs.dates.dates;
}else{
dates=[];
}
if(this.nbTravellers!="null" && dates.length!=0){
window.location.replace('/lodgement/search?lon='+selected+'&start='+dates[0]+'&end='+dates[1]+'&nbr='+this.nbTravellers);
}
else if(this.nbTravellers!="null"){
window.location.replace('/lodgement/search?lon='+selected+'&nbr='+this.nbTravellers);
}
else if(dates.length!=0){
window.location.replace('/lodgement/search?lon='+selected+'&start='+dates[0]+'&end='+dates[1]);
}
else{
window.location.replace('/lodgement/search?lon='+selected);
}