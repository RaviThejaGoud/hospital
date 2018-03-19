<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<td colspan="2" id="doViewademicsContentDetails">

<div>
	<s:form action="ajaxDoUpdateStudySubject" theme="css_xhtml" id="updateStudySubject" method="post">
	     <s:hidden name="studySubjectId" value="%{studySubject.id}"></s:hidden>
					
			<div class="grid_10">
				<div class="grid_3">
					<sj:textfield name="studySubject.name" id="name" required="true" label="Subject Name"
					 labelposition="top" cssClass="textfield required" maxlength="20" cssStyle="width:140px;"></sj:textfield>
				</div>
			<!--<div class="grid_4">
				<sj:textfield name="studySubject.description" id="description" required="true" label="Description" 
				labelposition="top" cssClass="textfield required" maxlength="20" cssStyle="width:140px;"></sj:textfield>
			</div>
			--><div class="grid_5" style="padding-top: 10px">
			
				<div class="grid_2">
				<sj:submit   href="%{doViewSubjectList}" cssClass="submit small" value="Submit" indicator="indicator"
				targets="addNewClass" validate="true" />
				</div>
				<div class="grid_2">
				<s:url id="doViewSubjectList" includeParams="all">
				</s:url>
			    <sj:a href="%{doViewSubjectList}" cssClass="cancelButton" onCompleteTopics="doInitStudySubjectDtails"
				indicator="indicator"  targets="doViewademicsContentDetails%{studySubject.id}" button="false">Cancel</sj:a>
				</div>
			</div>
			</div>
			
		 
	</s:form>
   </div>      
 </td>
 <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"> </script>
<script type="text/javascript">
	     $(document).ready(function() {
			$.subscribe('doEditStudySubject', function(event, data) {
				if ($('#' + data.id).is(":hidden")) {
					$('#' + data.id).show();
				} else {
					$('#' + data.id).hide();
				}
				});
			});
</script>

