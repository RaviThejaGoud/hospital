<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<h4 class="text-primary">
			Staff Time Table
		</h4>
		<s:if test="%{tempList != null && !tempList.isEmpty()}">
			<div class="row">
				<div class="col-md-6">
					<div class="form-group form-horizontal">
						<label class="control-label col-md-4">
							<span class="required">*</span> Select Teacher :
						</label>
						<div class="col-md-6">
							<s:select list="tempList" listKey="staffId"
								listValue="staffFullNameWithRole"
								cssClass="required form-control input-medium" id="staffId"
								theme="simple" name="tempId"
								onchange="javascript:getStaffTimeTable(this.value);">
							</s:select>
						</div>
					</div>
				</div>
			</div>
			<div class="spaceDiv"></div>
			<div id="vwStaffTimeCont">
			</div>
		</s:if>
		<s:else>
			<div class="alert alert-info">
				No staff available.
			</div>
		</s:else>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	var staffId = $('#staffId').val();
	if(isNonEmpty(staffId)){
		getStaffTimeTable(staffId);
	}
});
 function getStaffTimeTable(staffId) {
 	if(isNonEmpty(staffId)){
		$('#vwStaffTimeCont').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "tempId=" + staffId;					
		$.ajax( {
			url : jQuery.url.getChatURL("/common/ajaxGetStaffTimeTable.do"),
			cache : true,
			data : pars,
			success : function(response) {
				$('#vwStaffTimeCont').html(response);
			}
		});
	}else{
		$('#vwStaffTimeCont').html("<div class='grid_13 th thb'>Please select staff.</div>");
	}
}
	/*$(document).ready(function() {
		var classSectionId = $('#classSectionId').val();
		if(isNonEmpty(classSectionId)){
			var url = jQuery.url.getChatURL("/admin/getStaffTimeTable.do?classSectionId="+classSectionId);
			$.ajax({
			url : url,
			cache : false,
			dataType : 'json',
			success : function(response) {
				var timeTableList=response.objectList;
				var weekDayId='';
				var weekPeriodName='';
				var weekPeriodType='';
				var total=0;
				if(timeTableList){
						for ( var i = 0; i < timeTableList.length; i++) {
								weekDayId = timeTableList[i].dayId;
								weekPeriodName = timeTableList[i].periodName;
								weekPeriodType = timeTableList[i].periodType;
								if(isNonEmpty(timeTableList[i].subjectName))
									$('#dayId'+weekDayId+"_pName"+weekPeriodName+"_pType"+weekPeriodType).html(timeTableList[i].subjectName);
								else
									$('#dayId'+weekDayId+"_pName"+weekPeriodName+"_pType"+weekPeriodType).html("-");
						}
					}
				}
			});
		}
	});*/
</script>
