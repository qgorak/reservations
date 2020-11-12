selected = this.$refs.adress.selected.geometry.coordinates;
if(this.$refs.dates!=null){
dates = this.$refs.dates.dates;
}else{
dates=[];
}
var url=new URL(document.location.origin+"/lodgement/search");
url.searchParams.append('lon',selected[0]);
url.searchParams.append('lat',selected[1]);

if(this.nbTravellers!="null" && dates.length!=0){
	url.searchParams.append('start',dates[0]);
	url.searchParams.append('end',dates[1]);
	url.searchParams.append('nbr',this.nbTravellers);
}
else if(this.nbTravellers!="null"){
	url.searchParams.append('nbr',this.nbTravellers);
}
else if(dates.length!=0){
	url.searchParams.append('start',dates[0]);
	url.searchParams.append('end',dates[1]);
}
document.location.replace(url);