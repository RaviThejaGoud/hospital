<%@ include file="/common/taglibs.jsp"%>
<!--<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/validation/jquery.validate.js">
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
-->
<head>
	<title>SMS | School Library Details</title>
</head>
<body />
	<div class="wrapper container_18">
		<!-- wrapper begins -->
		<div class="wrapper">
			<div class="grid_18 block grid_18MarginLeft">
				<div class="grid_4 alpha">
					<div class="grid_4 alpha">
						<div class="block_head">
							<h2>
								Reports
							</h2>
						</div>
						<div class="block_content" id="sideMenu" >
							<ul>
							 	<s:if
									test='%{(user.isSchoolFinance=="N") && (user.isSchoolFinance=="N")}'>
									<li>
										<a href="#" id="Admin">Admin</a>
									</li>
									<li>
										<a href="#" id="Student">Student</a>
									</li>
									<li>
										<a href="#" id="Staff">Staff</a>
									</li>
								</s:if>
								<li>
									<a href="#" id="Finance">Finance </a>
								</li> 
							</ul>
						</div>
					</div>
					<div class="grid_4 alpha">
						&nbsp;
					</div>
					<div class="grid_4 alpha" id="AdminView">
						<div class="block_head">
							<h2>
								Admin
							</h2>
						</div>
						<div class="block_content" id="sideMenu">
							<ul>
							 	<li>
									<s:url id="importCommunityExcelSheet"
										action="ajaxDoViewDownloadSheets" escapeAmp="false"
										includeParams="all" namespace="/admin">
										<s:param name="tempString">Student</s:param>
										<s:param name="plTitle">Community Details</s:param>
									</s:url>
									<sj:a href="%{importCommunityExcelSheet}" targets="reportsList">Community Details</sj:a>
								</li>
								<li>
									<s:url id="importStudentReligion"
										action="ajaxDoReligionWiseDetails" namespace="/reports"
										includeParams="all" escapeAmp="false">
										<s:param name="tempString">Student</s:param>
										<s:param name="plTitle">Religion Details</s:param>
									</s:url>
									<sj:a href="%{importStudentReligion}" targets="reportsList">Religion Details</sj:a>
								</li>
								<li>
									<a 
										href="<c:url value='/reports/ajaxMediumWiseStudentsDetails.do'/>?tempString=Student&plTitle=Medium Wise Details"
										target="_new" title=" download Medium Wise Details">Medium Wise Details</a>
								</li>
							</ul>
						</div>
					</div>
					<div class="grid_4 alpha" id="StudentsView">
						<div class="block_head">
							<h2>
								Student Reports
							</h2>
						</div>
						<div class="block_content" id="sideMenu" >
							<ul>
						 		<li>

									Medium Wise Details
								</li>
								<li>
									<s:url id="StaffReligionsInPDF"
										action="ajaxDoReligionWiseDetails" namespace="/reports"
										includeParams="all" escapeAmp="false">
										<s:param name="anyId">Excel</s:param>
										<s:param name="tempString">Staff</s:param>
									</s:url>
									<sj:a href="%{StaffReligionsInPDF}"	targets="religionStudentsList">Religion Details</sj:a>
								</li>
							</ul>
						</div>
					</div>
					<div class="grid_4 alpha" id="StaffsView">
						<div class="block_head">
							<h2>
								Staff Reports
							</h2>
						</div>
						<div class="block_content" id="sideMenu" >
							<ul>
								<li>

									Medium Wise Details
								</li>
								<li>
									<s:url id="StaffReligionsInPDF"
										action="ajaxDoReligionWiseDetails" namespace="/reports"
										includeParams="all" escapeAmp="false">
										<s:param name="anyId">Excel</s:param>
										<s:param name="tempString">Staff</s:param>
									</s:url>
									<sj:a href="%{StaffReligionsInPDF}"	targets="religionStudentsList">Religion Details</sj:a>
								</li>
							</ul>
						</div>
					</div>
					<div class="grid_4 alpha" id="FinanceView">
						<div class="block_head">
							<h2>
								Finance Reports
							</h2>
						</div>
						<div class="block_content" id="sideMenu">
							<ul>
								<li>
									Medium Wise Details
								</li>
								<li>
									Religion Details
								</li>
							</ul>
						</div>
					</div>
				</div>
				<s:if test='%{(user.isSchoolFinance=="Y")}'>
					<jsp:include
						page="/WEB-INF/pages/admin/reports/viewAllFinanceReports.jsp"></jsp:include>
				</s:if>
				<s:else>
					<div id="reportsList" class="block grid_14 alpha">
						<jsp:include
							page="/WEB-INF/pages/admin/reports/ajaxDoViewDownloadSheets.jsp"></jsp:include>
					</div>
				</s:else>
			</div>
		</div>
	</div>
	<script Language="Javascript1.2" type="text/javascript">
