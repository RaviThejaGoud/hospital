<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Transfer Students
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<li>
							<s:url id="doTransferStudent" action="ajaxTransferStudentDetails"
								namespace="/student" includeParams="all" escapeAmp="false" />
							<sj:a href="%{doTransferStudent}" targets="classAdmissionDiv"
								data-toggle="tab">
						Transfer Student Details
						</sj:a>
						</li>
						<li>
							<s:url id="doIntraTransferStudent"
								action="ajaxIntraTransferStudentDetails" namespace="/student"
								includeParams="all" escapeAmp="false" />
							<sj:a href="%{doIntraTransferStudent}"
								targets="classAdmissionDiv" data-toggle="tab">
						Intra Branch Transferred Students
						</sj:a>
						</li>
						<li class="active">
							<s:url id="urlStudentTransfer" action="ajaxStudentTransfer"
								namespace="/student" />
							<sj:a href="%{urlStudentTransfer}" targets="mainContentDiv"
								data-toggle="tab">
							 View Student Transfer</sj:a>
						</li>
					</ul>
					<div id="classAdmissionDiv" class="tab-content">
					<%@ include file="/common/messages.jsp"%>
						<s:form id="searchStudentByAdmissionNumber"
							action="ajaxSearchTransferStudentDetails" theme="simple"
							cssClass="form-horizontal" namespace="/student">
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label col-md-4">
											Search Student :
										</label>
										<div class="col-md-8">
											<div class="input-group">
												<sj:textfield name="selectedId" id="admissionNumber"
													cssClass="form-control"
													placeholder="Student Admission Number"></sj:textfield>
												<span class="input-group-btn"><sj:submit
														targets="searchStudentsList" value="Find Student"
														cssClass="btn blue" indicator="indicator"
														cssStyle="float:none;" validate="true"
														onBeforeTopics="searchStudentAdmissionForm"
														formIds="searchStudentByAdmissionNumber" /> </span>
											</div>
											<span class="hint-message"> (Type at least 3 chars and
												hit 'Find Student'.) </span>
										</div>
									</div>
								</div>
							</div>
						</s:form>
						<div id="searchStudentsList"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<script type="text/javascript">
changePageTitle("Manage Student");
$(document).ready(function() {
	$.destroyTopic('searchStudentAdmissionForm');
	$(".hideSearchStudentBody").show();//hide the all of the element with class msg_body
		$.subscribe('searchStudentAdmissionForm', function(event, data) {
			$('#makePayment').hide();
			var admissionNumber = $('#admissionNumber').val();
			if (admissionNumber == null || admissionNumber == '' || admissionNumber == 'Student Admission Number') {
				alert("Please enter student admission number.");
				event.originalEvent.options.submit=false;

			} else if (admissionNumber.length < 3) {
				alert("Please enter minimum 3 characters.");
				event.originalEvent.options.submit=false;
			} else
				$(".hideSearchStudentBody").show()
			$('#searchStudentsList').show();
			return true;
		});
	});
</script>
