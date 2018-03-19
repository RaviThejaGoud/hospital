<%@ include file="/common/taglibs.jsp"%>
<div class="portlet box blue" style="margin-bottom:0px;border-width:0px 1px;">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-globe"></i>Holidays Settings
		</div>
	</div>
	<div class="portlet-body">
			<div class="form-body">
				<div class="row">
					<div class="col-md-12">
						<div class="form-group">
							<label class="control-label col-md-3">
								School Holidays :
							</label>
								<div class="col-md-5">
									<div class="make-switch has-switch" data-id="SH" data-value="CH"
										style="width: 180px" data-off="warning" data-on="success"
										data-off-label="ClassWise" data-on-label="SchoolWise">
										
										<s:if test='%{tempString3 != null}'>
											<s:if test='%{academicYear.holidayStatus =="SH"}'>
												<input type="radio" class="toggle" checked="checked" id="holidayStatus">
											 <input type="hidden" name="academicYear.holidayStatus"  
												value='<s:property value="academicYear.holidayStatus"/>'>
											</s:if>
											<s:elseif test='%{academicYear.holidayStatus =="CH"}'>
											 <input type="radio" class="toggle" id="holidayStatus">
												<input type="hidden" name="academicYear.holidayStatus" 
												value='<s:property value="academicYear.holidayStatus"/>' onclick="javascript:GetSchoolHolidayTimings(this.value);">
											</s:elseif>
											<s:else>
												 <input type="radio" class="toggle" checked="checked" id="holidayStatus">
											  	 <input type="hidden" name="academicYear.holidayStatus"  value='SH'>
											</s:else>
										</s:if>
										<s:else>
											<s:if test='%{academicYear.holidayStatus =="SH"}'>
												<input type="radio" class="toggle" checked="checked" id="holidayStatus" disabled="disabled" >
											 <input type="hidden" name="academicYear.holidayStatus"  
												value='<s:property value="academicYear.holidayStatus"/>'>
											</s:if>
											<s:elseif test='%{academicYear.holidayStatus =="CH"}'>
											 <input type="radio" class="toggle" id="holidayStatus" disabled="disabled" >
												<input type="hidden" name="academicYear.holidayStatus" 
												value='<s:property value="academicYear.holidayStatus"/>' onclick="javascript:GetSchoolHolidayTimings(this.value);">
											</s:elseif>
											<s:else>
												 <input type="radio" class="toggle" checked="checked" id="holidayStatus">
											  	 <input type="hidden" name="academicYear.holidayStatus"  value='SH'>
											</s:else>
										</s:else>
										
									</div>
								</div>
							</div>
						</div>
				</div>
				<div class="row" id="schoolWiseHolidays">
					<p>
						<span class="label label-danger"> NOTE : </span>&nbsp; Select working
						days ex:{Monday,Tuesday}
					</p>
					<div class="form-group">
						<label class="col-md-2 control-label">
							Select Working Week Days :
						</label>
						<div class="col-md-9">
							<s:checkboxlist list="weekDaysList" name="chkBoxSelectedIds"
								theme="ems"></s:checkboxlist>
						</div>
					</div>
				</div>
			<div class="portlet" id="classWiseHolidays">
			<table class="table table-striped table-bordered table-hover" style="width: 98.7%;">
				<tr>
					<td style="width: 45px;">
						<label style="width: 45px;" id="">
							Classes
						</label>
					</td>
					<td style="width: 45px;">
						Sun <input type="checkbox" name="" onClick="checkAllSun()" class="allSun" />
					</td>
					<td style="width: 45px;">
						Mon <input type="checkbox" name="" onClick="checkAllMon()" class="allMon" />
					</td>
					<td style="width: 45px;">
						Tue <input type="checkbox" name="" onClick="checkAllTue()" class="allTus" />
					</td>
					<td style="width: 45px;">
						Wed <input type="checkbox" name="" onClick="checkAllWed()" class="allWed" />
					</td>
					<td style="width: 45px;">
						Thu <input type="checkbox" name="" onClick="checkAllThu()" class="allThu" />
					</td>
					<td style="width: 45px;">
						Fri <input type="checkbox" name="" onClick="checkAllFri()" class="allFri" />
					</td>
					<td style="width: 45px;">
						Sat <input type="checkbox" name="" onClick="checkAllSat()" class="allSat" />
					</td>
				</tr>
			</table>
			<div class="table-scrollable" style="height: 200px;overflow-y:scroll;margin: 0px;">
			<table class="table table-striped table-bordered table-hover table-full-width classAndWeekWiseList">
				<s:if	test="%{classList != null  }">
					 <s:iterator value="classList">
						<tr class="classAndWeek" id="<s:property value="id"/>">
							<td class="grid_2" style="width: 14.6%;">
								<label id="<s:property value="id"/>" class="classNameWise">
									<s:property value="className" />
								</label>
							</td>
							<td class="grid_2 holidayEntry" style="width: 45px;" id="SUNDAY<s:property value='id'/>">
									<input type="checkbox" name="sunWithClasses" value="<s:property value='id'/>" id="Sun" class="SUNDAY" />
							</td>
							<td class="grid_2 holidayEntry" style="width: 45px;" id="MONDAY<s:property value='id'/>">
									<input type="checkbox" name="monWithClasses" value="<s:property value='id'/>" id="Mon" class="MONDAY" />
							</td>
							<td class="grid_2 holidayEntry" style="width: 45px;" id="TUESDAY<s:property value='id'/>">
									<input type="checkbox" name="tusWithClasses" value="<s:property value='id'/>" id="Tus" class="TUESDAY" />
							</td>
							<td class="grid_2 holidayEntry" style="width: 45px;" id="WEDNESDAY<s:property value='id'/>">
									<input type="checkbox" name="wedWithClasses" value="<s:property value='id'/>" id="Wed" class="WEDNESDAY" />
							</td>
							<td class="grid_2 holidayEntry" style="width: 45px;" id="THURSDAY<s:property value='id'/>">
									<input type="checkbox" name="thuWithClasses" value="<s:property value='id'/>" id="Thu" class="THURSDAY" />
							</td>
							<td class="grid_2 holidayEntry" style="width: 45px;" id="FRIDAY<s:property value='id'/>">
									<input type="checkbox" name="friWithClasses" value="<s:property value='id'/>" id="Fri" class="FRIDAY" />
							</td>
							<td class="grid_2 holidayEntry" style="width: 45px;" id="SATURDAY<s:property value='id'/>">
									<input type="checkbox" name="satWithClasses" value="<s:property value='id'/>" id="Sat" class="SATURDAY" />
							</td>
						</tr>
					</s:iterator> 
					</s:if>
					<s:else>
						<div class="alert alert-info">
							Currently there are no classes.
						</div>
					</s:else>
				</table>
			</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	var hStatus = '<s:property value="academicYear.holidayStatus"/>';
	GetSchoolHolidayTimings(hStatus);
 });
 
 $('table.classWiseList').each(function(){
	$(this).find('tr.dayWiseList:last').each(function(){
	    $(this).find('td#copyPasteDiv').hide();
	  });
	});
