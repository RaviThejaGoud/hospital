<%@ include file="/common/taglibs.jsp"%>
<head>
	<title>eazySchool | Student Payments</title>
</head>
<body />
	<div class="wrapper container_16">
		<div class="grid_16 block grid_16MarginLeft">
			<div class="block_head">
				<h2>
					Student/Staff Payments
				</h2>
				<div id="topMenu">
				  <s:if test='%{user.isOnlySchoolAdmin=="Y" || user.isSchoolFinance=="Y" ||user.isSchoolTransport=="Y" || user.isSchoolHostel=="Y"}'>
					<ul>
						<li>
							<s:url id="urlSchoolTransportInvice"
								action="ajaxViewHostelStudentInvoiceModifyers" namespace="/hostel"/>
							<sj:a href="%{urlSchoolTransportInvice}"
								targets="searchStudentsForm112" indicator="indicator">Delete / Modify Today's Receipts</sj:a>
						</li>

					</ul>
					</s:if>
				</div>
			</div>
			<div class="block_content" id="searchStudentsForm112">
					<jsp:include
								page="/WEB-INF/pages/hostel/fee/searchStudentDetails.jsp" />
		</div>
	</div>
	</div>
	<script type="text/javascript">
$('#financeHostelHome').addClass('current');
$(document).ready(
		function() {
			$.subscribe('searchStudentForm', function(event, data) {
				var rollNumber = $('#rollNumber').val();
				if (rollNumber == null || rollNumber == ''
						|| rollNumber == 'Enter Student Number.' || rollNumber=='Student First or Last Name.') {
					alert("Please enter first name or last name.");
					return false;
					
				}
				else if(rollNumber.length < 3){
					alert("Please enter minimum 3 characters.");
					$('#rollNumber').val('Student First or Last Name');
					return false;
				}
				 else
					$('.links').hide();
				    $(".hideSearchStudentBody").show()
					return true;
			});
			$.subscribe('searchStaffForm', function(event, data) {
				var rollNumber = $('#staffKeyword').val();
				if (rollNumber == null || rollNumber == ''
						|| rollNumber == 'Enter Staff Number.' || rollNumber=='Staff First or Last Name.') {
					alert("Please enter first name or last name.");
					return false;
					
				}
				else if(rollNumber.length < 3){
					alert("Please enter minimum 3 characters.");
					$('#rollNumber').val('Staff First or Last Name');
					return false;
				}
				 else
					$('.links').hide();
				    $(".hideSearchStudentBody").show()
					return true;
			});
			$.subscribe('doInitEditStudent', function(event, data) {
				if ($('#' + data.id).is(":hidden")) {
					$('#' + data.id).show();
				} else {
					$('#' + data.id).hide();
				}
			});
			$.subscribe('doInitViewStudent', function(event, data) {
				if ($('#' + data.id).is(":hidden")) {
					$('#' + data.id).show();
				} else {
					$('#' + data.id).hide();
				}
			});
		});
</script>