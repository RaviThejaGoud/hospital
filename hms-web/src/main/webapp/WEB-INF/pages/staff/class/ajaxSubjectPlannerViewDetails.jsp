<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<s:if test="%{objectList != null && !objectList.isEmpty()}">
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th>
					Chapter Name
				</th>
				<th>
					Topic Name
				</th>
				<th>
					Start Date
				</th>
				<th>
					Complete Date
				</th>
				<th>
					Comments
				</th>
				<s:if
					test='%{user.isOnlySchoolAdmin == "N" && (#session.previousYear == "N")}'>
					<th>
						Edit
					</th>
					<th>
						Delete
					</th>
				</s:if>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="objectList">
				<tr>
					<td>
						<s:property value="chapterName" />
					</td>
					<td>
						<s:property value="topicName" />
					</td>
					<td>
						<s:property value="startDateStr" />
					</td>
					<td>
						<s:property value="completeDateStr" />
					</td>
					<td>
						<a data-toggle="modal" href="#commentsPlannerDiv"
							class="btn btn-xs green"
							onclick="javascript:PopupCommentsDiv(<s:property value="%{studyClassId}" />,<s:property value="%{studySubjectId}" />,<s:property value="%{id}" />);"><i
							class="fa fa-plus"></i>Add / View </a>
					</td>
					<s:if
						test='%{user.isOnlySchoolAdmin == "N" && (#session.previousYear == "N")}'>
						<s:if test='%{staffId == selectedId}'>
							<td>
								<a data-toggle="modal" href="#subjectPlannerDiv"
									class="btn btn-xs purple"
									onclick="javascript:PopupSubjectPlannerDetials(<s:property value="%{studyClassId}" />,<s:property value="%{studySubjectId}" />,<s:property value="%{id}" />);"><i
									class="fa fa-edit"></i>Edit </a>
							</td>
							<td>
								<s:url id="removeSubjectPlanner"
									action="ajaxRemoveSubjectPlanner" includeParams="all"
									escapeAmp="false" namespace="/staff">
									<s:param name="tempId2" value="%{id}"></s:param>
									<s:param name="tempId1" value="%{studySubjectId}"></s:param>
									<s:param name="selectedId" value="%{selectedId}"></s:param>
								</s:url>
								<s:div cssClass="btn btn-xs red emsFileRemovesubjectPlanner"
									id='%{removeSubjectPlanner}' theme="simple"
									title="Delete Chapter">
									<i class="fa fa-times"></i>Delete</s:div>
							</td>
						</s:if>
						<s:else>
							<td>
								-
							</td>
							<td>
								-
							</td>
						</s:else>
					</s:if>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<%-- <s:else>
	<div class="alert alert-info">
		<!-- Subject planner is not created for selected subject. -->
		Please select the class and subject to create or view the subject planner.
	</div>
</s:else> --%>
<div id="subjectPlannerDiv"></div>
<div id="commentsPlannerDiv"></div>
<script type="text/javascript">
$(document).ready(function() {
	$('#classAssignment').addClass('current');
	TableAdvanced.init();
});
var staffId=$('span#staffIdSelected').attr("data-id");
function PopupSubjectPlannerDetials(studyClassId,studySubjectId,id) {
		var pars = "tempId=" + studyClassId + "&tempId1=" + studySubjectId + "&tempId2=" +id+"&selectedId="+staffId;;
		var url = jQuery.url.getChatURL("/staff/ajaxDoAddSubPlanner.do");
		$('#subjectPlannerDiv') .html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#subjectPlannerDiv").html(html);
			}
		});
	}
function PopupCommentsDiv(studyClassId,studySubjectId,id) {
		
		var pars = "tempId=" + studyClassId + "&tempId1=" + studySubjectId + "&tempId2="+id+"&selectedId="+staffId;
		var url = jQuery.url.getChatURL("/staff/ajaxDoAddCommentsSyllebusPlanner.do");
		$('#commentsPlannerDiv')
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#commentsPlannerDiv").html(html);
			}
		});
	}
	
	$(function() {
	if ($('div.emsFileRemovesubjectPlanner')) {
		$('div.emsFileRemovesubjectPlanner').unbind('click');
		$("div.emsFileRemovesubjectPlanner").click(function() {
			confirmDeleteSubjectPlanner(this);
		});
	}
});

function confirmDeleteSubjectPlanner(event) {
	thishref = $(event).attr('id');
	var filename = thishref.split("=");
	if ($(event).next('.question').length <= 0) {
		$(event)
				.after(
						'<div class="question">Are You Sure?<br/> <span class="yes">Yes</span><span class="cancel">Cancel</span></div>');
	}
	$(event).next('.question').animate( {
		opacity : 1
	}, 300);
	$('.yes').unbind('click');
	$('.yes').bind('click', function() {
		var prdDiv = $(this).parents('.question');
		prdDiv.html('Processing...');
		$.ajax( {
			url : thishref,
			cache : false,
			success : function(html) {
				prdDiv.parent().parent().remove();
				$('button.close').click();
				$('#subjectPlannerViewDiv').html(html);
			}
		});
	});
	$('.cancel').unbind('click');
	$('.cancel').bind('click', function() {
		$(this).parents('.question').fadeOut(300, function() {
			$(this).remove();
		});
		return false;
	});
}

</script>