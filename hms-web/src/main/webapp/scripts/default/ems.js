var monthNames= {"January" : {num:"0",sname:"Jan"},"February":{num:"1",sname:"Feb"},"March":{num:"2",sname:"Mar"},"April":{num:"3",sname:"Apr"},"May":{num:"4",sname:"May"},"June":{num:"5",sname:"Jun"},"July":{num:"6",sname:"Jul"},"August":{num:"7",sname:"Aug"},"September":{num:"8",sname:"Sep"},"October":{num:"9",sname:"Oct"},"November":{num:"10",sname:"Nov"},"December":{num:"11",sname:"Dec"} };
$(document).ready(function() {
	/*$('div#sideMenu > ul > li').click(function() {
		$('div#sideMenu > ul > li').removeClass('active');
		$(this).addClass('active');
	}, function() {
	});
	$('div#sideMenu > ul > li').hover(function() {
		$(this).addClass('hover');
	}, function() {
		$(this).removeClass('hover');
	});

	$('div#sideSubMenu > ul > li').click(function() {
		$('div#sideSubMenu > ul > li').removeClass('active');
		$(this).addClass('active');
	}, function() {
	});
	$('div#sideSubMenu > ul > li').hover(function() {
		$(this).addClass('hover');
	}, function() {
		$(this).removeClass('hover');
	});
	
	$('div#topMenu > ul > li').click(function() {
		$('div#topMenu > ul > li').removeClass('active');
		$(this).addClass('active');
	}, function() {
	});
	$('div#topMenu > ul > li').hover(function() {
		$(this).addClass('hover');
	}, function() {
		$(this).removeClass('hover');
	});

	$('div.subMenus > ul > li').click(function() {
		$('div.subMenus > ul > li').removeClass('active');
		$(this).addClass('active');
	}, function() {
	});*/
	
	$('.link').unbind('click');
	$(".link").click(function() {
		$(".dynamicForm").slideToggle(600);
	});
	$(".dynamicForm").hide();

	$('.addUserLink').unbind('click');
	$('.addUserLink').click(function() {
		if ($('#' + this.id + 'Child').is(":hidden")) {
			$('#' + this.id + 'Child').show();
		} else {
			$('#' + this.id + 'Child').hide();
		}
	});

	/* For Editing Form Data use the below script */

	$('.editlink').unbind('click');
	$('.editlink').click(function() {
		if ($('#' + this.id + 'Child').is(":hidden")) {
			$('#' + this.id + 'Child').show();
		} else {
			$('#' + this.id + 'Child').hide();
		}
	});
	/* For Alternate rows highlighting use the below script */
	if ($('table.striped')) {
		$('table.striped tr').removeClass('odd even');
		$('table.striped tr.loaded:even').addClass('odd');
		$('table.striped tr.loaded:odd').addClass('even');
		$('table.striped th').addClass('head');
	}

	$.subscribe('cleanOpenRows', function(event, data) {
		$("table.striped tr.load").each(function(i, row) {
			$(row).find('td').remove();
			$(row).hide();
		});
	});

	$.subscribe('cleanOpenDivs', function(event, data) {
		$("div.load").each(function(i, row) {
			$(row).find('div').remove();
			$(row).hide();
		});
	});

	$("tr.loaded").hover(function() {
		$(this).addClass("hover");
	}, function() {
		$(this).removeClass("hover");
	});

	// Scan divs with emsRemove class and bind lick function. This is used to
		// have a confirmation popup on the top of the same remove icon
		if ($('div.emsRemove')) {
			$('div.emsRemove').unbind('click');
			$("div.emsRemove").click(function() {
				confirmDialog(this);
			});
		}
		$('.defaultValue').each(function() {
		       var default_value = this.value;
		       $(this).css('color', '#000');
		       $(this).focus(function(){
	               if(this.value == default_value) {
	                       this.value = '';
	                       $(this).css('color', '#000');
	               }
		       });
		       $(this).blur(function(){
	               if(this.value == '') {
	            	   $(this).css('color', '#000');
	                       this.value = default_value;
	               }
		       });
		});

});
// Closed Document Ready method
function confirmDialog(event) {
	thishref = $(event).attr('id');
	if ($(event).next('.question').length <= 0) {
		$(event)
				.after(
						'<div class="question">Are You Sure?<br/> <span class="yes">Yes</span><span class="cancel">Cancel</span></div>');
	}

	$(event).next('.question').animate( {
		opacity : 1
	}, 300);
	$('.yes').unbind('click');
	$('.yes').bind('click', function() {
		var prdDiv = $(this).parents('.question');
		prdDiv.html('Processing...');
		$.ajax( {
			url : thishref,
			cache : false,
			success : function(html) {
				prdDiv.parent().parent().remove();
				if ($('table.striped')) {
					$('table.striped tr').removeClass('odd even');
					$('table.striped tr.loaded:even').addClass('odd');
					$('table.striped tr.loaded:odd').addClass('even');
				}
			}
		});
	});
	$('.cancel').unbind('click');
	$('.cancel').bind('click', function() {
		$(this).parents('.question').fadeOut(300, function() {
			$(this).remove();
		});
		return false;
	});
}

