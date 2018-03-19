<%@ include file="/common/taglibs.jsp"%>
<div id="eventpopupleft">
	<b><s:property value="messages.title" /></b>
	<table>
		<tr>
			<td>
				<strong>To&nbsp;:</strong>&nbsp;
			</td>
			<td>
				<s:property value="messages.receiverAccountIdDescForSWM" />
			</td>
		</tr>
		<tr>
			<td>
				<strong>From&nbsp;:</strong>&nbsp;
			</td>
			<td>
				<s:property value="messages.createdBy" />
			</td>
		</tr>
		<tr>
			<td valign="top">
				<strong>Message&nbsp;:</strong>&nbsp;
			</td>
			<td>
				<s:property value="messages.messageDescription"/>
			</td>
		</tr>
	</table>
</div>
