<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div class="block grid_12">
	<div class="block_head">
		<h2>
			Leave Details
		</h2>
		<div id="topMenu">
			<ul>
				<li>
					<s:url id="urlLeaveLink" action="ajaxDoGetleaveReportForStudent" namespace="/parent"/>
					<sj:a href="%{urlLeaveLink}" targets="applyLeave"
						indicator="indicator">Apply Leave</sj:a>
				</li>
			</ul>
		</div>
	</div>
	<div class="block_content" id="applyLeave">
		<jsp:include page="/WEB-INF/pages/parent/viewLeavesList.jsp" />

	</div>
</div>