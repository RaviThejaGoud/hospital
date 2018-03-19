<%@ include file="/common/taglibs.jsp"%>
<s:form id="addNewVehicle" action="ajaxAddVehicle" method="post"
	theme="simple" cssClass="form-horizontal" namespace="/admin">
	<h4>Add vehicle</h4>
	<span class="label label-danger"> NOTE : </span>&nbsp; You can select Driver/Helper who are not assigned to any vehicle.
	<div class="spaceDiv"></div>
	<div class="form-body">
		<jsp:include page="/common/messages.jsp"></jsp:include>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5">
						<span class="required">*</span>Vehicle Name :
					</label>
					<div class="col-md-7">
						<sj:textfield name="vehicleAcademicDetails.name" id="name"
							cssClass="form-control input-medium required" maxlength="50"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						<span class="required">*</span>Vehicle Number :
					</label>
					<div class="col-md-8">
						<sj:textfield name="vehicle.vehicleNumber" id="vehicleNumber"
							cssClass="form-control input-medium required" maxlength="17"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5">
						Vehicle Maker :
					</label>
					<div class="col-md-7">
						<sj:textfield name="vehicle.vehicleMaker" id="vehicleMaker"
							cssClass="form-control input-medium" maxlength="50"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						Registration Authority :
					</label>
					<div class="col-md-8">
						<sj:textfield name="vehicle.registrationAuthority"
							id="registrationAuthority" cssClass="form-control input-medium"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5">
						<span class="required">*</span> No Of Seats :
					</label>
					<div class="col-md-7">
						<sj:textfield name="vehicle.noOfSeats" id="no.OfSeats"
							cssClass="numeric form-control input-medium required"
							maxlength="2"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						Road Tax Amount :
					</label>
					<div class="col-md-8">
						<sj:textfield name="vehicle.roadTaxAmount" id="roadTaxAmount"
							cssClass="form-control input-medium" maxlength="20"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<%-- <div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5">
						<span class="required">*</span>Select Driver :
					</label>
					<div class="col-md-7">
						<s:select id="driverId" list="selectboxMap" 
						headerKey="" headerValue="- Select -"  cssClass="required form-control input-medium"
						name="vehicleAcademicDetails.driverId" />
						<!--<s:select list="driverList" listKey="accountId" id="driverId"
							listValue="personFirstLastNameOnly"
							cssClass="alphabet required form-control input-medium"
							name="vehicleAcademicDetails.driverId" headerKey=""
							headerValue="- Select -">
						</s:select>
					-->
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						Select Helper :
					</label>
					<div class="col-md-8">
					<s:select id="helperId" list="jsonResult" 
						headerKey="" headerValue="- Select -"  cssClass="form-control input-medium"
						name="vehicleAcademicDetails.helperId" />
						<!--<s:select list="helperList" listKey="accountId" id="helperId"
							listValue="personFirstLastNameOnly"
							cssClass="alphabet form-control input-medium"
							name="vehicleAcademicDetails.helperId" headerKey="0"
							headerValue="- Select -">
						</s:select> -->
					</div>
				</div>
			</div>
		</div> --%>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5">
						Insurance Number :
					</label>
					<div class="col-md-7">
						<sj:textfield name="vehicle.insuranceNumber" id="insuranceNumber"
							cssClass="form-control input-medium" maxlength="20"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						Insurance Details :
					</label>
					<div class="col-md-8">
						<sj:textfield name="vehicle.insuranceDetails"
							id="insuranceDetails" cssClass="form-control input-medium"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5">
						Insurance Taken Date :
					</label>
					<div class="col-md-7">
						<div data-date-format="mm/dd/yyyy"
							class="input-group input-medium date date-picker">
							<input type="text" id="startDate" name="vehicle.insurancePaidDate" onchange="checkDateValidation();"
								readonly="readonly" value='' class="form-control">
							<span class="input-group-btn">
								<button type="button" class="btn default">
									<i class="fa fa-calendar"></i>
								</button> </span>
						</div>
						<span class="help-block">(MM/DD/YYYY)</span>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						Insurance Expiry Date :
					</label>
					<div class="col-md-8">
						<div data-date-format="mm/dd/yyyy"
							class="input-group input-medium date date-picker">
							<input type="text" id="endDate" name="vehicle.insuranceExpiredDate" onchange="checkDateValidation();"
								readonly="readonly" value='' class="form-control">
							<span class="input-group-btn">
								<button type="button" class="btn default">
									<i class="fa fa-calendar"></i>
								</button> </span>
						</div>
						<span class="help-block">(MM/DD/YYYY)</span>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5">
						Pollution Checked Date :
					</label>
					<div class="col-md-7">
						<div data-date-format="mm/dd/yyyy"
							class="input-group input-medium date date-picker">
							<input type="text" id="date1" name="vehicle.pollutionCheckedDate" onchange="pollutionCheckValidation();"
								readonly="readonly" value='' class="form-control">
							<span class="input-group-btn">
								<button type="button" class="btn default">
									<i class="fa fa-calendar"></i>
								</button> </span>
						</div>
						<span class="help-block">(MM/DD/YYYY)</span>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						Pollution Expiry Date :
					</label>
					<div class="col-md-8">
						<div data-date-format="mm/dd/yyyy"
							class="input-group input-medium date date-picker">
							<input type="text" id="date2" name="vehicle.pollutionExpiryDate" onchange="pollutionCheckValidation();"
								readonly="readonly" value='' class="form-control">
							<span class="input-group-btn">
								<button type="button" class="btn default">
									<i class="fa fa-calendar"></i>
								</button> </span>
						</div>
						<span class="help-block">(MM/DD/YYYY)</span>
					</div>
				</div>
			</div>
		</div>
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-5">
					Fitness Checked Date :
				</label>
				<div class="col-md-7">
					<div data-date-format="mm/dd/yyyy"
						class="input-group input-medium date date-picker">
						<input type="text" id="date3" name="vehicle.fitnessCheckDate" onchange="fitnessValidation();"
							readonly="readonly" value='' class="form-control">
						<span class="input-group-btn">
							<button type="button" class="btn default">
								<i class="fa fa-calendar"></i>
							</button> </span>
					</div>
					<span class="help-block">(MM/DD/YYYY)</span>
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-4">
					Fitness Expiry Date :
				</label>
				<div class="col-md-8">
					<div data-date-format="mm/dd/yyyy"
						class="input-group input-medium date date-picker">
						<input type="text" id="date4" name="vehicle.fitnessExpiryDate" onchange="fitnessValidation();"
							readonly="readonly" value='' class="form-control">
						<span class="input-group-btn">
							<button type="button" class="btn default">
								<i class="fa fa-calendar"></i>
							</button> </span>
					</div>
					<span class="help-block">(MM/DD/YYYY)</span>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-5">
					Permit Issued Date :
				</label>
				<div class="col-md-7">
					<div data-date-format="mm/dd/yyyy"
						class="input-group input-medium date date-picker">
						<input type="text" id="date5" name="vehicle.permitCheckedDate" onchange="permitValidation();"
							readonly="readonly" value='' class="form-control">
						<span class="input-group-btn">
							<button type="button" class="btn default">
								<i class="fa fa-calendar"></i>
							</button> </span>
					</div>
					<span class="help-block">(MM/DD/YYYY)</span>
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-4">
					Permit Expiry Date :
				</label>
				<div class="col-md-8">
					<div data-date-format="mm/dd/yyyy"
						class="input-group input-medium date date-picker">
						<input type="text" id="date6" name="vehicle.permitExpiryDate" onchange="permitValidation();"
							readonly="readonly" value='' class="form-control">
						<span class="input-group-btn">
							<button type="button" class="btn default">
								<i class="fa fa-calendar"></i>
							</button> </span>
					</div>
					<span class="help-block">(MM/DD/YYYY)</span>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-5">
					Road Tax PaidDate :
				</label>
				<div class="col-md-7">
					<div data-date-format="mm/dd/yyyy"
						class="input-group input-medium date date-picker">
						<input type="text" id="date7" name="vehicle.roadTaxPaidDate"  onchange="taxValidation();"
							readonly="readonly" value='' class="form-control">
						<span class="input-group-btn">
							<button type="button" class="btn default">
								<i class="fa fa-calendar"></i>
							</button> </span>
					</div>
					<span class="help-block">(MM/DD/YYYY)</span>
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-4">
					Road Tax Next PaymentDate :
				</label>
				<div class="col-md-8">
					<div data-date-format="mm/dd/yyyy"
						class="input-group input-medium date date-picker">
						<input type="text" id="date8"  onchange="taxValidation();"
							name="vehicle.roadTaxNextPaymentDate" readonly="readonly"
							value='' class="form-control">
						<span class="input-group-btn">
							<button type="button" class="btn default">
								<i class="fa fa-calendar"></i>
							</button> </span>
					</div>
					<span class="help-block">(MM/DD/YYYY)</span>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-5">
					Chasis Number :
				</label>
				<div class="col-md-7">
					<sj:textfield name="vehicle.chasisNumber" id="chasisNumber"
						cssClass="form-control input-medium" maxlength="20"></sj:textfield>
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-4">
					Classification Type :
				</label>
				<div class="col-md-8">
					<sj:textfield name="vehicle.classificationType"
						id="classificationType" cssClass="form-control input-medium"
						maxlength="25"></sj:textfield>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-5">
					Permit Number :
				</label>
				<div class="col-md-7">
					<sj:textfield name="vehicle.permitNumber" id="permitNumber"
						cssClass="form-control input-medium" maxlength="20"></sj:textfield>
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-4">
					Engine Number :
				</label>
				<div class="col-md-8">
					<sj:textfield name="vehicle.engineNumber" id="engineNumber"
						cssClass="form-control input-medium" maxlength="20"></sj:textfield>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-5">
					Owner Name :
				</label>
				<div class="col-md-7">
					<sj:textfield name="vehicle.ownerName" id="ownerName"
						cssClass="form-control input-medium" maxlength="25"></sj:textfield>
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-4">
					Select Route :
				</label>
				<div class="col-md-8">
					<s:select id="routeId" list="routeList" listKey="id" tabindex="1"
						cssClass="form-control input-medium" listValue="routeName"
						headerKey="" headerValue="- Select -" theme="simple" name="anyId"
						onchange="javascript:getDriversByVehicleRoute(this.value);" />
				</div>
			</div>
		</div>
	</div>
	
	<div id="resultsDiv3"></div>
	
	<div id="transportDriverOrHelper"></div>
	
	
	<div id="popupStudPaymentDiv"></div>
	<br/><br/>
	<div class="form-actions fluid">
		<div class="col-md-6">
			<div class="col-md-offset-5	 col-md-6">
				<sj:submit   targets="transportVehicles" value="Submit"
					cssClass="submit small btn blue" formIds="addNewVehicle"
					onBeforeTopics="adddssVehicles" indicator="indicator"
					validate="true" />
					<s:url id="doVehicleMaintenance"
									action="ajaxManageTransportVehicles" namespace="/admin" />
								<sj:a href="%{doVehicleMaintenance}"
									targets="mainContentDiv" cssClass="cancelButton btn default">
								 Cancel</sj:a>
				<!--<s:url id="urlManageTransports"
					action="ajaxDoManageTransportVehicles" includeParams="all"
					escapeAmp="false">
				</s:url>
				<sj:a href="%{urlManageTransports}"
					onCompleteTopics="doInitEditVehicle"
					onClickTopics="doCancelVehicles"
					cssClass="cancelButton btn default" indicator="indicator"
					targets="transportVehicles" button="false"
					buttonIcon="ui-icon-plus">
					Cancel
			</sj:a>
			--></div>
		</div>
	</div>
	</div>
