<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<s:form id="downloadTemplate" action="ajaxDownloadStudent"
	cssClass="form-horizontal" enctype="multipart/form-data" method="post"
	theme="simple" namespace="/student"
	onsubmit="javascript:return generateClassIds();">
	<s:hidden id="classNameIds" name="selectedId" />
	<s:hidden id="classNames" name="tempString" />
	<s:hidden id="admisnum" name="anyId"></s:hidden>
	<s:hidden id="selectType" name="tempString1"></s:hidden>
	<h4 class="bold pageTitle">
		Download students 
	</h4>
	<div class="form-body">
		<div class="form-group">
			<div class="panel-body col-md-12">
				<div class="col-md-1">
					<span class="label label-danger"> NOTE : </span>
				</div>
				<div class="col-md-10">
					<ul>
						<li>
							Please download the student details by clicking the "Download
							Students" button below.The downloaded excel sheet contains all students
							details in the system.
						</li>
						<li>
							<font color="red">Please do not add new columns or delete
								the marked columns</font>. If you want add more columns, please contact
							EazySchool support team(support@eazyschool.com).
						</li>
						<li>
							You can update the existing student information or add the new students information into the downloaded sheet
						</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="form-body">
			<s:if test="%{studyClassList != null && !studyClassList.isEmpty()}">
				<div class="form-group">
					<div class="col-md-11">
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
					<label>
						Class With Students:
					</label>
					<div class="col-md-11">
						<div class="checkbox-list">
							<s:checkboxlist list="studyClassList" name="chkBoxSelectedIds"
								listKey="id" listValue="classAndSection" theme="ems"></s:checkboxlist>
						</div>
					</div>
				</div>
			</s:if>
			<s:if test='%{tempList2.size >0}'>
				<div class="form-group">
					<label>
						Class With Out Students:
					</label>
					<div class="col-md-11">
						<s:checkboxlist list="tempList2" name="chkBoxNotStudents"
							listKey="id" listValue="classAndSection" theme="ems"
							disabled="true"></s:checkboxlist>

					</div>
				</div>
			</s:if>
			<div class="form-group">
				<label class="col-md-2 control-label" style="width: 130px;">
					Sort Students By :
				</label>
				<div class="radio-list">
					<label class="radio-inline">
						<input type="radio" name="SelectType"
							value="firstName" onclick="handleClick(this.value);" checked>
						 Student Name
					</label>
					<label class="radio-inline">
						<input type="radio" name="SelectType"
							value="rollNumber"
							onclick="handleClick(this.value);">
						 Roll Number
					</label>
					<label class="radio-inline">
						<input type="radio" name="SelectType" value="admissionNumber"
							onclick="handleClick(this.value);">
						 Admission Number
					</label>
				</div>
			</div>
			</div>
		</div>
		</div>
		<s:if test="%{studyClassList != null && !studyClassList.isEmpty()}">
			<div class="form-actions fluid">
				<div class="col-md-offset-4 col-md-9">
					<s:submit value="Download Students" cssClass="btn blue long" />
					<s:url id="doCancelStudent" action="ajaxGetStudyClassList"
						includeParams="all" namespace="/student"></s:url>
					<sj:a href="%{doCancelStudent}" cssClass="btn default"
						targets="mainContentDiv" button="false">Cancel</sj:a>
				</div>
			</div>
		</s:if>
		<s:else>
			<div class="alert alert-info">
				Currently there are no students.
			</div>
		</s:else>
	</div>
</s:form>
<div class="form-body">
	<s:if test='%{#session.previousYear == "N"}'>
		<s:if test="%{studyClassList != null && !studyClassList.isEmpty()}">
			<s:form action="ajaxUploadStudentData" id="importStudentExcelSheet"
				cssClass="form-horizontal" namespace="/student" method="post"
				theme="simple" enctype="multipart/form-data" name="">
				<s:hidden name="tempString" value="Edit"></s:hidden>
				<h4 class="bold pageTitle">
					Upload students 
				</h4>
				<div class="panel-body col-md-12">
					<div class="col-md-1">
						<span class="label label-danger"> NOTE : </span>
					</div>
					<div class="col-md-10">
						<ul>
							<li>
								The upload excel sheet must be downloaded from the above
								"Download Students" option.
							</li>
							<li>
								The system will not able to process the upload successfully
								<font color="red">if any columns are changed or inserted</font>
								in the template.
							</li>
							<li>
								The system will generate the update status if any rows are not
								proceed due to data errors.
							</li>
						</ul>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-4">
						<span class="required">*</span>Select students updated template(Excel) :
					</label>
					<div class="col-md-4">
						<div class="fileupload fileupload-new" data-provides="fileupload">
							<div class="input-append">
								<span class="btn default"> <s:file name="upload" onchange="validateExcelSheet(this);"
										type="file" value="" id="photoURL" cssClass="required">
									</s:file> </span>
							</div>
						</div>
					</div>
				</div>
				<div class="form-actions fluid">
					<div class="col-md-offset-4 col-md-9">
						<sj:submit targets="staffList" value="Upload Students"
							indicator="indicator" cssClass="btn blue long"
							formIds="importStudentExcelSheet" validate="true" />
					</div>
				</div>
			</s:form>
		</s:if>
	</s:if>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		changePageTitle('Download Student Details');
		$("#searchStudentsList").hide();
		$("input:checkbox, input:radio").uniform();  
	});
	function getMarksSubmitErrors(){
		var classSectionId=$("#classSection").val();
		if(isNonEmpty(classSectionId)){
			$('#selectedClassName').val($("select[id='classSection'] option:selected").text());
			return true;
		}
		else{
			alert("Please select class.");
			return false;
		}
	  }
	  
	 function checkAllClasses() {
		if ($(".allClasses").is(':checked')){
		    $("input[name='chkBoxSelectedIds']").each(function(){
			   $(this).parent('span').addClass('checked');
			    $(this).attr("checked", "true");
			});
		}
		else{
		 $("input[name='chkBoxSelectedIds']").each(function(){
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
		
	function generateClassIds() {
		if ($("input[name=chkBoxSelectedIds]:checked").length > 0) {
			var classIds = $("input[name=chkBoxSelectedIds]:checked");
			var admissionNumber = $('input[name=SelectType]:radio:checked').val();
			var selectedType=$('input[name=SelectType]:radio:checked').val();
			var selectedClassIds = '';
			var selectedClassNames = '';
				selectedClassIds = '(';
				for ( var i = 0; i < classIds.length; i++) {
					selectedClassIds += classIds[i].value + ', ';
				}
				selectedClassIds += '0)';
				
			$("#classNameIds").val(selectedClassIds);
			$("#admisnum").val(admissionNumber);
			$("#selectType").val(selectedType);
			
			
			//$("#classNames").val(selectedClassNames);
			return true;
		} else if ($("input[name=chkBoxSelectedIds]:checked").length == 0) {
			alert("Please select at least one Class");
			return false;
		} else {
			return false;
		}
	}
</script>