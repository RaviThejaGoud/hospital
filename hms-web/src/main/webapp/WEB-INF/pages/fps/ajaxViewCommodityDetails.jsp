<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Transactional Details
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<li>
							<s:url id="urlVillageCommodity" action="ajaxGetDistrictListByVillage" namespace="/fps"/>
							<sj:a href="%{urlVillageCommodity}"
								targets="commodityDiv" indicator="indicator" data-toggle="tab">Shop wise</sj:a>
						</li>
						<li>
							<s:url id="urlMandalCommodity" action="ajaxGetDistrictList" namespace="/fps"/>
							<sj:a href="%{urlMandalCommodity}"
								targets="commodityDiv" indicator="indicator" data-toggle="tab">Mandal wise</sj:a>
						</li>
						<li class="active">
							<s:url id="urlDistrictCommodity" action="ajaxGetMonths" namespace="/fps"/>
							<sj:a href="%{urlDistrictCommodity}"
								targets="mainContentDiv" indicator="indicator" data-toggle="tab">District wise</sj:a>
						</li>
					</ul>
					<div id="commodityDiv" class="tab-content">
						<jsp:include page="/WEB-INF/pages/fps/ajaxDistrictCommodityMonths.jsp"></jsp:include>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		changePageTitle("Transactional Details");
	});
</script>
