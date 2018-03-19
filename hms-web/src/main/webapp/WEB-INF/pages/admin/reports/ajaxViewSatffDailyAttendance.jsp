<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>
					<span class="hidden-title"></span>
				</div>
			</div>
			<div class="portlet-body">
				<div class="dropdown tabbable tabbable-custom tabbable-full-width">
					<s:if test='%{plTitle == "DateWiseFeeCollection"}'>
						<ul class="nav nav-tabs">
							<li class="dropdown active">
							<a data-hover="dropdown" data-toggle="dropdown" class="dropdown-toggle js-activated" href="#">Fee Reports <b class="caret"></b> </a>
							<ul role="menu" class="dropdown-menu pull-left" style="overflow: visible;">
							<s:if test='%{user.isSchoolStudent=="N" && user.isParent=="N"}'>
									<li class="active">
										<s:url id="StaffDailyAttendanceInPDF"
											action="ajaxStaffDailyAttendDetails" namespace="/reports"
											includeParams="all" escapeAmp="false">
											<s:param name="tempString">Staff</s:param>
											<s:param name="plTitle">DateWiseFeeCollection</s:param>
										</s:url>
										<sj:a href="%{StaffDailyAttendanceInPDF}"
											targets="mainContentDiv" data-toggle="tab">Date Wise Fee Collection</sj:a>
									</li>
									
									</s:if>
									
									<s:if test='%{(user.isOnlySchoolAdmin=="Y") || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y"}'>
										<li>
											<s:url id="userwiseFeeCollection" namespace="/reports" action="ajaxDoUserWiseFeeCollection" escapeAmp="false" includeParams="all">
											<s:param name="plTitle">UsereWiseFeeCollection</s:param>
											</s:url>
											<sj:a href="%{userwiseFeeCollection}"
												targets="FeeCollectionDetailsDiv" data-toggle="tab">User Wise Fee Collection</sj:a>
										</li>
									</s:if>
									
									<s:if test='%{(user.isOnlySchoolAdmin=="Y") || user.isSchoolPrincipal=="Y" || user.isSchoolFinance=="Y" || user.isSchoolHostel=="Y" || user.isSchoolDirector == "Y"}'>
										<li>
											<s:url id="feeCollection" namespace="/reports"
												action="ajaxDoFeeCollection" escapeAmp="false"
												includeParams="all">
											</s:url>
											<sj:a href="%{feeCollection}"
												targets="FeeCollectionDetailsDiv" data-toggle="tab">Periodic Fee Collection Report</sj:a>
										</li>
										<s:if test='%{user.isSchoolHostel=="N"}'>
										<li>
											<s:url id="doFeePaidUnPaidDetails" namespace="/reports"
												action="ajaxDoFeePaidUnPaidDetails" escapeAmp="false"
												includeParams="all">
											</s:url>
											<sj:a href="%{doFeePaidUnPaidDetails}"
												targets="FeeCollectionDetailsDiv" data-toggle="tab">Paid & Unpaid Fee(term wise)</sj:a>
										</li>
										
										<li>
											<s:url id="doFeePaidUnPaidDetails" namespace="/reports"
												action="ajaxDoAllTermsFeePaidUnpaidDetails" escapeAmp="false"
												includeParams="all">
											</s:url>
											<sj:a href="%{doFeePaidUnPaidDetails}"
												targets="FeeCollectionDetailsDiv" data-toggle="tab">Overall Paid & Unpaid Fee(term wise)</sj:a>
										</li>
										<li>
											<s:url id="doConsolidatedFeeCollectionDetails" namespace="/reports"
												action="ajaxDoConsolidatedFeeCollectionDetails" escapeAmp="false"
												includeParams="all">
											</s:url>
											<sj:a href="%{doConsolidatedFeeCollectionDetails}"
												targets="FeeCollectionDetailsDiv" data-toggle="tab">Consolidated Fee Collection</sj:a>
										</li>
										
										</s:if>
									</s:if>
									<s:if
										test='%{(user.isOnlySchoolAdmin=="Y") || user.isSchoolPrincipal=="Y" || user.isSchoolFinance=="Y" || user.isSchoolDirector == "Y"}'>
										<li>
											<s:url id="excessPaymentsStuds" namespace="/reports"
												action="ajaxDoGetStudentsExcessAmounts" escapeAmp="false"
												includeParams="all">
											</s:url>
											<sj:a href="%{excessPaymentsStuds}"
												targets="FeeCollectionDetailsDiv" data-toggle="tab">Students Excess Amount</sj:a>
										</li>
									</s:if>
									<s:if test='%{user.isSchoolHostel=="N" && user.isSchoolStudent=="N" && user.isParent=="N"}'>
									<s:if test='%{customer.showPreviousYearPendingFee == "Y"}'>
										<li>
											<a href='${pageContext.request.contextPath}/reports/ajaxViewPreviosYearDefaultesDefaulters.do?academicYearId=<s:property value="tempId"/>'
												id="sManageLeaves"> <s:property value="tempString"/> Defaulters</span> <span class="selected"></span>
											</a>
											<%-- <s:url id="viewStudentPaymentPreviosYearDefaultesLink"
												action="ajaxViewPreviosYearDefaultesDefaulters" includeParams="all"
												escapeAmp="false" namespace="/reports">
												<s:param name="academicYearId"><s:property value="academicYearId"/></s:param>
											</s:url>
											<sj:a href="%{viewStudentPaymentPreviosYearDefaultesLink}"
												targets="FeeCollectionDetailsDiv" data-toggle="tab"><s:property value="tempString"/> Defaulters</sj:a> --%>
										</li>
									</s:if>
									<li>
										<s:url id="viewStudentPaymentDefaultesLink"
											action="ajaxViewPaymentDefaulters" includeParams="all"
											escapeAmp="false" namespace="/schoolfee">
											<s:param name="title">Sree</s:param>
										</s:url>
										<sj:a href="%{viewStudentPaymentDefaultesLink}"
											targets="FeeCollectionDetailsDiv" data-toggle="tab">Fee Defaulters</sj:a>
									</li>
									</s:if>
									<s:if
										test='%{(user.isOnlySchoolAdmin=="Y") || user.isSchoolPrincipal=="Y" || user.isSchoolFinance=="Y" || user.isSchoolStudent=="Y" || user.isParent=="Y" || user.isSchoolDirector == "Y"}'>
										<li>
											<s:url id="classWiseFeeStructure" namespace="/reports"
												action="ajaxDoGetClassWiseFeeStructure" escapeAmp="false"
												includeParams="all">
											</s:url>
											<sj:a href="%{classWiseFeeStructure}"
												targets="FeeCollectionDetailsDiv" data-toggle="tab">Fee Structure</sj:a>
										</li>
									</s:if>
									<s:if
										test='%{(user.isOnlySchoolAdmin=="Y") || user.isSchoolPrincipal=="Y" || user.isSchoolFinance=="Y" || user.isSchoolDirector == "Y"}'>
										<li>
											<s:url id="doFeeAuditingDetails" namespace="/reports"
												action="ajaxDoFeeAuditingDetails" escapeAmp="false"
												includeParams="all">
											</s:url>
											<sj:a href="%{doFeeAuditingDetails}"
												targets="FeeCollectionDetailsDiv" data-toggle="tab">Fee Auditing Reports</sj:a>
										</li>
										<%-- <li>
											<s:url id="dayBookExcelSheet" namespace="/reports"
												action="ajaxGetDayBookReports" escapeAmp="false"
												includeParams="all">
											</s:url>
											<sj:a href="%{dayBookExcelSheet}"
												targets="FeeCollectionDetailsDiv" data-toggle="tab">DayBook Reports</sj:a>
										</li> --%>
										<li>
											<s:url id="feeReportsByPercentWise" namespace="/reports"
												action="ajaxFeeReportsByClassPercentWise" escapeAmp="false"
												includeParams="all">
											</s:url>
											<sj:a href="%{feeReportsByPercentWise}"
												targets="FeeCollectionDetailsDiv" data-toggle="tab">% Wise Class Fee Reports</sj:a>
										</li>
										<li>
											<s:url id="libraryfineExcelSheet" namespace="/reports"
												action="ajaxGetLibraryFineFeeReports" escapeAmp="false"
												includeParams="all">
											</s:url>
											<sj:a href="%{libraryfineExcelSheet}"
												targets="FeeCollectionDetailsDiv" data-toggle="tab">Library Fine Reports</sj:a>
										</li>
									</s:if>
								</ul>
							</li>
						</ul>
					</s:if>
					<div class="tab-content" id="FeeCollectionDetailsDiv">
					
					<span id="startDateSpan" class="<s:property value='startDate'/>"></span>
					<span id="endDateSpan" class="<s:property value='endDate'/>"></span>
					<span id="toDateSpan" class="<s:property value='toDate'/>"></span>
					
						<div class="form-body">
							<s:if test='%{plTitle == "StaffDailyAttendance(Daily)"}'>
								<div class="form-group">
									<label class="control-label col-md-3">
										<span class="required">*</span>Select Date :
									</label>
									<div class="col-md-9">
										<div
											data-date-format="mm/dd/yyyy"
											class="input-group input-medium date date-picker">
											<input type="text" id="startDate" readonly="readonly"
												name="startDate"
												onchange="javascript:getStudentsByDate(this.value);"
												class="required form-control input-medium" />
											<span class="input-group-btn">
												<button type="button" class="btn default">
													<i class="fa fa-calendar"></i>
												</button> </span>
										</div>
										<span class="help-block">(MM/DD/YYYY)</span>
									</div>
								</div>
								<div class="spaceDiv"></div>
								<div class="spaceDiv"></div>
								<div class="spaceDiv"></div>
							</s:if>
						</div>
						<s:if test='%{plTitle == "DateWiseFeeCollection"}'>
						<s:if test='%{user.isSchoolStudent=="N" && user.isParent=="N"}'>
						<s:if test="%{(studyClassList != null && !studyClassList.isEmpty())}">
							<s:form action="ajaxDownloadCollectedFeeAmount" theme="simple"
								id="teachingAndNonTeaching" method="post"
								cssClass="form-horizontal"
								onsubmit="javascript:return collectedFeeType();"
								namespace="/reports">
								<s:hidden id="classNameIds" name="tempString" />
								<div class="form-body">
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-2">
													<span class="required">*</span> From Date :
												</label>
												<div class="col-md-5">
													<div 
														data-date-format="mm/dd/yyyy"
														class="input-group input-medium date date-picker">
														<input type="text" id="startDate" readonly="readonly"
															name="startDate" onchange="feeDatesValidation()"
															class="required form-control input-medium" />
														<span class="input-group-btn">
															<button type="button" class="btn default">
																<i class="fa fa-calendar"></i>
															</button> </span>
													</div>
													<span class="help-block">(MM/DD/YYYY)</span>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-2">
													<span class="required">*</span> To Date :
												</label>
												<div class="col-md-5">
													<div
														data-date-format="mm/dd/yyyy"
														class="input-group input-medium date date-picker">
														<input type="text" id="endDate" readonly="readonly"
															name="endDate" onchange="feeDatesValidation()"
															class="required form-control input-medium" />
														<span class="input-group-btn">
															<button type="button" class="btn default">
																<i class="fa fa-calendar"></i>
															</button> </span>
													</div>
													<span class="help-block">(MM/DD/YYYY)</span>
												</div>
											</div>
										</div>
									</div>
										<div class="form-body">
												<div class="form-group">
													<div class="col-md-12">
														<div class="checkbox-list">
															<label class="checkbox-inline">
																<input type="checkbox" name="" value=""
																	onClick="checkAllClasses()" class="checkbox allClasses">
																Available Class & Sections
															</label>
														</div>
													</div>
												</div>
											<div class="form-group">
												<label class="conLable col-md-3 control-label">
													<span class="required">*</span> Class With Students :
												</label>
												<div class="col-md-12">
													<div class="checkbox-list">
														<s:checkboxlist name="chkBoxSelectedIds"
															list="studyClassList" listKey="id"
															listValue="classAndSection" theme="ems" cssClass="small" />
													</div>
												</div>
											</div>
											<s:if test='%{tempList2.size >0}'>
												<div class="form-group">
													<label class="conLable control-label col-md-3">
														<span class="required">*</span> Class With Out Students :
													</label>
													<div class="col-md-12">
														<div class="checkbox-list">
															<s:checkboxlist name="chkBoxNotStudents" list="tempList2"
																listKey="id" listValue="classAndSection" theme="ems"
																cssClass="small" disabled="true" />
														</div>
													</div>
												</div>
											</s:if>
										</div>
									<div class="form-actions fluid">
										<div class="col-md-offset-3 col-md-9">
											<s:submit type="submit small" value="Generate Excel"
												onclick="reportType()" cssClass="submitBt btn blue long"
												title="generate report" />
										</div>
									</div>
								</div>
							</s:form>
							</s:if>
							<s:else>
								<div class="alert alert-info">
									Currently there is no class and Sections.
								</div>
							</s:else>
							</s:if>
						</s:if>
						<div id="createDailyAttendenceDiv">
							<jsp:include
								page="/WEB-INF/pages/admin/reports/ajaxGetTeachinAndNonTeachingRoles.jsp"></jsp:include>
						</div>
						<!-- below lines used in when click the Reports---Fee Collections in left nav  -->
						<div id="dynamicFeeCollections" style="display: none;">
							<s:iterator value="objectList">
								<li class="removeFeeDiv">
									<s:url id="StaffReligionsInPDF"
										action="ajaxFeeCollectionAndDues" includeParams="all"
										escapeAmp="false" namespace="/admin">
										<s:param name="title">
											<s:property value="settingName" />
										</s:param>
										<s:param name="tempId">
											<s:property value="id" />
										</s:param>
									</s:url>
									<sj:a href="%{StaffReligionsInPDF}" cssClass="ajaxify AFC"
										targets="mainContentDiv">
										<s:property value="settingName" />
									</sj:a>
								</li>
							</s:iterator>
						</div>
						<!--END -->
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript">

