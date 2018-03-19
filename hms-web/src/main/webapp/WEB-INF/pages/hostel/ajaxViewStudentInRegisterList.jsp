<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<div class="grid_14">
	<s:url id="searchStudentByNumber" action="ajaxViewStudentIn"
		includeParams="all" escapeAmp="false" namespace="/hostel">
	</s:url>
	<sj:a href="%{searchStudentByNumber}" indicator="indicator"
		targets="stepHostelInOutRegister" button="false" cssClass="linkRight">Search Student</sj:a>
	<h1>
		Current Student-In Details
	</h1>
	<%@ include file="/common/messages.jsp"%>
	<s:if test="%{studentOutList!= null && !studentOutList.isEmpty()}">
		<div class="grid_14" align="right" data-target="studentContent">
			<jsp:include
				page="/WEB-INF/pages/common/ajaxViewPaginationOptions.jsp"></jsp:include>
		</div>
		<div class="grid_14 th">
			<div class="grid_12">
				<div class="grid_2">
					Student Name
				</div>
				<div class="grid_2">
					Class Name
				</div>
				<div class="grid_2">
					Out Date
				</div>
				<div class="grid_2">
					In Date
				</div>
				<div class="grid_3">
					Out Time/In Time
				</div>
				<div class="grid_2">
					Visitor Name
				</div>
			</div>
			<div class="grid_2">
				Action
			</div>
		</div>
		<div id="studentContent">
			<s:iterator value="studentOutList">
				<div class="grid_14 row">
					<div class="grid_12">
						<div class="grid_2">
							<s:property value="firstName" />
							&nbsp;
							<s:property value="lastName" />
						</div>
						<div class="grid_2">
							<s:property value="className" />
							&nbsp;
							<s:property value="section" />
						</div>
						<div class="grid_2">
							<s:property value="outDateStr" />
						</div>
						<div class="grid_2">
							<s:property value="expectedInDateStr" />
						</div>
						<div class="grid_3">
							<s:property value="outTime" />
							/
							<s:property value="exceptedInTime" />
						</div>
						<div class="grid_2">
							<s:property value="visitorName" />
						</div>
					</div>
					<div class="grid_2">
						<s:url id="chgStudOutDetails" action="ajaxUpdateInStudent"
							includeParams="all" escapeAmp="false" namespace="/hostel">
							<s:param name="studentOut.id" value='%{studentOutId}' />
							<s:param name="anyTitle" value='%{anyTitle}' />
						</s:url>
						<sj:a href="%{chgStudOutDetails}"
							targets="stepHostelInOutRegister" onBeforeTopics="cleanOpenDivs"  onClickTopics="getStudentInDetails"
							indicator="indicator">Student In
									</sj:a>
					</div>
					<%--
					<div id="editStudentInDetails<s:property value='accountId' />"
						style="display: none;" class='load'></div>
				--%>
				</div>
			</s:iterator>
		</div>
	</s:if>
	<s:else>
		<div class="grid_14 th thb">
			Currently there are no outed students for confirmation of into the hostel.
		</div>
	</s:else>
</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/subscription/paginator_dev.js">
</script>
<script type="text/javascript">

$(document).ready(function() {
	$('#studentContent').pagination();
	//$('#searchStudentsList').show();
	});

$.subscribe('getStudentInDetails', function(event, data) {
	input_box = confirm("If you click OK student automatically into the hostel.");
	if (input_box == true)
	{
		return true;
	}
	else {
		return false;
	}
});
</script>
<style type="text/css">
.grid_1, .grid_2, .grid_3, .grid_4, .grid_5, .grid_6, .grid_7, .grid_8, .grid_9, .grid_10, .grid_11, .grid_12, .grid_13, .grid_14, .grid_15, .grid_16, grid_17, grid_18 {
    margin-right: 6px;
}
</style>