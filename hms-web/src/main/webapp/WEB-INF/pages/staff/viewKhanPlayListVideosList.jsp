<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<jsp:include page="/common/messages.jsp"></jsp:include>
<br/>
<div>
	<s:if test="%{playList != null}">
		<b> <s:property value="playList.title" /> </b>
	</s:if>
</div>
<s:if
	test="%{playListVideoSet != null && !playListVideoSet.isEmpty()}">
	<div style="padding-top: 1px">
		<ul id="resultsPage">
			<s:iterator value="playListVideoSet">
				<li>
					<a href="<c:url value='/staff/ajaxPlaySelectedVideo.do' />?id=<s:property value="id"/>" class="playVideo video-link" title="<s:property value="title"/>" onclick="javascript:writeNewVideo('<s:property value="playListVideo.youtubeId"/>')" ><s:property
							value="title" />
					</a>

				</li>
			</s:iterator>
		</ul>
	</div>
</s:if>
<s:else>
	<br />
	Currently there are no available videos for this section.
</s:else>

<br />

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
.video-link {
    background: url("../images/list-item-black.png") no-repeat scroll 0 3px transparent;
    font-weight: normal;
    padding: 0 2px 0 13px;
}
</style>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/subscription/paginator.js">
</script>
<script type="text/javascript">

 $("a.playVideo").fancybox({
	  		 'width'  : '51.5%',
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
