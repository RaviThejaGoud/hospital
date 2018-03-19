<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div id="paidAndUnpaid">
			<div class="portlet-body">
				<div id="allFeeReports">
						<s:form action="ajaxGenerateStudentConsolidatedFeeDetails" theme="simple" namespace="/reports" 
							onsubmit="javascript:return reportsTypes();" cssClass="form-horizontal">
							<input type="hidden" name="anyId" id="classNameIds" />
							<s:hidden name="wishTitle"  id="booleanValue" value="N" />
							<s:if test="%{(studyClassList != null && !studyClassList.isEmpty())}">
								<div class="form-body">
									<div id="checkStaffOrNot">
										
										<div class="form-group">
											<div class="col-md-12">
												<s:if test="%{(studyClassList != null && !studyClassList.isEmpty())}">
													<div class="form-group">
														<label class="conLable col-md-3 control-label">
															<span class="required">*</span> Class With Students :
														</label>
														<s:if test='%{studyClassList.size >1}'>
															<div class="col-md-12">
																<div class="checkbox-list">
																	<label class="checkbox-inline">
																		<input type="checkbox" name="" value="" onClick="checkAllClasses()" class="checkbox allClasses">
																		Available Class & Sections
																	</label>
																</div>
															</div>
														</s:if>
														<div class="col-md-12">
															<div class="checkbox-list">
																<s:checkboxlist name="chkBoxSelectedIds" list="studyClassList" listKey="id"
																	listValue="classAndSection" theme="ems" cssClass="small" />
															</div>
														</div>
													</div>
												</s:if>
												<s:else>
													<div class="alert alert-info">
														Currently there are no classes.
													</div>
												</s:else>
											</div>
										</div>
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label class="control-label col-md-6">
														Do you want to show inactive student's :
													</label>
													<div class="col-md-5">
														<p class="form-control-static"><s:checkbox name="wishTitle1" id="subjVal"/></p>
													</div>
												</div>
											</div>
											<div class="col-md-5">
												&nbsp;
											</div>
										</div>
										
										<div id="overalAndToday">
											<div class="form-actions fluid">
												<div class="col-md-offset-2 col-md-9">
													<s:submit type="submit" value="Generate Excel" cssClass="btn blue Excel generateReport"
														title="generate excel" />
												</div>
											</div>
										</div>
									</div>
								</div>
							</s:if>
							<s:else>
								<div class="alert alert-info">
									Currently there are no classes.
								</div>
							</s:else>
							
						</s:form>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Student Consolidated Fee Details");
	$("input:checkbox, input:radio").uniform();
	
	var studentInactive = $('#booleanValue').val();
	if(studentInactive == "Y"){
		$('input#subjVal').attr('checked',true);
		$('input#subjVal').parent('span').addClass('checked');
	}else
		$("#subjVal").removeAttr("checked");
});

function reportsTypes(queryString) {
	if ($("input[name=chkBoxSelectedIds]:checked").length == 0) {
		alert("Please select at least one class");
		return false;
	} 	
	else {
		generateReportsWithClassIds();
		return true;
	}
}

function checkAllClasses() {
	if ($(".allClasses").is(':checked')) {
		$("[name='chkBoxSelectedIds']").each(function() {
			$(this).parent('span').addClass('checked');
			$(this).attr("checked", "true");
		});
	} else {
		$("[name='chkBoxSelectedIds']").each(function() {
			$(this).parent('span').removeClass('checked');
			$(this).removeAttr("checked");
		});
	}
}
$("input[name=chkBoxSelectedIds]").click(function() {
	if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
		$(".allClasses").parent('span').removeClass("checked");
		$(".allClasses").attr("checked", false);
	} else {
		$(".allClasses").parent('span').addClass("checked");
		$(".allClasses").attr("checked", true);
	}
});
function generateReportsWithClassIds() {
	if ($("input[name=chkBoxSelectedIds]:checked").length > 0) {
		
		var classIds = $("input[name=chkBoxSelectedIds]:checked");
		if (classIds.length > 0) {
			var selectedClassIds  = '(';
			for ( var i = 0; i < classIds.length; i++) {
				selectedClassIds += classIds[i].value + ', ';
			}
			selectedClassIds += '0)';
		}
		$("#classNameIds").val(selectedClassIds);
		return true;
	}
}
$("input[name='wishTitle1']").click(function(){
    if($(this).prop("checked") == true){
        $('#booleanValue').val('Y');
    }
    else if($(this).prop("checked") == false){
        $('#booleanValue').val('N');
    }
});
</script>