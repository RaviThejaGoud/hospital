<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<s:if test="%{objectList != null && !objectList.isEmpty()}">
	<table class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
		<thead>
			<tr>
				<th>
					Date Of Purchase
				</th>
				<th>
					No.Of SMS
				</th>
				<th>
					Cost Per SMS(Paise)
				</th>
				<th>
					Total Amount
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="objectList">
				<tr>
					<td>
						<s:property value="paidDateStr" />
					</td>
					<td>
						<s:property value="paidSms" />
					</td>
					<td>
						<%-- <s:property value="paidSmsAmount/paidSms" /> --%>
						<%-- <fmt:formatNumber maxFractionDigits="2" pattern="#Paise"><s:property value="(paidSmsAmount)/(paidSms)*100" /></fmt:formatNumber> --%>
						0.14
						
					</td>
					<td>
						<s:property value="paidSmsAmount" />
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">Currently there are no buy sms history.</div>
</s:else>
<script type="text/javascript">
$(document).ready(function() {
	TableAdvanced.init();
	
	$("li.start").removeClass("active");
	$("li#smsDiv").addClass("open active");
	
	$("#buySmsLiId").addClass("active");
	
	//$("li#smsDiv").addClass("open active");
	
});
</script>