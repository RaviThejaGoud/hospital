<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>View Account Masters
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<s:if test='%{#session.previousYear == "N"}'>
							<li>
								<s:url id="doAddCategories" action="ajaxDoAddCategories" namespace="/account"/>
								<sj:a href="%{doAddCategories}" targets="accountMasterContentDiv" indicator="indicator" data-toggle="tab">Add Account Categories</sj:a>
							</li>
							<li>
								<s:url id="addAccountMaster" action="ajaxDoAddCreateAccountMaster" namespace="/account"/>
								<sj:a href="%{addAccountMaster}" targets="accountMasterContentDiv" indicator="indicator" data-toggle="tab">Add Account Master</sj:a>
							</li>
						</s:if>
						<li class="active">
							<s:url id="viewMaster" action="ajaxViewCreateAccountMaster"	 namespace="/account">
								<s:param name="financialAccountCategoryVO.id" value="0" />
							 </s:url>
							<sj:a href="%{viewMaster}" targets="mainContentDiv" data-toggle="tab">View Account Masters</sj:a>
						</li>
					</ul>
					<div id="accountMasterContentDiv" class="tab-content">
						<jsp:include page="/WEB-INF/pages/account/ajaxViewMasterAccountsDetails.jsp" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		changePageTitle("Account Master.");
	});
	$('html, body').animate({ scrollTop: $(document).height() - $(window).height() }, 10, function() {
	    $(this).animate({ scrollTop: 0 }, 10);
	});
</script>
