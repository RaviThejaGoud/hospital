<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{objectList != null && !objectList.isEmpty()}">
	<s:if test="%{objectList.size > 1 }">
	<div class="row"  id="studentListContent">
			<div class="form-group form-horizontal">
				<label class="control-label col-md-2">
					<span class="required">*</span>Student Name :
				</label>
				<div class="col-md-3">
					<s:select id="sectionId" list="objectList" listKey="id"
						label="Student Name" listValue="studentNameAndUserName"
						name="tempId" theme="simple" cssClass="form-control"
						onchange="javascript:getStudentExamSchedules(this.value);" />
				</div>
			</div>
		</div>
	</s:if>
</s:if>
<div class="spaceDiv"></div>
<s:if
	test="%{(examScheduleList != null && !examScheduleList.isEmpty())}">
	<div class="row">
		<div class="col-md-6">
			<div class="form-group form-horizontal">
				<label class="control-label col-md-3">
					<strong> Exam Type : </strong>
				</label>
					<p class="form-control-static">
						<strong> <s:property value='examType' /> </strong>
					</p>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group form-horizontal">
				<label class="control-label col-md-3">
					Print :
				</label>
				<a
					href="${pageContext.request.contextPath}/reports/printExamSchedules.do?examType=<s:property value='tempId1'/>&classId=<s:property value='tempId2'/>&section=<s:property value='tempString'/>&anyTitle=<s:property value='examType'/>&custId=<s:property value='custId'/>"
					target="_new"><img alt="Print" title="Print"
						src="../images/common/printer.png"> </a>
			</div>
		</div>
	</div>
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th>
					Subject
				</th>
				<th>
					SubType
				</th>
				<th>
					Exam Date
				</th>
				<th>
					Start Time
				</th>
				<th>
					End Time
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="examScheduleList">
				<tr>
					<td>
						<s:property value="subjectName" />
					</td>
					<td>
						<s:property value="subTypeName" />
					</td>
					<td>
						<s:property value="examDateStr" />
					</td>
					<td>
						<s:property value="startTime" />
					</td>
					<td>
						<s:property value="endTime" />
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no upcoming exam schedules.
	</div>
</s:else>
<script type="text/javascript">
function getStudentExamSchedules(studentId) {
	if (isNonEmpty(studentId)) {
		$('#examsContentDiv')
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
			url : jQuery.url.getChatURL("/exam/ajaxStudentExamSchedules.do?tempId="+studentId),
			cache : true,
			success : function(response) {
				if (isNonEmpty(response)) {
					$('#examsContentDiv').html(response);
				}
			}
		});
	}
}
</script>