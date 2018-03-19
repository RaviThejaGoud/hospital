<%@ include file="/common/taglibs.jsp"%>
<div class="form-body">
<h4 class="pageTitle bold form-section">Upload / Download Documents</h4>
	<%@ include file="/common/messages.jsp"%>
	<s:if test="%{tempList != null && !tempList.isEmpty()}">
		<table
			class="table table-striped table-bordered table-hover table-full-width"
			id="sample_2">
			<thead>
				<tr>
					<th>Name</th>
					<s:if test='%{#session.previousYear == "N"}'>
						<th>Delete</th>
					</s:if>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="tempList">
					<tr>
						<td><a rel="nofollow"
							href='<c:url value='/student/downloadStudentDocuments.do'/>?tempId1=<s:property value="id" />&fileName=<s:property value="fileName" />'><s:property
									value="fileName" /> </a></td>
						<s:if test='%{#session.previousYear == "N"}'>
							<td><s:url id="removeFile" action="ajaxRemoveDocuments"
									escapeAmp="false" includeParams="all">
									<s:param name="tempId" value="student.id"></s:param>
									<s:param name="tempId1" value="id"></s:param>
									 <s:param name="tempString" value="student.account.admissionNumber"></s:param>
									<%--<s:param name="fileName" value="fileName"></s:param> --%>
								</s:url> <s:div cssClass="btn btn-xs red emsFileRemove"
									id='%{removeFile}' theme="simple" title="Delete this File">
									<i class="fa fa-trash-o"></i>
								</s:div></td>
						</s:if>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		<div class="linkRight" align="right">
			<a rel="nofollow"
				href='<c:url value='/student/downloadStudentDocuments.do'/>?tempId=<s:property value="student.id" />&tempId1=<s:property value="student.account.id" />'
				class="btn blue" style="padding: 0 2px;">Download All</a>&nbsp;&nbsp;
			<s:if test='%{#session.previousYear == "N"}'> 
				|&nbsp;&nbsp;
					<s:url id="removeDocuments" action="ajaxRemoveDocuments" escapeAmp="false" includeParams="all">
					<s:param name="tempId" value="%{student.id}"></s:param>
					<s:param name="tempId1" value="%{student.account.id}"></s:param>
				</s:url>
				<sj:a id="removeDocumentsLink" href="%{removeDocuments}"
					targets="studentEditContentDiv" cssClass="btn btn-xs red">Delete All</sj:a>
			</s:if>
		</div>
	</s:if>
	<s:else>
		<span class="label label-danger">NOTE :</span> Currently there are no files. please upload files below.
	</s:else>
	<div class="clearfix">&nbsp;</div>
	<s:form id="doaddDocuments" action="ajaxAddDocumentsToStudent"
		cssClass="form-horizontal" method="post" theme="simple"
		enctype="multipart/form-data" namespace="/student">
		<s:hidden name="tempId" value="%{tempId}"></s:hidden>
		<div class="row">
			<div class="col-md-9">
				<div class="form-group">
					<label class="control-label col-md-4"> Upload Documents : </label>
					<div class="col-md-4">
						<s:file name="fileUpload" id="uploadScannedDocs"
							cssClass="btn default required"></s:file>
					</div>
				</div>
			</div>
		</div>
		<s:if test='%{#session.previousYear == "N"}'>
			<div class="form-actions fluid">
				<div class="col-md-offset-3 col-md-9">
					<sj:submit cssClass="btn blue" value="Submit" indicator="indicator"
						targets="studentEditContentDiv" validate="true"
						formIds="doaddDocuments" />
				</div>
			</div>
		</s:if>
	</s:form>
</div>
<script type="text/javascript">
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
					$('#studentEditContentDiv').html(html);
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
</script>