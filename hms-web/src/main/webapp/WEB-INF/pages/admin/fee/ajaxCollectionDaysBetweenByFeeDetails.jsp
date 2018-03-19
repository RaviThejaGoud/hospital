<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{schoolTermsList != null && !schoolTermsList.isEmpty()}">
<h4 class="form-section">Days Between Report </h4>

<span id="startDateSpan" class="<s:property value='startDate'/>"></span>
					<span id="endDateSpan" class="<s:property value='endDate'/>"></span>

			<div class="form-group">
				<label class="control-label col-md-2">
					<span class="required">*</span>From Date :
				</label>
				<div class="col-md-5">
					<div data-date-format="mm/dd/yyyy" 
						class="input-group input-medium date date-picker">
						<input type="text" id="startDate" readonly=""
							class="form-control required input-medium" onchange="dateValidation();"
							tabindex="3" name="startDate">
						<span class="input-group-btn">
							<button type="button" class="btn default">
								<i class="fa fa-calendar"></i>
							</button> </span>
					</div>
					<span class="help-block">(MM/DD/YYYY)</span>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-2">
					<span class="required">*</span>To Date :
				</label>
				<div class="col-md-5">
					<div data-date-format="mm/dd/yyyy"
						class="input-group input-medium date date-picker">
						<input type="text" id="endDate" readonly=""
							class="form-control required input-medium" onchange="dateValidation();"
							tabindex="4" name="endDate">
						<span class="input-group-btn">
							<button type="button" class="btn default">
								<i class="fa fa-calendar"></i>
							</button> </span>
					</div>
					<span class="help-block">(MM/DD/YYYY)</span>
				</div>
			</div>
</s:if>
<s:else>
	<div class="alert alert-info">
		You have not created any terms.
	</div>
</s:else>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript">
/* var startDate = $('span#startDateSpan').attr("class");
var endDate = $('span#endDateSpan').attr("class");
dateRestrictionWithinAcademicYear(startDate,endDate); */
FormComponents.init();
function dateValidation() {
	var startDateTrim = '';
	var endDateTrim = '';
	var startDate = $('#startDate').val();
	var endDate = $('#endDate').val();
	if (isNonEmpty(startDate) && isNonEmpty(endDate)) {
		startDate = Date.parse(startDate);
		endDate = Date.parse(endDate);
		if (endDate < startDate) {
			alert("Your end date is more than your start date.");
			$('#endDate').val("");
		}
	}
}
</script>