function confirmDialogRefresh(event) {
	var targets = $(event).attr('id').split('#');
	thishref = targets[0];
	if ($(event).next('.question').length <= 0) {
		$(event)
				.after(
						'<div class="question">Are You Sure?<br/> <span class="yes">Yes</span><span class="cancel">Cancel</span></div>');
	}

	$(event).next('.question').animate( {
		opacity : 1
	}, 300);
	$('.yes').unbind('click');
	$('.yes').bind('click', function() {
		var prdDiv = $(this).parents('.question');
		prdDiv.html('Processing...');
		$.ajax( {
			url : thishref,
			cache : false,
			success : function(html) {
				$.ajax( {
					url : targets[2],
					cache : false,
					success : function(html) {
						$('#' + targets[1]).html(html);
					}
				});
			}
		});
	});
	$('.cancel').unbind('click');
	$('.cancel').bind('click', function() {
		$(this).parents('.question').fadeOut(300, function() {
			$(this).remove();
		});
		return false;
	});
}

function disableAlphas(e) {
	var keynum;
	var keychar;
	var numcheck;

	if (window.event) // IE
	{

		keynum = e.keyCode;
	} else if (e.which) // Netscape/Firefox/Opera
	{
		keynum = e.which;
	}
	if (keynum == 8) {
		return true;
	} else if (keynum < 47 || keynum > 57) {
		return false;
	}
}

function isNonEmpty(str) {
    if (typeof str == 'undefined' || str == '' || str == null || str == 'null') {
        return false;
    }
    return true;
}

function commonLoadTab() {
	/*
	number of fieldsets
	*/
	var fieldsetCount;
	var stepObj;
	/*
	current position of fieldset / navigation link
	*/
	var current 	= 1;
    
	/*
	sum and save the widths of each one of the fieldsets
	set the final sum as the total width of the steps element
	*/
	var stepsWidth	= 0;
    var widths 		= new Array();
    
	if($('#steps').length > 0)
	{
		fieldsetCount = $('#steps').children().length;
		stepObj=$('#steps');
		$('#steps .step').each(function(i){
	        var $step 		= $(this);
			widths[i]  		= stepsWidth;
	        stepsWidth	 	+= $step.width();
    	});
	}
	else
	{
		fieldsetCount = $('#steps13').children().length;
		stepObj=$('#steps13');
		$('#steps13 .step13').each(function(i){
	        var $step 		= $(this);
			widths[i]  		= stepsWidth;
	        stepsWidth	 	+= $step.width();
	    });
	}
	
	
	
	stepObj.width(stepsWidth);
	
	/*
	to avoid problems in IE, focus the first input of the form
	*/
	stepObj.children(':first').find(':input:first').focus();	
	
	/*
	show the navigation bar
	*/
	$('#tabNavigation').show();
	
	/*
	when clicking on a navigation link 
	the form slides to the corresponding fieldset
	*/
	$('#tabNavigation a').bind('click',function(e){
		var $this	= $(this);
		var prev	= current;
		$this.closest('ul').find('li').removeClass('selected');
        $this.parent().addClass('selected');
		/*
		we store the position of the link
		in the current variable	
		*/
		current = $this.parent().index() + 1;
		//$('#' + $(this).attr('type')).html('<div align="center" style="padding:30px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		//if (isNonEmpty($(this).attr('type')))
		genericAjaxCall($(this).attr('id'),$(this).attr('type'),$(this).attr('class'))
		/*
		animate / slide to the next or to the corresponding
		fieldset. The order of the links in the navigation
		is the order of the fieldsets.
		Also, after sliding, we trigger the focus on the first 
		input element of the new fieldset
		If we clicked on the last link (confirmation), then we validate
		all the fieldsets, otherwise we validate the previous one
		before the form slided
		*/
        stepObj.stop().animate({
            marginLeft: '-' + widths[current-1] + 'px'
        },500,function(){
			/*if(current == fieldsetCount)
				validateSteps();
			else
				validateStep(prev);*/
			stepObj.children(':nth-child('+ parseInt(current) +')').find(':input:first').focus();	
		});
        e.preventDefault();
    });
	
	/*
	clicking on the tab (on the last input of each fieldset), makes the form
	slide to the next step
	*/
	/*if($('#steps').length > 0)
	{
		$('#steps > fieldset').each(function(){
			var $fieldset = $(this);
			$fieldset.children(':last').find(':input').keydown(function(e){
				if (e.which == 9){
					$('#tabNavigation li:nth-child(' + (parseInt(current)+1) + ') a').click();
					 force the blur for validation 
					$(this).blur();
					e.preventDefault();
				}
			});
		});
	}
	else
	{
		$('#steps13 > fieldset').each(function(){
			var $fieldset = $(this);
			$fieldset.children(':last').find(':input').keydown(function(e){
				if (e.which == 9){
					$('#tabNavigation li:nth-child(' + (parseInt(current)+1) + ') a').click();
					 force the blur for validation 
					$(this).blur();
					e.preventDefault();
				}
			});
		});
	}*/
	
	/*
	validates errors on all the fieldsets
	records if the Form has errors in $('#formElem').data()
	*/
	function validateSteps(){
		var FormErrors = false;
		for(var i = 1; i < fieldsetCount; ++i){
			var error = validateStep(i);
			if(error == -1)
				FormErrors = true;
		}
		stepObj.data('errors',FormErrors);	
	}
	
	/*
	validates one fieldset
	and returns -1 if errors found, or 1 if not
	*/
	function validateStep(step){
		if(step == fieldsetCount) return;
		
		var error = 1;
		var hasError = false;
		stepObj.children(':nth-child('+ parseInt(step) +')').find(':input:not(button)').each(function(){
			var $this 		= $(this);
			var valueLength = jQuisNonEmptyery.trim($this.val()).length;
			
			if(valueLength == ''){
				hasError = true;
				$this.css('background-color','#FFEDEF');
			}
			else
				$this.css('background-color','#FFFFFF');	
		});
		var $link = $('#tabNavigation li:nth-child(' + parseInt(step) + ') a');
		$link.parent().find('.error,.checked').remove();
		
		var valclass = 'checked';
		if(hasError){
			error = -1;
			valclass = 'error';
		}
		$('<span class="'+valclass+'"></span>').insertAfter($link);
		
		return error;
	}
	
}

