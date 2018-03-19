<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<s:if test="%{objectList != null && !objectList.isEmpty()}">
	<div class="form-group">
		<div class="panel-body col-md-12">
			<div class="col-md-1">
				<span class="label label-danger"> NOTE : </span>
			</div>
			<div class="col-md-10">
				<ul>
					<li>
						 For better view to print challan use Firefox.
					</li>
					
				</ul>
			</div>
		</div>
	</div>
	<table class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
		<thead>
			<tr>
				
				<th>
					#Admission
				</th>
				<th>
					Student Name
				</th>
				<th>
				 	Class Name
				</th>
				<th>
				 Challan Number
				</th>
				<th>
					Challan Verify
				</th>
				<th>
					Receipt <!-- / Delete -->
				</th>
				<th>
					Delete
				</th>
			</tr>
		</thead>
		<tbody>
		<s:set var="classNameId" value=""></s:set>
			<s:iterator value="objectList">
				<s:set var="studentDetailsId" value="studentId"></s:set>
				<tr>
					<td align="center">
						<s:property value="admissionNumber" />
					</td>
					<td>
						<s:property value="fullName" />
					</td>
					<td align="center">	
						<s:property value="classAndSection" />
					</td>
					<td>
						<s:property value="challanaNumber"/>
					</td>
					<td align="center">
						 <s:url id="challanaFeePayment" namespace="/schoolfee" action="ajaxVerifyStudentChallana" includeParams="all" escapeAmp="false">
									<s:param name="studentNumber" value="%{studentId}" />
									<s:param name="userVo.id" value="%{accountId}"></s:param>
									<s:param name="studentPayment.invoiceNumber" value="%{challanaNumber}"></s:param>
									<s:param name="studentPayment.academicYear.id" value="%{academicYearId}"></s:param>
									<s:param name="paymentStatus" value="%{paymentStatus}" />
									<s:param name="description">studInvPaymt</s:param>
								</s:url>
								<sj:a href="%{challanaFeePayment}" targets="searchStudentsForm112"> Verify </sj:a> 
					</td>
					<td align="center">
						<a style="cursor: pointer" id="noPrint"
										onClick="javascript:printChallanaPreview('<s:property value='studentId'/>',
                                        '<s:property value='challanaDateStr'/>',
                                        '<s:property value='challanaNumber'/>',<s:property value="paidAmount" />,'download','<s:property value="academicYearId"/>')"
										; target="_new"><img   src="../images/index.jpg">
										Challan : <s:property value="challanaNumber" /> </a>
						<%-- <a data-toggle="modal"  href="#popupFinePaymentDiv"  
								onclick="javascript:PopupAddFineFeePayment(<s:property value="studId" />,'<s:property value="fullNameAndClassName" />');"> Pay
						</a> --%>
					</td>
					<td align="center">
						<s:if test='%{#session.previousYear == "N"}'>
							<s:url id="removeStudentChallana" action="ajaxRemoveChallanaDetails" includeParams="all" escapeAmp="false" namespace="/schoolfee">
								<s:param name="challanaPayment.id" value="%{challanaId}" />
								<s:param name="challanaPayment.studentPayment.id" value="%{studentPaymentId}"></s:param>
							</s:url>
							<s:div cssClass="btn btn-xs red" onclick="javascript:confirmDialogWithTarget(this,'searchStudentsForm112');" 
								id='%{removeStudentChallana}' title="Delete this Challana"> <i class="fa fa-times"></i>
							</s:div>
						</s:if>
					</td>
					</tr>
				<s:set var="classNameId" value="classId"></s:set>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no generated challan.
	</div>
