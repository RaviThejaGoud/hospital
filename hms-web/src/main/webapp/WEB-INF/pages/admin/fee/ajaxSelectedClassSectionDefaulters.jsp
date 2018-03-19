<%@ include file="/common/taglibs.jsp"%>
	<div class="block_content">
			<label>
				<b>Select Class:</b>
			</label>
		<div>
		<form name="myform"
			action="${pageContext.request.contextPath}/reports/printViewClassSectionDefaulterReports.do">
			<s:select list="classList" listKey="id" listValue="className"
				cssClass="required" required="true" id="className" theme="css_xhtml"
				name="selectedId" headerKey="" headerValue="- Select Class -"
				onchange="javascript:onClassSectionDetailsChange(this.value);">
			</s:select>
		</form>
		</div>
	</div>
<script type="text/javascript">
	changePageTitle('Add Exam Schedules');
	function onClassSectionDetailsChange(id) {
		document.myform.submit();
	}
	
	
	/*function onClassDetailsChange(className) {
			var pars = "className=" + className;
			
		$.ajax({
			url :jQuery.url.getChatURL("/common/printViewSelectedClassFeeDetails.do"),
			cache : false,
			data : pars,
			success : function(response) {
				$('#examTypesContent').html(response);
			}
		});
	}*/
</script>

