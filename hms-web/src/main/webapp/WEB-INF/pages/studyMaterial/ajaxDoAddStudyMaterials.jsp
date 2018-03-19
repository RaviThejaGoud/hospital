<%@ include file="/common/taglibs.jsp"%>
<s:form action="ajaxAddStudyMaterials" theme="simple" id="addMaterialsForm" enctype="multipart/form-data"
	cssClass="form-horizontal form-body" namespace="/admin" method="post">
	<s:hidden name="studyMaterial.id" value="%{studyMaterial.id}" id="studyMaterial"></s:hidden>
		<s:hidden name="classSectionId" value="%{classSectionId}" id="classSectionId"></s:hidden>
		<h4 class="bold pageTitle">
		<s:if test="%{studyMaterial.id != 0}">
			Update Study Material
		</s:if>
		<s:else>
				Create Study Material
		</s:else>
		</h4>
	<s:if test="%{studySubjectList != null && !studySubjectList.isEmpty()}">
		<div class="form-group">
			<label class="control-label col-md-2">
				Select Subject :
			</label>
			<div class="col-md-3">
				<s:select list="studySubjectList" listKey="id"
					listValue="name" theme="simple" id="subjectId"
					cssClass="required form-control input-medium" name="studyMaterial.subjectId"
					onchange="javascript:onStudySubjectChange(this.value);">
				</s:select>
			</div>
			<div id="classSectionsdiv"></div>
		</div>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			There are no subjects.
		</div>
	</s:else>
	<div class="row">
	<div class="form-group">
		<label class="control-label col-md-2">
			<span class="required">*</span>Material Name :
		</label>
		<div class="col-md-5">
			<sj:textfield id="materialName"
				name="studyMaterial.materialName"
				cssClass="required form-control input-medium" />
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-2">
			Description :
		</label>
		<div class="col-md-5">
			<s:textarea cols="55" rows="3" id="description"
				name="studyMaterial.Description"
				cssClass="form-control input-large" />
		</div>
	</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-2"> <span class="required">*</span> Upload Documents : </label>
		<div class="col-md-4">
		<s:file name="fileUpload" id="uploadAndDownScannedDocs" cssClass="multi required" ></s:file>
		</div>
	</div>
	
	<s:if test="%{viewStudyClassMaterials.attachmentsList != null && !viewStudyClassMaterials.attachmentsList.isEmpty()}">
		<div class="arrow"></div>
			<h3 class="popover-title">
				Download Materials
			</h3>
			<div class="popover-content">
					<s:iterator value="viewStudyClassMaterials.attachmentsList">
						<li>
						<span><a rel="nofollow"
							href='<c:url value='/admin/ajaxDownloadStudyMaterialDocs.do'/>?classSectionId=<s:property value="viewStudyClassMaterials.classSectionId" />&subjectId=<s:property value="viewStudyClassMaterials.subjectId" />&fileName=<s:property value="fileName" />&materialId=<s:property value="id" />'><s:property
									value="fileName" />
						</a>
						</span>
						</li>
						
					</s:iterator>
				
			</div>
			</s:if>
				<%-- <s:else>
					<li>
						There is no uploaded study materials.
					</li>
				</s:else> --%>
										
										
	<div class="form-actions fluid">
		<div class="col-md-6">
			<div class="col-md-offset-3 col-md-12">
				<sj:submit  cssClass="submitBt btn blue" value="Submit"
					targets="mainContentDiv" validate="true" formIds="addMaterialsForm"
					onBeforeTopics="materialFormValidation" />
					<s:url id="doViewRecords" action="ajaxViewStudyClassMaterialList"
						includeParams="all" escapeAmp="false" namespace="/admin">
					</s:url>
					<sj:a href="%{doViewRecords}" cssClass="btn default"
						targets="mainContentDiv">Cancel</sj:a>
			</div>
		</div>
	</div>
</s:form>
<script language="JavaScript" type="text/javascript">
	$(document).ready(function() {
		$("input[type=file].multi").MultiFile();
		$.destroyTopic('materialFormValidation');
		var studySubjectIds=$("#subjectId").val();
		if(isNonEmpty(studySubjectIds))
			onStudySubjectChange(studySubjectIds);
		$.subscribe('materialFormValidation', function(event, data) {
			var subjectIds = $('select#subjectId').val();	
			var classId = $('select#classId').val();
			if(((subjectIds =="") || (subjectIds ==null)) || ((classId =="") || (classId ==null))){
				if(((subjectIds =="") || (subjectIds ==null)) && ((classId =="") || (classId ==null))){
					alert('Please select atleast one subject and one Class.');
					event.originalEvent.options.submit=false;
				}
				else if ((classId =="") || (classId ==null)) {
					alert('Please select atleast one Class.');
					event.originalEvent.options.submit=false;	
				} 
				else if ((subjectIds =="") || (subjectIds ==null)) {
					alert('Please select atleast one subject.');
					event.originalEvent.options.submit=false;	
				} 
			}
		});
	});
	
   function onStudySubjectChange(studySubjectIds){
	   var studyMaterialId =0;
	   var classSectionId=0;
	   if(isNonEmpty($("#studyMaterial").val())){
		   studyMaterialId = $("#studyMaterial").val();
		 classSectionId = $("#classSectionId").val();
	   }
   		var url = jQuery.url.getChatURL("/admin/ajaxGetAllStudySubjects.do");
	  	 $("#classSectionsdiv") .html( '<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "tempId1=" + studySubjectIds+"&quizId="+studyMaterialId+"&tempId2="+classSectionId;
			  	$.ajax( {
				url : url,
				cache : false,
				data : pars,
				success : function(html) {
					$("#classSectionsdiv").html(html);
				}
		});
   }
</script>