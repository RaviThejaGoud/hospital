<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/common/textcounter.js">
</script>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div class="form-body">
	<s:form action="ajaxAddPostCaseStudy" theme="simple"
		id="addPostCaseStudy" method="post" cssClass="form-horizontal" namespace="/admin" enctype="multipart/form-data">
		<s:hidden name="selectedId"></s:hidden>
		<%@ taglib prefix="s" uri="/struts-tags"%>
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
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						<span class="required">*</span>Title :
					</label>
					<div class="col-md-5">
						<sj:textfield name="knowledgeBank.title" id="title" label=""
							cssClass="alphabet required form-control" maxlength="60"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group ">
					<label class="control-label col-md-4">
						<span class="required">*</span>Lesson Keywords :
					</label>
					<div class="col-md-5">
						<sj:textfield name="knowledgeBank.searchKewords"
							cssClass="alphabet required form-control" placeholder=""
							maxlength="20">
						</sj:textfield>
						<span class="help-block">Separate each keyword with a (,)
						comma </span>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group ">
					<label class="control-label col-md-4">
						<span class="required">*</span>Description :
					</label>
					<div class="col-md-5">
						<sj:textarea rows="3" cols="20" name="knowledgeBank.description"
							cssClass="form-control  word_count required"></sj:textarea>
						<span class="hintMessage"><div class="counter"></div> </span>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						<span class="required">*</span>Upload File :
					</label>
					<div class="col-md-5">
						<s:file name="upload" cssClass="required btn default"></s:file>
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
							<s:select list="classList" listKey="id" listValue="className"
								cssClass="required form-control" name="classId" headerKey=""
								headerValue="- Select -">
							</s:select>
						</div>
					</div>
				</div>
			</s:if>

		</div>
		<div class="form-actions fluid">
			<div class="col-md-offset-2 col-md-9">
				
				<sj:submit   cssClass="submitBt btn blue" value="Submit"
					indicator="indicator" targets="mainContentDiv"
					formIds="addPostCaseStudy" validate="true" />
				<s:url id="urlKBankDetailsLink" action="ajaxGetKBankDetails"
					namespace="/admin" includeParams="all" escapeAmp="false">
					<s:param value="id" name="kBankTypeId" />
					<s:param value="typeName" name="kBankTypeName" />
				</s:url>
				<sj:a href="%{urlKBankDetailsLink}" targets="mainContentDiv"
					cssClass="btn default"> Cancel </sj:a>
			</div>
		</div>
	</s:form>
</div>
<script type="text/javascript">
changePageTitle("Add  <s:property value="kBankTypeName"/>");
</script>
