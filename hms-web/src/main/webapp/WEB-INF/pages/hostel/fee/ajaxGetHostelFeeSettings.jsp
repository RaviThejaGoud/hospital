<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<div class="wrapper container_16">
	<div class="wrapper">
		<div class="grid_16 block grid_16MarginLeft">
			<div id="hostelTermsContent">
				<div class="grid_16">
					<div class="block_head">
						<h2>
							Financial Information:
							<s:property value="#session.academicYearName" />
						</h2>
					</div>
					<div class="block_content" id="academicsBlockContent">
						<div id="tabContent">
							<div id="tabWrapper">
								<div id="tabNavigation" style="display: none;">
									<ul>
										<li>
											<a href="#" id='/hostel/ajaxDoHostelFeeCategory.do'
												class='academicYearId=<s:property value="#session.academicYear" />'
												type='stepHostelClassFee'>Hostel Fee</a>
										</li>
										<li>
											<a href="#" id='/hostel/ajaxViewHostelFeeSettings.do'
												class='academicYearId=<s:property value="#session.academicYear" />'
												type='stepHostelFees'>Terms &amp; Fees</a>
										</li>
										<li>
											<a href="#" id='/hostel/ajaxAdminGetFourteenSchoolFee.do'
												class='academicYearId=<s:property value="#session.academicYear" />'
												type='stepPaymentDefaulters'>Payment Defaulters</a>
										</li>
										<%--<li>
											<a href="#" id='/hostel/ajaxAllAdminSchoolFeeReports.do'
												class='academicYearId=<s:property value="#session.academicYear" />'
												type='stepReports'>Reports</a>
										</li>
									--%></ul>
								</div>
								<div id="steps" class='manageAcademicBlockContent'>
									<fieldset class="step" id='stepHostelClassFee'>
									</fieldset>
									<fieldset class="step" id='stepHostelFees'>
									</fieldset>
									<fieldset class="step" id='stepPaymentDefaulters'>
									</fieldset>
									<fieldset class="step" id='stepReports'>
									</fieldset>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$('#academicsBlockContent').addClass('current');
$(document).ready(
		function() {
			$('#adminAcademic').addClass('current');
			commonLoadTab();
			if ($('#tabNavigation ul li').hasClass("selected").toString()) {
				genericAjaxCall($('#tabNavigation ul li a').attr('id'), $(
						'#tabNavigation ul li a').attr('type'), $(
						'#tabNavigation ul li a').attr('class'));
			}
		});
</script>