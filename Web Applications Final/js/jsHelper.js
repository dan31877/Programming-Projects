
/* Fades in a div with a welcome message and audio of a wildcat.*/
$(document).ready(function() {       
    
	
	/*  welcome div not working in IE:
	http://www.daniweb.com/web-development/javascript-dhtml-ajax/threads/324560/how-to-hide-and-unhide-div-tag-in-ie7-and-ie8
	http://stackoverflow.com/questions/4411551/if-browser-is-internet-explorer-run-an-alternative-script-instead?lq=1

	*/
	var browserName=navigator.appName; 
	if (browserName=="Microsoft Internet Explorer"){
  		
  		$("#welcome").fadeIn(1000); 
	    $('#welcome').delay(3000).fadeOut(1000);
	    
  		
  	} else {
	    $("#welcome").fadeIn(1000); 
	    document.getElementById('open_sound').play();
	    $('#welcome').delay(3000).fadeOut(1000);
	    
	}
	    

}); 

/* Alerts when the calendar is ready.*/ 
function calendarReady() {
	startTime(); 
	alert('Your calendar is ready');
	
}

/* Runs the clock on the calendar site*/
function startTime() {

	//http://stackoverflow.com/questions/10752972/show-current-date-and-time-in-javascript
	
	// Define variables
	var now = new Date();
	var h=now.getHours();
	var min=now.getMinutes();
	var s=now.getSeconds();
	var ampm=(now.getHours()>11)?"PM":"AM";
	var d=now.getDay();
	var y=now.getFullYear();
	var mon=now.getMonth();
	var da=now.getDate();
	var days=["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"];
	var months=["January","February","March","April", "May", "June","July","August","September", "October", "Novemeber","Decemeber"];
	
	// Set conditions for formatting
	if (h>12) {
		h-=12
	}
	if (h==0) {
		h=12
	}
	if (min<10) {
		min= "0" + min; 
	}
	if (s<10) { 
		s="0" + s; 
	}
	
	// Sets the day and month.
	d=days[d];
	mon=months[mon];
	
	// Sets the result, prints on the screen and runs every second.
	var result=d+", "+mon+" "+da+", "+y+" "+h+":"+min+":"+s+" "+ampm;
	var tim = setTimeout("startTime()",1000);
	$("#time").html(result);
	//document.getElementById("#time").innerHTML=result;
}

/* Fades one image to another
$(window).load(function() {

	//http://bradsknutson.com/blog/fade-one-image-into-another/
	$("#jqueryfade").hover(function(){
	    $("#jqueryfade img").animate({ opacity: 0 }, 'slow');
	}, function() {
	    $("#jqueryfade img").animate({ opacity: 1 }, 'slow');
	});
});*/

function fade(divid){
	
	var id=divid+" img";
	$(id).animate({ opacity: 0 }, 'slow');
	
}

function fadeIn(divid){

	var id=divid+" img";
	$(id).animate({ opacity: 1 }, 'slow');
}
