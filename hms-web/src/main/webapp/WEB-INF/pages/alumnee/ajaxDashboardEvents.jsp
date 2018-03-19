<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		 <div class="portlet-body">
			<div class="tabbable-custom ">
				<ul class="nav nav-tabs ">
					<li class="active" style="float: left;"><a href="#tab_5_1" data-toggle="tab">UpComing Events</a></li>
				</ul>
				<div id="eventsViewDiv" class="tab-content">
					 <jsp:include page="/WEB-INF/pages/alumnee/viewEvents.jsp" />
				</div>
			</div>
	</div>
</div>
</div> 