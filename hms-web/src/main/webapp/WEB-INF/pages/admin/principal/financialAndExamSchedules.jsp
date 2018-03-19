<%@ include file="/common/taglibs.jsp"%>
<div class="row form-horizontal">
	<div class="form-group">
		<label class="control-label col-md-2">
			Select Class :
		</label>
		<div class="col-md-3">
			<s:select list="classList" id="classId" name="tempId1" listKey="id"
				listValue="className" headerKey="" cssClass="form-control"
				headerValue="- Select Class -" theme="simple"
				onchange="javascript:selectClass(this.value);">
			</s:select>
		</div>
	</div>
</div>
<div id="pendingPayments"></div>
<div>
	<s:url id="viewStudentPaymentDefaultesLink"
		action="ajaxViewPaymentDefaulters" namespace="/schoolfee"></s:url>
	<sj:a href="%{viewStudentPaymentDefaultesLink}"
		targets="staffLeaveApproval" cssClass="btn green btn-xs">More Details</sj:a>
</div>

<script  type="text/javascript">
$(document).ready(function() {
	var classId = $("#classId").val();
	if (isNonEmpty(classId))
		selectClass(classId);
});
function selectClass(classId) {
	$("#pendingPayments")
			.html(
					'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	var url = jQuery.url
			.getChatURL("/schoolfee/ajaxViewStudentPendingFeeList.do?tempId1="
					+ classId);
	$
			.ajax( {
				url : url,
				cache : false,
				dataType : 'json',
				success : function(response) {
					if (isNonEmpty(response)) {
						if (isNonEmpty(response.series)) {
							chart = new Highcharts.Chart(
									{
										chart : {
											renderTo : 'pendingPayments',
											defaultSeriesType : 'column'
										},

										title : {
											text : response.title
										},

										xAxis : {
											categories : response.categories,
											labels : {
												rotation : -65,
												align : 'right',
												style : {
													font : 'normal 13px Verdana, sans-serif'
												}
											}
										},

										yAxis : {
											allowDecimals : false,
											min : 0,
											title : {
												text : 'Paid And Not Paid Amount'
											}
										},

										tooltip : {
											formatter : function() {
												return '<b>' + this.x
														+ '</b><br/>'
														+ this.series.name
														+ ': ' + this.y
														+ '<br/>' + 'Total: '
														+ this.point.stackTotal;
											}
										},

										plotOptions : {
											column : {
												stacking : 'normal'
											}
										},

										series : response.series
									});
						} else {
							$('.classPendingAvg').hide();
							$('#pendingPayments')
									.html(
											'<div class="alert alert-info">No data found.</div>');
						}
					} else {
						$('.classPendingAvg').hide();
						$('#pendingPayments')
								.html(
										'<div class="alert alert-info">No data found.</div>');
					}
				}
			});
}
</script>