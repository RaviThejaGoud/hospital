<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />
<s:if test="%{(classList != null && !classList.isEmpty())}">
	<s:if test='%{tempString != null && tempString == "templateSettings"}'>
		<div class="form-body">
			<s:form action="ajaxAddHallTicketSettings" theme="simple"
				enctype="multipart/form-data" id="uploadHTInfo" method="post"
				cssClass="form-horizontal" namespace="/exam">
				<s:hidden id="classNames" name="anyId" />
				<s:hidden name="tempString" value="%{tempString}" />
				<div>
					<jsp:include page="/WEB-INF/pages/admin/common/ajaxClassAndSectionsList.jsp"></jsp:include>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3">
						<span class="required">*</span>Upload template (.docx) :
					</label>
					<div class="col-md-7">
						<input type='file' name="uploadList[0]"
							class="btn default required fileNames" />
					</div>
				</div>
				<div class="form-actions fluid">
					<div class="col-md-offset-3 col-md-4">
						<sj:submit targets="viewHTContent" value="Upload Template"
							formIds="uploadHTInfo" cssClass="submitBt btn blue"
							validate="true" onBeforeTopics="validateHTSettings" />
					</div>
				</div>
			</s:form>
		</div>
		<div class="spaceDiv"></div>
		<s:if test="%{objectList != null && !objectList.isEmpty()}">
			<h4 class="pageTitle bold">
				Hall Ticket Settings
			</h4>
			<table
				class="table table-striped table-bordered table-hover table-full-width"
				id="sample_2">
				<thead>
					<tr>
						<th>
							Class Names
						</th>
						<th>
							Hall Ticket Template
						</th>
						<th>
							Download
						</th>
						<th>
							Delete
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="objectList">
						<tr>
							<td>
								<s:property value="classNames" />
							</td>
							<td>
								<s:property value="fileName" />
							</td>
							<td width="10%">
								<s:form action="ajaxDownloadStudyAndHTTemplate" method="post" theme="css_xhtml" cssClass="form-horizontal" namespace="/admin">
									<s:hidden name="tempId" value="%{id}"></s:hidden>	
									<s:submit type="submit" value="Download" cssClass="btn btn-xs blue " cssStyle="cursor:pointer;float:left;" />											
								</s:form>
							</td>
							<td>
								<s:url id="removeHT" action="ajaxDeleteHTTemplate"
									includeParams="all" escapeAmp="false" namespace="/exam">
									<s:param name="tempId" value="id"></s:param>
									<s:param name="tempString" value="%{tempString}"></s:param>
								</s:url>
								<s:div cssClass="btn btn-xs red" id='%{removeHT}' theme="simple"
									title="Delete this TC"
									onclick="javascript:confirmDialogWithTarget(this,'viewHTContent');">
									<i class="fa fa-times"></i> Delete</s:div>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</s:if>
		<div class="spaceDiv"></div>
		<s:else>
			<div class="alert alert-info">
				Currently there are no HallTicket settings. Please add 'Hall Ticket'
				settings.
			</div>
		</s:else>
	</s:if>
</s:if>
<div class="spaceDiv"></div>
<s:else>
	<div class="alert alert-info">
		Classes are not available.
	</div>
</s:else>
<script type="text/javascript">
$(document).ready(function() {
	TableAdvanced.init();
	$.destroyTopic('validateHTSettings');
	changePageTitle("HallTicket Settings");
	$('.numeric').numeric();
	var settingClassIds = $("span#settingsCreatedClassIds").attr('class');
	if (isNonEmpty(settingClassIds)) {
		var classIds = settingClassIds.split(',');
		for ( var i = 0; i < classIds.length; i++) {
			$('input:checkbox[name="chkBoxSelectedIds"][value=' + classIds[i] + ']').attr('disabled', true);
		}
	}
	$.subscribe('validateHTSettings', function(event, data) {
		if(isNonEmpty($("input.fileNames").val())){ 
			var allowedExtensions = ['docx'];
			var res_field = $("input.fileNames").val();   
	        var extension = res_field.substr(res_field.lastIndexOf('.') + 1).toLowerCase();
			if (res_field.length > 0){
	   			if (allowedExtensions.indexOf(extension) === -1) {
	         	 	alert('Invalid file Format. Only ' + allowedExtensions.join(', ') + ' files are allowed.');
	         	 	event.originalEvent.options.submit=false;
	       	 	}
	  		}
		 }
	  var boolVal = generateHTSetingsWithClassIds();
	  if(boolVal==false)
		  event.originalEvent.options.submit=false;
	});
});

function generateHTSetingsWithClassIds() {
	if ($("input[name=chkBoxSelectedIds]:checked").length > 0) {
		var classIds = $("input[name=chkBoxSelectedIds]:checked");
		var selectedClassNames = [];
		if (classIds.length > 0) {
			$(classIds).each(function(){
				if (isNonEmpty($(this).val())
						&& isNonEmpty($(this).parents('label').text())) {
					selectedClassNames.push($(this).parents('label').text().trim())
				}
			})
		}
		$("#classNames").val(selectedClassNames);
	}
	 else if ($("input[name=chkBoxSelectedIds]:checked").length == 0) {
		    alert("Please select at least one class.");
		    return false;
		}
}
</script>
