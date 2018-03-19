<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/group/paginator.js"></script>
<script type="text/javascript">
changePageTitle('View Teacher Messages');
$(function(){ $("#resultsPage").pagination(); });
function viewReadMoreMin(id){
	 $("#resultsDiv").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	  var pars="id=" + id;
	  var url = jQuery.url.getChatURL("/student/ajaxViewReadMoreBirthDayMessagesForStudent.do");
			$.ajax( {
			url : url,
			cache : false,
			data: pars,
			success : function(html) {	
				$("#myMessagesContent").html(html);
				$('#formattedMsg').html($('#unFormattedMsg').text());
			}
			
		});	
	}
	function getAjaxRemoveUserInquiry(id){
		var pars="id=" + id;
		var urt = confirm("Are you sure you want to delete this topic?");
		if (urt==true)
		{
			$("#studentContent").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			var url = jQuery.url.getChatURL("/group/ajaxDeleteUserInquiry.do");
			$.ajax( {
			url : url,
			cache : false,
			data: pars,
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
<div class="block grid_12">
<div class="block_head">
<h2>My Messages</h2>
</div>
<div class="block_content"  id="myMessagesContent">
<s:if test="%{studentMessagesList == null || studentMessagesList.isEmpty()}">
	<div style="padding: 20px">
		Currently there are no  Messages.
	</div>
</s:if>
<s:elseif test="%{studentMessagesList != null && !studentMessagesList.isEmpty()}">
	  <div>
		<s:iterator value="studentMessagesList" status="status">
			<s:if test="%{status != 'FM'}">
				<div>
					<s:if test='%{isNewMessage == "UR"}'>
					<img style="" src="../images/new.gif">
					</s:if>
						<s:property value="createdBy" />
					<div style="float: right;">
					 <s:url id="doSeeMyBirthdayWish"
							action="ajaxViewReadMoreBirthDayMessagesForStudent"
							includeParams="all" escapeAmp="false">
							<s:param name="id" value="{id}" />
							<s:param name="receiverAccountId" value="{receiverAccountId}" />
							<s:param name="senderAccountId" value="{senderAccountId}" />
						</s:url>
							<sj:a href="%{doSeeMyBirthdayWish}" indicator="indicator"
							targets="studentContent">
								<s:property value="shortMessageTitle"/>
						</sj:a>
				    </div>
					</div>
				<div>
					<s:property value="messageDateStr" />
				</div>
				<!--<div style="display: none"
					id='viewStudentMessages<s:property value="id"/>' class='load'>
				</div>
				--><div style="border-bottom: 1px solid #757575"></div>
				<div>&nbsp;</div>
			</s:if>
			<s:else>
			<div style="margin-bottom: 15px">
				<h3>
					<s:property value="messageDescription" />
				</h3>
				Posted on
				<s:property value="messageDateStr" />
				by <b><s:property value="createdBy" /></b>
			</div>
			<div>
				<s:property value="title" /> of Rs.<s:property value="feeAmountForStudent" /> for &quot;<s:property value="feeOfStudentName" />&quot;
					 is due date on <b><s:property value="feeDueDateForStudentStr" /></b>
		   </div>
		   <div style="border-bottom: 1px solid #757575"></div>
				<div>&nbsp;</div>
		  </s:else><!--
		  <s:if test="%{status !='ES'}">
				<div>
					<s:if test='%{isNewMessage == "UR"}'>
					<img style="" src="../images/new.gif">
					</s:if>
						<s:property value="createdBy" />
					<div style="float: right;">
					 <s:url id="doSeeMyBirthdayWish"
							action="ajaxViewReadMoreBirthDayMessagesForStudent"
							includeParams="all" escapeAmp="false">
							<s:param name="id" value="{id}" />
							<s:param name="receiverAccountId" value="{receiverAccountId}" />
							<s:param name="senderAccountId" value="{senderAccountId}" />
						</s:url>
							<sj:a href="%{doSeeMyBirthdayWish}" indicator="indicator"
							targets="studentContent">
								<s:property value="shortMessageTitle"/>
						</sj:a>
				    </div>
					</div>
				<div>
					<s:property value="messageDateStr" />
				</div>
				<div style="display: none"
					id='viewStudentMessages<s:property value="id"/>' class='load'>
				</div>
				<div style="border-bottom: 1px solid #757575"></div>
				<div>&nbsp;</div>
			</s:if>
			--><s:else>
			<div>
				<s:property value="title" /> <s:property value="feeAmountForStudent" /> for &quot;<s:property value="feeOfStudentName" />&quot;
					  <b><s:property value="feeDueDateForStudentStr" /></b>
		   </div>
			<div style="margin-bottom: 15px">
				<h3>
					<s:property value="messageDescription" />
				</h3>
				Posted on
				<s:property value="messageDateStr" />
				by <b><s:property value="createdBy" /></b>
			</div>
			
		   <div style="border-bottom: 1px solid #757575"></div>
				<div>&nbsp;</div>
		  </s:else>
			</s:iterator>
	</div>
</s:elseif>
</div>
</div>
<style type="text/css">
.active
{
color:#0033CC;
text-decoration:none;
}
.inactive
{
font-weight: bold;
text-decoration: underline; 
cursor:default;
}
.paginator
{
text-align: center;
color: #FFF;
}
</style>

<script type="text/javascript">
	document.title = 'SMS | View Student Messages';
	$.subscribe('ajaxStudentMessages', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});
</script>

