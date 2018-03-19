<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i> Student Fee Refund
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
							<li class="active" id="viewFeeRefundDetails">
								<s:url id="viewFeeRefundDetails" action="ajaxViewFeeRefundDetails" namespace="/schoolfee" >
									<s:param name="academicYearId"><s:property value="tempId"/></s:param>
								</s:url>
									<sj:a href="%{viewFeeRefundDetails}"
										targets="searchStudentsForm112" data-toggle="tab">View Refunds <s:property value="tempString"/></sj:a>
							</li>
						<%-- <li class="active" id="searchStudentsForRefund">
							<s:url id="searchStudentsForRefund" action="ajaxSearchStudentsForRefund" namespace="/schoolfee" />
								<sj:a href="%{searchStudentsForRefund}"
									targets="searchStudentsForm112" data-toggle="tab">Fee Refund</sj:a>
						</li> --%>
					</ul>
					<div class="tab-content" id="searchStudentsForm112">
					<%-- <jsp:include page="/WEB-INF/pages/schoolfee/searchStudentDetailsForRefund.jsp" /> --%>
							<jsp:include page="/WEB-INF/pages/schoolfee/ajaxViewStudentFeeRefundDetails.jsp" />
							
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	//$('#financeHome').addClass('current');
	$(document).ready(function() {
		changePageTitle("Student Fee Refund");
	});
	$('html, body').animate({ scrollTop: $(document).height() - $(window).height() }, 10, function() {
	    $(this).animate({ scrollTop: 0 }, 10);
	});
</script>