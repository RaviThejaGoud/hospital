<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<s:if test="%{objectList != null && !objectList.isEmpty()}">
	<div class="spaceDiv"></div>
	<table class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
		<thead>
			<tr>
				<th>Sl.No</th>
				<th>Name</th>
				<th>Description</th>
				<th>Expiration Date</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
		</thead>
		<tbody>
			<%int i = 0; %>
			<s:iterator value="objectList">
				<tr>
					<td>
						<%
							i++;
						%><%=i%>
					</td>
					<td><s:property value="name" /></td>
					<td><s:property value="description" /></td>
					<td><s:property value="expirationDateStr" /></td>
					<td><s:url id="editReminders" action="ajaxEditReminders" includeParams="all" escapeAmp="false" namespace="/staff">
							<s:param name="reminderVO.id" value="%{id}" />
						</s:url> <sj:a href="%{editReminders}" targets="taskInfoContentDiv" cssClass="btn btn-xs purple" title="Edit">
							<i class="fa fa-edit"></i>Edit
							</sj:a></td>
					<td><s:url id="removeReminder"
							action="ajaxRemoveReminderDetails" includeParams="all" escapeAmp="false" namespace="/staff">
							<s:param name="reminderVO.id" value="%{id}" />
						</s:url> <s:div cssClass="btn btn-xs red" onclick="javascript:confirmDialogWithTarget(this,'taskInfoContentDiv');"
							id='%{removeReminder}' title="Delete this reminder">
							<i class="fa fa-times"></i>Delete
							</s:div></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">Currently there are no Reminders.</div>
</s:else>
<script type="text/javascript">
	TableAdvanced.init();
	UIExtendedModals.init();
	$(document).ready(function(){
		$("li#reminderActiveDiv").addClass("active");
		$("li#active").removeClass("active");
		$("li#taskandReminder").removeClass("active");
	});
</script>


