<%@ include file="/common/taglibs.jsp"%>
<td colspan="6" style="background-color: #CCC;">
<s:if test="%{viewStudentPersonAccountDetails != null}">
	<s:form action="ajaxSendExamAlertMsgToParent" theme="css_xhtml"
		id="formExamMsgAlert">
		<s:hidden name="viewStudentPersonAccountDetails.parentId" />
		<s:hidden name="viewStudentPersonAccountDetails.username" />
		<div class="grid_6">
			<div class="grid_5" style="text-align: left;">
				<h2>
					Send Exam Alert To Parent
				</h2>
			</div>
		</div>
		<div class="grid_10">
			<div class="grid_4" >
				Student Id:
			</div>
			<div class="grid_4">
				<s:property value="viewStudentPersonAccountDetails.rollNumber" />
			</div>
			<br />
			<br />
			<div class="grid_4" >
				Student Name:
			</div>
			<div class="grid_4">
				<s:property value="viewStudentPersonAccountDetails.personFullName" />
			</div>
			<br />
			<br />
			<div class="grid_4" >
				Parent Name:
			</div>
			<div class="grid_4">
				<s:property value="viewStudentPersonAccountDetails.fatherName" />
			</div>
			<br />
			<br />
			<div class="grid_4" >
				Parent Email:
			</div>
			<div class="grid_4">
				<s:property value="viewStudentPersonAccountDetails.parentEmail" />
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
					required="true" rows="5" cols="40"></sj:textarea>
			</div>
		</div>

		<div class="grid_6">
			<div class="grid_4" style="text-align: left;">
				<sj:submit   cssClass="submit small" value="Submit"
					indicator="indicator" targets="studentExamMessageContent"
					onClickTopics="formCalidationForExamAlert" />

				<s:url id="doCancelLeave" action="ajaxDoSendMyMessagesToParent"
					includeParams="all"></s:url>
				<sj:a href="%{doCancelLeave}" cssClass="cancelButton"
					indicator="indicator" targets="myMessageContent">Cancel</sj:a>
			</div>
		</div>
	</s:form>
	</s:if>
	<s:else>
		currently there are no student.
	</s:else>
</td>
<script type="text/javascript">
	$(document).ready(function() {
		var validator;
		$.subscribe('formCalidationForExamAlert', function(event, data) {
			if ($('#formExamMsgAlert').valid())
				return true;
			else
				return false;
		});
	});
</script>