/*function genericAjaxCall(link,targetdiv, params)
{
	if (isNonEmpty(link))
	{
		$('div#steps fieldset.step').each(function(){
			$(this).html("");
		});
		$('div#steps13 fieldset.step13').each(function(){
			$(this).html("");
		});
		$('#'+targetdiv).html('<div align="center"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle"> Loading ...</div>');
		$.ajax( {
		        url :jQuery.url.getChatURL(link),
				cache : false,
				data: params,
				success : function(response) {
				 $('#'+targetdiv).html(response);
				}
			});
	}
}*/

function clearTextField(field) {
	if (field.defaultValue == field.value)
		field.value = '';
	else if (field.value == '')
		field.value = field.defaultValue;
}

function getDateYYYYMMDD()
{
	var fullDate = new Date();
	var twoDigitMonth = ((fullDate.getMonth().length+1) === 1)? (fullDate.getMonth()+1) : '0' + (fullDate.getMonth()+1);
	return fullDate.getFullYear()+ "/" + fullDate.getDate() + "/" + twoDigitMonth;
}
/*function addNewTab2Nav(term, from, tomonth, id)
{
	var curmonth =(new Date()).getMonth();
	var active='';
	var title=term;
	if ( monthNames[from].num <= curmonth &&  monthNames[tomonth].num >= curmonth)
	{
		active='selected';
	}
	if ( monthNames[from].num ==  monthNames[tomonth].num)
	{
		title=title + ' ('+ monthNames[from].sname + ')';
	}
	else
	{
		title=title + ' ('+ monthNames[from].sname + ' - ' + monthNames[tomonth].sname+')';
	}
	$('div#tabNavigation ul').append($('<li>',{'class': active}).append($('<a>', {'title': title,'href':'#'}).append($('<div>',{'text': title, 'id':'feeTab'+id }))));
}
*/
function highLightFeeDueTab(id,from, tomonth)
{
	var curmonth =(new Date()).getMonth();
	if ( monthNames[from].num <= curmonth &&  monthNames[tomonth].num >= curmonth)
	{
		//$('#feeTab'+ id).attr('class','ribbon');
		/*bellow line is used in parent online payment*/
		$('ul.tooltipDiv  a#'+id).css({'color':'red','font-weight':'bold'});
	}
}
function highLightFeeDueTabParticularwise(id,from, tomonth)
{
	var curmonth =(new Date()).getMonth();
	if ( monthNames[from].num <= curmonth &&  monthNames[tomonth].num >= curmonth)
	{
		//$('#feeTab'+ id).attr('class','ribbon');
		/*bellow line is used in parent online payment*/
		$('tr.partclrTerm  span#'+id).css({'color':'red','font-weight':'bold'});
	}
}
function formatMobileNumber(object) {
       var phoneString = object.value;
       if (phoneString.length == 1) {
               phoneString = "+91-" + phoneString;
       }
       object.value = phoneString;
}
function formatPhoneNumber(object, e) {
	var charCode = (event.which) ? event.which : event.keyCode;
	if (charCode == 8) {
		return true;
	} else if (charCode < 47 || charCode > 57) {
		return false;
	}
	formatMobileNumber(object);
}

