<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<div class="grid_14">
	<s:url id="searchStudentByNumber" action="ajaxViewStudentInAndOut"
		includeParams="all" escapeAmp="false" namespace="/hostel">
	</s:url>
	<sj:a href="%{searchStudentByNumber}" indicator="indicator"
		targets="stepHostelInOutRegister" button="false" cssClass="linkRight">Search Student</sj:a>
	<h1>
		Current Visitor Details
	</h1>
	<%@ include file="/common/messages.jsp"%>
	<s:if test="%{objectList!= null && !objectList.isEmpty()}">
		<div class="grid_14" align="right" data-target="studentContent">
			<jsp:include
				page="/WEB-INF/pages/common/ajaxViewPaginationOptions.jsp"></jsp:include>
		</div>
		<div class="grid_14 th">
			<div class="grid_12">
				<div class="grid_2">
					Visitor Name
				</div>
				<div class="grid_2">
					Relation
				</div>
				<div class="grid_2">
					In Date
				</div>
				<div class="grid_2">
					Out Date
				</div>
				<div class="grid_3">
					In Time
				</div>
				<div class="grid_2">
					Out Time
				</div>
			</div>
			<div class="grid_2">
				Reason
			</div>
		</div>
		<div id="studentContent">
			<s:iterator value="objectList">
				<div class="grid_14 row">
				<div class="grid_12">
					<div class="grid_2">
						<s:if test="%{visitorName != ''}">
							<s:property value="visitorName" />
						</s:if>
						<s:else>
							-
						</s:else>
					</div>
					<div class="grid_2">
					<s:if test="%{relation != ''}">
						<s:property value="relation" />
					</s:if>
					<s:else>
						-
					</s:else>
					</div>
					<div class="grid_2">
						<s:property value="inDateStr" />
					</div>
					<div class="grid_2">
						<s:property value="outDateStr" />
					</div>
					<div class="grid_3">
						<s:property value="inTime" />
					</div>
					<div class="grid_2">
						<s:property value="outTime" />
					</div>
					</div>
					<div class="grid_2">
					<s:if test="%{reason != ''}">
						<s:property value="reason" />
						</s:if>
					<s:else>
						-
					</s:else>
					</div>
				</div>
			</s:iterator>
		</div>
	</s:if>
	<s:else>
		<div class="grid_14 th thb">
			Currently there are no visitor details into hostel. 
		</div>
	</s:else>
</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/subscription/paginator_dev.js">
</script>
<script type="text/javascript">
$(document).ready(function() {
	$('#studentContent').pagination();
});	
</script>

<style type="text/css">
.grid_1, .grid_2, .grid_3, .grid_4, .grid_5, .grid_6, .grid_7, .grid_8, .grid_9, .grid_10, .grid_11, .grid_12, .grid_13, .grid_14, .grid_15, .grid_16, grid_17, grid_18 {
    margin-right: 6px;
}
</style>