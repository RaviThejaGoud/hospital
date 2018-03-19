<%@ include file="/common/taglibs.jsp"%>
<div id="castNamesDiv">
	<div align="right">
		<s:url id="doAddNewSubCasteType" action="ajaxDoAddNewSubCasteType"
			includeParams="all" escapeAmp="false" namespace="/admin">
			<s:param name="quizId" value="%{quizId}"></s:param>
			<s:param name="subCastSettings.id" value="0"></s:param>
		</s:url>
		<sj:a href="%{doAddNewSubCasteType}" cssClass="btn green"
			onCompleteTopics="doInitAddSubCasteType" indicator="indicatorLeader"
			targets="addBlockRacks%{quizId}"><i class="fa fa-plus"></i>
			<b>Add Caste</b> </sj:a>
			&nbsp;&nbsp;
		<s:url id="urlGetCastSettings" action="ajaxCastSettingsHome"
			namespace="/admin" />
		<sj:a href="%{urlGetCastSettings}" targets="mainContentDiv"
			cssClass="btn default"><i class="fa fa-mail-reply"></i>
			Back</sj:a>
	</div>
	<div id="addBlockRacks<s:property value='quizId' />"
		style="display: none;">
	</div>
	<div id="hideRackSuccess">
		<jsp:include page="/common/messages.jsp"></jsp:include>
	</div>
	<div class="spaceDiv"></div>
	<div class="spaceDiv"></div>
	<s:if test="%{tempList != null && !tempList.isEmpty()}">
		<table
			class="table table-striped table-bordered table-hover table-full-width"
			id="sample_3">
			<thead>
				<tr>
					<th>
						Caste Name
					</th>
					<th>
						Edit
					</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="tempList">
					<tr>
						<td>
							<s:property value="subCastName" />
						</td>
						<td>
							<a data-toggle="modal" href="#popupSubCastDetailsDiv"
								class="btn btn-xs purple"
								onclick="javascript:popupSubCastDetails(<s:property value="id" />,<s:property value="quizId" />);"><i
								class="fa fa-edit"></i>Edit </a>
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			Currently there are no castes for this community.
		</div>
	</s:else>
</div>
<div id="popupSubCastDetailsDiv"></div>
<script type="text/javascript">
$(document).ready(function(){
	TableAdvanced.init();
});
function popupSubCastDetails(id,quizId) {
	var url = jQuery.url.getChatURL("/admin/ajaxDoAddNewSubCasteType.do");
	$('#popupSubCastDetailsDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
			url : url,
			cache : false,
			data : "subCastSettings.id=" + id +"&quizId=" +quizId,
			success : function(html) {
				$("#popupSubCastDetailsDiv").html(html);
			}
		});
	}
/*function toggleActivitypesCont(activityId){
	$('#viewAllRacks'+activityId).hide();
}*/
$.subscribe('doInitAddSubCasteType', function(event, data) {
	var rowObj = $('#' + data.id);
	rowObj.show();
});
</script>