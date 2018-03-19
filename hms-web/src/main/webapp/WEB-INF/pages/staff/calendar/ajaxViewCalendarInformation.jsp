<%@ include file="/common/taglibs.jsp"%>

<s:if test="%{anyTitle == null  ||  anyTitle == ''}">
	<div class="panel-body col-md-12">
		<div class="col-md-1">
			<span class="label label-danger"> NOTE : </span>
		</div>
		<div class="col-md-10">
			<ul>
				<li>You can see all Task Details in this page.</li>
				<li>You can see the completed Task Details at <s:url
						id="doViewCompleteTasks" action="ajaxTaskInformationHome"
						includeParams="all" escapeAmp="false" namespace="/staff">
						<s:param name="anyTitle">CT</s:param>
					</s:url> <sj:a href="%{doViewCompleteTasks}" indicator="indicator"
						targets="mainContentDiv">
						Completed Task Details
					</sj:a>
				</li>
			</ul>
		</div>
	</div>
	<div>&nbsp;</div>
</s:if>
<s:else>
	<div class="panel-body col-md-12">
		<div class="col-md-1">
			<span class="label label-danger"> NOTE : </span>
		</div>
		<div class="col-md-10">
			<ul>
				<li>You can see all Task Details in this page.</li>
				<li>You can see the View Task Details at <s:url
						id="doViewCompleteTasks" action="ajaxTaskInformationHome"
						includeParams="all" escapeAmp="false" namespace="/staff">
						<s:param name="anyTitle" value=""></s:param>
					</s:url> <sj:a href="%{doViewCompleteTasks}" indicator="indicator"
						targets="mainContentDiv">
						View Task Details
					</sj:a>
				</li>
			</ul>
		</div>
	</div>
	<div>&nbsp;</div>
</s:else>
<s:if test="%{objectList != null && !objectList.isEmpty()}">
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th>Sl.No</th>
				<th>Task Name</th>
				<th>Description</th>
				<th>Assign to</th>
				<th>Completion Date</th>
				<th>Status</th>
				<s:if test="%{anyTitle == null  ||  anyTitle == ''}">
					<th>Action</th>
				</s:if>
				<th>Comments</th>
				<s:if test="%{anyTitle == null  ||  anyTitle == ''}">
					<s:if
						test='%{user.isOnlySchoolAdmin == "Y" || user.roleName=="ROLE_VICEPRINCIPAL" || user.isSchoolPrincipal=="Y" || tempBoolean || user.isOnlySchoolHod=="Y" || user.isSchoolDirector == "Y"}'>
						<th>Edit</th>
						<th>Delete</th>
					</s:if>
				</s:if>
			</tr>
		</thead>
		<tbody>
			<%
				int i = 0;
			%>
			<s:iterator value="objectList">
				<tr>
					<td>
						<%
							i++;
						%><%=i%>
					</td>
					<td><s:property value="taskName" /></td>
					<td align="center"><a data-toggle="modal"
						href="#showTaskDescriptionDiv"
						onclick="javascript:showTaskDescription(<s:property value="taskDetailsId" />);">
							Description </a></td>
					<td><s:property value="staffName" /></td>
					<td><s:property value="taskDateStr" /></td>
					<td><s:property value="statusDesc" /></td>
					<s:if test="%{anyTitle == null  ||  anyTitle == ''}">
						<s:if test="%{accountId == taskCreator || accountId == user.id}">
							<td align="center"><a data-toggle="modal"
								href="#showTaskDetailsDiv"
								onclick="javascript:showTaskInfromationDetails(<s:property value="taskDetailsId" />);">
									Action </a></td>
						</s:if>
						<s:else>
							<td></td>
						</s:else>
					</s:if>
					<td align="center"><a data-toggle="modal"
						href="#showTaskCommentsDiv"
						onclick="javascript:showTaskComments(<s:property value="taskDetailsId" />);">
							Comments </a></td>
					<s:if test="%{anyTitle == null  ||  anyTitle == ''}">
						<s:if
							test='%{user.isOnlySchoolAdmin == "Y" || user.roleName=="ROLE_VICEPRINCIPAL" || user.isSchoolPrincipal=="Y" || tempBoolean || user.isOnlySchoolHod=="Y" || user.isSchoolDirector == "Y"}'>
							<s:if test="%{taskCreator == user.id}">
								<td><s:url id="editTaskInfo"
										action="ajaxEditTaskInformation" includeParams="all"
										escapeAmp="false" namespace="/staff">
										<s:param name="taskDetailsVO.id" value="%{taskDetailsId}" />
									</s:url> <sj:a href="%{editTaskInfo}" targets="taskInfoContentDiv"
										cssClass="btn btn-xs purple" title="Edit">
										<i class="fa fa-edit"></i>Edit
								</sj:a></td>
								<td><s:url id="removeTaskDetails"
										action="ajaxRemoveTaskDetails" includeParams="all"
										escapeAmp="false" namespace="/staff">
										<s:param name="taskDetailsVO.id" value="%{taskDetailsId}" />
									</s:url> <s:div cssClass="btn btn-xs red"
										onclick="javascript:confirmDialogWithTarget(this,'mainContentDiv');"
										id='%{removeTaskDetails}' title="Delete this sports">
										<i class="fa fa-times"></i>Delete
						</s:div></td>
							</s:if>
							<s:else>
								<td></td>
								<td></td>
							</s:else>
						</s:if>
					</s:if>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
