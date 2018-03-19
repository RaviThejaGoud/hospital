<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div id="studentActMarksContent">
		<jsp:include page="/WEB-INF/pages/exam/admin/ajaxUploadActivitiesGradesSheet.jsp" />
</div>
<script type="text/javascript">
$(document).ready(function() {
changePageTitle('Download And Upload Student Activities Sheet');
});
</script>