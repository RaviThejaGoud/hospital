<%@ include file="/common/taglibs.jsp"%>
 <div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
				<i class="fa fa-globe"></i>
					Manage Voucher
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<s:if test='%{#session.previousYear=="N"}'>
						<li>
							 <s:url id="urlVoucher" action="ajaxDoVoucher" namespace="/admin"   includeParams="all" escapeAmp="false">
								<s:param value="0" name="voucher.id" />
							 </s:url>
							<sj:a href="%{urlVoucher}" targets="voucherContentDiv" indicator="indicator" data-toggle="tab">Add Voucher</sj:a>
						</li>
						</s:if>
						<li class="active">
							<s:url id="viewBookResults" action="ajaxDoGetDayBookDetails" namespace="/admin"> </s:url>
						    <sj:a href="%{viewBookResults}" targets="mainContentDiv" data-toggle="tab"> View Vouchers</sj:a>
						</li>
					</ul>
					<div id="voucherContentDiv" class="tab-content">
						<jsp:include page="/WEB-INF/pages/admin/academic/voucher/ajaxViewVoucher.jsp"/>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>  
<script type="text/javascript">
$(document).ready(function() {
		changePageTitle("View Voucher");
		 $('.js-activated').dropdownHover().dropdown();
	});
$('html, body').animate({ scrollTop: $(document).height() - $(window).height() }, 10, function() {
    $(this).animate({ scrollTop: 0 }, 10);
});
</script>
 