<%@ include file="/common/taglibs.jsp"%>
<s:if
	test="%{viewStaffPersonAccountDetailsList != null && !viewStaffPersonAccountDetailsList.isEmpty()}">
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th>
					Employee ID
				</th>
				<th>
					Employee Name
				</th>
				<th>
					View
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="viewStaffPersonAccountDetailsList">
				<tr>
					<td>
						<s:property value="username" />
					</td>
					<td>
						<!--<s:url id="doEditGroupType" action="ajaxSelectOneStaff"
							includeParams="all" escapeAmp="false">
							<s:param name="username" value="{username}" />
							<s:param name="eventBelongs" value="{eventBelongs}" />
						</s:url>
						<sj:a href="%{doEditGroupType}" onBeforeTopics="cleanOpenRows"
							onCompleteTopics="doInitRegisterStudentEvent"
							indicator="indicator" targets="editStaffEvent%{id}">
							<s:property value="personFullName" />
						</sj:a>
					-->
					<a data-toggle="modal"  href="#responsive"  class="btn btn-xs purple"
							onclick="javascript:PopupEmployeNameDetials(<s:property value="%{username}" />,<s:property value="eventBelongs"/>);">
						</a>
					
					</td>
					<td>
						<img src='<c:url value="${adjustedAttachmentFilePath}"/>'
							alt='<s:property  value="viewStaffPersonAccountDetails.personFullName" />'
							align="left" height="50px" width="50px" border="0" />
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no Staff
	</div>
</s:else>
<div id="responsive"></div>
<script type="text/javascript">
changePageTitle('All Teacher Events List');
TableAdvanced.init();
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
function PopupEmployeNameDetials(username,eventBelongs) {
var pars = "username=" + username + "&eventBelongs=" + eventBelongs;
		var url = jQuery.url.getChatURL("/staff/ajaxSelectOneStaff.do");
		$('#responsive').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
				url : url,
				cache : false,
				data : pars,
				success : function(html) {
					$("#responsive").html(html);
				}
			});
		}
</script>
