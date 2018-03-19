<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<%@ include file="/common/messages.jsp"%>
     <div class="grid_14 noteFont">
		<div class="grid_1">
		 		<span class="noteMassage"> Note :</span>
		 	</div>
		 	<div class="grid_13">
				You can modify/delete passwords at <a href="${pageContext.request.contextPath}/admin/schoolSettings.do?mc=true"
								id="schoolSettings">School Settings</a>.		 		
		 	</div>
	</div>
<div id="commonTabContent">
	<div id="commonTabWrapper">
		<div id="commonStep">
			<fieldset>
				<s:if
					test="%{studentPaymentList != null && !studentPaymentList.isEmpty()}">
					<div class="grid_15" align="right"  data-target="studentPayment" style="width: 940px">
							<jsp:include page="/WEB-INF/pages/common/ajaxViewPaginationOptions.jsp"></jsp:include>
					</div>
					<div class="grid_15 th" style="width: 940px">
						<div class="grid_2">
							Invoice Number
						</div>
						<div class="grid_3">
							<div class="grid_2 studentNameDiv sortHeader divArrow" style="width: 115px;" >
								Student Name
							</div>
						</div>
						<div class="grid_2">
							RollNumber
						</div>
						<div class="grid_3">
							<div class="grid_2 paymentDiv sortHeader divArrow" style="width: 135px;" >
								Payment Amount
							</div>
						</div>
						<div class="grid_2">
							Modify
						</div>
						<div class="grid_2">
							Delete
						</div>
						<div class="grid_4">
							  <input type="checkbox" name="" value="" onClick="checkAllInvoices()" class="checkbox allInvoices">&nbsp;&nbsp;All Invoices
						</div>
						<div id="studentPayment">
						<s:iterator value="studentPaymentList" status="stat">
						  <div  firstName="<s:property value='studentPaymentList[#stat.count-1][1]' />" paymentAmount="<s:property value='studentPaymentList[#stat.count-1][4]' />" class="item">
							<div class="grid_15 row1" style="width: 940px">
								<div class="grid_2">
									<s:property value="studentPaymentList[#stat.count-1][0]"/>
								</div>
								<div class="grid_3">
									<s:property value="studentPaymentList[#stat.count-1][1]"/> <s:property value="objectList[#stat.count-1][2]"/>
								</div>
								<div class="grid_2">
									<s:property value="studentPaymentList[#stat.count-1][3]"/>
									&nbsp;
								</div>
								<div class="grid_3">
									<s:property value="studentPaymentList[#stat.count-1][4]"/>
									&nbsp;
								</div>
								<div class="grid_2">
									<s:url id="modifyInvoice" action="ajaxDoModifyInvoice"
										includeParams="all" escapeAmp="false">
										<s:param name="studentId" value="studentPaymentList[#stat.count-1][6]" />
										<s:param name="invoiceNumber" value="studentPaymentList[#stat.count-1][0]" />
										<s:param name="type" value="%{'Modify'}" /> 
										<s:param name="lastUpdatedDate" value="%{studentPaymentList[#stat.count-1][5]}" />	
										<s:param name="feeSettingId" value="%{studentPaymentList[#stat.count-1][8]}"></s:param>
									</s:url>
									<sj:a href="%{modifyInvoice}"
										targets="modifyInvoice%{studentPaymentList[#stat.count-1][0]}"
										onCompleteTopics="doModifyInvoice"
										onBeforeTopics="cleanOpenDivs" indicator="indicator">Modify
									</sj:a>
								</div>
								<div class="grid_2">
								<s:url id="deleteInvoice" action="ajaxDoDeleteInvoice"
										includeParams="all" escapeAmp="false">
										<s:param name="studentId" value="studentPaymentList[#stat.count-1][6]" />
										<s:param name="invoiceNumber" value="studentPaymentList[#stat.count-1][0]" />
									</s:url>
									<sj:a href="%{deleteInvoice}"
										targets="deleteInvoice%{studentPaymentList[#stat.count-1][0]}"
										onCompleteTopics="doDeleteInvoice"
										onBeforeTopics="cleanOpenDivs" indicator="indicator">Delete
									</sj:a>
								</div>
								<div class="grid_4">
								<input type="checkbox" name="chkBoxSelectedIds" value="<s:property value='studentPaymentList[#stat.count-1][6]'/>,<s:property value="todayDate"/>,<s:property value='studentPaymentList[#stat.count-1][0]'/>" />
												&nbsp;&nbsp;&nbsp;
								   <a id="noPrint" style="cursor: pointer;" 
                                            onClick="javascript:printPreview('<s:property value='studentPaymentList[#stat.count-1][6]'/>',
                                            '<s:property value="todayDate"/>',
                                            '<s:property value='studentPaymentList[#stat.count-1][0]'/>')";
                                            target="_new"><img style="" src="../images/index.jpg">
                                            Invoice : <s:property value="studentPaymentList[#stat.count-1][0]" /> </a>
								</div>
								<div id="modifyInvoice<s:property value='studentPaymentList[#stat.count-1][0]' />" style="display: none;" class='load'> </div>
								<div id="deleteInvoice<s:property value='studentPaymentList[#stat.count-1][0]' />" style="display: none;" class='load'> </div>
							</div>
						  </div>
						</s:iterator>
					</div>
					</div>
					<div class="grid_15" style="padding-top:10px ">
						<a style="cursor: pointer; float: right;" id="noPrint"
							class="cancelButton"
							onClick="javascript:studentPrintAllInvoice()" target="_new">
							<b>Print</b>
						</a>
					</div>
				</s:if>
				<s:else>
					<div class="grid_15">
						Currently there are no today Invoice numbers.
					</div>
				</s:else>
			</fieldset>
		</div>
	</div>
</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/subscription/paginator_dev.js">
</script>
<script language="JavaScript" type="text/javascript">
$("#studentPayment").pagination();
$(document).ready(function() {
	$.subscribe('doModifyInvoice', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});
		$.subscribe('doDeleteInvoice', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});
	
	$("input[name=chkBoxSelectedIds]").click(function() {
		if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
			//$(".allInvoices").removeAttr("checked");
			$(".allInvoices").attr("checked", false);
		} else {
			$(".allInvoices").attr("checked", true);
		}
	});
	
});

