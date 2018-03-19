<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<s:form id="addNewClass" action="marksSheetTemplateReport" method="post" onsubmit="javascript:return getSubmitErrors();"
	theme="css_xhtml">
	<div id="addSubjectFieldErrors"></div>
	<table width="300px">
		<tr>
			<td>
				<s:if test="{selectboxMap != NULL & !selectboxMap.isEmpty()}">
					<!-- Here is the list key studyClassId is studyClassId -->
					<div class="tableactions" style="padding-bottom: 0px;">
						<s:select list="selectboxMap" id="className" label="Select Class"
							cssClass="required" required="true" name="studyClass.className"
							headerKey="" headerValue="- Select -"  
							onchange="javascript:getAjaxDoGetSubjects(this.value);"
							requiredposition="first">
						</s:select>
					</div>
				</s:if>
			</td>
		</tr>
		
		<tr>
			<td>
				<div id="teacherSubjects"></div>
			</td>
		</tr>
		<tr>
			<td>
			</td>
		</tr>
		
		<tr>
			<td>
				<div id="teacherResults"></div>
			</td>
		</tr>
	</table>
	</s:form>
<div class="clear"></div>
<script type="text/javascript">
changePageTitle("My Messages");

	function getAjaxDoGetSubjects(classNameAndSectionName) {
		var pars = "classNameAndSectionName=" + classNameAndSectionName;
		$("#teacherSubjects").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var url = jQuery.url.getChatURL("/staff/ajaxGetStudentsList.do");
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#teacherSubjects").html(html);
			}
		});
	}
	
		
	
	function getSubmitErrors(){
            var parameters ='';
            var fieldErrorString ='';
            var classNameErrors = $("#className").val().trim();
            var teachreSubjectsListErrors = $("#teachreSubjectsList").val();
            var teachreSubjectsListErrorsTrim=$.trim(teachreSubjectsListErrors);
            var examTypeError=$("#examType").val().trim();
            var academicYearError=$("#academicYear").val().trim();
            var maxmarksError=$("#maxmarks").val().trim();
            var minMarksError=$("#minMarks").val().trim();
            if(examTypeError == ''){
               fieldErrorString = fieldErrorString + "<font style=\"color:red;font-weight:bold;\">Type of Exam is required.<br /></font>";
            }
            if(academicYearError == ''){
               fieldErrorString = fieldErrorString + "<font style=\"color:red;font-weight:bold;\">Academic Year is required.<br /></font>";
            }
            if(maxmarksError == ''){
               fieldErrorString = fieldErrorString + "<font style=\"color:red;font-weight:bold;\">Max Marks is required.<br /></font>";
            }
            if(minMarksError == ''){
               fieldErrorString = fieldErrorString + "<font style=\"color:red;font-weight:bold;\">Pass Marks is required.<br /></font>";
            }
            if(classNameErrors == ''){
               fieldErrorString = fieldErrorString + "<font style=\"color:red;font-weight:bold;\">Select Class is required.<br /></font>";
            }
            if(classNameErrors != ''){
	            if(teachreSubjectsListErrors == ''){
	               fieldErrorString = fieldErrorString + "<font style=\"color:red;font-weight:bold;\">Select Subject is required.<br /></font>";
	            }
            }
           if (fieldErrorString!='') {
               document.getElementById('addSubjectFieldErrors').innerHTML= fieldErrorString;
               document.getElementById('addSubjectFieldErrors').style.display = "block";
               return false;
               }
               else{
               document.getElementById('addSubjectFieldErrors').innerHTML= "";
               document.getElementById('addSubjectFieldErrors').style.display = "none";  
               return true;
           }  
       }
       
</script>