</s:form>

<script type="text/javascript">
$(document)
		.ready(
				function() {
					$("#name").focus();
					FormComponents.init();
					$.subscribe('doCancelVehicles', function(event, data) {
						$('#VehicleDetails').show();
					});
					$("#registrationNumber")
							.autoCheck(
									"${pageContext.request.contextPath}/admin/ajaxCheckRegistrationNumberInVehicle.do",
									{
										minChars : 3,
										min : "no"
									});
					$("#insuranceNumber")
							.autoCheck(
									"${pageContext.request.contextPath}/admin/ajaxCheckInsuranceNumberInVehicle.do",
									{
										minChars : 3,
										min : "no"
									});
					$("#chasisNumber")
							.autoCheck(
									"${pageContext.request.contextPath}/admin/ajaxCheckChasisNumberInVehicle.do",
									{
										minChars : 3,
										min : "no"
									});

					$('.numeric').numeric();
					$('.alphabet').alpha();
				});
changePageTitle('Create New Vehicle');
$("input#vehicleNumber").autoCheck(
		"${pageContext.request.contextPath}/admin/ajaxCheckVehicleNumber.do", {
			minChars : 3,
			min : "no"
		});
		
function checkDateValidation() {
	var startDate = $('#startDate').val();
	var endDate = $('#endDate').val();
	if (isNonEmpty(startDate) && isNonEmpty(endDate)) {
		startDate = new Date(startDate);
		endDate = new Date(endDate);
		if (endDate <= startDate) {
			alert("Start date should be less than end date.");
			$('#endDate').val('');
		}

	} 
}
function pollutionCheckValidation() {
	var startDate = $('#date1').val();
	var endDate = $('#date2').val();
	if (isNonEmpty(startDate) && isNonEmpty(endDate)) {
		startDate = new Date(startDate);
		endDate = new Date(endDate);
		if (endDate <= startDate) {
			alert("Start date should be less than end date.");
			$('#date2').val('');
		}

	} 
}
function fitnessValidation() {
	var startDate = $('#date3').val();
	var endDate = $('#date4').val();
	if (isNonEmpty(startDate) && isNonEmpty(endDate)) {
		startDate = new Date(startDate);
		endDate = new Date(endDate);
		if (endDate <= startDate) {
			alert("Start date should be less than end date.");
			$('#date4').val('');
		}

	} 
}
function permitValidation() {
	var startDate = $('#date5').val();
	var endDate = $('#date6').val();
	if (isNonEmpty(startDate) && isNonEmpty(endDate)) {
		startDate = new Date(startDate);
		endDate = new Date(endDate);
		if (endDate <= startDate) {
			alert("Start date should be less than end date.");
			$('#date6').val('');
		}

	} 
}
function taxValidation() {
	var startDate = $('#date7').val();
	var endDate = $('#date8').val();
	if (isNonEmpty(startDate) && isNonEmpty(endDate)) {
		startDate = new Date(startDate);
		endDate = new Date(endDate);
		if (endDate <= startDate) {
			alert("Start date should be less than end date.");
			$('#date8').val('');
		}

	} 
}