function changePageTitle(title)
{
	document.title='Eazy School | '+title;
}

function printFutureAcademicPaymentPreview(studentId,ctrDateStr,invoiceNumber,academicYearId,feePaymentType,paidAmount,tempString,invoiceString){
	var paidAmountInwords = feeAmountInWords(paidAmount);
    var url = jQuery.url.getChatURL("/reports/ajaxStudentPaymentAdmissionPdfFeeReport.do");
    var pars = 'spId=' + studentId+'&createdDate='+ctrDateStr+'&invoiceNumber='+invoiceNumber+'&academicYearId='+academicYearId+"&feePaymentType="+feePaymentType+"&plTitle="+paidAmountInwords + "&downloadrecipt="+tempString + "&invoiceString="+invoiceString;
    $.ajax({
        url : url,
        cache : false,
        data:pars,
        success : function(data) {
           prntPrvw(data,url,pars);
        }
     });
    }

function printPreview(studentId,ctrDateStr,invoiceNumber,paidAmount,tempString,academicYearId,invoiceString){
	var paidAmountInwords = feeAmountInWords(paidAmount);
    var url = jQuery.url.getChatURL("/reports/ajaxStudentPaymentAdmissionPdfFeeReport.do");
    var pars = 'spId=' + studentId+'&createdDate='+ctrDateStr+'&invoiceNumber='+invoiceNumber+"&plTitle="+paidAmountInwords+"&downloadrecipt="+tempString+"&academicYearId="+academicYearId+"&invoiceString="+invoiceString;
    $.ajax({
        url : url,
        cache : false,
        data:pars,
        success : function(data) {
           prntPrvw(data,url,pars);
        }
     });
    }
function printAdmissionPreview(studentId,ctrDateStr,invoiceNumber,paidAmount,tempString,invoiceString){
	var paidAmountInwords = feeAmountInWords(paidAmount);
    var url = jQuery.url.getChatURL("/reports/ajaxStudentPaymentAdmissionPdfFeeReport.do");
    var pars = 'spId=' + studentId+'&createdDate='+ctrDateStr+'&invoiceNumber='+invoiceNumber +"&plTitle="+paidAmountInwords +"&downloadrecipt="+tempString+"&invoiceString="+invoiceString;
    $.ajax({
        url : url,
        cache : false,
        data:pars,
        success : function(data) {
           prntPrvw(data,url,pars);
        }
     });
    }
function printChallanaPreview(studentId,ctrDateStr,invoiceNumber,paidAmount,tempString,academicYearId,invoiceString){
	var paidAmountInwords = feeAmountInWords(paidAmount);
    var url = jQuery.url.getChatURL("/reports/ajaxGenerateStudentChallana.do");
    var pars = 'spId=' + studentId+'&createdDate='+ctrDateStr+'&invoiceNumber='+invoiceNumber+"&plTitle="+paidAmountInwords+"&downloadrecipt="+tempString+"&academicYearId="+academicYearId+"&invoiceString="+invoiceString;
    $.ajax({
        url : url,
        cache : false,
        data:pars,
        success : function(data) {
           prntPrvw(data,url,pars);
        }
     });
    }
function prntPrvw(data,url,pars)
    {
        //put returned XML in the div
       var win = window.open(url +'?'+pars, 'popup', 'directories=no,titlebar=no,toolbar=no,location=no,status=no,menubar=no,scrollbars=1'); 
    	  
	   if (win == null || typeof(win)=='undefined') { 	
			//alert('Please disable your pop-up blocker and try again if you will not get receipt.'); 
			//window.location.reload();
		} 
		else { 	
			win.focus();
		}
       
       win.document.write("<body bgColor=''>");
        win.document.write("<div align='center'> "+ data +"</div><br/><br/>");
      //  win.document.write("<div align='center'><a href='javascript:window.print();'>Print</a>&nbsp;&nbsp;</div>");
        win.document.close(); 
       
        /*win.document.getElementById("noPrint").innerHTML='';
        win.document.getElementById("noPrintYui-nav").innerHTML='';*/
    }

function printTransportPreview(studentId,ctrDateStr,invoiceNumber){
    var url = jQuery.url.getChatURL("/reports/ajaxTransportStudentPaymentPdfFeeReportPopUp.do");
    var pars = 'spId=' + studentId+'&createdDate='+ctrDateStr+'&invoiceNumber='+invoiceNumber;
    $.ajax({
        url : url,
        cache : false,
        data:pars,
        success : function(data) {
           prntPrvw(data,url,pars);
        }
     });
    } 

