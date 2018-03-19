<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"> </script>
<div id="academicsContent">
	<s:if test="%{studyClassList != null && !studyClassList.isEmpty()}">
		<table class="striped" width="100%" cellpadding="1" cellspacing="1">
			<thead>
				<tr>
					<th width="20%" class="head">
						Class Name
					</th>
					<th class="head">
						No of students
					</th>
					<!--<th class="head">
						Class Subjects
					</th>
					--><th width="10%" class="head">
						Edit
					</th>
				</tr>
			</thead>
			<s:iterator value="studyClassList">
				<tr class="loaded">
					<td width="20%" >
						<s:url id="removeClass" action="ajaxRemoveClass" escapeAmp="false">
							<s:param name="id" value="id"></s:param>
						</s:url>
						<s:div cssStyle="margin-top:-1px;" cssClass="close emsRemove"
							id='%{removeClass}' theme="simple" title="Delete this Class"></s:div>

						<s:property value="className" />
						-
						<s:property value="section" />
					</td>
					<td width="20%">
						<s:property value="noOfStudents" />
					</td>
					<!--<td style="vertical-align: top;">
						<s:property value="subjectsString" />
					</td>-->
					<td>
						<s:url id="doEditClassSubjects" action="ajaxDoEditClassSubjects"
							includeParams="all" escapeAmp="false">
							<s:param name="classSubjectId" value="id" />
						</s:url>
						<sj:a href="%{doEditClassSubjects}"
							targets="editClassSubjects%{id}" onBeforeTopics="cleanOpenRows"
							onCompleteTopics="doInitClassDetails" indicator="indicator"
							button="false" buttonIcon="ui-icon-plus"  cssClass="normalEdit" title="Edit">
						</sj:a>
					</td>
				</tr>
				<tr id="editClassSubjects<s:property value="id"/>" class="load"></tr>
			</s:iterator>
		</table>
	</s:if>
	<s:else>
	Currently there are no Study classes.
</s:else>
</div>
<script type="text/javascript">
	$.subscribe('doInitClassDetails', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});
</script>
