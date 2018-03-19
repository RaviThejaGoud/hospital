<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{tempList != null && !tempList.isEmpty()}">
	<div class="form-group">
		<label class="control-label col-md-2">
			<span class="required"> * </span>Select Class :
		</label>
		<div class="col-md-3">
		<s:select list="tempList" id="classId" name="tempId2"
			listKey="id" listValue="className" headerKey=""
			theme="simple"
			onchange="javascript:getSectionDetails(this.value);"
			cssClass="required form-control input-medium selectSubject">
		</s:select>
		</div>
		<div id="viewStudyClassesDiv" class="col-md-12"></div>
	</div>
</s:if>
<s:else>
	<div class="alert alert-info col-md-6">
		There are no classes.
	</div>
</s:else>
<script type="text/javascript">
	changePageTitle('Study Materials');
	$(document).ready(function() {
		var classId = $("#classId option:selected").val();
		$("select#className").attr('selected','selected');
		if(isNonEmpty(classId)){
			getSectionDetails(classId);
		}
	});
	function getSectionDetails(classId) {
		var quizId ='<s:property value="quizId"/>';
		var pars = "classSectionId=" + classId+"&quizId="+quizId;
		if(isNonEmpty(classId)){		
		$("#viewStudyClassesDiv").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var url = jQuery.url.getChatURL("/admin/ajaxAllClassesSections.do");
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#viewStudyClassesDiv").html(html);
			}
		});
	  }
	  else{
	   alert("!Oops select Subject");
	   return false;
	  }
	} 
	
</script>

