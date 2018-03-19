<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<s:form action="ajaxMonthwiseStudentAttendanceTemplate" theme="simple"
	namespace="/reports" cssClass="form-horizontal"
	onsubmit="javascript:return validateFormFields();">
	<s:hidden name="tempString" id="classesInfo"></s:hidden>
	<s:hidden name="anyTitle" id="stdClases"></s:hidden>
	<s:hidden id="admisnum" name="anyId"></s:hidden>
	<s:if test="%{studyClassList != null && !studyClassList.isEmpty()}">
		<s:if test="%{monthNamesList != null && !monthNamesList.isEmpty()}">
			<div class="form-body">
				<div class="form-group">
					<div class="panel-body col-md-12">
						<div class="col-md-1">
							<span class="label label-danger"> NOTE : </span>
						</div>
						<div class="col-md-10">
							<ul>
								<li>
									Select classes and months to which you need to add attendance.
								</li>
								<li>
									Click on 'Download Template' button to download students
									attendance template.
								</li>
								<li>
									Fill up the attendance template with students attendance data
									from selected classes and months.
								</li>
								<li>
									It is mandatory to add the total working days before adding the present days.
								</li>
							</ul>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-12">
						<div class="checkbox-list">
							<label class="checkbox-inline">
								<input type="checkbox" name="selectAllclass" id="wrkAllClass"
									onClick="javascript:checkAllClasses()"
									class="checkbox allClasses">
								Select All Classes
							</label>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="conLable col-md-3 control-label">
						Classes :
					</label>
					<div class="col-md-12">
						<div class="checkbox-list">
							<s:checkboxlist name="selectBoxIdList" list="studyClassList"
								listValue="classAndSection" listKey="id" theme="ems"
								cssClass="small" />
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-12">
						<div class="checkbox-list">
							<label class="checkbox-inline">
								<input type="checkbox" name="selectAllMonth" id="wrkAllMonth"
									onClick="javascript:selectAllMonths()"
									class="checkbox wrkgMonths">
								Select All Months
							</label>
						</div>
						<s:checkboxlist name="chkBoxSelectedIds" list="monthNamesList"
							listValue="key" theme="ems" cssClass="small" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" style="width: 130px;">
						Sort Students By :
					</label>
					<div class="radio-list">
						<label class="radio-inline">
							<input type="radio" name="SelectType"
								value="firstName" checked>
							 Student Name
						</label>
						<label class="radio-inline">
							<input type="radio" name="SelectType"
								value="rollNumber"
								>
							 Roll Number
						</label>
						<label class="radio-inline">
							<input type="radio" name="SelectType" value="admissionNumber"
								>
							 Admission Number
						</label>
					</div>
			    </div>
				<div class="form-actions fluid">
					<div class="col-md-offset-3 col-md-9">
						<s:submit type="submit small btn blue" value="Download Template"
							cssClass="btn blue long" title="generate report"
							cssStyle="cursor:pointer">
						</s:submit>
					</div>
				</div>
			</div>
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
</s:form>
<div class="spaceDiv"></div>
<s:form action="ajaxImportStudentsAttendance"
	id="importStudentExcelSheet" namespace="/admin" method="post"
	theme="simple" enctype="multipart/form-data" name=""
	cssClass="form-horizontal">
	<div class="form-body">
		<div class="form-group">
			<div class="panel-body col-md-12">
				<div class="col-md-1">
					<span class="label label-danger"> NOTE : </span>
				</div>
				<div class="col-md-10">
					<ul>
						<li>
							Click on 'Browse' button to select attendance template.
						</li>
						<li>
							Click on 'Submit' button to add attendance into the system.
						</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-4">
				<span class="required">*</span>Select Students Import
				Template(Excel) :
			</label>
			<div class="col-md-4">
				<div class="fileupload fileupload-new" data-provides="fileupload">
					<div class="input-append">
						<span class="btn default"> <s:file name="upload"
								type="file" value="" id="photoURL" cssClass="required">
							</s:file> </span>
					</div>
				</div>
			</div>
		</div>
		<s:if test='%{#session.previousYear == "N"}'>
			<div class="form-actions fluid">
				<div class="col-md-offset-4 col-md-9">
					<sj:submit targets="attendanceCont" value="Submit"
						indicator="indicator" cssClass="submit small btn blue"
						formIds="importStudentExcelSheet" validate="true" />
				</div>
			</div>
		</s:if>
	</div>
</s:form>
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
function validateFormFields(){
	if (($("input[name=chkBoxSelectedIds]:checked").length > 0)
			&& ($("input[name=selectBoxIdList]:checked").length > 0)) {
		var jsonObj = [];
		var classNames = '';
		$("input[name=selectBoxIdList]").each(
				function() {
				var parentTag = $( this ).parent('span').attr('class');
				if(parentTag == 'checked'){
					classNames += $(this).parents('label').text().replace(/^\s+|\s+$/g,' ');
					jsonObj.push( {"className" : $(this).parents('label').text().replace(/^\s+|\s+$/g,' '),
					"classSectionId" : $(this).val()});
				}
		});
		$('#classesInfo').val(JSON.stringify(jsonObj));
		$('#stdClases').val(classNames);
		var admissionNumber = $('input[name=SelectType]:radio:checked').val();
		$("#admisnum").val(admissionNumber);
		return true;
	} else if (($("input[name=chkBoxSelectedIds]:checked").length == 0)
			&& ($("input[name=selectBoxIdList]:checked").length > 0)) {
		alert("Please select at least one month.");
		return false;
	} else if (($("input[name=chkBoxSelectedIds]:checked").length > 0)
			&& ($("input[name=selectBoxIdList]:checked").length == 0)) {
		alert("Please select at least one class.");
		return false;
	} else if (($("input[name=chkBoxSelectedIds]:checked").length == 0)
			&& ($("input[name=selectBoxIdList]:checked").length == 0)) {
		alert("Please select at least one class and month.");
		return false;
	}
}

</script>