<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div class="block grid_9 omega">
	<div class="block_head">
		<h2>
			Leave Details
		</h2>
		<div id="topMenu">
			<ul>
				<li>
					<s:url id="urlLeaveLink" action="ajaxDoGetleaveReport" />
					<sj:a href="%{urlLeaveLink}" targets="applyLeave"
						indicator="indicator">Apply Leave</sj:a>
				</li>
				<li>
					<s:url id="urlApprovalLeaveLink" action="ajaxDoStaffLeaveApprovalsAsApprover" />
					<sj:a href="%{urlApprovalLeaveLink}" targets="applyLeave"
						indicator="indicator">Leave Approvals</sj:a>
				</li>
				<li>
					<s:url id="urlMyLeaveLink" action="ajaxDoGetLeaveDetails" />
					<sj:a href="%{urlMyLeaveLink}" targets="applyLeave"
						indicator="indicator">My Leaves</sj:a>
				</li>
				
			</ul>
		</div>
	</div>
	<div class="block_content" id="applyLeave">
		<jsp:include page="/WEB-INF/pages/staff/leaves/staffLeavesList.jsp" />

	</div>
</div>