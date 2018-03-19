<%@ include file="/common/taglibs.jsp"%>
<div id="addChildDiv">
	<div class="portlet box blue">
		<div class="portlet-title">
			<div class="caption"><i class="fa fa-globe"></i>
				Examination Results
			</div>
			<s:if test="%{user.parent &&  studentsList.size > 1}">
				<div class="actions">
					<s:url id="urlAddChildren" action="ajaxDoAddChildren" namespace="/student"/>
					<sj:a href="%{urlAddChildren}" targets="addChildDiv" cssClass="btn default btn-xs">Add My children</sj:a>
				</div>
			</s:if>
		</div>
		<div class="portlet-body">
			<div id="site_statistics_content">
				<s:if test='%{studentsList != null && !studentsList.isEmpty()}'>
					<span id="tempIdSpan" class="<s:property value='student.id'/>"></span>
					<s:if test='%{studentsList.size > 1}'>
						<div class="form-group form-horizontal">
							<label class="control-label col-md-3">
								Student Name :
							</label>
							<div class="col-md-5">
								<s:select id="sectionId" list="studentsList" listKey="id"
									label="Student Name" listValue="studentNameAndUserName"
									cssClass="form-control" name="anyId" theme="simple"
									onchange="javascript:getStudentMarksDetails(this.value);" />
							</div>
						</div>
						<div class="spaceDiv">&nbsp;</div><div class="spaceDiv">&nbsp;</div>
					</s:if>
				</s:if>
				<div id="studentExamMarksDiv"> </div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function() {
 	var tempId=$('span#tempIdSpan').attr("class");
		if(isNonEmpty(tempId)){
			getStudentMarksDetails(tempId);
		}
	else{
		getStudentMarksDetails('');		
	}
 });
 
         function getStudentMarksDetails(studentId){
        	 alert("studentId:" + studentId);
         		var str="";
         		var url ;
	    	     if(isNonEmpty(studentId))
					 url = jQuery.url.getChatURL("/student/ajaxGetStudentLatestMarks.do?tempId="+studentId);
		          else
         	 		url = jQuery.url.getChatURL("/student/ajaxGetStudentLatestMarks.do");
         	 	$('#studentExamMarksDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
				
         	 	$.ajax( {
					url : url,
					cache : true,
					success : function(response) {
						alert("response:" + response);
					 	if(isNonEmpty(response)){
					 		
					 		$('#studentExamMarksDiv').html(response);
						}
					}
				});
         }
 </script>