$('input[name="chkBoxSelectedIds"]').each(function(){
    var divClass1=$(this).parents('label').text().trim();
    if($(this).is(":checked")){
		 $('tr.dayWiseList').each(function(){		    
		   if($(this).attr('id')==divClass1)
			 		$(this).show();
			});
	  }
	  else{
	    $('tr.dayWiseList').each(function(){
	    	 if($(this).attr('id')==divClass1)
			 	$(this).hide();
		 });
		}	  
});
 
 var stauts='<s:property value="academicYear.holidayStatus"/>';
 if(stauts == "CH"){
  var dataURL = jQuery.url.getChatURL("/admin/ajaxGetSchoolHolidaysDetails.do?academicYearId="+ $('input#academicYearId').val());
	           $.ajax({
	 				url : dataURL,
	 				cache : false,
	 				dataType : 'json',
	 				success : function(response) {
	 					if(isNonEmpty(response)) {
	 						var strArr=[];
	 						var data = response.data;
	 						//alert("data.length ="+data.length);
	 						if (data.length > 0) {
	 						  var boolVal=true;
	 						  for ( var i = 0; i < data.length; i++) {
		 						  if(boolVal){
		 						      $('input[name="academicYear.holidayStatus"]').val("CH");						       
		 						      $('div.make-switch').find("label[for='holidayStatus']").parent("div.switch-animate").removeClass("switch-on").addClass("switch-off");
		 						       GetSchoolHolidayTimings("CH");
		 						       boolVal=false;
		 						    } 
		 						    strArr=data[i].CLASSID.split("_");
		 						    $('table.classAndWeekWiseList').each(function(){
		 						      $(this).find('tr').each(function(){
		 						      if(strArr[1]==$(this).attr("id")){
		 						    	 $(this).find('td.holidayEntry').each(function(){
		  							   		//alert($(this).find('input[type=checkbox]').attr("class")+"-----week name ----"+strArr[0]);
		  							  		if($(this).find('input[type=checkbox]').attr("class") == strArr[0]){
		  							  			$(this).find('input[type=checkbox]').parent('span').removeClass('checked');
		  							  		    $(this).find('input[type=checkbox]').parent('span').addClass('removeChecked');
		  							  		    $(this).find('input[type=checkbox]').prop('checked', false);
		  							  			$(this).find('input[type=checkbox]').removeAttr("checked");
		  								    }
		  							  		else{
		  							  			if(!$(this).find('input[type=checkbox]').parent('span').hasClass("removeChecked")){
		  							  				$(this).find('input[type=checkbox]').parent('span').addClass('checked');
		  							  		    	$(this).find('input[type=checkbox]').attr('checked',true);
		  							  			}
		  							  		}
		  						    });
		 						      }else{
		 						    	if(!$(this).find('input[type=checkbox]').parent('span').hasClass("removeChecked")){
					  				$(this).find('input[type=checkbox]').parent('span').addClass('checked');
					  		    	$(this).find('input[type=checkbox]').attr('checked',true);
					  			}
		 						      }
		 							  
		 						   });
		 						 });
	 					}
	 				 } 
	 			}else{	  
	 				$('table.classAndWeekWiseList').each(function(){
				      $(this).find('tr').each(function(){
					   $(this).find('td.holidayEntry').each(function(){
						   $(this).find('input[type=checkbox]').parent('span').addClass('checked');
				    	$(this).find('input[type=checkbox]').attr('checked',true);
					   
				   });
				 });
				});
			   }
   	  	 var s = $("input[type='checkbox'].SUNDAY");
		    if(s.length == s.filter(":checked").length){
		    	 $('input.allSun').parent('span').addClass('checked');
		    }
		  var m = $("input[type='checkbox'].MONDAY");
		    if(m.length == m.filter(":checked").length){
		    	 $('input.allMon').parent('span').addClass('checked');
		    }
		  var t = $("input[type='checkbox'].TUESDAY");
		    if(t.length == t.filter(":checked").length){
		    	 $('input.allTus').parent('span').addClass('checked');
		    }
		  var w = $("input[type='checkbox'].WEDNESDAY");
		    if(w.length == w.filter(":checked").length){
		    	 $('input.allWed').parent('span').addClass('checked');
		    }
		  var th = $("input[type='checkbox'].THURSDAY");
		    if(th.length == th.filter(":checked").length){
		    	 $('input.allThu').parent('span').addClass('checked');
		    }
		  var f = $("input[type='checkbox'].FRIDAY");
		    if(f.length == f.filter(":checked").length){
		    	 $('input.allFri').parent('span').addClass('checked');
		    }
		  var sa = $("input[type='checkbox'].SATURDAY");
		    if(sa.length == sa.filter(":checked").length){
		    	 $('input.allSat').parent('span').addClass('checked');
		    }
         }
                  
	});
}
 
 $('input[name="chkBoxSelectedIds"]').click(function(){
   var divClass=$(this).parents('label').text().trim(); 
	  if($(this).is(":checked")){
		$('tr.dayWiseList').each(function(){
		  $('tr#'+divClass).show();
		});
	  }else{
	  $('tr.dayWiseList').each(function(){
		  $('tr#'+divClass).hide();
		});
	  }
 });
 
 function  GetSchoolHolidayTimings(obj){
		if(obj=="SH"){
	       $('div#classWiseHolidays').hide();
	       $('div#schoolWiseHolidays').show();
	       }
	   else{
	        $('div#schoolWiseHolidays').hide();
	        $('div#classWiseHolidays').show();
	     }
 }
 $('div.make-switch').find("label[for='holidayStatus']").click(function(){	
	  if($(this).parent().hasClass('switch-on')==true){
	  	GetSchoolHolidayTimings("CH");
	  }
	  else{
	     GetSchoolHolidayTimings("SH");
	  }
 });
