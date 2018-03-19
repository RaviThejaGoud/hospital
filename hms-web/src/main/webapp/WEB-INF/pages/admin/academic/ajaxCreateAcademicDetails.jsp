<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<s:if test='%{#session.newAcademicYear == "C"}'>
		 			<a id="showNewSchool" class="warningSchoolResponse" href="#responsiveSchoolDates" data-toggle="modal"><jsp:include
					page="/WEB-INF/pages/admin/academic/ajaxShowSchoolStartDateEndDate.jsp" />
					</a>
		</s:if>
		<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrap-switch/static/js/bootstrap-switch.min.js"> </script>
		<s:if test="%{approvedLeavesList != null && !approvedLeavesList.isEmpty()}">
			<s:form action="ajaxCreateAcademicDetails" theme="simple" id="addSchoolSettings" method="post" cssClass="form-horizontal" namespace="/admin">
				<s:hidden name="anyTitle" id="jsonResponseData" value="{}"></s:hidden>
				<s:hidden name="anyId" id="selectedToggle" value=""></s:hidden>
				<s:hidden name="tempString" id="jsonWeekWithClassIdsData" value="{}"></s:hidden>
				<jsp:include page="/WEB-INF/pages/admin/academic/schoolSettings/addSetttings/ajaxDoAddSchoolGeneralSettings.jsp"></jsp:include>
					
				<jsp:include page="/WEB-INF/pages/admin/academic/schoolSettings/addSetttings/ajaxDoAddAttendanceSettings.jsp"></jsp:include>
					
				<jsp:include page="/WEB-INF/pages/admin/academic/schoolSettings/addSetttings/ajaxDoAddSchoolHolidayslSettings.jsp"></jsp:include>
				<div class="portlet box blue" style="margin-bottom:0px;border-width:0px 1px;">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-globe"></i>Mobile SMS Settings
					</div>
				</div>
				<div class="portlet-body">
					<div class="form-group form-horizontal">
					<label class="col-md-3 control-label">Communication Alerts : </label>
							<div class="radio-list">
								<label class="radio-inline">
									<s:if test='%{customer.mobileType == "B"}'>
										<input type="radio" value="B" name="customer.mobileType"  checked="checked">
									</s:if>
									<s:else>
										<input type="radio" value="B" name="customer.mobileType">
									</s:else>
										Both
								</label>
								<label class="radio-inline">
									<s:if test='%{customer.mobileType == "P"}'>
									 <input type="radio" checked="checked"
										value="P" name="customer.mobileType">
										</s:if>
										<s:else>
										 <input type="radio" value="P" name="customer.mobileType"  >
										</s:else>
										 Primary 
								</label>
								<label class="radio-inline"> 
								<s:if test='%{customer.mobileType == "S"}'>
									<input type="radio"	value="S" name="customer.mobileType" checked="checked">
									</s:if>
									<s:else>
										<input type="radio"	value="S" name="customer.mobileType">
									</s:else>
									 Secondary 
								</label>
						</div>
					</div>
					<div class="form-group">
					<label class="col-md-3 control-label">
						Mobile Number Update Alerts : </label>
						<div class="radio-list">
							<label class="checkbox-inline"> <span>
								<input type="checkbox" name="customer.sendSmsUpdatedMobile" id="sendSmsUpdatedMobile">
							</span>
							</label>
						</div>
					</div>
				</div>
			</div>
				<jsp:include page="/WEB-INF/pages/admin/academic/schoolSettings/addSetttings/ajaxDoAddSchoolTimeSettings.jsp"></jsp:include>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-6">
								Next Year School Open Date :
							</label>
							<div class="col-md-6">
								<div data-date-start-date="+0d" data-date-format="mm/dd/yyyy"
									class="input-group input-medium date date-picker">
									<input type="text" id="nextYrDate"
										name="academicYear.nextAcademicStartDate" readonly="readonly"
										class="form-control">
									<span class="input-group-btn">
										<button type="button" class="btn default">
											<i class="fa fa-calendar"></i>
										</button> </span>
								</div>
								<span class="help-block">(MM/DD/YYYY)</span>
							</div>
						</div>
					</div>
				</div>
				<div class="form-actions fluid" style="margin: 0px -15px -14px;">
					<div class="col-md-6">
						<div class="col-md-offset-6 col-md-6">
							<sj:submit   cssClass="submit small btn blue" value="Submit"
								indicator="indicator" targets="mainContentDiv"
								validate="true" formIds="addSchoolSettings"
								onBeforeTopics="addYearInformation" />
							<s:url id="doCancelStudent" action="ajaxAcademicSchoolSettings"
								includeParams="all" escapeAmp="false" namespace="/admin">
							</s:url>
							<sj:a href="%{doCancelStudent}"
								cssClass="cancelButton btn default" 
								targets="mainContentDiv" button="false">Cancel</sj:a>
						</div>
					</div>
				</div>
			</s:form>
		</s:if>
		<s:else>
		<div class="alert alert-info">
				No academic year found to create academic details. 
				<s:url
				 id="doViewAcademicPlanner" action="ajaxAcademicSchoolSettings"
				 includeParams="all" escapeAmp="false" namespace="/admin">
				</s:url>
			<sj:a href="%{doViewAcademicPlanner}" indicator="indicator"
				targets="mainContentDiv" button="false">Click here </sj:a>to go back.
		</div>
		</s:else>
 	</div>
 </div>
