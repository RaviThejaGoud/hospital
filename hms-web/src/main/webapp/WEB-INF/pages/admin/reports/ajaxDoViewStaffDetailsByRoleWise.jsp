<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>
					<span class="hidden-title"> </span>
				</div>
			</div>
			<div class="portlet-body">
					<div class="tab-content">
						<!--<s:if
						test='%{(user.isOnlySchoolAdmin=="Y" && customer.hostelModuleStatus) || (user.isSchoolPrincipal=="Y" && customer.hostelModuleStatus=="Y")}'>
							<div id="classWiseStuDetailsPdfDetails">
								<div class="grid_3 alpha">
									<label class="labelRight">
										Select Students for:
									</label>
								</div>
								<div class="grid_5 alpha">
									<input type="radio" id="student" value="ROLE_STUDENT"
										class="radio" name="selectedName" checked="checked">
									School
									</input>
									<input type="radio" id="staff" value="ROLE_HOSTEL" class="radio"
										name="selectedName">
									Hostel
									</input>
								</div>
							</div>
					</s:if>
					-->
						<s:if test="%{(objectList != null && !objectList.isEmpty())}">
							<s:form action="ajaxDownLoadStaffDetails" theme="simple"
								namespace="/reports" cssClass="form-horizontal"
								onsubmit="return generateStaffRoleIds();" id="classAndCommunity"
								method="post">
								<s:hidden id="satffRoleIds" name="selectedId" />
								<div class="form-body">
									<div class="form-group">
										<label class="conLable col-md-2 control-label">
											<span class="required">*</span> Available Roles :
										</label>
										<div class="col-md-12">
											<div class="checkbox-list">
												<label class="checkbox-inline">
													<input type="checkbox" name=""
															value="" onClick="checkAllRoles()"
															class="checkbox allRoles">
													All Roles
												</label>
											</div>
											<s:checkboxlist name="chkBoxSelectedIds" list="objectList"
												listKey="roleId" listValue="roleDescription" theme="ems"
												cssClass="small" />
										</div>
									</div>
									<div class="form-actions fluid">
										<div class="col-md-6">
											<div class="col-md-offset-3 col-md-9">
												<s:submit type="submit small" value="Generate Excel"
													cssClass="submitBt btn blue long" title="generate report"
													onclick="reportFormate()" />
											</div>
										</div>
									</div>
								</div>
							</s:form>
						</s:if>
						<s:else>
							<div class="alert alert-info">
								Currently there are no roles.
							</div>
						</s:else>
					</div>
				</div>
			</div>
		</div>
	</div>
<span id="loginRoleName" style="display: none;"><s:property
		value="user.userRoleDescription" /> </span>
<script type="text/javascript">
$(document).ready(
		function() {
		changePageTitle("Role Wise Staff Details");
		 $("input:checkbox, input:radio").uniform();
			$('span.hidden-title').html($('.page-sidebar-menu li.active').find('li.active').children('a').children('span.title').text().trim() + "-->"
		+ $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim())
		var title = $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim();
	});
	$("input[name=chkBoxSelectedIds]").click(function() {
		if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
		   $(".allRoles").parent('span').removeClass("checked");
			$(".allRoles").attr("checked", false);
		} else {
		    $(".allRoles").parent('span').addClass("checked");
			$(".allRoles").attr("checked", true);
		}
	});
function reportFormate() {
	$('.anyId').val('Excel');
}

function checkAllRoles() {
		if ($(".allRoles").is(':checked')){
		    $("[name='chkBoxSelectedIds']").each(function(){
			   $(this).parent('span').addClass('checked');
			    $(this).attr("checked", "true");
			});
		}
		else{
		 $("[name='chkBoxSelectedIds']").each(function(){
			   $(this).parent('span').removeClass('checked');
			    $(this).removeAttr("checked");
			});
		}	
	}
	
function generateStaffRoleIds() {
	if ($("input[name=chkBoxSelectedIds]:checked").length > 0) {
		var roleIds = $("input[name=chkBoxSelectedIds]:checked");
		var selectedRoleIds = '';
		if (roleIds.length > 0) {
			selectedRoleIds = '(';
			for ( var i = 0; i < roleIds.length; i++) {
				selectedRoleIds += roleIds[i].value + ', ';
			}
			selectedRoleIds += '0)';
		}
		$("#satffRoleIds").val(selectedRoleIds);
		return true;
	} else if ($("input[name=chkBoxSelectedIds]:checked").length == 0) {
		alert("Please select at least one Role");
		return false;
	} else {
		return false;
	}
}
</script>