$(document).ready(function() {
	$('#AdminView').hide();
	$('#StudentsView').hide();
	$('#StaffsView').hide();
	$('#FinanceView').hide();
	
	changePageTitle("Manage Student");
	$('#reportsContent').addClass('current');
});
</script>
	<script type="text/javascript">
jQuery(function($) {
	$('#Admin').click(function() {
		$('#AdminView').show();
		$('#StudentsView').hide();
		$('#StaffsView').hide();
	  $('#FinanceView').hide();
	});
	$('#Student').click(function() {
		$('#StudentsView').show();
		$('#AdminView').hide();
		$('#StaffsView').hide();
	  $('#FinanceView').hide();
	});
	$('#Staff').click(function() {
		$('#StaffsView').show();
		$('#AdminView').hide();
		$('#StudentsView').hide();
		$('#FinanceView').hide();
	});
	$('#Finance').click(function() {
		$('#FinanceView').show();
		$('#AdminView').hide();
		$('#StudentsView').hide();
	    $('#StaffsView').hide();   
	});
});
</script>






	<!--<div class="block_head" id="topMenu">
	<h2>
		Reports
	</h2>
	<s:if
		test='%{(#session.academicYear == null || #session.academicYear.isEmpty()) || (#session.previousYear=="N")}'>
		<s:if test='%{user.isOnlySchoolAdmin == "Y"}'>
			<ul>
				<li>							
				<s:url id="downloadMarksSheet" action="ajaxDoViewDownloadMarksSheets"/>
				<sj:a href="%{downloadMarksSheet}" targets="promoteStudentsCont">Download Student Promotion List Reports</sj:a>
			  </li>
				<li>
					<s:url id="importStaffCommunity"
						action="ajaxDoViewDownloadSheets" escapeAmp="false" includeParams="all">
						<s:param name="tempString" >Staff</s:param>
					</s:url>
					<sj:a href="%{importStaffCommunity}" targets="reportsList">Staff Reports</sj:a>
				</li>
				<li>
					<s:url id="importCommunityExcelSheet"
						action="ajaxDoViewDownloadSheets" escapeAmp="false" includeParams="all">
						<s:param name="tempString" >Student</s:param>
					</s:url>
					<sj:a href="%{importCommunityExcelSheet}" targets="reportsList">Students Reports</sj:a>
				</li>
			</ul>
		</s:if>
	</s:if>
</div>
<div class="block_content" >
<div class="grid_14 commonFormTabs" id="promoteStudentsCont">
	<jsp:include page="/common/messages.jsp" />
		<fieldset>
			<div class="grid_13">
					<div class="grid_12">
						<div class="grid_4">
							<label class="right">
								<span class="required">*</span>Select Report :
							</label>
						</div>
						<div class="grid_4">
							<s:select cssClass="required" required="true" headerKey="S"
								headerValue="-Select Option-" 
								onchange="getAjaxClassWiseGenderCount(this.value);"
								list="#{'STF':'Staff Reports','STD':'Student Reports','FR':'Finance Reports'}"
								id="yourOptionValue" theme="css_xhtml" value="2" />
	
						</div>
					</div>
			</div>
		</fieldset>
		<br/>
		<div class="grid_13" id="schoolComunityReports"></div>
</div>
</div>
-->
	<!--<script Language="Javascript1.2" type="text/javascript">
function getAjaxClassWiseGenderCount(yourOptionValue) {
	var pars = "yourOptionValue=" + yourOptionValue;
	//alert(pars);
	var url='';
	if (yourOptionValue == "STF") {
		$("#schoolComunityReports").hide();
		url = jQuery.url.getChatURL("/admin/ajaxDoViewDownloadSheets.do?tempString=Staff");
		  	$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
					$("#schoolComunityReports").html(html);
					$("#schoolComunityReports").show();
			}
		});
		document.myform1.submit();
		//$("#schoolTermlist").html("");
		}
	else if(yourOptionValue == 'STD'){
	     url = jQuery.url.getChatURL("/admin/ajaxDoViewDownloadSheets.do?tempString=Student");
		  	$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
					$("#schoolComunityReports").html(html);
					$("#schoolComunityReports").show();
			}
		});
	
	}else if(yourOptionValue == 'FR'){
	     url = jQuery.url.getChatURL("/admin/ajaxAllAdminSchoolFeeReports.do");
		  	$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
					$("#schoolComunityReports").html(html);
					$("#schoolComunityReports").show();
			}
		});
	}
	else if (yourOptionValue == 'S') {
	   $('div#steps13').hide();
	   $('div#schoolComunityReports').hide();

	   
		alert("Please select at least one value.");
		return false;
	}
}
$(document).ready(function() {
	changePageTitle("Manage All Reports");
	$('.blockHeader h2').html('Manage Reports');
});
</script>-->