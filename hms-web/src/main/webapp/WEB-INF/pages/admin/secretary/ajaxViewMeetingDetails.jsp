<%@ include file="/common/taglibs.jsp"%>
	<s:if test="%{objectList != null && !objectList.isEmpty()}">
		<table
			class="table table-striped table-bordered table-hover table-full-width"
			id="sample_2">
			<thead>
				<tr>
					<th>School Name</th>
					<th>Agenda</th>
					<th>venue</th>
					<th>Date- Time</th>
					<th>Meeting Minutes</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="objectList">
					<tr>
						<td><s:property value="organization" /></td>
						<td><s:property value="meetingAgenda" /></td>
						<td><s:property value="place" /></td>
						<td><s:property value="meetingDateTime" /></td>
						<td>
							<a data-toggle="modal" title="Audio" href="#audioDiv" onclick="javascript:PopupViewAudioDetials(<s:property value="%{meetingDetailsId}" />,'audio');"> <i class="fa fa-volume-up"></i></a>&nbsp;&nbsp; |&nbsp;&nbsp;
							<a data-toggle="modal"  title="Video" href="#audioDiv" onclick="javascript:PopupViewAudioDetials(<s:property value="%{meetingDetailsId}" />,'video');"> <i class="fa fa-video-camera"></i></a>&nbsp;&nbsp; |&nbsp;&nbsp;
							<a data-toggle="modal"  title="Documents" href="#audioDiv" onclick="javascript:PopupViewAudioDetials(<s:property value="%{meetingDetailsId}" />,'application');"><i class="fa fa-book"></i></a>
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</s:if>
	<s:else>
		<div class="alert alert-info">No Meeting Details found.</div>
	</s:else>
	<div id="audioDiv"></div>
	<script type="text/javascript">
	function PopupViewAudioDetials(id,type) {
	var pars = "tempId1=" + id + "&anyId=" + type;
			var url = jQuery.url.getChatURL("/admin/ajaxPlayOrViewMeetingDetails.do");
			$('#responsive').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			$.ajax( {
					url : url,
					cache : false,
					data : pars,
					success : function(html) {
						$("#audioDiv").html(html);
					}
				});
			}
 </script>
