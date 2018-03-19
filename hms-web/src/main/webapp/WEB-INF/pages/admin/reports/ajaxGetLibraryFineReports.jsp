<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet-body">
			<div id="staffsContent" class="tab-content">
			
					<span id="startDateSpan" class="<s:property value='startDate'/>"></span>
					<span id="endDateSpan" class="<s:property value='endDate'/>"></span>
			
				<s:form action="ajaxGenrateLibraryFineReports" theme="simple"
					id="dayBookReports" method="post" cssClass="form-horizontal"
					onsubmit="javascript:return dayBookReportsValidate();"
					namespace="/reports">
					<s:hidden name="anyId" cssClass="anyId" value=""></s:hidden>
					<div class="form-body">
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label col-md-3">
										<span class="required">*</span>From Date :
									</label>
									<div class="col-md-8">
										<div 
											data-date-format="mm/dd/yyyy"
											class="input-group input-medium date date-picker">
											<input type="text" id="startDate" name="startDate"
												readonly="readonly"  
												onchange="javascript:getStudents(0,this.value);"
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
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label col-md-2">
										<span class="required">*</span>To Date :
									</label>
									<div class="col-md-8">
										<div  
											data-date-format="mm/dd/yyyy"
											class="input-group input-medium date date-picker">
											<input type="text" id="endDate" name="endDate"
												readonly="readonly" 
												onchange="javascript:getStudents(0,this.value);"
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

						<div class="form-actions fluid">
							<div class="col-md-6">
								<div class="col-md-offset-3 col-md-9">
									<s:submit type="submit small" value="Generate Excel"
										cssClass="submitBt btn blue long" title="generate report"
										onclick="reportFormate()">
									</s:submit>
									<s:submit type="submit small" value="Generate Pdf"
										onclick="reportType()" cssClass="submitBt btn blue long"
										title="generate report">
									</s:submit>
													
													
													
								</div>
							</div>
						</div>
					</div>
				</s:form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript">
/* var startDate = $('span#startDateSpan').attr("class");
var endDate = $('span#endDateSpan').attr("class");
dateRestrictionWithinAcademicYear(startDate,endDate); */
FormComponents.init();

$(document).ready(function(){
changePageTitle("Library Fine Details");
});
function reportFormate() {
	$('.anyId').val('Excel');
}
function reportType() {
	$('.anyId').val('PDF');
}
function getStudents(event,data){
		var startDate = $('#startDate').val();
		var endDate = $('#endDate').val();
		if (isNonEmpty(startDate) || isNonEmpty(endDate)) {
			startDate = Date.parse(startDate);
			endDate = Date.parse(endDate);
			if (endDate < startDate) {
				alert("Your end date is more than your start date.");
				$('#endDate').val("");
			}
		}
	}
function dayBookReportsValidate(){
	var enddate=$('#endDate').val();
	var startdate=$('#startDate').val();
	if(startdate =='' && enddate ==''){
 		alert('Please select From and To date.');
  		return false;
	}
	else if(startdate ==''){
	 	alert('Please select from date.');
	 	return false;	
	}
	else if(enddate ==''){
		alert('Please select To date.');
		return false;
	}
	else{
		return true;
	}
}
</script>
