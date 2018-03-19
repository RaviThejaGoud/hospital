<%@ include file="/common/taglibs.jsp"%>
<div class="form-body">
	<div class="form-group">
		<label class="control-label col-md-2">
			<span class="required">*</span>Employment Type :
		</label>
		<div class="col-md-3">
			<s:select id="staffType2" 
			  headerKey="" cssClass="required form-control" tabindex="2" onchange="javascript:getDetailsWithLeaveTypeAdd(this.value);"
			 name="leaveManagement.permanentOrContract"
			list="#{'P':'Permanent','C':'Temporary'}"/>
		</div>
	</div>
	<div>&nbsp;</div><div>&nbsp;</div>
	<s:if test="%{(teachingRoleMap!= null && !teachingRoleMap.isEmpty()) || (nonTeachingRoleMap!= null && !nonTeachingRoleMap.isEmpty())}">
	<div class="row">
		<div class="col-md-12 form-horizontal">
			<div class="form-group">
				<label class="col-md-2 control-label">
					Select Staff Type :
				</label>
				<div class="col-md-9">
					<div class="radio-list">
						<label class="radio-inline">
							<input type="radio" value="teaching"
								onclick="changeQualification(this.value);" name="addGroupLeader"
								class="radio" checked="checked">
							Teaching
						</label>
						<label class="radio-inline">
							<input type="radio" value="nonTeaching"
								onclick="changeQualification(this.value);" name="addGroupLeader"
								class="radio">
							Non Teaching
						</label>
					</div>
				</div>
			</div>
		</div>
	</div>
