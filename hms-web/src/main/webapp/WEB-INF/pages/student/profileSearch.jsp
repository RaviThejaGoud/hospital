<%@ include file="/common/taglibs.jsp"%>
<div class="grid_5 border"></div>
<div class="links" onclick="GetContent('S')" style="width: 231px;">
	<a class="cancelButton grid_3" >profile Search</a>
</div>
<div class="hideStaffDetails grid_5">
	<div class="block_content grid_5" id="sideMenu" style="padding-left: 10px;">
		<!--<input type="radio" value="ST" onclick="changeFrequencyMessage('ST');" name="eventBelongs" checked="checked">
	Student
	-->
	<input type="radio" value="S" onclick="changeFrequencyMessage('S');" name="eventBelongs" checked="checked">	Staff
	<div id="selectToMessages">
		<jsp:include page="getStaffProfile.jsp"></jsp:include>
	</div>
	</div>
</div>
<script language="JavaScript" type="text/javascript">
$(".hideStaffDetails").hide();
	function GetContent(prm){
	$(".hideStaffDetails").slideToggle(600);
    changeFrequencyMessage(prm)
}

	changePageTitle('Staff Leave Details');
	$.subscribe('doSelfApprovalLeaves', function(event, data) {
		var rowObj = $('#' + data.id);
		if (rowObj.is(":hidden")) {
			rowObj.show();
		} else {
			rowObj.hide();
		}
	});
	
	 function  changeFrequencyMessage(clickButton) { 
	 var frequency=clickButton;
	 var pars = "frequency=" + frequency;
		/*if(frequency=='ST'){
		$("#selectToMessages").html('<div align="center" style="margin: 150px;"><img src="../images/indicator.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$("#selectToMessages").hide();
		 	 var url = jQuery.url.getChatURL("/student/ajaxGetStudentsList.do");
			$.ajax( {
			url : url,
			data : pars,
			cache : false,
			success : function(html) {	
				$("#selectToMessages").show();
				$("#selectToMessages").html(html);
			}
			});	
		}
		else */if(frequency=='S')
		{
		$("#selectToMessages").html('<div align="center" style="margin: 150px;"><img src="../images/indicator.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$("#selectToMessages").hide();
		 	 var url = jQuery.url.getChatURL("/student/ajaxDoGetStaffProfile.do");
			$.ajax( {
			url : url,
			data : pars,
			cache : false,
			success : function(html) {	
			$("#selectToMessages").show();
				$("#selectToMessages").html(html);
			}
			});	
		}
	}
	/*$(".hideStaffDetails").hide();//hide the all of the element with class msg_body
	$(".links").click(function()//toggle the componenet with class msg_body
	{
		$(".hideStaffDetails").slideToggle(600);
		changeFrequencyForProfileSearch('ST');
	}); */
</script>