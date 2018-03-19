<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/group/paginator10.js">
</script>
<jsp:include page="/common/messages.jsp"></jsp:include>

<% int i = 0; %>
<s:form id="promoteClassStudent" action="ajaxPromoteStudent" method="post" theme="css_xhtml">

<div id="resultsPage">
	<table class="striped" width="100%" style="margin-bottom: 0;"
		cellpadding="1" cellspacing="1">
		<thead>
			<tr style="text-align: left;">
				<th style="width: 140px;">
					Student No
				</th>
				<th style="width: 170px;">
					Student Name
				</th>
				<th width="16%">
						Academic
				</th>
				<th style="width: 105px;">
					Class
				</th>
				<th style="width: 80px; text-align: left;">
					&nbsp;
				</th>
			</tr>
		</thead>
		<s:if
			test="%{studentsList != null && !studentsList.isEmpty()}">
			<s:iterator value="studentsList">
				<tr class='loaded'>
					<td width="73px">
						<s:property value="rollNumber" />
					</td>
					<td width="90px">
						<a
							href="<c:url value='/staff/ajaxDoViewStudentDetails.do' />?id=<s:property value="studentId"/>&classAndSection=<s:property value="classAndSection"/>"
							class="student" title="Student Views"><s:property
								value="firstName" />&nbsp;<s:property value="lastName" />
						</a>
					</td>
					<td width="16%">
						<s:url id="doViewPromoteStudent" action="ajaxViewStudentAcademic" includeParams="all"
							escapeAmp="false">
							 <s:param name="accountId" value="%{id}" />
						</s:url>
						<sj:a href="%{doViewPromoteStudent}" targets="editPromoteStudentForm%{id}" onCompleteTopics="doInitEditStudent"
							indicator="indicator" onBeforeTopics="cleanOpenRows">
							<b>View </b>
						</sj:a>
					</td>
					<td style="text-align: left;" width="50px">
						<s:property value="classAndSection" />
					</td>
					<td style="text-align: left;" width="50px">
						<input type="checkbox" value="<s:property value="studentId" />" name="chkBoxSelectedIds">
					</td>
				</tr>
				<tr id="editStaffEvent<s:property value='id' />" style="display: none;" class="load"></tr>
				<tr id="editPromoteStudentForm<s:property value='id' />" style="display: none;" class='load'></tr>

			</s:iterator>
		</s:if>
		<s:else>
			  	Currently there are no Students
		   </s:else>

	</table>
</div>
<div class="grid_11" style="float: right;">
				<div class="grid_6">
					&nbsp;
				</div>
	<sj:submit   targets="marksTemplate" value="Submit"
		cssClass="submit small" formIds="promoteClassStudent"
		indicator="indicator" onClickTopics="promoteClassStudentFormValidation" />
	<s:url id="cancelPromoteClassUrl" action="ajaxClancelClassPromote"
		includeParams="all" escapeAmp="false">
	</s:url>
	<sj:a href="%{cancelPromoteClassUrl}" cssClass="cancelButton"
		indicator="indicator" targets="marksTemplate" button="false"
		buttonIcon="ui-icon-plus">Cancel</sj:a>
</div>
</s:form>
<script type="text/javascript">
changePageTitle('Fail Students List');
$(function() {
	$("#resultsPage").pagination();
});

$.subscribe('promoteClassStudentFormValidation', function(event, data) {
	if ($('#promoteClassStudent').valid())
		return true;
	else
		return false;
});
$(document).ready(function() {
	$("a.student").fancybox( {
		'width' : '60%',
		'height' : '60%',
		'autoScale' : false,
		'transitionIn' : 'none',
		'transitionOut' : 'none',
		'autoDimensions' : true,
		'showCloseButton' : true

	});
});

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

changePageTitle('Edit Events');
$.subscribe('doEditStaffEvent', function(event, data) {
	if ($('#' + data.id).is(":hidden")) {
		$('#' + data.id).show();
	} else {
		$('#' + data.id).hide();
	}
});
$(document).ready(function() {
	$.subscribe('doInitEditStudent', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});
});
</script>
