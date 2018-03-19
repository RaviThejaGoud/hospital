<%@ include file="/common/taglibs.jsp"%>
<div >
		<b>Yes(<s:property value="inviteAccepted.size()" />)&nbsp;:&nbsp;</b><br/>
		<s:if test="%{inviteAccepted != null && !inviteAccepted.isEmpty()}">
			<s:iterator value="inviteAccepted" >
			<s:property /><br />
			</s:iterator>
		</s:if><br />
		<b>No(<s:property value="inviteDecline.size()" />)&nbsp;:&nbsp;</b><br/>
			<s:if test="%{inviteDecline != null && !inviteDecline.isEmpty()}">
			<s:iterator value="inviteDecline" >
			<s:property /><br />
			</s:iterator>
		</s:if><br />
		<b>MayBe(<s:property value="objectList.size()" />)&nbsp;:&nbsp;</b><br/>
			<s:if test="%{objectList != null && !objectList.isEmpty()}">
			<s:iterator value="objectList" >
			<s:property /><br />
			</s:iterator>
		</s:if>
</div>      
	
	
	
	