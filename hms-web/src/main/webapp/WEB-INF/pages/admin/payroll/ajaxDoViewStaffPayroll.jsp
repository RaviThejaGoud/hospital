<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.io.File"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="java.awt.Rectangle"%>
<%@page import="java.awt.Toolkit"%>
<%@page import="java.awt.Dimension"%>
<%@page import="java.awt.Robot"%>
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
<center>
	<b>Payslip For <s:property
			value="viewStaffPersonAccountDetails.payrollEffectiveFrom" /> </b>
</center>
<div id="steps" style="padding-left: 8px">
	<s:form action="#" theme="css_xhtml">
		<!-- this s:form only design perpose-->
		<fieldset>
			<div class="grid_12">
				<div class="grid_6">
					<s:if test="%{viewStaffPersonAccountDetails != null }">
						<div class="grid_6" id="TextBoxDiv1">
							<label class="labelRight">
								Staff Name:
							</label>
							<s:property value="viewStaffPersonAccountDetails.personFullName" />
							<br />
							<label class="labelRight">
								Role :
							</label>
							<s:property value="viewStaffPersonAccountDetails.roleDescription" />
							<br />
							<label class="labelRight">
								Location :
							</label>
							<s:property value="viewStaffPersonAccountDetails.city" />
							<br />
							<label class="labelRight">
								Date of Joining :
							</label>
							<s:property
								value="viewStaffPersonAccountDetails.staffDateOfJoing" />
							<br />
							<label class="labelRight">
								Bank Details :
							</label>
							<s:property
								value="viewStaffPersonAccountDetails.bankAccountNumber" />
							,
							<s:property value="viewStaffPersonAccountDetails.bankName" />
							,
							<s:property value="viewStaffPersonAccountDetails.bankBranchName" />
						</div>
					</s:if>
				</div>
				<div class="grid_6">
					<s:if test="%{staffStatutory != null }">
						<div class="grid_6" id="TextBoxDiv1">
							<label class="labelRight">
								Income Tax Number (PAN):
							</label>
							<s:property value="viewStaffPersonAccountDetails.panNumber" />
							<br />
							<label class="labelRight">
								ESI No:
							</label>
							<s:property value="staffStatutory.esiNo" />
							<br />
							<label class="labelRight">
								ESI Date Of Join :
							</label>
							<s:property value="staffStatutory.esiDateofJoinStr" />
							<br />
							<label class="labelRight">
								PF No :
							</label>
							<s:property value="staffStatutory.pfNo" />
							<br />
							<label class="labelRight">
								PF Date Of Join :
							</label>
							<s:property value="staffStatutory.pfDateofJoinStr" />
						</div>
					</s:if>
				</div>
			</div>
		</fieldset>
	</s:form>
</div>