var allCheckBoxIds = [];

function checkAllSun() {
	if ($(".allSun").is(':checked')){
	    $("[name='sunWithClasses']").each(function(){
		   $(this).parent('span').addClass('checked');
		    $(this).attr("checked", "true");
		  });
	}
	else{
	 $("[name='sunWithClasses']").each(function(){
		   $(this).parent('span').removeClass('checked');
		    $(this).removeAttr("checked");
		 });
	}	
}
$("input[name=sunWithClasses]").click(function() {
	if ($("input[name=sunWithClasses]:unchecked").length > 0) {
	   $(".allSun").parent('span').removeClass("checked");
		$(".allSun").attr("checked", false);
	} else {
	    $(".allSun").parent('span').addClass("checked");
		$(".allSun").attr("checked", true);
	}
});
function checkAllMon() {
	if ($(".allMon").is(':checked')){
	    $("[name='monWithClasses']").each(function(){
		   $(this).parent('span').addClass('checked');
		    $(this).attr("checked", "true");
		 });
	}
	else{
	 $("[name='monWithClasses']").each(function(){
		   $(this).parent('span').removeClass('checked');
		    $(this).removeAttr("checked");
		 });
	}	
}
$("input[name=monWithClasses]").click(function() {
	if ($("input[name=monWithClasses]:unchecked").length > 0) {
	   $(".allMon").parent('span').removeClass("checked");
		$(".allMon").attr("checked", false);
	} else {
	    $(".allMon").parent('span').addClass("checked");
		$(".allMon").attr("checked", true);
		}
});
function checkAllTue() {
	if ($(".allTus").is(':checked')){
	    $("[name='tusWithClasses']").each(function(){
		   $(this).parent('span').addClass('checked');
		    $(this).attr("checked", "true");
		 });
	}
	else{
	 $("[name='tusWithClasses']").each(function(){
		   $(this).parent('span').removeClass('checked');
		    $(this).removeAttr("checked");
		 });
	}	
}
$("input[name=tusWithClasses]").click(function() {
	if ($("input[name=tusWithClasses]:unchecked").length > 0) {
	   $(".allTus").parent('span').removeClass("checked");
		$(".allTus").attr("checked", false);
	} else {
	    $(".allTus").parent('span').addClass("checked");
		$(".allTus").attr("checked", true);
	}
});

