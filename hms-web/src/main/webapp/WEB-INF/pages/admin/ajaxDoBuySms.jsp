<%@ include file="/common/taglibs.jsp"%>
	<s:form id="getsmsCont" action="ajaxBuySms" method="post"
		theme="simple" cssClass="form-horizontal" namespace="/admin">
		<div class="form-body">
		<s:hidden name="tempId" />
		<s:hidden name="startDate" value="%{events.startDate}"></s:hidden>
		<p>
			<span class="label label-danger"> NOTE : </span>&nbsp; From the below screen you can buy the sms through online.
		</p>
		<div class="form-group">
			<label class="control-label col-md-2"><span class="required">*</span> No. of SMS : </label>
			<div class="col-md-2">
				<sj:textfield name="noOfsms" id="noOfsms"
					cssClass="form-control required numeric" maxlength="9"></sj:textfield>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-2">Each SMS Cost (paise) :</label>
			<div class="col-md-2">
				<sj:textfield  id="smsCost"  readonly="true" name="customer.smsCost" cssClass="form-control required" maxlength="15"></sj:textfield> 
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-2"><span
				class="required">*</span>Total Amount (Rs.):</label>
			<div class="col-md-2">
				<sj:textfield  id="totalAmount" readonly="true" value="" name="totalAmount"
					cssClass="form-control required" maxlength="15"></sj:textfield>
			</div>
		</div>
		
		<div class="form-actions fluid">
			<div class="col-md-offset-2 col-md-9">
				<sj:submit value="Buy SMS" cssClass="submitBt btn blue"
					onBeforeTopics="BuySMSCount" formIds="getsmsCont"
					targets="mainContentDiv" indicator="indicator" validate="true" />
				<s:url id="viewEventsUrl" action="ajaxViewEvents"
					includeParams="all" escapeAmp="false" namespace="/admin">
					<s:param name="tempId" value="0" />
				</s:url>
			</div>
		</div>
		</div>
	</s:form>
<script type="text/javascript">
$('.numeric').numeric();
$("#noOfsms").keyup(function () {
	var smsCost='<s:property value="customer.smsCost"/>'
	//var smsCost=$('#smsCost').val();
	//alert("smsCost:" + smsCost);
	var totSms=$(this).val();
	var amount=(totSms*smsCost);
	//if(totSms>=10){
		//alert(totSms+"==="+amount);
		$('#totalAmount').val(amount.toFixed(2));
	//}
});

$.subscribe('BuySMSCount', function(event, data) {
	if ($('#getsmsCont').valid()){
		var totSms=$("#noOfsms").val();
		if(totSms < 10)
		{
			$(this).val('');
			$('#totalAmount').val('');
			alert("Please buy a min 10 SMS");
			event.originalEvent.options.submit=false;
		}
		else
			return true;
	}else{
		event.originalEvent.options.submit=false;
	}
}); 
</script>