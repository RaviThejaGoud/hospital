<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div data-width="760" class="modal fade modal-overflow in" style="display: block; width: 760px; margin-left: -379px; margin-top: 150px;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
		<h4 class="modal-title">view School Timings</h4>
	</div>
	<div class="modal-body">
<div class="grid_12 omega" id="kBankContent">
	<div class="block_head" id="topMenu">
		<h4 class="pageTitle">
			View Knowledge Videos
		</h4>
	</div>
	<div class="block_content">
		<b> 
		<s:url id="playSelectedVideo" action="ajaxGetKhanTopics"
				includeParams="all" escapeAmp="false">
				<s:param name="plSubjectName" value="%{plSubjectName}"></s:param>
			</s:url> <sj:a href="%{playSelectedVideo}" targets="kVideosContent"
				onBeforeTopics="cleanOpenRows" onCompleteTopics=""
				indicator="indicator1">
				<s:property value="plSubjectName" />
			</sj:a>&nbsp;>&nbsp;
			
			<s:url id="playSubTopics" action="ajaxGetKhanSubTopics"
				includeParams="all" escapeAmp="false">
				<s:param name="videoTopicId" value="%{selectedId}"></s:param>
				<s:param name="plSubjectName" value="%{plSubjectName}"></s:param>
				<s:param name="subTopic" value="%{subTopic}"></s:param>
				<s:param name="plTitle" value="%{plTitle}"></s:param>
			</s:url> <sj:a href="%{playSubTopics}" targets="kVideosContent"
				onBeforeTopics="cleanOpenRows" onCompleteTopics=""
				indicator="indicator1">
				<s:property value="plTitle" />
			</sj:a>
			&nbsp;>&nbsp;<s:property value="plSubTopic" />
		</b>
		<s:if test="%{objectList != null && !objectList.isEmpty()}">
			<div style="padding-top: 1px">
				<ul  id="resultsPage">
					<s:iterator value="objectList">
						<li>
							<a
								href="<c:url value='/admin/ajaxPlaySelectedVideo.do' />?id=<s:property value="id"/>"
								class="playVideo video-link" title="<s:property value="title"/>"
								onclick="javascript:writeNewVideo('<s:property value="playListVideo.youtubeId"/>')"><s:property
									value="title" /> </a>
						</li>
					</s:iterator>
				</ul>
			</div>
		</s:if>
	<s:else>
		Currently there are no topics for this Subject.
	</s:else>
	</div>
</div>
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
<script type="text/javascript">
$("a.playVideo").fancybox( {
	'width' : '51.5%',
	'height' : '72%',
	'autoScale' : false,
	'transitionIn' : 'none',
	'transitionOut' : 'none',
	'autoDimensions' : false,
	'showCloseButton' : true
});
</script>