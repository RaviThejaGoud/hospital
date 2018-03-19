<%@ include file="/common/taglibs.jsp"%>
<td colspan="7">
	<s:form action="ajaxEditSchoolWideMessages" theme="css_xhtml"
		id="formApprovalLeaves">
		 <s:hidden name="id" value="%{messages.id}"></s:hidden>
		<div class="grid_5" style="text-align: left;">
			<h2>
				Edit School Wide Message
			</h2>
		</div>
		<div class="grid_10">
			<br />
			<div class="grid_7" style="text-align: left;">

				<s:select name="messages.receiverAccountId" label="TO "
					cssClass="textfield required" required="true"
					cssStyle="width:191px;hight:30px;"
					list="#{'ALL':'ALL','PO':'Parents Only','SO':'Staff Only'}"></s:select>
			</div>

			<br />
			<br />
			<div class="grid_7">
				<s:textfield name="messages.title" id="title" label="Subject"
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
				<s:url id="doCancelLeave" action="ajaxCancelSchoolWideMessages"
					includeParams="all"></s:url>
				<sj:a href="%{doCancelLeave}" cssClass="cancelButton"
					indicator="indicator" targets="schoolWideMessagesHome">Cancel</sj:a>
				<sj:submit   cssClass="submit small" value="Submit"
					indicator="indicator" targets="schoolWideMessagesHome"
					onClickTopics="formValidationForApprovalLeaves" />
			</div>
		</div>
	</s:form>
</td>
<script type="text/javascript">
changePageTitle("Edit School Wide Message");
</script>
