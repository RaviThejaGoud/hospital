<%@ include file="/common/taglibs.jsp"%>
<div class="spaceDiv"></div>
  <div class="scroller" style="max-height: 600px;" data-always-visible="0" data-rail-visible="0" data-handle-color="#dae3e7">
	<div class="todo-tasklist">
		<s:if test="%{objectList != null && !objectList.isEmpty()}">
			<s:iterator value="objectList" status="status">
				<div class="todo-tasklist-item todo-tasklist-item-border-green">
						 <s:if test="%{adjustedStudentImagePath != null &&  adjustedStudentImagePath != ''}">
							<img src='<c:url value="${adjustedStudentImagePath}"/>'  class="todo-userpic pull-left"  width="40px" height="40px"/> 
						</s:if> 
						<s:else>
							<img src="../img/avatar.png" id="profileImagesDiv"  class="todo-userpic pull-left" width="40px" height="40px">
						</s:else> 
						<div class="todo-tasklist-item-title">
							<s:url id="urlInboxesDetails" action="ajaxViewSocialDiscussions" namespace="/alumnee" includeParams="all" escapeAmp="false">
								<s:param value="%{id}" name="socialDiscussionsVO.id" />
							</s:url>
							<sj:a href="%{urlInboxesDetails}" targets="discussionsDiv" cssClass='ajaxify ASMEL'>
								<s:property value="subject" />
							</sj:a>
						</div>
						<div class="todo-tasklist-item-text">
							<%--  <s:property value="description" escapeHtml="false"/> --%>
							Category: <s:property value="categoryStr" escapeHtml="false"/>
						</div>
						<div class="todo-tasklist-controls pull-left">
							topic started by <b><s:property value="accountVo.fullName" /></b> &nbsp;<span class="todo-tasklist-date"><i class="fa fa-calendar"></i> <s:property value="messageDateStr" /> </span>
						</div>
					</div>
		</s:iterator>
	</s:if>
	<s:else>
		<div class="alert alert-info">
		currently there are no discussions.
		</div>
	</s:else>
	  </div>
	</div>
	
