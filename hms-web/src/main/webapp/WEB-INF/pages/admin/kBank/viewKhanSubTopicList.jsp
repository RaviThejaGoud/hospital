<%@ include file="/common/taglibs.jsp"%>
<h4 class="pageTitle bold">
	<s:property value="plSubjectName" />
	---->
	<s:property value="plSubTopic" />
</h4>
<div class="spaceDiv"></div>
<s:if test="%{objectList != null && !objectList.isEmpty()}">
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample">
		<s:iterator value="objectList">
			<tr>
				<td>
					<s:url id="playSubTopics" action="ajaxGetKhanSubVideoTopics"
						includeParams="all" escapeAmp="false">
						<s:param name="videoTopicId" value="%{id}"></s:param>
						<s:param name="subjectName" value="%{subjectName}"></s:param>
						<s:param name="subTopic" value="%{subTopic}"></s:param>
						<s:param name="title" value="%{title}"></s:param>
					</s:url>
					<sj:a href="%{playSubTopics}" targets="kVideosContent"
						onBeforeTopics="cleanOpenRows" onCompleteTopics=""
						indicator="indicator1">
						<s:property value="subTopic" />
					</sj:a>
				</td>
			</tr>
		</s:iterator>
	</table>
</s:if>
<s:else>
	<s:if
		test="%{knowledgeBankList != null && !knowledgeBankList.isEmpty()}">
		<table
			class="table table-striped table-bordered table-hover table-full-width" id="sample_3">
			<s:iterator value="knowledgeBankList">
				<tr>
					<td>
					<a data-toggle="modal"  href="#PlaySelectedVideoDiv"  
								onclick="javascript:writeNewVideo(<s:property value="%{id}" />);"><s:property
								value="title" /> 
							</a>
					</td>
				</tr>
			</s:iterator>
		</table>
	</s:if>
</s:else>
<div id="PlaySelectedVideoDiv"></div>
<script type="text/javascript">
TableAdvanced.init();
UIExtendedModals.init();
	function writeNewVideo(id) {
		var url = jQuery.url.getChatURL("/admin/ajaxPlaySelectedVideo.do");
		$('#PlaySelectedVideoDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
				url : url,
				cache : false,
				data : "id=" + id,
				success : function(html) {
					$("#PlaySelectedVideoDiv").html(html);
				}
			});
		} 
</script>