function getDriversByVehicleRoute(routeId) {
	 var url = jQuery.url
			.getChatURL("/admin/ajaxGetDriverDetatilsByVRoteId.do");
	if (routeId.length == 0) {
		alert("!Oops select Route.");
		$('#resultsDiv3').hide();
		$('#transportDriverOrHelper').hide();
	} else {
		$("#resultsDiv3").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "anyId=" + routeId+"&tempId2="+0;
	 	$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#resultsDiv3").html(html);
				$('#resultsDiv3').show();
				
				$('#driverParentLabelId ').removeClass('col-md-3');
				$('#driverParentLabelId ').addClass('col-md-5');
				$('#driverChildId ').removeClass('col-md-9');
				$('#driverParentLabelId ').addClass('col-md-7');
				
				$('#helperParentLabelId ').removeClass('col-md-3');
				$('#helperParentLabelId ').addClass('col-md-4');
				$('#helperChildId ').removeClass('col-md-9');
				$('#helperChildId ').addClass('col-md-8');
			}
		});
	}
}


function popupViewFeePayments() {
	routeId = $( "#routeId" ).val();
	var url = jQuery.url.getChatURL("/admin/ajaxViewAddVehicleDriverOrHelper.do?anyTitleName=ROLE_DRIVER&tempString=driver&tempString2=vehicle&tempString3=addRoute&tempId2=0&anyId=" + routeId);
	$('#popupStudPaymentDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
			url : url,
			cache : false,
			success : function(html) {
				$("#popupStudPaymentDiv").html(html);
				
				
			}
		});
}

</script>