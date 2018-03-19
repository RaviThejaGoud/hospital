<%@ include file="/common/taglibs.jsp"%>
	<jsp:include page="/common/messages.jsp" />
<s:form action="ajaxMonthwiseStafftAttendanceTemplate" theme="simple"
	namespace="/reports" cssClass="form-horizontal"
	onsubmit="return selectedAllMonthNames();">
	<s:hidden id="monthNameIds" name="selectedMonthNames" />
	<div class="form-body">
		<div class="row">
			<div class="col-md-12">
				<div class="form-group">
					<div class="col-md-12">
						<div class="checkbox-list">
							<label class="checkbox-inline">
								<span> <input type="checkbox" name="" value=""
										onClick="checkAllMonths()" class="checkbox allmonths">
								</span> All Months
							</label>
						</div>
						<s:checkboxlist name="chkBoxMonthIds" list="monthNamesList"
							listValue="key" theme="ems" cssClass="small" />
					</div>
				</div>
			</div>
		</div>
		<div class="form-actions fluid">
			<div class="col-md-offset-3 col-md-9">
				<s:submit type="submit btn blue" value="Download Template"
					cssClass="btn blue long " title="generate report"
					cssStyle="cursor:pointer">
				</s:submit>
			</div>
		</div>
	</div>
</s:form>
<div class="spaceDiv"></div>
<s:form action="ajaxImportStaffsAttendance" cssClass="form-horizontal"
	id="importStudentExcelSheet" namespace="/reports" method="post"
	theme="simple" enctype="multipart/form-data" name="">
	<div class="form-body">
		<p>
			<span class="label label-danger"> NOTE : </span>&nbsp;&nbsp; If
			you want to upload the staff-details template (xls) then select
			(browse button) the staff details template excel sheet.
		</p>
		<div class="space10">
		</div>
		
		<div class="form-group">
			<label class="control-label col-md-4">
				<span class="required">*</span>Select staffs Import Template (Excel)
			</label>
			<div class="col-md-4">
				<div class="fileupload fileupload-new" data-provides="fileupload">
					<div class="input-append">
						<span class="btn default"> 
						<s:file name="upload" value="" id="photoURL" cssClass="required">
							</s:file> </span>
					</div>
				</div>
			</div>
		</div>
		<div class="form-actions fluid">
					<div class="col-md-offset-3 col-md-9">
						<sj:submit   targets="attendanceCont" value="Submit"
							indicator="indicator" cssClass="submit small btn blue"
							formIds="importStudentExcelSheet" validate="true" />
					</div>
				</div>
	</div>
</s:form>
<script type="text/javascript">
$(document).ready(function() {
	$("input:checkbox, input:radio").uniform();
	$("input[name=chkBoxMonthIds]").click(function() {
		if ($("input[name=chkBoxMonthIds]:unchecked").length > 0) {
			//$(".allReligions").removeAttr("checked");
			$(".allmonths").attr("checked", false);
		} else {
			$(".allmonths").attr("checked", true);
		}
	});
});
function checkAllMonths() {
	if ($(".allmonths").is(':checked')) {
		$("input[name='chkBoxMonthIds']").each(function() {
			$(this).parent('span').addClass('checked');
			$(this).attr("checked", "true");
		});
	} else {
		$("input[name='chkBoxMonthIds']").each(function() {
			$(this).parent('span').removeClass('checked');
			$(this).removeAttr("checked", "true");
		});
	}
}
function selectedAllMonthNames() {

		var monthIds = $("input[name=chkBoxMonthIds]").parent('span.checked');
		var selectedMonthIds = '';
		if (monthIds.length > 0) {
			selectedMonthIds = '';
			$("span.checked > input[name=chkBoxMonthIds]").each(function(){
				selectedMonthIds += $(this).val() + ',';			
			});
			selectedMonthIds += '';
		}else{
			alert("Please select at least one month");
			return false;
		}
		$("#monthNameIds").val(selectedMonthIds);
}
</script>