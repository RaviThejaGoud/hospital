<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<div class="form-body">
<s:form action="ajaxDoAddOraganizationCustomers" theme="simple"
	id="addOraganizationCustomers" method="post" cssClass="form-horizontal"
	enctype="multipart/form-data" namespace="/masterAdmin">
	<s:hidden type="hidden" name="anyTitle" id="anyTitle" />
	<s:hidden type="hidden" name="tempString" id="tempString" />
	<s:hidden name="tempId" value="%{tempId}" />
	<div class="row">
		<div class="col-md-6">
			<div class="form-group ">
				<label class="control-label col-md-4"> <span
					class="required">*</span>Organization Name : </label>
				<div class="col-md-5">
					<sj:textfield name="orgObj.organizationName" id="organizationName"
						tabindex="10" cssClass="organizationName required form-control"
						maxlength="50"></sj:textfield>
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-4"> <span
					class="required">*</span>Contact Number :
				</label>
				<div class="col-md-8">
					<sj:textfield name="orgObj.mobileNumber" id="mobileNumber"
						cssClass="numeric required form-control input-medium as-input"
						maxlength="14" onkeypress="return formatMobileNumber(this,event);"
						onchange="javascript:validateMobNumbers(this.value)"></sj:textfield>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-4"> Street : </label>
				<div class="col-md-8">
					<sj:textfield name="address.streetName" id="streetName"
						cssClass="form-control input-medium as-input" maxlength="255"></sj:textfield>
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-4"> City : </label>
				<div class="col-md-8">
					<sj:textfield name="address.city" id="city"
						cssClass="form-control input-medium as-input" maxlength="22"></sj:textfield>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-4"> State : </label>
				<div class="col-md-8">
					<s:select id="state" list="statesList" label="State"
						cssClass="form-control input-medium" listKey="stateCode"
						listValue="stateName" headerKey="" headerValue="- Select -"
						name="address.state" />
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-4"> Pincode : </label>
				<div class="col-md-8">
					<sj:textfield name="address.postalCode" id="pincode"
						cssClass="numeric form-control input-medium as-input"
						maxlength="6"></sj:textfield>
				</div>
			</div>
		</div>
	</div>
	<br />
	<s:if test='%{customersList != null && !customersList.isEmpty()}'>
		<div class="form-group">
			<label class="checkbox-inline"> Select Schools : </label>
			<div class="spaceDiv"></div>
			<div class="col-md-12">
				<s:checkboxlist name="chkBoxSelectedIds" theme="ems"
					cssClass="checkbox required form-control allCustIds"
					list="customersList" listKey="id" listValue="organization" />
			</div>
		</div>
	</s:if>
	<s:else>
		<div class="alert alert-info">Currently there is no school.</div>
	</s:else>
	<div class="form-actions fluid">
		<div class="col-md-offset-2 col-md-5">
			<sj:submit cssClass="submitBt btn blue " value="Submit" targets="mainContentDiv" validate="true" formIds="addOraganizationCustomers" onBeforeTopics="oraganizationCustomers" />
			<s:url id="urlOrganizationCustomerDetailsLink" action="ajaxOranizationCustomerView" namespace="/masterAdmin" />
			<sj:a href="%{urlOrganizationCustomerDetailsLink}" targets="mainContentDiv" cssClass="btn default">Cancel</sj:a>
		</div>
	</div>
</s:form>
</div>
<script type="text/javascript">
	$(document)	.ready(	function() {
		$('.numeric').numeric();
		$("input:checkbox, input:radio").uniform();
		var selectedValues = [];
		var unSelectedValues = [];
		$("input[name=chkBoxSelectedIds]").click(
				function() {
					if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
						$(".allCustIds").parent('span').removeClass("checked");
						$(".allCustIds").attr("checked", false);
					} else {
						$(".allCustIds").attr("checked", true);
						$(".allCustIds").parent('span').addClass("checked");
					}
					selectedValues = $("input[name=chkBoxSelectedIds]:checked").map(function() {
								return this.value;
							}).get().join(",");
					$('#anyTitle').val(selectedValues);
					unSelectedValues = $("input[name=chkBoxSelectedIds]:unchecked").map(function() {
						return this.value;
					}).get().join(",");
					$('#tempString').val(unSelectedValues);
				});
		
	});
		$.destroyTopic('oraganizationCustomers');
			$("input#organizationName").autoCheck("${pageContext.request.contextPath}/masterAdmin/ajaxCheckOrganizationName.do",
			{
				minChars : 3,
				min : "no"
			});
	function validateMobNumbers(txtMobId) {
		var mob = /^(\+91-|\+91|0)?\d{10}$/;
		if (mob.test($.trim(txtMobId)) == false) {
			alert("Please enter valid mobile number.");
			$("#mobileNumber").val('');
			$("#mobileNumber").focus();
			return false;
		} else if (!(txtMobId.length == 14)) {
			alert("The phone number is the wrong length. \nPlease enter 10 digit mobile no.");
			$("#mobileNumber").val("");
			$("#mobileNumber").focus();
			return false;
		} else if ((txtMobId.length == 14)) {
			return true;
		}
	}
	$(document).ready(function() {
			$.subscribe('oraganizationCustomers', function(event, data) {
					var selectedChkCount = $("input[name=chkBoxSelectedIds]:checked").length;
					if (selectedChkCount > 0){
						var secletCustIds = [];
						$("input:checkbox[name=chkBoxSelectedIds]:checked").each(function()
						{
							secletCustIds.push($(this).val());
						});
						$('#anyTitle').val(secletCustIds);	
					}
					else {
						alert("Please select at least one school.");
						event.originalEvent.options.submit=false;
					}
						 $('p.word-taken').each(function() {
						  if($(this).html()=='Already taken!!!'){
						     event.originalEvent.options.submit=false;
						   }
						 });
						 $('button.close').click();
			});
		});
</script>