<div id="commonStep13" style="padding-left: 31px;">
	<fieldset id="stepDoViewStaffPayroll">
		<div class="grid_13" id="TextBoxDiv1">
			<div id="doViewStaffPayroll" class="grid_13">
					<div class="grid_12 th">
						<div class="grid_2">
							Earnings
						</div>
						<div class="grid_2">
							Amount
						</div>
						<div class="grid_2">
							Gross Salary
						</div>

						<div class="grid_2">
							Deduction
						</div>
						<div class="grid_2">
							Amount
						</div>
						<div class="grid_2">
							Gross Salary
						</div>
					</div>
					<div class="grid_12">
						<div class="grid_12  row">
							<div class="grid_6">
								<div class="grid_2">
									Basic
								</div>
								<div class="grid_2">
									<s:property value="viewStaffPersonAccountDetails.basicSalary" />
								</div>
								<div class="grid_2">
									<s:property value="viewStaffPersonAccountDetails.basicSalary" />
								</div>
								<s:iterator value="objectList">
								<div class="grid_6">

									<div class="grid_2">
										<s:property value="staffPayrollDesc" />
									</div>
									<div class="grid_2">
										<s:property value="percentageValue" />
									</div>
									<div class="grid_2">
										<s:property value="percentageValue" />
									</div>
								</div>
							</s:iterator>
							</div>
							<div class="grid_6">
								<div class="grid_2">
									ESI
								</div>
								<div class="grid_2">
									<s:property value="staffStatutory.esiPercentageValue" />
								</div>
								<div class="grid_2">
									<s:property value="staffStatutory.esiPercentageValue" />
								</div>
								
								<div class="grid_2">
									PF
								</div>
								<div class="grid_2">
									<s:property value="staffStatutory.pfPercentageValue" />
								</div>
								<div class="grid_2">
									<s:property value="staffStatutory.pfPercentageValue" />
								</div>
								
								<s:iterator value="tempList">

									<div class="grid_2">
										<s:property value="staffPayrollDesc" />
									</div>
									<div class="grid_2">
										<s:property value="percentageValue" />
									</div>
									<div class="grid_2">
										<s:property value="percentageValue" />
									</div>
								</s:iterator>
								<s:iterator value="loanEmiDetailsList">

									<div class="grid_2">
										Staff Loan Emi : <s:property value="installmentNumber" />
									</div>
									<div class="grid_2">
										<s:property value="installmentAmount" />
									</div>
									<div class="grid_2">
										<s:property value="installmentAmount" />
									</div>
								</s:iterator>
								
								<div class="grid_2">
									Income Tax :
								</div>
								<div class="grid_2">
									<s:property value="taxAccount.taxAmountPerMonth" />
								</div>
								<div class="grid_2">
									<s:property value="taxAccount.taxAmountPerMonth" />
								</div>
									
								
							</div>
						</div>
						<!--<div class="grid_12  row">
							<s:iterator value="objectList">
								<div class="grid_6">

									<div class="grid_2">
										<s:property value="staffPayrollDesc" />
									</div>
									<div class="grid_2">
										<s:property value="percentageValue" />
									</div>
									<div class="grid_2">
										<s:property value="percentageValue" />
									</div>
								</div>
							</s:iterator>
							<s:iterator value="tempList">
								<div class="grid_6">

									<div class="grid_2">
										<s:property value="staffPayrollDesc" />
									</div>
									<div class="grid_2">
										<s:property value="percentageValue" />
									</div>
									<div class="grid_2">
										<s:property value="percentageValue" />
									</div>
								</div>
							</s:iterator>
							<div class="grid_6">
								<div class="grid_2">
									PF
								</div>
								<div class="grid_2">
									<s:property value="staffStatutory.pfPercentageValue" />
								</div>
								<div class="grid_2">
									<s:property value="staffStatutory.pfPercentageValue" />
								</div>
							</div>
						</div>
						-->
						
					</div>
					<div class="grid_12 row">
						<div class="grid_6">
							<div class="grid_2">
								<b>Total Earnings</b>
							</div>
							<div class="grid_2">
								<s:property value="viewStaffPersonAccountDetails.totalAllowance" />
							</div>
							<div class="grid_2">
								<s:property value="viewStaffPersonAccountDetails.totalAllowance" />
							</div>
						</div>
						<div class="grid_6">
							<div class="grid_2">
								<b>Total Deduction</b>
							</div>
							<div class="grid_2">
								<s:property value="viewStaffPersonAccountDetails.totalDeduction" />
							</div>
							<div class="grid_2">
								<s:property value="viewStaffPersonAccountDetails.totalDeduction" />
							</div>
						</div>
					</div>
					<div class="grid_12">
						<div class="grid_6">
						<div class="grid_2">
							<b>Net Amount</b>
						</div>
						<div class="grid_2">
							<s:property value="viewStaffPersonAccountDetails.netPay" />
						</div>
						<div class="grid_2">
							<s:property value="viewStaffPersonAccountDetails.netPay" />
						</div>
						</div>
				</div>
			</div>
		</div>
	</fieldset>
</div>

<script type="text/javascript">
changePageTitle('Get Payroll Settings');
</script>