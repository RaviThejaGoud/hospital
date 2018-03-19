<%@ include file="/common/taglibs.jsp"%>
<div id="tab_1" class="tab-pane active">
	<div class="portlet box blue">
		<div class="portlet-body form">
			<s:if
				test="%{(studyClassList != null && !studyClassList.isEmpty()) || (objectList != null && !objectList.isEmpty())}">
				<s:if test="%{monthNamesList != null && !monthNamesList.isEmpty()}">
					<s:form action="ajaxSendStudentMonthlyAttendanceToParents"
						theme="simple" namespace="/student" cssClass="form-horizontal">
						<s:hidden name="tempString" id="classesInfo"></s:hidden>
						<div class="form-body">
							<div class="form-group">
								<label class="conLable col-md-3 control-label">
									<span class="required">*</span> Select Classes :
								</label>
								<div class="col-md-12">
									<div class="checkbox-list">
										<label class="checkbox-inline">
											<input type="checkbox" name="selectAllclass" id="wrkAllClass"
												onClick="javascript:checkAllClasses()"
												class="checkbox allClasses">
											Select all classes
										</label>
									</div>
								</div>
							</div>
							<s:if
								test='%{studyClassList != null && !studyClassList.isEmpty()}'>
								<div class="form-group">
									<label class="conLable col-md-3 control-label">
										Class With Students :
									</label>
									<div class="col-md-12">
										<div class="checkbox-list">
											<s:checkboxlist name="selectBoxIdList" list="studyClassList"
												listValue="classAndSection" listKey="id" theme="ems"
												cssClass="small" />
										</div>
									</div>
								</div>
							</s:if>
							<s:if test='%{objectList != null && !objectList.isEmpty()}'>
								<div class="form-group">
									<label class="conLable col-md-3 control-label">
										Class With Out Students :
									</label>
									<div class="col-md-12">
										<div class="checkbox-list">
											<s:checkboxlist name="chkBoxNotStudents" list="objectList"
												listValue="classAndSection" listKey="id" theme="ems"
												cssClass="small" disabled="true" />
										</div>
									</div>
								</div>
							</s:if>
							<div class="form-group">
								<label class="conLable col-md-3 control-label">
									<span class="required">*</span> Select Months :
								</label>
								<div class="col-md-12">
									<div class="checkbox-list">
										<label class="checkbox-inline">
											<input type="checkbox" name="selectAllMonth" id="wrkAllMonth"
												onClick="javascript:selectAllMonths()"
												class="checkbox wrkgMonths">
											Select all months
										</label>
									</div>
									<s:checkboxlist name="chkBoxSelectedIds" list="monthNamesList"
										listValue="key" theme="ems" cssClass="small" />
								</div>
							</div>
							<div class="form-actions fluid">
								<div class="col-md-6">
									<div class="col-md-offset-3 col-md-12">
										<sj:submit   type="submit small btn blue" value="Send to Mail"
											targets="studentMonthlyAttte" validate="true"
											cssClass="long submit" title="generate report"
											onclick="javascript:return validateFormFields();"
											cssStyle="cursor:pointer">
										</sj:submit>
									</div>
								</div>
							</div>
						</div>
					</s:form>
				</s:if>
				<s:else>
					<div class="alert alert-info">
						Please define academic year planner.
					</div>
				</s:else>
			</s:if>
			<s:else>
				<div class="alert alert-info">
					No classes found for creating attendance.
				</div>
			</s:else>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$("input:checkbox, input:radio").uniform();
	$("input[name=chkBoxSelectedIds]").click(function() {
		if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
		   $(".wrkgMonths").parent('span').removeClass("checked");
			$(".wrkgMonths").attr("checked", false);
		} else {
		    $(".wrkgMonths").parent('span').addClass("checked");
			$(".wrkgMonths").attr("checked", true);
		}
	});
	$("input[name=selectBoxIdList]").click(function() {
		if ($("input[name=selectBoxIdList]:unchecked").length > 0) {
		   $(".allClasses").parent('span').removeClass("checked");
			$(".allClasses").attr("checked", false);
		} else {
		    $(".allClasses").parent('span').addClass("checked");
			$(".allClasses").attr("checked", true);
		}
	});
});

function selectAllMonths() {
	if ($(".wrkgMonths").is(':checked')){
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
function checkAllClasses() {
		if ($(".allClasses").is(':checked')){
		    $("[name='selectBoxIdList']").each(function(){
			   $(this).parent('span').addClass('checked');
			    $(this).attr("checked", "true");
			});
		}
		else{
		 $("[name='selectBoxIdList']").each(function(){
			   $(this).parent('span').removeClass('checked');
			    $(this).removeAttr("checked");
			});
		}	
	}
function validateFormFields() {
	if (($("input[name=chkBoxSelectedIds]:checked").length > 0)
			&& ($("input[name=selectBoxIdList]:checked").length > 0)) {
		var jsonObj = [];
		var classNames = '';
		$("input[name=selectBoxIdList]:checked").each(
				function() {
					classNames += $(this).parent().text().replace(/^\s+|\s+$/g,
							' ');
					jsonObj.push( {
						"className" : $(this).parent().text().replace(
								/^\s+|\s+$/g, ' '),
						"classSectionId" : $(this).val()
					});
				});
		$('#classesInfo').val(JSON.stringify(jsonObj));
	} else if (($("input[name=chkBoxSelectedIds]:checked").length == 0)
			&& ($("input[name=selectBoxIdList]:checked").length > 0)) {
		alert("Please select at least one month.");
		event.originalEvent.options.submit = false;
	} else if (($("input[name=chkBoxSelectedIds]:checked").length > 0)
			&& ($("input[name=selectBoxIdList]:checked").length == 0)) {
		alert("Please select at least one class.");
		event.originalEvent.options.submit = false;
	} else if (($("input[name=chkBoxSelectedIds]:checked").length == 0)
			&& ($("input[name=selectBoxIdList]:checked").length == 0)) {
		alert("Please select at least one class and month.");
		event.originalEvent.options.submit = false;
	}
}
</script>