function printHostelPreview(studentId,ctrDateStr,invoiceNumber){
    var url = jQuery.url.getChatURL("/reports/ajaxStudentHostelPaymentPdfFeeReportPopUp.do");
    var pars = 'studentId=' + studentId+'&createdDate='+ctrDateStr+'&invoiceNumber='+invoiceNumber;
    $.ajax({
        url : url,
        cache : false,
        data:pars,
        success : function(data) {
           prntPrvw(data,url,pars);
        }
     });
}
function printSraffHostelPreview(staffId,ctrDateStr,invoiceNumber){
    var url = jQuery.url.getChatURL("/reports/ajaxStaffHostelPaymentPdfFeeReportPopUp.do");
    var pars = 'staffId=' + staffId+'&createdDate='+ctrDateStr+'&invoiceNumber='+invoiceNumber;
    $.ajax({
        url : url,
        cache : false,
        data:pars,
        success : function(data) {
           prntPrvw(data,url,pars);
        }
     });
}
function loadCloseIcon() {
	var errMsg = $('.block .message');
	if (errMsg) {
		$('.block .message').hide().append(
				'<span class="close1" title="Click to Close"></span>').fadeIn(
				'slow');
		$('.block .message .close1').hover(function() {
			$(this).addClass('hover');
		}, function() {
			$(this).removeClass('hover');
		});

		$('.block .message .close1').click(function() {
			$(this).parent().fadeOut('slow', function() {
				$(this).remove();
			});
		});
	}
}
function confirmDialogWithTarget(event,target) {
	thishref = $(event).attr('id');
	if ($(event).next('.question').length <= 0) {
		$(event).after('<div class="question">Are You Sure?<br/> <span class="yes">Yes</span><span class="cancel">Cancel</span></div>');
	}
	$(event).next('.question').animate( {
		opacity : 1
	}, 300);
	$('.yes').unbind('click');
	$('.yes').bind('click', function() {
		var prdDiv = $(this).parents('.question');
		prdDiv.html('Processing...');
		$.ajax( {
			url : thishref,
			cache : false,
			success : function(html) {
				$('#'+target).html(html);
				/*prdDiv.parent().parent().remove();
				if ($('table.striped')) {
					$('table.striped tr').removeClass('odd even');
					$('table.striped tr.loaded:even').addClass('odd');
					$('table.striped tr.loaded:odd').addClass('even');
				}*/
			}
		});
	});
	$('.cancel').unbind('click');
	$('.cancel').bind('click', function() {
		$(this).parents('.question').fadeOut(300, function() {
			$(this).remove();
		});
		return false;
	});
}
function checkStartTimeEndTImeValidation() {
	var checkindatestr = $('#startDate').val();
	var dateParts = checkindatestr.split("/");
	var checkindate = new Date(dateParts[2], dateParts[1] - 1, dateParts[0]);

	var checkinEnddatestr = $('#endDate').val();
	var dateendParts = checkinEnddatestr.split("/");
	var checkinEnddate = new Date(dateendParts[2], dateendParts[1] - 1,	dateendParts[0]);
	if (checkindate > checkinEnddate) {
	alert("Your end date is equal or more than your start date.");
	$('#endDate').val("");
	return false;
	}
}

function onlyNumbers(event){
	  var charCode = (event.which) ? event.which : event.keyCode;
	  if (charCode > 31 && (charCode < 48 || charCode > 57))
	   return false;
	 return true;
}
function confirmServicesStatusDialog(event) {
			var thishref = $(event).attr('data');
			if ($(event).next('.question').length <= 0) {
				$(event)
						.after(
								'<div class="question">Are you sure?<br/> <span class="yes">Yes</span><span class="cancel">Cancel</span></div>');
			}
			$(event).parent('span').addClass("checked");
	        $(event).attr("checked",true);
			$(event).next('.question').animate( {
				opacity : 1
			}, 300);
			$('.yes').unbind('click');
			$('.yes').bind('click', function() {
				var prdDiv = $(this).parents('.question');
				prdDiv.html('Processing...');
				$.ajax( {
					url : thishref+".do",
					cache : false,
					dataType : 'json',
					success : function(response) {
							prdDiv.remove();							
							/*var tempStr = response.status;
							if(tempStr == "true"){
								$(event).attr("checked","checked");
								$(event).val(true);
							}else{
								$(event).removeAttr("checked");
								$(event).val(false);
							}*/
						}
				});
			});
			$('.cancel').unbind('click');
			$('.cancel').bind('click', function() {
				$(this).parents('.question').fadeOut(300, function() {
					$(this).parent("span").removeClass("checked");
			        $(this).parent("span").find("input").removeAttr("checked");
			        $(this).remove();
				});
				return false;
			});
}
 
