<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Student Payments
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						 <li>
							<s:url id="paymentReceipts" action="ajaxDoPaymentRecepts" namespace="/student"/>
							<sj:a id="addRack" href="%{paymentReceipts}" 
								targets="parentPaymentReceipts"  data-toggle="tab">Payment Receipts</sj:a>
						</li>
						 <li class="active">
							<s:url id="ParentMakePayment" action="ajaxParentMakePaymentFee" namespace="/student">
							</s:url>
							<sj:a id="ParentMakePayment" href="%{ParentMakePayment}" targets="parentPaymentReceipts" data-toggle="tab">View Payment</sj:a> 	
						 </li>
					</ul>
					<div id="parentPaymentReceipts" class="tab-content">
						<jsp:include
						page="/WEB-INF/pages/student/parent/payment/ajaxStudentFeePaymentDetails.jsp" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
