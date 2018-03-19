<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{roomList!= null && !roomList.isEmpty()}">
	<div class="form-group">
		<label class="control-label col-md-3">
			<span class="required">*</span>Select Room :
		</label>
		<div class="col-md-6">
			<s:select list="roomList" listKey="id" theme="simple"
				id="roomId" headerValue="- Select -" headerKey="S"
				cssClass="select2_category form-control required" listValue="roomName" name="hostelStudents.roomId" onchange="javascript:getBedDetailsByRoomId(this.value);">
			</s:select>
		</div>
	</div>
</s:if>
<s:else>
	<div class="alert alert-info">
		Floor doesn't contains Rooms.
	</div>
</s:else>

<script>
function getBedDetailsByRoomId(id) { 
	if(isNonEmpty(id)){
		$('#resultsDiv6').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "roomId=" + id;
		$.ajax( {
			url : jQuery.url.getChatURL("/hostel/ajaxGetBedDetailsByRoomId.do"),
			cache : false,
			data : pars,
			success : function(response) {
				$('#resultsDiv6').html(response);
				document.getElementById('resultsDiv5').style.display = "none";  
				document.getElementById('resultsDiv6').style.display = "block";  
			}
		});
	}else
		$('#resultsDiv6').html('<div class="thb" style="margin-top:10px;">Please select Building & Floor.</div>');
} 
</script>
