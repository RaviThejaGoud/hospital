<%@ include file="/common/taglibs.jsp"%>
<div id="roomsContentDiv">
<%@ include file="/common/messages.jsp"%>
	<s:if test="%{floor.roomList!= null && !floor.roomList.isEmpty()}">
		<div class="spaceDiv"></div>
		<table
			class="table table-striped table-bordered table-hover table-full-width"
			id="sample_2">
			<thead>
				<tr>
					<th>
						Room Name
					</th>
					<th>
						Room Type
					</th>
					<th>
						No.of Beds
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
				<s:iterator value="floor.roomList">
					<tr>
						<td>
							<s:property value="roomName" />
						</td>
						<td>
							<s:property value="roomType" />
						</td>
						<td>
							<s:property value="capacity" />
						</td>
						<td>
							<s:if test='%{#session.previousYear == "N"}'>
								<a data-toggle="modal"  href="#editRoomSettingsDiv"  class="btn btn-xs purple"
									onclick="javascript:PopupEditRoomSettings(<s:property value="%{id}" />);"><i class="fa fa-edit"></i>Edit
								</a>
							</s:if>
						</td>
						<td>
							<s:url id="removeRoom" action="ajaxDeleteRoom"
								includeParams="all" escapeAmp="false" namespace="/hostel">
								<s:param name="room.id" value="id"></s:param>
							</s:url>
							<s:div  cssClass="btn btn-xs red"
								id='%{removeRoom}' theme="simple"
								onclick="javascript:confirmDialogWithTarget(this,'roomsContentDiv');"
								title="Delete this Room"><i class="fa fa-times"></i> Delete</s:div>
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			You have not created rooms, Creating rooms is simple process and
			system would guide you.
		</div>
	</s:else>
</div>
	<div id="editRoomSettingsDiv"></div>
<script type="text/javascript">
TableAdvanced.init();
UIExtendedModals.init();
function PopupEditRoomSettings(id) {
	var url = jQuery.url.getChatURL("/hostel/ajaxDoAddRoomSettings.do");
	$('#editRoomSettingsDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
			url : url,
			cache : false,
			data : "room.id="+id,
			success : function(html) {
				$("#editRoomSettingsDiv").html(html);
			}
		});
	}
</script>
