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
					Item Name
				</th>
				<th>
					Measurement
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
						<s:property value="itemName" />
					</td>
					<td>
						<s:property value="measurement" />
					</td>
					<td>
						<%-- <s:if test='%{#session.previousYear == "N"}'>
							<a data-toggle="modal" href="#editBuildingSettingsDiv"
								class="btn btn-xs purple"
								onclick="javascript:PopupEditBuildingSettings(<s:property value="%{buildingId}" />);"><i
								class="fa fa-edit"></i>Edit </a>
						</s:if> --%>
						--
					</td>
					<td>
						<%-- <s:url id="removeBuilding" action="ajaxDeleteBuilding"
							includeParams="all" escapeAmp="false" namespace="/hostel">
							<s:param name="tempId2" value="%{buildingId}" />
						</s:url>
						<s:div cssClass="btn btn-xs red" id='%{removeBuilding}'
							theme="simple"
							onclick="javascript:confirmDialogWithTarget(this,'hostelSettingContent');"
							title="Delete this Building">
							<i class="fa fa-times"></i> Delete</s:div> --%>
							--
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no provision items.
	</div>
</s:else>
<script type="text/javascript">
$(document).ready(function() {
	TableAdvanced.init();
	UIExtendedModals.init();
});
changePageTitle("Merchant Details");
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