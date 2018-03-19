<%@ include file="/common/taglibs.jsp"%>
<div class="form-body" id="createLeaveTypeDiv">
<%@ include file="/common/messages.jsp"%>
<s:if test="%{(teachingRoleMap!= null && !teachingRoleMap.isEmpty()) || (nonTeachingRoleMap!= null && !nonTeachingRoleMap.isEmpty())}">
<s:form action="ajaxManageLeaves" theme="simple"
		cssClass="form-horizontal"
		id="teachingAndNonTeachingEdit" method="post" namespace="/admin">
		<s:hidden id="teachingRoleNameIds" name="selectedId" />
		<div class="form-group">
			<label class="control-label col-md-2">
				<span class="required">*</span>Employment Type :
			</label>
			<div class="col-md-3">
				<s:select id="permanentOrContract" 
				  headerKey="" cssClass="required form-control" tabindex="2" onchange="javascript:getDetailsWithLeaveType(this.value);"
				name="leaveManagement.permanentOrContract" list="#{'P':'Permanent','C':'Temporary'}"/>
			</div>
		</div>
		<div class="row">
		<div class="col-md-12 form-horizontal">
			<div class="form-group">
				<label class="col-md-2 control-label">
					Select Staff Type :
				</label>
				<div class="col-md-9">
					<div class="radio-list">
						<label class="radio-inline">
							<input type="radio" value="teaching" name="anyTitle" 
								onclick="changeQualification(this.value);" class="radio" checked="checked">
							Teaching
						</label>
						<label class="radio-inline">
							<input type="radio" value="nonTeaching" name="anyTitle"
								onclick="changeQualification(this.value);" class="radio">
							Non Teaching
						</label>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="teachingDiv" style="display: none;">
	<s:if test="%{teachingRoleMap!= null && !teachingRoleMap.isEmpty()}">
		<div class="form-group">
			<label class="control-label col-md-2">
				<span class="required">*</span>Role :
			</label>
			<div class="col-md-3">
				<s:select id="teachingLeave" list="teachingRoleMap" onchange="javascript:getLeaveManagementDetailsByRoleId(this.value);"
				 cssClass="required form-control" name="leaveManagement.role.name"/>
			</div>
		</div>
		</s:if>
		<s:else>
		<div class="alert alert-info">
			Currently there are no leaves defined.<a href="#"
		onclick="javascript:addLeaveSettings()"> <b>Click here</b></a> to add the leave settings
		</div>
		</s:else>
	</div>
	<div id="nonTeachingDiv" style="display: none;">
	<s:if test="%{nonTeachingRoleMap!= null && !nonTeachingRoleMap.isEmpty()}">
		<div class="form-group">
			<label class="control-label col-md-2">
				<span class="required">*</span>Role :
			</label>
			<div class="col-md-3">
				<s:select id="nonTeachingLeave" list="nonTeachingRoleMap" onchange="javascript:getLeaveManagementDetailsByRoleId(this.value);"
					cssClass="required form-control" name="leaveManagement.role.description" />
			</div>
		</div>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			Currently there are no leaves defined.<a href="#"
		onclick="javascript:addLeaveSettings()"> <b>Click here</b> </a> to add the leave settings
		</div>
	</s:else>
	</div>
		<div id="getRoleByManageLeaves"></div>
	</s:form> 
</s:if>
<s:else>
		<div class="alert alert-info">
			Currently there are no leaves defined.<a href="#"
		onclick="javascript:addLeaveSettings()"> <b>Click here</b> </a> to add the leave settings
		</div>
	</s:else>
</div>
<script type="text/javascript">
$(document).ready(function() {
TableAdvanced.init();
$('ul.nav-tabs li').removeClass('active');
$('li#staffLeaveDetails').addClass('active');
	$("input:checkbox, input:radio").uniform();
	$('#teachingDiv').show();
	var roleName=$('#teachingLeave').val();
	 
	if(isNonEmpty(roleName)){
		$("#teachingRoleNameIds").val(roleName);
		getLeaveManagementDetailsByRoleId(roleName);
	}
});
function addLeaveSettings(){
	$('a#addLeaveSetting').click();
}
function popupLeaveSettings(id) {
	var url = jQuery.url.getChatURL("/admin/ajaxDoEditManageLeaves.do");
	$('#popupLeaveSettingDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
			url : url,
			cache : false,
			data : "id=" + id,
			success : function(html) {
				$("#popupLeaveSettingDiv").html(html);
			}
		});
	}
	function changeQualification(staffType) {
	if (staffType == 'teaching') {
		var roleName =$('select#teachingLeave').val();
		if(isNonEmpty(roleName)){
			getLeaveManagementDetailsByRoleId(roleName);
		}

		$('#teachingDiv').show();
		$('#nonTeachingDiv').hide();
	} else {
		var roleName =$('select#nonTeachingLeave').val();
		if(isNonEmpty(roleName)){
			getLeaveManagementDetailsByRoleId(roleName);
		}
		$('#teachingDiv').hide();
		$('#nonTeachingDiv').show();
	}
		if($('div.alert-info').is(':visible')){
		   $('div#getRoleByManageLeaves').hide();
		 }else{
		   $('div#getRoleByManageLeaves').show();
		 }
}
function getDetailsWithLeaveType(changeStatus ) {
	var status = changeStatus;
	if(isNonEmpty(status)){
		var pars = "anyId=" + status;
		$("#stepStaffLeavesEditDiv").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
			url : jQuery.url.getChatURL("/admin/ajaxViewAllManageLeaves.do"),
			cache : false,
			data : pars,
			success : function(response) {
				$('#stepStaffLeavesEditDiv').html(response);
			}
		});
	}else{
			$('#stepStaffLeavesDiv').html("<p><span class='label label-danger'> NOTE : </span> &nbsp;&nbsp;Please select Leave type .</p>");
	}
}
function getLeaveManagementDetailsByRoleId(roleName) {
	var pars = "anyTitle=" +roleName+"&anyId="+$('select#permanentOrContract').val();
	$("#teachingRoleNameIds").val(roleName);
	$("#getRoleByManageLeaves").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	var url = jQuery.url.getChatURL("/admin/ajaxGetRoleByLeaveDetails.do");
	$.ajax( {
		url : url,
		cache : false,
		data : pars,
		success : function(html) {
			$("#getRoleByManageLeaves").html(html);
			$("#getRoleByManageLeaves").show();
		}
	});
}
</script>
