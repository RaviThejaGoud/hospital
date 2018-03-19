<%@ include file="/common/taglibs.jsp"%>
<div id="tabContent13" class="grid_12">
	<div id="tabWrapper13">
	<div id="successShowHide"><jsp:include page="/common/messages.jsp"></jsp:include></div>
	<span id='respectedBusOrCarOrVan' style="display: none;"><s:property value='anyTitle'/></span>
		<div id="tabNavigation">
		<ul>
			<li class="selected" id="bus"  onclick="javascript:sucessShowHideMsg();">
				<a href='#' id='/admin/ajaxLoadVehiclesByStatus.do?anyTitleName=<s:property value="anyTitle"/>' class='type=Bus'  type='busInfo'>Buses Information</a>
			</li>
			<li id="van"  onclick="javascript:sucessShowHideMsg();">
				<a href='#' id='/admin/ajaxLoadVehiclesByStatus.do?anyTitleName=<s:property value="anyTitle"/>' class='type=Van'   type='vanInfo'>Vans Information</a>
			</li>
			<li id="car"  onclick="javascript:sucessShowHideMsg();">
				<a href='#' id='/admin/ajaxLoadVehiclesByStatus.do?anyTitleName=<s:property value="anyTitle"/>' class='type=Car'   type='carInfo'>Cars Information</a>
			</li>
		</ul>
		</div>
		<div id="steps">
			<fieldset class="step" id='busInfo'> 
            </fieldset> 
            <fieldset class="step" id='vanInfo'> 
            </fieldset> 
			<fieldset class="step" id='carInfo'> 
            </fieldset>
		</div>
	</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script Language="Javascript1.2" type="text/javascript">
$(document).ready(function() {
	changePageTitle("Manage Vehicles");
	$('#hostelAdmin').addClass('current');
	commonLoadTab();
	if($('#tabNavigation ul li').hasClass("selected").toString()){
			genericAjaxCall($('#tabNavigation ul li a').attr('id'),$('#tabNavigation ul li a').attr('type'), $('#tabNavigation ul li a').attr('class'));
	}
	if($('span#respectedBusOrCarOrVan').html()=='Bus'){
		$('li#car').removeClass('selected');
		$('li#van').removeClass('selected');
		$('li#bus').addClass('selected');
	}
	else if($('span#respectedBusOrCarOrVan').html()=='Car'){
	    $('li#bus').removeClass('selected');
	    $('li#van').removeClass('selected');
		$('li#car').addClass('selected');
	}else if($('span#respectedBusOrCarOrVan').html()=='Van'){
		$('li#bus').removeClass('selected');
		$('li#car').removeClass('selected');
		$('li#van').addClass('selected');
	}else{
		$('li#car').removeClass('selected');
		$('li#van').removeClass('selected');
		$('li#bus').addClass('selected');
	}
});

function sucessShowHideMsg(){
  $('#successShowHide').hide();
}
</script> 







<%--<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/common/jquery.autoCheck.js"></script>
<div>
<jsp:include page="/common/messages.jsp"></jsp:include>
<img style="display: none; padding-top: 18px;" alt="Loading..."
		src="${pageContext.request.contextPath}/images/indicator.gif"
		id="indicator">
	<div id="addTransport<s:property value='id' />" style="display: none;"
		style="margin: 0px 0px 0px 3px;display: none;">
	</div>
</div>
<div id="VehicleDetails">
	<s:url id="getBusDetails" action="ajaxLoadVehiclesByStatus"
		includeParams="all" escapeAmp="false">
		<s:param name="type" value="%{'bus'}"></s:param>
	</s:url>
	<sj:a href="%{getBusDetails}" 
		indicator="indicator" targets="busInfo"  onCompleteTopics="getBusInfo" buttonIcon="ui-icon-plus" onBeforeTopics="cleanDivs">
			Bus Information(<s:property value="busesList.size" />)
	</sj:a>
	<div id="busInfo" style="display: none;" class='loadVehicle'
		style="margin: 0px 0px 0px 3px;display: none;">
	</div>
		
	<br clear="all" /><br clear="all" />

	<s:url id="getVanDetails" action="ajaxLoadVehiclesByStatus"
		includeParams="all" escapeAmp="false">
		<s:param name="type" value="%{'van'}"></s:param>
	</s:url>
	<sj:a href="%{getVanDetails}" 
		indicator="indicator" targets="vanInfo"  onCompleteTopics="getVanInfo"
		buttonIcon="ui-icon-plus" onBeforeTopics="cleanDivs">
			Van Information(<s:property value="vansList.size" />)
	</sj:a>
	<div id="vanInfo" style="display: none;" class='loadVehicle'
		style="margin: 0px 0px 0px 3px;display: none;">
	</div>

	<br clear="all" /><br clear="all" />

	<s:url id="getCarDetails" action="ajaxLoadVehiclesByStatus"
			includeParams="all" escapeAmp="false">
			<s:param name="type" value="%{'car'}"></s:param>
		</s:url>
		<sj:a href="%{getCarDetails}" 
			indicator="indicator" targets="carInfo"  onCompleteTopics="getCarInfo"
			buttonIcon="ui-icon-plus" onBeforeTopics="cleanDivs">
				Car Information(<s:property value="carsList.size" />)
		</sj:a>
		<div id="carInfo" style="display: none;" class='loadVehicle'
			style="margin: 0px 0px 0px 3px;display: none;">
		</div>
		</div>
<script type="text/javascript">
	$.subscribe('cleanDivs', function(event, data) {
		$("div.loadVehicle").each(function(i, row) {
			$(row).find('div').remove();
			$(row).hide();
		});
	});
	$.subscribe('doInitEditVehicle', function(event, data) {
	   if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
		});
	
	$.subscribe('getBusInfo', function(event, data) {
		
			if ($('#' + data.id).is(":hidden")) {
				$('#' + data.id).show();
			} else {
				$('#' + data.id).hide();
			}
		});
		
	$.subscribe('getVanInfo', function(event, data) {
	
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});
	
	$.subscribe('getCarInfo', function(event, data) {
	
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});
		
	$.subscribe('removeVehicle', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			alert('hee: ' + data.id);
			$('#' + data.id).hide();
		}
	});

	$.subscribe('addTransport', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});

</script>
--%>