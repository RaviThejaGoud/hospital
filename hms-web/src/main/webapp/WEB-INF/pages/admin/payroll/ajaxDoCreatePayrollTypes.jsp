<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/common/jquery.alphanumeric.js">
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/subscription/textcounter.js">
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<%@ include file="/common/taglibs.jsp"%>


<div>
	<s:form action="ajaxCreatePayrollTypes" theme="css_xhtml"
		id="createPayrolltypes" method="post" namespace="/admin">
		<input type="hidden" id="hidInput" name="hidInput" value="hidInput" />
		<div class="grid_12" id="TextBoxDiv1">
			<div class="grid_4">
				<label>
					<span class="noteMassage">*</span>Payroll Name:
				</label>
				<sj:textfield name="payrollTypes.payrollName" required="true"
					cssStyle="width:200px;Padding:4px;" id='allocationRoomForExam1'
					cssClass="required textfield" maxlength="40"></sj:textfield>
			</div>
			<div class="grid_4">
				<label>
					Description:
				</label>
				<sj:textfield id="contactNumber" name="payrollTypes.payrollDescription"
					 cssStyle="width:200px;Padding:4px;"
					cssClass="textfield "></sj:textfield>
			</div>
			<div class="grid_2">
				<label>
					<span class="noteMassage">*</span>Allowance/Deduction:
				</label>
				
				<s:select cssClass="required" required="true" 
				list="#{'AW':'Allowance','DN':'Deduction'}" 
				name="payrollTypes.payrollType" id="yourOptionValue" theme="css_xhtml"
				value="2" />
				
			</div>
			<div class="grid_12" id="floorNames"></div>
		</div>
		<div class="grid_12">
			<s:url id="doGetPayrollTypes" action="ajaxDoCancelPayrollTypes"
				includeParams="all" escapeAmp="false" namespace="/admin">
			</s:url>
			<sj:a href="%{doGetPayrollTypes}" cssClass="cancelButton"
				targets="payrollTypesSettings" indicator="indicator">Cancel</sj:a>
			<sj:submit targets="payrollTypesSettings" value="Save"
				cssClass="submit small" formIds="createPayrolltypes"
				validate="true" indicator="indicator"
				/>
		</div>
	</s:form>
</div>

<script type="text/javascript">
	changePageTitle('Create Payroll Types');
</script>