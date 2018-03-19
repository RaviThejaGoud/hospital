<%@ include file="/common/taglibs.jsp"%>
<div style="padding: 20px;">
	<s:if test="%{messages != null }">
		<h3>
			<s:property value="messages.title" />
		</h3>
		 <div>
			<a  rel="nofollow" href='<c:url value='/admin/ajaxDownloadFiles.do'/>?id=<s:property value="id"/>'><s:property value="attachment.fileName" /></a>
			<br />
		</div>
		<div style="margin-bottom: 15px">
			Posted on
			<s:property value="messages.createdDateStr" />
			by
			<a class="tooltip1" href="#">
                           <s:property value="messages.createdBy" /> </a>
		</div>
		<!--<div >
			Email&nbsp;&nbsp;&nbsp;:&nbsp;<s:property value="userInquiry.emailAddress"/><br/>
			Phone&nbsp;:&nbsp;<s:property value="userInquiry.phoneNumber"/>
			</div><br/>
		-->
		<div id="unFormattedMsg" style="display: none">
			<s:property value="messages.description" />
		</div>
		<div id="formattedMsg"></div>
	</s:if>
</div>
<script type="text/javascript">
	changePageTitle('Read More Teacher Message');
</script>


