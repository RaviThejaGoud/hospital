<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/group/paginator.js"></script>
<script type="text/javascript">
changePageTitle('View Teacher Messages');
$(function(){ $("#resultsPage").pagination(); });

function viewReadMoreMin(id){
	 $("#resultsDiv").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	  var pars="id=" + id;
	  var url = jQuery.url.getChatURL("/student/ajaxViewReadMoreTeacherMessagesForParent.do");
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
<s:if test="%{messagesList == null || messagesList.isEmpty()}">
	<div style="padding: 20px">
		Currently there are no Teacher Messages.
	</div>
</s:if>
<s:elseif test="%{messagesList != null && !messagesList.isEmpty()}">
	<div id="resultsPage">
		<s:iterator value="messagesList" status="status">
			<s:if test="#status.index % 2 == 1 ">
				<div class="odd">
			</s:if>
			<s:else>
				<div class="even">
			</s:else>
			<s:if test="%{status != 'FM'}">
			<div style="margin-bottom: 15px">
				<h3>
					<s:property value="title" />
				</h3>
				Posted on
				<s:property value="messageDateStr" />
				by <b><s:property value="fullPersonName" /></b>
			</div>
			<!--<div >
			Email&nbsp;&nbsp;&nbsp;:&nbsp;<s:property value="emailAddress"/><br/>
			Phone&nbsp;:&nbsp;<s:property value="phoneNumber"/>
			</div><br/>
			-->
			<div id="minutesDesc<s:property value="id"/>" style="display: none">
				<s:property value="shortMessageDesc" />
			</div>
			<p>
				<script language="JavaScript" type="text/javascript">
						shortDescWithLink("#minutesDesc<s:property value="id"/>","javascript:viewReadMoreMin(<s:property value="id"/>);",50);
		  			</script>
			</p>
			<tr>
			<td style="width: 30px;text-align: left;">
				<s:url id="doReplyMessage" 
					action="ajaxDoReplayMessage" includeParams="all"
					escapeAmp="false">
					<s:param name="id" value="{id}" />
				</s:url>
				<sj:a href="%{doReplyMessage}"
					onBeforeTopics="cleanOpenRows" onCompleteTopics="doInitEditGroupType"
					indicator="indicator"
					targets="editStaffEvent%{id}">
					Reply
				</sj:a>
			</td>
			</tr>
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
			</s:else>
			<table>
			<tr id="editStaffEvent<s:property value='id' />" ></tr>
			</table>
	</div>
</s:iterator>
	</div>
</s:elseif>

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



