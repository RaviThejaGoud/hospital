<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Upload/Download Documents
				</div>
			</div>
			<div class="portlet-body">
				<div class="tab-content form-body">
					<%@ include file="/common/messages.jsp"%>
					<div id="studentDocInfo"></div>
					<s:if test="%{tempList != null && !tempList.isEmpty()}">
						<table
							class="table table-striped table-bordered table-hover table-full-width"
							id="sample_2">
							<thead>
								<tr>
									<th>
										Student Admission No
									</th>
									<th>
										Document Name
									</th>
									<s:if
										test='%{user.isOnlySchoolAdmin=="Y" || user.isParent=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y"}'>
										<th>
											Delete
										</th>
									</s:if>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="tempList">
									<tr>
										<td>
											<s:if test='%{user.isOnlySchoolAdmin=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolClerk=="Y" || user.isSchoolDirector == "Y"}'>
												<s:property value="fileTypePath" />
											</s:if>
										</td>
										<td>
											<s:if test='%{user.isOnlySchoolAdmin=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolClerk=="Y" || user.isSchoolDirector == "Y"}'>
												<a rel="nofollow"
													href='<c:url value='/student/downloadStudentDocuments.do'/>?filePath=<s:property value="filePath" />&fileName=<s:property value="fileName" />'><s:property
														value="fileName" /> </a>
											</s:if>
											<s:else>
												<a rel="nofollow"
													href='<c:url value='/student/downloadStudentDocuments.do'/>?fileName=<s:property value="fileName" />'><s:property
														value="fileName" /> </a>
											</s:else>
										</td>
										<td>
											<s:if
												test='%{user.isOnlySchoolAdmin=="Y" || user.isParent=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y"}'>
												<s:url id="removeFile" action="ajaxRemoveDocumentsOfStudent"
													escapeAmp="false" includeParams="all" namespace="/student">
													<s:param name="tempString"
														value="fileTypePath"></s:param>
													<s:param name="anyTitle" value="filePath"></s:param>
													<s:param name="fileName" value="fileName"></s:param>
												</s:url>
												<s:div cssClass="btn btn-xs red emsFileRemoveStudent" targets="mainContentDiv"
													id='%{removeFile}' theme="simple" title="Delete this File">
													<i class="fa fa-times"></i>Delete</s:div>
											</s:if>
										</td>

									</tr>
								</s:iterator>
							</tbody>
						</table>
						<div class="spaceDiv"></div>
						<div class="col-md-3" style="float: right;">
							<s:if test='%{user.isOnlySchoolAdmin=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y"}'>
								<a rel="nofollow"
									href='<c:url value='/student/downloadStudentDocuments.do'/>'
									class="btn blue" style="padding: 0 2px;">Download All</a> |
								<s:url id="removeDocumentsOfStudent" namespace="/student">
								</s:url>
								<sj:a id="removeDocumentssLink"
									onclick="javascript:getAjaxDoRemoveAllDocuments();"
									href="%{removeDocumentsOfStudent}"
									cssClass="btn btn-xs red deletealldocuments">Delete All</sj:a>
							</s:if>
							<s:if test='%{user.isParent=="Y"}'>
								<a rel="nofollow"
									href='<c:url value='/student/downloadStudentDocuments.do'/>'
									class="btn blue" style="padding: 0 2px;">Download All</a> |
								<s:url id="removeDocumentsOfStudent" namespace="/student">
								</s:url>
								<sj:a id="removeDocumentssLink"
									onclick="javascript:getAjaxDoRemoveAllDocuments();"
									href="%{removeDocumentsOfStudent}"
									cssClass="btn btn-xs red deletealldocuments">Delete All</sj:a>
							</s:if>
						</div>
					</s:if>
					<s:else>
						<div class="alert alert-info">
							Currently there are no files. please upload files below.
						</div>
					</s:else>
					<div class="spaceDiv"></div>
					<div class="spaceDiv"></div>
					<div class="spaceDiv"></div>
					<s:if test='%{user.isOnlySchoolAdmin=="Y" || user.isParent=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y"}'>
						<s:form id="doaddDocumentsForStudent"
							action="ajaxAddDocumentsOfStudent" method="post" theme="simple"
							enctype="multipart/form-data" cssClass="form-horizontal"
							namespace="/student">
							<s:if test='%{user.isOnlySchoolAdmin=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y"}'>
								<div class="form-group">
									<label class="control-label col-md-3">
										<span class="required">*</span>Search Admission Number :
									</label>
									<div class="col-md-3">
										<select name="admissionNumber" id="select2_sample4" class="form-control select2 required">
										<option value=""> - Select -  </option>
											<s:iterator value="tempList1">
												<option value='<s:property />'>
													<s:property />
												</option>
											</s:iterator>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">
										<span class="required">*</span>Upload Document :
									</label>
									<div class="col-md-5">
										<s:file name="fileUpload" id="uploadAndDownScannedDocs"
											cssClass="btn default required" multiple="multiple"
											tabindex="8"></s:file>
									</div>
								</div>
								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-6">
										<sj:submit cssClass="btn blue" value="Submit"
											targets="mainContentDiv" validate="true"
											formIds="doaddDocumentsForStudent" />
									</div>
								</div>
							</s:if>
							<s:else>
								<div class="form-group">
									<label class="control-label col-md-2">
										Upload Document :
									</label>
									<div class="col-md-5">
										<s:file name="fileUpload" id="uploadAndDownScannedDocs"
											cssClass="btn default required" multiple="multiple"
											tabindex="8"></s:file>
									</div>
								</div>
								<div class="form-actions fluid">
									<div class="col-md-offset-2 col-md-9">
										<sj:submit cssClass="submitBt btn blue"
											value="Upload Document" targets="mainContentDiv"
											validate="true" formIds="doaddDocumentsForStudent" />
									</div>
								</div>
							</s:else>
						</s:form>
					</s:if>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	//TableAdvanced.init();
	$('#tempList').focus();
	changePageTitle("Upload/Download Documents");
	 FormComponents.init();
});
$(function() {
	if ($('div.emsFileRemoveStudent')) {
		$('div.emsFileRemoveStudent').unbind('click');
		$("div.emsFileRemoveStudent").click(function() {
			confirmDeleteStudentDocument(this);
		});
	}
});

