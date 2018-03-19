<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i> Student Payments
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<s:if test='%{feeModuleUsege == "W" && customer.chalanaGenerationStatus == "Y"}'>
							<li id="challanaDivId">
								<s:url id="generatChallana" action="ajaxManageGeneratChallana" namespace="/schoolfee" />
								<sj:a href="%{generatChallana}" id="generatChallana"
									targets="searchStudentsForm112" data-toggle="tab">Verify Challan</sj:a>
							</li>
						</s:if>
					<s:if test='%{feeModuleUsege == "W" }'>
						<s:if test="%{#session.customerHostelMode}">
							<li>
								<s:url id="studentPocketMoney" action="ajaxManageStudentpocketMoney" namespace="/schoolfee" />
								<sj:a href="%{studentPocketMoney}"
									targets="searchStudentsForm112" data-toggle="tab">Student Pocket Money</sj:a>
							</li>
						</s:if>
						<li>
								<s:url id="studentExcelStudentInvoice" action="ajaxDoDownloadAndUploadStudentInvoice" namespace="/schoolfee" />
									<sj:a href="%{studentExcelStudentInvoice}"
										targets="searchStudentsForm112" data-toggle="tab">Import Students Payment   </sj:a>
					</li>
						</s:if>
						<s:if test='%{customer.showPreviousYearPendingFee == "Y"}'>
							<s:if test="%{studentSize > 0}">
								<li class="" id="searchStudentsForMakePaymentTest">
								<s:url id="viewPreviousPendingFeeDetails" action="ajaxViewPreviousPendingFeeDetails" namespace="/schoolfee" >
									<s:param name="academicYearId"><s:property value="tempId"/></s:param>
								</s:url>
									<sj:a href="%{viewPreviousPendingFeeDetails}"
										targets="searchStudentsForm112" data-toggle="tab">Collect <s:property value="tempString"/> Fee</sj:a>
							</li>
							</s:if>
						</s:if>
						<li class="active" id="searchStudentsForMakePaymentTest">
							<s:url id="searchStudentsForMakePayment" action="ajaxSearchStudentsForMakePayment" namespace="/schoolfee" />
								<sj:a href="%{searchStudentsForMakePayment}"
									targets="searchStudentsForm112" data-toggle="tab">Collect Fee</sj:a>
							
							<%-- <a
								href="${pageContext.request.contextPath}/schoolfee/adminGetSchoolFee.do?id=Fee"
								id="Fee"><span class="title">Collect Fee </span> <span
								class="selected" data-toggle="tab"></span> </a> --%>
						</li>
					</ul>
					<div class="tab-content" id="searchStudentsForm112">
					<jsp:include
							page="/WEB-INF/pages/schoolfee/searchStudentDetails.jsp" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	//$('#financeHome').addClass('current');
	$(document).ready(function() {
		changePageTitle("Student Invoice");
		$.subscribe('searchStudentForm', function(event, data) {
		    $('#makePayment').hide();
			var rollNumber = $('#rollNumber').val();
			if (rollNumber == null || rollNumber == '' || rollNumber=='Student First or Last Name') {
				alert("Please enter first name or last name.");
				event.originalEvent.options.submit=false;
			}
			else if(rollNumber.length < 3){
				alert("Please enter minimum 3 characters.");
				$('#rollNumber').val('');
				event.originalEvent.options.submit=false;
			}
			 else{
			    $(".hideSearchStudentBody").show();
			    $('#searchStudentsList').show();
				return true;
			}
		});
		
		$.subscribe('searchStudentAdmissionForm', function(event, data) {
		    $('#makePayment').hide();
			var admissionNumber = $('#admissionNumber').val();
			if (admissionNumber == null || admissionNumber == '' || admissionNumber == 'Student Admission Number') {
				alert("Please enter student admission number.");
				event.originalEvent.options.submit=false;
				
			}
			else if(admissionNumber.length < 2){
				alert("Please enter minimum 2 characters.");
				$('#admissionNumber').val('');
				event.originalEvent.options.submit=false;
			}
			 else{
			    $(".hideSearchStudentBody").show()
			    $('#searchStudentsList').show();
				return true;
			}
		});
	});
	$('html, body').animate({ scrollTop: $(document).height() - $(window).height() }, 10, function() {
	    $(this).animate({ scrollTop: 0 }, 10);
	});
</script>