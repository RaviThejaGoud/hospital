<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<s:if
	test="%{classStudentsList != null && !classStudentsList.isEmpty()}">
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th>
					Student Image
				</th>
				<th>
					Student No
				</th>
				<th>
					Student Name
				</th>
				<th>
					Date Of Birth
				</th>
				<!--<th>
					Parent Mobile
				</th>
			--></tr>
		</thead>
		<tbody>
			<s:iterator value="classStudentsList">
				<tr>
					<td>
						<img src='<c:url value="${adjustedAttachmentFilePath}"/>'
							alt='<s:property  value="viewStudentPersonAccountDetails.personFullName" />'
							align="left" height="25px" width="50px" border="0" />
					</td>
					<td>
						<s:property value="rollNumber" />
					</td>
					<td>
						<a data-toggle="modal" href="#viewStudentDetials"
							onclick="javascript:PopupViewStudentDetials(<s:property value="accountId" />,'<s:property value="classAndSection" />');"><s:property
								value="firstName" />&nbsp;<s:property value="lastName" /> </a>

					</td>
					<td>
						<s:property value="dateOfBirthStr" />
					</td>
					<!--<td>
						<s:property value="mobileNumber" />
					</td>
				--></tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no Students.
	</div>
</s:else>
<div id="viewStudentDetials"></div>
<script type="text/javascript">
TableAdvanced.init();
function PopupViewStudentDetials(id, classAndSection) {
	var url = jQuery.url.getChatURL("/student/ajaxDoViewStudentDetails.do");
	$('#viewStudentDetials')
			.html(
					'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
		url : url,
		cache : false,
		data : "id=" + id + "&classAndSection=" + classAndSection,
		success : function(html) {
			$("#viewStudentDetials").html(html);
		}
	});
}
</script>

