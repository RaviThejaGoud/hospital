<%@ include file="/common/taglibs.jsp"%>
<s:form id="editVehicle" action="ajaxEditVehicle" method="post"
	cssClass="form-horizontal" theme="simple" namespace="/admin">
	<s:hidden name="vehicleAcademicDetails.id" />
	<h4>Update vehicle</h4>
	<div class="form-body">
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5">
						<span class="required">*</span>Vehicle Name :
					</label>
					<div class="col-md-5">
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
					<div class="col-md-5">
						<sj:textfield name="vehicleAcademicDetails.vehicle.vehicleNumber"
							id="vehicleNumber" cssClass="form-control input-medium required"
							maxlength="17"></sj:textfield>
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
					<div class="col-md-5">
						<sj:textfield name="vehicleAcademicDetails.vehicle.vehicleMaker"
							id="vehicleMaker" cssClass="form-control input-medium"
							maxlength="50"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						Registration Authority :
					</label>
					<div class="col-md-5">
						<sj:textfield
							name="vehicleAcademicDetails.vehicle.registrationAuthority"
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
					<div class="col-md-5">
						<sj:textfield name="vehicleAcademicDetails.vehicle.noOfSeats"
							id="no.OfSeats"
							cssClass="numeric form-control input-medium required"
							maxlength="3"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						Road Tax Amount :
					</label>
					<div class="col-md-5">
						<sj:textfield name="vehicleAcademicDetails.vehicle.roadTaxAmount"
							id="roadTaxAmount" cssClass="form-control input-medium"
							maxlength="20"></sj:textfield>
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
					<div class="col-md-5">
						<s:select id="driverId" list="selectboxMap" 
							headerKey="" headerValue="- Select -"  cssClass="required form-control input-medium" onchange="javascript:displayDriverInfo(this.value);"
							name="vehicleAcademicDetails.driverId" />
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						Select Helper :
					</label>
					<div class="col-md-5">
						<s:select id="helperId" list="jsonResult" 
							headerKey="" headerValue="- Select -"  cssClass="required form-control input-medium" onchange="javascript:displayHelperInfo(this.value);"
							name="vehicleAcademicDetails.helperId" />
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
					<div class="col-md-5">
						<sj:textfield
							name="vehicleAcademicDetails.vehicle.insuranceNumber"
							id="insuranceNumber" cssClass="form-control input-medium"
							maxlength="20"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						Insurance Details :
					</label>
					<div class="col-md-5">
						<sj:textfield
							name="vehicleAcademicDetails.vehicle.insuranceDetails"
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
					<div class="col-md-5">
						<div data-date-format="mm/dd/yyyy"
							class="input-group input-medium date date-picker">
							<input type="text" id="startDate"
								name="vehicleAcademicDetails.vehicle.insurancePaidDate"  onchange="checkDateValidation();"
								value='<s:property value="vehicleAcademicDetails.vehicle.insurancePaidDateStr"/>'
								readonly="readonly" value='' class="form-control fdate">
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
					<div class="col-md-5">
						<div data-date-format="mm/dd/yyyy"
							class="input-group input-medium date date-picker">
							<input type="text" id="endDate"
								name="vehicleAcademicDetails.vehicle.insuranceExpiredDate"  onchange="checkDateValidation();"
								value='<s:property value="vehicleAcademicDetails.vehicle.insuranceExpiredDateStr"/>'
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
					<div class="col-md-5">
						<div data-date-format="mm/dd/yyyy"
							class="input-group input-medium date date-picker">
							<input type="text" id="date1"
								name="vehicleAcademicDetails.vehicle.pollutionCheckedDate"  onchange="pollutionCheckValidation();"
								value='<s:property value="vehicleAcademicDetails.vehicle.pollutionCheckedDateStr"/>'
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
					<div class="col-md-5">
						<div data-date-format="mm/dd/yyyy"
							class="input-group input-medium date date-picker">
							<input type="text" id="date2"
								name="vehicleAcademicDetails.vehicle.pollutionExpiryDate"  onchange="pollutionCheckValidation();"
								value='<s:property value="vehicleAcademicDetails.vehicle.pollutionExpiryDateStr"/>'
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
					<div class="col-md-5">
						<div data-date-format="mm/dd/yyyy"
							class="input-group input-medium date date-picker">
							<input type="text" id="date3"
								name="vehicleAcademicDetails.vehicle.fitnessCheckDate"  onchange="fitnessValidation();"
								value='<s:property value="vehicleAcademicDetails.vehicle.fitnessCheckDateStr"/>'
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
					<div class="col-md-5">
						<div data-date-format="mm/dd/yyyy"
							class="input-group input-medium date date-picker">
							<input type="text" id="date4"
								name="vehicleAcademicDetails.vehicle.fitnessExpiryDate"  onchange="fitnessValidation();"
								value='<s:property value="vehicleAcademicDetails.vehicle.fitnessExpiryDateStr"/>'
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
					<div class="col-md-5">
						<div data-date-format="mm/dd/yyyy"
							class="input-group input-medium date date-picker">
							<input type="text" id="date5"
								name="vehicleAcademicDetails.vehicle.permitCheckedDate" onchange="permitValidation();"
								value='<s:property value="vehicleAcademicDetails.vehicle.permitCheckedDateStr"/>'
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
					<div class="col-md-5">
						<div data-date-format="mm/dd/yyyy"
							class="input-group input-medium date date-picker">
							<input type="text" id="date6"
								name="vehicleAcademicDetails.vehicle.permitExpiryDate" onchange="permitValidation();"
								value='<s:property value="vehicleAcademicDetails.vehicle.permitExpiryDateStr"/>'
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
					<div class="col-md-5">
						<div data-date-format="mm/dd/yyyy"
							class="input-group input-medium date date-picker">
							<input type="text" id="date7"
								name="vehicleAcademicDetails.vehicle.roadTaxPaidDate" onchange="taxValidation();"
								value='<s:property value="vehicleAcademicDetails.vehicle.roadTaxPaidDateStr"/>'
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
					<div class="col-md-5">
						<div data-date-format="mm/dd/yyyy"
							class="input-group input-medium date date-picker">
							<input type="text" id="date8"
								name="vehicleAcademicDetails.vehicle.roadTaxNextPaymentDate" onchange="taxValidation();"
								value='<s:property value="vehicleAcademicDetails.vehicle.roadTaxNextPaymentDateStr"/>'
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
						Chasis Number :
					</label>
					<div class="col-md-5">
						<sj:textfield name="vehicleAcademicDetails.vehicle.chasisNumber"
							id="chasisNumber" cssClass="form-control input-medium"
							maxlength="20"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						Classification Type :
					</label>
					<div class="col-md-5">
						<sj:textfield
							name="vehicleAcademicDetails.vehicle.classificationType"
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
					<div class="col-md-5">
						<sj:textfield name="vehicleAcademicDetails.vehicle.permitNumber"
							id="permitNumber" cssClass="form-control input-medium"
							maxlength="20"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						Engine Number :
					</label>
					<div class="col-md-5">
						<sj:textfield name="vehicleAcademicDetails.vehicle.engineNumber"
							id="engineNumber" cssClass="form-control input-medium"
							maxlength="20"></sj:textfield>
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
					<div class="col-md-5">
						<sj:textfield name="vehicleAcademicDetails.vehicle.ownerName"
							id="ownerName" cssClass="form-control input-medium"
							maxlength="25"></sj:textfield>
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
				<div class="col-md-offset-5 col-md-5">
					<sj:submit   formIds="editVehicle" targets="transportVehicles"
						value="Submit" onBeforeTopics="editVehicles" validate="true"
						cssClass="submit small btn blue" indicator="indicator" />
					<s:url id="doEditvehicle" action="ajaxManageTransportVehicles"
						includeParams="all" namespace="/admin">
						<s:param name="vehicleAcademicDetails.id"
							value="%{vehicleAcademicDetails.id}" />
					</s:url>
					<sj:a href="%{doEditvehicle}" cssClass="cancelButton btn default"
						targets="mainContentDiv">
			         Cancel
		         </sj:a>
				</div>
			</div>
		</div>
	</div>
