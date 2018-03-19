<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<div id="commonTabContent" class="grid_15">
	<div id="commonTabWrapper">
		<div id="commonStep">
			<fieldset style="padding:15px 19px 5px 1px">
				<s:form action="ajaxSaveModifyHostelStaffInvoice" id="modifyStaffInvoiceNumber" method="post"
					theme="css_xhtml" namespace="/hostel">
					<s:hidden name="staffId" value="%{anyId}"/>
					<s:hidden name="invoiceNumber" value="%{selectedId}"/>
					<s:hidden name="type" value="%{tempString}"/>
					<s:hidden name="lastUpdatedDateStr" value="%{selectedDate}"/>
					<div class="grid_13">
					<div class="grid_9">
						<div class="grid_3 alpha">
							Enter Modify Password:
						</div>
						<div class="grid_4 alpha">
							<input type="password" name="customer.modifyInvoicePassword" id="modifyInvoicePassword" maxlength="40" class="textfield defaultValue" value="Enter modify Invoice Password.">
						</div>
						<sj:submit   targets="searchStudentsForm112" value="Submit" indicator="indicator"
							cssClass="submit small right" formIds="modifyStaffInvoiceNumber" validate="true"  onClickTopics="checkStaffModifyPassword"/>
					</div>
					<div class="grid_3 alpha">&nbsp;</div>
					</div>
				</s:form>
			</fieldset>
		</div>
	</div>
</div>
<script type="text/javascript">
$.subscribe('checkStaffModifyPassword', function(event, data) {
		var keyword = $('#modifyInvoicePassword').val();
		if (keyword == null || keyword == '' || keyword == 'Enter modify Invoice Password.') {
			alert("Please Enter Modify Invoice Password.");
			return false;
		} else
			return true;
	});
</script>
