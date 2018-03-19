<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Curricular Activities
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<li class="active">
							<s:url id="viewStaffCurricularActivities" action="ajaxViewStaffCurricularActivities"
								namespace="/staff">
							</s:url>
							<sj:a href="%{viewStaffCurricularActivities}" targets="mainContentDiv"
								data-toggle="tab">Add And View</sj:a>
						</li>
					</ul>
					<div id="staffLibraryContent" class="tab-content">
						<%@ include file="/common/messages.jsp"%>
						<s:form id="addStaffCurriculamActivities" action="ajaxAddStaffCurricularActivities" theme="simple" cssClass="form-horizontal" namespace="/staff">
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label col-md-4">
											<span class="required">*</span> Activity Name :
										</label>
										<div class="col-md-5">
											<sj:textfield name="staffCurricularActivities.activityName" id="nationality"
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
												name="staffCurricularActivities.activityTypeId" headerKey="" headerValue="- Select -">
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
											<sj:textarea rows="3" cols="20" name="staffCurricularActivities.description"
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
										<label class="control-label col-md-3">
											Upload Documents :
										</label>
										<div class="col-md-5">
											<s:file name="fileUpload" id="uploadDocs" multiple="multiple"
												tabindex="4" cssClass="btn default"></s:file>
										</div>
									</div>
									<div class="form-actions">
									<div class="col-md-offset-3 col-md-6">
										<img src="../img/bg/bigWaiting.gif" alt="Loading..." title="Loading..." id="indicator"
											style="display: none; background-repeat: no-repeat; position: relative; z-index: 1000; bottom: 200px; margin-left: 240px;" />
										<sj:submit cssClass="submitBt btn blue" value="Submit"
											targets="mainContentDiv" validate="true"
											 formIds="addStaffCurriculamActivities"
											tabindex="5" />
										<!--<s:url id="doCancelForm" action="ajaxViewStaffCurriculamActivities" namespace="/staff" />
										<sj:a href="%{doCancelForm}" targets="mainContentDiv" cssClass="btn default" tabindex="3">
											Cancel </sj:a>
									--></div>
									</div></div>
							</s:form>
							<hr/>
						<jsp:include page="/WEB-INF/pages/staff/manageStaff/ajaxViewCurricularActivitiesList.jsp"></jsp:include>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
changePageTitle("Staff Curricular Activities");
$(document).ready(function() {
FormComponents.init();
});
</script>
