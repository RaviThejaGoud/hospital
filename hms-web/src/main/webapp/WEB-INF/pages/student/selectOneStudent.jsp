<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/group/paginator.js">
</script>
<jsp:include page="/common/messages.jsp"></jsp:include>

<div id="commonTabContent" class="grid_12">
	<div id="commonTabWrapper">
		<div id="commonStep">
			<fieldset>
				<s:if
					test="%{viewStudentPersonAccountDetailsList != null && !viewStudentPersonAccountDetailsList.isEmpty()}">
					<%
						int i = 0;
					 %>
					<div class="grid_11 th" id="results">
						<div class="grid_3">
							Student No
						</div>
						<div class="grid_3">
							Student Name
						</div>
						<div class="grid_3">
							Class - Section
						</div>
						<div class="grid_2">
							View
						</div>
						<div id="resultsPage">
							<s:iterator value="viewStudentPersonAccountDetailsList">
								<div class="grid_11 row1" id="results">
									<div class="grid_3">
										<s:property value="rollNumber" />
									</div>
									<div class="grid_3">
										<s:url id="doEditGroupType" action="ajaxGetSelectedStudent"
											includeParams="all" escapeAmp="false" namespace="/student">
											<s:param name="anyId" value="%{accountId}"/>
										</s:url>
										<sj:a href="%{doEditGroupType}" onBeforeTopics="cleanOpenDivs"
											onCompleteTopics="doInitRegisterStudentEvent"  button="false" buttonIcon="ui-icon-pencil"
											indicator="indicator" targets="editStaffEvent%{accountId}">
											<s:property value="personFullName" />
										</sj:a>
									</div>
									<div class="grid_3">
										<s:property value="classAndSection" />
									</div>
									<div class="grid_2">
										<img src='<c:url value="${adjustedAttachmentFilePath}"/>'
											alt='<s:property  value="viewStudentPersonAccountDetails.personFullName" />'
											align="left" height="50px" width="50px" border="0" />
									</div>
									<div id="editStaffEvent<s:property value='accountId'/>"
										style="display: none;" class="load">
									</div>
								</div>
							</s:iterator>
						</div>
					</div>
				</s:if>
				<s:else>
					<div class="grid_11">
						Currently there are no Students.
					</div>
				</s:else>
			</fieldset>
		</div>
	</div>
</div>

<script type="text/javascript">
changePageTitle('All Teacher Events List');
$.subscribe('doInitRegisterStudentEvent', function(event, data) {
	if ($('#' + data.id).is(":hidden")) {
		$('#' + data.id).show();
	} else {
		$('#' + data.id).hide();
	}
});

$.subscribe('doInitEditGroupType', function(event, data) {
	if ($('#' + data.id).is(":hidden")) {
		$('#' + data.id).show();
	} else {
		$('#' + data.id).hide();
	}
});

$(function() {
	$("#resultsPage").pagination();
});

$.subscribe('doEditStaffEvent', function(event, data) {
	if ($('#' + data.id).is(":hidden")) {
		$('#' + data.id).show();
	} else {
		$('#' + data.id).hide();
	}
});

function getStudent(username) {
	var pars = "username=" + username;
	$("#createAttendenceDiv")
			.html(
					'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	var url = jQuery.url.getChatURL("/student/ajaxGetSelectedStudent.do");
	$.ajax( {
		url : url,
		cache : false,
		data : pars,
		success : function(html) {
			$("#createAttendenceDiv").html(html);
		}
	});
}
</script>
