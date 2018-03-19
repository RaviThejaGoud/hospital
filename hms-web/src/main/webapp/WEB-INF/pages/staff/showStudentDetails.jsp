<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<jsp:include page="/common/messages.jsp"></jsp:include>
	<s:form id="registerStudentEvent1"
					action="ajaxRegisterStudentEventByStaff" method="post"
					theme="css_xhtml">
<s:hidden name="anyId" />
<s:if test="%{studentList != null && !studentList.isEmpty()}">
	<%
		int i = 0;
	%>
	<table class="striped" width="100%" style="margin-bottom: 0;"
		cellpadding="1" cellspacing="1">
		<thead>
			<tr>
				<th style="width: 20%">
					Sno
				</th>
				<th style="width: 40%">
					Roll number
				</th>
				<th style="width: 40%">
					Name
				</th>
				<th>&nbsp;</th>
			</tr>
		</thead>
		
		<s:iterator value="studentList">
			<tr class='loaded'>
				<td style="width: 20%">
					<%	i++; %><%=i%>)
				</td>
				<td style="width: 40%">
					<s:property value="rollNumber" />
				</td>
				<td style="width: 40%">
					<s:property value="account.person.firstName" />
					<s:property value="account.person.lastName" />
				</td>
				<td>
				<input type="checkbox" name="chkBoxSelectedIds" value='<s:property value="rollNumber"/>' 
								class="checkbox" id="chkBoxClassIds" checked="checked"/>
				</td>
			</tr>
			
		</s:iterator>
		<tr>
			<td>
					<sj:submit   id="submitFormAutocomplete1" targets="eventsResults"
						cssClass="submit small" value="Submit" indicator="indicator"
						onClickTopics="registerStudentFormVal" formIds="registerStudentEvent1" />
			</td>
			<td style="padding-left: 0px;">
				<s:url id="viewStaffEventsUrl" action="ajaxStaffCancelEvent"
					includeParams="all" escapeAmp="false">
				</s:url>
				<sj:a href="%{viewStaffEventsUrl}" cssClass="cancelButton"
					indicator="indicator" targets="eventsResults" button="false"
					buttonIcon="ui-icon-plus">Cancel</sj:a>
			</td>
		</tr>
		<tr>
		</tr>
	</table>
</s:if>
</s:form>
<s:else>
	<div style="padding:0px 0px 20px 30px">
		currently there are no students.
	</div>
</s:else>
