<%@ include file="/common/taglibs.jsp"%>
<div class="wrapper container_16">
	<div class="wrapper">
		<div class="grid_16 block grid_16MarginLeft">
			<div id="timeTableMainCont">
				<div class="grid_16">
					<div class="block_head">
						<h2>
							TimeTable
						</h2>
							<a href="${pageContext.request.contextPath}/admin/adminManageStaffAndStudent.do" class="right">Back to Admin</a>
					</div>
					<div class="block_content" id="timeTableBodyCont">
						<div id="tabContent">
							<div id="tabWrapper">
								<div id="tabNavigation" style="display: none;">
									<ul>
										<li class="selected">
											<a href="#" id='/admin/ajaxGetSchoolPeriods.do' class=''  type='stepSchoolPeriods'>Periods</a>
										</li>
										<li>
											<a href="#" id='/admin/ajaxGetTimeTableClassSubjectsSettings.do' class=''  type='stepSubDet'>Subjects Settings</a>
										</li>
										<li >
											<a href='#' id='/admin/ajaxViewTeacherSubjects.do' class=''  type='stepTeacherClasses'>Staff Class Subjects</a>
										</li>
										<li>
											<a href="#" id='/admin/ajaxViewCombinedClassSubjects.do' class=''  type='stepCombinedClassSubjects'>Combined ClassSubjects</a>
										</li>
										<li>
											<a href='#' id='/admin/ajaxViewManageTimeTable.do' class=''  type='stepTimeTableCont'>Generate Timetable</a>
										</li>
										<li>
											<a href="#" id='/timeTable/ajaxManageFETTimeTable.do' class=''  type='stepsFETCont'>FET Timetable</a>
										</li>
										<!--<li>
											<a href="#" id='/admin/ajaxDoFeeCategory.do' class=''  type='stepClassFee'>Class Fee</a>
										</li>
									--></ul>
								</div>
								<div id="steps" class='manageTimeTableBlockContent'>
									<fieldset class="step" id='stepSchoolPeriods'> 
			                        </fieldset> 
			                        <fieldset class="step" id='stepSubDet'> 
			                        </fieldset> 
									<fieldset class="step" id='stepTeacherClasses'> 
			                        </fieldset>
			                        <fieldset class="step" id='stepCombinedClassSubjects'> 
			                        </fieldset>
			                         <fieldset class="step" id='stepTimeTableCont'> 
			                        </fieldset>
			                        <fieldset class="step" id='stepsFETCont'> 
			                        </fieldset>
			                        <!--<fieldset class="step" id='stepClassFee'> 
			                        </fieldset>
								--></div>
								
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script Language="Javascript1.2" type="text/javascript">
$(document).ready(function() {
	changePageTitle("Manage TimeTable");
	$('#adminStaffAndStudent').addClass('current');
	commonLoadTab();
	if($('#timeTableBodyCont #tabNavigation ul li').hasClass("selected").toString()){
			genericAjaxCall($('#timeTableBodyCont #tabNavigation ul li a').attr('id'),$('#timeTableBodyCont #tabNavigation ul li a').attr('type'), $('#timeTableBodyCont #tabNavigation ul li a').attr('class'));
	}
});
</script>