<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<div class="wrapper container_16">
	<div class="wrapper">
		<div class="grid_16 block grid_16MarginLeft">
			<div class="block_head">
						<h2>
							Financial Information : <s:property value="#session.academicYearName" />
						</h2>
					</div>
					<div class="block_content" id="academicsBlockContent">
						<!-- <div class="blockHeader"><h2>Manage Class</h2></div> -->
						<div id="tabContent">
							<div id="tabWrapper">
								
								<div id="tabNavigation" style="display: none;">
									<ul>
										<!--<li class="selected">
											<a href="#" id='/admin/ajaxSchoolSettings.do' class=''  type='academicConfiguration'>Configuration</a>
										</li>
										-->
										<li class="selected">
											<a href="#" id='/schoolfee/ajaxDoFeeCategory.do' class='academicYearId=<s:property value="#session.academicYear" />'  type='stepClassFee'>Class Fee</a>
										</li>
										<li>
											<a href="#" id='/admin/ajaxAdminGetFourteenSchoolFee.do' class='academicYearId=<s:property value="#session.academicYear" />'  type='stepPaymentDefaulters'>Payment Defaulters</a>
										</li>
										<!--<li>
											<a href="#" id='/admin/ajaxAllAdminSchoolFeeReports.do' class='academicYearId=<s:property value="#session.academicYear" />'  type='stepReports'>Reports</a>
										</li>-->
										<li>
											<a href="#" id='/schoolfee/ajaxSchoolFeeSetting.do' class='academicYearId=<s:property value="#session.academicYear" />'  type='stepFees'>Terms &amp; Fees</a>
										</li>
										<li>
	                                         <a href="#" id='/admin/ajaxDoGetDayBookDetails.do' class='' type='stepDayBook'>DayBook</a>
                                       </li>
									</ul>
								</div>
								<div id="steps" class='manageAcademicBlockContent'>
									<!--<fieldset class="step" id='academicConfiguration'> 
			                        </fieldset> 
			                        -->
			                        <fieldset class="step" id='stepClassFee'> 
			                        </fieldset> 
			                        <fieldset class="step" id='stepPaymentDefaulters'> 
			                        </fieldset>
			                        <%-- <fieldset class="step" id='stepReports'> 
			                            </fieldset>   --%>
			                        <fieldset class="step" id='stepFees'> 
			                        </fieldset>
			                        <fieldset class="step" id='stepDayBook'> 
			                        </fieldset>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
<script type="text/javascript">
$('#manageSettings').addClass('current');
$(document).ready(function() {
	$('#adminAcademic').addClass('current');
	commonLoadTab();
	if($('#tabNavigation ul li').hasClass("selected").toString()){
			genericAjaxCall($('#tabNavigation ul li a').attr('id'),$('#tabNavigation ul li a').attr('type'), $('#tabNavigation ul li a').attr('class'));
	}
});
</script>