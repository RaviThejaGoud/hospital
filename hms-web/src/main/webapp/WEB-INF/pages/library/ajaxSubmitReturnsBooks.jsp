<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
	<jsp:include page="/common/messages.jsp" />
	<s:if test="%{tempList != null && !tempList.isEmpty()}">
		<table class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
			<thead>
				<tr>
					<th>
						Book Name
					</th>
					<th>
						Issued Date
					</th>
					<th>
						Due Date
					</th>
					<th>
						Due Days
					</th>
					<th>
						Book No
					</th>
					<th>
						Fine
					</th>
					<th>
						Fee Exemption
					</th>
					<th>
						Fine Pay Amount
					</th>
					<th>
						Return Book
					</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="tempList">
					<tr>
						<td>
							<s:property value="bookName" />
						</td>
						<td>
							<s:property value="issuedDateStr" />
						</td>
						<td>
							<s:property value="dueDateStr" />
						</td>
						<td>
							<s:if test="%{betweenDueDays<=0}">
								 0 Days
							</s:if>
							<s:else>
								<s:property value="betweenDueDays" />
							</s:else>
						</td>
						<td>
							<s:property value="lableCode" />
						</td>
						<td>
							<s:if test="%{betweenDueDays<=0}">
										 0
							</s:if>
							<s:else>
								<span style="font-size: 12px;">&#2352; </span> &nbsp;
								<s:property	value="(numberOfDays)*(betweenDueDays)" />
							</s:else>
						</td>
						<s:if test="%{fine>0}">
						<td>
							<input type="checkbox" name="feeExemption" value="" id="feeExemptions" />
						</td>
						<td>
							<sj:textfield name="paidFineAmount" id="amount" maxlength="35"
								cssClass="required numeric form-control input-medium"/>
						</td>
						</s:if>
						<s:else>
						<td>
							&nbsp;&nbsp; -
						</td>
						<td>
							&nbsp;&nbsp; -
						</td>
						</s:else>
						<td>
							<div>
								<input type="checkbox" name="check" value="" id="amountDiv"
									onclick="javascript:bookSubmittedConfirmDialog(this,'returnBooksContent',<s:property value='issuedBookId'/>,<s:property value='lableCodeId'/>,<s:property value='fine'/>);" />
							</div>
							<!--<div class="grid_1" align="center">
								<s:url id="addIssuedBook" action="ajaxSubmitedIssuedBook"
									includeParams="all" escapeAmp="false">
									<s:param name="issudBookId" value="%{issuedBookId}"></s:param>
									<s:param name="lableCodeId" value="%{lableCodeId}"></s:param>
									<s:param name="fine" value="%{fine}"></s:param>
								</s:url>
								<sj:a href="%{addIssuedBook}" targets="resultsDiv"
									indicator="indicator">
									<input type="checkbox" name="check" />
								</sj:a>
							</div>
						-->
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</s:if>
	<br/>
<s:else>
<div class="alert alert-info">
	Currently there are no taken Books.
</div>
</s:else>
<script type="text/javascript">
$('div#returnBooksContent').show();
$("input:checkbox, input:radio:not('.toggle')") .uniform();
 TableAdvanced.init();
 var fineExemption = '';
$('input#feeExemptions').click(function(){
	if($(this).is(":checked")){
		fineExemption = "Y";
		$('#amount').val('0');
		$('#amount').attr('disabled',true);
	}else{
		fineExemption ="N";	
		$('#amount').val('');
		$('#amount').attr('disabled',false);
	}
});	
$('#amount').change(function(){
	$('#amountDiv').val($(this).val());
});
function bookSubmittedConfirmDialog(event, target, issuedBookId, lableCodeId, fine) {
	var fineAmount=$('#amountDiv').val();
	//alert(fineAmount);
	if ($(event).parent().parent().next('.question').length <= 0) {
		$(event).parent().parent().after('<div class="question">Are You Sure?<br/> <span class="yes">Yes</span><span class="cancel">Cancel</span></div>');
	}
	$(event).parent('span').addClass("checked");
	$(event).attr("checked",true);
	$(event).parent().parent().next('.question').animate( {
		opacity : 1
	}, 300);
	$('.yes').unbind('click');
	$('.yes').bind(
			'click',
			function() {
				var prdDiv = $(this).parents('.question');
				prdDiv.html('Processing...');
				var url = jQuery.url
						.getChatURL("/library/ajaxSubmitedIssuedBook.do");
				var pars = "tempId1=" + issuedBookId + "&tempId=" + lableCodeId
						+ "&anyTitle=" + fine + "&paidAmount=" + fineAmount + "&plTitle="+fineExemption;
				$.ajax( {
					url : url,
					cache : false,
					data : pars,
					success : function(html) {
						$('#' + target).html(html);
					}
				});
			});
		$('.cancel').unbind('click');
	$('.cancel').bind('click', function() {
		$(this).parents('.question').fadeOut(300, function() {
			$(this).prev("div.checker").find('span.checked').removeClass("checked");
			$(this).prev("div.checker").find('span.checked').find("input").removeAttr("checked");
			$(this).remove();
		});
		return false;
	});
}


</script>