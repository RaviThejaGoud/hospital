<%@ include file="/common/taglibs.jsp"%>
<div class="portlet box blue"
	style="margin-bottom: 0px; border-width: 0px 1px;">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-globe"></i>Fee Settings
		</div>
	</div>
	<div class="portlet-body">
			
		<div id="staffAttBy">
			<div class="form-group">
				<label class="control-label col-md-3"><span class="required">*</span>Fee Module Usage By : </label>
				<div class="col-md-3">
					<s:if
						test="%{(academicYear.feeModuleUsegeBy == null) || (academicYear.feeModuleUsegeBy =='D')}">
						<div class="make-switch has-switch" data-id="W" data-value="D"
							style="width: 180px" data-off="warning" data-on="success"
							data-off-label="Web" data-on-label="Desktop">
							<input type="radio" class="toggle" id="feeModuleUsage"
								checked="checked"> <input type="hidden"
								name="academicYear.feeModuleUsegeBy" value="D">
						</div>
					</s:if>
					<s:else>
						<div class="make-switch has-switch" data-id="D" data-value="W"
							style="width: 180px" data-off="warning" data-on="success"
							data-off-label="Desktop" data-on-label="Web">
								<s:if test='%{academicYear.feeModuleUsegeBy=="W"}'>
									<input type="radio" class="toggle" id="feeModuleUsage"
										disabled="disabled" checked="checked">
								</s:if>
								<s:else>
									<input type="radio" class="toggle" id="feeModuleUsage"
										disabled="disabled">
								</s:else>
							<input type="hidden" name="academicYear.feeModuleUsegeBy"
								value="<s:property value="academicYear.feeModuleUsegeBy"/>">
						</div>
					</s:else>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3"><span class="required">*</span>Generate fee receipt number by : </label>
				<div class="col-md-3">
					<s:if
						test="%{(academicYear.receiptGenerationType == null) || (academicYear.receiptGenerationType =='')}">
						<div class="make-switch has-switch" data-id="A" data-value="M"
							style="width: 180px" data-off="warning" data-on="success"
							data-off-label="Manual" data-on-label="Automatic">
							<input type="radio" class="toggle" id="feeReceiptMode"
								checked="checked"> <input type="hidden"
								name="academicYear.receiptGenerationType" value="A">
						</div>
					</s:if>
					<s:else>
						<div class="make-switch has-switch" data-id="M" data-value="A"
							style="width: 180px" data-off="warning" data-on="success"
							data-off-label="Automatic" data-on-label="Manual">
							<s:if test="%{tempId > 0}">
								<s:if test='%{academicYear.receiptGenerationType=="M"}'>
									<input type="radio" class="toggle" id="feeReceiptMode"
										disabled="disabled" checked="checked">
								</s:if>
								<s:else>
									<input type="radio" class="toggle" id="feeReceiptMode"
										disabled="disabled">
								</s:else>
							</s:if>
							<s:else>
							<s:if test='%{academicYear.receiptGenerationType=="M"}'>
									<input type="radio" class="toggle" id="feeReceiptMode" checked="checked">
								</s:if>
								<s:else>
									<input type="radio" class="toggle" id="feeReceiptMode" />
								</s:else>
							</s:else>
							<input type="hidden" name="academicYear.receiptGenerationType"
								value="<s:property value="academicYear.receiptGenerationType"/>">
						</div>
					</s:else>
				</div>
			</div>
		</div>
		
	</div>
</div>

<script type="text/javascript">
$(document).ready(function() {
	$('#minAttendance').numeric();
 });
 
$('div.make-switch').find("label[for='manageAttendanceBy']").click(function(){	
	if($(this).parent().hasClass('switch-on')==true){
		GetAttendanceType("M");
		alert("M")
	}
	else{
	alert("D")
		GetAttendanceType("D");
	}
}); 
</script>