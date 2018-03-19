<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/group/paginator.js">
</script>
<script type="text/javascript">
changePageTitle('View Teacher Messages');
$(function() {
	$("#resultsPage").pagination();
});


function viewReadMoreMin(id) {
	$("#resultsDiv")
			.html(
					'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	var pars = "id=" + id;
	var url = jQuery.url
			.getChatURL("/student/ajaxViewReadMoreBirthDayMessagesForStudent.do");
	$.ajax( {
		url : url,
		cache : false,
		data : pars,
		success : function(html) {
			$("#myMessagesContent").html(html);
			$('#formattedMsg').html($('#unFormattedMsg').text());
		}

	});
}
function getAjaxRemoveUserInquiry(id) {
	var pars = "id=" + id;
	var urt = confirm("Are you sure you want to delete this topic?");
	if (urt == true) {
		$("#studentContent")
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var url = jQuery.url.getChatURL("/group/ajaxDeleteUserInquiry.do");
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#userInquiryList").html(html);
			}
		});
	}
}
</script>
<style>
div.odd {
	background-color: #F5F7F7;
	border-bottom: 1px solid #CCCCCC;
	border-top: 1px solid #CCCCCC;
	height: auto;
	width: 500px;
	padding: 10px 0px 0px 20px;
}

div.even {
	padding: 10px 0px 0px 20px;
}
</style>

<%@ include file="/common/taglibs.jsp"%>
<div class="grid_12">
	<s:url id="urlComposeScrapMsgLink" action="ajaxDoComposeScrapMessage" />
	<sj:a href="%{urlComposeScrapMsgLink}" targets="stepSendMsg"
		indicator="indicator1" onClickTopics="myInboxDetails"
		cssClass="linkRight">Compose Messages</sj:a>
	<fieldset>
		<s:if
			test="%{scrapMessagesList != null && !scrapMessagesList.isEmpty()}">
			
				 <div style="float: right; margin-left: 125px">
					<s:url id="doSeeMyBirthdayWish" action="ajaxDoReplayScrapMessages"
						includeParams="all" escapeAmp="false">
						<s:param value="id" name="id"></s:param>
					</s:url>
					 
				</div>
				 <div class="grid_13 th">
				 <div class="grid_2">
				 Sent To
				 </div>
				 <div class="grid_6">
				 Message
				 </div>
				 <div class="grid_3">
				 Date
				 </div>
				  <div class="grid_1">
				 Remove
				 </div>
				 </div>
				 <s:iterator value="scrapMessagesList" status="status">
				 <div class="grid_13 row">
				 <div class="grid_2">
				<s:property value="receiverAccount.person.fullPersonName" />
				 </div>
				 <div class="grid_6">
					<s:property value="scrapDescription" />
				 </div>
				 <div class="grid_3">
				 <s:property value="scrapMessageDateStr"/>
				 </div>
				  <div class="grid_1">
				<div id="removeMessage<s:property value='id' />" class="innerTable loaded">
					 		<s:url id="removeMessage" action="ajaxDeleteMessage"
							includeParams="all" escapeAmp="false">
							<s:param name="id" value="id"></s:param> 
						</s:url>
						<s:div cssClass="close emsRemove" id='%{removeMessage}' theme="simple" title="Delete this Message" ></s:div>
						</div>	
				 </div>
				 </div>
				<div style="display: none"
					id='viewStudentMessages<s:property value="id"/>' class='load'>
				</div>
			</s:iterator>
		</s:if>
		<s:else>
			Currently there are no messages.
		</s:else>
	</fieldset>
</div>


<style type="text/css">
.active {
	color: #0033CC;
	text-decoration: none;
}

.inactive {
	font-weight: bold;
	text-decoration: underline;
	cursor: default;
}

.paginator {
	text-align: center;
	color: #FFF;
}
</style>

<script type="text/javascript">

changePageTitle('Inbox');
$('#studentInbox').addClass('current');
document.title = 'Eazy School | Inbox Details';
$.subscribe('ajaxStudentMessages', function(event, data) {
	if ($('#' + data.id).is(":hidden")) {
		$('#' + data.id).show();
	} else {
		$('#' + data.id).hide();
	}
});

$(document).ready(function() {
	$("a.newMessage").fancybox( {
		'width' : '45%',
		'height' : '66%',
		'autoScale' : false,
		'transitionIn' : 'none',
		'transitionOut' : 'none',
		'autoDimensions' : false,
		'showCloseButton' : true

	});
});
</script>
<script type="text/javascript">
$('#staffInbox').addClass('current');
$(document).ready(function() {
	$.subscribe('staffBirthDayDetails', function(event, data) {
		$("#myBirthDayHome").show();
		$("#inboxHome").hide();
	})
});
$(document).ready(function() {
	$.subscribe('myInboxDetails', function(event, data) {
		$("#myBirthDayHome").hide();
		$("#inboxHome").show();
	})
});
</script>



