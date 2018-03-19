<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{driverList != null && !driverList.isEmpty()}">
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th>
					Name
				</th>
				<th>
					License Number
				</th>
				<th>
					License Expiry Date
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
			-->
			</tr>
		</thead>
		<tbody>
			<s:iterator value="driverList">
				<tr>
					<td>
						<s:property value="personFirstLastNameOnly" />
					</td>
					<td>
						<s:if test="%{licenseNumber != null && licenseNumber != ''}">
							<s:property value="licenseNumber" />
						</s:if>
					</td>
					<td>
						<s:if test="%{licenseExpDate != null && licenseExpDate != ''}">
							<s:property value="licenseExpDate" />
						</s:if>
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
								onclick="javascript:PopupEditDriverInformation(<s:property value="%{staffId}" />,'driverInfo');"><i
								class="fa fa-edit"></i>Edit </a>
						</td>
					</s:if>
					<!--<td>
						<a data-toggle="modal" href="#disableDriverInformation"
							class="btn btn-xs red"
							onclick="javascript:PopupDisableDriverInfo(<s:property value="%{staffId}" />,<s:property value="%{accountId}" />,'driverInfo','ROLE_DRIVER');"><i class="fa fa-times"></i>
							Inactive
						</a>
					</td>
				--></tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="spaceDiv"></div>
	<div class="spaceDiv"></div>
	<div class="alert alert-info">
		Currently there are no Drivers.
	</div>
</s:else>
<div id="driverInformation"></div>
<div id="disableDriverInformation"></div>
<script type="text/javascript">
$(document).ready(function() {
UIExtendedModals.init();
	TableAdvanced.init();
});
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