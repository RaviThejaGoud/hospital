<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{studyClassList != null && !studyClassList.isEmpty()}">
			<jsp:include page="/common/messages.jsp" />
				<div class="grid_8">
					<div class="grid_3">
						<label class="labelRight">
							Select Class:
						</label>
					</div>
					<div class="grid_5">
							<s:select list="studyClassList" id="className" name="" listKey="id"
								listValue="classAndSection" headerKey=""
								headerValue="- Select Class -" theme="simple"
								onchange="javascript:getAjaxDoGetStudent(this.value);"
								requiredposition="first">
							</s:select>
					</div>
				</div>
			<div id="myStudentList"></div>
			</s:if>
			
<script type="text/javascript">
function getAjaxDoGetStudent(studyClassId) 
{
        //alert('do you want to generate TC for all the students');
	var r=confirm('do you want to generate TC for all the students');
	if (r==true)
	 {
	 $("#religionStudentsList")
				.html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	  var pars = "classId=" + studyClassId;
			var url = jQuery.url.getChatURL("/reports/ajaxClassWideTCGeneration.do");
			$.ajax( {
				url : url,
				cache : false,
				data : pars,
				success : function(html) {
				$("#religionStudentsList").html(html);
					$("#religionStudentsList").show();
				}
			});
	  }
	
	}  
        
		
</script>