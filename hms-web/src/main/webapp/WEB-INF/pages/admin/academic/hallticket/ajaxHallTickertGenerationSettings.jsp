<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Hall Ticket Generation
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<s:if test='%{user.isSchoolClerk != "Y"}'>
							<li>
								<s:url id="urlDoHallTicketSettings"
									action="ajaxHallTicketSettings" escapeAmp="false"
									includeParams="all"  namespace="/exam">
									<s:param name="tempString">templateSettings</s:param>
								</s:url>
								<sj:a id="doHallTicketSettings" href="%{urlDoHallTicketSettings}"
									targets="viewHTContent" data-toggle="tab">Hall Ticket Settings</sj:a>
							</li>
						</s:if>
						<li class="active">
							<s:url id="urlHallTicket" action="ajaxDoGetHallTicketGeneration"
								namespace="/exam">
							</s:url>
							<sj:a id="urlHallTicket" href="%{urlHallTicket}"
								targets="mainContentDiv" data-toggle="tab">View Hall Tickets</sj:a>
						</li>
					</ul>
					<div id="viewHTContent" class="tab-content">
						<jsp:include
							page="/WEB-INF/pages/admin/academic/hallticket/ajaxDoHallTicketGeneration.jsp" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle('Manage HallTicket Settings');
});
</script>