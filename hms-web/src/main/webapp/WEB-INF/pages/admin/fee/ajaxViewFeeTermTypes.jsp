<td colspan="6" style="background-color: #CCC;">
	<%@ include file="/common/taglibs.jsp"%>

	<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/common/jquery.alphanumeric.js">
</script>
	<s:if test="{classFeeList != null && classFeeList.isEmpty()}">
		<table width="100%" style="margin-bottom: 0;" cellpadding="1" cellspacing="1" id="results">
			<s:iterator value="classFeeList">
				<tr>
					<td width="20%">
						<s:property value="feeType" />
					</td>
					<td width="23%">
						<div id="chkBoxFeeSelectedIds<s:property value="paymentTypeId"/>">
							<s:property value="feeAmount" />
						</div>
					</td>
					<td width="18%">
						<s:property value="feeDueDateStr" />
					</td>
					<td width="13%">
						<s:property value="paymentDateStr" />
					</td>
					<td width="12%">
						<s:if test='%{paymentStatus == "P"}'>
								Paid 
							</s:if>
						<s:else>
							<input type="checkbox" name="chkBoxFeeSelectedIds"
								id="admisionPayStatus" value="<s:property value="paymentTypeId"/>" />
						</s:else>

					</td>
					<td>
						<s:if test='%{paymentType== "D"}'>
								 DD 
							 </s:if>
						<s:elseif test='%{paymentType== "C"}'>
					 			Cash 
							</s:elseif>
					</td>
				</tr>
			</s:iterator>
		</table>
	</s:if>
	<div class="grid_6" style="float: right;">
		<div class="grid_2">
			<label>
				Total Amount
			</label>
		</div>
		<div class="grid_4">
		<sj:textfield name="termTotalAmount" id="termTotalAmount"
				cssClass="numeric required textfield small" cssStyle="width:200px;"
				maxlength="8"></sj:textfield>
		</div>
	</div>
<script language="JavaScript" type="text/javascript">
	$("input:checkbox[name=chkBoxFeeSelectedIds]").change(function() {
	var levelId = document.getElementsByName("chkBoxFeeSelectedIds");
	var amount = 0;
	for (i = 0; i < levelId.length; i++) {
		if (levelId[i].checked) {
			var valueDiv = 'chkBoxFeeSelectedIds' + levelId[i].value;
			amount += parseFloat($('#' + valueDiv).html());
		}
	}
	document.getElementById('termTotalAmount').value = amount;
	
	//$('.alphanumeric').alphanumeric();
});
$('.numeric').numeric();
	$('.alphabet').alpha();
	</script>
</td>
