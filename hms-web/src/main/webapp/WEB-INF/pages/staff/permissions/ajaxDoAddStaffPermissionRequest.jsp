<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
 <div class="form-body">
	<s:form id="staffAddPermission" action="ajaxAddStaffPermissionsRequest" method="post" theme="simple" cssClass="form-horizontal" namespace="/staff">
	<s:hidden name="staffPermissionRequestsVO.permissionDate" cssClass="permissionDate"></s:hidden>
	<s:hidden name="staffPermissionRequestsVO.status" value="P"></s:hidden>
			<div class="form-group">
				<label class="control-label col-md-4"> <span
					class="required">*</span>Select Date :
				</label>
				<div class="col-md-6">
					<div data-date-format="mm/dd/yyyy" data-date-start-date="+0d"
						class="input-group input-medium date date-picker">
						<input type="text" id="permissionDate" readonly=""
							class="form-control required" 
							name=""> <span
							class="dateInput input-group-btn">
							<button type="button" class="btn default">
								<i class="fa fa-calendar"></i>
							</button>
						</span>
					</div>
					<span class="help-block">(MM/DD/YYYY)</span>
				</div>
			</div>
			<div id="permissiosDiv"></div>
		<div class="form-actions fluid">
			<div class="col-md-offset-4 col-md-6">
				<sj:submit value="Submit" cssClass="submitBt btn blue"
					onBeforeTopics="staffPermissions" formIds="staffAddPermission" 
					targets="mainContentDiv" indicator="indicator" validate="true" />
				<button type="button" data-dismiss="modal" class="btn default">Cancel</button>
			</div>
		</div>
	</s:form>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		FormComponents.init();
		changePageTitle("Permission Request");
	});
	$.subscribe('staffPermissions', function(event, data) {
		if ($('#staffAddPermission').valid()){
			return true;
		}else{
			event.originalEvent.options.submit=false;
		}
	});	
	$('.date-picker').datepicker().on('changeDate', function(ev){
		var permissionDate = $("input#permissionDate").val();
		 var dateStr = permissionDate.split("/");
	     var startDate = dateStr[2]+"-"+dateStr[0]+"-"+dateStr[1]
	     $('.permissionDate').val(startDate);
	     var pars = "anyId=" + startDate;
		var url = jQuery.url.getChatURL("/staff/ajaxGetStaffPermissionsByDate.do");
		$("#permissiosDiv").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(response) {
				$('#permissiosDiv').html(response);
			}
		});
	});
</script>
