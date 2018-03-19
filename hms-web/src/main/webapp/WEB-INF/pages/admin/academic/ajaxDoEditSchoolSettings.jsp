<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<%@ include file="/common/messages.jsp"%>
		<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrap-switch/static/js/bootstrap-switch.min.js"></script>
		
		<s:form id="addUpdateSchoolSettings" action="ajaxDoUpdateSchoolSettings" theme="simple" method="post"
			cssClass="form-horizontal" namespace="/admin">
			<s:hidden name="anyTitle" id="jsonResponseData" value="{}"></s:hidden>
			<s:hidden name="anyId" id="selectedToggle" value=""></s:hidden>
			<s:hidden name="academicYearId" id="academicYearId" value="%{academicYear.id}"></s:hidden>
			<s:hidden name="tempString" id="jsonWeekWithClassIdsData" value="{}"></s:hidden>
			<!-- <input type="hidden" name="academicYear.holidayStatus"  value='SH'> -->
			
			<jsp:include page="/WEB-INF/pages/admin/academic/schoolSettings/ajaxEditSchoolGeneralSettings.jsp"></jsp:include>
			
			<jsp:include page="/WEB-INF/pages/admin/academic/schoolSettings/ajaxEditAttendanceSettings.jsp"></jsp:include>
			<jsp:include page="/WEB-INF/pages/admin/academic/schoolSettings/ajaxEditFeeSettings.jsp"></jsp:include>
			<jsp:include page="/WEB-INF/pages/admin/academic/schoolSettings/ajaxEditSchoolHolidayslSettings.jsp"></jsp:include>
			
			<div class="portlet box blue" style="margin-bottom:0px;border-width:0px 1px;">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-globe"></i>Mobile SMS Settings
					</div>
				</div>
				<div class="portlet-body">
					<div class="form-group form-horizontal">
					<label class="col-md-3 control-label">
						Communication Alerts : </label>
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
									 	<input type="radio" checked="checked" value="P" name="customer.mobileType">
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
			<jsp:include page="/WEB-INF/pages/admin/academic/schoolSettings/ajaxEditSchoolTimeSettings.jsp"></jsp:include>
			
			<div class="spaceDiv"></div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-6">
							Next Year School Open Date :
						</label>
						<div class="col-md-4">
							<div data-date-start-date="+0d" data-date-format="mm/dd/yyyy"
								class="input-group input-medium date date-picker">
								<input type="text" id="nextYrDate" readonly="readonly" value='<s:property value="academicYear.nextAcademicStartDateStr"/>'
									class="form-control" name="academicYear.nextAcademicStartDate" onchange="nextYrDateDiff(this.value)">
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
				<div class="col-md-offset-3 col-md-9">
					<sj:submit   cssClass="submitBt btn blue" value="Submit"
						targets="mainContentDiv" validate="true"
						formIds="addUpdateSchoolSettings" indicator="indicator"
						onBeforeTopics="editSchoolSettingsInformation" />
					<a href="" target="_new" class="acdId btn green"
						onclick="javascript: downloadAcademicPlanSettings();"> <img>Generate Pdf </a>
					<s:url id="doCancelStudent" action="ajaxAcademicSchoolSettings"
						includeParams="all" escapeAmp="false" namespace="/admin">
					</s:url>
					<sj:a href="%{doCancelStudent}" cssClass="submitBt btn default"
						targets="mainContentDiv">Cancel</sj:a>
				</div>
			</div>
		</s:form>
	</div>
</div>
	
<script type="text/javascript">
	changePageTitle('School Settings');
	$(document).ready(function() {
	  FormComponents.init();
		FormAdvanced.init();
		$("input:checkbox, input:radio:not('.toggle')").uniform();
		$('#minAttendance').numeric();
		//generateNextYearDateRange();
		$.destroyTopic('editSchoolSettingsInformation');
		var updatedMobileType = '<s:property value='customer.sendSmsUpdatedMobile'/>';
		if ("Y" == updatedMobileType) {
			$('#sendSmsUpdatedMobile').parent('span').addClass('checked');
			$('#sendSmsUpdatedMobile').attr("checked", true);
		} else {
			$('#sendSmsUpdatedMobile').parent('span').removeClass('checked');
			$('#sendSmsUpdatedMobile').attr("checked", false);
		} 
	 });
	$.subscribe('editSchoolSettingsInformation', function(event, data) {
		if($('#schoolWiseHolidays').is(":visible")){
	   		if($('input[name="chkBoxSelectedIds"]:checked').length ==0){
		      	alert("!Oops select working days.");
				event.originalEvent.options.submit=false;
		   }
		}
		//else{
			    var jsonObj = new Array();
				var html=new Array();
				var jsonRes='';
				$('table.classWiseList:visible').each(function(){
				$(this).find('tr.dayWiseList:visible').each(function(){
				   $(this).find('td.timeEntry:visible').each(function(){
						  html.push({"CLASSID" : $(this).find('label.className:visible').attr('id'),
						 				"DAYID" : $(this).find('label.dayName:visible').attr('id'),
						  				"SST" : $(this).find('input.SST:visible').val(),
										"MBST" : $(this).find('input.MBST:visible').val(),
										"MBET" :$(this).find('input.MBET:visible').val(),
									    "LBST" : $(this).find('input.LBST:visible').val(),
									    "LBET" : $(this).find('input.LBET:visible').val(),
									    "EBST" : $(this).find('input.EBST:visible').val(),
									    "EBET" : $(this).find('input.EBET:visible').val(),
									    "SET" : $(this).find('input.SET:visible').val()
							
				      });
				 	});
				 	 jsonObj.push(html);
				 	 if(jsonObj!='' && jsonObj!=','){
				 	   jsonRes={"JSONOBJ": jsonObj};
				 	 }
				 	 html=[];
					});
					});
					if(jsonRes==''){
					 jsonRes=({});
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
				return true;
		    //}
	});  
	
	/* function generateNextYearDateRange() {
		var endDate = $('#endDate').val();
		if (isNonEmpty(endDate)) {
			endDate = new Date(endDate);
			endDate.setDate(endDate.getDate() + 1);
			$('#nextYrDate').datepicker("option", 'minDate', endDate);
		}
	} */
	function nextYrDateDiff(nextYeardate) {
		var schoolEndDate = $('#endDate').val();
		if(isNonEmpty(schoolEndDate) && nextYeardate != '') {
			schoolEndDate = Date.parse(schoolEndDate);
			nextYeardate = Date.parse(nextYeardate);
			//alert(endDate+'---'+nextYeardate);
			if(schoolEndDate>nextYeardate){
				alert('Next year school open date  must be greater than the school end date.');
				$('#nextYrDate').val('');
			}
		}
	}

	function downloadAcademicPlanSettings(){
		var acedmicYearId = $('#academicId').val();
		var selectedId=  $('input#selectedToggle').val(); 
		var dayTimings = $('input[name="dayTimings"]:checked').val();
		var mainimgurl = jQuery.url.getChatURL("/admin/printAcademicPlanSettings.do?academicYearId=" + acedmicYearId +"&selectedId="+ selectedId+"&tempString="+dayTimings);
		$('.acdId').attr("href", mainimgurl);
	}
	 
</script>