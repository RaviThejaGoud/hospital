<%@ include file="/common/taglibs.jsp"%>
<div>
	<jsp:include page="/common/messages.jsp" />
	<s:if
		test="%{schoolCategoriesList!= null || !schoolCategoriesList.isEmpty}">
		<div class="form-group">
			<label class="control-label col-md-3">
				<span class="required">*</span> Select Category Name :
			</label>
			<div class="col-md-4">
				<s:select list="schoolCategoriesList" listKey="id"
					listValue="categoryName" cssClass="form-control"
					id="categoriesCont" name="tempId1" theme="simple"
					headerKey="categoryName" headerValue="-Select Category-"></s:select>
			</div>
		</div>
	</s:if>
	<div class="form-group">
		<label class="control-label col-md-3">
			<span class="required">*</span> Select Room :
		</label>
		<div class="col-md-4">
			<s:select list="roomList" listKey="roomId"
				listValue="roomNameAndRoomLevel" name="roomId"
				headerKey="yourOptionValue"
				onchange="getAjaxDoGetRoomBeds(this.value);" cssClass="form-control"
				headerValue="- Select -" theme="simple">
			</s:select>
		</div>
	</div>
	<div id="hostelFLoorRooms"></div>
</div>
<script Language="Javascript1.2" type="text/javascript">
$(document).ready(function() {
	var selectedValueId=$("#roomId option:selected").val();
	if(selectedValueId!=0 && selectedValueId!='yourOptionValue'){
	     getAjaxDoGetRoomBeds(selectedValueId);
	}
});

function getAjaxDoGetRoomBeds(yourOptionValue) {
	$("#hostelFLoorRooms")
			.html(
					'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	var url = jQuery.url.getChatURL("/hostel/ajaxDoGetBedsOfOneRoom.do");
	var pars = "yourOptionValue=" + yourOptionValue+'&accountId='+<s:property value="selectedId"/>;
	$.ajax( {
		url : url,
		cache : false,
		data : pars,
		success : function(html) {
			$("#hostelFLoorRooms").html(html);
		}
	});
}
</script>