</s:if>
	<div id="teachingDiv" class="grid_13" style="display: block;">
		<s:if test="%{teachingRoleMap!= null && !teachingRoleMap.isEmpty()}">
		<s:form action="ajaxManageLeaves" theme="simple"
	id="manageStaffLeaves" cssClass="form-horizontal" namespace="/admin">
	<s:hidden id="teachingRoleNameIds" name="selectedId" />
	<s:hidden id="empType" name="leaveManagement.permanentOrContract" />
			<div class="form-group">
					<div class="col-md-12">
						<div class="checkbox-list">
							<label class="checkbox-inline">
									<input type="checkbox" name=""
											value="" onClick="checkAllTeachingRole()" class="checkbox TeachingRole">
									All Teaching Roles
							</label>
						</div>
					</div>
				</div>
				<div class="form-group">
					<!--<label class="conLable col-md-3 control-label">
						Roles :
					</label>
					--><div class="col-md-12">
						<div class="checkbox-list">
							<s:checkboxlist name="teachingRole" list="teachingRoleMap" value=""
							 theme="ems"/>
						</div>
					</div>
				</div>
				<s:if test="%{(teachingRoleMap!= null && !teachingRoleMap.isEmpty()) || (nonTeachingRoleMap!= null && !nonTeachingRoleMap.isEmpty())}">
			<div class="form-group ">
				<label class="control-label col-md-2">
					<span class="required">*</span>Sick Leaves :
				</label>
				<div class="col-md-3">
					<sj:textfield name="leaveManagement.sickLeaves" id="sickLeaves"
						cssClass="required numeric form-control" maxlength="2"></sj:textfield>
				</div>
			</div>
			<div class="form-group ">
				<label class="control-label col-md-2">
					<span class="required">*</span>Casual Leaves :
				</label>
				<div class="col-md-3">
					<sj:textfield name="leaveManagement.casualLeaves"
						id="casualLeaves" cssClass="required numeric form-control"
						maxlength="2"></sj:textfield>
				</div>
			</div>
			<div class="form-group ">
				<label class="control-label col-md-2">
					<span class="required">*</span>Earned Leaves :
				</label>
				<div class="col-md-3">
					<sj:textfield name="leaveManagement.earnedLeaves"
						id="earnedLeaves" cssClass="numeric required form-control "
						maxlength="2">
					</sj:textfield>
				</div>
			</div>
		<div class="form-actions fluid">
			<div class="col-md-offset-2 col-md-9">
				<sj:submit cssClass="submitBt btn blue" value="Submit" 
					targets="stepStaffLeavesDiv" validate="true"
					formIds="manageStaffLeaves" onBeforeTopics="validateManageLeavesForm" />
				<s:url id="doCancelManageLeaves" action="ajaxDoCancelManageLeaves"
					includeParams="all" namespace="/admin"></s:url>
				<sj:a href="%{doCancelManageLeaves}" cssClass="btn default"
					targets="stepStaffLeavesDiv" button="false">Cancel</sj:a>
			</div>
		</div>
		</s:if>
				
			</s:form>
			</s:if>
		<s:else>
			<div class="alert alert-info">
				You have already created all teaching sick leaves, casual
				leaves and earned leaves. If you want edit
				<s:url id="urlLeaves" action="ajaxViewAllManageLeaves" namespace="/admin" />
				<sj:a  href="%{urlLeaves}" targets="mainContentDiv">
					<b>Click here</b></sj:a>
			</div>
		</s:else>
	</div>
	<div id="nonTeachingDiv" style="display: none;">
		<s:if test="%{nonTeachingRoleMap!= null && !nonTeachingRoleMap.isEmpty()}">
		<s:form action="ajaxManageLeaves" theme="simple"
	id="manageNonStaffLeaves" cssClass="form-horizontal" namespace="/admin">
	<s:hidden id="nonTeachingRoleNameIds" name="selectedId" />
	<s:hidden id="empType" name="leaveManagement.permanentOrContract" />
			<div class="form-group">
					<div class="col-md-12">
						<div class="checkbox-list">
							<label class="checkbox-inline">
									<input type="checkbox" name=""
											value="" onClick="checkAllNonTeachingRole()" class="checkbox NonTeachin">
								All Non Teaching Roles
							</label>
						</div>
					</div>
				</div>
				<div class="form-group">
					<!--<label class="conLable col-md-3 control-label">
						Roles :
					</label>
					--><div class="col-md-12">
						<div class="checkbox-list">
							<s:checkboxlist name="nonTeachingRole" list="nonTeachingRoleMap" value=""
							 theme="ems"/>
						</div>
					</div>
				</div>
				<s:if test="%{(teachingRoleMap!= null && !teachingRoleMap.isEmpty()) || (nonTeachingRoleMap!= null && !nonTeachingRoleMap.isEmpty())}">
			<div class="form-group ">
				<label class="control-label col-md-2">
					<span class="required">*</span>Sick Leaves :
				</label>
				<div class="col-md-3">
					<sj:textfield name="leaveManagement.sickLeaves" id="sickLeavesn"
						cssClass="required numeric form-control" maxlength="2"></sj:textfield>
				</div>
			</div>
			<div class="form-group ">
				<label class="control-label col-md-2">
					<span class="required">*</span>Casual Leaves :
				</label>
				<div class="col-md-3">
					<sj:textfield name="leaveManagement.casualLeaves"
						id="casualLeavesn" cssClass="required numeric form-control"
						maxlength="2"></sj:textfield>
				</div>
			</div>
			<div class="form-group ">
				<label class="control-label col-md-2">
					<span class="required">*</span>Earned Leaves :
				</label>
				<div class="col-md-3">
					<sj:textfield name="leaveManagement.earnedLeaves"
						id="earnedLeavesn" cssClass="numeric required form-control "
						maxlength="2">
					</sj:textfield>
				</div>
			</div>
		<div class="form-actions fluid">
			<div class="col-md-offset-2 col-md-9">
				<sj:submit cssClass="submitBt btn blue" value="Submit" 
					targets="stepStaffLeavesDiv" validate="true"
					formIds="manageNonStaffLeaves" onBeforeTopics="validateManageLeavesForm" />
				<s:url id="doCancelManageLeaves" action="ajaxDoCancelManageLeaves"
					includeParams="all" namespace="/admin"></s:url>
				<sj:a href="%{doCancelManageLeaves}" cssClass="btn default"
					targets="stepStaffLeavesDiv" button="false">Cancel</sj:a>
			</div>
		</div>
		</s:if>
		</s:form>
			</s:if>
		<s:else>
			<div class="alert alert-info">
				You have already created all non teaching sick leaves, casual
				leaves and earned leaves. If you want edit
				<s:url id="urlLeaves" action="ajaxViewAllManageLeaves" namespace="/admin" />
				<sj:a  href="%{urlLeaves}" targets="mainContentDiv">
					<b>Click here</b></sj:a>
			</div>
		</s:else>
	</div>
