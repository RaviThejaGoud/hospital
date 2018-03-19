<%@ include file="/common/taglibs.jsp"%>
<fieldset>
	<s:if test="%{studentsList != null && !studentsList.isEmpty()}">
		<div class="grid_14" align="right" data-target="resultsPage">
			<jsp:include
				page="/WEB-INF/pages/common/ajaxViewPaginationOptions.jsp"></jsp:include>
		</div>
		<div class="grid_14 th">
			<div class="grid_3">
				Roll Number
			</div>
			<div class="grid_3">
				Class
			</div>
			<div class="grid_5">
				Student Name
			</div>
			<div class="grid_3">
				Action
			</div>
		</div>
		<div id="resultsPage">
			<s:iterator value="studentsList">
				<div class="grid_14 row" id="results">
					<div class="grid_3">
						<s:property value="rollNumber" />
					</div>
					<div class="grid_3">
						<s:property value="className" />
						&nbsp;
						<s:property value="section" />
					</div>
					<div class="grid_5">
						<s:property value="firstName" />
						&nbsp;
						<s:property value="lastName" />
					</div>
					<div class="grid_3">
					<s:if test='%{tempString == "Visitors"}'>
						<s:url id="editVisitorDetails" action="ajaxDoAddVisitorDetails"
							includeParams="all" escapeAmp="false" namespace="/hostel">
							<s:param name="accountId" value='%{accountId}' />
							<s:param name="tempString"><s:property value="tempString"/></s:param>
						</s:url>
						<sj:a href="%{editVisitorDetails}"
							targets="editStudentDetails%{accountId}"
							onCompleteTopics="doInitEditStudentDetails"
							onBeforeTopics="cleanOpenDivs" indicator="indicator"
							button="false">Visitor In</sj:a>
					</s:if>
					<s:else>
						<s:url id="editStudentDetails" action="ajaxDoAddStudent"
							includeParams="all" escapeAmp="false" namespace="/hostel">
							<s:param name="accountId" value='%{accountId}' />
						</s:url>
						<sj:a href="%{editStudentDetails}"
							targets="editStudentDetails%{accountId}"
							onCompleteTopics="doInitEditStudentDetails"
							onBeforeTopics="cleanOpenDivs" indicator="indicator"
							button="false">Student Out Permission
					</sj:a>
					</s:else>
					</div>
					<div id="editStudentDetails<s:property value='accountId' />"
						style="display: none;" class='load'></div>
				</div>
			</s:iterator>
		</div>
	</s:if>
	<s:else>
		<div class="grid_14">
			Oops! system couldn't find any match with keyword (or) above keyword related students are already taken permission.
		</div>
	</s:else>
</fieldset>
<script type="text/javascript">
$.subscribe('doInitEditStudentDetails', function(event, data) {
	if ($('#' + data.id).is(":hidden")) {
		$('#' + data.id).show();
	} else {
		$('#' + data.id).hide();
	}
});
</script>
<script type="text/javascript">
$(document).ready(function() {
$('#stepVisitorInOut').hide();
	$('#searchStudentsList').show();
	$('#resultsPage').pagination();
});
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/subscription/paginator_dev.js">
</script>