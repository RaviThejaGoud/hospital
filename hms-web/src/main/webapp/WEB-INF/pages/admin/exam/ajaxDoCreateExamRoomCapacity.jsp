<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/subscription/textcounter.js">
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<div>
	<s:if test="%{(tempList != null && !tempList.isEmpty())}">
		<s:form action="ajaxDoGetExamBuildingDetails" theme="css_xhtml"
			id="updateBuildingtypes" method="post" namespace="/admin">
			<input type="hidden" id="hidInput" name="hidInput" value="" />
			<div class="grid_12" id="TextBoxDiv1">
				<div class="grid_6">
					<s:select list="tempList" listKey="id" tabindex="11"
						listValue="floorName" cssClass="required textfield"
						onchange="getRoomByFloors(this.value)" label="Select FloorName"
						required="true" name="floorName" headerKey=""
						headerValue="- Select -" requiredposition="first">
					</s:select>
				</div>
			</div>
			<div class="grid_12" id="roomNames"></div>
			<div class="grid_12">
				<s:url id="addRoomCapacity" action="ajaxDoAddExaminationSettings"
					includeParams="all" escapeAmp="false" namespace="/admin">
				</s:url>
				<sj:a href="%{addRoomCapacity}" cssClass="cancelButton"
					targets="roomForExamination" indicator="indicator">Cancel</sj:a>
				<sj:submit   targets="roomForExamination" value="Submit"
					cssClass="submit small" formIds="updateBuildingtypes"
					validate="true" indicator="indicator"
					onClickTopics="updateRoomValidation" />
			</div>
		</s:form>
	</s:if>
</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/common/jquery.alphanumeric.js">
</script>
<script type="text/javascript">
changePageTitle('Examination Rooms');
$(document).ready(function() {
$.subscribe('updateRoomValidation', function(event, data) {
                    var roomNo= '';
					var noOfSeets='';
					var jsonObj = [];
					var i=0;
					$('span.allRoomIds').each(
							function() {
							roomNo= $(this).find('.roomName').val();
								noOfSeets= $(this).find('.noOfSeets').val();
								roomId= $(this).find('span.roomId').attr('id');
								if(isNonEmpty(roomNo)){
									jsonObj.push( {
										"roomNo" : roomNo,
										"noOfSeets" : noOfSeets,
										"roomId" : roomId,
									});											
								}
							});
                          $("#hidInput").val(JSON.stringify(jsonObj));
						return true;
		});
});
function getRoomByFloors(val) {
var res = '';
$.ajax( {
			url : jQuery.url.getChatURL("/admin/ajaxGetNoOfExaminationRooms.do?tempId="+val),
			cache : false,
			dataType : 'json',
			success : function(response) {
			if (isNonEmpty(response)) {
			var roomNames=response.objectList;
					for (i = 0; i < roomNames.length; i++) {
					var nameno= roomNames[i].roomNumber;
					var capacity= roomNames[i].noOfSeets;
						res += '<span class="allRoomIds"><span class="roomId" id="'+roomNames[i].id+'"/> <div class="grid_12"><div class="grid_6"><div class="grid_2"><label>Room No: </label></div><div class="grid_4"><input type="text" value="'+nameno+'" style="width:200px;padding:4px;" id="allocationRoomForExam'
								+ i
								+ '"class="roomName textfield"/></div></div><div class="grid_6"><div class="grid_3"><label> Capacity Of Rooms : </label></div><div class="grid_3"><input type="text" onclick="onlyNumbers(this.value);" maxlength="2" style="width:45px;padding:4px;" id="allocationRoomForExam'
								+ i
								+ '" value="'+capacity+'" class="noOfSeets textfield"/></div></div></div></spsan>';
					}
					$('#roomNames').html(res);
					}
     			}
			});
}
function onlyNumbers(evt) {
	var e = evt; // for trans-browser compatibility	
	var charCode = e.which || e.keyCode;
	if (charCode > 31 && (charCode < 48 || charCode > 57))
		return false;
	return true;
}
</script>