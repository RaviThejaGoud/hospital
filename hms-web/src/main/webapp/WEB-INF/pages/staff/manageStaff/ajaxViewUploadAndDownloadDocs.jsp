<%@ include file="/common/taglibs.jsp"%>
<div class="form-body">
	<div class="row">
		<div class="form-horizontal">
			<div class="form-group">
				<label class="col-md-3 control-label">
					Select Document Type :
				</label>
				<div class="col-md-9">
					<div class="radio-list">
						<label class="radio-inline">
							<input type="radio" name="selectType" value="uploadStaffDoc" 
								onclick="getAjaxDoGetStaffUpaload(this.value);" class="radio uploadStaffDocs" checked="checked">
							Upload Staff Documents
						</label>
						<!-- <label class="radio-inline">
							<input type="radio" value="uploadTimetable" name="selectType" 
								onclick="getAjaxDoGetStaffUpaload(this.value);" class="radio uploadstaffTimetable">
							Upload Timetable Documents
						</label>
						<label class="radio-inline">
							<input type="radio" value="uploadClassWiseTimetable" name="selectType" 
								onclick="getAjaxDoGetStaffUpaload(this.value);" class="radio uploadClassTimetable">
							 Class Wise Timetable
						</label> -->
					</div>
				</div>
			</div>
			<%@ include file="/common/messages.jsp"%>
		</div>
		<div id="uploadDocumentsViewDiv"></div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$("input:checkbox, input:radio").uniform();
	var selected = $('input[name=selectType]:radio:checked').val();
	var checkedvalue = '<s:property value="tempString"/>';
	if(isNonEmpty(checkedvalue) && checkedvalue == 'uploadTimetable'){
		$('input.uploadStaffDocs').parent('span').removeClass('checked');
		$('input.uploadstaffTimetable').parent('span').addClass("checked",true);
		selected = 'uploadTimetable';
	}
	if(isNonEmpty(checkedvalue) && checkedvalue == 'uploadClassWiseTimetable'){
		$('input.uploadStaffDocs').parent('span').removeClass('checked');
		$('input.uploadClassTimetable').parent('span').addClass("checked",true);
		selected = 'uploadClassWiseTimetable';
	}
	getAjaxDoGetStaffUpaload(selected);
});
function getAjaxDoGetStaffUpaload(selected) {
	var pars = "tempString=" + selected+"&plTitle=Staff Details";
	$("#uploadDocumentsViewDiv")
			.html(
					'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	var url = jQuery.url.getChatURL("/staff/ajaxDoUploadAndDownloadDocs.do");
	$.ajax( {
		url : url,
		cache : false,
		data : pars,
		success : function(html) {
			$("#uploadDocumentsViewDiv").html(html);
		}
	});
}
</script>