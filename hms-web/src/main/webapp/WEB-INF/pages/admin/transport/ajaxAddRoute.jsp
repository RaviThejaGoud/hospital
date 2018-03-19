<%@ include file="/common/taglibs.jsp"%>
<s:form action="ajaxAddRoute" theme="simple" id="addNewRoutes"
	cssClass="form-horizontal" namespace="/admin">
	<span class="boardingPointsCount" id="<s:property value='tempId1'/>"></span>
	<span class="routeId" id="<s:property value='tempId'/>"></span>
	<span class="feeSetting" id="<s:property value='tempBoolean'/>"></span>
	<s:hidden name="tempString" cssClass='tempString' />
	<s:hidden name="tempId" />
	<s:hidden name="boardingPoint" cssClass='boardingPointIds' />
	<h4>
		<s:if test="%{route.id == 0}">
			Add route
		</s:if>
		<s:else>
			Update route
		</s:else>
	</h4>
	<div class="form-group">
		<div class="panel-body col-md-12">
			<div class="col-md-1">
				<span class="label label-danger"> NOTE : </span>
			</div>
			<div class="col-md-10">
				<ul>
					<li>
						You can add any number of boarding points in a route.
					</li>
					<li>
						Arrival point will be considered from school address.
					</li>
					<li>
						You can add the fee for each boarding point. This fee will be
						added to the students whomever using this boarding point.
					</li>
				</ul>
			</div>
		</div>
	</div>
	<div class="form-body">
		<jsp:include page="/common/messages.jsp"></jsp:include>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-3">
						<span class="required">*</span>Route Name :
					</label>
					<div class="col-md-9">
						<sj:textfield name="route.routeName" id="routeName"
							cssClass="required form-control input-medium" maxlength="40"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="form-group">
			<h4 class="bold pageTitle">
				<span>Boarding points :</span>
			</h4>
		</div>
		<div id="dispayErrorMsg" class="grid_13" style="display: none;">
			<div class='block'>
				<div style='display: block' class='message errormsg'>
					You can not remove this boarding point.This boarding point already
					assigned to students.
				</div>
			</div>
		</div>
		<div id='TextBoxesGroup'>
			<div id="TextBoxDiv1" class="boardingPointsData">
				<span id='' class='boardingPointId1 boardingIds'></span>
				<div class="row">
					<div class="col-md-3">
						<label>
							Departure Point :
						</label>
						<sj:textfield name="routeBoardingPoints.boardingPointName"
							cssStyle="width:180px;Padding:4px;" id='boardingPointName1'
							cssClass="boardingPoint required form-control input-medium"
							maxlength="40"></sj:textfield>
					</div>
					<div class="col-md-2 timeEntry">
						<label>
							Departure Time :
						</label>
						<sj:textfield name="routeBoardingPoints.boardingPointStatTime" id='boardingPointStatTime1'
							cssClass="required form-control input-small startTime timeChange"
							maxlength="10"></sj:textfield>
						<span class="hintMessage">Time format 11:00 AM</span>
					</div>
					<div class="col-md-2 timeEntry">
						<label>
							Arrival Time :
						</label>
						<sj:textfield name="routeBoardingPoints.boardingPointEndTime" id='boardingPointEndTime1'
							cssClass="required form-control input-small endTime timeChange"
							maxlength="10"></sj:textfield>
						<span class="hintMessage">Time format 11:00 AM</span>
					</div>
					<s:if test='%{tempBoolean}'>
						<div class="col-md-2">
							<label>
								Amount :
							</label>
							
							<sj:textfield name="routeBoardingPoints.boardingPointFeeAmount" id='boardingPointFeeAmount1'
								cssClass="numericDot required form-control input-small boardingPointFeeAmount"
								maxlength="10"></sj:textfield> 
							
						</div>
					</s:if>
					<div class="col-md-2" style="margin-top: 25px;">
						<a title="Add Boarding Point"
							style="cursor: pointer; width: 78px;" id="insertValues"
							class="normalAdd btn btn-xs green"
							onclick="insertValue(1,'isNew','<s:property value="tempBoolean"/>')">
							<i class="fa fa-plus"></i>Add Point</a>
					</div>
					<!-- <div class="col-md-1" style="margin-top: 25px;">
						<a title="Delete" style="cursor: pointer; width: 60px;"
							id="removeValues" class="btn btn-xs red"
							onclick="removeValue(1,this)"><i class="fa fa-times"></i>Delete</a>
					</div> -->
				</div>
			</div>
		</div>
		<div class="spaceDiv"></div>
		<div class="row">
			<div class="col-md-3">
				<label>
					Arrival Point :
				</label>
				<sj:textfield name="customer.Address.addressLine1"
					id="customerShortName" cssStyle="width:200px;Padding:4px;"
					disabled="true" cssClass="required form-control input-medium"
					maxlength="40"></sj:textfield>
			</div>
			<div class="col-md-2 timeEntry">
				<label>
					Arrival Time :
				</label>

				<sj:textfield id="arrivalTimeInput" name="route.routePointEndTime"
					cssClass="required form-control input-small startTime timeChange"
					maxlength="10"></sj:textfield>
				<span class="hintMessage">Time format 11:00 AM</span>
			</div>
			<div class="col-md-2 timeEntry">
				<label>
					Departure Time :
				</label>
				<sj:textfield id="departureTimeInput"
					name="route.routePointReturnStartTime"
					cssClass="required form-control input-small startTime timeChange"
					maxlength="10"></sj:textfield>
				<span class="hintMessage">Time format 11:00 AM</span>
			</div>
		</div>
		
		<div id="vehiclesDivId"></div>
		
		<div id="resultsDiv3"></div>
		
		<div id="transportDriverOrHelper"></div>
		
		<div class="form-actions fluid">
			<div class="col-md-6">
				<div class="col-md-offset-6 col-md-9">
					<sj:submit   targets="routeContent" value="Submit" validate="true"
						cssClass="submit small btn blue" formIds="addNewRoutes"
						onBeforeTopics="addRouteFormValidation1" indicator="indicator" />
					<s:url id="urlManageRoute" action="ajaxManageRoutes"
						namespace="/admin" />
					<sj:a href="%{urlManageRoute}" targets="mainContentDiv"
						cssClass="cancelButton btn default">
								 Cancel</sj:a>
				</div>
			</div>
		</div>
	</div>
	
	<div id="popupStudPaymentDiv"></div>
	
	
