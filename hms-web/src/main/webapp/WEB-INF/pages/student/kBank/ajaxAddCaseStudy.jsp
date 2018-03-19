<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/common/textcounter.js"></script>
<jsp:include page="/common/messages.jsp"></jsp:include>
<s:form action="ajaxAddPostCaseStudy" theme="css_xhtml" id="addPostCaseStudy" method="post" namespace="/student">
<s:hidden name="selectedId"></s:hidden>
<fieldset>
<%@ taglib prefix="s" uri="/struts-tags" %>


	<!--<div class="grid_9">
		<div class="tableactions" style="padding-bottom: 0px;">
			<s:select list="knowledgeBankTypeList" listKey="id" listValue="typeName" cssClass="required textfield" label="Select KBankType(s)"
				required="true" name="kBankTypeId" headerKey="" headerValue="- Select -" requiredposition="first" cssStyle="width:200px;height:35px">
			</s:select>
		</div>
	</div>

	-->
	<div class="grid_9">
		<div class="tableactions" style="padding-bottom: 0px;">
			<s:select list="studySubjectList" listKey="id" listValue="name" cssClass="required textfield" label="Select Subject"
				required="true" name="subjectId" headerKey="" headerValue="- Select -" requiredposition="first" cssStyle="width:200px;height:35px">
			</s:select>
		</div>
	</div>
	<div class="grid_9">
		<div class="tableactions" style="padding-bottom: 0px;">
		<s:select list="objectList" listKey="id" listValue="skillTypeName" cssClass="required textfield" label="Select SkillType"
				required="true" name="commonTypeId" headerKey="" headerValue="- Select -" requiredposition="first" cssStyle="width:200px;height:35px">
			</s:select>
		</div>
	</div>
	<div class="grid_10" style="padding-bottom: 5px;">
		<div class="grid_6">
				<sj:textfield name="knowledgeBank.title" id="title" required="true" label="Title"
					cssClass="alphabet required textfield" maxlength="60"></sj:textfield>
			</div>
	</div>
	<br />
	<div class="grid_9">
		<div class="grid_5" style="text-align: left;">
			<b><font style="color: red">*&nbsp;</font>Description:</b>
		</div>
		<div class="grid_6">
			<sj:textarea rows="3" cols="20" name="knowledgeBank.description" cssStyle="width:88%"
				cssClass="text small  word_count required" required="true" requiredposition="left"
				></sj:textarea>
			<div class="counter"></div>
		</div>
	</div>
	<div class="grid_9">
		<div class="grid_5" style="text-align: left;">
				<sj:textfield name="knowledgeBank.searchKewords" required="true" label="Lesson Keywords"
					cssClass="alphabet required textfield" maxlength="20">
					Separate each keyword with a comma 
					</sj:textfield>
			</div>
	</div>
	<!--<div class="grid_9">
		<div class="grid_5" style="text-align: left;">
			<b><font style="color: red">*&nbsp;</font>Lesson Keywords:</b><br/>
			Separate each keyword with a comma 
		</div>
		<div class="grid_6">
			<sj:textarea rows="3" cols="20" name="knowledgeBank.searchKewords" cssStyle="width:88%"
				cssClass="text small required" required="true" requiredposition="left"></sj:textarea>
		</div>
	</div>
	--><div class="grid_9">
		<div class="grid_5">
		<s:file name="upload"  required="true"  cssClass="required textfield" label="Upload File"></s:file>
		</div>
	</div>
	
	<div class="grid_4" style="float: right;">
		<sj:submit   cssClass="submit small" value="Submit" indicator="indicator"
		targets="kBankContent" formIds="addPostCaseStudy" 
		onClickTopics="formValidationForCaseStudy" />
		<s:url id="urlDoAddCaseStudy" includeParams="all" action="ajaxGetKBankDetails" escapeAmp="false" namespace="/student">
		<s:param name="kBankTypeId" value="%{selectedId}"></s:param>
		<s:param name="kBankTypeName" value="%{kBankTypeName}" />
		</s:url>
		<sj:a href="%{urlDoAddCaseStudy}" cssClass="cancelButton"
			indicator="indicator" targets="kBankContent" 
			button="false" buttonIcon="ui-icon-plus">Cancel</sj:a>
	</div>
	</fieldset>
</s:form>
<script type="text/javascript">
changePageTitle("Add  <s:property value="kBankTypeName"/>");
$.subscribe('formValidationForCaseStudy', function(event, data) {
	if ($('#addPostCaseStudy').valid())
		return true;
	else
		return false;
});
</script>