function checkAllWed() {
if ($(".allWed").is(':checked')){
	    $("[name='wedWithClasses']").each(function(){
		   $(this).parent('span').addClass('checked');
		    $(this).attr("checked", "true");
		});
	}
	else{
	 $("[name='wedWithClasses']").each(function(){
		   $(this).parent('span').removeClass('checked');
		    $(this).removeAttr("checked");
		});
	}	
}
$("input[name=wedWithClasses]").click(function() {
	if ($("input[name=wedWithClasses]:unchecked").length > 0) {
	   $(".allWed").parent('span').removeClass("checked");
		$(".allWed").attr("checked", false);
	} else {
	    $(".allWed").parent('span').addClass("checked");
		$(".allWed").attr("checked", true);
	}
});

function checkAllThu() {
	if ($(".allThu").is(':checked')){
	    $("[name='thuWithClasses']").each(function(){
		   $(this).parent('span').addClass('checked');
		    $(this).attr("checked", "true");
		});
	}
	else{
	 $("[name='thuWithClasses']").each(function(){
		   $(this).parent('span').removeClass('checked');
		    $(this).removeAttr("checked");
		  });
	}	
}
$("input[name=thuWithClasses]").click(function() {
	if ($("input[name=thuWithClasses]:unchecked").length > 0) {
	   $(".allThu").parent('span').removeClass("checked");
		$(".allThu").attr("checked", false);
	} else {
	    $(".allThu").parent('span').addClass("checked");
		$(".allThu").attr("checked", true);
		}
});

function checkAllFri() {
	if ($(".allFri").is(':checked')){
	    $("[name='friWithClasses']").each(function(){
		   $(this).parent('span').addClass('checked');
		    $(this).attr("checked", "true");
		});
	}
	else{
	 $("[name='friWithClasses']").each(function(){
		   $(this).parent('span').removeClass('checked');
		    $(this).removeAttr("checked");
		  });
	}	
}
$("input[name=friWithClasses]").click(function() {
	if ($("input[name=friWithClasses]:unchecked").length > 0) {
	   $(".allFri").parent('span').removeClass("checked");
		$(".allFri").attr("checked", false);
	} else {
	    $(".allFri").parent('span').addClass("checked");
		$(".allFri").attr("checked", true);
		}
});

function checkAllSat() {
	if ($(".allSat").is(':checked')){
	    $("[name='satWithClasses']").each(function(){
		   $(this).parent('span').addClass('checked');
		    $(this).attr("checked", "true");
		 });
	}
	else{
	 $("[name='satWithClasses']").each(function(){
		   $(this).parent('span').removeClass('checked');
		    $(this).removeAttr("checked");
		 });
	}	
}
$("input[name=satWithClasses]").click(function() {
	if ($("input[name=satWithClasses]:unchecked").length > 0) {
	   $(".allSat").parent('span').removeClass("checked");
		$(".allSat").attr("checked", false);
	} else {
	    $(".allSat").parent('span').addClass("checked");
		$(".allSat").attr("checked", true);
	}
});
</script>