<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div class="grid_14 omega block" id="kVideosContent">
	<div class="block_head">
		<h2>
			My kVideos
		</h2>
		<div id="topMenu">
			<ul>
				<li>
					<s:url id="urlSearchKVideosLink" action="ajaxDoSearchKVideos" />
					<sj:a href="%{urlSearchKVideosLink}" targets="myKVideosContent"
						indicator="indicator">Search Knowledge Videos</sj:a>
				</li>
			</ul>
		</div>
	</div>
	<div class="block_content" id="myKVideosContent">
		<div class="grid_13">
			<p>
				<font size="4" face="verdana" color="blue">Browse our library
					of over 400 educational videos...</font>
			</p>
			<s:if test="%{subjectsList != null && !subjectsList.isEmpty()}">
				<s:iterator value="subjectsList">
					<s:if test='%{subjectName != null && subjectName!=""}'>
						<div class='grid_3'>
							<s:url id="playSelectedVideo" action="ajaxGetKhanTopics"
								includeParams="all" escapeAmp="false">
								<s:param name="subjectName" value="%{subjectName}"></s:param>
							</s:url>
							<sj:a href="%{playSelectedVideo}" targets="kVideosContent"
								onBeforeTopics="cleanOpenRows" onCompleteTopics=""
								indicator="indicator1">
								<s:property value="subjectName" />
							</sj:a>
						</div>
					</s:if>
				</s:iterator>
			</s:if>
			<s:else>
		kVideos not prepared.
	</s:else>
		</div>
		<div id="kBankContent"></div>
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

function getPlayListVideosList(playListId) {
	var pars = "playListId=" + playListId;
	$("#kvideoResponseDiv")
			.html(
					'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	var url = jQuery.url.getChatURL("/admin/ajaxGetPlayListVideosList.do");
	$.ajax( {
		url : url,
		cache : false,
		data : pars,
		success : function(html) {
			$("#kvideoResponseDiv").html(html);
		}
	});
}
/*function getKhanTopicsList(subjectName)
 {
 alert(subjectName);
 var pars = "subjectName=" + subjectName;
 $("#name").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
 var url = jQuery.url.getChatURL("/kBank/ajaxGetKhanTopics.do");
 $.ajax( {
 url : url,
 cache : false,
 data : pars,
 success : function(html) {
 $("#name").html(html);
 }
 });
 }*/
</script>
<script type="text/javascript">
$(document).ready(function() {
	$(".khanTopicsDetails").click(function()
	{
		$(".khanTopicsDetailsBody").slideToggle(600);
	});
	$(".khanTopicsDetailsBody").hide();
});
</script>