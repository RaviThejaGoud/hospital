<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>View Photos
				</div>
			</div>
			<div class="portlet-body">
				 <div id="photosDiv" class="tab-content">
							<jsp:include page="/WEB-INF/pages/alumnee/ajaxViewEventPhotos.jsp"></jsp:include>
					</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("View Photos");
});
</script>
