jQuery(function($) {
    var list = [];
    var currentObj='';
	$("a.topopup").click(function(e) {
		//if($(this).is(":checked")){
	    loading($(this).parents('table.classWiseList')); // loading
		currentObj=$(this).parents('table.classWiseList');
		 $(this).parents('td.parentClass').parent('tr').nextAll('tr.dayWiseList:visible').find('td.timeEntry:visible:gt(1)').each(function(index){
    	  $(this).find('input.timeChange:visible').each(function(){
    	  list.push([$(this).attr('class'), $(this).val()]); 
    	  });
	  	 });
			setTimeout(function(){ // then show popup, deley in .5 second
				loadPopup(); // function show popup
			}, 500); // .5 second
			e.stopPropagation();
	    return true;
	//	}else{
			//$(this).parent('span').removeAttribute("checked");
			//$(this).removeAttribute("checked");
		//	return false;
		//}
	});
	
	$("a#showTimingsHref").click(function(e) {
		loadingTable();
	 if($('input#hiddenVar').val()=="SD" || $('input#hiddenVar').val()=="CT"){
		  $('#toPopup').css("width","450px");
		  $('#toPopup').css("left","60%"); 
	 }
		setTimeout(function(){ // then show popup, deley in .5 second
				loadPopup(); // function show popup
			}, 500); // .5 second
			e.stopPropagation();
	return false;
	});
	$("a.clickToIssueBook").click(function(e) {
		var dataURL = jQuery.url.getChatURL("/library/ajaxLibrarianIssueBookDetails.do?tempId="+ $(this).attr('id')+"&anyId="+$(this).attr('data-id'));
		  $.ajax({
				url : dataURL,
				cache : false,
				dataType : 'text/html',
				success : function(response) {
					 $('div#assignIssueBooks').html(response);
					}
				});
		  	setTimeout(function(){ // then show popup, deley in .5 second
				loadPopup(); // function show popup
			}, 500); // .5 second
	});
	

 $('input[name="chkCheckBoxIds"]').click(function(e) {
	 if($(this).is(":checked")){
		var dataURL = jQuery.url.getChatURL("/library/ajaxAssignBooksToRacks.do?tempId="+ $(this).attr('id'));
		  $.ajax({
				url : dataURL,
				cache : false,
				dataType : 'text/html',
				success : function(response) {
					 $('div#assignBooksToRack').html(response);
					}
				});
		  	setTimeout(function(){ // then show popup, deley in .5 second
				loadPopup(); // function show popup
			}, 500); // .5 second
		 }
	});
	
	
	/* event for close the popup */
	$("div.closePopUp").hover(
					function() {
						$('span.ecs_tooltip').show();
					},
					function () {
    					$('span.ecs_tooltip').hide();
  					}
				);

	$("div.closePopUp").click(function() {
		disablePopup();  // function close pop up
	});

	$(this).keyup(function(event) {
		if (event.which == 27) { // 27 is 'Ecs' in the keyboard
			disablePopup();  // function close pop up
		}
	});

        $("div#backgroundPopup").click(function() {
		disablePopup();  // function close pop up
	});

	$('a.livebox').click(function() {
		$('input.classNames:checked').each(function(){
			var divId=$(this).attr('id');
			currentObj.nextAll('table.classWiseList').children('tbody').children('tr#'+divId).each(function(){
				if($(this).find('a.linkClassName').parents('td.parentClass').hasClass('closed')){
				  $(this).find('a#'+divId).click();	
				}
				     var thisObj1=$(this);
				            var i=0;
				             var thisObj=thisObj1.nextAll('tr.dayWiseList:visible:eq('+i+')'); //.find('div.timeEntry:gt(1):visible');
				             $.each(list,function(item){ 								 
 							   if(list[item][0].indexOf("SST") != -1){
 								   thisObj.find('input.SST').val(list[item][1]);
 							   }
 							   else if(list[item][0].indexOf('MBST') != -1){
 								    thisObj.find('input.MBST').val(list[item][1]);
 							   }
 							   else if(list[item][0].indexOf('MBET') != -1){
 								    thisObj.find('input.MBET').val(list[item][1]);
 							   }
 							   else if(list[item][0].indexOf('LBST') != -1){
 								    thisObj.find('input.LBST').val(list[item][1]);
 							   }
 							   else if(list[item][0].indexOf('LBET') != -1){
 								   thisObj.find('input.LBET').val(list[item][1]);
 							   }
 							    else if(list[item][0].indexOf('EBST') != -1){
 								    thisObj.find('input.EBST').val(list[item][1]);
 							   }
 							    else if(list[item][0].indexOf('EBET') != -1){
 								    thisObj.find('input.EBET').val(list[item][1]);
 							   }
 							   else if(list[item][0].indexOf('SET') != -1){
 								    thisObj.find('input.SET').val(list[item][1]);
 								    i++;
 								    thisObj=thisObj1.nextAll('tr.dayWiseList:visible:eq('+i+')');
 							   }
 							});
          			   });
				});
		    disablePopup();
		    list=[];
	       return false;
	});

	 /************** start: functions. **************/
/*	function loading(obj) {
		$("div.loader").show();
		var chkVal='';
	  
		obj.nextAll('table.classWiseList:visible').each(function(){	   
				chkVal += '<div><label><div class="checker"><span class="checked"><input type="checkbox" checked="checked" value='+$(this).find('tr:first').attr('class')+' class=classNames ' + $(this).find('tr:first').attr('class') + ' id='+ $(this).find('tr:first').attr('id')+'></span></div></label><span>'+$(this).find('tr:first').attr('class')+'</span></div>' ;
			});
	FormComponents.init();
	FormAdvanced.init();
		$('div.editable-checklist').html(chkVal);
		return false;
	}*/
	
	  
	
	function loading(obj) {
		$("div.loader").show();
		var chkVal='';
		chkVal +='<table class="spanValue" style="float: right; padding-bottom: 10px; width: 25%;"><tbody>'
		obj.nextAll('table.classWiseList:visible').each(function(){
				chkVal += '<tr><input type="checkbox" checked="checked" style=" min-width:15px;" class=classNames ' + $(this).find('tr:first').attr('class') + ' id='+ $(this).find('tr:first').attr('id')+'> <label class="css-label lite-green-check" name="checkbox1_lbl" for="checkbox1">'+$(this).find('tr:first').attr('class')+'</label></tr><br/>' ;
			});
		chkVal +='</tbody></table>';
		$('div#chkBoxesToSelect').html(chkVal);
		return false;
	}
	
	
	function loadingTable(){
		$("div.loader").show();
		$('p#timeTableSelect').html($('div#timingValues').html());
	}
	
	function closeloading() {
		$("div.loader").fadeOut('normal');
	}

	var popupStatus = 0; // set value

	function loadPopup() {
		if(popupStatus == 0) { // if value is 0, show popup
			closeloading(); // fadeout loading
			$("#toPopup").fadeIn(0500); // fadein popup div
			$("#backgroundPopup").css("opacity", "0.7"); // css opacity, supports IE7, IE8
			$("#backgroundPopup").fadeIn(0001);
			popupStatus = 1; // and set value to 1
		}
	}

	function disablePopup() {
		if(popupStatus == 1) { // if value is 1, close popup
			$("#toPopup").fadeOut("normal");
			$("#backgroundPopup").fadeOut("normal");
			popupStatus = 0;  // and set value to 0
		}
	}
	/************** end: functions. **************/
}); // jQuery End