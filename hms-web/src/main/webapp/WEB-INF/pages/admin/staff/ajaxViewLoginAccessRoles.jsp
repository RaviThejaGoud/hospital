<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>
					<span class="hidden-title">Login Access Roles </span>
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
				<ul class="nav nav-tabs">
				
								<li id="doLoginPage">
									<s:url id="doLoginSite" action="ajaxCopyPasteLoginPage" includeParams="all" namespace="/admin" >
									<s:param name="academicYearId" value="%{academicYear.id}"></s:param>
									</s:url>
									<sj:a href="%{doLoginSite}" indicator="indicator"  
										data-toggle="tab" targets="copyPasteDiv" button="false">Login Page - iFrame Code</sj:a>
								</li>
								<li class="active" id="logInAccess">
									<s:url id="doLoginAccessRoles"
										action="ajaxManageLoginAccessbilityForRoles" includeParams="all" namespace="/admin" >
									</s:url>
									<sj:a href="%{doLoginAccessRoles}" indicator="indicator" 
										data-toggle="tab" targets="mainContentDiv" button="false">Login Access Roles</sj:a>
								</li>
								 
					</ul>
					<div id="copyPasteDiv" class="tab-content">
					<%@ include file="/common/messages.jsp"%>
					<s:form action="ajaxChangeStatusForCheckedRoles" theme="simple"
						id="validateSelectedRoles" namespace="/admin"
						cssClass="form-horizontal" method="post">
						<s:hidden id="selectedRoleIds" name="selectedId" />
						<s:hidden id="unselectedRoleIds" name="anyId" />
						<div class="form-body">
							<div class="panel-body col-md-12">
								<div class="col-md-1">
									<span class="label label-danger"> NOTE : </span>
								</div>
								<div class="col-md-10">
									<ul>
										<li>
											You can enable the login access by selecting the respective role check box
										</li>
										<li>
											You can disable the login access by unselecting the respective role check box
										</li>
									</ul>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-12">
									<div class="checkbox-list">
										<label class="checkbox-inline">
											<input type="checkbox" name="" value=""
												onClick="allRoleNames()" class="checkbox userRoleNames">
											All Roles 
										</label>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-12">
									<div class="checkbox-list">
										<label class="conLable  control-label">
											<span class="required">*</span> Teaching Roles :
										</label>
										<s:if test="%{(tempList2 != null && !tempList2.isEmpty())}">
											<s:checkboxlist name="tempList" list="tempList2" listKey="id"
												listValue="description" theme="ems"></s:checkboxlist>
										</s:if>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-12">
									<div class="checkbox-list">
										<label class="conLable  control-label">
											<span class="required">*</span> Non Teaching Roles :
										</label>
										<s:if test="%{(objectList != null && !objectList.isEmpty())}">
											<s:checkboxlist name="tempList" list="objectList" id="roleValues"
												listKey="id" listValue="description" theme="ems"></s:checkboxlist>
										</s:if>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-12">
									<div class="checkbox-list">
										<label class="conLable  control-label">
											<span class="required">*</span> Parent & Student Roles :
										</label>
										<s:if test="%{(tempList1 != null && !tempList1.isEmpty())}">
											<s:checkboxlist name="tempList" list="tempList1" listKey="id" 
												listValue="description" theme="ems"></s:checkboxlist>
										</s:if>
									</div>
								</div>
							</div>
							<div class="form-actions fluid">
								<div class="col-md-6">
									<div class="col-md-offset-3 col-md-9">
										<sj:submit type="submit small" value="Save"
											targets="mainContentDiv" formIds="validateSelectedRoles"
											onBeforeTopics="loginRolesValidation"
											cssClass="submitBt btn blue long" title="Save"
											indicator="indicator1" />
									</div>
								</div>
							</div>
						</div>
					</s:form>
					</div>
			</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$("input:checkbox, input:radio").uniform()
	changePageTitle("Manage Login Accessbility");
	$.destroyTopic('loginRolesValidation');
		var unchecked = $("input[name=tempList]:unchecked").length;
		if (unchecked >0) {
			$(".userRoleNames").parent('span').removeClass("checked");
			$(".userRoleNames").attr("checked", false);
		}else{
			$(".userRoleNames").parent('span').addClass("checked");
			$(".userRoleNames").attr("checked",true);
		}
});
$.subscribe('loginRolesValidation', function(event, data) {
	var selectedChkCount = $("input[name=tempList]:checked").length;
	var UnselectedChkCount = $("input[name=tempList]:unchecked").length;
	if (selectedChkCount > 0) {
		var checkedroles = [];
		$("input:checkbox[name=tempList]:checked").each(function() {
			checkedroles.push($(this).val());
		});
		$('#selectedRoleIds').val(checkedroles);
	}
	if (UnselectedChkCount > 0) {
		var uncheckedroles = [];
		$("input:checkbox[name=tempList]:unchecked").each(function() {
			uncheckedroles.push($(this).val());
		});
		$('#unselectedRoleIds').val(uncheckedroles);
	}

});
function allRoleNames() {
	if ($(".userRoleNames").is(':unchecked')) {		
		var selectedChkCount = $("input[name=tempList]:checked").length;
		if (selectedChkCount > 0) {
			$("[name='tempList']").each(function() {
				$(this).parent('span').removeClass('checked');
				$(this).removeAttr("checked");
			});
		}
		else{		
			$("[name='tempList']").each(function() {
				$(this).parent('span').addClass('checked');
				$(this).attr("checked", "true");
			});
		}	
	}else{
		$("[name='tempList']").each(function() {
				$(this).parent('span').addClass('checked');
				$(this).attr("checked", "true");
			});
	}
	}
$("input[name=tempList]").click(function() {
	if ($("input[name=tempList]:unchecked").length > 0) {
		$(".userRoleNames").parent('span').removeClass("checked");
		$(".userRoleNames").attr("checked", false);
	} else {	
		$(".userRoleNames").parent('span').addClass("checked");
		$(".userRoleNames").attr("checked", true);
	}
});

</script>