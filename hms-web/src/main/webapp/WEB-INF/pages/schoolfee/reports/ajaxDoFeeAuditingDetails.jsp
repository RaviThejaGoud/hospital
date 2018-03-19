<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div id="paidAndUnpaid">
			<div class="portlet-body">
				<div id="allFeeReports">
				<s:if test="%{(studyClassList != null && !studyClassList.isEmpty())}">
					<s:if test="%{schoolTermsList != null && !schoolTermsList.isEmpty()}">
					
					<span id="startDateSpan" class="<s:property value='startDate'/>"></span>
					<span id="endDateSpan" class="<s:property value='endDate'/>"></span>
					
						<s:form action="ajaxDownloadAuditingDetailsForFee" theme="simple"
							namespace="/reports" 
							onsubmit="javascript:return reportsTypes();"
							cssClass="form-horizontal">
							<input type="hidden" name="pdfId" value="pdf" />
							<input type="hidden" name="selectedId" id="termNameIds" />
							<input type="hidden" name="queryString" id="queryString" value="todayReport"/>
							<input type="hidden" name="tempString" id="tempString" />
							<input type="hidden" name="tempId" id="tempId" />
							<div class="form-body">
								<div id="checkStaffOrNot">
									<s:if
										test="%{schoolTermsList != null && !schoolTermsList.isEmpty()}">
										<div class="form-group" id="classWiseStuDetailsPdfDetails">
											<label class="conLable col-md-1 control-label">
												<strong>Select Report: </strong>
											</label>
											<div class="radio-list">
												<label class="radio-inline">
													<input type="radio" name="SelectReportType"
														value="dailyCollectionReport"
														class="feeCollectionReportIds" checked="checked">
													Daily Collection Report
												</label>
												<label class="radio-inline">
													<input type="radio" name="SelectReportType"
														value="advancedCollectionReport"
														class="feeCollectionReportIds">
													Advanced Collection Report
												</label>
												<label class="radio-inline">
													<input type="radio" name="SelectReportType"
														value="outStandingCollectionReport"
														class="feeCollectionReportIds">
													OutStanding Collection Report
												</label>
											</div>
										</div>
										<div class="form-group" id="showHide">
											<label class="conLable col-md-1 control-label">
												<strong>Select : </strong>
											</label>
											<div class="radio-list">
												<label class="radio-inline">
													<input type="radio" name="SelectType" value="todayReport"
														class="todayReportClass"
														onclick="getSelectedValue(this.value);" checked="checked">
													Today Report
												</label>
												<label class="radio-inline">
													<input type="radio" name="SelectType"
														value="daysBetweenReprot" class="daysBetweenReportClass"
														onclick="getSelectedValue(this.value);">
													Days Between Report
												</label>

											</div>
										</div>
										<div class="form-group" id="onlyTransportFee">
											<label class="conLable col-md-1 control-label">
												<strong>Select : </strong>
											</label>
											<div class="radio-list">
												<label class="radio-inline">
													<input type="checkbox" name="SelectType"
														value="onlyTransport" class="onlyTransportClass">
													Only Transport Fee
												</label>
											</div>
											<div class="panel-body col-md-8">
												<div class="col-md-1">
													<span class="label label-danger">NOTE :</span>
												</div>
												<div class="col-md-9">
													<ul>
														<li>
															By checking above one you get only transport fee report.
														</li>
													</ul>
												</div>
											</div>
										</div>
										<div class="row" id="daysBetweenReprotDiv" style="display: none;">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label col-md-4">
														From Date :
													</label>
													<div class="col-md-4">
														<div 
															data-date-format="mm/dd/yyyy"
															class="input-group input-medium date date-picker">
															<input type="text" id="startDate" readonly="readonly"
																name="startDate" onchange="feeDatesValidation()"
																class="required form-control input-medium" />
															<span class="input-group-btn">
																<button type="button" class="btn default">
																	<i class="fa fa-calendar"></i>
																</button> </span>
														</div>
														<span class="help-block">(MM/DD/YYYY)</span>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label col-md-6">
														To Date :
													</label>
													<div class="col-md-4">
														<div 
															data-date-format="mm/dd/yyyy"
															class="input-group input-medium date date-picker">
															<input type="text" id="endDate" readonly="readonly"
																name="endDate" onchange="feeDatesValidation()"
																class="required form-control input-medium" />
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
										<div class="form-group">
											<label class="conLable col-md-3 control-label">
												<span class="required">*</span> Available Terms :
											</label>
											<div class="col-md-12">
												<div class="checkbox-list">
													<label class="checkbox-inline">
														<input type="checkbox" name="" value=""
															onClick="checkAllTermsForReports()"
															class="checkbox allTerms">
														All Terms
													</label>
												</div>

												<s:iterator value="schoolTermsList" status="itStatus">
													<div class="col-md-3">
													<s:if test='%{feeSettingId==2}'>
														<input type="checkbox" id='<s:property value="#itStatus.count"/>' name="chkBoxClassSelectedIds" value='<s:property value="id"/>' class="checkbox termFeeReports">
														<s:property value="termName"/> (Term-Fee)
													</s:if>
													<s:else>
													<input type="checkbox" id='0' name="chkBoxClassSelectedIds" disabled value='<s:property value="id"/>'  class="checkbox transportFeeReports">
														<s:property value="termName"/> (Transport-Fee)
													</s:else>
													</div>
													</s:iterator>
												<!--<s:checkboxlist name="chkBoxClassSelectedIds" list="schoolTermsList" listKey="id" listValue="termName"
														theme="ems" cssClass="small" />
												-->
											</div>
										</div>
									</s:if>
									<s:else>
										<div class="alert alert-info">
											You have not created any terms.
										</div>
									</s:else>
									<div id="overalAndToday">
										<div class="form-actions fluid">
											<div class="col-md-offset-2 col-md-9">
												<!--<s:submit type="submit" value="Generate Pdf" cssClass="btn blue PDF generateReport" title="generate report" />
												-->
												<s:submit type="submit" value="Generate Excel"
													cssClass="btn blue Excel generateReport"
													title="generate excel" />
											</div>
										</div>
									</div>
								</div>
							</div>
						</s:form>
					</s:if>
					<s:else>
						<div class="alert alert-info">
							Currently there are no Terms Fee and Transport Fee.
						</div>
					</s:else>
					</s:if>
					<s:else>
						<div class="alert alert-info">
							Currently there are no classes.
						</div>
					</s:else>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("input:checkbox, input:radio:not('.toggle')").uniform(); 
});
function getSelectedValue(value) {
	$("#queryString").val(value);	
		if (isNonEmpty(value)) {	
			 if (value == "todayReport" || value == "onlyTransport") {
				$("#daysBetweenReprotDiv").hide();
			} else{
				$("#daysBetweenReprotDiv").show();
			}
			
		}
	}
