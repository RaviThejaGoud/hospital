<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<s:form id="downloadTemplate" action="ajaxUploadEmailsToMailchimp"
	cssClass="form-horizontal" enctype="multipart/form-data" method="post"
	theme="simple" namespace="/admin"
	>
	<s:hidden id="classNameIds" name="selectedId" />
	<s:hidden id="classNames" name="tempString" />
	<s:hidden id="admisnum" name="anyId"></s:hidden>
	<h4 class="bold pageTitle">
		Upload Emails To Mailchimp
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
							Please upload your student emails address to your mailchimp account.
						</li>
						<!-- <li>
							<font color="red">Please do not add new columns or delete
								the marked columns</font>. If you want add more columns, please contact
							EazySchool support team(support@eazyschool.com).
						</li>
						<li>
							You can update the existing student information or add the new students information into the downloaded sheet
						</li> -->
					</ul>
				</div>
			</div>
		</div>
		<div class="form-body">
			<s:if test="%{studyClassList != null && !studyClassList.isEmpty()}">
			
			<div class="form-group">
				<div class="col-md-6">
					<label class="control-label col-md-5">
						<span class="required">*</span>Lists :
					</label>
					<div class="col-md-6">
						<s:select id="state" list="selectboxMap" label="Lists"  
							cssClass="form-control required"
							headerKey="" headerValue="- Select -" name="listId" />
					</div>
				</div>
			</div>
		
		
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
			<!-- <div class="form-group">
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
			</div> -->
			</div>
		</div>
		</div>
		<s:if test="%{studyClassList != null && !studyClassList.isEmpty()}">
			<div class="form-actions fluid">
				<div class="col-md-offset-4 col-md-9">
				
				<sj:submit cssClass="submitBt btn blue" value="Submit"
					formIds="downloadTemplate" targets="mailChimpDivId" validate="true"
					onBeforeTopics="uploadEmailsToMailchimpSubscribeId" /> 
					
					
					<%-- <s:submit value="Upload" cssClass="btn blue long"/> --%>
					<%-- <s:url id="doCancelStudent" action="ajaxGetStudyClassList"
						includeParams="all" namespace="/student"></s:url>
					<sj:a href="%{doCancelStudent}" cssClass="btn default"
						targets="mainContentDiv" button="false">Cancel</sj:a> --%>
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

<script type="text/javascript">



$.subscribe('uploadEmailsToMailchimpSubscribeId', function(event, data) {
		if ($('#downloadTemplate').valid()){
			generateClassIds();
			return true;
		}
		else
			event.originalEvent.options.submit=false;
	}); 
	
	
	$(document).ready(function() {
		changePageTitle('Upload Emails To Mailchimp');
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
			var selectedClassIds = '';
			var selectedClassNames = '';
				selectedClassIds = '(';
				for ( var i = 0; i < classIds.length; i++) {
					selectedClassIds += classIds[i].value + ', ';
				}
				selectedClassIds += '0)';
				
			$("#classNameIds").val(selectedClassIds);
			$("#admisnum").val(admissionNumber);
			
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