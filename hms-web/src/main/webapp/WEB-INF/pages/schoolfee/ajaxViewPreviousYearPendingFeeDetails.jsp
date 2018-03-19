<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />
<s:if test="%{studentsList != null && !studentsList.isEmpty()}">
   <!-- <div class="form-group">
		<div class="panel-body col-md-12">
			<div class="col-md-1">
				<span class="label label-danger"> NOTE : </span>
			</div>
			<div class="col-md-10">
				<ul>
					<li>
						<b>'-'</b> indicates <strong>Fee not configured</strong>.
					</li>
					<li>
						'Pay' indicates <strong>Student not made full payment </strong>. 
					</li>
					<li>
						'Pay/View' indicates <strong>Student has balance amount</strong>. 
					</li>
					<li>
						'View' indicates <strong>Student made full payment </strong>. 
					</li>
					<li>
						For better view to print challan use Firefox.
					</li>
				</ul>
			</div>
		</div>
	</div> -->
	<s:if test="%{(smsAlloted == 0) || (smsAlloted <= smsCnt)}">
		<div style="color: red;" class="alert alert-info col-md-12">
			You have been used all your allotted
			<s:property value="smsAlloted" />
			SMS. Please contact EazySchool support team (080-46620999) to recharge your SMS.
		</div>
	</s:if>
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
				 	Class & Section
				</th>
				<th>
					Due Amount
				</th>
				<th>
					Regular Fee
				</th>
				<th>
					Receipt <!-- / Delete -->
				</th>
			</tr>
		</thead>
		<tbody>
		<s:set var="classNameId" value=""></s:set>
			<s:iterator value="studentsList" status="stat">
				<s:set var="studentDetailsId" value="studId"></s:set>
				<tr>
					<td>
						<s:property value="studentsList[#stat.count-1][2]" />
					</td>
					<td>
						<s:property value="studentsList[#stat.count-1][1]" />
					</td>
					<td>	
						<s:property value="studentsList[#stat.count-1][3]" />-<s:property value="studentsList[#stat.count-1][4]" />
						<s:property value="classAndSection" />
					</td>
					<td>
						<s:property value="studentsList[#stat.count-1][0]" />
					</td>
					<td align="center">
						 <s:url id="editPayment" namespace="/schoolfee" action="ajaxFeeSettingInvoice" includeParams="all" escapeAmp="false">
							<s:param name="id" value="%{studentsList[#stat.count-1][7]}" />
							<s:param name="paymentStatus" value="%{paymentStatus}" />
							<s:param name="description">prevStudInvPaymt</s:param>
						</s:url>
						<sj:a href="%{editPayment}" targets="searchStudentsForm112"> Pay </sj:a> / 
						<a data-toggle="modal"  href="#popupStudPaymentDiv"  
								onclick="javascript:popupViewFeePayments(<s:property value="%{studentsList[#stat.count-1][7]}" />,'P',null);"> View
						</a>
					</td>
					
					
					
					
					<td align="center">
							<s:if test='%{(studentsList[#stat.count-1][8] != "N") && (studentsList[#stat.count-1][8] != "C")}'>
								<a data-toggle="modal"  href="#popupDownloadReceiptDiv" class="btn btn-xs blue" onclick="javascript:PopupStudentDownloadReceipt(<s:property value="studentsList[#stat.count-1][7]" />,<s:property value="academicYearId" />);"> Download </a>
							</s:if>
							<s:else>
								-
							</s:else>
					</td>
					
					</tr>
				<s:set var="classNameId" value="classId"></s:set>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		Oops! system couldn't find any match with keyword. (or) Fee not assign respective class (or) Try by
		correcting the word
	</div>
</s:else>
<s:if test='%{studentPayment.paymentType!="CL" }'>
	<form method="post" id="printReport"
		action="javaScript:printPreview('<s:property value="studentNumber" />','<s:property value="todayDate"/>','<s:property value="quizId" />','<s:property value="empId" />','payandprint','<s:property value="tempId1" />','<s:property value="alertSendType"/>')"
		style="display: none;"></form>
</s:if>
<s:if test='%{studentPayment.paymentType=="CL" }'>
	<form method="post" id="printChallana"
		action="javaScript:printChallanaPreview('<s:property value="studentNumber" />','<s:property value="todayDate"/>','<s:property value="quizId" />','<s:property value="empId" />','payandprint','<s:property value="tempId1" />','<s:property value="alertSendType"/>')"
		style="display: none;"></form>
</s:if>
<div id="popupDownloadReceiptDiv"></div>
<!-- <div id="popupFinePaymentDiv"></div> -->
<div id="popupStudPaymentDiv"></div>
<script type="text/javascript">
$(document).ready(function() {
	/* var selected =$('input[name=SelectType]:checked').val();
	if(selected == 'Select Class'){
		$("div.alert-info").html('Currently there are no students found to selected class.');
	} */
});
	 TableAdvanced.init();
	UIExtendedModals.init();
	function PopupStudentDownloadReceipt(studentDetailsId,academicYearId,studentPayment) {
		var url = jQuery.url.getChatURL("/schoolfee/ajaxStudentDownloadReceipt.do");
		var pars = "anyId=" + studentDetailsId+"&tempId2="+academicYearId;
		$('#popupDownloadReceiptDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
				url : url,
				cache : false,
				data : pars,
				success : function(html) {
					$("#popupDownloadReceiptDiv").html(html);
				}
			});
		}
	function PopupAddFineFeePayment(studentId,nameAndClassName) {
		var url = jQuery.url.getChatURL("/schoolfee/ajaxDoFineFeePayment.do");
		var pars = "anyId=" + studentId+"&anyTitle="+nameAndClassName;
		$('#popupFinePaymentDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
				url : url,
				cache : false,
				data : pars,
				success : function(html) {
					$("#popupFinePaymentDiv").html(html);
				}
			});
	}
		
	function popupViewFeePayments(studentId,paymentStatus,futureAcademicFee) {
		var url = jQuery.url.getChatURL("/schoolfee/ajaxViewFeeSettingInvoice.do");
		var pars = "id=" + studentId+"&paymentStatus="+paymentStatus+"&tempString="+futureAcademicFee;
		$('#popupStudPaymentDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
				url : url,
				cache : false,
				data : pars,
				success : function(html) {
					$("#popupStudPaymentDiv").html(html);
				}
			});
	}
		
		
</script>
