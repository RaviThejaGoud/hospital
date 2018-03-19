<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />
<div class="form-body">
		<s:form id="changeAcademicYearStatus" action="ajaxChangeAcademicYearStatus"
			method="post" theme="simple" cssClass="form-horizontal" namespace="/admin">
			<h4 class="pageTitle bold">
				 Switch Future  Academicyear
			</h4>
				<div class="form-group">
					<label class="control-label col-md-4"> Future  Academic Start Date : </label>
					<div class="col-md-8">
						<div class="input-group input-medium date date-picker"  data-date-start-date="+0d">
							<input type="text" readonly="readonly" class="form-control required" value='<s:property value="academicYearVo.academicStartDateStr"/>'
								id="date0" name="academicYearVo.academicStartDate" onchange="verifyDate();">
							<span class="input-group-btn">
								<button type="button" class="btn default">
									<i class="fa fa-calendar"></i>
								</button>
							</span>
						</div>
						<span class="help-block">(MM/DD/YYYY)</span>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-4"> Future  Academic End Date : </label>
					<div class="col-md-8">
						<div class="input-group input-medium date date-picker"
							data-date-start-date="0d">
							<input type="text" readonly="readonly" class="form-control required"
								id="date1" name="academicYearVo.academicEndDate" onchange="verifyEndDate();">
							<span class="input-group-btn">
								<button type="button" class="btn default">
									<i class="fa fa-calendar"></i>
								</button>
							</span>
						</div>
						<span class="help-block">(MM/DD/YYYY)</span>
					</div>
				</div>
			<div class="form-actions fluid">
				<div class="col-md-offset-3 col-md-9">
					<sj:submit value="Submit" cssClass="submitBt btn blue"
						onBeforeTopics="addLatePermissionsForm" onCompleteTopics="addLatePermissionsForms"
						formIds="changeAcademicYearStatus" targets="mainContentDiv"
						indicator="indicator" validate="true" />
					<s:url id="urlSchoolSettings" action="ajaxAcademicSchoolSettings"
						namespace="/admin">
						<s:param value='<s:property value="#session.academicYear" />'
							name="academicYearId">
						</s:param>
					</s:url>
					<sj:a href="%{urlSchoolSettings}" targets="mainContentDiv"
						cssClass="btn default">
							Cancel</sj:a>
				</div>
			</div>
		</s:form>
	
</div>
<script type="text/javascript">
	$(document).ready(function() {
		FormAdvanced.init();
		$("input:checkbox, input:radio:not('.toggle')").uniform();
		changePageTitle("Switch Feature Academicyear");
		FormComponents.init();
		UIExtendedModals.init();
		/* $.subscribe('addLatePermissionsForms', function(event, data) {
			window.location.reload();
		}); */
	});
	
	function verifyDate() {
		var date0 = $('#date0').val();
		var academicEndDate = '<s:property value="academicYearVo.academicStartDateStr" />';
		if (isNonEmpty(academicEndDate) && isNonEmpty(date0)) {
			date0 = new Date(date0);
			academicEndDate = new Date(academicEndDate);
			//var birthYear = new Date(date0.setYear(date0.getFullYear() + 2));
			if ( date0 <=  academicEndDate) {
				$('#date0').val('<s:property value="academicYearVo.academicStartDateStr" />');
				alert("Feature Academic Start Date should be greater than the current academic end date.");
			}
			
		}
	}
	
	
	function verifyEndDate() {
		var startDate = $('#date0').val();
		var endDate = $('#date1').val();
		
		if (isNonEmpty(startDate) && isNonEmpty(endDate)) {
			startDate = new Date(startDate);
			endDate = new Date(endDate);
			//var birthYear = new Date(date0.setYear(date0.getFullYear() + 2));
			if (  endDate <=   startDate) {
				$('#date1').val('');
				alert("Future Academic year end date has to be greater than Future academic year start date.");
			}
		}
	}
	
</script>

