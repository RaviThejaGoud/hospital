<%@ include file="/common/taglibs.jsp"%>
<s:form action="#" cssClass="form-horizontal" id="submit_form">
	<div class="form-wizard" id="admissionSettingDiv">
		<div class="form-body">
			<ul class="nav nav-pills nav-justified steps">
				<li id="admissionDiv">
					<s:url id="admissionSettings1" action="ajaxDoAddAdmissionSettings"
						namespace="/admin" includeParams="all" escapeAmp="false">
						<s:param name="admissionSettings.id"><s:property value="anyId"/></s:param>
					</s:url>
					<sj:a href="%{admissionSettings1}" targets="admissionSettingsDiv" cssClass="step">
						<span class="number">1</span>
						<span class="desc"><i class="fa fa-check"></i> Admission
							Settings</span>
					</sj:a>
				</li>
				<li id="AdmissionSettings2">
					<a href="#" class="step"> <span
						class="number">2</span> <span class="desc"><i
							class="fa fa-check"></i> Class Settings</span> </a>
				</li>
				<li id="AdmissionSettings3">
					<a  href="#" class="step"> <span
						class="number">3</span> <span class="desc"><i
							class="fa fa-check"></i> Fee Categories</span> </a>
				</li>
				<li id="AdmissionSettings4">
					<a href="#" class="step"> <span
						class="number">4</span> <span class="desc"><i
							class="fa fa-check"></i> Fee Particulars</span> </a>
				</li>
				<li id="AdmissionSettings5">
					<a href="#"  class="step"> <span
						class="number">5</span> <span class="desc"><i
							class="fa fa-check"></i>Fee Terms</span> </a>
				</li>
				<li id="AdmissionSettings6">
					<a href="#" class="step"> <span
						class="number">6</span> <span class="desc"><i
							class="fa fa-check"></i> Class Fee</span> </a>
				</li>
			</ul>
			<div id="admissionSettingsDiv">
			</div>
		</div>
	</div>
</s:form>
<style type="text/css">
.nav-justified>li {
	width : 0%;
}
.nav > li > a{
padding :5px 12px;
}
</style>
<span id="admissionId" style="display: none;"><s:property
		value="anyId" /> </span>
<script Language="Javascript1.2" type="text/javascript">
jQuery(document).ready(function() {
	FormWizard.init();
});
$(document).ready(function() {
$('li#admissionDiv a').click();
$("li#admissionDiv").addClass('active');
});
$('li#settingId').addClass('active');
$('li#closeId').removeClass('active');
</script>
