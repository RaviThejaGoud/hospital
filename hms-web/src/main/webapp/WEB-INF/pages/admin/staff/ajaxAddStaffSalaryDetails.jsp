<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<fieldset id="stepStaffSalary" class="step13">
	<div class="grid_12">
		<s:form action="ajaxAddStaffSalaryIfo" theme="css_xhtml"
			id="addStaffSalaryIfo" method="post">
			<div id="viewStaffPersondetails">
				<%@ include file="/common/messages.jsp"%>
			</div>
			<s:hidden name="tempId" value="%{tempId}" />
			<div class="grid_12">
				<p>
					<b>Update New Salary for this Staff</b>
				</p>
			</div>
			<div class="grid_11">
				<sj:textfield name="salary.salary" id="salaryIfo"
					label="Enter Salary" required="true" tabindex="1"></sj:textfield>

				<sj:submit   cssClass="submit small" value="Save"
					targets="stepStaffSalary" formIds="addStaffSalaryIfo" />
			</div>
		</s:form>
	</div>
	<div class="grid_12">
		<div class="commomnTabs">
			<s:if test="%{salary != null}">
				<div class="grid_12 th">
					<div class="grid_3">
						Current Salary
					</div>
					<div class="grid_3">
						Current Salary Date
					</div>
				</div>
				<div class="grid_12 row">
					<div class="grid_3">
						<s:property value="salary.salary" />
					</div>
					<div class="grid_3">
						<s:property value="salary.salaryCreatedDateStr" />
					</div>
				</div>
			</s:if>
			<s:else>
				<div class="examTabBorder">
					Salary data not found for this Staff.
				</div>
			</s:else>
		</div>
	</div>
</fieldset>