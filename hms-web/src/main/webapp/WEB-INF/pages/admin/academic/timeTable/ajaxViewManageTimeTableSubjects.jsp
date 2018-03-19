<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Subjects Settings &amp; Priority Details
				</div>
			</div>
			<div class="portlet-body">
					<div class="tab-content">
						<s:if test="%{studyClassList != null && !studyClassList.isEmpty()}">
							<s:form id="addClassSubSettings" action="ajaxAddClassSubjectsSettings" method="post" theme="simple" cssClass="form-horizontal" namespace="/admin">
									<s:hidden name="tempString" cssClass="tempString"></s:hidden>
									<div class="row">
										<div class="col-md-6">
										<div class="form-group">
												<label class="control-label col-md-3">Select Class :</label>
												<div class="col-md-6">
													<s:select list="studyClassList" listKey="id" listValue="classAndSection"  
														cssClass="required form-control" id="classSectionId"
														name="classId" onchange="javascript:onClassChange(this.value);">
												</s:select>
												</div>
											</div>
										</div>
									</div>
									<jsp:include page="/common/messages.jsp" />
									<div id="timeTableSubDetails"> 
									</div>
							</s:form>	
						</s:if>
						<s:else>
						<div class="alert alert-info">
						No classes found for assigning subject details.
						</div>
						</s:else>
				</div>
			</div>
		</div>
	</div>
</div>
 <script type="text/javascript">
	 $(document).ready(function() {
	 			var classSectionId = $('#classSectionId').val();
 				onClassChange(classSectionId);
	 			$.subscribe('classSubjectSttingsFormValidation', function(event, data) {
	 				var subjectSettingId = '';
	 				var subjectId= '';
	 				var periodsCount= '';
	 				var priority= '';
	 				var combinedPeriods = '';
	 				var jsonObj = [];
					$('tr.subjectSettingsData').each(function() {
								subjectSettingId = $(this).find("td.subjectSettingId").attr('id');
								subjectId = $(this).find("td.subjectId").attr('id');
								periodsCount = $(this).find(".periodsCount").val();
								priority = $(this).find(".priority").val();
								combinedPeriods = $(this).find(".combinedPeriods").val();
								if(isNonEmpty(subjectId)){
										jsonObj.push( {
											"subjectSettingId" : subjectSettingId,
											"subjectId" : subjectId,
											"periodsCount" : periodsCount,
											"priority" : priority,
											"combinedPeriods" : combinedPeriods
										});											
								}
							});
					$('.tempString').val(JSON.stringify(jsonObj));
					if(JSON.stringify(jsonObj) == '[]'){
					   event.originalEvent.options.submit=false;
					} 
			});
 });
 
 
 function onClassChange(classSectionId) {
 	if(isNonEmpty(classSectionId)){
		$('#timeTableSubDetails').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "classId=" + classSectionId;	
		$.ajax( {
			url : jQuery.url.getChatURL("/admin/ajaxGetClassSubjectsDetails.do"),
			cache : true,
			data : pars,
			success : function(html) {
				$('#timeTableSubDetails').html(html);
			}
		});
	} 
}
</script>