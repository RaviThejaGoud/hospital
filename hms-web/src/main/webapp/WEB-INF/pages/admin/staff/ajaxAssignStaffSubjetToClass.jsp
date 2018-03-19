<%@ include file="/common/taglibs.jsp"%>
<div id="stepStaffClasses">
	<div data-width="760" class="modal fade modal-overflow in"
		style="display: block; width: 760px; margin-left: -379px; margin-top: 150px;">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true"></button>
			<h4 class="modal-title">
				Assign Staff Classes
			</h4>
		</div>
		<div class="modal-body">
			<div class="form-body">
				<s:if test="%{studyClassList != null && !studyClassList.isEmpty()}">
					<s:form id="doAddTeacherSubjectToClass"
						action="ajaxAddStaffSubjectToClass" theme="simple" method="post"
						cssClass="form-horizontal" namespace="/admin">
						<s:hidden name="subjectId" />
						<s:hidden name="tempId" />
						<s:hidden name="anyId" />
						<div class="form-group ">
							<label class="control-label col-md-2">
								Select Class :
							</label>
							<div class="col-md-3">
								<s:select list="studyClassList" listKey="id"
									listValue="classAndSection" theme="simple"
									cssClass="required form-control" name="studyClassId"
									headerKey="" headerValue="- Select -">
								</s:select>
							</div>
						</div>
						<div class="form-actions fluid">
							<div class="col-md-offset-2 col-md-9">
								<img src="../img/bg/bigWaiting.gif" alt="Loading..."
									title="Loading..." id="indicator"
									style="display: none; background-repeat: no-repeat; position: relative; z-index: 1000; bottom: 200px; margin-left: 240px;" />
								<sj:submit cssClass="submitBt btn blue" value="Add"
									targets="staffEditContentDiv"
									formIds="doAddTeacherSubjectToClass"
									onBeforeTopics="doAddTeacherSubjectToClassValidation" />
								<button type="button" data-dismiss="modal" class="btn default">
									Cancel
								</button>
							</div>
						</div>
					</s:form>
				</s:if>
				<s:else>
					<div class="alert alert-info">
						Currently there are no classes with this subject.
					</div>
				</s:else>
			</div>
		</div>
	</div>
</div>
<script language="JavaScript" type="text/javascript">
function onClassChange(classSectionId) {
	var subjectId = document.getElementById("subjectId");
	var staffId = document.getElementById("tempId");
	var pars = "classId=" + classSectionId;
	pars = pars + "&subjectId=" + '<s:property value="subjectId"/>';
	pars = pars + "&staffId=" + '<s:property value="tempId"/>';
	$.ajax( {
		url : "ajaxCheckTeacherSubject.do",
		cache : true,
		data : pars,
		success : function(response) {
			$('#examTypesContent').html(response);
		}
	});
}
$(document).ready(function() {
	FormComponents.init();
	$.subscribe('doAddTeacherSubjectToClassValidation', function(event, data) {
		if ($('#doAddTeacherSubjectToClass').valid()) {
			$('button.close').click();
			return true;
		} else
			event.originalEvent.options.submit = false;
	});
});
</script>