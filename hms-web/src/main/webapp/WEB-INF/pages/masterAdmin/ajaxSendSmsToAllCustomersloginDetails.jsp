<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i> Send Login Credentials to School
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<jsp:include page="/common/messages.jsp" />
					<div class="form-horizontal">
						<div class="" id="manuvalDiv">
							<div class="form-group">
								<label class="control-label col-md-3" id="labelForStudentStaff">
									<span class="required">*</span>Search School :
								</label>
								<div class="col-md-3">
									<input type="hidden" id="select2_sample11" class="form-control select11 required">
								</div>
							</div>
						</div>
						<div id="viewSchoolMemberDetails"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	FormComponents.init();
	changePageTitle("Send Login Credentials to School");
});
</script>