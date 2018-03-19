<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div  class="col-md-12">
		<div  id="commentsReplyDiv">
			<div class="todo-tasklist">
				<div class="todo-tasklist-item todo-tasklist-item-border-green">
					 <s:hidden id="%{#rowstatus.count}" name="createdDate" value="%{createdDate}"></s:hidden>
						<s:if test="%{socialDiscussionsVO.adjustedStudentImagePath != null}">
								<img src='<c:url value="${socialDiscussionsVO.adjustedStudentImagePath}"/>'  class="todo-userpic pull-left"  width="40px" height="40px"/> 
						</s:if> 
						<s:else>
							<img src="../img/avatar.png" id="profileImagesDiv"  class="todo-userpic pull-left" width="40px" height="40px">
						</s:else> 
						<div class="todo-tasklist-item-title">
							 <s:property value="socialDiscussionsVO.subject" />
						</div>
						<div class="todo-tasklist-item-text">
							  <s:property value="socialDiscussionsVO.description" escapeHtml="false"/>
						</div>
						 <div class="todo-tasklist-controls pull-left">
							<span class="todo-tasklist-date"><i class="fa fa-calendar"></i> <s:property value="socialDiscussionsVO.messageDate" /> </span>
							Attachments:  <a href='${pageContext.request.contextPath}/<s:property value="socialDiscussionsVO.attachmentPath" /><s:property value="socialDiscussionsVO.attachmentName" />'><s:property value="socialDiscussionsVO.attachmentName" /></a>
						</div>
					</div>
					<div> 
				  		<jsp:include page="/WEB-INF/pages/alumnee/ajaxGetDisComments.jsp"></jsp:include>
				     </div>
				 </div>
			 </div>
	</div>
</div>