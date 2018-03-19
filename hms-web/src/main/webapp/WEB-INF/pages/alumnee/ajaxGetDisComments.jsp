<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />
<div class="spaceDiv"></div> 
<div class="col-md-12">
	<s:form id="forDiscussions" action="ajaxAddCommentsForDiscussions" method="post" theme="simple" cssClass="form-horizontal" namespace="/alumnee">
		<s:hidden name="socialDiscussionsVO.id" value="%{socialDiscussionsVO.id}"></s:hidden>
		<sj:textarea name="socialDiscussionsCommentsVO.commentDescription" id="commentDescription" rows="2" cssClass="form-control required" cols="20"></sj:textarea>
		<div class="spaceDiv"></div>
		<div>
			<sj:submit value="Post a Comment" cssClass="submitBt btn blue btn-sm" onBeforeTopics="addNewCommentsForm" resetForm="true"
				formIds="forDiscussions" targets="commentsReplyDiv" indicator="indicator" validate="true"/>
				<s:url id="viewDiscussions" action="ajaxDiscussionsHome" namespace="/alumnee"> </s:url>
				<sj:a href="%{viewDiscussions}" cssClass="btn btn-sm default" targets="mainContentDiv">Cancel</sj:a>
		</div>
		<div class="spaceDiv"></div>
	</s:form>
</div>
<div class="row col-md-12">
   <div style="min-height: 800px;overflow-y: scroll;">
	<div class="todo-tasklist">
		<s:if test="%{socialDiscussionsCommentsList != null && !socialDiscussionsCommentsList.isEmpty()}">
			 <ul class="timeline">
				<li class="timeline-grey">
					 <s:iterator value="socialDiscussionsCommentsList">
						 <div class="timeline-body">
							<div class="media">
								<s:if test="%{adjustedStudentImagePath != null &&  adjustedStudentImagePath != ''}">
									<img src='<c:url value="${adjustedStudentImagePath}"/>'  class="todo-userpic pull-left"/> 
								</s:if> 
								<s:else>
									<img src="../img/avatar.png" id="profileImagesDiv"  class="todo-userpic pull-left">
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
</div>
<script>
$(document).ready( function() {
	$('#commentDescription').val('');
});
$.subscribe('addNewCommentsForm', function(event, data) {
	if ($('#forDiscussions').valid()){
		return true;
	}else{
		event.originalEvent.options.submit=false;
	}
});
</script>