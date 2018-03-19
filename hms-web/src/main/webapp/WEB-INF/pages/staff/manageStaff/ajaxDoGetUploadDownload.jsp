<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<s:if test="%{tempList != null && !tempList.isEmpty()}">
<div class="spaceDiv"></div>
	<div id="delelteAllDiv">
		<table
			class="table table-striped table-bordered table-hover table-full-width dataTable">
			<tr>
				<th>
					File Name
				</th>
				<th>
					Delete
				</th>
			</tr>
			<tbody data-target="#modal-gallery" data-toggle="modal-gallery"
				class="files">
				<tr class="template-upload fade in">
					<s:iterator value="tempList" status="stat">
						<tr class="fileNameRows"
							id='rowTodisplay<s:property value="#stat.count" />'>
							<td class="name">
								<span><a rel="nofollow"
									href='<c:url value='/staff/ajaxDownloadStaffDocs.do'/>?tempId=<s:property value="user.id" />&tempId1=<s:property value="staff.id" />&anyTitle=<s:property value="user.person.firstName" />&fileName=<s:property value="fileName" />'><s:property
									value="fileName" />
								</a>
								</span>
							</td>
							<td class="cancel">
								<s:url id="removeDirectoryFile" action="ajaxRemoveDocsDirectory"
									includeParams="all" escapeAmp="false" namespace="/staff">
									<s:param name="tempId" value="%{user.id}" />
									<s:param name="tempId1" value="%{staff.id}" />
									<s:param name="anyTitle" value="%{user.person.firstName}" />
									<s:param name="fileName" value="%{fileName}" />
									<s:param name="tempString">DELETEONE</s:param>
								</s:url>
								<s:div cssClass="btn btn-xs red emsFileRemove" 
									id='%{removeDirectoryFile}' theme="simple" title="Delete this File">
									<i class="fa fa-trash-o"></i>
								</s:div>
							</td>
						</tr>
					</s:iterator>
			</tbody>
		</table>
		<div class="row-fluid fileupload-buttonbar">
			<div class="span7">
				<a
					href="${pageContext.request.contextPath}/staff/ajaxDownloadStaffDocs.do?tempId=<s:property value="user.id" />&tempId1=<s:property value="staff.id" />&anyTitle=<s:property value="user.person.firstName" />"
					class="btn btn-xs purple" title="Download All"><i
					class="fa fa-edit"></i><span>Download All</span> </a>
				<s:url id="removeDirectoryFiles" action="ajaxRemoveDocsDirectory"
					includeParams="all" escapeAmp="false" namespace="/staff">
					<s:param name="tempId" value="%{user.id}" />
					<s:param name="tempId1" value="%{staff.id}" />
					<s:param name="anyTitle" value="%{user.person.firstName}" />
					<s:param name="tempString">DELETEALL</s:param>
				</s:url>
				<sj:a id="removeDirectoryFileId" href="%{removeDirectoryFiles}"
					targets="staffEditContentDiv" cssClass="btn btn-xs red">Delete All</sj:a>
			</div>
		</div>
	</div>
</s:if>
<s:form id="doUploadDocuments" action="ajaxUploadIndividualDocuments" cssClass="form-horizontal"
	method="post" theme="simple" enctype="multipart/form-data">
	<s:hidden name="tempId" value="%{user.id}"></s:hidden>
	<s:hidden name="tempId1" value="%{staff.id}"></s:hidden>
	<s:hidden name="anyTitle" value="%{user.person.firstName}"></s:hidden>
	<div class="form-body">
	<div class="form-group">
		<label class="control-label col-md-3">
			<span class="required">*</span>Upload Documents :
		</label>
		<div class="col-md-5">
			<s:file name="fileUpload" id="uploadDocs" multiple="multiple"
				tabindex="1" cssClass="btn default required"></s:file>
		</div>
	</div>
	<div class="form-actions">
	<div class="col-md-offset-3 col-md-6">
		<img src="../img/bg/bigWaiting.gif" alt="Loading..." title="Loading..." id="indicator"
			style="display: none; background-repeat: no-repeat; position: relative; z-index: 1000; bottom: 200px; margin-left: 240px;" />
		<sj:submit cssClass="submitBt btn blue" value="Submit"
			targets="staffEditContentDiv" validate="true"
			formIds="doUploadDocuments"
			tabindex="2" />
		<s:url id="doCancelForm" action="ajaxDoManageStaff" namespace="/staff" />
		<sj:a href="%{doCancelForm}" targets="mainContentDiv" cssClass="btn default" tabindex="3">
			Cancel </sj:a>
	</div>
	</div></div>
</s:form>
<script type="text/javascript">
	$(document).ready(function(){
			changePageTitle("Upload / Download staff documents");
	});
	 $(function() {
			if ($('div.emsFileRemove')) {
				$('div.emsFileRemove').unbind('click');
				$("div.emsFileRemove").click(function() {
					confirmDeleteDocument(this);
				});
			}
		});
		function confirmDeleteDocument(event) {
			thishref = $(event).attr('id');
			var filename = thishref.split("=");
			if ($(event).next('.question').length <= 0) {
				$(event)
						.after(
								'<div class="question">Are You Sure?<br/> <span class="yes">Yes</span><span class="cancel">Cancel</span></div>');
			}
			$(event).next('.question').animate({
				opacity : 1
			}, 300);
			$('.yes').unbind('click');
			$('.yes').bind('click', function() {
				var prdDiv = $(this).parents('.question');
				prdDiv.html('Processing...');
				$.ajax({
					url : thishref,
					cache : false,
					success : function(html) {
						// deleteFile(filename);
						prdDiv.parent().parent().remove();
						$('#staffEditContentDiv').html(html);
						//prdDiv.remove();
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
	 /*
	  $('div.deleteIndividual').click(function(){
		if($('tr.fileNameRows:has(td)').length==1){
			var str=$(this).attr("id");
		    str = str.replace("DELETEONE","DELETEALL");
			$(this).attr("id")
			$('input[name="anyId"]').val("DELETEALL");
		} 
	 }); */
</script>