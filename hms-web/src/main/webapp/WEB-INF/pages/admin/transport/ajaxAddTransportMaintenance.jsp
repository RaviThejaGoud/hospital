<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{monthNamesList != null && !monthNamesList.isEmpty()}">
	<s:form id="addPetrolCostForm" action="ajaxAddPetrolCost" method="post"
		theme="simple" cssClass="form-horizontal" namespace="/admin">
		<s:hidden name="vehicleAcademicDetails.vehicle.id" />
		<s:hidden name="vehicleAcademicDetails.id"></s:hidden>
		<div class="form-body">
			<h4 class="pageTitle bold">
				Create Vehicle Maintenance Cost
			</h4>
			<div class="panel-body col-md-12">
				<div class="col-md-1">
					<span class="label label-danger"> NOTE : </span>
				</div>
				<div class="col-md-10">
					<ul>
						<li>
							Closing Reading should be greater than Open Reading.
						</li>
						<li>
							No.of Liters Purchased should be greater than Consumed Oil.
						</li>
					</ul>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-5">
							<span class="required">*</span>Select Month :
						</label>
						<div class="col-md-5">
							<s:select list="monthNamesList" listKey="Value" listValue="Key"
								id="monthName" cssClass="form-control input-medium required"
								name="transportMaintenance.monthId" headerKey=""
								headerValue="- Select -" theme="simple">
							</s:select>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-5">
							<span class="required">*</span>Opening Reading :
						</label>
						<div class="col-md-5">
							<s:textfield name="transportMaintenance.openingReading"
								id="openingReadingId"
								cssClass="numericDotted numeric form-control required input-medium"
								value="" onchange="javascript:onChangeOpening(this.value);">
							</s:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-5">
							Closing Reading :
						</label>
						<div class="col-md-5">
							<s:textfield name="transportMaintenance.closingReading"
								id="closingReadingId" value=""
								cssClass="numericDotted numeric form-control input-medium" readonly="true"
								onchange="javascript:onChangeOpeningCloseReading();"></s:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-5">
						 Total Kilometers :
						</label>
						<div class="col-md-5">
							<s:textfield name="transportMaintenance.totalKms" id="totalKmsId"
								cssClass="numericDotted numeric form-control input-medium"
								readonly="true"></s:textfield>
						</div>
					</div>
				</div>
			</div>
			<h4 class="pageTitle bold">
				Oil Info & Cost
			</h4>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-5">
							No.Of Liters Purchased :
						</label>
						<div class="col-md-5">
							<s:textfield name="transportMaintenance.oilPurchased"
								id="noOfLitersPurchasedId"
								cssClass="numericDotted numeric form-control input-medium"
								></s:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-5">
							Consumed Oil :
						</label>
						<div class="col-md-5">
							<s:textfield name="transportMaintenance.oilConsumed"
								id="oilConsumedId"
								cssClass="numericDotted numeric form-control input-medium "
								onchange="javascript:onChangePurchasedConsumedOil();"></s:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-5">
							Balance in Tank :
						</label>
						<div class="col-md-5">
							<s:textfield name="transportMaintenance.oilBalance"
								id="oilBalanceId" readonly="true"
								cssClass="numericDotted numeric form-control input-medium "></s:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-5">
							Oil Cost :
						</label>
						<div class="col-md-5">
							<s:textfield name="transportMaintenance.oilCost" id="oilCost"
								cssClass="numericDotted numeric form-control input-medium"></s:textfield>
						</div>
					</div>
				</div>
			</div>
			<h4 class="pageTitle bold">
				Repairs & Cost
			</h4>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-5">
							Repairs Description :
						</label>
						<div class="col-md-5">
							<s:textarea name="transportMaintenance.repairDescription"
								id="repairDescription"
								cssClass="word_count form-control input-large" rows="2" cols="2"
								maxlength="1024"></s:textarea>
							<span class="counter"></span>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-5">
							Repairs Cost :
						</label>
						<div class="col-md-5">
							<s:textfield name="transportMaintenance.repairsCost"
								id="repairsCost"
								cssClass="numericDotted numeric form-control input-medium "></s:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="form-actions fluid">
				<div class="col-md-6">
					<div class="col-md-offset-5 col-md-9">
						<sj:submit   targets="transportVehicles" value="Submit"
							cssClass="submit small btn blue" validate="true"
							onBeforeTopics="addPetrolCoarFormValidation1"
							indicator="indicator" />
						<s:url id="doVehicleMaintenance"
							action="ajaxManageTransportVehicles" namespace="/admin" />
						<sj:a href="%{doVehicleMaintenance}" targets="mainContentDiv"
							cssClass="btn default">
								 Cancel</sj:a>
					</div>
				</div>
			</div>
		</div>
	</s:form>
</s:if>
<s:else>
	<div class="alert alert-info">
		You don't have months for adding vehicle maintenance cost.
	</div>
</s:else>

<script type="text/javascript">
$(document).ready(function() {			 
	 $('.numericDotted').numeric( {
		allow : "."
	});
});
function onChangeOpening(data){
    if($("#openingReadingId").val() === ''){
        $('#closingReadingId').attr('readonly');
    }else{
        $('#closingReadingId').removeAttr('readonly');
    }
}
function onChangeOpeningCloseReading() {
	var openingReading = $('#openingReadingId').val();
	var closingReading = $('#closingReadingId').val();
	if ((isNonEmpty(openingReading) && isNonEmpty(closingReading)) && (openingReading != 0.0 && closingReading != 0.0)) {
		closingReading = Number(closingReading);
		openingReading = Number(openingReading);
		if (closingReading < openingReading ){
			alert("closing reading should be greater than opening reading");
			$('#closingReadingId').val('');
			$('#totalKmsId').val('');
		}
		else if (closingReading > openingReading){
				$('#totalKmsId').val((closingReading - openingReading).toFixed(0));
		}
	} else{
		alert("Please enter opening reading");
		$('#closingReadingId').val('');
		$('#totalKmsId').val('');
	}
		
}
function onChangePurchasedConsumedOil() {
	var noOfLitersPurchased = $('#noOfLitersPurchasedId').val();
	var oilConsumed = $('#oilConsumedId').val();
	if ((isNonEmpty(noOfLitersPurchased) && isNonEmpty(oilConsumed)) && (noOfLitersPurchased != 0.0 && oilConsumed != 0.0)) {
		noOfLitersPurchased = Number(noOfLitersPurchased);
		oilConsumed = Number(oilConsumed);
		if (noOfLitersPurchased < oilConsumed) {
			alert("No.of Liters Purchased should be greater than Consumed Oil.");
			$('#oilConsumedId').val('');
			$('#oilBalanceId').val('');
		}
		else if (noOfLitersPurchased > oilConsumed) {
			$('#oilBalanceId').val(noOfLitersPurchased - oilConsumed);
		} 
	} else{
		alert("Please enter No.of Liters Purchased");
		$('#oilConsumedId').val('');
		$('#oilBalanceId').val('');
	}
}
</script>