<%@ include file="/common/taglibs.jsp"%>
<div class="row">
<span class='navSelectionSpan' id='<s:property value="description"/>'></span>
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Admitted Students
				</div>
			</div>
			<div class="portlet-body">
			
				<div id="admittedStudentDiv1"  class="tab-content">
					<s:if test="%{academicYearList != null && !academicYearList.isEmpty}">
						<jsp:include page="/WEB-INF/pages/admin/admission/ajaxViewStudentsAcademicSearch.jsp"></jsp:include>
					</s:if>
					<s:else>
						<div class="alert alert-info">
							Currently there are no admission settings created to any academic
							year.
							<s:url id="admissionSettingsCreat" namespace="/admin"
								action="ajaxAdmissionSettingsHome">
								<s:param name="description">createSettings</s:param>
							</s:url>
							<sj:a id="createAdmissionSettings"
								href="%{admissionSettingsCreat}" targets="mainContentDiv"
								data-toggle="tab"><b>Click here</b></sj:a>
							to add Admission Settings
						</div>
					</s:else>
				</div>
			</div>
			<form method="post" id="printReport"
			action="javaScript:printPreview('<s:property value="studentNumber" />','<s:property value="todayDate"/>','<s:property value="quizId" />','<s:property value="empId" />','payandprint','<s:property value="academicYearId" />','<s:property value="alertSendType"/>')"
			style="display: none;">
		</form>
		</div>
	</div>
</div> 
<script type="text/javascript">
changePageTitle('Admitted Students');
</script>
