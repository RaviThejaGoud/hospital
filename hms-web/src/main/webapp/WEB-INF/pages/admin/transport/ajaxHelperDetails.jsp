<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{helperList != null && !helperList.isEmpty()}">
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th>
					Name
				</th>
				<th>
					Mobile Number
				</th>
				<th>
					Vehicle - Route Details
				</th>
				<s:if test='%{#session.previousYear == "N"}'>
					<th>
						Edit
					</th>
				</s:if>
				<!--<th>
					Inactive
				</th>
			--></tr>
		</thead>
		<tbody>
			<s:iterator value="helperList">
				<tr>
					<td>
						<s:property value="personFirstLastNameOnly" />
					</td>
					<td>
						<s:if test="%{mobileNumber != null}">
							<s:property value="mobileNumber" />
						</s:if>
					</td>
					<td>
						<s:if test="%{assignedVehicleList != null && !assignedVehicleList.isEmpty()}">
							<s:iterator value="assignedVehicleList">
								<s:property/><br/>
							</s:iterator>
						</s:if>
						<s:else>
							--
						</s:else>
					</td>
					
					<s:if test='%{#session.previousYear == "N"}'>
						<td>
							<a data-toggle="modal" href="#driverInformation"
								class="btn btn-xs purple"
								onclick="javascript:PopupEditDriverInformation(<s:property value="%{staffId}" />,'helperInfo');"><i
								class="fa fa-edit"></i>Edit </a>
						</td>
					</s:if>
					<!--<td>
						<a data-toggle="modal" href="#disableDriverInformation"
							class="btn btn-xs red"
							onclick="javascript:PopupDisableDriverInfo(<s:property value="%{staffId}" />,<s:property value="%{accountId}" />,'helperInfo','ROLE_HELPER');"><i class="fa fa-times"></i>
							Inactive
						</a>
					</td>
				--></tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no Helpers.
	</div>
</s:else>
<div id="driverInformation"></div>
<div id="disableDriverInformation"></div>
<script type="text/javascript">
$(document).ready(function() {
	TableAdvanced.init();	
	UIExtendedModals.init();
});
function deleteHelperAccount(helperAccountId) {
	$.ajax( {
		url : jQuery.url.getChatURL("/admin/ajaxRemoveDriverOrHelper.do?id="
				+ helperAccountId),
		cache : false,
		success : function(html) {
			$('#helperAccount').html(html);
		}
	});
}

function PopupEditDriverInformation(staffId, tempString) {
	var url = jQuery.url.getChatURL("/admin/ajaxDoEditDriverOrHelper.do");
	$('#driverInformation')
			.html(
					'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
		url : url,
		cache : false,
		data : "staff.id=" + staffId + "&tempString=" + tempString,
		success : function(html) {
			$("#driverInformation").html(html);
		}
	});
}
function PopupDisableDriverInfo(staffId, accountId, tempString, anyTitle) {
	var url = jQuery.url.getChatURL("/staff/ajaxDoDisableStaff.do");
	$('#disableDriverInformation')
			.html(
					'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
		url : url,
		cache : false,
		data : "tempId=" + staffId + "&tempId1=" + accountId + "&tempString="
				+ tempString + "&anyTitle=" + anyTitle,
		success : function(html) {
			$("#disableDriverInformation").html(html);
		}
	});
}
</script>