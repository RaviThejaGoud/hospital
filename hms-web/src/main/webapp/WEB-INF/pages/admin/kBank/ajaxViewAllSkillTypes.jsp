<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<s:if test="%{objectList != null && !objectList.isEmpty()}">
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th>
					Skill Type Name
				</th>
				<th>
					Edit
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="objectList">
				<tr>
					<td>
						<s:property value="skillTypeName" />
					</td>
					<td>
						<a data-toggle="modal" href="#popupEditSkillTypeDiv"
							class="btn btn-xs purple"
							onclick="javascript:PopupEditSkillType(<s:property value="%{id}" />);"><i
							class="fa fa-edit"></i>Edit </a>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no SkillTypeNames.
	</div>
</s:else>
<div id="popupEditSkillTypeDiv"></div>
<script type="text/javascript">
$(document).ready(function() {
	TableAdvanced.init();
});
function PopupEditSkillType(id) {
	var url = jQuery.url.getChatURL("/admin/ajaxEditSkillTypeList.do");
	$('#popupEditSkillTypeDiv')
			.html(
					'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
		url : url,
		cache : false,
		data : "commonTypeId=" + id,
		success : function(html) {
			$("#popupEditSkillTypeDiv").html(html);
		}
	});
}
</script>