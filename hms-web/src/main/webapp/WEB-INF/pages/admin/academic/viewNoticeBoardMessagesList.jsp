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
	  var url = jQuery.url.getChatURL("/staff/ajaxViewReadMoreTeacherMessagesForParent.do");
			$.ajax( {
			url : url,
			cache : false,
			data: pars,
			success : function(html) {	
				$("#myMessageContent").html(html);
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
	 $.subscribe('doEditStaffEvent', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});
</script>
<style>
div.odd {
	background-color: #F5F7F7;
	border-bottom: 1px solid #CCCCCC;
	border-top: 1px solid #CCCCCC;
	height: auto;
	width: 700px;
	padding: 10px 0px 0px 20px;
}

div.even {
	padding: 10px 0px 0px 20px;
}

</style>
<s:if test="%{noticeBoardMessagesList == null || noticeBoardMessagesList.isEmpty()}">
	<div style="padding: 20px">
		Currently there are no Messages.
	</div>
</s:if>
<s:elseif test="%{noticeBoardMessagesList != null && !noticeBoardMessagesList.isEmpty()}">
	<div id="resultsPage" >
		<s:iterator value="noticeBoardMessagesList" status="status">
			<s:if test="#status.index % 2 == 1 ">
				<div class="odd">
			</s:if>
			<s:else>
				<div class="even">
			</s:else>
			<div style="margin-bottom: 15px">
				<h3>
					<s:property value="title" />
				</h3>
				Posted on
				<s:property value="messageDateStr" />
				by <b><s:property value="createdBy" /></b>
			</div>
			<!--<div >
			Email&nbsp;&nbsp;&nbsp;:&nbsp;<s:property value="emailAddress"/><br/>
			Phone&nbsp;:&nbsp;<s:property value="phoneNumber"/>
			</div><br/>
			-->
			<div>
				<s:property value="messageDescription" />
			</div>
			<!--<tr>
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
			--><table>
			<tr id="editStaffEvent<s:property value='id' />" ></tr>
			</table>
	</div>
	</s:iterator>
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



