<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<div id="kBankContent">
<s:url id="urlMyFavouriteLink" action="ajaxGetKhanPlayList" namespace="/admin" />
	<sj:a href="%{urlMyFavouriteLink}" targets="kBankContent"
		indicator="indicator">
		 <h4><s:property value="plSubjectName" /></h4>
	</sj:a>
<div class="spaceDiv"></div>
<s:if test="%{objectList != null && !objectList.isEmpty()}">
<table
	class="table table-striped table-bordered table-hover table-full-width"
	id="sample_2">
		<s:iterator value="objectList">
			<tr> 
			<td>
				<s:url id="playSubTopics" action="ajaxGetKhanSubTopics" namespace="/common"
						includeParams="all" escapeAmp="false">
						<s:param name="subTopic" value="%{title}"></s:param>
						<s:param name="subTopicId" value="%{id}"></s:param>
						<s:param name="subjectName" value="%{subjectName}"></s:param>
					</s:url>
					<sj:a href="%{playSubTopics}" targets="myKVideosContent">
						<s:property value="title" />
					</sj:a>
				</td>
		</tr>
		</s:iterator>
	<s:else>
		<div class="alert alert-info">
			Currently there are no available videos for this section.
		</div>
	</s:else>
</table>
</s:if>
<s:else>
		Currently there are no topics for this Subject.
	</s:else>
	</div>
<script type="text/javascript">
TableAdvanced.init();
UIExtendedModals.init();
/*function PopupKViedeosubTopicsDetails(title,id,subject) {
alert('hi');
	var url = jQuery.url.getChatURL("common/ajaxGetKhanSubTopics.do");
	$('#PopupSubTopicsDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
		url : url,
		cache : false,
		data : "subTopic=" + title +  "subTopicId=" + id + "subjectName=" + subject,
		success : function(html) {
			$("#PopupSubTopicsDiv").html(html);
		}
	});
}*/
</script>
	