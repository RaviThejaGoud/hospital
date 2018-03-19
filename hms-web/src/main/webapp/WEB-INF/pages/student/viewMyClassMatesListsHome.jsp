<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>My Class Mates
				</div>
			</div>
			<div class="portlet-body">
					 
					<div id="myClassMatesHome" class="tab-content">
						<jsp:include page="viewMyClassMatesLists.jsp"></jsp:include>
					</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Classmates");
});
</script>
 