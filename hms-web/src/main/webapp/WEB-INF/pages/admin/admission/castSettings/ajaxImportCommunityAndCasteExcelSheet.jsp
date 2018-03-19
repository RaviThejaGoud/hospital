<%@ include file="/common/taglibs.jsp"%>
<div id="steps13">
	<jsp:include page="/common/messages.jsp"></jsp:include>
	<s:form action="ajaxUploadCommunityAndCasteData" id="importCommunityAndCasteExcelSheet" method="post" theme="css_xhtml"
		enctype="multipart/form-data" name="" namespace="/admin">
		<s:hidden name="tempString" value="Import"></s:hidden>
		<fieldset>
			<h1>
				Add Community And Caste
			</h1>
			<p>
				If you do not have a Community And Caste template (xls), please download, add
				Community(s) And Caste(s) to sheet then import the sheet. System can't process any
				records, if the data is not in specified format
			</p>
			<div class="grid_5 right">
			<s:if test='%{tempString != "" && tempString != null && tempString != empty}'>
			<a class="linkRight"
				href="${pageContext.request.contextPath}/<s:property value='tempString'/>/ImportCommunityAndCasteTemplate.xls">Download
				Template (Excel Sheet)</a>
			</s:if>
			<s:else>
				<a class="linkRight"
				href="${pageContext.request.contextPath}/userFiles/userDocs/ImportCommunityAndCasteTemplate.xls">Download
				Template (Excel Sheet)</a>			
			</s:else>
			</div>
			<div class="grid_7">
				<div class="grid_7">
					<label class="large">
						<span class="required">*</span>Select Community(s) And Caste(s) Import Template
						(Excel)
					</label>
				</div>
				<div class="grid_5">
					<s:file name="upload" id="photoURL" required="true"
						requiredposition="first" cssClass="textfield required">
					</s:file>
				</div>
				<div class="grid_2">
					<sj:submit   targets="academicsContent" value="Submit"
						formIds="importCommunityAndCasteExcelSheet" indicator="indicator"
						cssClass="submit small" validate="true" />
				</div>
			</div>
		</fieldset>
	</s:form>
</div>
<script type="text/javascript">
changePageTitle('Import CommunityAndCast Template');
</script>
