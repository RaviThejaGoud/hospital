<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<div class="grid_16">
	<div class="block_head">
		<h2>
			Academic Information : <s:property value="academicYear.startDateStr" /> &nbsp;- <s:property value="academicYear.endDateStr" />
		</h2>
		<s:if test='%{(#session.academicYear == null || #session.academicYear.isEmpty()) || (#session.previousYear=="N")}'>
		<ul>
			<li>
				<s:url id="urlCreateSchoolSettingsAcademic"
					action="ajaxCreateSchoolSettings" />
				<sj:a id="doCreateSchoolSettingsAcademic"
					href="%{urlCreateSchoolSettingsAcademic}"
					targets="academicsContent" indicator="indicator">Create Settings</sj:a>
			</li>
		</ul>
		</s:if>
	</div>
	<div class="block_content" id="schoolSettingsView">
		<div>
			<jsp:include
				page="/WEB-INF/pages/admin/academic/ajaxCreateSchoolSettings.jsp"></jsp:include>
		</div>
	</div>
</div>
<script type="text/javascript">
changePageTitle('Manage School');
$(document).ready(function() {
	$.subscribe('schoolSettingsValidation', function(event, data) {
		if ($('#addSchoolSettings').valid())
			return true;
		else
			return false;
	});

});
</script>