<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />
<div class="form-body">
	<s:if test="%{tempList != null && !tempList.isEmpty()}">
	<table class="table table-striped table-bordered table-hover table-full-width">
			<thead>
				<tr>
					<th>
						Role
					</th>
					<th>
						No Of Days
					</th>
					<th>
						Is Rolebased
					</th>
					 <th>
						Month (Or) Year
					</th>
					
				</tr>
			</thead>
			<tbody>
				<s:iterator value="tempList">
					<tr>
						<td>
							<s:property value="roleDescription" />
						</td>
						<td>
							<s:property value="days" />
						</td>
						<td>
							<s:property value="isRolebased" />
						</td>
						<td>
							<s:property value="monthOrYear" />
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</s:if>
	<s:else>
		<s:form id="addLatePermissions" action="ajaxAddLatePermissions"
			method="post" theme="simple" cssClass="form-horizontal" namespace="/admin">
			<s:hidden name="anyTitle" id='staffPermissionSettings' />
			<s:hidden name="tempString" value="%{status}" />
			<h4 class="pageTitle bold">
				<s:if test='%{status =="L"}'>
				 Add Late Settings
				</s:if>
				<s:else>
				 Add Permission Settings
				</s:else>
			</h4>
			<div class="form-group">
				<label class="control-label col-md-3"> Select Permissions : </label>
				<div class="col-md-4">
					<div class="radio-list">
						<label class="radio-inline"> <input type="radio"
							checked="checked" value="M"
							onclick="changePemissions(this.value);" name="addPermissions">
							Month Wise
						</label> <label class="radio-inline"> <input type="radio" value="Y"
							onclick="changePemissions(this.value);" name="addPermissions">
							Year Wise
						</label>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3"> <s:if
						test='%{status =="L"}'>
				  Do you want to copy same as permissions settings:
				</s:if> <s:else>
				  Do you want to copy same as late settings:
				</s:else>
				</label>
				<div class="col-md-4">
					<input type="checkbox" name="plTitle">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3"> Select Staff : </label>
				<div class="col-md-4">
					<div class="radio-list">
						<label class="radio-inline"> <input type="radio"
							checked="checked" value="A" onclick="changeRole(this.value);"
							name="checkStaffType"> All Staff
						</label> <label class="radio-inline"> <input type="radio" value="R"
							onclick="changeRole(this.value);" name="checkStaffType">
							Role Wise
						</label>
					</div>
				</div>
			</div>
			<div id="allRolesDiv">
				<div class="form-group">
					<label class="control-label col-md-3"> No.Of.Days for
						permission: </label>
					<div class="col-md-4">
						<input value="0" id="staffDays"
							class="required form-control input-small" width="60px"
							maxlength="1">
					</div>
				</div>
			</div>
			<div id="staffRolesDiv" style="display: none;">
				<s:if test="%{objectList != null && !objectList.isEmpty()}">
					<div class="form-group">
						<label class="control-label col-md-3"> <span
							class="required">*</span>Select Role :
						</label>
						<div class="col-md-9">
							<s:iterator value="objectList">
								<div class="col-md-3 daysList" id='<s:property value="id"/>'>
									<div class="form-group">
										<label class="col-md-7 control-label"><s:property
												value="description" />:</label>
										<div class="col-md-3">
											 <input value="0" id='noOfdays_<s:property value="id"/>' title='<s:property value="id"/>'
												class="required form-control numeric" style="width: 60px;"
												maxlength="1">
										</div>
									</div>
								</div>
							</s:iterator>
						</div>
					</div>
				</s:if>
				<s:else>
					<div class="alert alert-info">Currently there are no roles.</div>
				</s:else>
			</div>
			<div class="form-actions fluid">
				<div class="col-md-offset-3 col-md-9">
					<sj:submit value="Submit" cssClass="submitBt btn blue"
						onBeforeTopics="addLatePermissionsForm"
						formIds="addLatePermissions" targets="subjectsContentDiv"
						indicator="indicator" validate="true" />
					<s:url id="urlSchoolSettings" action="ajaxAcademicSchoolSettings"
						namespace="/admin">
						<s:param value='<s:property value="#session.academicYear" />'
							name="academicYearId">
						</s:param>
					</s:url>
					<sj:a href="%{urlSchoolSettings}" targets="mainContentDiv"
						cssClass="btn default">
							Cancel</sj:a>
				</div>
			</div>
		</s:form>
	</s:else>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		FormAdvanced.init();
		$("input:checkbox, input:radio:not('.toggle')").uniform();
		changePageTitle("add Permissions");
		$('.numeric').numeric();
		$.destroyTopic('addLatePermissionsForm');
	});
	function changePemissions(type) {
		if (type == 'M')
			$('#type').val('M');
		else
			$('#type').val('L');
	}
	function changeRole(type) {
		if (type == 'A') {
			$('#roleWise').val('A');
			$('#allRolesDiv').show();
			$('#staffRolesDiv').hide();
		} else {
			$('#roleWise').val('R');
			$('#staffRolesDiv').show();
			$('#allRolesDiv').hide();
		}

	}
	$.subscribe('addLatePermissionsForm', function(event, data) {
	     var jsona = [];
	     var noOfdays;
	     var roleId;
	     var plTitle;
	     var isRolebased = $('input:radio[name=checkStaffType]:checked').val();
	     var monthOrYear=$('input:radio[name=addPermissions]:checked').val();
	     var status='<s:property value="status"/>';
	     var pSettings= $("input[name='plTitle']:checked").val();
	     if(isNonEmpty(pSettings))
	    	  plTitle = pSettings;
	     else
	    	  plTitle = "";
	    // alert(plTitle+"---"+monthOrYear+"==="+status+"==="+noOfdays);
	     if(isRolebased=="A"){
	    	 noOfdays=$('input#staffDays').val();
	    	 jsona.push( {
					"noOfdays" : noOfdays,
					"roleId" : 0
				});
	     }else{
			$('div.daysList').each(function () {
				   if(isNonEmpty(status)){
					   noOfdays = $("input#noOfdays_"+$(this).attr("id")).val();
					   roleId = $(this).attr("id");
					   //alert(noOfdays+"---"+roleId+"==="+$(this).attr("id"));
					   jsona.push( {
							"noOfdays" : noOfdays,
							"roleId" : roleId
						});
				   }
			});
	     }
		var permissionData = {
				"permissionData": [ {"isRolebased" : isRolebased,"status":status,"monthOrYear":monthOrYear, "dayValues" : jsona} ]
			};
		if(isNonEmpty(permissionData) ){ 
			//alert(JSON.stringify(permissionData));
			$('#staffPermissionSettings').val(JSON.stringify(permissionData));
			//event.originalEvent.options.submit=false;
			return true;		
		}
		else{
			alert('Something gone wrong! Unable to read the response. Please reload the screen and try or contact system administrator');
			event.originalEvent.options.submit=false;
		}
	});
</script>

