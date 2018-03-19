<%@ include file="/common/taglibs.jsp"%>
<div data-width="760" class="modal fade modal-overflow in"
	style="display: block; width: 760px; margin-left: -379px; margin-top: 150px;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="modal-title">
			Assign To Room 
		</h4>
	</div>
	<div class="modal-body">
	<div class="form-body">
		<s:form action="ajaxEditStudentHostelInformation" theme="simple"
			id="editStudentHostelInfos" method="post" cssClass="form-horizontal" namespace="/common"> 
			<div id="studentPersonalDetails">
				<jsp:include page="/common/messages.jsp"></jsp:include>
			</div>
			<s:hidden name="hostelStudents.id" />
			<s:hidden name="hostelStudents.studentId" value="%{tempId}"/>
			<s:hidden name="hostelStudents.accountId" value="%{bankId}"/>
			<div class="form-group">
				<label class="control-label col-md-3">
					<span class="required">*</span>Select Hostel & Building :
				</label>
				<div class="col-md-6">
					<s:select list="objectList" listKey="buildingId" id="buildingId"
						listValue="hostelNameAndBuildingName" cssClass="form-control required"
						label="Select Hostel & Building"
						onchange="javascript:getFloorDetailsByBuilding(this.value,'floors');"
						theme="simple" name="tempId2" headerKey=""
						headerValue="- Select -">
					</s:select>
				</div>
			</div>
			<div id="resultsDiv1" style="display: block">
				<div class="form-group">
					<label class="control-label col-md-3">
						<span class="required">*</span>Select Building & Floor :
					</label>
					<div class="col-md-6">
						<s:select list="tempList" listKey="floorId" id="lfloorId"
							headerValue="- Select -" headerKey="" cssClass="form-control required"
							onchange="javascript:getRoomsDetailsByFloor(this.value,'rooms');"
							listValue="buildingNameAndFloorName" name="anyId" theme="simple">
						</s:select>
					</div>
				</div>
			</div>
			<div id="resultsDiv2"> </div>
			<div id="resultsDiv3" style="display: block">
				<div class="form-group">
					<label class="control-label col-md-3">
						<span class="required">*</span>Select Room :
					</label>
					<div class="col-md-6">
						<s:select id="studentRoomId" list="tempList1" listKey="id"
							cssClass="required form-control" listValue="roomName"
							headerKey="" headerValue="- Select -" name="tempId"
							theme="simple" />
					</div>
				</div>
			</div>
			<div id="resultsDiv4" style="display: none;"> </div>
			<div id="resultsDiv5" style="display: block;">
				<div class="form-group">
					<label class="control-label col-md-3">
						<span class="required">*</span>Select Bed No / Name :
					</label>
					<div class="col-md-6">
						<s:select list="tempList2" listKey="id" theme="simple"
							id="bedId" headerValue="- Select -" headerKey=""
							cssClass="select2_category form-control required" listValue="bedName" name="bankId">
						</s:select>
					</div>
				</div>
			</div>
			<div id="resultsDiv6" style="display: none;"> </div>
			<div class="form-actions fluid">
				<div class="col-md-offset-3 col-md-9">
					<s:if
						test='%{(#session.academicYear == null || #session.academicYear.isEmpty()) || (#session.previousYear=="N")}'>
						<sj:submit cssClass="submitBt btn blue" value="Save" formIds="editStudentHostelInfos" indicator="indicator" validate="true"
							targets="hideRetunPageDiv" onBeforeTopics="checkHostelEditStudentInfo" />
							<button type="button" data-dismiss="modal" class="btn default">Cancel</button>
					</s:if>
				</div>
			</div>
		</s:form>
		</div>
	</div>
</div>
<script type="text/javascript">
$.subscribe('checkHostelEditStudentInfo', function(event, data) {
	if($("#buildingId").is(":hidden")){
		$("#buildingId").removeClass('required');
	}
	if($("#floorId").is(":hidden")){
		$("#floorId").removeClass('required');
	}
	if($("#lfloorId").is(":hidden")){
		$("#lfloorId").removeClass('required');
	}
	if($("#roomId").is(":hidden")){
		$("#roomId").removeClass('required');
	} 
	if($("#studentRoomId").is(":hidden")){
		$("#studentRoomId").removeClass('required');
	} 
	/* var roomId = $('#roomId:visible').val();
	if (roomId == null || roomId == '' || roomId == 'S') {
		alert("Please select atleast one room.");
		event.originalEvent.options.submit=false;
	}
	var bedId = $('#bedId:visible').val();
	if (bedId == null || bedId == '' || bedId == 'S') {
		alert("Please select atleast one bed.");
		event.originalEvent.options.submit=false;
	} */
	if ($('#editStudentHostelInfos').valid()) {
		$('button.close').click();
		return true;
	} 
});
function getFloorDetailsByBuilding(buldingId, anyTitle) {
	if(isNonEmpty(buldingId)){ 
		$('div#resultsDiv2').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "tempId2=" + buldingId + "&anyTitle=" + anyTitle;
		$.ajax( {
			url : jQuery.url.getChatURL("/hostel/ajaxDoGetFloorsByBuildings.do"),
			cache : false,
			data : pars,
			success : function(response) {
				$('div#resultsDiv2').html(response);
				document.getElementById('resultsDiv1').style.display = "none";  
			}
		});
	}else
		$('#resultsDiv2').html('<div class="thb" style="margin-top:10px;">Please select Hostel & Building.</div>');
} 
 function getRoomsDetailsByFloor(id, type) { 
	if(isNonEmpty(id)){
		$('#resultsDiv4').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "tempId1=" + id + "&anyTitle=" + type;
		$.ajax( {
			url : jQuery.url.getChatURL("/hostel/ajaxGetRoomsByFloorId.do"),
			cache : false,
			data : pars,
			success : function(response) {
				$('#resultsDiv4').html(response);
					document.getElementById('resultsDiv4').style.display = "block";
					document.getElementById('resultsDiv3').style.display = "none";  
			}
		});
	}else
		$('#resultsDiv4').html('<div class="thb" style="margin-top:10px;">Please select Building & Floor.</div>');
} 
 
</script>