</s:form>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/timeEntry/jquery.timeentry.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/moment.min.js"></script>
<!-- <script src="http://momentjs.com/downloads/moment.min.js"></script> -->
<script type="text/javascript">
changePageTitle('Add Route');
$(document).ready(function() {
	$(this).find('div.timeEntry').each(function() {
		$(this).find('input.timeChange').each(function() {
			$('#' + $(this).attr('id')).timeEntry();
		});
	});
	$('#' + $(this).attr('id')).timeEntry();
	var property = $('input[name=tempBoolean]').val();
	var counter = 1;
	var routeId = $("span.routeId").attr("id");
	if (isNonEmpty(routeId)) {
		var url = jQuery.url.getChatURL("/admin/ajaxGetBoardingPoints.do?tempId1=" + routeId);
		// changed for namespace error var url = "admin/ajaxGetBoardingPoints.do?tempId1=" + routeId;
		$.ajax( {
			url : url,
			cache : false,
			dataType : 'json',
			success : function(response) {
				var boardingPointList = response.routeBoardingPointsList;
				if (boardingPointList) {
					for ( var j = 0; j <= (boardingPointList.length + 1); j++) {
						if (isNonEmpty(boardingPointList[j])) {
							$("span.boardingPointId"+ (j + 1)).attr('id',boardingPointList[j][0]);
							$('#boardingPointName' + (j + 1)).val(boardingPointList[j][1]);
							$('#boardingPointStatTime' + (j + 1)).val(boardingPointList[j][2]);
							$('#boardingPointEndTime' + (j + 1)).val(boardingPointList[j][3]);
							$('#boardingPointFeeAmount' + (j + 1)).val(boardingPointList[j][4]);
							
							if(boardingPointList[j][5] == "Y"){
								$('#boardingPointFeeAmount' + (j + 1)).prop('disabled', true);
							}
							if (j < (boardingPointList.length - 1)) 
								counter = insertValue(counter,'isExist',property);
						}
					}
				}
			}
		});
	}
	
	$.subscribe('addRouteFormValidation1',function(event, data) {
		var boardingPointId = '';
		var startTime = '';
		var endTime = '';
		var boardingPointName = '';
		var boardingPointFeeAmount = '';
		var jsonObj = [];
		var unSelectedSchIds = '(';
		var i = 0;
		var isBoardingAmount=false;
		$('div.boardingPointsData').each(function() {
			boardingPointName = $(this).find('.boardingPoint').val();
			startTime = $(this).find('.startTime').val();
			endTime = $(this).find('.endTime').val();
			boardingPointId = $(this).find("span.boardingIds").attr('id');
			boardingPointFeeAmount = $(this).find('.boardingPointFeeAmount').val();
			if (isNonEmpty(boardingPointId)) {
				unSelectedSchIds += boardingPointId+ ",";
			}
			if (isNonEmpty(boardingPointFeeAmount)) {
				if(boardingPointFeeAmount==0)
				isBoardingAmount=true;
			}
			if (isNonEmpty(boardingPointName)) {
				jsonObj.push( {
					"boardingPointId" : boardingPointId,
					"boardingPointName" : boardingPointName,
					"startTime" : startTime,
					"endTime" : endTime,
					"boardingPointFeeAmount" : boardingPointFeeAmount
				});
			}
		});
		if(isBoardingAmount){
			alert("Boarding point fee amount not allowed 0.");
			event.originalEvent.options.submit = false;
		}
		unSelectedSchIds += '0)';
		$('.tempString').val(JSON.stringify(jsonObj));
		$('.boardingPointIds').val(unSelectedSchIds);
		var response = $('.tempString').val();
		if (response == '[]') {
			//alert("Please create at least one boarding point.");
			event.originalEvent.options.submit = false;
		}
		
		var boardingPointStatTime1 = $( "#boardingPointStatTime1" ).val();
		var arrivalTimeInput = $( "#arrivalTimeInput" ).val();
		var beginningTime = moment(boardingPointStatTime1, 'h:mma');
		var endTime = moment(arrivalTimeInput, 'h:mma');
		
		if(!beginningTime.isBefore(endTime) || beginningTime==endTime){
			alert("Arrival time must not accept a value less than departure time/Both departure time or arrival time cannot accept a same value");
			event.originalEvent.options.submit = false;
		}
		
		$('ul.nav-tabs li').removeClass('active');
		$('li#doAddRoutes').addClass('active');
	});
	
	$("#routeName").autoCheck("${pageContext.request.contextPath}/admin/ajaxCheckRouteNameInRoute.do?selectedId="+ routeId, {
		minChars : 3,
		min : "no"
	}).focus();
	
	arrivalTimeInput = $( "#arrivalTimeInput" ).val();
	departureTimeInput = '<s:property value="route.routePointStartTime"/>';
	getVehiclesByRouteStartTimeAndEndTime(arrivalTimeInput,departureTimeInput);
});
function removeValue(counter, event) {
	var countLength = $(".boardingPoint").length;
	if (counter <= 1) {
		alert("You cann't remove this departure point.Update this departure point details.");
		return false;
	}
	var boardingPointId = $('span.boardingPointId' + counter).attr('id');
	if ($(event).next('.question').length <= 0) {
		$(event).after('<div class="question" style="margin-left:-6em">Are You Sure?<br/> <span class="yes">Yes</span><span class="cancel">Cancel</span></div>');
	}
	$(event).next('.question').animate( {
		opacity : 1
	}, 300);
	$('.yes').unbind('click');
	$('.yes').bind('click',function() {
		var prdDiv = $(this).parents('.question');
		prdDiv.html('Processing...');
		if (isNonEmpty(boardingPointId)) {
			$.ajax( {
				url : jQuery.url.getChatURL("/admin/ajaxCheckBoardingPointAssignedStatus.do?tempId2="+ boardingPointId),
				cache : false,
				success : function(response) {
					if (isNonEmpty(response)) {
						if (response.tempBoolean)
							$("#TextBoxDiv" + counter).remove();
						else {
							boardingPointErrorMsg();
							return false;
						}
					} else {
						boardingPointErrorMsg();
						return false
					}
				}
			});
		} else {
			$("#TextBoxDiv" + counter).remove();
		}
					});
	$('.cancel').unbind('click');
	$('.cancel').bind('click', function() {
		$(this).parents('.question').fadeOut(300, function() {
			$(this).remove();
		});
		return false;
	});
}

