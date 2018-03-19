<%@ include file="/common/taglibs.jsp"%>
<div class="form-body">
	<s:form action="ajaxEditKBankStudies" theme="simple"
		id="doEditKBankStudies" method="post" cssClass="form-horizontal" namespace="/admin" enctype="multipart/form-data">
		<s:hidden name="knowledgeBank.id" />
		<s:hidden name="selectedId"></s:hidden>
		<s:hidden name="kBankTypeName"></s:hidden>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						<span class="required">*</span>Select Subject :
					</label>
					<div class="col-md-5">
						<s:select list="studySubjectList" listKey="id" listValue="name"
							cssClass="required form-control" name="subjectId" headerKey=""
							headerValue="- Select -">
						</s:select>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group ">
					<label class="control-label col-md-4">
						<span class="required">*</span>Select SkillType :
					</label>
					<div class="col-md-5">
						<s:select list="objectList" listKey="id" listValue="skillTypeName"
							cssClass="required form-control" name="commonTypeId" headerKey=""
							headerValue="- Select -">
						</s:select>
					</div>
				</div>
			</div>
		</div>
	<div class="row">
	<s:if test="%{kBankTypeName == 'My Class Material'}">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						<span class="required">*</span>Select Class :
					</label>
					<div class="col-md-5">
					<s:select list="classList" listKey="id" listValue="className" cssClass="required form-control textfield" label="Select Class"
				  name="classId" headerKey="" headerValue="- Select -" >
			</s:select>
			</div>
		</div>
	   </div>
	</s:if>
	<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						<span class="required">*</span>Title :
					</label>
					<div class="col-md-5">
	 
				<sj:textfield name="knowledgeBank.title" id="title"   label="Title"
					cssClass="alphabet required form-control" maxlength="20"></sj:textfield>
			</div>
	</div>
	</div></div> 
	<div class="row">
	<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						<span class="required">*</span>Description :
					</label>
					<div class="col-md-5">
			<sj:textarea rows="3" cols="20" name="knowledgeBank.description" cssStyle="width:88%"
				cssClass="text small  word_count required form-control" 
				></sj:textarea>
			<div class="counter"></div>
		</div>
	</div></div>
   <div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						<span class="required">*</span>Lesson Keywords  :
					</label>
					<div class="col-md-5">
				<sj:textfield name="knowledgeBank.searchKewords"    
					cssClass="alphabet required form-control" maxlength="20">
					Separate each keyword with a comma 
					</sj:textfield>
			</div>
	</div>
	</div>
	</div>
	<div class="row">
	<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						<span class="required">*</span>Upload File :
					</label>
					<div class="col-md-5">
			 <s:file name="upload"></s:file>
		</div>
	</div></div>
	<div class="col-md-6">
		 <s:property value="knowledgeBank.attachment.fileName"/></div>
	</div>
	
	<div class="form-actions fluid">
			<div class="col-md-offset-2 col-md-9">
			<sj:submit formIds="doEditKBankStudies" targets="mainContentDiv" value="Submit"  
			cssClass="submitBt btn blue" indicator="indicator" validate="true"/>
		<s:url id="urlKBankDetailsLink" includeParams="all" action="ajaxGetKBankDetails" escapeAmp="false" namespace="/admin">
		<s:param name="kBankTypeId"><s:property value="selectedId"/></s:param>
		<s:param name="kBankTypeName" value="%{kBankTypeName}" /> 
		</s:url>
		<!--<sj:a href="%{urlDoAddCaseStudy}" cssClass="btn default"
			 targets="kBankContent" action="ajaxGetKBankDetails" 
			button="false" >Cancel</sj:a>-->
			<sj:a  href="%{urlKBankDetailsLink}" targets="mainContentDiv" 
			         cssClass="btn default">Cancel</sj:a>
			
			
		</div></div>
	</s:form>
 </div>
<script type="text/javascript">
$(document).ready(
function() {
	$('.numeric').numeric();	
	$('.alphabet').alpha() ;
});
changePageTitle('Edit Information');

</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/common/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/common/jquery.autoCheck.js"></script> 