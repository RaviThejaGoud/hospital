<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{studySubjectList != null && !studySubjectList.isEmpty()}">
	<span class="label label-danger"> NOTE : </span>&nbsp;
	 You can see all classes subjects in this page.
	 <div class="spaceDiv"></div>
	<table class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
		<thead>
			<tr>

				<th style="display: none;">
				</th>
				<th>
					Subject Name
				</th>
				<th>
					Subject Code
				</th>
				<th>
					Edit
				</th>
				<th>
					Delete
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="studySubjectList">
				<tr>
					<td style="display: none;">
						<s:property value="sortingOrder" />
					</td>
					<td>
						<s:property value="name" />
					</td>
					<td>
						<s:property value="subjectNumber" />
					</td>
					<td>
						<s:if test='%{#session.previousYear == "N"}'>
							<a data-toggle="modal" href="#responsive2"
								class="btn btn-xs purple"
								onclick="javascript:PopupSchoolTimingsDetials(<s:property value="%{id}" />);"><i
								class="fa fa-edit"></i>Edit </a>
						</s:if>
					</td>
					<td>
						<s:if test='%{#session.previousYear == "N"}'>
							<s:url id="removeStudySubject" action="ajaxRemoveSubject"
								includeParams="all" escapeAmp="false" namespace="/admin">
								<s:param name="studySubject.id" value="%{id}" />
							</s:url>
							<s:div cssClass="btn btn-xs red"
								onclick="javascript:confirmDialogWithTarget(this,'mainContentDiv');"
								id='%{removeStudySubject}' title="Delete this subject">
								<i class="fa fa-times"></i>Delete
							</s:div>
						</s:if>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no subjects. Please <a href="#" onclick="javascript:addSubjects()"><font color="red">Click here</font></a> to add a subject.
	</div>
</s:else>
<div id="responsive2"></div>
<script type="text/javascript">
TableAdvanced.init();
UIExtendedModals.init();
function formateSubjectNumber(event) {
	var subjectNum = $(event).val();
	if (isNonEmpty(subjectNum)) {
		if (subjectNum.length < 3) {
			for ( var i = 0; i < (4 - subjectNum.length); i++) {
				subjectNum = '0' + subjectNum;
			}
		}
		$(event).val(subjectNum);
	}
}
function PopupSchoolTimingsDetials(id) {
	var url = jQuery.url.getChatURL("/admin/ajaxDoAddSubject.do");
	$('#responsive2')
			.html(
					'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
		url : url,
		cache : false,
		data : "studySubject.id=" + id,
		success : function(html) {
			$("#responsive2").html(html);
		}
	});
}
function addSubjects() {
	$('a#addSub').click();
}

</script>
