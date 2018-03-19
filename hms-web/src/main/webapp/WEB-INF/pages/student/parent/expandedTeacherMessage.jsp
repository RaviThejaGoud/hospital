<%@ include file="/common/taglibs.jsp"%>
<div style="padding: 20px;">
	<div style="float: right;">
		<s:url id="doCancelGroup" action="ajaxDoGetTeacherMessagesForParent"
			includeParams="all" namespace="/student"></s:url>
		<sj:a href="%{doCancelGroup}" indicator="indicator"
			targets="myMessagesContent" button="false">Back</sj:a>
	</div>
	<s:if test="%{messages != null }">
		<h3>
			<s:property value="messages.title" />
		</h3>

		<div style="margin-bottom: 15px">
			Posted on
			<s:property value="messages.messageDateStr" />
			by
			<b><s:property value="messages.fullPersonName" />
			</b>
		</div>
		<!--<div >
			Email&nbsp;&nbsp;&nbsp;:&nbsp;<s:property value="userInquiry.emailAddress"/><br/>
			Phone&nbsp;:&nbsp;<s:property value="userInquiry.phoneNumber"/>
			</div><br/>
		-->
		<div id="unFormattedMsg" style="display: none">
			<s:property value="messages.messageDescription" />
		</div>
		<div id="formattedMsg"></div>
		<!--<div>
		<a href="#" onclick="javascript:getAjaxRemoveUserInquiry(<s:property value="id" />);">Remove this Post</a>
		</div>
	-->
	</s:if>
</div>
<script type="text/javascript">
	changePageTitle('Read More Teacher Message');
</script>