function getAjaxDoRemoveAllDocuments() 
{
	var r=confirm('Are you Sure you want to delete all document(s)?');
	if (r==true)
	 {
	 $("#mainContentDiv")
				.html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			var url = jQuery.url.getChatURL("/student/ajaxRemoveDocumentsOfStudent.do");
			$.ajax( {
				url : url,
				cache : false,
				//data : pars,
				success : function(html) {
				$("#mainContentDiv").html(html);
					$("#mainContentDiv").show();
				}
			});
	  }
	
	} 
function confirmDeleteStudentDocument(event) {
	thishref = $(event).attr('id');
	var filename = thishref.split("=");
	if ($(event).next('.question').length <= 0) {
		$(event)
				.after(
						'<div class="question">Are You Sure?<br/> <span class="yes">Yes</span><span class="cancel">Cancel</span></div>');
	}
	$(event).next('.question').animate( {
		opacity : 1
	}, 300);
	$('.yes').unbind('click');
	$('.yes').bind('click', function() {
		var prdDiv = $(this).parents('.question');
		prdDiv.html('Processing...');
		$.ajax( {
			url : thishref,
			cache : false,
			success : function(html) {
				prdDiv.parent().parent().remove();
				$('button.close').click();
				$('#mainContentDiv').html('<div class="alert alert-success"><button class="close" data-dismiss="alert"></button><strong>Documents removed successfully.	</strong></div>');
				$("#mainContentDiv").html(html);
			}
		});
	});
	$('.cancel').unbind('click');
	$('.cancel').bind('click', function() {
		$(this).parents('.question').fadeOut(300, function() {
			$(this).remove();
		});
		return false;
	});
}
</script>