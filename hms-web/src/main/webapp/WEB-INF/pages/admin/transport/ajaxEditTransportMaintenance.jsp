<%@ include file="/common/taglibs.jsp"%>
<div data-width="760" class="modal fade modal-overflow in"
	style="display: block; width: 900px; margin-left: -379px; margin-top: 100px;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="modal-title">
			Update Vehicle Maintenance Cost
		</h4>
	</div>
	<div class="modal-body">
		<s:if test="%{monthNamesList != null && !monthNamesList.isEmpty()}">
			<s:form id="addPetrolCostForm" action="ajaxEditMaintenanceCost"
				method="post" theme="simple" cssClass="form-horizontal"
				namespace="/admin">
				<div class="form-body">
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
					<s:hidden name="vehicleAcademicDetails.id"
						value="%{vehicleAcademicDetails.id}"></s:hidden>
					<s:hidden name="transportMaintenance.id"
						value="%{transportMaintenance.id}"></s:hidden>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-5">
									<span class="required"> * </span>Select Month :
								</label>
								<div class="col-md-6">
									<s:select list="monthNamesList" listKey="Value" listValue="Key"
										label="Select Month" id="monthName"
										cssClass="required form-control input-medium"
										labelposition="no" name="transportMaintenance.monthId"
										headerKey="" headerValue="- Select -" theme="simple">
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
										onchange="javascript:onChangeOpeningCloseReading();">
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
										id="closingReadingId"
										cssClass="numericDotted numeric form-control input-medium"
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
									<s:textfield name="transportMaintenance.totalKms"
										id="totalKmsId" label="Total Kilometers"
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
										onchange="javascript:onChangePurchasedConsumedOil();"></s:textfield>
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
										id="oilConsumedId" label="Consumed Oil"
										cssClass="numericDotted numeric form-control input-medium"
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
										cssClass="numericDotted numeric form-control input-medium"></s:textfield>
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
						Repairs & cost
					</h4>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-5">
								Repairs Description :
							</label>
							<div class="col-md-6">
								<sj:textarea rows="2" cols="30" maxlength="1024"
									id="repairDescription"
									name="transportMaintenance.repairDescription"
									readonly="readonly" cssClass="form-control"></sj:textarea>
								<span class="counter"></span>
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
										id="repairsCost" label=" Repairs Cost"
										cssClass="numericDotted numeric form-control required input-medium"></s:textfield>
								</div>
							</div>
						</div>
					</div>
					<div class="form-actions fluid">
						<div class="col-md-7">
							<div class="col-md-offset-4 col-md-12">
								<img src="../img/bg/bigWaiting.gif" alt="Loading..."
									title="Loading..." id="indicator"
									style="display: none; background-repeat: no-repeat; position: relative; z-index: 1000; bottom: 200px; margin-left: 240px;" />
								<sj:submit targets="transportVehicles" value="Submit"
									cssClass="submitBt btn blue" validate="true"
									onBeforeTopics="addPetrolCoarFormValidation1" />
								<button type="button" data-dismiss="modal" class="btn default">
									Cancel
								</button>
								<!--<s:if test="%{transportMaintenance.monthId != 0}" >
						<a onclick ='javascript:toggleActivitypesForm(<s:property value="transportMaintenance.monthId"/>);'
							style="cursor: pointer;" class="btn default">Cancel</a>
					</s:if>
				-->
							</div>
						</div>
					</div>
					<div class="clear"></div>
				</div>
			</s:form>
		</s:if>
		<s:else>
			<div class="alert alert-info">
				Currently there are no Maintenance cost for this transport vehicle.
			</div>
		</s:else>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function()
{
$.destroyTopic('addPetrolCoarFormValidation1');
	/* $("input.numericDot").each(function(){
		 
		 if($(this).val().indexOf('.')> -1)
		 {                 
		  var value= $(this).val().split(".");
		  $(this).val(value[0]);
		 }
	 });*/
	$.subscribe('addPetrolCoarFormValidation1', function(event, data)
	{
		var openingReading = $('#openingReadingId').val();
		var closingReading = $('#closingReadingId').val();
		var oilConsumed = $('#oilConsumedId').val();
		var noOfLitersPurchased = $('#noOfLitersPurchasedId').val();
		openingReading = Number(openingReading);
				closingReading = Number(closingReading);
				if(closingReading <= openingReading)
				{
					alert('Closing Reading should be greater than Opening Reading.');
					event.originalEvent.options.submit=false;
				}
				else if(isNonEmpty(noOfLitersPurchased) && isNonEmpty(oilConsumed))
				{
					noOfLitersPurchased = Number(noOfLitersPurchased);
					oilConsumed = Number(oilConsumed);
					if(noOfLitersPurchased <= oilConsumed)
					{
						alert('No.of Liters Purchased should be greater than Consumed Oil.');
						event.originalEvent.options.submit=false;
					}
				}
			if(isNonEmpty(openingReading) && isNonEmpty(closingReading))
			{
				 $('button.close').click();	
			}
			
	});
		
	$('.numericDot').numeric( {
		allow : "."
	});
	$('.numericDotted').numeric( {
		allow : "."
	});
});
	function onChangeOpeningCloseReading() 
	{
		var openingReading = $('#openingReadingId').val();
		var closingReading = $('#closingReadingId').val();
		if((isNonEmpty(openingReading) || isNonEmpty(closingReading)) && (closingReading != 0.0 || openingReading != 0.0))
		{
			closingReading = Number(closingReading);
			openingReading = Number(openingReading);
			if(closingReading > openingReading )
			{
				$('#totalKmsId').val((closingReading - openingReading).toFixed(0));
			}
		}else
			$('#totalKmsId').val('');
	}
	function onChangePurchasedConsumedOil() 
	{
		var noOfLitersPurchased = $('#noOfLitersPurchasedId').val();
		var oilConsumed = $('#oilConsumedId').val();
			if((isNonEmpty(noOfLitersPurchased) || isNonEmpty(oilConsumed)) && (noOfLitersPurchased != 0.0 || oilConsumed  != 0.0))
			{
					noOfLitersPurchased = Number(noOfLitersPurchased);
					oilConsumed = Number(oilConsumed);	
				if(noOfLitersPurchased > oilConsumed ){		
				
					$('#oilBalanceId').val(noOfLitersPurchased - oilConsumed);
				}
			}
			else
			$('#oilBalanceId').val('');
	}
	
</script>