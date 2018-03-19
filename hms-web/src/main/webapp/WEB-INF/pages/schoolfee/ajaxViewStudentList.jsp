<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{studentsList != null && !studentsList.isEmpty()}">
   <div class="form-group">
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
	</div>
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
					#Roll
				</th>
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
					Regular Fee
				</th>
				<th>
					Other Fee
				</th>
				<th>
					Next Academic Year Fee 
				</th>
				<th>
					Receipt <!-- / Delete -->
				</th>
			</tr>
		</thead>
		<tbody>
		<s:set var="classNameId" value=""></s:set>
			<s:iterator value="studentsList">
				<s:set var="studentDetailsId" value="studId"></s:set>
				<tr>
					<td align="center">
						<s:property value="rollNumber" />
					</td>
					<td align="center">
						<s:property value="admissionNumber" />
					</td>
					<td>
						<s:property value="fullName" />
					</td>
					<td>	
						<s:property value="classAndSection" />
					</td>
					<s:if test='%{feeConfigured == "Y"}'>
						<td align="center">
							<s:if test='%{feeModuleUsege == "W" && feePaidStatus != "F" && feePaidStatus != "C"}'>
								 <s:url id="editPayment" namespace="/schoolfee" action="ajaxFeeSettingInvoice" includeParams="all" escapeAmp="false">
									<s:param name="id" value="%{studId}" />
									<s:param name="paymentStatus" value="%{paymentStatus}" />
									<s:param name="description">studInvPaymt</s:param>
							</s:url>
								<sj:a href="%{editPayment}" targets="searchStudentsForm112"> Pay </sj:a> / 
							</s:if>
							<a data-toggle="modal"  href="#popupStudPaymentDiv"  
							         onclick="javascript:popupViewFeePayments(<s:property value="studId" />,'P',null);"> View
							</a>
						</td>
					</s:if>
					<s:else>
						<td align="center">-</td>
					</s:else>
					
					
					<td align="center">
						<%-- <a data-toggle="modal"  href="#popupFinePaymentDiv"  
								onclick="javascript:PopupAddFineFeePayment(<s:property value="studId" />,'<s:property value="fullNameAndClassName" />');"> Pay
						</a> --%>
						<s:url id="fineFeePayment" namespace="/schoolfee" action="ajaxDoFineFeePayment" includeParams="all" escapeAmp="false">
							<s:param name="anyId" value="%{studId}" />
							<s:param name="anyTitle" value="%{fullNameAndClassName}" />
							<s:param name="admissionNumber" value="%{admissionNumber}" />
						</s:url> <sj:a href="%{fineFeePayment}" targets="searchStudentsForm112"> Pay     </sj:a>
					</td>
					
					<s:if test='%{feeConfigured == "Y"}'>
						<td align="center" width="70px">
							<s:if test='%{feeModuleUsege == "W" && (feePaidStatus == "F" || feePaidStatus == "C")}'>
								<s:url id="futureAcademicFee" namespace="/schoolfee" action="ajaxDoFutureAcademicYearStudentFeePayment" includeParams="all" escapeAmp="false">
									<s:param name="studentNumber" value="%{studId}" />
									<s:param name="paymentStatus" value="%{paymentStatus}" />
									<s:param name="tempString" value="%{anyId}"></s:param>
									<s:param name="description">studInvPaymt</s:param>
								</s:url>
								<s:if test='%{feeModuleUsege == "W" }'>
									<sj:a href="%{futureAcademicFee}" targets="searchStudentsForm112" onClickTopics="hideShowDivs"
										onCompleteTopics="hilightTermValue" indicator="indicator"> Pay </sj:a>
								</s:if>
								<s:else>
									<s:if test="%{futureAcademicClassSecId != null && futureAcademicClassSecId != 0}"> / 
								   		<a data-toggle="modal" href="#popupStudPaymentDiv" onclick="javascript:popupViewFeePayments(<s:property value="studId" />,'P','futureAcademic');"> View </a>
									</s:if>
								</s:else>	
						
							</s:if>
							<s:else>
								-
							</s:else>
						</td>
					</s:if>
					<s:else>
						<td align="center">-</td>
					</s:else>
					<td align="center">
							<s:if test='%{(feePaidStatus != "N") && (feePaidStatus != "C")}'>
								<a data-toggle="modal"  href="#popupDownloadReceiptDiv" class="btn btn-xs blue" onclick="javascript:PopupStudentDownloadReceipt(<s:property value="studId" />,<s:property value="academicYearId" />);"> Download </a>
							</s:if>
							<s:else>
								-
							</s:else>
					</td>
					<!--<s:if test="%{objectList != null && !objectList.isEmpty()}">
						<s:iterator value="objectList">
							<s:if test="%{#studentDetailsId == id}">
									<td>
									<s:if test='%{paymentStatus != null}'>
										<s:if test='%{paymentStatus == "N"}'>
											<s:url id="editPayment" namespace="/schoolfee"
												action="ajaxFeeSettingInvoice" includeParams="all"
												escapeAmp="false">
												<s:param name="id" value="%{#studentDetailsId}" />
												<s:param name="paymentStatus" value="%{paymentStatus}" />
												<s:param name="description">studInvPaymt</s:param>
											</s:url>
											<sj:a href="%{editPayment}" targets="searchStudentsForm112">
												Pay
										   </sj:a> / 
										</s:if>
										<a data-toggle="modal"  href="#popupStudPaymentDiv"  
												onclick="javascript:popupViewFeePayments(<s:property value="#studentDetailsId" />,'P',null);"> View
										</a>
										
										</s:if>
										<s:else>
											-
										</s:else>
									</td>
									<td>
										<s:url id="futureAcademicFee" namespace="/schoolfee"
												action="ajaxDoFutureAcademicYearStudentFeePayment" includeParams="all"
												escapeAmp="false">
												<s:param name="studentNumber" value="%{#studentDetailsId}" />
												<s:param name="paymentStatus" value="%{paymentStatus}" />
												<s:param name="tempString" value="%{anyId}"></s:param>
												<s:param name="description">studInvPaymt</s:param>
											</s:url>
											<sj:a href="%{futureAcademicFee}" targets="searchStudentsForm112"
												onClickTopics="hideShowDivs"
												onCompleteTopics="hilightTermValue" indicator="indicator">
														Pay
										   </sj:a> 
										   <s:if test="%{futureAcademicClassSecId != null && futureAcademicClassSecId != 0}">
										   		/ 
										   	<a data-toggle="modal"  href="#popupStudPaymentDiv"  
												onclick="javascript:popupViewFeePayments(<s:property value="#studentDetailsId" />,'P','futureAcademic');"> View
											</a>
										   </s:if> 
									</td>
									<td>
										<s:if test='%{paymentStatus != null}'>
											<a data-toggle="modal"  href="#popupDownloadReceiptDiv"  
												onclick="javascript:PopupStudentDownloadReceipt(<s:property value="#studentDetailsId" />,<s:property value="academicYearId" />);"> Download
											</a>
										</s:if>
										<s:else>
											-
										</s:else>
									</td>
								</s:if>
						</s:iterator>
					</s:if>
					<s:else>
						<td align="center">-</td>
						<td align="center">-</td>
						<td align="center">-</td>
					</s:else> -->
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
	function PopupAddFineFeePayment(studentId,nameAndClassName,admissionNumber) {
		var url = jQuery.url.getChatURL("/schoolfee/ajaxDoFineFeePayment.do");
		var pars = "anyId=" + studentId+"&anyTitle="+nameAndClassName+"&admissionNumber="+admissionNumber;
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
		var pars = "id=" + studentId+"&paymentStatus="+paymentStatus+"&anyTitle="+futureAcademicFee;
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
