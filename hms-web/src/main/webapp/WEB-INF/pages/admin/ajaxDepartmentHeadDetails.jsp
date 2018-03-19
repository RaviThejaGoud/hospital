<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Department Details
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<li>
						<s:url id="doCreateDepartment"	action="ajaxCreateDepartment" escapeAmp="false"	namespace="/admin" />
							<sj:a id="createDepartment" href="%{doCreateDepartment}" targets="contentDiv" data-toggle="tab"> 
								Create Department </sj:a>
						</li>
						<li  class="active">
							<s:url id="viewDepartmentHead" action="ajaxViewDepartmentHead" namespace="/admin" />
							<sj:a href="%{viewDepartmentHead}" targets="mainContentDiv" >
								View
							</sj:a>
						</li>
					</ul>
					<div class="tab-content" id="contentDiv">
						<jsp:include page="/WEB-INF/pages/admin/ajaxViewDepartmentHeadList.jsp" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Create Department")
});
</script>