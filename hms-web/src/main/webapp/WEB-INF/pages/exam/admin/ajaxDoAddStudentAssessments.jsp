<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{studentsAssessments.id != 0}">
<div data-width="760" class="modal fade modal-overflow in" style="display: block; width: 900px; margin-left: -490px; margin-top: 120px;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
		<h4 class="modal-title">Update assessment</h4>
	</div>
	<div class="modal-body">
</s:if>
<div class="form-body">
<s:form action="ajaxAddStudentAssessment" method="post" theme="simple"
	id="addAssessment" cssClass="form-horizontal" namespace="/exam">
	<s:hidden name="studentsAssessments.id" value="%{studentsAssessments.id}"></s:hidden>
	<s:if test="%{studentsAssessments == null}">
		<h4 class="modal-title">Add assessment</h4>
	<hr/>
	</s:if>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5">
						<span class="required">*</span> Assessment Name :
					</label>
					<div class="col-md-4">
						<sj:textfield name="studentsAssessments.assessmentName"
							id="assessmentName" cssClass="required form-control input-medium">
						</sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5">
						Description :
					</label>
					<div class="col-md-4">
						<sj:textarea rows="4" cols="30"
							name="studentsAssessments.assessmentDescription"
							cssClass="textSmall form-control input-medium"></sj:textarea>
					</div>
				</div>
			</div>
		</div>
		<div class="form-actions fluid">
			<div class="col-md-6">
				<div class="col-md-offset-5 col-md-6">
						<sj:submit   cssClass="submitBt btn blue" value="Submit" 
							onBeforeTopics="addAssessmnetFormValidation" 
							targets="studentsActivitiesContent" formIds="addAssessment" validate="true" />
						<s:if test="%{studentsAssessments.id != 0}">
								<button type="button" data-dismiss="modal" class="btn default">Cancel</button>
						</s:if>
						<s:else>
							<s:url id="doViewAssessments" action="ajaxManageStudentAssessments" namespace="/exam">
							</s:url>
							<sj:a href="%{doViewAssessments}" cssClass="btn default" targets="studentsActivitiesContent">Cancel</sj:a>
					</s:else> 
				</div>
			</div>
		</div>
</s:form>
</div>
<s:if test="%{studentsAssessments.id != 0}">
	</div>
	</div>
</s:if>
 
<script type="text/javascript">
$(document).ready(function() {
	var stuId = '<s:property value="studentsAssessments.id"/>';
	if(stuId==0){
		$('li#manageActive').addClass('active');
	}
});
$.subscribe('addAssessmnetFormValidation', function(event, data) {
		if ($('#addAssessment').valid()){
			$('button.close').click();
			$('ul.nav-tabs li').removeClass('active');
			$('li#manageActive').addClass('active');
			return true;
		}
		else
			return false;
	});
</script>