<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="spaceDiv"></div>
   <div id="inner-content-div">
	<div class="todo-tasklist">
		<s:if test="%{objectList != null && !objectList.isEmpty()}">
			<s:iterator value="objectList" status="rowstatus">
			  <s:hidden id="%{#rowstatus.count}" name="createdDate" value="%{createdDate}"></s:hidden>
				<s:if test='%{messageType == "S"}'>
					<div class="todo-tasklist-item todo-tasklist-item-border-green">
							<s:if test="%{adjustedStudentImagePath != null &&  adjustedStudentImagePath != ''}">
								<img src='<c:url value="${adjustedAttachmentFilePath}"/>'  class="todo-userpic pull-left"  width="40px" height="40px"/> 
							</s:if> 
							<s:else>
								<img src="../img/avatar.png" id="profileImagesDiv"  class="todo-userpic pull-left" width="40px" height="40px">
							</s:else> 
						<div class="todo-tasklist-item-title">
							 <s:property value="accountVO.fullName" />
						</div>
						<div class="todo-tasklist-item-text">
							 <s:property value="description" escapeHtml="false"/>
						</div>
						<div class="todo-tasklist-controls pull-left">
							<span class="todo-tasklist-date"><i class="fa fa-calendar"></i> <s:property value="messageDateStr" /> </span>
						</div>
					</div>
				</s:if>
				<s:elseif test='%{messageType == "E"}'>
					<div class="todo-tasklist-item todo-tasklist-item-border-green">
							<s:if test="%{adjustedStudentImagePath != null &&  adjustedStudentImagePath != ''}">
								<img src='<c:url value="${adjustedStudentImagePath}"/>'  class="todo-userpic pull-left"  width="40px" height="40px"/> 
							</s:if> 
							<s:else>
								<img src="../img/avatar.png" id="profileImagesDiv"  class="todo-userpic pull-left" width="40px" height="40px">
							</s:else> 
						<div class="todo-tasklist-item-title" id="eventsContentDiv">
							 <s:property value="accountVO.fullName" /> added a new event,		
							 <s:url id="eventsDetails" action="ajaxViewEventsDetails" namespace="/alumnee" includeParams="all" escapeAmp="false">
							 	<s:param value="%{id}" name="shareUserActivitiesVO.id" />
							 </s:url> 
							 <sj:a  href="%{eventsDetails}" targets="mainContentDiv" cssClass="ajaxify EVENT"><s:property value="eventName" /></sj:a>
						</div>
						<div class="todo-tasklist-item-text">
							 <s:property value="description" escapeHtml="false"/>
						</div>
						 <div class="todo-tasklist-controls pull-left">
							<span class="todo-tasklist-date"><i class="fa fa-calendar"></i> <s:property value="messageDateStr" /> </span>
						</div>
					</div>
				</s:elseif>
				<s:else>
					<div class="todo-tasklist-item todo-tasklist-item-border-green">
						<s:if test="%{adjustedStudentImagePath != null &&  adjustedStudentImagePath != ''}">
							<img src='<c:url value="${adjustedStudentImagePath}"/>'  class="todo-userpic pull-left"  width="40px" height="40px"/> 
						</s:if> 
						<s:else>
							<img src="../img/avatar.png" id="profileImagesDiv"  class="todo-userpic pull-left" width="40px" height="40px">
						</s:else> 
						<div class="todo-tasklist-item-title">
							 <s:property value="accountVO.fullName" /> added a new photo
						</div>
						<div class="todo-tasklist-item-text">
							 <s:property value="description" escapeHtml="false"/>
						</div>
						<div class="todo-tasklist-controls pull-left">
							<span class="todo-tasklist-date"><i class="fa fa-calendar"></i> <s:property value="messageDateStr" /> </span>
						</div>
					</div>
				</s:else>
				<div class="col-md-12">
					<ul class="list-inline">
						<li>
							<s:url id="urlEventsDetails" action="ajaxGetComments" namespace="/alumnee" includeParams="all" escapeAmp="false">
							 	<s:param value="%{id}" name="shareUserActivitiesVO.id" />
						 	</s:url> 
							<sj:a href="%{urlEventsDetails}" targets='commentsDiv%{id}' indicator="indicator">Comment</sj:a>
						</li>
						<li><a href="#" id="likeDiv">Like</a></li>
						<li style="color: cadetblue"><div id="date<s:property value="#rowstatus.count" escapeHtml="false"/>"> </div></li>
					</ul>
				</div>
				<div id='commentsDiv<s:property value="id" />'></div>
			</s:iterator>
	   </s:if>
	   <s:else>
	   	<div class="alert alert-info">
	   		currently ther are no Posts.  
	   	</div>
	   </s:else>
	  </div>
	</div>
	<script type="text/javascript"	src="${pageContext.request.contextPath}/scripts/jQuery/jquery.timeago.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
	<script type="text/javascript">
	  $('div#eventsContentDiv').click(function() {
		window.location.hash = "target=USTM.ajaxify EVENT";
		$('a#dashboard').parent('li').removeClass('start active');
		$('li#SOD').addClass('active');
		$('a#eventsHome').parent('li').addClass('active');
		$('a#USTM').click();
	});
	
	 
	$('span#clickCommentDiv').click(function(){
		$('div#commentsDiv').toggle();
	})
	$.subscribe('addNewCommentsForm', function(event, data) {
		if ($('#addNewComments').valid()){
			return true;
		}else{
			event.originalEvent.options.submit=false;
		}
	});
	dateCal();
	function dateCal() {
		var listSize = ${fn:length(objectList)};
		for(var i=1;i<=listSize;i++)
		{
			var date = $('#'+i).val();
			$('#date'+i).html(jQuery.timeago(date)) ; 
		}
	}
	$('#inner-content-div').slimScroll({
	    height: '900px'
	}); 
	</script>