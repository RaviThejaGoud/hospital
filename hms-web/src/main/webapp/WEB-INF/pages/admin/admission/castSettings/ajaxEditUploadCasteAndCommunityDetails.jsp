<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div class="grid_10 commomnTabs">
	<s:form action="ajaxDownLoadAllCommunityAndCasteDetails" theme="css_xhtml" id="classAndCommunity" method="post" namespace="/admin">
		 	<div class="grid_12">
		 	<p>
		 Please click on Community&Caste button to download Community&Caste Details. 
	</p>
			<s:submit type="submit long" value="Download Community&Caste" cssClass="submit long"
				title="generate report" onclick="reportFormate()"
				cssStyle="cursor:pointer">
			</s:submit>
		</div>
	</s:form>
</div>
<s:if test='%{#session.previousYear == "N"}'>
<div class="grid_12 commomnTabs">
	<s:form action="ajaxUploadCommunityAndCasteData" id="editCommunityAndCasteExcelSheet" method="post" theme="css_xhtml"
		enctype="multipart/form-data" name="" namespace="/admin">
		<s:hidden name="tempString" value="Edit"></s:hidden>
		<h1>Upload community/caste Details</h1>
		<fieldset>
			<p>
				If you want to upload the community/caste-details template (xls) then select (browse button) the community/cast details template excel sheet.
			</p>
			<div class="grid1_12">
			</div>
			<div class="grid_12">
				<div class="grid_8">
					<label class="large">
						<span class="required">*</span>Select community/caste Import Template
						(Excel)
					</label>
				</div>
				<div class="grid_5">
					<s:file name="upload" id="photoURL" required="true"
						requiredposition="first" cssClass="textfield required">
					</s:file>
				</div>
				<div class="grid_2">
					<sj:submit   targets="academicsContent" value="Submit" indicator="indicator"
						cssClass="submit small" formIds="editCommunityAndCasteExcelSheet"
						validate="true" />
				</div>
			</div>
		</fieldset>
	</s:form>
</div>
</s:if>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle('Download CommunityAndCaste Details');
	$("#searchStudentsList").hide();
	var size=250px;
	alert("Hi");
	 $("div.long").width( size );
});
function reportFormate() {
	$('.anyId').val('Excel');
} 
</script>