</s:else>
<s:if test='%{customer.showPreviousYearPendingFee == "Y"}'>
<s:if test="%{studentSize > 0}">
<s:if test="%{annualYearlyClassList != null && !annualYearlyClassList.isEmpty()}">
	<div class="form-group">
		<div class="panel-body col-md-12">
			<div class="col-md-10">
				<h4><s:property value="tempString"/> Fee Challan</h4>
			</div>
		</div>
	</div>
	<table class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
		<thead>
			<tr>
				
				<th>
					#Admission
				</th>
				<th>
					Student Name
				</th>
				<th>
				 	Class Name
				</th>
				<th>
				 Challan Number
				</th>
				<th>
					Challan Verify
				</th>
				<th>
					Receipt <!-- / Delete -->
				</th>
				<th>
					Delete
				</th>
			</tr>
		</thead>
		<tbody>
		<s:set var="classNameId" value=""></s:set>
			<s:iterator value="annualYearlyClassList">
				<s:set var="studentDetailsId" value="studentId"></s:set>
				<tr>
					<td align="center">
						<s:property value="admissionNumber" />
					</td>
					<td>
						<s:property value="fullName" />
					</td>
					<td align="center">	
						<s:property value="classAndSection" />
					</td>
					<td>
						<s:property value="challanaNumber"/>
					</td>
					<td align="center">
						 <s:url id="challanaFeePayment" namespace="/schoolfee" action="ajaxVerifyStudentChallana" includeParams="all" escapeAmp="false">
									<s:param name="studentNumber" value="%{studentId}" />
									<s:param name="userVo.id" value="%{accountId}"></s:param>
									<s:param name="studentPayment.invoiceNumber" value="%{challanaNumber}"></s:param>
									<s:param name="studentPayment.academicYear.id" value="%{academicYearId}"></s:param>
									<s:param name="paymentStatus" value="%{paymentStatus}" />
									<s:param name="description">studInvPaymt</s:param>
								</s:url>
								<sj:a href="%{challanaFeePayment}" targets="searchStudentsForm112"> Verify </sj:a> 
					</td>
					<td align="center">
						<a style="cursor: pointer" id="noPrint"
										onClick="javascript:printChallanaPreview('<s:property value='studentId'/>',
                                        '<s:property value='challanaDateStr'/>',
                                        '<s:property value='challanaNumber'/>',<s:property value="paidAmount" />,'download','<s:property value="academicYearId"/>')"
										; target="_new"><img   src="../images/index.jpg">
										Challan : <s:property value="challanaNumber" /> </a>
						<%-- <a data-toggle="modal"  href="#popupFinePaymentDiv"  
								onclick="javascript:PopupAddFineFeePayment(<s:property value="studId" />,'<s:property value="fullNameAndClassName" />');"> Pay
						</a> --%>
					</td>
					<td align="center">
						<s:if test='%{#session.previousYear == "N"}'>
							<s:url id="removeStudentChallana" action="ajaxRemoveChallanaDetails" includeParams="all" escapeAmp="false" namespace="/schoolfee">
								<s:param name="challanaPayment.id" value="%{challanaId}" />
								<s:param name="challanaPayment.studentPayment.id" value="%{studentPaymentId}"></s:param>
							</s:url>
							<s:div cssClass="btn btn-xs red" onclick="javascript:confirmDialogWithTarget(this,'searchStudentsForm112');" 
								id='%{removeStudentChallana}' title="Delete this Challana"> <i class="fa fa-times"></i>
							</s:div>
						</s:if>
					</td>
					</tr>
				<s:set var="classNameId" value="classId"></s:set>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no <s:property value="tempString"/> generated challan(s).
	</div>
</s:else>
</s:if>
</s:if>
<form method="post" id="printReport" style="display: none;"
	action="javaScript:printPreview('<s:property value="studentNumber" />','<s:property value="todayDate"/>','<s:property value="tempId2" />','<s:property value="empId" />','payandprint','<s:property value="tempId1" />','<s:property value="alertSendType"/>')" >
</form>
<script type="text/javascript">
$(document).ready(function() {
	
});
	 TableAdvanced.init();
	UIExtendedModals.init();
</script>