function updateDirectionArrows(obj, inverse) {
    if (inverse > 0) { obj.css("background-image", "url('../images/icons/bullet-arrow-up.png')");}
    else { obj.css("background-image", "url('../images/icons/bullet-arrow-down.png')"); }
}
function validateMobNumber(txtMobId) {
	/*var mob = /^(\+91-|\+91|0)?\d{10}$/;
	if (mob.test($.trim(txtMobId)) == false) {
		alert("Please enter valid mobile number.");
		$("#mobileNumber").val('');
		$("#mobileNumber").focus();
		return false;
	}else if(txtMobId.length == 10){
		//$("#mobileNumber").val("+91-"+txtMobId);
		return true;
	}else */
	if (isNonEmpty(txtMobId)) {
		if (!(txtMobId.length == 10)) {
			alert("Please enter 10 digits mobile number.");
			$("#mobileNumber").val("");
			$("#mobileNumber").focus();
			return false;
		}else if((txtMobId.length == 10)) {
			return true;
		}
	}
}

/*$("#termDescountAmount").change(function(){ 
   var total=$("#totalPayAmountDiv").val();
 	var termDescountAmount=($(this).val()).replace('.','');
     if (Math.floor(termDescountAmount) != termDescountAmount) {
      alert("Please enter numbers.");
      $("#termDescountAmount").val('');
      $("#totalPayAmountAfterDiscount").val(total);
      return  false;
     }
});*/

function validateSslcNumber(sslcNumber) {
	if((sslcNumber.length == 15)) {
	   return true;
	}else if(sslcNumber.length ==0){
			$("#sslcNumber").val('');
		return true;
	}else if((sslcNumber.length >0) && (sslcNumber.length <15)){
		alert("Please enter valid SSLC Number.");
		$("#sslcNumber").val('');
		$("#sslcNumber").focus();
		return false;
	}
}
function validateTmrNumber(tmrNumber) {
	if((tmrNumber.length == 7)) {
	   return true;
	}else if(tmrNumber.length ==0){
		$("#tmrNumber").val('');
		return true;
	}else if((tmrNumber.length >0) && (tmrNumber.length <7)){
		alert("Please enter valid TMR Number.");
		$("#tmrNumber").val('');
		$("#tmrNumber").focus();
		return false;
	}
}

function printStudentFineFeeInvoice(studentId,invoiceNumber,fineAmount,paymentDateStr){
	var fineAmountInwords = feeAmountInWords(fineAmount);
    var pars = "anyId=" + studentId +'&invoiceNumber='+invoiceNumber+"&plTitle="+fineAmountInwords+"&paymentDateStr="+paymentDateStr;
	var url = jQuery.url.getChatURL("/schoolfee/ajaxStudentPaymentPdfFineFeeReportPopup.do");
	$.ajax( {
	url : url,
	cache : false,
	data : pars,
	success : function(data) {
	       	prntPrvw(data,url,pars);
	    }
	});
}

