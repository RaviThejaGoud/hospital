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
										<s:if test='%{academicYear.holidayStatus =="SH"}'>
											<input type="radio" class="toggle" checked="checked" id="holidayStatus">
										 <input type="hidden" name="academicYear.holidayStatus"  value="SH">
										</s:if>
										<s:elseif test='%{academicYear.holidayStatus =="CH"}'>
										 <input type="radio" class="toggle" id="holidayStatus">
											<input type="hidden" name="academicYear.holidayStatus" 
											 value="" onclick="javascript:GetSchoolHolidayTimings(this.value);">
										</s:elseif>
										<s:else>
											 <input type="radio" class="toggle" checked="checked" id="holidayStatus">
										  	 <input type="hidden" name="academicYear.holidayStatus"  value='SH'>
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
							<s:checkboxlist list="weekDayList" name="chkBoxSelectedIds" theme="ems" listKey="id" listValue="dayName"></s:checkboxlist>
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
				</table>
			</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
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
	alert('sdsd');
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
	alert('sdsd333');
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