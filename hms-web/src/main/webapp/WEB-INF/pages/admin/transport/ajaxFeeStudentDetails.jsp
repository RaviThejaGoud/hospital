<%@ include file="/common/taglibs.jsp"%>
<div class="block grid_14">
	<div class="block_head" >
		<h2>
			Transport Fee
		</h2>
		<ul>
			<li>
				<s:url id="doAddTripFee" action="ajaxDoAddTripFee"
					includeParams="all" escapeAmp="false">
				</s:url>
				<sj:a href="%{doAddTripFee}" onCompleteTopics="doInitEditTripFee" onClickTopics="doAddMoreTransportFee"
					indicator="indicator" targets="addTransportFee%{id}" 
					buttonIcon="ui-icon-plus">
						+ Fee 
				</sj:a>
			</li>
		</ul>
	</div>
	<div class="block_content" id="transportContentFee">
		<jsp:include page="/WEB-INF/pages/admin/transport/ajaxManageTransportFee.jsp"/>
	</div>
</div>
<script type="text/javascript">
changePageTitle('Manage Transport Fee');
 $.subscribe('doAddMoreTransportFee', function(event, data) {
		 $('#moreTransportFee').hide();
	});
</script>