<s:if test="%{anyTitle == null  ||  anyTitle == ''}">
	<s:if
		test='%{user.isOnlySchoolAdmin == "Y" || user.roleName=="ROLE_VICEPRINCIPAL" || user.isSchoolPrincipal=="Y" || tempBoolean || user.isOnlySchoolHod=="Y" || user.isSchoolDirector == "Y"}'>
		<div class="alert alert-info">
			Currently there are no Task Information. Please <a href="#"
				onclick="javascript:addTaskInfo()"> <b>Click here</b>
			</a> to create a Task.
		</div>
	</s:if>
	<s:else>
		<div class="alert alert-info">Currently there are no task assign
			to you.</div>
	</s:else>
	</s:if>
	<s:else>
	<div class="alert alert-info">Currently there are no any completed tasks.</div>
	</s:else>
</s:else>
<div id="showTaskDetailsDiv"></div>
<div id="showTaskDescriptionDiv"></div>
<div id="showTaskCommentsDiv"></div>
<script type="text/javascript">
$(document).ready(function() {
	FormComponents.init();
	TableAdvanced.init();
	FormAdvanced.init();
	UIExtendedModals.init();
	$("input:checkbox, input:radio:not('.toggle')").uniform();
});
function showTaskDescription(id){
	if(isNonEmpty(id)){
	  var pars = "tempId=" + id;
        $.ajax( {
	        url : jQuery.url.getChatURL("/staff/ajaxDoPopUpTaskDescPreview.do"),
			cache : true,
			data : pars,
			success : function(html) {
			$("#showTaskDescriptionDiv").html(html);
			}
		});
	}
 }
function showTaskInfromationDetails(taskDetailsId) {
	var pars = "taskId=" + taskDetailsId;
	$('#showTaskDetailsDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
			url : jQuery.url.getChatURL("/staff/ajaxShowTaskDetailsProcess.do"),
			cache : false,
			data : pars,
			success : function(html) {
				$("#showTaskDetailsDiv").html(html);
			}
		});
} 
 function showTaskComments(id){
		if(isNonEmpty(id)){
		  var pars = "taskId=" + id;
	        $.ajax( {
		        url : jQuery.url.getChatURL("/staff/ajaxDoPopUpTaskComments.do"),
				cache : true,
				data : pars,
				success : function(html) {
				$("#showTaskCommentsDiv").html(html);
				}
			});
		}
	 }
 
 function addTaskInfo() {
	$('a#addTaskInfo').click();
} 
 
</script>
