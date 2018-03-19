<%@ include file="/common/taglibs.jsp"%>
<div class="spaceDiv"></div> 
<div id="commentsReplyDiv" class="col-md-12">
   <div class="scroller" style="max-height: 600px;" data-always-visible="0" data-rail-visible="0" data-handle-color="#dae3e7">
	<div class="todo-tasklist">
		<s:if test="%{shareUserActivitiesCommentsList != null && !shareUserActivitiesCommentsList.isEmpty()}">
			 <ul class="timeline">
				<li class="timeline-grey">
					 <s:iterator value="shareUserActivitiesCommentsList">
						 <div class="timeline-body">
							<div class="media">
								 <s:if test="%{adjustedStudentImagePath != null &&  adjustedStudentImagePath != ''}">
									<img src='<c:url value="${adjustedStudentImagePath}"/>'  class="todo-userpic pull-left"/> 
								</s:if> 
								<s:else>
									<img src="../img/avatar.png" id="profileImagesDiv"  class="todo-userpic pull-left"/>
								</s:else>
								<div class="media-body">
									<h5 class="media-heading"><s:property value="commentUserAccount.fullPersonName" /></h5>
									<s:property value="commentDescription" escapeHtml="false"/>
								</div>
								<div class="todo-tasklist-controls pull-left">
										<span class="todo-tasklist-date"><i class="fa fa-calendar"></i> <s:property value="commentDateStr" /> </span>
								</div>
							</div>
						</div>
					</s:iterator>
				</li>
			</ul>
	   </s:if>
 	</div>
</div>
<div class="col-md-12">
	<s:form id="addNewComments" action="ajaxAddCommentsForSocail" method="post" theme="simple" cssClass="form-horizontal" namespace="/alumnee" enctype="multipart/form-data">
		<s:hidden name="shareUserActivitiesVO.id" value="%{shareUserActivitiesVO.id}"></s:hidden>
		<sj:textarea name="shareUserActivitiesCommentsVO.commentDescription" id="commentDescription" rows="2" cssClass="form-control required" cols="20"></sj:textarea>
		<div class="spaceDiv"></div>
		<div>
			<sj:submit value="Post a Comment" cssClass="submitBt btn blue btn-sm" onBeforeTopics="addNewCommentsForm" resetForm="true"
				formIds="addNewComments" targets="commentsReplyDiv" indicator="indicator" validate="true"/>
				<a href="#" id="cancelDiv" class=" btn btn-sm default">Cancel</a>
		</div>
		<div class="spaceDiv"></div>
	</s:form>
</div>
</div>
<script>
$('#cancelDiv').click(function(){
	$('#commentsReplyDiv').remove();
});
$(document).ready( function() {
	$('#commentDescription').val('');
});
$.subscribe('addNewCommentsForm', function(event, data) {
	if ($('#addNewComments').valid()){
		return true;
	}else{
		event.originalEvent.options.submit=false;
	}
});
</script>