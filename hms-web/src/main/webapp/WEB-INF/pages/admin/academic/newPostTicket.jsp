<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/common/textcounter.js"></script>
<jsp:include page="/common/messages.jsp"></jsp:include>
<s:form action="ajaxAddSupportTicket" theme="css_xhtml" id="addPostTicket" method="post" enctype="multipart/form-data" namespace="/admin">
<s:hidden name="selectedId"></s:hidden>
<fieldset>
<%@ taglib prefix="s" uri="/struts-tags" %>
	<div class="grid_10" style="padding-bottom: 5px;">
		<div class="grid_6">
				<sj:textfield name="supportTicket.title" id="title" required="true" label="Title"
					cssClass="alphabet required textfield" maxlength="20"></sj:textfield>
			</div>
	</div>
	<div class="grid_9">
		<div class="grid_5" style="text-align: left;">
			<b><font style="color: red">*&nbsp;</font>Description:</b>
		</div>
		<div class="grid_6">
			<sj:textarea rows="3" cols="20" name="supportTicket.description" cssStyle="width:88%"
				cssClass="text small required" required="true" requiredposition="left"></sj:textarea>
			<div class="counter"></div>
		</div>
	</div>
	
	<div class="grid_9">
		<div class="tableactions" style="padding-bottom: 0px;">
			<s:select list="#{'F':'Featured','I':'Issue','O':'Other'}" cssClass="required textfield" label="Select Category"
				required="true" name="supportTicket.category" headerKey="" headerValue="- Select -" requiredposition="first" cssStyle="width:200px;height:35px">
			</s:select>
		</div>
	</div>
	
	<div class="grid_9">
		<div class="tableactions" style="padding-bottom: 0px;">
		<s:select list="objectList" listKey="skillTypeName" listValue="skillTypeName" cssClass="required textfield" label="Select SkillType"
				required="true" name="supportTicket.priority" headerKey="" headerValue="- Select -" requiredposition="first" cssStyle="width:200px;height:35px">
			</s:select>
		</div>
	</div>
	
	<!--<div class="grid_9">
		<div class="tableactions" style="padding-bottom: 0px;">
		<s:select list="#{'C':'Critical','M':'Major','N':'Normal'}" cssClass="required textfield" label="Select Priority"
				required="true" name="supportTicket.priority" headerKey="" headerValue="- Select -" requiredposition="first" cssStyle="width:200px;height:35px">
			</s:select>
		</div>
	</div>
	
	--><div class="grid_9">
		<div class="grid_5">
		<s:file name="upload"  cssClass="required textfield" label="Upload File"></s:file>
		</div>
	</div>

		<div class="grid_4" style="float: right;">
			<s:url id="cancelTicketUrl" action="ajaxCancelTicket"
				includeParams="all" escapeAmp="false" namespace="/admin">
			</s:url>
			<sj:a href="%{cancelTicketUrl}" cssClass="cancelButton"
				indicator="indicator" targets="ticketContent" button="false"
				buttonIcon="ui-icon-plus">Cancel</sj:a>
			<sj:submit   cssClass="submit small" value="Submit"
				indicator="indicator" targets="ticketContent" validate="true"
				formIds="addPostTicket" />
		</div>
	</fieldset>
</s:form>
<script type="text/javascript">
changePageTitle("Add  <s:property value="kBankTypeName"/>");
</script>