</s:form>
<script type="text/javascript">
$(document).ready(function() {
	FormComponents.init();
	$('.numeric').numeric();
	$('.alphabet').alpha();
	
	routeId = '<s:property value="anyId"/>';
	if (routeId.length > 0)
		getDriversByVehicleRoute(routeId);
});
changePageTitle('Edit Vehicle Information');

function changeVehicleTypeDiv(vehicleType) {
	if (vehicleType == 'Bus') {
		$('#vehiclesTypeDiv').show();
	} else if (vehicleType == 'Van') {
		$('#vehiclesTypeDiv').show();
	} else if (vehicleType == 'Car') {
		$('#vehiclesTypeDiv').hide();
	}
}
function checkDateValidation() {
	var startDate = $('#startDate').val();
	var endDate = $('#endDate').val();
	if (isNonEmpty(startDate) && isNonEmpty(endDate)) {
		startDate = new Date(startDate);
		endDate = new Date(endDate);
		if (endDate <= startDate) {
			alert("Start date should be less than end date.");
			$('#date2').val('');
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
		vehicleAcademicDetailsId='<s:property value="vehicleAcademicDetails.id"/>';
		$("#resultsDiv3").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "anyId=" + routeId+"&tempId2="+0+"&vehicleAcademicDetails.id="+vehicleAcademicDetailsId;
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
	var url = jQuery.url.getChatURL("/admin/ajaxViewAddVehicleDriverOrHelper.do?anyTitleName=ROLE_DRIVER&tempString=driver&tempString3=addRoute&tempId2=0&anyId=" + routeId);
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

<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/common/jquery.autoCheck.js">
</script>
