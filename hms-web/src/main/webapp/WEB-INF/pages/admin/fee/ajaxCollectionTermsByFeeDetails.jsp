<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{schoolTermsList != null && !schoolTermsList.isEmpty()}">
	<h4 class="form-section">
		Available Terms & Class Sections
	</h4>
	<div class="form-group">
		<div class="col-md-12">
			<div class="checkbox-list">
				<label class="checkbox-inline">
					<input type="checkbox" name="" value=""
						onClick="checkAllTermsForReports()" class="checkbox allTerms">
					All Terms
				</label>
			</div>
		</div>
		<div class="col-md-12">
			<s:iterator value="schoolTermsList">
				<div class="col-md-3">
					<s:if test='%{feeSettingId==1}'>
						<input type="checkbox" name="chkBoxSelectedIds"
							value='<s:property value="id"/>' class="checkbox">
						<s:property value="termName" /> (Non Term-Fee)
					</s:if>
					<s:if test='%{feeSettingId==2}'>
						<input type="checkbox" name="chkBoxSelectedIds"
							value='<s:property value="id"/>' class="checkbox">
						<s:property value="termName" /> (Term-Fee)
					</s:if>
					<s:if test='%{feeSettingId==3}'>
						<input type="checkbox" name="chkBoxSelectedIds"
							value='<s:property value="id"/>' class="checkbox">
						<s:property value="termName" /> (Transport-Fee)
					</s:if>
					<s:if test='%{feeSettingId==4}'>
						<input type="checkbox" name="chkBoxSelectedIds"
							value='<s:property value="id"/>' class="checkbox">
						<s:property value="termName" /> (Hostel-Fee)
					</s:if>
				</div>
			</s:iterator>
		</div>
	</div>
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
									<input type="checkbox" name="" value=""
										onClick="checkAllClasses()" class="checkbox allClasses">
									All Class & Sections
								</label>
							</div>
						</div>
					</s:if>
					<div class="col-md-12">
						<div class="checkbox-list">
							<s:checkboxlist name="chkBoxClassSelectedIds"
								list="studyClassList" listKey="id" listValue="classAndSection"
								theme="ems" cssClass="small" />
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
</s:if>
<s:else>
	<div class="alert alert-info">
		You have not created any terms.
	</div>
</s:else>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript">
 $("input:checkbox, input:radio").uniform();
function checkAllTermsForReports() {
	if ($(".allTerms").is(':checked')) {
		$("[name='chkBoxSelectedIds']:enabled").each(function() {
			$(this).parent('span').addClass('checked');
			$(this).attr("checked", "true");
			generateReportsWithTermIds();
		});
	} else {
		$("[name='chkBoxSelectedIds']:enabled").each(function() {
			$(this).parent('span').removeClass('checked');
			$(this).removeAttr("checked");
			generateReportsWithTermIds();
		});
	}
	
}
$("input[name=chkBoxSelectedIds]").click(function() {
	if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
		$(".allTerms").parent('span').removeClass("checked");
		$(".allTerms").attr("checked", false);
		generateReportsWithTermIds();
	} else {
		$(".allTerms").parent('span').addClass("checked");
		$(".allTerms").attr("checked", true);
		generateReportsWithTermIds();
	}
});

function checkAllClasses() {
	if ($(".allClasses").is(':checked')) {
		$("input[name='chkBoxClassSelectedIds']").parent().addClass('checked');
		$("input[name='chkBoxClassSelectedIds']").attr("checked", "true");
		generateReportsWithTermIds();
	} else {
		$("input[name='chkBoxClassSelectedIds']").removeAttr("checked");
		$("input[name='chkBoxClassSelectedIds']").parent().removeClass('checked');
		generateReportsWithTermIds();
	}
}
$("input[name=chkBoxClassSelectedIds]").click(function() {
	if ($("input[name=chkBoxClassSelectedIds]:unchecked").length > 0) {
		$(".allClasses").attr("checked", false);
		$(".allClasses").parent().removeClass('checked');
		generateReportsWithTermIds();
	} else {
		$(".allClasses").attr("checked", true);
		$(".allClasses").parent().addClass('checked');
		generateReportsWithTermIds();

	}
});
</script>
