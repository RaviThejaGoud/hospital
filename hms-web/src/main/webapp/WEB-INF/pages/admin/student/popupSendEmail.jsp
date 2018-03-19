<%@ include file="/common/taglibs.jsp"%>
<td colspan="9">
<s:form action="ajaxSendEmailToParent" theme="css_xhtml"
	id="sendEmailToParent" namespace="/staff">

	<s:hidden name="tempString" />
	<s:hidden name="anyId" />
	<div style="background-color: #CCC;">
		<div>
			<div style="text-align: center;">
				<h5>
					Send Mail To Parent
				</h5>
			</div>
		</div>
		<div>
			<s:textfield id="subject" label="Subject" required="true" cssClass="required textfield small" name="subject" />
		</div>
		<div>
			<s:textarea rows="7" cols="40" label="Description" cssClass="textfield  required" name="description" />
		</div>

		<div class="grid_6">
			<div class="grid_4" style="text-align: left;">
			<s:url id="doCancelLeave" action="ajaxDoCancelSendMailToParent"
					includeParams="all" namespace="/staff"></s:url>
				<sj:a href="%{doCancelLeave}" cssClass="cancelButton" onCompleteTopics="doInitEditStudent"
					indicator="indicator" targets="editStudentForm%{anyId}">Cancel</sj:a>
				<sj:submit   cssClass="submit small" value="Submit" clearForm="true"
					indicator="indicator" targets="studentsContent" formIds="sendEmailToParent" 
					onClickTopics="formValidationForsendEmailToParent" />
			</div>
		</div>
	</div>
</s:form>
</td>
<script type="text/javascript">
$(document).ready(function() {
	var validator;
	$.subscribe('formValidationForsendEmailToParent', function(event, data) {
		if ($('#sendEmailToParent').valid())
			return true;
		else
			return false;
	});
});
</script>