var startDate = $('span#startDateSpan').attr("class");
var endDate = $('span#endDateSpan').attr("class");
dateRestrictionWithinAcademicYear(startDate,endDate);
function reportsTypes() {
	if ($("input[name=chkBoxClassSelectedIds]:checked").length == 0) {
		
		if($("input.onlyTransportClass").is(":checked")){
		 if($('input.transportFeeReports:checked').length > 0){
			return true;
		 }
		 else{
			alert("Please select at least one transport term");
			return false;
		 }
		}else{
			alert("Please select at least one term");
			return false;
		}
		
	} 	
	else {
		generateReportsWithTermIds();
		if($("input.daysBetweenReportClass").is(":checked")){
			var startDate = $('#startDate').val();
			var endDate = $('#endDate').val();
			if(startDate == null || startDate =='' ){
				alert('Please select from date ');
				return false;
			}else if(endDate == null  || endDate ==''){
				alert('Please select to date ');
				return false;
			}
			else{
				return true;
			}
		}
	}
}
function checkAllTermsForReports() {
	if ($(".allTerms").is(':checked')) {
		$("[name='chkBoxClassSelectedIds']:enabled").each(function() {
			$(this).parent('span').addClass('checked');
			$(this).attr("checked", "true");
		});
	} else {
		$("[name='chkBoxClassSelectedIds']:enabled").each(function() {
			$(this).parent('span').removeClass('checked');
			$(this).removeAttr("checked");
		});
	}
}
$("input[name=chkBoxClassSelectedIds]").click(function() {
	if ($("input[name=chkBoxClassSelectedIds]:unchecked").length > 0) {
		$(".allTerms").parent('span').removeClass("checked");
		$(".allTerms").attr("checked", false);
	} else {
		$(".allTerms").parent('span').addClass("checked");
		$(".allTerms").attr("checked", true);
	}
});
function generateReportsWithTermIds() {
	if ($("input[name=chkBoxClassSelectedIds]:checked").length > 0) {
		var termIds = $("input[name=chkBoxClassSelectedIds]:checked");
		var selectedTermIds = '';
		if (termIds.length > 0) {
			selectedTermIds = '(';
			for ( var i = 0; i < termIds.length; i++) {
				selectedTermIds += termIds[i].value + ', ';
			}
			selectedTermIds += '0)';
		}
		if(termIds.length==1){
		 var selectedId=$("input[name=chkBoxClassSelectedIds]:checked").attr("id");
		 if(selectedId > 1){
		  $("#tempId").val(2);
		 }
		 else{
		 $("#tempId").val(1);
		 }
		}else{
		 $("#tempId").val(termIds.length);
		}
		if($('input.onlyTransportClass').is(":checked")){
			if($("input.transportFeeReports").length==$("input.transportFeeReports:checked").length){
				$("#tempId").val(3);
			}	
		}else{
			if($("input.termFeeReports").length==$("input.termFeeReports:checked").length){
				$("#tempId").val(3);
			}
		}
		
		$("#termNameIds").val(selectedTermIds);
		return true;
	} else if ($("input[name=chkBoxClassSelectedIds]:checked").length == 0) {
		alert("Please select at least one term");
		return false;
	} else {
		return true;
	}
}
function feeDatesValidation(){
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
$("input.feeCollectionReportIds").click(function(){
	if (isNonEmpty($(this).val())) {
	$("#tempString").val($(this).val());	
	if ($(this).val() == "dailyCollectionReport" ) {
		$("#showHide").show();
		$("input.todayReportClass").attr("checked","checked");
		$("input.todayReportClass").parent("span").addClass("checked");
		$("input.daysBetweenReportClass").removeAttr("checked");
		$("input.daysBetweenReportClass").parent("span").removeClass("checked");
		$("input.onlyTransportClass").parent("span").removeClass("checked");
		$("input.onlyTransportClass").removeAttr("checked");
		$("#queryString").val("todayReport");
		if($('input.todayReportClass').parent("span").hasClass("checked")){
			$("#daysBetweenReprotDiv").hide();			
		}else{
		   $("#daysBetweenReprotDiv").show();
		}
		$("#onlyTransportFee").hide();
		
	}else{
		$("input.daysBetweenReportClass").removeAttr("checked");
		$("input.daysBetweenReportClass").parent("span").removeClass("checked");
		$("#daysBetweenReprotDiv").hide();
		$("#showHide").hide();
		$("#onlyTransportFee").show();
	}

		 enableDisableRadio($("input.onlyTransportClass"));
	}
});
$("input.onlyTransportClass").click(function(){
	if ($('.onlyTransportClass:checked').length == $('.onlyTransportClass').length) {
		$("#queryString").val($(this).val());
	}else{
		$("#queryString").val("todayReport");
	}
	   enableDisableRadio($(this));
});

function enableDisableRadio(onlyTransObj){
$(".allTerms").parent('span').removeClass("checked");
		$(".allTerms").attr("checked", false);
$("[name='chkBoxClassSelectedIds']:enabled").each(function() {
			$(this).parent('span').removeClass('checked');
			$(this).removeAttr("checked");
		});
       if(onlyTransObj.is(":checked")){
		 $('input.transportFeeReports').each(function(){
		   $(this).removeAttr("disabled");
		   $(this).parents('div.disabled').removeClass("disabled");
		 });
		 $('input.termFeeReports').each(function(){
		   $(this).attr("disabled",true);
		 });
		}else{
		 $('input.transportFeeReports').each(function(){
		   $(this).attr("disabled",true);
		 });
		 $('input.termFeeReports').each(function(){
		   $(this).removeAttr("disabled");
		 });
		}
}
</script>