function removeHTMLTags(strInputCode) {	
	if (strInputCode) {
		/*
		 * This line is optional, it replaces escaped brackets with real
		 * ones, i.e. < is replaced with < and > is replaced with >
		 */ 
		strInputCode = strInputCode.replace(/&amp;#39;/g,"'");
		strInputCode = strInputCode.replace(/&amp;nbsp;/g,' ');
		strInputCode = strInputCode.replace(/&amp;lt;/g,'<');
		strInputCode = strInputCode.replace(/&amp;gt;/g,'>');
		strInputCode = strInputCode.replace(/&amp;lsquo;/g,"'");
		strInputCode = strInputCode.replace(/&amp;quot;/g,'"');
		strInputCode = strInputCode.replace(/&amp;ldquo;/g,'"');
		strInputCode = strInputCode.replace(/&amp;rdquo;/g,'"');
		strInputCode = strInputCode.replace(/&amp;rsquo;/g,"'");
		// strInputCode=strInputCode.replace(/br/g,'ohcbreak');
		// strInputCode=strInputCode.replace(/\/li.*?/g,'ohcbreak');
		strInputCode = strInputCode.replace(/&(lt|gt);/g,
		function(strMatch, p1) {
			return (p1 == "lt") ? "<" : ">";
		});
		
		strInputCode=strInputCode.replace(/<br.*?>/g,'ohcbreak');
		strInputCode=strInputCode.replace(/<\/li>/g,'ohcbreak');
		strInputCode=strInputCode.replace(/<hr.*?>/g,'ohcbreak');
		strInputCode=strInputCode.replace(/<div.*?>/g,'ohcbreak');
		var strTagStrippedText = strInputCode.replace(/<\/?[^>]+(>|$)/g, "");
		
		strTagStrippedText=strTagStrippedText.replace(/ohcbreak/g,"<br/>");
		strTagStrippedText=strTagStrippedText.replace(/<br\/><br\/>/g,"<br/>");
		// var temp = strTagStrippedText.replace(/[\s(&amp;)]+$/g,'');
		// temp = temp.replace(/[\s(nbsp;)]+$/g,'');
		return strTagStrippedText;
	}
}
function shortDescWithLink(source,id,limit) {
	var obj = $(source);
	if (obj) {
		var trunc = removeHTMLTags(obj.html());
		if (trunc.length > limit) {
			/*
			 * Truncate the content of the P, then go back to the end of the
			 * previous word to ensure that we don't truncate in the middle
			 * of a word
			 */
			trunc = trunc.substring(0, limit);
			trunc = trunc.replace(/\w+$/, '');
			/*
			 * Add an ellipses to the end and make it a link that expands
			 * the paragraph back to its original size
			 */
			
			trunc += '<a href="#popupReadMoreDiv" style="text-decoration:none" data-toggle="modal" onclick="javascript:popupReadMoreKbankStudy('+id+');">' + ' Read More</a>';
		}
		
		$('<div>'+trunc+'</div>').insertAfter(obj);
		obj.remove();
	}
}	
 function getUrlVars() {
	var vars = [], hash;
	var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
	for ( var i = 0; i < hashes.length; i++) {
		hash = hashes[i].split('=');
		vars.push(hash[0]);
		vars[hash[0]] = hash[1];
	}
	  return vars;
}
 
 // Auto dropdown and clicked link enable with active state
 $('li.dropdown li').click(function(){
	  $('ul.nav-tabs li').removeClass('active');
	  $('.dropdown li').removeClass('active');
	  $(this).parents('li.dropdown').addClass('active');
	  $(this).parents('li.dropdown').children('.js-activated').html($(this).find('a').html()+'<b class="caret"></b>');
	  $(this).addClass('active');
	});

 // Check All, Uncheck All common functions , we should hav to use this globally 
 /* $('input.allClasses').click(function(){
	  if ($(this).is(':checked')){
		   $(this).closest('div.form-group').next('div.form-group').find("input[name='chkBoxSelectedIds']").each(function(){
		   $(this).parent('span').addClass('checked');
		   $(this).attr("checked", "true");
		});
	}
	else{
	 $(this).closest('div.form-group').next('div.form-group').find("input[name='chkBoxSelectedIds']").each(function(){
		   $(this).parent('span').removeClass('checked');
		   $(this).removeAttr("checked");
		});
	}
 });
$("input[name='chkBoxSelectedIds']").click(function(){
	 if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
		    $(this).closest('div.form-group').previous('div.form-group').find("input.allClasses").parent('span').removeClass("checked");
			$(this).closest('div.form-group').previous('div.form-group').find("input.allClasses").attr("checked", false);
		} else {
			$(this).closest('div.form-group').previous('div.form-group').find("input.allClasses").attr("checked", true);
			$(this).closest('div.form-group').previous('div.form-group').find("input.allClasses").parent('span').addClass("checked");
		}
 });*/

  //below script used to value to words
 function feeAmountInWords(value) {
    var fraction = Math.round(frac(value) * 100);
    var f_text = "";

    if (fraction > 0) {
        f_text = "And " + convert_number(fraction) + " Paise";
    }

    //return convert_number(value) + " Rupees " + f_text + " Only";
    return convert_number(value);
}

function frac(f) {
    return f % 1;
}

function convert_number(number) {
    if ((number < 0) || (number > 999999999)) {
        return "NUMBER OUT OF RANGE!";
    }
    var Gn = Math.floor(number / 10000000); /* Crore */
    number -= Gn * 10000000;
    var kn = Math.floor(number / 100000); /* lakhs */
    number -= kn * 100000;
    var Hn = Math.floor(number / 1000); /* thousand */
    number -= Hn * 1000;
    var Dn = Math.floor(number / 100); /* Tens (deca) */
    number = number % 100; /* Ones */
    var tn = Math.floor(number / 10);
    var one = Math.floor(number % 10);
    var res = "";

    if (Gn > 0) {
        res += (convert_number(Gn) + " Crore");
    }
    if (kn > 0) {
        res += (((res == "") ? "" : " ") +
            convert_number(kn) + " Lakh");
    }
    if (Hn > 0) {
        res += (((res == "") ? "" : " ") +
            convert_number(Hn) + " Thousand");
    }

    if (Dn) {
        res += (((res == "") ? "" : " ") +
            convert_number(Dn) + " Hundred");
    }


    var ones = Array("", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen");
    var tens = Array("", "", "Twenty", "Thirty", "Fourty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety");

    if (tn > 0 || one > 0) {
        if (!(res == "")) {
            res += " And ";
        }
        if (tn < 2) {
            res += ones[tn * 10 + one];
        } else {

            res += tens[tn];
            if (one > 0) {
                res += ("-" + ones[one]);
            }
        }
    }

    if (res == "") {
        res = "zero";
    }
    return res;
}
 
 //below script used to age cal
 function calculateAge(value){
	    var now = new Date();
	    var past = new Date(value);
	    var nowYear = now.getFullYear();
	    var pastYear = past.getFullYear();
	    var age = nowYear - (pastYear+1);
	    return age;
				 
	 }
 function printPreviewAdmissions(empId, bedId, anyTitle, tempId1) {
	 if(isNonEmpty(anyTitle) && (empId !=0 || bedId !=0) && tempId1 !=0){
		 var paidAmountInwords = feeAmountInWords(anyTitle);
			var url = jQuery.url.getChatURL("/reports/ajaxStudentAdmissionFeeReportPopup.do");
			var pars = "empId=" + empId + "&plTitle=" + paidAmountInwords + "&tempId1="+ tempId1 + "&anyTitle=" + anyTitle + "&bedId=" + bedId;
			alert(pars)
			$.ajax({
				url : url,
				cache : false,
				data : pars,
				success : function(data) {
					prntPrvw(data,url,pars);
				}
			});
	 }
}
	function getIpAddressLocation(){
		//if(isNonEmpty(geoip_city()) && geoip_city() != ""){
			var location = "";//geoip_city();
			var pars = "location="+location;
			$.ajax({
				url : jQuery.url.getChatURL("/common/ajaxSaveIpAddressLocation.do"),
				cache : true,
				data : pars,
				success : function(response) {
					//$('#asignedClassSubjCont').html("");
				}
			});
		//}
	}

function dateRestrictionWithinAcademicYear(startDate,endDate){
	var stDate = new Date(startDate);
	var enDate = new Date(endDate);
	var yyyy ='';
	var ayyyy ='';			
	//alert(strDate+"===="+endDate);
	var dd = stDate.getDate();
    var mm = stDate.getMonth()+1; //January is 0!
    var edd = enDate.getDate();
    var emm = enDate.getMonth()+1; //January is 0!
    $.browser.chrome = /chrom(e|ium)/.test(navigator.userAgent.toLowerCase()); 
    if($.browser.chrome) {
    		yyyy = stDate.getFullYear();
    		ayyyy = enDate.getFullYear();
    	} else if ($.browser.mozilla) {
    		if($.browser.version >=49){
	    		yyyy = stDate.getFullYear();
	    		ayyyy = enDate.getFullYear();
    		}else{
    			yyyy = stDate.getFullYear()+100;
	    		ayyyy = enDate.getFullYear()+100;
    		}
	    		
    	} else if ($.browser.msie) {
    		yyyy = stDate.getFullYear()+100;
    		ayyyy = enDate.getFullYear()+100;
    	}
    if(dd<10){
        dd='0'+dd
    } 
    if(mm<10){
        mm='0'+mm
    } 
    if(edd<10){
        edd='0'+edd
    } 
    if(emm<10){
        emm='0'+emm
    } 
    var sday = mm+'/'+dd+'/'+yyyy;
    var eday = emm+'/'+edd+'/'+ayyyy;
    $('div.date-picker').datepicker({
        startDate: sday,
        endDate: eday
    }); 
}
function printPreviewForAdmissionEnquiry(admissionInquiryId,admissionFeeAmount){
	var paidAmountInwords = feeAmountInWords(admissionFeeAmount);
    var url = jQuery.url.getChatURL("/reports/ajaxAdmissionEnquiryFeePaidReport.do");
    var pars = 'admissionInquiry.id=' + admissionInquiryId+"&plTitle="+paidAmountInwords;
    $.ajax({
        url : url,
        cache : false,
        data:pars,
        success : function(data) {
           prntPrvw(data,url,pars);
        }
     });
 }
function isEmptyVal(val){
	if(typeof(val) === 'object'){
        if(JSON.stringify(val) === '{}' || JSON.stringify(val) === '[]'){
            return true;
        }else if(!val){
            return true;
        }
        return false;
    }else if(typeof(val) === 'string'){
        if(!val.trim()){
            return true;
        }
        return false;
    }else if(typeof(val) === 'undefined' || (typeof val == 'undefined' || !val || val.length === 0 || val === "" || !/[^\s]/.test(val) || /^\s*$/.test(val) || val.replace(/\s/g,"") === "")){
        return true;
    }else{
        return false;
	}
}