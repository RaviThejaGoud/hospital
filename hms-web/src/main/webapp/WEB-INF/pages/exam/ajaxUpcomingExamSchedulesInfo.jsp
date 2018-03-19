<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{examScheduleList != null && !examScheduleList.isEmpty()}">
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th>
					Class &amp; Exam Type
				</th>
				<th>
					Start Date
				</th>
				<th>
					End Date
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="examScheduleList">
				<tr>
					<td>
						<s:property value="classAndSection" />
						:
						<s:property value="examTypeName" />
					</td>
					<td>
						<s:property value="startDateStr" />
					</td>
					<td>
						<s:property value="endDateStr" />
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<div id="moreDetailsDiv">
		<s:if test='%{user.isSchoolStudent == "Y" || user.isParent == "Y"}'>
		<s:url id="urlMoreDetailsLinks" action="ajaxManageStudentExamSchedulesAndResults" 
				includeParams="all" namespace="/exam"/>
			<sj:a href="%{urlMoreDetailsLinks}" targets="mainContentDiv" 
				indicator="indicator" id="moreDetailIds" cssClass="btn green btn-xs">More Details</sj:a>
		</s:if>
		<s:else>
			<div id="moreDetailsDiv">
			<s:url id="urlMoreDetailsLinks" action="ajaxStaffExamSchedules" 
				includeParams="all" namespace="/exam"/>
			<sj:a href="%{urlMoreDetailsLinks}" targets="mainContentDiv" 
				indicator="indicator" id="moreDetailIds" cssClass="btn green btn-xs">More Details</sj:a>
			</div>
		</s:else>
	</div>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no upcoming examschedules.
	</div>
</s:else>
<script type="text/javascript">
$('a#urlMoreDetailsLinks').click(function(){
	window.location.hash="target=staffMyClass.ajaxify staffMyClass";
	window.location.reload();
});

</script>
