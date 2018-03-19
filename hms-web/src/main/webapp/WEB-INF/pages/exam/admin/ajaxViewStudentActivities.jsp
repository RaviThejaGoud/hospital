<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />
<s:if test="%{objectList != null && !objectList.isEmpty()}">
<div class="form-group">
	<div class="panel-body col-md-12">
		<div class="col-md-1">
			<span class="label label-danger"> NOTE : </span>
		</div>
		<div class="col-md-10">
			<ul>
				<li>
					You can view all activities from the below.
				</li>
				<li>
					You can create or Update any activities.
				</li>
			</ul>
		</div>
	</div>
</div>
	<table class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
		<thead>
			<tr>
				<th>
					Student Activity Name
				</th>
				<th>
					Category Name
				</th>
				<th>
					Manage Sub Activities
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
				<tr id="removeTarget">
					<td>
						<s:if test='%{activityName != null && activityName != ""}'>
							<s:property value="activityName" />
						</s:if>
					</td>
					<td>
						<s:if test='%{categoryName != null && categoryName != ""}'>
							<s:property value="categoryName" />
						</s:if>
					</td>
					<td>
						<s:url id="addActitypes"
							action="ajaxViewAllStudentActivitiesTypes" includeParams="all"
							escapeAmp="false" namespace="/exam">
							<s:param name="studentActivities.id" value="%{id}" />
						</s:url>
						<sj:a href="%{addActitypes}" cssClass="btn btn-xs purple"
							targets="studentsActivitiesContent">
							<i class="fa fa-edit"></i>Manage Sub Activities</sj:a>
					</td>
					<td>
						<a data-toggle="modal" data-target="#EditActivityDiv"
							class="btn btn-xs purple"
							onclick="javascript:PopupEditStudentActivity(<s:property value="%{id}" />);"><i
							class="fa fa-edit"></i>Edit </a>
					</td>
					<td>
						<s:if test='%{#session.previousYear=="N"}'>
							<s:url id="removeStudActivity"
								action="ajaxRemoveStudentActivityInfo" escapeAmp="false"
								includeParams="all" namespace="/exam">
								<s:param name="studentActivities.id" value="%{id}"></s:param>
							</s:url>
							<s:div cssClass="btn btn-xs red"
								onclick="javascript:confirmDeleteActivity(this,'studentsActivitiesContent');"
								id='%{removeStudActivity}' theme="simple"
								title="Delete this grade">
								<i class="fa fa-times"></i>Delete
						</s:div>
							<%--<s:div cssClass="btn btn-xs red emsStudentActivityRemove" 
								id='%{removeStudActivity}' title="Delete this student activity">
								<i class="fa fa-times"></i>Delete
							</s:div>
						--%>
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
		Currently there are no  activities added.
	</div>
</s:else>
<div id="EditActivityDiv"></div>
<script type="text/javascript">
changePageTitle("Manage Student Activities");
$(document).ready(function(){
 TableAdvanced.init();
UIExtendedModals.init();
});
 function confirmDeleteActivity(event) {
	thishref = $(event).attr('id');
	var studentActivityId = thishref.split("=");
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
			var studentActivityList = response.studActivities;
			var studentActivityId= studentActivityList.split("_");
				if(studentActivityId[0] > 0){
				 	var r=confirm('This student activity have activity types. If you want to remove this activity and activity types please click on Ok button otherwise click on Cancel button.');
				 	if(r==true){
				 	 	prdDiv.parent().parent().remove();
					 	deleteStudentActivity(studentActivityId[1]);
				 	}else{
				 	  prdDiv.remove();
				 	}
				}else{
					prdDiv.parent().parent().remove();
					deleteStudentActivity(studentActivityId[1]);
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
  function deleteStudentActivity(studentActivityId){
        $.ajax( {
	        url : jQuery.url.getChatURL("/exam/ajaxRemoveStudentActivity.do?studentActivities.id="+studentActivityId),
			cache : false,
			success : function(html) {
			 $('#studentsActivitiesContent').html(html);
			}
		});
  }
$('.js-activated').dropdownHover().dropdown();
function PopupEditStudentActivity(id) {
		var url = jQuery.url.getChatURL("/exam/ajaxDoAddStudentActivities.do");
		$('#EditActivityDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
				url : url,
				cache : false,
				data : "studentActivities.id=" + id,
				success : function(html) {
					$("#EditActivityDiv").html(html);
				}
			});
		} 

</script>