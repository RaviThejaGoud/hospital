<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>
					<span class="hidden-481"> </span>
				</div>
			</div>
			<div class="portlet-body">
				<div class="tab-content">
					<jsp:include
						page="/WEB-INF/pages/admin/idCards/ajaxViewIdCardSelections.jsp"></jsp:include>
				</div>
			</div>
		</div>
	</div>
</div>