/* var startDate = $('span#startDateSpan').attr("class");
var endDate = $('span#endDateSpan').attr("class");
dateRestrictionWithinAcademicYear(startDate,endDate); */
FormComponents.init();

	$('#studRegister').hide();
	$(document).ready(function() {
		$('li#allFeeReportsDiv').addClass('active');
		$('.page-sidebar-menu li.active').addClass('open');
		var title = '';
		var str = $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim();
		if(isNonEmpty(str)){
			$('span.hidden-title').html($('.page-sidebar-menu li.active').find('li.active').children('a').children('span.title').text().trim() + "-->"
					+ $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim())
		}else{
			$('span.hidden-title').html($('.page-sidebar-menu li.active').find('li.active').children('a').children('span.title').text().trim())
		}
		/* $('span.hidden-title').html($('.page-sidebar-menu li.active').find('li.active').children('a').children('span.title').text().trim() + "-->"
							+ $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim()) */
		title = $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim();
		if(!isNonEmpty(title)){//this is used to parent and student logns
			 $('span.hidden-title').html($('.navbar-nav li.active').children('a').children('span.title').text().trim() + "-->"
						+ $('.navbar-nav li.active').find('li.active').find('ul.dropdown-menu').find('li.active').children('a').text().trim())
		  title = $('.navbar-nav li.active').find('li.active').find('ul.dropdown-menu').find('li.active').children('a').text().trim();
		}
	    changePageTitle(title);
	  $("input:checkbox, input:radio").uniform();
	  $("#createDailyAttendenceDiv").find('span.hidden-title').remove();
	  $("input[name=chkBoxSelectedIds]").click(function() {
		if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
		   $(".allClasses").parent('span').removeClass("checked");
			$(".allClasses").attr("checked", false);
		} else {
		    $(".allClasses").parent('span').addClass("checked");
			$(".allClasses").attr("checked", true);
		}
	});
	});
	 $('.js-activated').dropdownHover().dropdown();
	function feeDatesValidation(){
		var startDate = $('#startDate').val();
		var endDate = $('#endDate').val();
		if (isNonEmpty(startDate) || isNonEmpty(endDate)) {
			startDate = Date.parse(startDate);
			endDate = Date.parse(endDate);
			if (endDate < startDate) {
				alert("Your end date is more than your start date.");
				$('#endDate').val("");
			}
		}
	}
	
	function generateClassIds() {
		if ($("input[name=chkBoxSelectedIds]:checked").length > 0) {
			var classIds = $("input[name=chkBoxSelectedIds]:checked");
			var selectedClassIds = '';
			if (classIds.length > 0) {
				selectedClassIds = '(';
				for ( var i = 0; i < classIds.length; i++) {
					selectedClassIds += classIds[i].value + ', ';
				}
				selectedClassIds += '0)';
			}
			$("#classNameIds").val(selectedClassIds);
			return true;
		} else if ($("input[name=chkBoxSelectedIds]:checked").length == 0) {
			alert("Please select at least one Class");
			return false;
		} else {
			return false;
		}
	}
	
	function collectedFeeType() {
		var startDate = $('#startDate').val();
		var endDate = $('#endDate').val();
		if (startDate == '' && endDate == '') {
			alert("Please select From and To date.");
			return false;
		} else if (startDate == '') {
			alert("Please select From date.");
			return false;
		} else if (endDate == '') {
			alert("Please select To date.");
			return false;
		} else {
			if ($("input[name=chkBoxSelectedIds]:checked").length > 0) {
				generateClassIds();
				return true;
			} else {
				alert("Please select at least one Class");
				return false;
			}
		}
	}
	
	function getStudentsByDate(startDate) {
		var pars = "startDate=" + startDate
				+ "&plTitle= StaffDailyAttendance(Daily) &tempString=Staff";
		$("#createDailyAttendenceDiv")
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
			url : "../reports/ajaxTeachNonTeachStaffAttendDetails.do",
			cache : false,
			data : pars,
			success : function(response) {
				$('#createDailyAttendenceDiv').html(response);
				$("#createDailyAttendenceDiv").find('div.caption').remove();
				$("#createDailyAttendenceDiv").find('span.hidden-title').remove();
			}
		});
	}
	function checkAllClasses() {
		if ($(".allClasses").is(':checked')){
		    $("[name='chkBoxSelectedIds']").each(function(){
			   $(this).parent('span').addClass('checked');
			    $(this).attr("checked", "true");
			});
		}
		else{
		 $("[name='chkBoxSelectedIds']").each(function(){
			   $(this).parent('span').removeClass('checked');
			    $(this).removeAttr("checked");
			});
		}	
	}
	$('html, body').animate({ scrollTop: $(document).height() - $(window).height() }, 10, function() {
	    $(this).animate({ scrollTop: 0 }, 10);
	});
</script>
<style>
.dropdown{
	overflow: visible;
}
</style>