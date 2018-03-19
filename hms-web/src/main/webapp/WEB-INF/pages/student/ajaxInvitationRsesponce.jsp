<%@ include file="/common/taglibs.jsp"%>
<td colspan="6">
<s:if test="%{eventInvitedUser != null}">
	<jsp:include page="/WEB-INF/pages/student/eventInterestedList.jsp" />
</s:if>
<s:else>
	<a href="javascript:viewIntersetedList(<s:property value="anyId"/>);">
		<font size="1px">SEE WHO ELSE IS ATTENDING &nbsp;&gt;&gt;</font> </a>
		<br/><br/>
		<div id="interestedInvitesDiv<s:property value="anyId"/>" style="display: none;">
		   <jsp:include page="/WEB-INF/pages/student/eventInterestedList.jsp" />
		</div>
		<div id="test<s:property value="anyId"/>">
	           Will you be attending this event?
			<br />
		<s:form id="editGroupType" action="ajaxRegisterStudentEvent" method="post" theme="css_xhtml">
		<s:hidden name="anyId"/>
			
			<table>
				<tr>
					<td>
						<input type="radio" name="eventInvitedUser.eventAccepted" value="Y" checked="checked"
							id="selectedStatusY<s:property value="id"/>">
						Yes
					</td>
				</tr>
				<tr>
					<td>
						<input type="radio" name="eventInvitedUser.eventAccepted" value="N"
							id="selectedStatusN<s:property value="id"/>">
						No
					</td>
				</tr>
				<tr>
					<td>
						<input type="radio" name="eventInvitedUser.eventAccepted" value="MB"
							id="selectedStatusMB<s:property value="id"/>">
						May Be
					</td>
				</tr>
			<tr>
				<td >
					<div>
						<sj:submit   targets="eventsResults" value="Register" onClickTopics="editGroupTypeFormValidation"
				cssClass="submit small" indicator="indicator" formIds="editGroupType" />
					</div>
				</td>
				<td>
					<s:url id="cancelReg" action="ajaxCancelRegistration"
				includeParams="all" escapeAmp="false">
				<s:param name="anyId" value="{anyId}" />
			</s:url>
			<sj:a href="%{cancelReg}" onCompleteTopics="cancelRegistration" cssClass="cancelButton"
				indicator="indicator" targets="registerEvent%{anyId}">
				Cancel
			</sj:a>
				</td>
			</tr>
			</table>
		</s:form>
		</div>
		</s:else>
		 
</td>
<script type="text/javascript">
changePageTitle('Edit Group Type');
$(document).ready(
function() {
	$.subscribe('editGroupTypeFormValidation', function(event, data) {
		  if ($('#editGroupType').valid())
               return true;
           else
               return false;
	});
});

$.subscribe('cancelRegistration', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});

function viewIntersetedList(eventId)
	{		
		if(document.getElementById("interestedInvitesDiv"+eventId).style.display == 'block'){
			document.getElementById("interestedInvitesDiv"+eventId).style.display="none";
			document.getElementById("test"+eventId).style.display="block";	
		}else{
			document.getElementById("interestedInvitesDiv"+eventId).style.display="block";	
			document.getElementById("test"+eventId).style.display="none";		
		}		
	}	
</script>