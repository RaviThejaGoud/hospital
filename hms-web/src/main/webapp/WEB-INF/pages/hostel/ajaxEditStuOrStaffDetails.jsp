<%@ include file="/common/taglibs.jsp"%>
<div data-width="760" class="modal fade modal-overflow in"
	style="display: block; width: 760px; margin-left: -379px; margin-top: 150px;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="modal-title">
			Assign to Room  
		</h4>
	</div>
	<div class="modal-body">
	<div class="form-body">
		<s:form action="ajaxAddStudentToHostel" theme="simple"
			id="studentinfoinHostel" method="post" cssClass="form-horizontal" namespace="/hostel">
			<s:hidden name="student.accountId" value="%{selectedId}"></s:hidden>
			<s:hidden name="anyTitle" value="%{anyTitle}"></s:hidden>
				<div class="form-group">
					<label class="control-label col-md-3">
						<span class="required"> * </span>Select Building/Floor :
					</label>
					<div class="col-md-4">
						<s:select list="buildingList" listKey="FloorId"
							listValue="buildingNameAndFloorName" name="anyId"
							headerKey="yourOptionValue" cssClass="form-control required"
							onchange="javascript:getAjaxDoGetRooms(this.value);"
							headerValue="- Select -">
						</s:select>
					</div>
				</div>
				<div id="hostelRooms"></div>
				<div class="form-actions fluid">
					<div class="col-md-offset-3 col-md-9">
						<sj:submit   cssClass="submitBt btn blue" value="Submit" targets="searchStudentsList" validate="true"
							onBeforeTopics="addStudentInHostel" formIds="studentinfoinHostel" />
						<button type="button" data-dismiss="modal" class="btn default">Cancel</button>
					</div>
				</div>
		</s:form>
	</div>
	</div>
</div>
<script Language="Javascript1.2" type="text/javascript">
$(document).ready(function() {
	changePageTitle("Add students To Room");
	$('.blockHeader h2').html('Manage Reports');
	var selectedValue=$("#studentinfoinHostel_anyId option:selected").val();
	if(selectedValue!=0 && selectedValue!='yourOptionValue'){
	     getAjaxDoGetRooms(selectedValue);
	}
	$.subscribe('addStudentInHostel', function(event, data) {
		if ($('#studentinfoinHostel').valid()) {
			$("input#anyword").val('Enter First or Last Name.');
			if ($("input[name='tempList']:checked").length == 0) {
				alert('Please select at least one bed.');
				event.originalEvent.options.submit=false;
			} else {
				var bedId = ($("input[name='tempList']:checked").attr('id'));
				$('<input>').attr('type', 'hidden').appendTo('form');
				$('button.close').click();
				return true;
			}
		} else {
			event.originalEvent.options.submit=false;
		}
	});
});

function getAjaxDoGetRooms(yourOptionValue) {
	$("#hostelRooms")
			.html(
					'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	var url = jQuery.url.getChatURL("/hostel/ajaxDoGetRoomsOfOneFloor.do");
	var pars = "yourOptionValue=" + yourOptionValue+'&accountId='+<s:property value="selectedId"/>;
	$.ajax( {
		url : url,
		cache : false,
		data : pars,
		success : function(html) {
			$("#hostelRooms").html(html);

		}
	});
}
</script>