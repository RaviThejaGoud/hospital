<%@ include file="/common/taglibs.jsp"%>
	<div class="block_content">
			<label>
				<b>Select Class:</b>
			</label>
		<div>
		<form name="myform"
			action="${pageContext.request.contextPath}/reports/printViewSelectedClassFeeDetails.do">
			<input type="hidden" name="className" id="classNameId"
				value='' />
			<s:select list="classList" listKey="className" listValue="className"
				cssClass="required" required="true" id="className" theme="css_xhtml"
				name="className" headerKey="" headerValue="- Select Class -"
				onchange="javascript:onClassDetailsChange(this.value);">
			</s:select>
		</form>
		</div>
	</div>
<script type="text/javascript">
	changePageTitle('Add Exam Schedules');
	function onClassDetailsChange(id) {
		$("#classNameId").val(id);
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

