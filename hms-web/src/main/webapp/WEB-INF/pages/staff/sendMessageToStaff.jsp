<%@ include file="/common/taglibs.jsp"%>
<td colspan="6" style="background-color: #CCC;">
<s:if test="%{viewStaffPersonAccountDetails != null}">
	<s:form action="ajaxStaffSendMessageToStaff" theme="css_xhtml"
		id="formApprovalLeaves">
		<s:hidden name="viewStaffPersonAccountDetails.accountId" />
		<s:hidden name="viewStaffPersonAccountDetails.username" />
		<div class="grid_6">
			<div class="grid_5" style="text-align: left;">
				<h2>
					Send Message To Staff
				</h2>
			</div>
		</div>
		<div class="grid_10">
			<div class="grid_4" >
				Staff Id:
			</div>
			<div class="grid_4">
				<s:property value="viewStaffPersonAccountDetails.username" />
			</div>
			<br />
			<br />
			<div class="grid_4" >
				Staff Name:
			</div>
			<div class="grid_4">
				<s:property value="viewStaffPersonAccountDetails.personFullName" />
			</div>
			<br />
			<br />
			
			<div class="grid_4" >
				Staff Role:
			</div>
			<div class="grid_4">
				<s:property value="viewStaffPersonAccountDetails.roleDescription" />
			</div>
			<br />
			<br />
			
			<div class="grid_4">
				<s:textfield name="messages.title" id="title" label="Title"
					size="25" labelposition="top" cssClass="small required"
					required="true" maxlength="80" requiredposition="first"></s:textfield>
			</div>
			<br />
			<br />
			<br />
			<div class="grid_4" style="text-align: left;">
				<sj:textarea name="messages.messageDescription" cssStyle="height: 95px;width : 120%"
					id="messageDescription" label="Comments" cssClass="required"
					required="true" rows="3" cols="40"></sj:textarea>
			</div>
		</div>

		<div class="grid_6">
			<div class="grid_4" style="text-align: left;">
				<sj:submit   cssClass="submit small" value="Submit"
					indicator="indicator" targets="myInboxHome"
					onClickTopics="formValidationForApprovalLeaves" />

				<s:url id="doCancelLeave" action="ajaxGetStaffList"
					includeParams="all"></s:url>
				<sj:a href="%{doCancelLeave}" cssClass="cancelButton"
					indicator="indicator" targets="selectToMessages">Cancel</sj:a>
			</div>
		</div>
	</s:form>
	</s:if>
	<s:else>
		currently there are no Staff.
	</s:else>
</td>
<script type="text/javascript">
	$(document).ready(function() {
		var validator;
		$.subscribe('formValidationForApprovalLeaves', function(event, data) {
			if ($('#formApprovalLeaves').valid())
				return true;
			else
				return false;
		});
	});
</script>
