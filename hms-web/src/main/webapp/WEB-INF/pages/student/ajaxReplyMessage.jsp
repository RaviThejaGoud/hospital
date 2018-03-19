<%@ include file="/common/taglibs.jsp"%>
<div style="padding: 2px;">
<s:if test="%{messages != null}">
	<s:property value="messages.createdBy" />&nbsp;&nbsp;<s:property value="messages.messageDateStr" />
	<br/>
	<s:property value="messages.messageDescription" />
	</s:if>
	<s:else>
		currently there are no Messages.
	</s:else>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		var validator;
		$.subscribe('formValidationForStudentMessages', function(event, data) {
			if ($('#formStudentMessages').valid())
				return true;
			else
				return false;
		});
	});
</script>
