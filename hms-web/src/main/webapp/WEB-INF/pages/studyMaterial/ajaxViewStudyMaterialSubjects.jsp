<%@ include file="/common/taglibs.jsp"%>
<div class="col-md-6">
	<s:if test="%{tempList2 != null && !tempList2.isEmpty()}">
		<div class="form-group">
			<label class="control-label col-md-3">
				Select Subject :
			</label>
			<div class="col-md-3">
				<s:select list="tempList2" listKey="subjectId"
					listValue="subjectName" theme="simple" id="subjectIds"
					cssClass="required form-control input-medium" name="subjectName"
					onchange="javascript:onStudySubjectChange(this.value);">
				</s:select>
			</div>
		</div>
</s:if>
<s:else>
	<div class="alert alert-info">
		Study material is not uploaded for this class subjects.
	</div>
</s:else>
</div>
<div class="col-md-12">
<div id="viewStudySubjectMaterial"></div>
</div>
<script type="text/javascript">
	changePageTitle('DayBook');
	$(document).ready(function() {
		TableAdvanced.init();
		UIExtendedModals.init();
		var subId = $("#subjectIds option:first").val();
		if(isNonEmpty(subId)){
			onStudySubjectChange(subId);
		}
		
	});
		function onStudySubjectChange(subId) {
			var classSectionId = '<s:property value="classId"/>';
			var pars = "subjectId=" + subId+ "&classSectionId=" + classSectionId;
			if(isNonEmpty(classSectionId) && isNonEmpty(subId)){	
			$("#viewStudySubjectMaterial").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			var url = jQuery.url.getChatURL("/admin/ajaxViewUploadStudyMaterialsInfo.do");
			$.ajax( {
				url : url,
				cache : false,
				data : pars,
				success : function(html) {
					$("#viewStudySubjectMaterial").html(html);
				}
			});
		  }
		 
		} 
</script>
 
 