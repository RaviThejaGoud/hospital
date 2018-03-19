<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div id="studentLibraryContent" class="tab-content">
			<h4 class="pageTitle bold form-section">Curricular / Co-Curricular Activities</h4>
			<s:if test="%{objectList != null && !objectList.isEmpty()}">
				<s:form id="addStudentCurriculamActivities" action="ajaxAddStudentCurricularActivities" theme="simple" cssClass="form-horizontal" namespace="/student">
					<s:hidden  value="%{tempId1}" name="tempId1" /><!-- Student Id-->
					<s:hidden value="%{tempId}" name="tempId" /><!-- Student Account Id-->
							
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-4">
									<span class="required">*</span> Activity Name :
								</label>
								<div class="col-md-5">
									<sj:textfield name="studentCurricularActivities.activityName" id="nationality"
										tabindex="1" cssClass="form-control required" maxlength="50"></sj:textfield>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group ">
								<label class="control-label col-md-4">
									<span class="required">*</span> Select Activity Type :
								</label>
								<div class="col-md-5">
									<s:select list="objectList" listKey="id" tabindex="2"
										listValue="activityTypeName" cssClass="form-control required"
										name="studentCurricularActivities.activityTypeId" headerKey="" headerValue="- Select -">
									</s:select>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-4">
									Description :
								</label>
								<div class="col-md-5">
									<sj:textarea rows="3" cols="20" name="studentCurricularActivities.description"
										maxCharsData="1000" tabindex="3"
										cssClass="form-control word_count"></sj:textarea>
									<span class="help-block">
										<div class="counter"></div> </span>
								</div>
							</div>
						</div>
					</div>
					<div class="form-body">
							<div class="form-group">
								<label class="control-label col-md-2">
									Upload Documents :
								</label>
								<div class="col-md-5">
									<s:file name="fileUpload" id="uploadDocs" multiple="multiple"
										tabindex="4" cssClass="btn default"></s:file>
								</div>
							</div>
							<div class="form-actions">
							<div class="col-md-offset-2 col-md-5">
								<img src="../img/bg/bigWaiting.gif" alt="Loading..." title="Loading..." id="indicator"
									style="display: none; background-repeat: no-repeat; position: relative; z-index: 1000; bottom: 200px; margin-left: 240px;" />
								<sj:submit cssClass="submitBt btn blue" value="Submit"
									targets="studentEditContentDiv" validate="true"
									 formIds="addStudentCurriculamActivities"
									tabindex="5" />
								<!--<s:url id="doCancelForm" action="ajaxViewStaffCurriculamActivities" namespace="/staff" />
								<sj:a href="%{doCancelForm}" targets="studentEditContentDiv" cssClass="btn default" tabindex="3">
									Cancel </sj:a>
							--></div>
							</div></div>
					</s:form>
					<hr/>
				<jsp:include page="/WEB-INF/pages/admin/student/ajaxViewCurricularActivitiesList.jsp"></jsp:include>
			</s:if>
			<s:else>
				<div class="alert alert-info">
					No Activity Types found, please contact Administrator.
				</div>
			</s:else>
		</div>
	</div>
</div>
<script type="text/javascript">
changePageTitle("Student Curricular Activities");
$(document).ready(function() {
FormComponents.init();
});
</script>