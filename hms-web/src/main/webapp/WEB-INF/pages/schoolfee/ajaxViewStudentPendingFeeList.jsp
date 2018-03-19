<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div id="paymentDefaultersList">
<div class="spaceDiv"></div>
	<s:if test="%{studentsFeeTypeList != null && !studentsFeeTypeList.isEmpty() }">
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">Total Amount :</label>
					<div class="col-md-8 text-danger">
						<p class="form-control-static">
						<del>
							<b style="font-size: 14px;">&#2352;</b>
						</del>
						<c:set var="paymentAmt1" value='${thirtyTotalAmount}' />
						<fmt:formatNumber value="${paymentAmt1}" type="currency"
							pattern="##,##,#00.00" var="paymentBalance1" />
						<c:out value="${paymentBalance1}"  />
						&nbsp; of Students :
						<strong><%-- <s:property value="studentsFeeTypeList.size" /> --%><s:property value="displayAttendanceSet.size" /> </strong></p>
					</div>
				</div>
			</div>
			<!--/span-->
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Total Paid Amount :</label>
					<div class="col-md-8 text-danger">
						<p class="form-control-static">
							<del>
								<b style="font-size: 14px;">&#2352;</b>
							</del>
							<c:set var="paymentAmt2" value='${thirtyto60totalAmount}' />
							<fmt:formatNumber value="${paymentAmt2}" type="currency"
								pattern="##,##,#00.00" var="paymentBalance2" />.
							<c:out value="${paymentBalance2}" />
						</p>
					</div>
				</div>
			</div>
			<!--/span-->
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Total  Due Amount :</label>
					<div class="col-md-8 text-danger">
						<p class="form-control-static">
							<del>
								<b style="font-size: 14px;">&#2352;</b>
							</del>
							<c:set var="paymentAmt" value='${totalAmount}' />
							<fmt:formatNumber value="${paymentAmt}" type="currency"
								pattern="##,##,#00.00" var="paymentBalance" />
							<c:out value="${paymentBalance}" />
						</p>
					</div>
				</div>
			</div>
			<!--/span-->
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Total Discount Amount :</label>
					<div class="col-md-8 text-danger">
						<p class="form-control-static">
							<del>
								<b style="font-size: 14px;">&#2352;</b>
							</del>
							<c:set var="paymentAmt4" value='${fourteenTotalAmount}' />
							<fmt:formatNumber value="${paymentAmt4}" type="currency"
								pattern="##,##,#00.00" var="paymentBalance4" />.
							<c:out value="${paymentBalance4}" />
						</p>
					</div>
				</div>
			</div>
			<!--/span-->
		</div>
		
		<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_7">
			<thead>
				<tr>
					<th>
						Student Name
					</th>
					<th>
						Term Name - Due Date
					</th>
					<th>
						Parent Phone#
					</th>
					<th>
						Total Amount
					</th>
					<th>
						Balance
					</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="studentsFeeTypeList">
					<s:set name="rollNum" value="rollNumber" />
					<tr>
						<td>
							<s:property value="firstName" />
						</td>
						<td>
							<s:property value="termName" /> - <s:property value="dueDateStr" />
						</td>
						<td>
							<s:property value="phoneNumber" />
						</td>
						<td>
							<c:set var="paymentAmt" value='${feeAmount}' />
							<fmt:formatNumber value="${paymentAmt}" type="currency"
								pattern="##,##,###.00" var="paymentBalance" />
							<c:out value="${paymentBalance}" />
						</td>
						<td>
							<s:if test="%{paymentAmount !=0}">
								<c:set var="paymentAmt" value='${paymentAmount}' />
								<fmt:formatNumber value="${paymentAmt}" type="currency"
									pattern="##,##,###.00" var="paymentBalance" />
								<c:out value="${paymentBalance}" />
							</s:if>
							<s:else>
											00.00
										</s:else>
						</td>
						<s:set name="termNameStr" value="termName"></s:set>
				</s:iterator>
			</tbody>
		</table>
		<s:form action="ajaxCommonFeeCollectionAndDues" theme="simple" namespace="/reports" id="feeDefaultersExcel" cssClass="form-horizontal">
			<input type="hidden" name="pdfId" value="pdf" />
			<input type="hidden" name="tempId" value="<s:property value='tempId'/>" />
			<s:hidden name="queryString" value="Class Wise Defaulters"></s:hidden>
			<input type="hidden" name="selectedId" id="classNameIds" value="<s:property value='classId'/>"/>
			<input type="hidden" name="anyId" class="anyId"/>
			<input type="hidden" name="empId" id="empId" value="<s:property value="tempString"/>"/>
				<div class="" id="singleClass">
					<div class="form-actions fluid">
						<div class="col-md-offset-2 col-md-9">
							<%-- <s:submit type="submit" value="Generate Pdf" cssClass="btn blue PDF generateReport" title="generate report" /> --%>
							<s:submit type="submit" value="Generate Excel" cssClass="btn blue Excel generateExcelReport" title="Generate Excel" />
							<%-- <s:submit type="submit" value="Send SMS" cssClass="btn blue SMS generateReport" title="Send SMS" /> --%>
						</div>
					</div>
				</div>
		</s:form>
		<s:form action="ajaxCommonFeeCollectionAndDues" theme="simple" namespace="/reports" id="feeDefaultersSMS" cssClass="form-horizontal">
			<input type="hidden" name="tempId" value="<s:property value='tempId'/>" />
			<s:hidden name="queryString" value="Class Wise Defaulters"></s:hidden>
			<input type="hidden" name="selectedId" id="classNameIds" value="<s:property value='classId'/>"/>
			<input type="hidden" name="anyId" class="anyId"/>
			<input type="hidden" name="empId" id="empId" value="<s:property value="tempString"/>"/>
			<div class="">
				<div style="margin-top: -33px;margin-left: 74px;float: left;" id="sendSmsDiv" class="smsClassDiv">
					 <sj:submit cssClass="btn blue SMS generateSMSReport" value="Send SMS"  targets="FeeCollectionDetailsDiv" validate="true" onBeforeTopics="validateSendSMS" formIds="feeDefaultersSMS"/>
				</div>
			</div>	
		</s:form>
	</s:if>
</div>
<s:else>
	<div class="spaceDiv"></div><div class="spaceDiv"></div>
	<div class="alert alert-info">
		There are no students fee defaulters.
	</div>
</s:else>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<script type="text/javascript">
$(document).ready(function() {
	//UIExtendedModals.init();
	//TableEditable.init();
	var classId='<s:property value="classId"/>';	 
	//alert(classId);
	$("#classNameIds").val(classId+",0");
	var termId='<s:property value="tempString"/>';	 
	$("#empId").val(termId);
});
$('input.generateExcelReport').mouseover(function(){
 		$('.anyId').val('Excel');
});	

$('input.generateSMSReport').mouseover(function(){
	$('.anyId').val('SMS');
});	
TableAdvanced.init();
TableEditable.init();
UIExtendedModals.init()
var classWiseType =  $("input[name=SelectType]:checked").val();
if(classWiseType=="classSectionWise"){
$("div#singleClass").addClass("form-body");
$("div.smsClassDiv").css("margin-top", "-43px");
}
</script>
