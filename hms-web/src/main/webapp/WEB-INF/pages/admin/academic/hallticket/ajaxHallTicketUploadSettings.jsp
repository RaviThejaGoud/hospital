<%@ include file="/common/taglibs.jsp"%>
<div id="htSettingsCont">
	<jsp:include
		page="/WEB-INF/pages/admin/academic/hallticket/ajaxAddHallTicketSettings.jsp" />
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle('Manage HallTicket Settings');
});
function getHTGenerate() {
	$('a#doHallTicketGeneration').click();
}
</script>