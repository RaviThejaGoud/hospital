<%@ include file="/common/taglibs.jsp"%>
<div class="grid_13">
	<div id="staffDetailsForAdmin" class="commomnTabs">
		<jsp:include page="/common/messages.jsp"></jsp:include>
	</div>
	
</div>
		<div class="grid_14">
			<div id="viewEmiDetailsId" class="grid_13">
					<s:if test="%{objectList!= null && !objectList.isEmpty()}">
							<div class="grid_12 th">
									<div class="grid_3">
										Installment Amount
									</div>
									<div class="grid_3">
										Paid Amount
									</div>
									<div class="grid_3">
										Installment No
									</div>
									<div class="grid_3">
										Status
									</div>
									
					         </div>
					         <s:iterator value="objectList">
					         
					         	<div class="grid_12 row">
										
										<div class="grid_3" >
										   <s:property value="installmentAmount" />
										</div>
										<div class="grid_3" >
										   <s:property value="paidAmount" />
										</div>
										<div class="grid_3" >
											<s:property value="installmentNumber" />
										</div>
										
										<div class="grid_3" >
											<s:property value="emiStatus" />
										</div>
								</div>
							</s:iterator>
						</s:if>
						<s:else>
						There are no Emi's for this loan
						</s:else>
										
						</div>
						<div class="grid_12" >
						<s:url id="urlViewStaffLoanEmi" action="ajaxGetStaffByRoleForLoan"
							includeParams="all" escapeAmp="false" namespace="/staff">
						</s:url>
						<sj:a href="%{urlViewStaffLoanEmi}" 
							indicator="indicator" targets="staffLoanSettings" button="false"
							buttonIcon="ui-icon-plus" cssClass="linkRight">
							Back 
						</sj:a>
						</div>
						</div>
