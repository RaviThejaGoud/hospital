<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{objectList != null && !objectList.isEmpty()}">
<span id='<s:property value="tempString"/>' class='staffsSpan'></span>
	<div class="form-group">
		<label class="control-label col-md-2"><span class="required">*</span>Select Classes :</label>
		<div class="col-md-10">
			<s:checkboxlist list="objectList" name="chkBoxSelectedIds" listKey="%{classSectionIdAndClassId}" listValue="classAndSection" id="classes" 
			  theme="ems"  onchange="javascript:getTeachersByClassIdSubjectId(this);"></s:checkboxlist>
		</div>
	</div>
	<div id="staffsCont"> </div>
</s:if>
<s:else>
	<div class="alert alert-info">
		No classes found for this subject.
	</div>
</s:else>
<script type="text/javascript">
	$(document).ready(function() {
		getTeachersByClassIdSubjectId();
		 $("input:checkbox, input:radio").uniform();
	});
	function getTeachersByClassIdSubjectId(){
		var subjectId = $('#subjectId').val();
		var classSectionId = 0;
		//var classId = 0;
		//var assignedClassId = '';
		var selectedStaffId = $('span.staffsSpan').attr('id');
		if ($("input[name=chkBoxSelectedIds]:checked").length > 0 && isNonEmpty(subjectId)) {
				$('#staffsCont').html('<div align="center" style="padding:30px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
					var classIds = $("input[name=chkBoxSelectedIds]:checked");
					var selectedClassIds = '(';
					for ( var i = 0; i < classIds.length; i++) {
						classSectionId = classIds[i].value.split('_')[0];
							selectedClassIds += classSectionId + ', ';
					}
					selectedClassIds += '0)';
					//$('#selectedClassSectionIds').val(selectedClassIds);
					var url = jQuery.url.getChatURL("/admin/ajaxGetStaffDetailsByClassSectionIdAndSubjectId.do?classId="+selectedClassIds+"&subjectId="+subjectId);
					$.ajax( {
						url : url,
						cache : false,
						dataType : 'json',
						success : function(response) {
						var classTeachersList=response.objectList;
						var res = '';
						var staffId = '';
						var isStaffAssigned = false;
						if(classTeachersList){
								if(classTeachersList.length > 0){
									//var selectedTeacherIds = '(';
									res = "<div class='form-group'><label class='control-label col-md-2'>Select Staff :</label><div class='col-md-9' id='teachers'>";
									for ( var i = 0; i < classTeachersList.length; i++) {
										if(staffId != classTeachersList[i][0]){
											//selectedTeacherIds += classTeachersList[i][0] +',';
											if(isNonEmpty(selectedStaffId) && selectedStaffId != 0){
												var assignedStaffIds = selectedStaffId.split(',');
												if(isNonEmpty(assignedStaffIds)){
													isStaffAssigned = false;
													for(var j=0; j < assignedStaffIds.length; j++){
														if(assignedStaffIds[j] == classTeachersList[i][0]){
															res += "<label class='checkbox checkbox-inline' style='width:270px;margin-left:0px;'><input type='checkbox' name='selectBoxIdList' value='"+classTeachersList[i][0]+"' checked='checked'>"+classTeachersList[i][1]+", "+classTeachersList[i][2]+"</input></label>";
															isStaffAssigned = true;
														}
													}
													if(!isStaffAssigned){
														res += " <label class='checkbox checkbox-inline' style='width:270px;margin-left:0px;'><input type='checkbox' name='selectBoxIdList' value='"+classTeachersList[i][0]+"'> "+classTeachersList[i][1]+" "+classTeachersList[i][2]+"</input> </label>";
													}
												}else
													res += " <label class='checkbox checkbox-inline'  style='width:270px;margin-left:0px;'><input type='checkbox' name='selectBoxIdList' value='"+classTeachersList[i][0]+"'>"+classTeachersList[i][1]+" "+classTeachersList[i][2]+"</input> </label>";
											}else{
												res += " <label class='checkbox checkbox-inline'  style='width:270px;margin-left:0px;'><input type='checkbox' name='selectBoxIdList' value='"+classTeachersList[i][0]+"'>"+classTeachersList[i][1]+" "+classTeachersList[i][2]+"</input></label>";
											}
											staffId = classTeachersList[i][0];
										}
									}
									//selectedTeacherIds += '0)';
									//$('#selectedTeacherIds').val(selectedTeacherIds);
									res+="</div></div>";
									$('#staffsCont').html(res);
								}else{
									$('#staffsCont').html('<div class="alert alert-info">No staff for this class subject. Please define staff for this class subject.</div>');
									$('#selectedTeacherIds').val('');
								}
								$("input:checkbox, input:radio").uniform()
							}else{
								$('#staffsCont').html('<div class="alert alert-info">No staff for this class subject. Please define staff for this class subject.</div>');
								$('#selectedTeacherIds').val('');
							}
						}
					});
				
		}else{
			$('#staffsCont').html('<div class="alert alert-info">Please select class.</div>');
		}
			
	}
</script>