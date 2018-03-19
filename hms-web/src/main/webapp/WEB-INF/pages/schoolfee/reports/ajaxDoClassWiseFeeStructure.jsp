<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet-body">
			<div class="tab-content">
				<%@ include file="/common/messages.jsp"%>
				<s:if test="%{classList != null && !classList.isEmpty()}">
					<s:form action="ajaxGenrateClassWiseFeeStructure" theme="simple"
						cssClass="form-horizontal" id="classWiseFeeStructure" method="post"
						onsubmit="javascript:return validateExcessAmtForm();"
						namespace="/reports">
						<s:hidden name="tempString" id="tempString"></s:hidden>
						<s:hidden name="anyId" id="anyId"></s:hidden>
						<div class="form-body">
							<div class="form-group">
								<div class="col-md-12">
									<label class="control-label">
										<span class="required">*</span>Available Classes :
									</label>
									<div class="checkbox-list">
										<label class="checkbox-inline">
											<input type="checkbox" name="" value=""
												onClick="checkAllClassesForReports()"
												class="checkbox allClasses" />
											All Classes
										</label>
									</div>
									<s:checkboxlist name="chkBoxSelectedIds" list="classList"
										label="Class and section" listKey="id" listValue="className"
										theme="ems"></s:checkboxlist>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-12">
									<label class="control-label">
										<span class="required">*</span>Available Terms :
									</label>
									<s:if test="%{schoolTermsList != null && !schoolTermsList.isEmpty()}">
										<div class="checkbox-list">
											<label class="checkbox-inline">
												<input type="checkbox" name="" value=""
													onClick="checkAllFeeTerms()" class="checkbox allTerms" />
												All Terms
											</label>
										</div>
										<s:checkboxlist name="schoolTermchkBoxSelectedIds"
											list="schoolTermsList" label="Term Name" listKey="id"
											listValue="termName" theme="ems"></s:checkboxlist>
										</s:if>
										<s:else>
											<div class="alert alert-info">
												You have not created any terms.
											</div>
										</s:else>
								</div>
							</div>
							<div class="form-actions fluid">
								<div class="col-md-offset-3 col-md-8">
									<s:submit type="submit small" value="Generate Excel"
										cssClass="btn blue" title="Generate Excel"
										cssStyle="cursor:pointer">
									</s:submit>
								</div>
							</div>
						</div>
					</s:form>
			</s:if>
			<s:else>
					<div class="alert alert-info">You have not created any classes.</div>
			</s:else>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
changePageTitle("Class Wise Fee Structure");
$("input:checkbox, input:radio").uniform();
function checkAllClassesForReports() {
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
	prepareClassIds();
}
$("input[name=chkBoxSelectedIds]").click(function() {
	if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
		$(".allClasses").parent('span').removeClass("checked");
		$(".allClasses").attr("checked", false);
	} else {
		$(".allClasses").parent('span').addClass("checked");
		$(".allClasses").attr("checked", true);
	}
	prepareClassIds();
});
function checkAllFeeTerms() {
	if ($(".allTerms").is(':checked')) {
		$("[name='schoolTermchkBoxSelectedIds']").each(function() {
			$(this).parent('span').addClass('checked');
			$(this).attr("checked", "true");
		});
	} else {
		$("[name='schoolTermchkBoxSelectedIds']").each(function() {
			$(this).parent('span').removeClass('checked');
			$(this).removeAttr("checked");
		});
	}
	prepareSchoolTermIds();
}
$("input[name=schoolTermchkBoxSelectedIds]").click(function() {
	if ($("input[name=schoolTermchkBoxSelectedIds]:unchecked").length > 0) {
		$(".allTerms").parent('span').removeClass("checked");
		$(".allTerms").attr("checked", false);
	} else {
		$(".allTerms").parent('span').addClass("checked");
		$(".allTerms").attr("checked", true);
	}
	prepareSchoolTermIds();
});
function prepareClassIds(){
	var classIdsLength = $("input[name='chkBoxSelectedIds']").length;
	if (classIdsLength > 0) {
		var classIds = [];
		classIds = '(';
		$("input[name=chkBoxSelectedIds]:checked").each(function() {
			classIds += $(this).val() + ', ';
		});
		classIds += '0)';
		$("#anyId").val(classIds);
	}
}
function prepareSchoolTermIds(){
	var schoolTermsIdsLength = $("input[name='schoolTermchkBoxSelectedIds']").length;
	if (schoolTermsIdsLength > 0) {
		var schoolTermsIds = [];
		schoolTermsIds = '(';
		$("input[name=schoolTermchkBoxSelectedIds]:checked").each(function() {
			schoolTermsIds += $(this).val() + ', ';
		});
		schoolTermsIds += '0)';
		$("#tempString").val(schoolTermsIds);
	}
}
function validateExcessAmtForm() {
	if ($('input[name="chkBoxSelectedIds"]:checked').length > 0 && $('input[name="schoolTermchkBoxSelectedIds"]:checked').length > 0) {
		return true;
	} else if($('input[name="chkBoxSelectedIds"]:checked').length == 0 && $('input[name="schoolTermchkBoxSelectedIds"]:checked').length == 0){
		alert("Please select at least one class and term.");
		return false;
	}else if($('input[name="chkBoxSelectedIds"]:checked').length == 0){
		alert("Please select at least one class.");
		return false;
	}else if($('input[name="schoolTermchkBoxSelectedIds"]:checked').length == 0){
		alert("Please select at least one term.");
		return false;
	}
}
</script>