</div>
<script language="JavaScript" type="text/javascript">
changePageTitle('Leave Management');
$(document).ready(function() {
	var selected = $('input[name=SelectType]:radio:checked').val();
	changeQualification(selected);
	$("input:checkbox, input:radio").uniform();
	$('.numeric').numeric( {
		allow : "0-9"
	});
	$.destroyTopic('validateManageLeavesForm');
	
	$("input[name=nonTeachingRole]").click(function() {
	var nonTeachingRoleNameIds = [];
			if ($("input[name=nonTeachingRole]:unchecked").length > 0) {
			    $(".NonTeachin").parent('span').removeClass("checked");
				$(".NonTeachin").attr("checked", false);
			} else {
			    $(".NonTeachin").parent('span').addClass("checked");
				$(".NonTeachin").attr("checked", true);
			}
			nonTeachingRoleNameIds=$("input[name=nonTeachingRole]:checked").map(function () {return this.value;}).get().join(",");
			$("#nonTeachingRoleNameIds").val(nonTeachingRoleNameIds);
		});
		$("input[name=teachingRole]").click(function() {
		var teachingRoleNameIds = [];
			if ($("input[name=teachingRole]:unchecked").length > 0) {
			    $(".TeachingRole").parent('span').removeClass("checked");
				$(".TeachingRole").attr("checked", false);
			} else {
			    $(".TeachingRole").parent('span').addClass("checked");
				$(".TeachingRole").attr("checked", true);
			}
			teachingRoleNameIds=$("input[name=teachingRole]:checked").map(function () {return this.value;}).get().join(",");
			$("#teachingRoleNameIds").val(teachingRoleNameIds);
	});	
	var empStatus =$('select#permanentOrContract').val();
	if(isNonEmpty(empStatus)){
		$('#empType').val(empStatus);
	}
});


$.subscribe('validateManageLeavesForm', function(event, data) {
       if ($("input[name=teachingRole]:checked").length==0 && $("input[name=nonTeachingRole]:checked").length==0 ) {
              alert('Please select at least one role.');
              event.originalEvent.options.submit=false;
        }
 });
	function checkAllTeachingRole() {
	var selectedteachingRoleIds = [];
		if ($(".TeachingRole").is(':checked')){
		    $("[name='teachingRole']").each(function(){
			   $(this).parent('span').addClass('checked');
			    $(this).attr("checked", "true");
			});
		}
		else{
		 $(".TeachingRole").parent('span').removeClass('checked');	
		 $(".TeachingRole").removeAttr("checked");
		 $("[name='teachingRole']").each(function(){
			   $(this).parent('span').removeClass('checked');
			    $(this).removeAttr("checked");
			});
		}	
		selectedteachingRoleIds=$("input[name=teachingRole]:checked").map(function () {return this.value;}).get().join(",");
			$("#teachingRoleNameIds").val(selectedteachingRoleIds);
	}
	function checkAllNonTeachingRole() {
	var nonTeachingRoleNameIds = [];
		if ($(".NonTeachin").is(':checked')){
		    $("[name='nonTeachingRole']").each(function(){
			   $(this).parent('span').addClass('checked');
			    $(this).attr("checked", "true");
			});
		}
		else{
			$(".NonTeachin").parent('span').removeClass('checked');	
			$(".NonTeachin").removeAttr("checked");
		 $("[name='nonTeachingRole']").each(function(){
			   $(this).parent('span').removeClass('checked');
			    $(this).removeAttr("checked");
			});
		}	
		nonTeachingRoleNameIds=$("input[name=nonTeachingRole]:checked").map(function () {return this.value;}).get().join(",");
			$("#nonTeachingRoleNameIds").val(nonTeachingRoleNameIds);
	}
	function getSelectedValue(value) {			    
	}
function changeQualification(staffType) {
	if (isNonEmpty(staffType)) {				
		 if (staffType == 'teaching' ) {
			checkAllTeachingRole();
		 	$(".NonTeachin").removeAttr("checked");  
	   		$("input[name='nonTeachingRole']").removeAttr("checked");   		
			$('#teachingDiv').show();
			$("#nonTeachingDiv").hide();
			$("#nonTeachingRoleNameIds").hide();
			$('#sickLeavesn').val('0');
			$('#casualLeavesn').val('0');
			$('#earnedLeavesn').val('0');
		} else {
			checkAllNonTeachingRole();			
			$("input[name='teachingRole']").removeAttr("checked");
		 	$(".TeachingRole").removeAttr("checked");
			$('#teachingDiv').hide();
			$("#nonTeachingDiv").show();
			$("#teachingRoleNameIds").val(null);
			$('#sickLeaves').val('0');
			$('#casualLeaves').val('0');
			$('#earnedLeaves').val('0');
		}
	}
}
function getDetailsWithLeaveTypeAdd(changeStatus) {
	var status = changeStatus;
	if(isNonEmpty(status)){
		var pars = "anyId=" + status;
		$("#stepStaffLeavesDiv").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
			url : jQuery.url
					.getChatURL("/admin/ajaxDoManageLeaves.do"),
			cache : false,
			data : pars,
			success : function(response) {
				$('#stepStaffLeavesDiv').html(response);
			}
		});
	}else{
		$('#stepStaffLeavesDiv').html("<p><span class='label label-danger'> NOTE : </span> &nbsp;&nbsp;Please select Leave type .</p>");
	}
}
</script>