<%@ include file="/common/taglibs.jsp"%>
<div id="activityTypeDiv">
	<s:url id="doStudActivitiesSettings"
		action="ajaxManageStudentActivities" namespace="/exam" />
	<sj:a href="%{doStudActivitiesSettings}" targets="mainContentDiv"
		cssClass="btn default" cssStyle="float:right;margin-right:20px;">
		<i class="fa fa-mail-reply"></i> 
		Back To View Activities</sj:a>	
	<s:if test='%{#session.previousYear == "N"}'>
		<s:url id="doAddNewActivityType" action="ajaxDoAddNewActivityType"
			includeParams="all" escapeAmp="false" namespace="/exam">
			<s:param name="studentActivities.id" value="%{studentActivities.id}"></s:param>
			<s:param name="studentActivityTypes.id" value="0"></s:param>
		</s:url>
		<sj:a href="%{doAddNewActivityType}"
			targets="studentsActivitiesContent" cssClass="btn green"
			cssStyle="float:right;margin-right:20px;"><i class="fa fa-plus"></i>
			<b>Add Sub Activity</b>
		</sj:a>
	</s:if>	
	<div class="spaceDiv">
		&nbsp;
	</div>
	<div class="spaceDiv">
		&nbsp;
	</div>
	<div id="addBlockRacks<s:property value='studentActivities.id' />"
		style="display: none;"></div>
	<div id="hideRackSuccess">
		<jsp:include page="/common/messages.jsp"></jsp:include>
	</div>
	<s:if test="%{tempList != null && !tempList.isEmpty()}">
		<table
			class="table table-striped table-bordered table-hover table-full-width"
			id="sample_2">
			<thead>
				<tr>
					<th>
						Sub Activity Name
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
			</thead>
			<tbody>
				<s:iterator value="tempList">
					<tr>
						<td>
							<s:property value="activityTypeName" />
						</td>
						<td>
							<s:property value="activityTypeDescription" />
						</td>
						<td>
							<a data-toggle="modal" data-target="#editManageActivityDiv"
								class="btn btn-xs purple"
								onclick="javascript:PopupEditManageActivity(<s:property value="%{id}" />,<s:property value="studentActivities.id"/>);"><i
								class="fa fa-edit"></i>Edit </a>
						</td>
						<td>
						<s:if test='%{#session.previousYear == "N"}'>
							<s:url id="removeStudActivityType" action="ajaxRemoveStudentActivityTypeInfo"
								includeParams="all" escapeAmp="false" namespace="/exam">
								<s:param name="studentActivityTypes.id" value="%{id}"></s:param>
							</s:url>
							<s:div cssClass="btn btn-xs red"
								onclick="javascript:confirmDialogWithTarget(this,'studentsActivitiesContent');"
								id='%{removeStudActivityType}' title="Delete this student activity type">
								<i class="fa fa-times"></i>Delete
							</s:div>
						</s:if>
					</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			Currently there no sub activities added.
		</div>
	</s:else>
</div>
<div id="editManageActivityDiv"></div>
<script type="text/javascript">
TableAdvanced.init();	
 $(function(){
		if ($('div.emsStudentActivityTypeRemove')) {
			$('div.emsStudentActivityTypeRemove').unbind('click');
			$("div.emsStudentActivityTypeRemove").click(function() {
				confirmDeleteActivityType(this);
			});
		}  
	});
 function confirmDeleteActivityType(event) {
	thishref = $(event).attr('id');
	var studentActivityTypeId = thishref.split("=");
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
			var academicPerformanceList = response.studAcademic;
				if(academicPerformanceList > 0 ){
				 	var r=confirm('This activity type have student grades. If you want to delete activity type and students grades click on Ok button otherwise click on cancel button.');
				 	if(r==true){
				 	 	prdDiv.parent().parent().remove();
					 	deleteStudentActivityType(studentActivityTypeId[1]);
				 	}else{
				 	  prdDiv.remove();
				 	}
				}else{
					prdDiv.parent().parent().remove();
					deleteStudentActivityType(studentActivityTypeId[1]);
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
function PopupEditManageActivity(id,activityId) {
		var url = jQuery.url.getChatURL("/exam/ajaxDoAddNewActivityType.do");
		$('#editManageActivityDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
				url : url,
				cache : false,
				data : "studentActivityTypes.id=" + id+"&studentActivities.id="+activityId,
				success : function(html) {
					$("#editManageActivityDiv").html(html);
				}
			});
		}	
	
   
  function deleteStudentActivityType(studentActivityTypeId){
  url=jQuery.url.getChatURL("/exam/ajaxRemoveStudentActivityType.do?studentActivityTypes.id="+studentActivityTypeId+"&studentActivities.id="+<s:property value="studentActivities.id"/>);
        $.ajax( {
	        url : url,
			cache : false,
			success : function(html) {
			 $('#viewAllRacks'+<s:property value="studentActivities.id"/>).html(html);
			}
		});
  }
function toggleActivitypesCont(activityId){
	$('#viewAllRacks'+activityId).hide();
}
</script>