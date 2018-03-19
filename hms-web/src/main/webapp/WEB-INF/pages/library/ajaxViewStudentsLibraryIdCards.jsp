<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>
					<span class="hidden-481"> </span>Library Id Card Generation
				</div>
			</div>
			<div class="portlet-body">
				<div class="tab-content">
					<div class="row">
						<div class="col-md-12 form-horizontal">
							<div class="form-group">
								<label class="col-md-2 control-label">Select User :
								</label>
								<div class="col-md-9">
									<div class="make-switch has-switch" data-id="ST" data-value="S"
										style="width: 480px" data-off="warning" data-on="success"
										data-off-label="Staff Library Id Card Model" data-on-label="Student Library Id Card Model ">
										<input type="radio" class="toggle" checked="checked" id="idcardGeneration"> 
										<input type="hidden" name="" value="ST">
									</div>
								</div>
							</div>
						</div>
					</div>
					<s:if test='%{customer != null}'>
					<div id="studentformIdCards">
						<s:form name="myformIdCards" namespace="/reports" id="LibIdCardGenForm" action="ajaxAllStudentIdCardGeneration" theme="simple" cssClass="form-horizontal"
							onsubmit="javascript: return getClassIdsAndAdmissionNumber();">
							<input type="hidden" name="pdfId" value="pdf" />
							<input type="hidden" name="selectedId" id="classNameIds" />
							<div class="form-body">
								<div class="row">
									<div class="col-md-6">
										<div>
											<label>
												Student Library Id Card Model
											</label>
										</div>
										<table
											class="table table-striped table-bordered table-hover table-full-width"
											id="sample_4">
											<thead>
												<tr>
													<td>
													<div class="col-md-2 left" style="width: 125px;">
															<img src='<s:property  value="#session.custImage"/>' border="0" alt="logo" class="logoHeader" id="customerLogoDiv"/> 
														</div>
														<div class="col-md-5" style="width: 278px;">
															<b style="color: MediumVioletRed;"><s:property
																	value="customer.organization" /> </b>
															<br/>
															School Address
															<br />
															LIBRARY
														</div>
													</td>
												</tr>
												<tr>
													<td>
														<div class="col-md-8">
															<div class="col-md-8">
																<label>
																	Name :
																</label>
																Jokab.C
															</div>
															<div class="col-md-8">
																<label>
																	Roll No:
																</label>

																24
															</div>
															<div class="col-md-8">

																<label>
																	Class :
																</label>
																Arts
															</div>
															<div class="col-md-8">
																<label>
																	Academic Year :
																</label>
																2017-2018
															</div>
															
															<div class="col-md-10">
																<label>
																	Phone No :
																</label>
																080-46620999
															</div>
														</div>
														<div class="col-md-7 left " style="width: 140px;">
															<div>
																<img src="<c:url value='../images/nophoto.jpg'/>"
																	height="150px" width="122px;"></img>
															</div>
															<div>
																&nbsp;
															</div>
															<div align="center">
																LIBRARIAN
															</div>
														</div>
														<div class="col-md-6" style="bottom: 18px;">
																<label>
																	Specimen Signature :
																</label>
																
															</div>
													</td>
												</tr>
												<tr>
													<td style="border-top: 1px solid #D6D6D6;">
														<div>
															<label>
																Address :
															</label>
															#8, First Floor, Near Navjeevan Ashram, HBR Layout, 4th Block, Bangalore - 560043.
															
							 							</div>
													</td>
												</tr>
											</thead>
										</table>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-1 control-label">
										<strong>Select : </strong>
									</label>
									<div class="radio-list">
										<label class="radio-inline">
											<input type="radio" name="SelectType" value="classSection"
												checked="checked">
											Class & Section
										</label>
									</div>
								</div>
								<div id="classAndSection">
									<s:if
										test="%{studyClassList != null && !studyClassList.isEmpty()}">
										<div class="form-group">
											<div class="col-md-12">
												<div class="checkbox-list">
													<label class="checkbox-inline">
														<input type="checkbox" name="" value=""
															onClick="checkAllClasses()" class="checkbox allClasses">
														All Class & Sections
													</label>
												</div>
											</div>
										</div>
										<div class="form-group">
											<label class="conLable col-md-3 control-label">
												Class With Students :
											</label>
											<div class="col-md-12">
												<div class="checkbox-list">
													<s:checkboxlist name="chkBoxSelectedIds"
														list="studyClassList" listKey="id"
														listValue="classAndSection" theme="ems" cssClass="small" />
												</div>
											</div>
										</div>
										<s:if test='%{tempList2.size >0}'>
											<div class="form-group">
												<label class="conLable col-md-3 control-label">
													  Class With Out Students :
												</label>
												<div class="col-md-12">
													<div class="checkbox-list">
														<s:checkboxlist name="chkBoxNotStudents" list="tempList2"
															listKey="id" listValue="classAndSection" theme="ems"
															cssClass="small" disabled="true" />
													</div>
												</div>
											</div>
										</s:if>
										<div id="generateId">
											<div class="form-actions fluid">
												<div class="col-md-6">
													<div class="col-md-offset-3 col-md-12">
														<s:submit value="Generate ID Cards"
															cssClass="submitBt btn blue long" id="LibIdCardGenForm"></s:submit>
													</div>
												</div>
											</div>
										</div>
									</s:if>
									<s:else>
										<div class="alert alert-info">
											Currently there are no classes.
										</div>
									</s:else>
								</div>
							</div>
						</s:form>
					</div>
					<div id="staffformIdCards" style="display: none;">
						<s:form name="staffformIdCards" namespace="/reports" id="LibstaffIdCardGenForm" action="ajaxAllStaffIdCardGeneration" theme="simple" cssClass="form-horizontal"
							onsubmit="javascript: return getAllStaffLibraryIdCards();">
							<input type="hidden" name="pdfId" value="pdf" />
							<s:hidden id="teachingRoleNameIds" name="plTitle" />
							<div class="form-body">
								<div class="row">
									<div class="col-md-6">
										<div>
											<label>
												Staff Library Id Card Model
											</label>
										</div>
										<table
											class="table table-striped table-bordered table-hover table-full-width"
											id="sample_4">
											<thead>
												<tr>
													<td>
													<div class="col-md-2 left" style="width: 125px;">
															<img src='<s:property  value="#session.custImage"/>' border="0" alt="logo" class="logoHeader" id="customerLogoDiv"/>
														</div>
														<div class="col-md-4" style="width: 278px;">
															<b style="color: MediumVioletRed;"><s:property
																	value="customer.organization" /> </b>
															<br/>
															School Address
															<br />
															LIBRARY
														</div>
													</td>
												</tr>
												<tr>
													<td>
														<div class="col-md-8">
															<div class="col-md-8">
																<label>
																	Name :
																</label>
																Jokab.C
															</div>
															<div class="col-md-8">
																<label>
																	Academic Year :
																</label>
																2014-2015
															</div>
															
															<div class="col-md-10">
																<label>
																	Phone No :
																</label>
																+080-46620999
															</div>
														</div>
														<div class="col-md-7 left " style="width: 140px;">
															<div>
																<img src="<c:url value='../images/nophoto.jpg'/>"
																	height="150px" width="122px;"></img>
															</div>
															<div>
																&nbsp;
															</div>
															<div align="center">
																LIBRARIAN
															</div>
														</div>
														<div class="col-md-6" style="bottom: 18px;">
																<label>
																	Specimen Signature :
																</label>
																
															</div>
													</td>
												</tr>
												<tr>
													<td style="border-top: 1px solid #D6D6D6;">
														<div>
															<label>
																Address :
															</label>
															#208B, 1st stage HBR Layout,Bangalore.
															
														</div>
													</td>
												</tr>
											</thead>
										</table>
									</div>
								</div>
								<div id="classAndSection">
									<div class="form-group">
										<label class="conLable col-md-2 control-label">
											<span class="required">*</span> Teaching Roles :
										</label>
										<div class="col-md-12">
											<div class="checkbox-list">
												<label class="checkbox-inline">
													<input type="checkbox" name="" value=""
															onClick="checkAllTeachingRole()" class="checkbox teachingRole">
													All Teaching Roles
												</label>
											</div>
											<s:checkboxlist name="teachingRole" list="teachingRoleMap"
												theme="ems" cssClass="small staffRoles" />
										</div>
									</div>
								</div>
								<div id="generateId">
											<div class="form-actions fluid">
												<div class="col-md-6">
													<div class="col-md-offset-3 col-md-12">
														<s:submit value="Generate ID Cards"
															cssClass="submitBt btn blue long" id="LibstaffIdCardGenForm"></s:submit>
													</div>
												</div>
											</div>
										</div>
							</div>
						</s:form>
						</div>
					</s:if>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrap-switch/static/js/bootstrap-switch.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("input:checkbox, input:radio:not('.toggle')") .uniform();
});
function checkAllClasses() {
	if ($(".allClasses").is(':checked')) {
		$("[name='chkBoxSelectedIds']").each(function() {
			$(this).parent('span').addClass('checked');
			$(this).attr("checked", "true");
		});
	} else {
		$(".allClasses").parent('span').removeClass('checked');
		$(".allClasses").removeAttr("checked");
		$("[name='chkBoxSelectedIds']").each(function() {
			$(this).parent('span').removeClass('checked');
			$(this).removeAttr("checked");
		});
	}
}
$("input[name=chkBoxSelectedIds]").click(function() {
	if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
		$(".allClasses").parent('span').removeClass("checked");
		$(".allClasses").attr("checked", false);
	} else {
		$(".allClasses").parent('span').addClass("checked");
		$(".allClasses").attr("checked", true);
	}
});

