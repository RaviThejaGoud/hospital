<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{schoolHolidays.id != 0}">
	<div data-width="760" class="modal fade modal-overflow in"
		id="responsive"
		style="display: block; width: 760px; margin-left: -379px; margin-top: 200px;"
		aria-hidden="false">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true"></button>
			<h4 class="modal-title">
				Update School Holidays
			</h4>
		</div>
	<div class="modal-body">
</s:if>
<span id="startDateSpan" class="<s:property value='startDate'/>"></span>
<span id="endDateSpan" class="<s:property value='endDate'/>"></span>
<span id="toDateSpan" class="<s:property value='toDate'/>"></span>
<s:form action="ajaxCreateSchoolHolidays" theme="simple"
	id="updateSchoolHolidays1" method="post" cssClass="form-horizontal"
	namespace="/admin">
	<s:hidden name="academicYearId" value="%{#session.academicYear}"></s:hidden>
	<s:hidden name="anyId" value="%{schoolHolidays.id}"></s:hidden>
	<s:hidden name="tempString" value="%{schoolHolidays.description}"></s:hidden>
	<div class="spaceDiv"></div>
	<p>
		<span class="label label-danger"> NOTE : </span>&nbsp;
		<s:if test="%{schoolHolidays.id != 0}">
				You can update school holidays within the academic year.
			</s:if>
			<s:else>
				You can create the holidays within the academic year.
			</s:else>
	</p>
	<div class="form-body">
	
	<s:if test="%{(studyClassList != null && !studyClassList.isEmpty())}">
			<div class="form-group">
				<label class="control-label col-md-2">
					<span class="required">*</span>Select ClassName :
				</label>
				<div class="col-md-3">
				<s:if test='%{selectedId=="0" || selectedId=="" || selectedId==null}'>
					<s:select list="studyClassList" listKey="id"
						listValue="classAndSection" id="dropDownClassId" headerKey="all" headerValue="All"
						cssClass="form-control input-medium required"
						name="selectedId">
					</s:select>
				</s:if>
					<s:else>
						<input type="hidden" name="selectedId" value="<s:property value='selectedId'/>"/>
						<s:select list="studyClassList" listKey="id"
							listValue="classAndSection" id="dropDownClassId" disabled="true"
							cssClass="form-control input-medium required" onchange="javascript:getHolidaysForThisClass(this.value);"
							name="selectedId">
						</s:select>
					</s:else>
				</div>
			</div>
	 </s:if>
	 <div>&nbsp;</div>
							 
		<div class="form-group">
			<label class="control-label col-md-2">
				<span class="required">*</span>Holiday Description :
			</label>
			<div class="col-md-3">
				<sj:textfield name="schoolHolidays.description" id="holidayName"
					labelposition="top" cssClass="form-control input-medium required"
					maxlength="40"></sj:textfield>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-2">
			<span class="required">*</span>Start Date :
			</label>
			<div class="col-md-5">
				<div data-date-format="mm/dd/yyyy"
					class="input-group input-medium date date-picker">
					<input type="text" id="startDate" readonly=""
						class="required form-control"
						onchange="javascript:verifyDate(this.value);"
						value='<s:property value="schoolHolidays.startDateStr"/>'
						name="schoolHolidays.startDate" />
					<span class="dateInput input-group-btn">
						<button type="button" class="btn default">
							<i class="fa fa-calendar"></i>
						</button> </span>
				</div>
				<span class="help-block">(MM/DD/YYYY)</span>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-2">
			<span class="required">*</span>End Date :
				
			</label>
			<div class="col-md-5">
				<div data-date-format="mm/dd/yyyy"
					class="input-group input-medium date date-picker">
					<input type="text" id="endDate" readonly=""
						class="required form-control"
						onchange="javascript:verifyDate(this.value);"
						value='<s:property value="schoolHolidays.endDateStr"/>'
						name="schoolHolidays.endDate" />
					<span class="dateInput input-group-btn">
						<button type="button" class="btn default">
							<i class="fa fa-calendar"></i>
						</button> </span>
				</div>
				<span class="help-block">(MM/DD/YYYY)</span>
			</div>
		</div>
		<div class="form-actions fluid">
			<div class="col-md-offset-2 col-md-9">
				<sj:submit cssClass="submitBt btn blue" value="Submit"
					targets="mainContentDiv" validate="true"
					onBeforeTopics="formValidationHoliday"
					formIds="updateSchoolHolidays1" />
				<s:if test="%{schoolHolidays.id != 0}">
					<button type="button" data-dismiss="modal" class="btn default">
						Cancel
					</button>
				</s:if>
				<s:else>
					<s:url id="doCancelHoliday" action="ajaxViewSchoolSettingsHolidays"
						includeParams="all" escapeAmp="false" namespace="/admin">
						<s:param name="academicYearId" value="%{#session.academicYear}" />
					</s:url>
					<sj:a href="%{doCancelHoliday}" cssClass="btn default"
						indicator="indicator" targets="mainContentDiv">Cancel</sj:a>
				</s:else>
			</div>
		</div>
	</div>
</s:form>
<s:if test="%{schoolHolidays.id != 0}">
	</div>
	</div>
</s:if>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript">
changePageTitle('Add Holidays');
$(document).ready( function() {
			var startDate = $('span#startDateSpan').attr("class");
			var endDate = $('span#endDateSpan').attr("class");
			dateRestrictionWithinAcademicYear(startDate,endDate);
	        FormComponents.init();  
			/* if (isNonEmpty(startDate) && isNonEmpty(endDate)
					&& isNonEmpty(toDayDate)) {
				if (Date.parse(toDayDate) >= Date.parse(startDate)
						&& Date.parse(toDayDate) <= Date.parse(endDate)) {
					$('#startDate').datepicker("option", 'minDate', toDayDate);
					$('#startDate').datepicker("option", 'maxDate', endDate);
					$('#endDate').datepicker("option", 'minDate', toDayDate);
					$('#endDate').datepicker("option", 'maxDate', endDate);
				} else if (Date.parse(toDayDate) < Date.parse(startDate)) {
					$('#startDate').datepicker("option", 'minDate', startDate);
					$('#startDate').datepicker("option", 'maxDate', endDate);
					$('#endDate').datepicker("option", 'minDate', startDate);
					$('#endDate').datepicker("option", 'maxDate', endDate);
				} else if (Date.parse(toDayDate) > Date.parse(endDate)) {
					$("#startDate").datepicker('disable');
					$("#endDate").datepicker('disable');
				}
					
			} */
		});
function verifyDate(date) {
	var startDate = $("#startDate").val();
	var endDate = $("#endDate").val();
	if (isNonEmpty(startDate) && isNonEmpty(endDate)) {
		if (Date.parse(endDate) < Date.parse(startDate)) {
			alert("To Date should be greater than or equal to From Date.");
			$('#endDate').val("");
		}
	}
}

$.subscribe('formValidationHoliday', function(event, data) {
		if ($('#updateSchoolHolidays1').valid()){
		$('button.close').click();
		} else
			 event.originalEvent.options.submit=false;	
}); 
</script>