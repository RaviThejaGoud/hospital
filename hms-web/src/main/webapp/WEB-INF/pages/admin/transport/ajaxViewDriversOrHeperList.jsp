<%@ include file="/common/taglibs.jsp"%>
<div id="tabContent13" class="grid_12">
	<div id="tabWrapper13">
	<span id='respectedDriverOrHelper' style="display: none;"><s:property value='anyTitle'/></span>
		<div id="tabNavigation">
		<ul>
			<li class="selected" id="roleDriver" onclick="javascript:sucessShowHideMsg();">
				<a href='#' id='/admin/ajaxLoadDriverOrHelperByRoleName.do?anyTitleName=<s:property value="anyTitle"/>' class='type=ROLE_DRIVER'  type='driverInfo'>Drivers Information</a>
			</li>
			<li id="roleHelper" onclick="javascript:sucessShowHideMsg();">
				<a href='#' id='/admin/ajaxLoadDriverOrHelperByRoleName.do?anyTitleName=<s:property value="anyTitle"/>' class='type=ROLE_HELPER'   type='helperInfo'>Helpers Information</a>
			</li>
		</ul>
		</div>
		<div id="steps">
			<fieldset class="step" id='driverInfo'> 
            </fieldset> 
            <fieldset class="step" id='helperInfo'> 
            </fieldset> 
		</div>
	</div>
</div>
<script Language="Javascript1.2" type="text/javascript">
$(document).ready(function() {
	changePageTitle("Manage Driver/Helper");
	if($('span#respectedDriverOrHelper').html()=='ROLE_HELPER'){
		$('li#roleDriver').removeClass('selected');
		$('li#roleHelper').addClass('selected');
		
	}
	else{
	    $('li#roleHelper').removeClass('selected');
		$('li#roleDriver').addClass('selected');
	}
	$('#hostelAdmin').addClass('current');
	commonLoadTab();
	if($('#tabNavigation ul li').hasClass("selected").toString()){
			genericAjaxCall($('#tabNavigation ul li a').attr('id'),$('#tabNavigation ul li a').attr('type'), $('#tabNavigation ul li a').attr('class'));
	}
});
  
	$.subscribe('doInitEditDriverOrHelper', function(event, data) {
	
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});

	function sucessShowHideMsg(){
	  $('#successShowHide').hide();
	}
</script> 




















<%--<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/common/jquery.autoCheck.js"></script>
<div>
<img style="display: none; padding-top: 18px;" alt="Loading..."
		src="${pageContext.request.contextPath}/images/indicator.gif"
		id="indicator">
	<div id="addTransportDriverOrHelper<s:property value='id' />" style="display: none;"
		style="margin: 0px 0px 0px 3px;display: none;">
	</div>
</div>

<s:url id="getDriverDetails" action="ajaxLoadDriverOrHelperByRoleName"
		includeParams="all" escapeAmp="false">
		<s:param name="type" value="%{'ROLE_DRIVER'}"></s:param>
	</s:url>
	<sj:a href="%{getDriverDetails}" 
		indicator="indicator" targets="driverInfo"  onCompleteTopics="getDriverInfo"
		buttonIcon="ui-icon-plus"  onBeforeTopics="cleanOpenDivs">
			Driver Information(<s:property value="driverList.size"/>)
	</sj:a>
	<div id="driverInfo" style="display: none;" class='load'
		style="margin: 0px 0px 0px 3px;display: none;">
	</div>
		
		
	<br clear="all" /><br clear="all" />

	<s:url id="getHelperDetails" action="ajaxLoadDriverOrHelperByRoleName"
		includeParams="all" escapeAmp="false">
		<s:param name="type" value="%{'ROLE_HELPER'}"></s:param>
	</s:url>
	<sj:a href="%{getHelperDetails}" 
		indicator="indicator" targets="helperInfo"  onCompleteTopics="getHelperInfo"
		buttonIcon="ui-icon-plus"  onBeforeTopics="cleanOpenDivs">
			Helper Information(<s:property value="helperList.size"/>)
	</sj:a>
	<div id="helperInfo" style="display: none;" class='load'
		style="margin: 0px 0px 0px 3px;display: none;">
	</div>


<script type="text/javascript">
	$.subscribe('doInitEditDriverOrHelper', function(event, data) {
	
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});
		
	$.subscribe('getHelperInfo', function(event, data) {
	
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});
	$.subscribe('removeDriverOrHelper', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			alert('hee: ' + data.id);
			$('#' + data.id).hide();
		}
	});

	$.subscribe('addTransportDriverOrHelper', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});
	$.subscribe('getDriverInfo', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});
	
</script>
--%>