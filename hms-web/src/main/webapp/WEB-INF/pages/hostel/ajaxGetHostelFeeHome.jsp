<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Manage Student
				</div>
			</div>
			<div class="portlet-body">
				<div id="searchStudentsForm112" class="tab-content">
					<jsp:include
						page="/WEB-INF/pages/hostel/ajaxSearchStudentDetails.jsp" />
				</div>
			</div>
		</div>
	</div>
</div>
