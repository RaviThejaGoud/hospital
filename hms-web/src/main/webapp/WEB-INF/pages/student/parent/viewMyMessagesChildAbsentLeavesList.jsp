<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
	
</script>
<jsp:include page="/common/messages.jsp"></jsp:include>

<s:if
	test="%{viewStudentLeaveDetailsList != null && !viewStudentLeaveDetailsList.isEmpty()}">
	<div style="padding-top: 1px">
		<div id="resultsPage">
			<s:iterator value="viewStudentLeaveDetailsList">
				<table class="striped" width="100%" style="margin-bottom: 0;"
					cellpadding="1" cellspacing="1">
					<tr class='loaded'>

						<td width="22%" class="head">

							Your Children
							<s:property value="personFullName" />
							Studying in
							<s:property value="classAndSection" />
							was Absent to School on
							<br />
							<s:property value="startDateStr" />
							. Please
							<s:url id="editGroup"
								action="ajaxDoApplyChildAbsentLeaveDetailsForParent"
								includeParams="all" escapeAmp="false" namespace="/student">
								<s:param name="id" value="{id}" />
								<s:param name="accountId" value="{accountId}"></s:param>
							</s:url>
							<sj:a href="%{editGroup}" targets="myMessagesContent"
								indicator="indicator" button="false" buttonIcon="ui-icon-pencil">
									Click here
								</sj:a>
							To submit leave application

						</td>
					</tr>
					<tr id="addLeader<s:property value='id' />" style="display: none;"
						class='load'>
					</tr>
				</table>
			</s:iterator>
		</div>

	</div>
</s:if>
<s:else>
	<div style="padding: 10px">
		currently there are no child absent messages.
	</div>
</s:else>
<style type="text/css">
.active {
	color: #0033CC;
	text-decoration: none;
}

.inactive {
	font-weight: bold;
	text-decoration: underline;
	cursor: default;
}

.paginator {
	text-align: center;
	color: #FFF;
}
</style>
<script language="JavaScript" type="text/javascript">
	$(document).ready(function() {

		$(".pendingLeaves").click(function()

		{

			$(".pendingLeavesBody").slideToggle(600);

		});
		$(".pendingLeavesBody").hide();

	});

	$(document).ready(function() {

		$(".approvedLeaves").click(function()

		{

			$(".approvedLeavesBody").slideToggle(600);

		});
		$(".approvedLeavesBody").hide();

	});
	$(document).ready(function() {

		$(".rejectedLeaves").click(function()

		{

			$(".rejectedLeavesBody").slideToggle(600);

		});
		$(".rejectedLeavesBody").hide();

	});
</script>
