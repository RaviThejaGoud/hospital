<%@ include file="/common/taglibs.jsp"%>
<!--<div style="float:right">
    <s:url id="importMembersSheetForm" action="ajaxDoImportneighborhoodMembers"/>
    <sj:a href="%{importMembersSheetForm}" targets="neighbourhoodContent">Back</sj:a>
</div>
--><s:if test="%{studentMarksList == null || studentMarksList.isEmpty()}">
	<div class="grid_11">
		Currently there are no Failure Import Student Marks Details.
	</div>
</s:if>
<s:elseif test="%{studentMarksList != null && !studentMarksList.isEmpty()}">
<div class="grid_11" style="float: right;">
			<table class="striped" width="100%" style="margin-bottom: 0;"
			cellpadding="1" cellspacing="1" id="results">
				<thead>
					<tr>
						<th class="head" width="20%">
							Student Roll Number
						</th>
						<th>
						    Description
						</th>
						<th class="head" width="20%">
							Study Class								
						</th>
						<th class="head" width="25%">
							Subject								
						</th>
						<th width="1%">
							Edit								
						</th>
					</tr>
				</thead>
			</table>
			<div id="failureStudentMarks">
				<s:iterator value="studentMarksList">
					<table class="striped" width="100%" cellpadding="1"
					style="margin-bottom: 0;" cellspacing="1" id="results">
						<tr class="loaded">
							<td width="20%" class="head">
								<s:property value="student.rollNumber" />
							</td>
							<td>
								<s:property value="description" />
							</td>
							<td width="20%" class="head">
								<s:property value="examSchedule.classId.classAndSection" />
							</td>
							<td  width="25%" class="head">
								<s:property value="examSchedule.subjectId.name" />
							</td>
							<td width="1%">
								<s:url id="doEditStudentMarks" action="ajaxDoEditStudentMarks"
	                            includeParams="all" escapeAmp="false">
		                            <s:param name="studentChangesId" value="id" />
			                    </s:url>
			                    <sj:a href="%{doEditStudentMarks}" targets="doViewStudentMarksChanges%{id}"
			                            indicator="indicator" onCompleteTopics="doViewStudentMarksChanges" onBeforeTopics="cleanOpenRows"
			                            button="false" buttonIcon="ui-icon-plus" cssClass="normalEdit" title="Edit" >
			                    </sj:a>
							</td>
						</tr>
						<tr style="background-color: #ccc;width: 100%; display: none;" id="doViewStudentMarksChanges<s:property value='id'/>" class="load"></tr>
					</table>
				</s:iterator>
			</div>
		</div>
</s:elseif>
<script type="text/javascript">
	$(document).ready(function() {
	document.title = 'Import Student Marks Failure Details';
	$.subscribe('doViewStudentMarksChanges', function(event, data) {
				if ($('#' + data.id).is(":hidden")) {
					$('#' + data.id).show();
				} else {
					$('#' + data.id).hide();
				}
			});
});
</script>
<!--<style type="text/css">
.active {
	color: #0033CC;
	text-decoration: none;
}

.inactive {
	font-weight: bold;
	text-decoration: underline;
	cursor: default;
}

.paginator {
	text-align: center;
	color: #FFF;
}
</style>
--><!--<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/map/paginator_dev.js"></script>
<script type="text/javascript">
	$(document).ready( function() {
		$("#failureMembersList").pagination();
	});
</script>-->