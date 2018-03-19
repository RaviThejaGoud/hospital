<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{packageDetails == null || packageDetails == ''}">
	<div id="steps">
		<s:form id="addPackageDetails" action="ajaxAddPackgeDetails"
			method="post" theme="simple" cssClass="form-horizontal" namespace="/masterAdmin">
			<div class="form-body">
				<h4 class="pageTitle bold form-section">
					Create Package
				</h4>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-5">
								<span class="required">*</span>Student Range :
							</label>
							<div class="col-md-7">
								<sj:textfield name="packageDetails.studentsRange" id="author"
									cssClass="required form-control input-medium" maxlength="10"
									onkeypress="return onlyNumbers(event);" />
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4">
								Cost Per Student :
							</label>
							<div class="col-md-8">
								<sj:textfield name="packageDetails.costPerStudent"
									id="publisher" onkeypress="return onlyNumbers(event);"
									cssClass="form-control input-medium"
									maxlength="10" />
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-5">
								<span class="required">*</span>Max Allowable Students :
							</label>
							<div class="col-md-7">
								<sj:textfield name="packageDetails.maxAllowableStudents"
									id="cost" cssClass="required form-control input-medium"
									maxlength="4" onkeypress="return onlyNumbers(event);"
									/>
							</div>
						</div>
					</div>
				</div>
				<div class="form-actions fluid">
					<div class="col-md-12">
						<div class="col-md-offset-3 col-md-6">
							<sj:submit   cssClass="submitBt btn blue"
								formIds="addPackageDetails" value="Submit" indicator="indicator"
								targets="packageDetailsDiv"
								onBeforeTopics="formValidationForAddPackage" />
							<a id="masterAdminPackages" href="${pageContext.request.contextPath}/masterAdmin/masterAdminHome.do?id=masterAdminPackages" 
								class="btn default"><span class="title">Cancel</span> 
						</a>
						</div>
					</div>
				</div>
			</div>
		</s:form>
	</div>
</s:if>
<s:else>
	<s:form id="editPackageDetails" action="ajaxEditPackgeDetails"
		method="post" theme="simple" cssClass="form-horizontal" namespace="/masterAdmin">
		<s:hidden name="id" value="%{packageDetails.id}"></s:hidden>
		<div class="form-body">
			<h4 class="pageTitle bold form-section">
				Update Package
			</h4>

			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-5">
							<span class="required">*</span>Student Range :
						</label>
						<div class="col-md-7">
							<sj:textfield name="packageDetails.studentsRange" id="author"
								cssClass="required form-control input-medium" maxlength="10"
								onkeypress="return onlyNumbers(event);" />
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							Cost Per Student :
						</label>
						<div class="col-md-8">
							<sj:textfield name="packageDetails.costPerStudent" id="publisher"
								onkeypress="return onlyNumbers(event);" 
								cssClass="form-control input-medium" maxlength="10" />
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-5">
							<span class="required">*</span>Max Allowable Students :
						</label>
						<div class="col-md-7">
							<sj:textfield name="packageDetails.maxAllowableStudents"
								id="cost" cssClass="required form-control input-medium"
								maxlength="4" onkeypress="return onlyNumbers(event);"
								/>
						</div>
					</div>
				</div>
			</div>
			<div class="form-actions fluid">
				<div class="col-md-offset-3 col-md-6">
					<sj:submit   cssClass="submitBt btn blue"
						formIds="editPackageDetails" value="Submit" indicator="indicator"
						targets="packageDetailsDiv"
						onBeforeTopics="formValidationForEditPackage" />
					<a id="masterAdminPackages"
						href="${pageContext.request.contextPath}/masterAdmin/masterAdminHome.do?id=masterAdminPackages"
						class="btn default"><span class="title">Cancel</span> </a>

				</div>
			</div>
		</div>
	</s:form>
</s:else>
<script type="text/javascript">
changePageTitle("Add Package");
$(document).ready(function() {
	$.subscribe('formValidationForAddPackage', function(event, data) {
		if ($('#addPackageDetails').valid()){
		}
		else
		event.originalEvent.options.submit=false; 	
	});
	$.subscribe('formValidationForEditPackage', function(event, data) {
		if ($('#editPackageDetails').valid()){
		}
		else
		event.originalEvent.options.submit=false; 
	});
});
</script>
