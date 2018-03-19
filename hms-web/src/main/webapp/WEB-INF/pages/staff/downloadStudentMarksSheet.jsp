<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/common/jquery.alphanumeric.js"></script>
<s:form id="generateMarksSheet" action="downloadStudentMarksSheet" method="post" onsubmit="javascript:return checkFields();"
	theme="css_xhtml">
	<div id="addFieldErrors"></div>
	<table width="300px">
	    <tr>
		    <td>
		      <div class="tableactions" style="padding-bottom: 0px;">
					<s:select list="#{'Half Yearly':'Half Yearly','Quarterly':'Quarterly','Yearly':'Yearly'}" id="examType"  label="Type of Exam"
						cssClass="required" required="true" name="typeOfExam"
						headerKey="" headerValue="- Select -" 
						requiredposition="first">
					</s:select>
			  </div>
		     </td>
	    </tr>
		<tr>
			<td>
				<s:if test="{selectboxMap != NULL & !selectboxMap.isEmpty()}">
					<!-- Here is the list key studyClassId is studyClassId -->
					<div class="tableactions" style="padding-bottom: 0px;">
						<s:select list="selectboxMap"id="className"  label="Select Class"
							cssClass="required" required="true" name="studyClass.className"
							headerKey="" headerValue="- Select -" 
							onchange="javascript:getSubjects(this.value);"
							requiredposition="first">
						</s:select>
					</div>
				</s:if>
			</td>
		</tr>
		<tr>
			<td>
				<div id="teacherSubjectsList"></div>
			</td>
		</tr>
		<tr>
			<td>
			</td>
		</tr>
		<tr>
			<td>
				<s:submit  value="Download MarkSheet" cssClass="submit long" />
			</td>
		</tr>
	</table>
	</s:form>
<div class="clear"></div>
<script type="text/javascript">
$(document).ready(function() {
	$('.numeric').numeric();
});	
	function getSubjects(stydyClassId) {
		var pars = "classId=" + stydyClassId;
		$("#teacherSubjectsList").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var url = jQuery.url.getChatURL("/staff/ajaxDoGetSubjects.do");
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#teacherSubjectsList").html(html);
			}
		});
	}
	
	function checkFields(){
            var parameters ='';
            var fieldErrorString ='';
            var classNameErrors = $("#className").val().trim();
            var teachreSubjectsListErrors = $("#teachreSubjectsList").val();
            var teachreSubjectsListErrorsTrim=$.trim(teachreSubjectsListErrors);
            var examTypeError=$("#examType").val().trim();
            if(examTypeError == ''){
               fieldErrorString = fieldErrorString + "<font style=\"color:red;font-weight:bold;\">Type of Exam is required.<br /></font>";
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
               document.getElementById('addFieldErrors').innerHTML= fieldErrorString;
               document.getElementById('addFieldErrors').style.display = "block";
               return false;
               }
               else{
               document.getElementById('addFieldErrors').innerHTML= "";
               document.getElementById('addFieldErrors').style.display = "none";  
               return true;
           }  
       }
       
</script>