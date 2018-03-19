<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{hostelTermsList != null && !hostelTermsList.isEmpty()}">
	<form
		action="${pageContext.request.contextPath}/reports/ajaxHostelnvoiceTermsWiseFeeDetails.do?pdfId=pdf"
		id="addHostelTermsList" method="post"
		onsubmit="javascript:return checkBoxFieldErrors();">
		<div class="grid_5 checkbox">
			<h1>
				Available Terms
			</h1>
			<div class="grid_4" style="width: auto;">
			<s:checkboxlist name="chkBoxSelectedIds" list="hostelTermsList"
				listKey="id" listValue="hostelTermName" theme="ems" cssClass="small" />
			</div>
			
		
			<div class="grid_2">
				<input type="submit" value="Generate" class="submit small" />
			</div>
		</div>	
	</form>
</s:if>
<s:else>
	You have not created any terms. 
</s:else>

<script type="text/javascript">
function checkBoxFieldErrors() {
	var checkList = document.getElementsByName("chkBoxSelectedIds");
	for ( var m = 0; m < checkList.length; m++) {
		if ($("input[type=checkbox]").is(':checked')) {
			return true;
		} else {
			alert("Please select at least one Term.");
			return false;
		}
	}
}
</script>