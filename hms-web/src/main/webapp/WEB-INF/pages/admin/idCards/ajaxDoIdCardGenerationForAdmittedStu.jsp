<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>ID Cards Generation
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<div id="subjectsContentDiv" class="tab-content">
						<%@ include file="/common/messages.jsp"%>
						<jsp:include	page="/WEB-INF/pages/admin/idCards/ajaxViewIdCardSelectionsForAdmittedStu.jsp"></jsp:include>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
