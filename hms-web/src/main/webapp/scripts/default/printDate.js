
var 
month = new Array();
month[0]="January";	
month[1]="February";
month[2]="March";
month[3]="April";
month[4]="May";
month[5]="June";
month[6]="July";
month[7]="August";
month[8]="September";
month[9]="October";
month[10]="November";
month[11]="December";

var 
day = new Array();
day[0]="Sunday";
day[1]="Monday";
day[2]="Tuesday";
day[3]="Wednesday";
day[4]="Thursday";
day[5]="Friday";
day[6]="Saturday";

today = new Date();
date = today.getDate();
day = (day[today.getDay()]);
month = (month[today.getMonth()]);
year = (today.getFullYear());

 suffix = (date==1 || date==21 || date==31) ? "st" : "th" &&
 (date==2 || date==22) ? "nd" : "th" && (date==3 || date==23) ? "rd" : "th";

function print_date()
{
  //document.write(day + "," + "&nbsp;" + date  + "<sup>" + suffix + "</sup>" + "&nbsp;" +  month + "," + "&nbsp;" + year);
  var currTime= date  + "<sup>" + suffix + "</sup>" + "&nbsp;" + month + "," + "&nbsp;" + year +  "," + "&nbsp;" + day;
  document.getElementById("currDate").innerHTML=currTime;
}

 