function insertValue(counter, status, property) {
	var isNew = 808080;
	var countLength = $(".boardingPoint").length;
	if (status == 'isExist') {
		countLength = counter;
	}
	countLength = countLength + 1;
	var newTextBoxDiv = $(document.createElement('div')).attr("id",
			'TextBoxDiv' + countLength);
	newTextBoxDiv.addClass('boardingPointsData');
	property = "'" + $("span.feeSetting").attr("id") + "'";
	if (property == "'false'") {
		newTextBoxDiv
				.html('<div class="spaceDiv"></div><div class="row"><span id="" class="boardingPointId'
						+ countLength
						+ ' boardingIds"></span><div class="col-md-3"><label>Boarding Point : </label>'
						+ '<input type="text" style="width:200px;padding:4px;" name="boardingPointName'
						+ countLength
						+ '" id="boardingPointName'
						+ countLength
						+ '" value="" class="boardingPoint required form-control input-medium"/> </div>'
						+ '<div class="col-md-2 timeEntry"><label>Departure Time : </label>'
						+ '<input type="text"  name="boardingPointStatTime'
						+ countLength
						+ '" id="boardingPointStatTime'
						+ countLength
						+ '"  onchange="checkDepartureTime(this)" onclick="javascript:timeEntryChange(this);" onfocus="javascript:timeEntryChange(this);" style="width:100px;padding:4px;margin-bottom:10px;" class="startTime required form-control input-small timeChange"></input> <span class="hintMessage">Time format 11:00 AM</span></div>'
						+ '<div class="col-md-2 timeEntry"><label>Arrival Time : </label>'
						+ '<input type="text" name="boardingPointEndTime'
						+ countLength
						+ '" id="boardingPointEndTime'
						+ countLength
						+ '"onchange="checkArrivalTime(this)" onclick="javascript:timeEntryChange(this);" onfocus="javascript:timeEntryChange(this);" style="width:100px;padding:4px;margin-bottom:10px;" class="endTime required form-control input-small timeChange" ></input> <span class="hintMessage">Time format 11:00 AM</span></div>  '
						+ '<div class="col-md-2" style="margin-top:25px;"><a title="Add Boarding Point" style="cursor: pointer;width:78px;" id="insertValues" class="normalAdd btn btn-xs green" onclick="insertValue('
						+ countLength
						+ ','
						+ isNew
						+ ','
						+ property
						+ ')"><i class="fa fa-plus"></i>Add Point</a></div>'
						+ '<div class="col-md-1" style="margin-top:25px;"><a title="Delete" indicator="indicator" class="btn btn-xs red normalDelete" style="cursor: pointer;position: absolute;width:60px;" id="removeValues" onclick="removeValue('
						+ countLength
						+ ',this)"><i class="fa fa-times"></i>Delete</a> </div>'
						+ '</div><div class="spaceDiv"></div>');
	} else {
		newTextBoxDiv .html('<div class="spaceDiv"></div><div class="row"><span id="" class="boardingPointId' + countLength + ' boardingIds"></span><div class="col-md-3"><label>Boarding Point : </label>'
						+ '<input type="text" style="width:200px;padding:4px;" name="boardingPointName'
						+ countLength
						+ '" id="boardingPointName'
						+ countLength
						+ '" value=""  class="boardingPoint required form-control input-medium"/> </div>'
						+ '<div class="col-md-2 timeEntry"><label>Departure Time : </label>'
						+ '<input type="text"  name="boardingPointStatTime'
						+ countLength
						+ '" id="boardingPointStatTime'
						+ countLength
						+ '"  onchange="checkDepartureTime(this)" onclick="javascript:timeEntryChange(this);" onfocus="javascript:timeEntryChange(this);" style="width:100px;padding:4px;margin-bottom:10px;" class="startTime required form-control input-small"></input> <span class="hintMessage">Time format 11:00 AM</span></div>'
						+ '<div class="col-md-2 timeEntry"><label>Arrival Time : </label>'
						+ '<input type="text" name="boardingPointEndTime'
						+ countLength
						+ '" id="boardingPointEndTime'
						+ countLength
						+ '"  onchange="checkArrivalTime(this)" onclick="javascript:timeEntryChange(this);" onfocus="javascript:timeEntryChange(this);" style="width:100px;padding:4px;margin-bottom:10px;" class="required endTime form-control input-small"></input> <span class="hintMessage">Time format 11:00 AM</span></div>  '
						+ '<div class="col-md-2"> <label>Amount : </label>'
						+ '<input type="text" style="width:65px;padding:4px;" name="boardingPointFeeAmount'
						+ countLength
						+ '" id="boardingPointFeeAmount'
						+ countLength
						+ '" value="" class="boardingPointFeeAmount numericDot required form-control input-small"/></div>'
						+ '<div class="col-md-2" style="margin-top:25px;"><a title="Add Boarding Point" style="cursor: pointer;width:78px;" id="insertValues" class="normalAdd btn btn-xs green" onclick="insertValue('
						+ countLength
						+ ','
						+ isNew
						+ ','
						+ property
						+ ')"><i class="fa fa-plus"></i>Add Point</a></div>'
						+ '<div class="col-md-1" style="margin-top:25px;"><a title="Delete" indicator="indicator" style="cursor: pointer;position: absolute;width:60px;" id="removeValues" class="btn btn-xs red normalDelete"  onclick="removeValue('
						+ countLength
						+ ',this)"><i class="fa fa-times"></i>Delete</a> </div>'
						+ '</div><div class="spaceDiv"></div>');
	}

	if (status == 'isExist') {
		newTextBoxDiv.insertAfter("#TextBoxDiv" + counter);
		counter++;
		return counter;
	} else {
		newTextBoxDiv.insertAfter("#TextBoxDiv" + counter);
	}
	$('.numericDot').numeric( {
		allow : "."
	});
}
function timeEntryChange(obj) {
	if(!$(obj).hasClass("hasTimeEntry")){
		$('#' + $(obj).attr('id')).timeEntry();
		$(obj).focus();
	}
}