function checkAllTeachingRole() {
	if ($(".teachingRole").is(':checked')){
	    $("[name='teachingRole']").each(function(){
		   $(this).parent('span').addClass('checked');
		    $(this).attr("checked", "true");
		});
	}
	else{
	 $("[name='teachingRole']").each(function(){
		   $(this).parent('span').removeClass('checked');
		    $(this).removeAttr("checked");
		});
	}	
}
$("input[name=teachingRole]").click(function() {
	if ($("input[name=teachingRole]:unchecked").length > 0) {
		$(".teachingRole").parent('span').removeClass("checked");
		$(".teachingRole").attr("checked", false);
	} else {
		$(".teachingRole").parent('span').addClass("checked");
		$(".teachingRole").attr("checked", true);
	}
});

 
function getClassIdsAndAdmissionNumber() {
	var selected = $('input[name=SelectType]:radio:checked').val();
	// alert('selected:'+selected)
	if (selected == 'classSection') {
		if ($("input[name=chkBoxSelectedIds]:checked").length > 0) {
			var classIds = $("input[name=chkBoxSelectedIds]:checked");
			var selectedClassNames = [];
			var selectedClassIds = [];
			if (classIds.length > 0) {
				$(classIds).each(function() {
					if (isNonEmpty($(this).val()) && isNonEmpty($(this).parents('label').text())) {
						selectedClassIds.push($(this).val());
						selectedClassNames.push($(this).parents('label').text().trim())
					}
				});
			}
			$("#classNameIds").val("(" + selectedClassIds + ")");
			$("#classNames").val(selectedClassNames);
			return true;
		} else {
			alert("Please select at least one class.");
			return false;
		}
	}
}
$('div.make-switch').find("label[for='idcardGeneration']").click(function(){
	//alert($("label[for='idcardGeneration']").parent().hasClass('switch-off'));
	if($("label[for='idcardGeneration']").parent().hasClass('switch-off')){
		$('div#studentformIdCards').show();
		$('div#staffformIdCards').hide();
	}else{
		$('div#staffformIdCards').show();
		$('div#studentformIdCards').hide();
		
	}
})
function getAllStaffLibraryIdCards() {
		if ($("input[name=teachingRole]:checked").length > 0 ) {
			//Teaching
			var teachingRoleIds = $("input[name=teachingRole]:checked");
			var selectedteachingRoleIds = '';
			selectedteachingRoleIds = '(';
			if (teachingRoleIds.length > 0) {
				for ( var i = 0; i < teachingRoleIds.length; i++) {
					selectedteachingRoleIds += "'"+teachingRoleIds[i].value +"'"+ ',';
				}
			}
			selectedteachingRoleIds += '0)';
		$("#teachingRoleNameIds").val(selectedteachingRoleIds);
		} else if ($("input[name=teachingRole]:checked").length == 0) {
			if ($("input[name=teachingRole]:checked").length == 0) {
				alert("Please select at least one Role.");
			}
			return false;
		} else {
			return false;
		}
	}
</script>