function checkAllInvoices() {
	if ($(".allInvoices").is(':checked'))
		$("input[name='chkBoxSelectedIds']").attr("checked", "true");
	else
		$("input[name='chkBoxSelectedIds']").removeAttr("checked");

}
 

function studentPrintAllInvoice(){
	if($("input[name=chkBoxSelectedIds]:checked").length>0){
		var classIds = $("input[name=chkBoxSelectedIds]:checked");
		var selectedClassIds = '';
			for ( var i = 0; i < classIds.length; i++) {
				selectedClassIds += classIds[i].value + '~';
			}
		//$("#classNameIds").val(selectedClassIds);
		var pars = "selectedClassIds=" + selectedClassIds;
		var url = jQuery.url.getChatURL("/reports/ajaxStudentPaymentPdfFeeReport.do");
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			 success : function(data) {
           		prntPrvw(data);
        	}
		});
	return true;
	} else if ($("input[name=chkBoxSelectedIds]:checked").length == 0) {
		  alert("Please select at least one Invoice");
	return false;
   }
}
 
var name=1;
$('div.studentNameDiv').click(function () {
     $('div#studentPayment>div.item').sortElements(function (a, b) {return ($(a).attr('firstName').toLowerCase() > $(b).attr('firstName').toLowerCase() ? 1 : -1) * name;});
    updateDirectionArrows($(this), name);
    $("#studentPayment").pagination();
    name= name* -1;
    return false;
});

var amount=1;
$('div.paymentDiv').click(function () {
    $('div#studentPayment>div.item').sortElements(function (a, b) {return (parseInt($(a).attr('paymentAmount')) > parseInt($(b).attr('paymentAmount')) ? 1 : -1) * amount; });
    updateDirectionArrows($(this), amount);
    $("#studentPayment").pagination();
    amount = amount * -1;
    return false;
});

</script>
<style type="text/css">
.grid_1,.grid_2,.grid_3,.grid_4,.grid_5,.grid_6,.grid_7,.grid_8,.grid_9,.grid_10,.grid_11,.grid_12,.grid_13,.grid_14,.grid_15,.grid_16,grid_17,grid_18
	{
	margin-right: 0px;
}
</style>
