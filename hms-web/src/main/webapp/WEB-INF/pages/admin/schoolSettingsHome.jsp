<%@ include file="/common/taglibs.jsp"%>
<div class="wrapper container_16">
	<div class="wrapper">
		<div class="grid_16 block grid_16MarginLeft">
			<div id="academicsContent">
				<div class="grid_16">
					<div class="block_head">
						<h2>
							Academic Planner:
							<s:property value="#session.academicYearName" />
						</h2>
						<div class="lableRight subMenus">
						<ul>
							 <li >
								<s:url id="urlUploadCustEnroll" action="ajaxDoUploadCustomerEnrollmentSheet" namespace="/admin">
								</s:url>
								<sj:a id="uploadEnrollSheet" href="%{urlUploadCustEnroll}" targets="academicsBlockContent">Upload Customer Enrollment Sheet</sj:a> 	
							 </li>
						</ul>
						</div>
					</div>
					<div class="block_content" id="academicsBlockContent">
					<%@ include file="/common/messages.jsp"%>
						<div id="tabContent">
							<div id="tabWrapper">
								<div id="tabNavigation" style="display: none;">
									<ul>
										<!--<li class="selected">
											<a href="#" id='/admin/ajaxSchoolSettings.do' class=''  type='academicConfiguration'>Configuration</a>
										</li>
										-->
										<li class="selected">
											<a href="#" id='/admin/ajaxAcademicSchoolSettings.do'
												class='academicYearId=<s:property value="#session.academicYear" />'
												type='stepAcademicYear'>Academic Planner</a>
										</li>
										<li class="manageSchoolId">
											<a href="#" id='/admin/ajaxDoSchoolInformation.do' class='' type='stepSchoolDetails'>Manage School</a>
										</li>
										<s:if test="%{customer.hostelModuleStatus}">
											<li>
											<a href="#" id='/admin/ajaxViewHostelDetails.do' class='academicYearId=<s:property value="#session.academicYear" />' type='stepHostelDetails'>Manage Hostel</a>
										</li>
										</s:if>
										<li>
											<a href='#' id='/admin/ajaxViewEvents.do'
												class='academicYearId=<s:property value="#session.academicYear" />'
												type='stepEvents'>Events</a>
										</li>
										<li>
											<a href='#' id='/admin/ajaxViewSchoolSettingsHolidays.do'
												class='academicYearId=<s:property value="#session.academicYear" />'
												type='stepHolidays'>Holidays</a>
										</li>
										<li>
											<a href='#' id='/admin/ajaxGetAdminCalendar.do'
												class='academicYearId=<s:property value="#session.academicYear" />'
												type='stepCalendar'>Calendar</a>
										</li>
										<s:if test='%{smsCnt}'>
										<li>
											<a href='#' id='/admin/ajaxTotalSmsCount.do'
												class='academicYearId=<s:property value="#session.academicYear" />'
												type='stepTotalSmsCount'>Used SMS (<s:property value="smsCnt" />)</a>
										</li>
										</s:if>
									</ul>
								</div>
								<div id="steps" class='manageAcademicBlockContent'>
									<fieldset class="step" id='stepAcademicYear'>
									</fieldset>
									<fieldset class="step" id='stepSchoolDetails'>
									</fieldset>
									<s:if test="%{customer.hostelModuleStatus}">
										<fieldset class="step" id='stepHostelDetails'>
										</fieldset>
									</s:if>
									<fieldset class="step" id='stepEvents'>
									</fieldset>
									<fieldset class="step" id='stepHolidays'>
									</fieldset>
									<fieldset class="step" id='stepCalendar'>
									</fieldset>
									<fieldset class="step" id='stepTotalSmsCount'>
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
<script Language="Javascript1.2" type="text/javascript">
	$(document).ready(
		function() {
			$('#ui-datepicker-div').hide(); 
			changePageTitle("Academics Planer");
			$('#schoolSettings').addClass('current');
			commonLoadTab();
			if ($('#tabNavigation ul li').hasClass("selected").toString()) {
				genericAjaxCall($('#tabNavigation ul li a').attr('id'), $(
						'#tabNavigation ul li a').attr('type'), $(
						'#tabNavigation ul li a').attr('class'));
			}
		});
		
</script>