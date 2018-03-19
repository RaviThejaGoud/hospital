<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
	<div class="form-group">
		<label class="control-label col-md-4"> <span
			class="required">*</span>Start Time:
		</label>
		<div class="col-md-6">
			 <div class="input-group bootstrap-timepicker col-md-5" id='<s:property value="id" />'>
					<input type="text" class="form-control timepicker-default startTimeChange required" value="" name="staffPermissionRequestsVO.startTime" id="startTime"  onchange="checkStartTimeEndTImeValidation();">
					<span class="dateInput input-group-btn">
						<button class="btn default" type="button">
							<i class="fa fa-clock-o"></i>
						</button>
					</span>
				</div>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-4"> <span
			class="required">*</span>End Time :
		</label>
		<div class="col-md-6">
			 <div class="input-group bootstrap-timepicker col-md-5" id='<s:property value="id" />'>
					<input type="text" class="form-control timepicker-default startTimeChange required"  value="" name="staffPermissionRequestsVO.endTime" id="endTime"  onchange="checkStartTimeEndTImeValidation();">
					<span class="dateInput input-group-btn">
						<button class="btn default" type="button">
							<i class="fa fa-clock-o"></i>
						</button>
					</span>
				</div>
		</div>
	</div>
	<div class="form-group ">
		<label class="control-label col-md-4"><span class="required">*</span>Reason For Permission :</label>
		<div class="col-md-4">
			<sj:textarea name="staffPermissionRequestsVO.comments"
				label="Event Description" id="comments"
				cssClass="form-control required"></sj:textarea>
		</div>
	</div>
<script type="text/javascript">
		FormComponents.init();
		function checkStartTimeEndTImeValidation() {
			    var startDate = $('input#permissionDate').val();
				var startTime = $('input#startTime').val();
				var endTime = $('input#endTime').val();
				//alert(startDate+"--"+startTime+"==="+endTime);
				if (isNonEmpty(startTime) && isNonEmpty(endTime)) {
				var selectedStartTime = new Date(startDate+" " + startTime);
				var selectEndTime = new Date(startDate+" " + endTime);
					if (selectEndTime <= selectedStartTime) {
						alert("Start time should be less than end time.");
						$('#endTime').val('');
					}
				}
		}
</script>