<script type="text/javascript">
changePageTitle('Add Academic Details');
$(document).ready(function() {
	var newYear = '<s:property value="%{#session.newAcademicYear}" />';
	if(newYear == "C"){
		 $("ul.page-sidebar-menu").click(function() {
			  window.location = $(this).find("a").attr("href");
			  return false;
		 });
		 $("li#schoolSettingsDiv").find("ul.sub-menu").each(function(){
		 $(this).find("li").find("a").unbind("click");
		 });
	}
/* var updatedMobileType = '<s:property value='customer.sendSmsUpdatedMobile'/>';
if ("Y" == updatedMobileType) {
	$('#sendSmsUpdatedMobile').parent('span').addClass('checked');
	$('#sendSmsUpdatedMobile').attr("checked", true);
} else {
	$('#sendSmsUpdatedMobile').parent('span').removeClass('checked');
	$('#sendSmsUpdatedMobile').attr("checked", false);
} */
});
$('#classWiseHolidays').hide();
$.subscribe('addYearInformation',function(event, data) {
	var manageAttendanceBy = "";
	if ($('input[name="chkBoxSelectedIds"]:checked').length == 0) {
		alert("!Oops select working days.");
		event.originalEvent.options.submit=false;
	} else {
		var jsonObj = new Array();
		var html = new Array();
		var jsonRes = '';
		$('table.classWiseList:visible').each(function() {
				$(this).find('tr.dayWiseList:visible').each(function() {
					$(this).find('td.timeEntry:visible').each(function() {
					html.push( {"CLASSID" : $(this).find('label.className:visible').attr('id'),
								  "DAYID" : $(this).find('label.dayName:visible').attr('id'),
									"SST" : $(this).find('input.SST:visible').val(),
								   "MBST" : $(this).find('input.MBST:visible').val(),
								   "MBET" : $(this).find('input.MBET:visible').val(),
								   "LBST" : $(this).find('input.LBST:visible').val(),
								   "LBET" : $(this).find('input.LBET:visible').val(),
								   "EBST" : $(this).find('input.EBST:visible').val(),
								   "EBET" : $(this).find('input.EBET:visible').val(),
								    "SET" : $(this).find('input.SET:visible').val()
								    });
								});
								jsonObj.push(html);
								if (jsonObj != '' && jsonObj != ',') {
									jsonRes = {
										"JSONOBJ" : jsonObj
									};
								}
							html = [];
						});
						});
		if (jsonRes == '') {
			alert(jsonRes);
			jsonRes = ( {});
		}
		$('input#jsonResponseData').val(JSON.stringify(jsonRes));
		//return true;
		if($('#classWiseHolidays').is(":visible")){	
		  	var jsonObj1 = new Array();
			var htmlArrey=new Array();
			var jsonResH='';
			var weekName='';
			var classSectionId='';
			var isHoliday=true;
			$('table.classAndWeekWiseList:visible').each(function(){
			$(this).find('tr.classAndWeek:visible').each(function(){
			   classSectionId= $(this).attr('id');
			   $(this).find('td.holidayEntry:visible').each(function(){
				   weekName=$(this).find('input:visible').attr("class");
				   isHoliday=$(this).find('input:visible').is(":checked");
					   if(!isHoliday){
						   weekName=  weekName+"_"+classSectionId;  
					       htmlArrey.push({"weekName " : weekName});
					   }
						
			 	});
				   jsonObj1.push(htmlArrey);
			 	 if(jsonObj1!='' && jsonObj1!=','){
			 		jsonResH={"JSONOBJH": jsonObj1};
			 	 }
			 	htmlArrey=[];
				});
				});
				if(jsonResH==''){
					jsonResH=({});
				}
			    $('input#jsonWeekWithClassIdsData').val(JSON.stringify(jsonResH));
			    //alert(JSON.stringify(jsonResH));
		}
	}
});

function loadYearRangeValue() {
	var academicYear = $('#academicId option:selected').text();
	var totalYear = '';
	if (isNonEmpty(academicYear)) {
		if (academicYear != '- Select Year -') {
			$('#startDate').val('');
			$('#endDate').val('');
			$('#nextYrDate').val('');
			var academicIds = academicYear.split("-");
			totalYear = academicIds[0] + ':' + (Number(academicIds[0]) + 1);
 		}
	}
}
</script>