function boardingPointErrorMsg() {
	$('div.question').remove();
	$('#dispayErrorMsg').show();
	loadCloseIcon();
}
function checkDepartureTime(event) {
	var selectedTime = $(event).val();
	var previousTime = '';
	if (isNonEmpty(selectedTime)) {
		$('select.startTime option:selected[value != ""]').each(
				function() {
					if (isNonEmpty(previousTime)) {
						var previousSelectedDate = new Date("1/1/2007 "
								+ previousTime);
						var selectedDate = new Date("1/1/2007 "
								+ $(this, 'option:selected').val());
						if (selectedDate <= previousSelectedDate) {
							alert("Please change selectd time.");
							event.value = '';
						}
					}
					previousTime = $(this, 'option:selected').val();
				});
	}
}
function checkArrivalTime(event) {
	var selectedTime = $(event).val();
	var previousTime = '';
	if (isNonEmpty(selectedTime)) {
		$('select.endTime option:selected[value != ""]').each(
				function() {
					if (isNonEmpty(previousTime)) {
						var previousSelectedDate = new Date("1/1/2007 "
								+ previousTime);
						var selectedDate = new Date("1/1/2007 "
								+ $(this, 'option:selected').val());
						if (selectedDate >= previousSelectedDate) {
							alert("Please change selected time.");
							event.value = '';
						}
					}
					previousTime = $(this, 'option:selected').val();
				});
	}
}
$('.numericDot').numeric( {
	allow : "."
})

