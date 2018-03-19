<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<s:form action="ajaxGovtCustomerDetails" id="signupForm" method="post"
	theme="simple" namespace="/signup" cssClass="form-horizontal">
	<%@ include file="/common/messages.jsp"%>
	<s:hidden name="packageId" value="%{tempString}"></s:hidden>
	<%-- <s:hidden name="anyId" cssClass="anyId"></s:hidden> --%>
	<div class='error' style='display: none;'>
		<span></span>
	</div>
	<h4 class="pageTitle bold form-section">
		Personal Details
	</h4>
	<div class="form-body">
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-5">
					<span class="required">*</span>Select Role :
				</label>
				<div class="col-md-7">
					<s:select id="roleName" list="staffRoles" 
						cssClass="form-control input-medium required" theme="simple"
						name="roleName" onchange="javascript:getRoleName(this.value);" />
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-4">
					<span class="required">*</span>Select State :
				</label>
				<div class="col-md-8">
					<s:select id="state" list="statesList" label="State"
						cssClass="form-control input-medium required" listKey="Id"
						listValue="stateName" headerKey="" headerValue="- Select -"
						name="state.id" 
						onchange="javascript:getDistrictByState(this.value);" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6" id="districtDiv">
			<div class="form-group">
				<jsp:include page="/WEB-INF/pages/govt/ajaxGovtDistricts.jsp" /></div>
		</div>
		<div class="col-md-6" id="mandalDiv">
			<div class="form-group">
				<jsp:include page="/WEB-INF/pages/govt/ajaxGovtMandals.jsp" /></div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-5">
					<span class="required">*</span>First Name :
				</label>
				<div class="col-md-7">
					<sj:textfield id="firstName" name="customer.firstName" size="50"
						maxlength="50"  
						cssClass="form-control input-medium required" />
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-4">
					<span class="required">*</span>Last Name :
				</label>
				<div class="col-md-8">
					<sj:textfield id="lastName" name="customer.lastName" size="50"
						maxlength="50"  
						cssClass="form-control input-medium required" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-5">
					<span class="required">*</span>Email Address :
				</label>
				<div class="col-md-7">
					<sj:textfield id="email" size="30" maxlength="60"
						 name="customer.custEmail" 
						cssClass="form-control input-medium required" />
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-4">
					<span class="required">*</span>Your Organization Is:
				</label>
				<div class="col-md-5">
				<div class="make-switch has-switch" data-id="S" data-value="C" style="width:120px" data-off="warning" data-on="success" data-off-label="College" data-on-label="School">
					<input type="radio" class="toggle" checked="checked" id="organizationLevel" tabindex="7">
					<input type="hidden" name="customer.organizationLevel" value="S">
				</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/pages/masterAdmin/ajaxGetGeneralSubjectTypes.jsp" />
	<div id="displaySubjectTypes"></div>
	
	<div class="form-actions fluid">
		<div class="col-md-6">
			<div class="col-md-offset-5 col-md-9">
				<sj:submit   targets="mainContentDiv" value="Submit"
					cssClass="btn blue"  validate="true"
					onCompleteTopics="clickGovtCustomers" />
				<s:url id="doCancel" action="ajaxDoAddNewCustomerRegister"
					includeParams="all" namespace="/signup"></s:url>
				<sj:a href="%{doCancel}" cssClass="btn default"
					indicator="indicator" targets="mainContentDiv" button="false">Cancel</sj:a>
			</div>
		</div>
	</div>
</div>
</s:form>
<script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrap-switch/static/js/bootstrap-switch.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
FormComponents.init();
FormAdvanced.init();
$("input:checkbox, input:radio:not('.toggle')").uniform();  
	$("div#districtDiv").hide();
	$("div#mandalDiv").hide();
	
	/* var stateId = '<s:property value="state.id"/>';
	alert("stateId:"+stateId);
	getDistrictByState(stateId); */
});

/*$.subscribe('clickGovtCustomers', function(event, data) {
	document.location.href = "";
});*/

function getRoleName(roleName) {
	//$("select#state option:first-child").attr("selected", "selected");
	
	$("select#state").val('').change();
	$("div#districtDiv").empty();
	$("div#mandalDiv").empty();
	//getDistrictByState();
	if (roleName == "ROLE_CEO") {
		$("div#districtDiv").show();
		$("div#mandalDiv").show();
	}
	else if (roleName == "ROLE_BEO") {
		$("div#districtDiv").show();
		$("div#mandalDiv").show();
	} 
	else if (roleName == "ROLE_DEO") {
		$("div#districtDiv").show();
		$("div#mandalDiv").hide();
	} 
	else {
		$("div#districtDiv").hide();
		$("div#mandalDiv").hide();
	}
}

function getDistrictByState(selectBox) {
	var roleName = $("select#roleName option:selected").val();
	if (roleName != "ROLE_SEO") {
		var stateId = $("select#state option:selected").val();
		var url = jQuery.url.getChatURL("/govt/ajaxGovtDistrictsByState.do");

		$("#districtDiv")
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "student.categoryId=" + stateId;
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("div#districtDiv").show();
				$("div#districtDiv").html(html);
			}
		});
	} else {
		$("div#districtDiv").hide();
		$("div#mandalDiv").hide();
	}
}
</script>
