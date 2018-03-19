<%@ include file="/common/taglibs.jsp"%>
 <div id="addEventCommentsDiv">
 <div class="row">
	<div class="col-md-12" >
	<jsp:include page="/common/messages.jsp" />
		<div class="form-horizontal"  style="background: #d9cfd0;float:left;">
			<div class="col-md-2">
				<div class="tile-body">
						<img alt="" src="../img/event.png" style="height: 130px;width: 130px;">
			</div>
			<div>
				<s:url id="EditEventDetails" action="ajaxEditEventDetails" namespace="/alumnee" >
							<s:param name="shareUserActivitiesVO.id" value="%{shareUserActivitiesVO.id}"></s:param>
				</s:url>
				<sj:a  href="%{EditEventDetails}" targets="eventsDiv" cssClass="btn btn-xs purple"><i class="fa fa-edit"></i>Edit Event</sj:a>
			</div>
		</div>
		<s:if test="%{shareUserActivitiesVO != null}">
			<div class="col-md-10">
				<div class="form-group">
					<label class="control-label col-md-2">Category :</label>
					<div class="col-md-9">
						<p class="form-control-static"><s:property value="shareUserActivitiesVO.status"/></p>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-2">Event Name:</label>
					<div class="col-md-9">
						<p class="form-control-static"><s:property value="shareUserActivitiesVO.eventName"/></p>
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-md-2">Location :</label>
					<div class="col-md-9">
						<p class="form-control-static"><s:property value="shareUserActivitiesVO.eventLocation"/></p>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-2">Start Time :</label>
					<div class="col-md-9">
						<p class="form-control-static"><s:property value="shareUserActivitiesVO.startDateTime"/></p>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-2">End Time :</label>
					<div class="col-md-9">
						<p class="form-control-static"><s:property value="shareUserActivitiesVO.endDateTime"/></p>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-2">Creator :</label>
					<div class="col-md-9">
						<p class="form-control-static"><s:property value="shareUserActivitiesVO.description"/></p>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-2">Description :</label>
					<div class="col-md-9">
						<p class="form-control-static"><s:property value="shareUserActivitiesVO.description"/></p>
					</div>
				</div>
			</div>
		</s:if>
		</div>
	</div>
	<div class="spaceDiv">&nbsp;</div>
	<div class="col-md-12">
		<s:form id="addcomments" action="ajaxAddCommentsForSocailEvent" method="post" theme="simple" cssClass="form-horizontal" namespace="/alumnee" enctype="multipart/form-data">
			<s:hidden name="shareUserActivitiesVO.id" value="%{shareUserActivitiesVO.id}"></s:hidden>
				<div class="form-group">
					<label class="control-label col-md-2"> Comment : </label>
					<div class="col-md-8">
						 <sj:textarea name="shareUserActivitiesCommentsVO.commentDescription" id="description" rows="2" cssClass="form-control required" cols="20"></sj:textarea>
					</div>
				</div>
				<div class="col-md-offset-2 col-md-9">
					<sj:submit value="Post a Comment" cssClass="submitBt btn blue btn-sm" onBeforeTopics="addEventCommentsForm" resetForm="true"
					formIds="addcomments" targets="addEventCommentsDiv" indicator="indicator" validate="true"/>
					<s:url id="eventsHome" action="ajaxEventsHome" namespace="/alumnee"></s:url>
					<sj:a  href="%{eventsHome}" targets="mainContentDiv" cssClass="btn btn-sm default">Cancel</sj:a>
				</div>
		</s:form>
	 </div>
	 <div class="spaceDiv">&nbsp;</div>
	 <div class="col-md-12">
	  <div style="height: 800px;overflow-y: scroll;">
			<div class="todo-tasklist">
				<s:if test="%{shareUserActivitiesCommentsList != null && !shareUserActivitiesCommentsList.isEmpty()}">
					 <ul class="timeline">
						<li class="timeline-grey">
							 <s:iterator value="shareUserActivitiesCommentsList">
								 <div class="timeline-body">
									<div class="media">
										 <s:if test="%{adjustedStudentImagePath != null &&  adjustedStudentImagePath != ''}">
											<img src='<c:url value="${adjustedStudentImagePath}"/>'  class="todo-userpic pull-left"  width="40px" height="40px"/> 
										</s:if> 
										<s:else>
											<img src="../img/avatar.png" id="profileImagesDiv"  class="todo-userpic pull-left" width="40px" height="40px">
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
			   <s:else>
			   <div class="alert alert-info">Currently there are no comments. </div>
			   </s:else>
		 	</div>
		</div>
  </div>
</div>
</div>
<script type="text/javascript">
		$(document).ready(function() {
			changePageTitle("View Event Details");
			$('#description').val('');
		});
		$.subscribe('addEventCommentsForm', function(event, data) {
			if ($('#addcomments').valid()){
				return true;
			}else{
				event.originalEvent.options.submit=false;
			}
		});
	</script>