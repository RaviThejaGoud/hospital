<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{classList != null && !classList.isEmpty()}">
<form  action="${pageContext.request.contextPath}/reports/ajaxHostelStudentClassWiseDefaulters.do?pdfId=pdf" id="addSchoolClassDefaultersList" method="post" id="target" onSubmit="return selectClass(); return false;" name="sform">
	<div class="grid_3">
	<input type="hidden" name="pdfId" value="pdf" />
				<s:select list="classList" listKey="id" listValue="className" label="Slect Class"
					cssClass="required" required="true" id="id" theme="css_xhtml"
					name="classId" headerKey="s" headerValue="- Select Class -" cssStyle="width:180px;"
					>
				</s:select>
		</div>
	<div class="grid_3">
	<div class="grid_3">&nbsp;</div>
		<input type="submit"  value="Generate" 
			class="submit small"  />
	</div>
</form>
</s:if>
<s:else>
	You have not created any class. 
</s:else>
<script type="text/javascript">
function selectClass() {
	var pars = $("#id option:selected").text();
    if(pars == '- Select Class -'){
    	alert('Please select at least one class.');
    	return false;
    }else{
    	return true;
    }
}
</script>