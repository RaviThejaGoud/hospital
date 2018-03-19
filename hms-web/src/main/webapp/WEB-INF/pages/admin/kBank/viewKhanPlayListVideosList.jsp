<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div>
	<s:if test="%{playList != null}">
		<b> <s:property value="playList.title" /> </b>
	</s:if>
</div>
<table
	class="table table-striped table-bordered table-hover table-full-width"
	id="sample_2">
	<s:if test="%{playListVideoSet != null && !playListVideoSet.isEmpty()}">
		<s:iterator value="playListVideoSet">
			<tr>
				<td>
					<a data-toggle="modal" href="#PopupKVideosDiv"  class="playVideo video-link" title="<s:property value="title"/>"
						onclick="javascript:PopupKViedeosDetails(<s:property value="id"/>);"><s:property value="title" />
					 </a>

					<!--<a
						href="<c:url value='/admin/ajaxPlaySelectedVideo.do' />?id=<s:property value="id"/>"
						class="playVideo video-link" title="<s:property value="title"/>"
						onclick="javascript:writeNewVideo('<s:property value="playListVideo.youtubeId"/>')"><s:property
							value="title" /> </a>
				-->
				</td>
			</tr>
		</s:iterator>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			Currently there are no available videos for this section.
		</div>
	</s:else>
</table>
<div id="PopupKVideosDiv"></div>
<script type="text/javascript">
TableAdvanced.init();
UIExtendedModals.init();
function PopupKViedeosDetails(id) {
	var url = jQuery.url.getChatURL("/admin/ajaxPlaySelectedVideo.do");
	$('#PopupKVideosDiv')
			.html(
					'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
		url : url,
		cache : false,
		data : "id=" + id,
		success : function(html) {
			$("#PopupKVideosDiv").html(html);
		}
	});
}
</script>
