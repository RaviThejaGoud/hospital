<%@ include file="/common/taglibs.jsp"%>
<!--<div class="grid_14 omega" id="manageSkill">
	<div class="block_head">
		<h2>
			Manage Skill Type
		</h2>
		<ul>
			<li>
				<a href="${pageContext.request.contextPath}/admin/getAdminKBank.do"
					id="KBank">Back</a>
			</li>
		</ul>
	</div>
</div>
<div class="block_content" id="caseStudySkillView">
	<jsp:include page="/WEB-INF/pages/admin/kBank/ajaxViewSkillType.jsp"></jsp:include>
</div>
<script type="text/javascript">
changePageTitle("Add SkillType Name");
$(document).ready(function() {
	$.subscribe('addSkillValidation', function(event, data) {
		if ($('#addSkills').valid()) {
			return true;
		} else
			return false;
	});
});
</script>
-->
<jsp:include page="/WEB-INF/pages/admin/kBank/ajaxViewSkillType.jsp"></jsp:include>