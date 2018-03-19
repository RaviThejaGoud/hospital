<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<div id="commonTabContent" class="grid_15">
	<div id="commonTabWrapper">
		<div id="commonStep">
			<fieldset style="padding:15px 19px 5px 1px">
				<s:form action="ajaxSaveDeleteHostelStudentInvoice" id="deleteInvoiceNumber" method="post"
					theme="css_xhtml">
					<s:hidden name="studentId" value="%{anyId}"/>
					<s:hidden name="invoiceNumber" value="%{selectedId}"/>
					<div class="grid_13">
						<div class="grid_9">
							<div class="grid_3 alpha">
								Enter Delete Password:
							</div>
							<div class="grid_4 alpha">
								<input type="password" name="customer.deleteInvoicePassword" id="deleteInvoicePassword" maxlength="40" class="textfield" value="Enter Delete Invoice Password.">
							</div>
							<sj:submit   targets="searchStudentsForm112" value="Submit" indicator="indicator"
								cssClass="submit small right" formIds="deleteInvoiceNumber" validate="true"  onClickTopics="checkDeletePassword"/>
						</div>
						<div class="grid_3 alpha">&nbsp;</div>
					</div>
				</s:form>
			</fieldset>
		</div>
	</div>
</div>
<script type="text/javascript">
$.subscribe('checkDeletePassword', function(event, data) {
		var keyword = $('#deleteInvoicePassword').val();
		if (keyword == null || keyword == '' || keyword == 'Enter Delete Invoice Password.') {
			alert("Please Enter Delete Invoice Password.");
			return false;
		} else
			return true;
	});
</script>
