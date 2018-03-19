<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i> Fee Concessions
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<jsp:include page="/common/messages.jsp" />
					<div class="row">
						<div class="col-md-12" id="manuvalDiv">
							<div class="form-group">
								<label class="control-label col-md-4" id="labelForStudentStaff">
									<span class="required">*</span>Search Student Name / Admission Number :
								</label>
								<div class="col-md-3">
									<input type="hidden" id="select2_sample10" class="form-control select10 required">
								</div>
							</div>
						</div>
					</div>
					<br/>
					<div id="viewStudentFeeDetails"></div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	FormComponents.init();
	changePageTitle("Student Fee Concession");
});
</script>