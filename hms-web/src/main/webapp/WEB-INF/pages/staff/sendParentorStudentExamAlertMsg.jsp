<%@ include file="/common/taglibs.jsp"%>
<div style="padding: 20px;">
	<input type="radio" value="P" onclick=changeFrequencyMessage('P'); name="eventBelongs" style="vertical-align: top;" checked>
	Parent
	<input type="radio" value="S" onclick=changeFrequencyMessage('S'); name="eventBelongs" style="vertical-align: top;">
	Student

	<div id="selectToMessages">
		<jsp:include page="/WEB-INF/pages/staff/sendExamAlertsToParent.jsp" />
	</div>
</div>
<script language="JavaScript" type="text/javascript">
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
		if(frequency=='P'){
		$("#selectToMessages").html('<div align="center" style="margin: 150px;"><img src="../images/indicator.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$("#selectToMessages").hide();
		 	 var url = jQuery.url.getChatURL("/staff/ajaxDoSendExamAlertToParent1.do");
			$.ajax( {
			url : url,
			cache : false,
			success : function(html) {	
				$("#selectToMessages").show();
				$("#selectToMessages").html(html);
			}
			});	
		}
		else if(frequency=='S')
		{
		$("#selectToMessages").html('<div align="center" style="margin: 150px;"><img src="../images/indicator.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$("#selectToMessages").hide();
		 	 var url = jQuery.url.getChatURL("/staff/ajaxGetStaffList.do");
			$.ajax( {
			url : url,
			cache : false,
			success : function(html) {	
			$("#selectToMessages").show();
				$("#selectToMessages").html(html);
			}
			});	
		}
	}
	

</script>
