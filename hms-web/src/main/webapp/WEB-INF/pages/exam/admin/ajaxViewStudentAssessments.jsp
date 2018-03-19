<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />
<h4>
	Student assessments
</h4>
<s:if test="%{objectList != null && !objectList.isEmpty()}">
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th>
					Assessment Name
				</th>
				<th>
					Description
				</th>
				<th>
					Edit
				</th>
				<th>
					Delete
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="objectList">
				<tr>
					<td>
						<s:if test='%{assessmentName != null && assessmentName != ""}'>
							<s:property value="assessmentName" />
						</s:if>
					</td>
					<td>
						<s:property value="assessmentDescription" />
					</td>
					<td>
						<a data-toggle="modal" data-target="#EditAssessmentDiv"
							class="btn btn-xs purple"
							onclick="javascript:PopupEditStudentAssessment(<s:property value="%{id}" />);"><i
							class="fa fa-edit"></i>Edit </a>
					</td>
					<td>
						<s:if test='%{#session.previousYear=="N"}'>
							<s:url id="removeStudentAssessment"
								action="ajaxRemoveStudentAssessmentInfo" includeParams="all"
								escapeAmp="false" namespace="/exam">
								<s:param name="studentsAssessments.id" value="%{id}"></s:param>
								<s:param name="anyTitle" value="%{assessmentName}"></s:param>
							</s:url>
							<s:div cssClass="btn btn-xs red"
								onclick="javascript:confirmDialogWithTarget(this,'studentsActivitiesContent');"
								id='%{removeStudentAssessment}' theme="simple"
								title="Delete this Assessment">
								<i class="fa fa-times"></i>Delete
							</s:div>
						</s:if>
						&nbsp;
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no assessments added.
	</div>
</s:else>
<div id="EditAssessmentDiv"></div>
<script type="text/javascript">
changePageTitle("Manage Student Activities");
$(document).ready(function(){
 TableAdvanced.init();
UIExtendedModals.init();
});
 function confirmDeleteAssessment(event) {
	thishref = $(event).attr('id');
	var studentAssessmentId = thishref.split("=");
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
			dataType : 'json',
			success : function(response) {
			var studentAssessmentsList = response.studAssessments;
			var studentAssessmentId= studentAssessmentsList.split("_");
				if(studentAssessmentId[0] > 0){
				prdDiv.parent().parent().remove();
					deleteStudentAssessment(studentAssessmentId[1]);
				 	
				}
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
  function deleteStudentAssessment(studentAssessmentId){
        $.ajax( {
	        url : jQuery.url.getChatURL("/exam/ajaxRemoveStudentAssessments.do?studentsAssessments.id="+studentAssessmentId),
			cache : false,
			success : function(html) {
			 $('#studentsActivitiesContent').html(html);
			}
		});
  }
$('.js-activated').dropdownHover().dropdown();
function PopupEditStudentAssessment(id) {
		var url = jQuery.url.getChatURL("/exam/ajaxDoAddStudentAssessments.do");
		$('#EditAssessmentDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
				url : url,
				cache : false,
				data : "studentsAssessments.id=" + id,
				success : function(html) {
					$("#EditAssessmentDiv").html(html);
				}
			});
		} 

</script>