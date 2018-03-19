<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet-body">
			<div class="tab-content">
				<%@ include file="/common/messages.jsp"%>
				<s:form action="ajaxGenrateStudentsExcessAmountReport"
					theme="simple" cssClass="form-horizontal" id="excessAmtReport"
					method="post" onsubmit="javascript:return validateExcessAmtForm();"
					namespace="/reports">
					<s:hidden id="classNameIds" name="selectedId" />
					<s:if
						test="%{(studyClassList != null && !studyClassList.isEmpty())}">
						<div class="form-body">
							<div class="form-group">
								<div class="col-md-12">
									<label class="control-label">
										<span class="required">*</span>Available Class & Sections :
									</label>
									<div class="checkbox-list">
										<label class="checkbox-inline">
											<input type="checkbox" name="" value=""
												onClick="checkAllClassesForReports()"
												class="checkbox allClasses" />
											All Classes
										</label>
									</div>
									<s:checkboxlist name="chkBoxSelectedIds" list="studyClassList"
										label="Class and section" listKey="id"
										listValue="classAndSection" theme="ems"></s:checkboxlist>
								</div>
							</div>
							<div class="form-actions fluid">
								<div class="col-md-offset-3 col-md-8">
									<s:submit type="submit small" value="Generate Pdf"
										cssClass="btn blue" title="generate report"
										cssStyle="cursor:pointer">
									</s:submit>
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
<script type="text/javascript">
$("input:checkbox, input:radio").uniform();
function checkAllClassesForReports() {
	if ($(".allClasses").is(':checked')){
	    $("[name='chkBoxSelectedIds']").each(function(){
		   $(this).parent('span').addClass('checked');
		    $(this).attr("checked", "true");
		});
	}
	else{
	 $("[name='chkBoxSelectedIds']").each(function(){
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
function validateExcessAmtForm(){
	if($('input[name="chkBoxSelectedIds"]:checked').length > 0){
		var classIds = $("input[name=chkBoxSelectedIds]:checked");
			var selectedClassIds = '';
			if (classIds.length > 0) {
				selectedClassIds = '(';
				for ( var i = 0; i < classIds.length; i++) {
					selectedClassIds += classIds[i].value + ', ';
				}
				selectedClassIds += '0)';
			}
			$("#classNameIds").val(selectedClassIds);
		return true;
	}else{
		alert("Please select at least one class.");
		return false;	
	}
}
</script>
