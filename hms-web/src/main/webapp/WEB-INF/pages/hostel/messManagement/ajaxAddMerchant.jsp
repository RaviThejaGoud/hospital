<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/common/jquery.alphanumeric.js">
</script>
<div class="row">
	<div class="col-md-12">
		<div class="portlet-body" id="studRegister">
			<div class="tab-content">
				<%@ include file="/common/messages.jsp"%>
				<s:form action="ajaxAddMerchant" theme="simple"
					cssClass="form-horizontal" id="addMerchantFormId" method="post"
					namespace="/hostel">
					<s:hidden name="merchant.id" value="%{merchant.id}" id="merchantId" />
					<div class="form-body">
						<s:if test="%{merchant.id != 0}">
							<h4 class="bold pageTitle">
								Update Merchant
							</h4>
						</s:if>
						<s:if test="%{merchant.id == 0}">
							<h4 class="bold pageTitle">
								Add Merchant
							</h4>
						</s:if>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label col-md-4"> <span
										class="required">*</span>Merchant Name :
									</label>
									<div class="col-md-6">
										<sj:textfield name="merchant.merchantName" id="merchantName"
											cssClass="required form-control input-medium as-input"
											maxlength="50"></sj:textfield>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label col-md-4"> <span
										class="required">*</span>Mobile Number :
									</label>
									<div class="col-md-6">
										<sj:textfield name="merchant.mobileNumber" id="mobileNumber"
											cssClass="required form-control input-medium as-input numeric"
											maxlength="10" onchange="javascript:validateMobNumbers(this.value)"></sj:textfield>
									</div>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label col-md-4"> Street : </label>
									<div class="col-md-5">
										<sj:textfield name="address.addressLine1" id="addressLines"
											tabindex="1" cssClass="form-control " maxlength="55"></sj:textfield>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group ">
									<label class="control-label col-md-4"> <span
										class="required">*</span>City :
									</label>
									<div class="col-md-5">
										<sj:textfield name="address.city" id="city" tabindex="2"
											cssClass="required form-control " maxlength="40"></sj:textfield>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label col-md-4"> <span
										class="required">*</span>State :
									</label>
									<div class="col-md-5">
										<s:select id="state" list="statesList" listKey="stateCode"
											listValue="stateName" tabindex="3" headerKey=""
											headerValue="- Select -" name="address.state"
											cssClass="required form-control" />
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group ">
									<label class="control-label col-md-4"> Pincode : </label>
									<div class="col-md-5">
										<sj:textfield name="address.postalCode" id="pincode"
											label="Pincode" tabindex="4" cssClass="form-control numeric"
											maxlength="6"></sj:textfield>
									</div>
								</div>
							</div>
						</div>
						<div class="form-actions fluid">
							<div class="col-md-6">
								<div class="col-md-offset-3 col-md-12">
									<sj:submit cssClass="btn blue" value="Submit"
										indicator="indicator" formIds="addMerchantFormId"
										targets="messSettingContent" validate="true" />
								</div>
							</div>
						</div>
					</div>
				</s:form>
			</div>
		</div>
	</div>
</div>
<script language="JavaScript" type="text/javascript">
changePageTitle('Add Merchant Details');
$(document).ready(function() {
	FormAdvanced.init();
	$('.numeric').numeric();
		});
$('.numeric').numeric();
$('.numericDot').numeric( {
	allow : "."
});
function validateMobNumbers(txtMobId) {
	/* var mob = /^(\+91-|\+91|0)?\d{10}$/;
	if (mob.test($.trim(txtMobId)) == false) {
		alert("Please enter valid mobile number.");
		$("#mobileNumber").val('');
		$("#mobileNumber").focus();
		return false;
	}else */ if (!(txtMobId.length == 10)) {
		alert("The phone number is the wrong length. \nPlease enter 10 digit mobile no.");
		$("#mobileNumber").val("");
		$("#mobileNumber").focus();
		return false;
	}else if((txtMobId.length == 10)) {
	return true;
	}
}

$("#phoneNumber").change(function(){ 
	var text = $(this).val();
     if (Math.floor(text) != text) {
      alert("Please enter numbers.");
      $("#phoneNumber").val('');
      return  false;
     }
});
	
	
</script>