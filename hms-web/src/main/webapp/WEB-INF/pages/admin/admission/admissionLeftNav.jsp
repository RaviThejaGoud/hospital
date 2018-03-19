<%@ include file="/common/taglibs.jsp"%>
<div class="grid_4 alpha">
<span class='navSelectionSpan' id='<s:property value="description"/>'></span>
	<div class="block_head">
		<h2>
			Manage Admissions
		</h2>
	</div>
	<div class="block_content" id="sideMenu"
		style="padding: 0px 0px 10px 0px;">
		<ul>
			<li id="applicationsNav">
				<s:url id="urlGetAdmissions" action="ajaxGetOnlineAdmissions" namespace="/admin"/>
				<sj:a id="admissionDetails" href="%{urlGetAdmissions}"
					targets="admissionContentDetails" indicator="indicator">Application Details</sj:a>
			</li>
			<li id="approvedStudsNav">
				<s:url id="urlPendingApplications"
					action="ajaxApprovedApplicationsHome" namespace="/admin"/>
				<sj:a id="urlPendingApplications" href="%{urlPendingApplications}"
					targets="admissionContentDetails" indicator="indicator">Short-Listed Applications</sj:a>
			</li>
			<li>
				<s:url id="urlRejectedApplications"
					action="ajaxRejectedApplications" namespace="/admin"/>
				<sj:a id="urlRejectedApplications" href="%{urlRejectedApplications}"
					targets="admissionContentDetails" indicator="indicator">Rejected Applications</sj:a>
			</li>
			<li>
				<s:url id="urlGetAdmissionSettings"
					action="ajaxAdmissionSettingsHome" namespace="/admin"/>
				<sj:a id="classDetails" href="%{urlGetAdmissionSettings}"
					targets="admissionContentDetails" indicator="indicator">Admission Settings</sj:a>
			</li>
			<li id="admittedStudsNav">
				<s:url id="urlGetAdmittedStudents"
					action="ajaxViewAdmittedStudents" namespace="/admin"/>
				<sj:a id="urlGetAdmittedStudents" href="%{urlGetAdmittedStudents}"
					targets="admissionContentDetails" indicator="indicator">Admitted Students</sj:a>
			</li>
			<li>
				<s:url id="urlGetCastSettings"
					action="ajaxCastSettingsHome" namespace="/admin"/>
				<sj:a id="castDetails" href="%{urlGetCastSettings}"
					targets="admissionContentDetails" indicator="indicator">Community &amp; Caste Settings</sj:a>
			</li>
			<li>
				<s:url id="urlGetEditAdmissions" action="ajaxGetEditOnlineAdmissions" namespace="/admin"/>
				<sj:a id="editAdmissionDetails" href="%{urlGetEditAdmissions}"
					targets="admissionContentDetails" indicator="indicator">Update Applications</sj:a>
			</li>
		</ul>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	var content = $('span.navSelectionSpan').attr('id');
	$('ul.nav-tabs li').removeClass('active');
	if(isNonEmpty(content) && "viewAdmittedStudents" == content){
		$('li#admittedStudsNav').addClass('active');
	}else if(isNonEmpty(content) && "admissionPaymentCalcel" == content){
		$('li#applicationsNav').addClass('active');
	}
	else{
		$('li#admittedStudsNav').addClass('active');
	}
$('#createApplicationForm').hide();
changePageTitle('Application Details');
});
</script>
