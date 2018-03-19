<%@ include file="/common/taglibs.jsp"%>
 <div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
				<i class="fa fa-globe"></i>
					Manage Ledger
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
							<s:if test='%{#session.previousYear=="N"}'>
							<li>
								<s:url id="urlLedger" action="ajaxDoLedgerVoucher" namespace="/admin"/>
								<sj:a href="%{urlLedger}"
								targets="ledgerContentDiv" indicator="indicator" data-toggle="tab">Add Ledger</sj:a>
							</li>
							</s:if>
							<li class="active"> 
							<s:url id="urlViewLedger" action="ajaxViewLedgerVoucher" namespace="/admin"/>
								<sj:a href="%{urlViewLedger}"
								targets="mainContentDiv" indicator="indicator" data-toggle="tab">View Ledger</sj:a>
							</li>
					</ul>
					<div id="ledgerContentDiv" class="tab-content">
						<jsp:include page="/WEB-INF/pages/admin/academic/voucher/ajaxViewLedgerVoucher.jsp"/>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>  
<script type="text/javascript">
$(document).ready(function() {
		changePageTitle("Manage Groups");
		 $('.js-activated').dropdownHover().dropdown();
	});
$('html, body').animate({ scrollTop: $(document).height() - $(window).height() }, 10, function() {
    $(this).animate({ scrollTop: 0 }, 10);
});

</script>
 