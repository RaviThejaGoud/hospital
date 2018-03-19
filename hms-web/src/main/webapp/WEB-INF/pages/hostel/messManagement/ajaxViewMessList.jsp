<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<s:if test="%{objectList!= null && !objectList.isEmpty()}">
	<p>
		<span class="label label-danger"> NOTE : </span>&nbsp;You can
		edit/update existing mess settings by clicking on edit pen icon in
		each row at right side.
	</p>
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th>
					Mess Name
				</th>
				<th>
					Description
				</th>
				<th>
					Edit
				</th>
				<th>
					Delete
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="objectList">
				<tr>
					<td>
						<s:property value="messName" />
					</td>
					<td>
						<s:property value="messDescription" />
					</td>
					<td>
						<s:url id="editMessDetails" action="ajaxDoEditMessDetails" includeParams="all" escapeAmp="false" namespace="/hostel">
							<s:param name="tempId" value="%{id}" />
						</s:url>
						<sj:a href="%{editMessDetails}" targets="messSettingContent"  cssClass="btn btn-xs purple" title="Edit"><i class="fa fa-edit"></i>Edit
						</sj:a>
					</td>
					<td>
						<s:if
							test='%{(#session.academicYear == null || #session.academicYear.isEmpty()) || (#session.previousYear=="N")}'>
							<s:url id="removeMessDetails" action="ajaxDeleteMess"
								includeParams="all" escapeAmp="false" namespace="/hostel">
								<s:param name="tempId" value="%{id}"></s:param>
							</s:url>
							<s:div cssClass="btn btn-xs red" id='%{removeMessDetails}' theme="simple"
								onclick="javascript:confirmDialogWithTarget(this,'mainContentDiv');"
								title="Delete this mess">
								<i class="fa fa-times"></i>Delete</s:div>
						</s:if>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no mess.
	</div>
</s:else>

<div id="editBuildingSettingsDiv"></div>
<script type="text/javascript">
$(document).ready(function() {
	TableAdvanced.init();
	UIExtendedModals.init();
});

function getBedDetailsByRoom(id, type) {
	if(isNonEmpty(id)){
		$('#hostelSettingContent').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "tempId1=" + id + "&anyTitle=" + type;
		$.ajax( {
			url : jQuery.url.getChatURL("/hostel/ajaxLoadManageInfoByBeds.do"),
			cache : false,
			data : pars,
			success : function(response) {
				$('#hostelSettingContent').html(response);
			}
		});
	}else
		$('#bedsContent').html('<div class="alert alert-info">Please select Floor & Room.</div>');
}

</script>