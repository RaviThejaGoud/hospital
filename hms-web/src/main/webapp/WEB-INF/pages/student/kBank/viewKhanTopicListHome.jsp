<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<div class="grid_12 omega" id="kBankContent">
	<div class="block_head" id="topMenu">
		<h2>
			View Knowledge Videos
		</h2>
	</div>
	<div class="block_content">
		<b> <s:url id="urlMyFavouriteLink" action="ajaxGetKhanPlayList" />
			<sj:a href="%{urlMyFavouriteLink}" targets="kBankContent"
				indicator="indicator">
				<s:property value="plSubjectName" />
			</sj:a></b>
		<s:if test="%{objectList != null && !objectList.isEmpty()}">
			<table class="striped" width="100%" style="margin-bottom: 0;"
				cellpadding="1" cellspacing="1">
				<s:iterator value="objectList">
					<tr class='loaded'>
						<td width="20%">
							<s:url id="playSubTopics" action="ajaxGetKhanSubTopics"
								includeParams="all" escapeAmp="false">
								<s:param name="subTopic" value="%{title}"></s:param>
								<s:param name="subTopicId" value="%{id}"></s:param>
								<s:param name="subjectName" value="%{subjectName}"></s:param>
							</s:url>
							<sj:a href="%{playSubTopics}" targets="kVideosContent"
								onBeforeTopics="cleanOpenRows" onCompleteTopics=""
								indicator="indicator1">
								<s:property value="title" />
							</sj:a>
						</td>
					</tr>
				</s:iterator>
			</table>
		</s:if>
		<s:else>
		Currently there are no topics for this Subject.
	</s:else>
	</div>
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
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/subscription/paginator.js">
</script>
<script type="text/javascript">
$("a.playVideo").fancybox( {
	'width' : '51%',
	'height' : '72%',
	'autoScale' : false,
	'transitionIn' : 'none',
	'transitionOut' : 'none',
	'autoDimensions' : false,
	'showCloseButton' : true

});

$(function() {
	$("#resultsPage").pagination();
});
</script>