<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Manage School Shift Info
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<li>
							<s:url id="urlSchoolShift" action="ajaxDoManageSchoolShiftInfo" 
								includeParams="all" escapeAmp="false" namespace="/staff">
								<s:param name="schoolShiftInfo.id">0</s:param>
							</s:url>
							<sj:a href="%{urlSchoolShift}" indicator="indicator" data-toggle="tab"
								targets="schoolShiftInfoDiv" button="false">Create School Shift</sj:a>
						</li>
						<li class="active">
							<s:url id="viewSchoolShiftInfo" action="ajaxViewSchoolShiftInfo" namespace="/staff" />
							<sj:a href="%{viewSchoolShiftInfo}" targets="mainContentDiv"
								data-toggle="tab">View School Shift</sj:a>
						</li>
					</ul>
					<div class="tab-content" id="schoolShiftInfoDiv">
						<%@ include file="/common/messages.jsp"%>
						<jsp:include page="/WEB-INF/pages/staff/manageStaff/ajaxViewSchoolShiftList.jsp" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Manage School Shift Info");
});
</script>
 