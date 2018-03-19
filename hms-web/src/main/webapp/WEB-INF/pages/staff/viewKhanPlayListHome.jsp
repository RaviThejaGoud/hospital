<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<div class="grid_12 omega block">
	<div class="block_head" id="topMenu">
		<h2>
			My Kvideos
		</h2>
		<div id="topMenu">
			<ul>
				<li>
					<s:url id="urlSearchKVideosLink" action="ajaxDoSearchKVideos" />
					<sj:a href="%{urlSearchKVideosLink}" targets="myKVideosContent"
						indicator="indicator">Search Knowledge Videos</sj:a>
				</li>
				<!--<li>
					<s:url id="urlLeaveLink" action="ajaxDoGetTeacherMessagesForParent" />
					<sj:a href="%{urlLeaveLink}" targets="myMessagesContent"
						indicator="indicator">Teacher Messages</sj:a>
				</li>
			--></ul>
		</div>
	</div>
	<div class="block_content" id="myKVideosContent">
		<b> Select Video Type: </b><br/> 
			<s:select id="sectionId" list="presentList" listKey="id" onchange="javascript:getPlayListVideosList(this.value);"
				listValue="title" label="Select Video Type" cssClass="required" theme="simple"
				required="true" headerKey="" headerValue="- Select -" name="anyId" />
				
				<div id="kvideoResponseDiv"></div>
	</div>
</div>
<script type="text/javascript">

	changePageTitle('Knowledge Videos');

	$(document).ready(function() {
		var validator;
		$.subscribe('formValidationForLeavesForStudent', function(event, data) {
			if ($('#addLeaveReportForStudent').valid())
				return true;
			else
				return false;
		});

	});
	
	function getPlayListVideosList(playListId)
	{
		var pars = "playListId=" + playListId;
		$("#kvideoResponseDiv").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var url = jQuery.url.getChatURL("/staff/ajaxGetPlayListVideosList.do");
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#kvideoResponseDiv").html(html);
			}
		});
	}
	
</script>