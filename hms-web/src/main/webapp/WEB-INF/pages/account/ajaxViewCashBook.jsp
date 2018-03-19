<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>View Cash Book
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<s:if test='%{#session.previousYear == "N"}'>
							<li>
								<s:url id="DoAddCashBook" action="ajaxDoAddCashBook" namespace="/account"/>
								<sj:a href="%{DoAddCashBook}" targets="CashContentDiv" indicator="indicator" data-toggle="tab">Add Voucher</sj:a>
							</li>
							<li>
								<s:url id="feeParticularToAccount" action="ajaxDoFeeParticularToAccount" namespace="/account"/>
								<sj:a href="%{feeParticularToAccount}" targets="CashContentDiv" indicator="indicator" data-toggle="tab">Associate Particular To Account</sj:a>
							</li>
						</s:if>
						<li class="active">
							<s:url id="viewMaster" action="ajaxViewCashBookDetails"	 namespace="/account"> </s:url>
							<sj:a href="%{viewMaster}" targets="mainContentDiv" data-toggle="tab">View Cash Book</sj:a>
						</li>
					</ul>
					<div id="CashContentDiv" class="tab-content">
						<jsp:include page="/WEB-INF/pages/account/ajaxViewCashBookDetails.jsp" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		changePageTitle("Cash Book");
	});
	$('html, body').animate({ scrollTop: $(document).height() - $(window).height() }, 10, function() {
	    $(this).animate({ scrollTop: 0 }, 10);
	});
</script>
