<%@ include file="/common/taglibs.jsp"%>
<td colspan="6">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/common/jquery.alphanumeric.js">
</script>
	<s:form id="addCustomerDetails" action="ajaxAddCustomerDetails" method="post" theme="css_xhtml" namespace="/masterAdmin">
		<div class="grid_10">
			<sj:textfield name="customer.organization" id="customerName" label="Organization Name" cssClass="required textfield small" required="true"
						cssStyle="width:270px;" maxlength="60"></sj:textfield>
			</div>
			<div class="grid_10">
					<sj:textfield name="customer.custEmail" id="customerEmail" label="Email Id" cssClass="required textfield small" required="true"
						cssStyle="width:270px;" maxlength="60"></sj:textfield>
			</div>
			<div class="grid_10">
					<sj:textfield name="customer.contactNumber" id="customerContactNumber" label="Contact Number" 
					cssClass="numeric required textfield small" required="true" cssStyle="width:270px;" maxlength="60"></sj:textfield>
			</div>
		<div class="grid_11">
			<div class="grid_4" style="float: left;">
				<sj:submit   cssClass="submit small" formIds="addCustomerDetails"
					value="Submit" indicator="indicator" targets="customerDetails"
					onClickTopics="formValidationForAddCustomer" />
				<s:url id="doCancelCustomer" action="ajaxDocancelCustomer"
					includeParams="all" namespace="/masterAdmin"></s:url>
				<sj:a href="%{doCancelCustomer}" cssClass="cancelButton"
					indicator="indicator" targets="customerDetails" button="false">Cancel</sj:a>
			</div>
		</div>
	</s:form>
</td>
<script type="text/javascript">
changePageTitle("Add Customer");
$(document).ready(function() {
	$.subscribe('formValidationForAddCustomer', function(event, data) {
		if ($('#addCustomerDetails').valid())
			return true;
		else
			return false;
	});
	$('.numeric').numeric();
		$('.alphabet').alpha();
});
</script>