$( "#boardingPointStatTime1" ).keyup(function() {
	
	arrivalTimeInput = $( "#arrivalTimeInput" ).val();
	departureTimeInput = $( "#boardingPointStatTime1" ).val();
	
	if(isNonEmpty(arrivalTimeInput))
	{
		getVehiclesByRouteStartTimeAndEndTime(arrivalTimeInput,departureTimeInput);
		
	}
	
	  
});
	
$( "#arrivalTimeInput" ).keyup(function() {
	
	arrivalTimeInput = $( "#arrivalTimeInput" ).val();
	departureTimeInput = $( "#boardingPointStatTime1" ).val();
	
	
	if(isNonEmpty(arrivalTimeInput) && isNonEmpty(departureTimeInput))
	{
		getVehiclesByRouteStartTimeAndEndTime(arrivalTimeInput,departureTimeInput);
	}
	else if(!isNonEmpty(departureTimeInput))
	{
		alert("Please add Departure Point's Departure Time." );
	}
	
});


function getVehiclesByRouteStartTimeAndEndTime(arrivalTimeInput,departureTimeInput) {
	
	if(isNonEmpty(arrivalTimeInput) && isNonEmpty(departureTimeInput))
	{
			routeId = '<s:property value="tempId"/>';
		
			var pars = "route.routePointEndTime=" + arrivalTimeInput+"&route.routePointStartTime="+departureTimeInput+"&tempId="+routeId;
			$("div#vehiclesDivId")
					.html(
							'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			var url = jQuery.url.getChatURL("/admin/ajaxGetVehiclesByRouteStartTimeAndEndTime.do");
			$.ajax( {
				url : url,
				cache : false,
				data : pars,
				success : function(html) {
					$("div#vehiclesDivId").html(html);
				}
			});
		}
	}
	
function popupViewFeePayments() {
	
	arrivalTimeInput = $( "#arrivalTimeInput" ).val();
	departureTimeInput = $( "#boardingPointStatTime1" ).val();
	vehicleId = $( "#vehicleId" ).val();
	var url = jQuery.url.getChatURL("/admin/ajaxViewVehicleDriverOrHelper.do?anyTitleName=ROLE_DRIVER&tempString=driver&tempString3=addRoute&route.routePointEndTime=" + arrivalTimeInput+"&route.routePointStartTime="+departureTimeInput+"&tempId2="+vehicleId);
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

