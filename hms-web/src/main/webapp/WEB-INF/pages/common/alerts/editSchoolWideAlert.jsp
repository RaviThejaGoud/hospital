<%@ include file="/common/taglibs.jsp"%>
<td colspan="3">
	<s:form id="schoolWideAlerts" action="ajaxEditSchoolWideAlert"
		method="post" theme="css_xhtml">

		<s:hidden name="id" value="%{messages.id}"></s:hidden>
		<!--<div class="grid_5" style="text-align: left;">
			<h2>
				Edit School Wide Message
			</h2>
		</div>
		-->
		<div class="grid_10">
			<!--<br />
			<div class="grid_7" style="text-align: left;">

				<s:select name="messages.receiverAccountId" label="TO "
					cssClass="textfield required" required="true"
					cssStyle="width:191px;hight:30px;"
					list="#{'ALL':'ALL','PO':'Parents Only','SO':'Staff Only'}"></s:select>
			</div>

			<br />
			<br />
			-->
			<div class="grid_7">
				<s:textfield name="messages.title" id="title" label="Title"
					size="40" labelposition="top" cssClass="textsmall required"
					required="true" maxlength="80" requiredposition="first"></s:textfield>
			</div>
			<br />
			<br />
			<br />
			<div class="grid_7" style="text-align: left;">
				<sj:textarea name="messages.messageDescription"
					cssStyle="height: 95px;width : 83%" id="messageDescription"
					label="Message" cssClass="required" required="true" rows="3"
					cols="40"></sj:textarea>
			</div>
		</div>

		<div class="grid_10">
			<div class="grid_4" style="float: right;">
				<s:url id="doCancelLeave" action="ajaxCancelSchoolWideAlerts"
					includeParams="all"></s:url>
				<sj:a href="%{doCancelLeave}" cssClass="cancelButton"
					indicator="indicator" targets="schoolWideAlertsHome">Cancel</sj:a>
				<sj:submit   cssClass="submit small" value="Submit"
					formIds="schoolWideAlerts" indicator="indicator"
					targets="schoolWideAlertsHome"
					onClickTopics="formValidationForApprovalLeaves" />
			</div>
		</div>

	</s:form>
</td>
<script type="text/javascript">
changePageTitle("Edit School Wide Message");
$(document).ready(function() {
	$.subscribe('formValidationForSchoolAlerts', function(event, data) {
		if ($('#schoolWideAlerts').valid())
			return true;
		else
			return false;
	});

});
</script>

