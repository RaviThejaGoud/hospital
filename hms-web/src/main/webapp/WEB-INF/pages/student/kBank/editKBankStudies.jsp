<%@ include file="/common/taglibs.jsp"%>
<td colspan="4">
	<s:form id="doEditKBankStudies" action="ajaxEditKBankStudies" method="post"
		theme="css_xhtml">
		<s:hidden name="knowledgeBank.id" />
		<s:hidden name="selectedId"></s:hidden>
		<div class="grid_9">
		<div class="tableactions" style="padding-bottom: 0px;">
			<s:select list="studySubjectList" listKey="id" listValue="name" cssClass="required textfield" label="Select Suject(es)"
				required="true" name="subjectId" headerKey="" headerValue="- Select -" requiredposition="first" cssStyle="width:200px;height:35px">
			</s:select>
		</div>
		</div>
		<div class="grid_9">
		<div class="tableactions" style="padding-bottom: 0px;">
			<s:select list="#{'B':'Basic','I':'Intermediate','H':'High'}" cssClass="required textfield" label="Select SkillsType" required="true" id="skillsType" headerKey="" headerValue="- Select -"  name="knowledgeBank.skillsType" cssStyle="width:200px;height:35px"></s:select>
		</div>
	</div>
	<div class="grid_10" style="padding-bottom: 5px;">
		<div class="grid_6">
				<sj:textfield name="knowledgeBank.title" id="title" required="true" label="Title"
					cssClass="alphabet required textfield" maxlength="20"></sj:textfield>
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
			<s:file name="upload" label="Upload File"></s:file>
		</div>
		<div class="grid_9"><s:property value="knowledgeBank.attachment.fileName"/></div>
	</div>
	
	<div class="grid_4" style="float: right;">
		<sj:submit   formIds="doEditKBankStudies" targets="kBankContent" value="Submit" onClickTopics="editKBank"
			cssClass="submit small" indicator="indicator"/>
		<s:url id="urlDoAddCaseStudy" includeParams="all" action="ajaxGetKBankDetails" escapeAmp="false">
		<s:param name="kBankTypeId" value="%{selectedId}"></s:param>
		<s:param name="kBankTypeName" value="%{kBankTypeName}" /> 
		</s:url>
		<sj:a href="%{urlDoAddCaseStudy}" cssClass="cancelButton"
			indicator="indicator" targets="kBankContent" 
			button="false" buttonIcon="ui-icon-plus">Cancel</sj:a>
	</div>
	</s:form>
</td>
<script type="text/javascript">
$(document).ready(
function() {
	$.subscribe('editKBank', function(event, data) {
		  if ($('#doEditKBankStudies').valid())
               return true;
           else
               return false;
	});
	$('.numeric').numeric();	
	$('.alphabet').alpha() ;
});
changePageTitle('Edit Information');

</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/common/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/common/jquery.autoCheck.js"></script> 