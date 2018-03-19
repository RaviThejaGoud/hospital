<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />
<s:if test="%{(classList != null && !classList.isEmpty())}">
	<s:if test='%{tempString != null && tempString == "LC"}'>
		<s:form action="ajaxAddLcSettings" theme="simple" id="uploadLcInfo"
			method="post" enctype="multipart/form-data"
			cssClass="form-horizontal" namespace="/admin">
			<s:hidden id="classNames" name="anyId" />
			<s:hidden name="tempString" value="%{tempString}" />
			<div class="panel-body col-md-12">
				<div class="col-md-1">
					<span class="label label-danger"> NOTE : </span>
				</div>
				<div class="col-md-10">
					<ul>
						<li>
							If you are not find any certificate or need to prepare the
							certificate please contact eazyschool supporting team at
							(support@eazyschool.com).
						</li>
						<li>
							Eazyschool supporting will prepare the certificate with respect
							to your certificate design and upload into the respective place
							after getting the approval from the school management.
						</li>
					</ul>
				</div>
			</div>
			<jsp:include
				page="/WEB-INF/pages/admin/common/ajaxClassAndSectionsList.jsp"></jsp:include>
			<br\>
			<div class="form-body">
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-6">
								<span class="required">*</span>Upload Template (.docx) :
							</label>
							<div class="col-md-6">
								<input type='file' name="uploadList[0]" id="photoURL"
									class="btn default required fileNames" />
							</div>
						</div>
					</div>
				</div>
				<div class="form-actions fluid">
					<div class="col-md-offset-3 col-md-4">
						<sj:submit targets="viewLcContent" value="Submit"
							formIds="uploadLcInfo" indicator="indicator" cssClass="btn blue"
							validate="true" onBeforeTopics="validateLCSettings" />
					</div>
				</div>
			</div>
			<br />
		</s:form>
		<div style="margin-left: 0px;">
			<table
				class="table table-bordered table-striped table-condensed flip-content"
				id="sample_2">
				<s:if test="%{objectList != null && !objectList.isEmpty()}">
					<h4 class="bold pageTitle">
						Uploaded 'LC' templates
					</h4>
					<thead class="flip-content">
						<tr>
							<th>
								Class Names
							</th>
							<th>
								LC Template
							</th>
							<th>
								Download
							</th>
							<th>
								Delete
							</th>
						</tr>
					</thead>
					<div id="tcSettings">
						<s:iterator value="objectList">
							<tr>
								<td>
									<s:property value="classNames" />
								</td>
								<td>
									<s:property value="fileName" />
								</td>
								<td width="10%">
									<s:form action="ajaxDownloadTCAndLCTemplate" method="post" theme="css_xhtml" cssClass="form-horizontal" namespace="/admin">
										<s:hidden name="tempId" value="%{id}"></s:hidden>		
										<s:hidden name="tempString" value="%{tempString}"></s:hidden>					
										<s:submit type="submit" value="Download" cssClass="btn btn-xs blue " cssStyle="cursor:pointer;float:left;" />											
									</s:form>
								</td>
								<td>
									<s:url id="removeLc" action="ajaxDeleteLcTemplate"
										includeParams="all" escapeAmp="false" namespace="/admin">
										<s:param name="tempId" value="id"></s:param>
										<s:param name="tempString" value="%{tempString}"></s:param>
									</s:url>
									<s:div cssClass="btn btn-xs red"
										onclick="javascript:confirmDialogWithTarget(this,'viewLcContent');"
										id='%{removeLc}' theme="simple" title="Delete this LC">
										<i class="fa fa-times"></i>Delete
								</s:div>
								</td>
							</tr>
						</s:iterator>
					</div>
				</s:if>
				<s:else>
					<div class="alert alert-info">
						Currently there are no LC settings. Please add LC settings.
					</div>
				</s:else>
			</table>
		</div>
	</s:if>
	<s:if test='%{tempString != null && tempString == "lcbookSettings"}'>
		<span id='settingsCreatedClassIds1'
			class='<s:property value="anyTitle"/>'></span>
		<s:form action="ajaxAddLcBookSettings" theme="simple"
			id="lcBookSettings" method="post" cssClass="form-horizontal"
			namespace="/admin">
			<s:hidden name="tempString" value="%{tempString}" />
			<s:hidden id="classNames" name="anyId" />
			<h4 class="bold pageTitle">
				Book Number Settings
			</h4>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-6">
							<span class="required">*</span>LC Book Number :
						</label>
						<div class="col-md-5">
							<sj:textfield name="tempId" id="tcBookNumber"
								cssClass="required form-control input-medium" value=""
								maxlength="11"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-3">
							<span class="required">*</span>LC Start No :
						</label>
						<div class="col-md-5">
							<sj:textfield name="tempId1" id="slStartNo"
								cssClass="required form-control input-medium" value="" onchange="verifyLcNo();"
								maxlength="11"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-6">
							<span class="required">*</span>LC End No :
						</label>
						<div class="col-md-5">
							<sj:textfield name="tempId2" id="slEndNo"
								cssClass="required form-control input-medium numeric" value="" onchange="verifyLcNo();"
								maxlength="7"></sj:textfield>
						</div>
					</div>
				</div>
				</div>
			<div class="form-body">
				<jsp:include
					page="/WEB-INF/pages/admin/common/ajaxClassAndSectionsList.jsp"></jsp:include>
				<div class="form-actions fluid">
					<div class="col-md-offset-3 col-md-4">
						<sj:submit targets="viewLcContent" value="Submit"
							formIds="lcBookSettings" indicator="indicator"
							cssClass="btn submit blue" validate="true"
							onBeforeTopics="validateLCBookSettings" />

					</div>
				</div>
			</div>
			<br />
			<br />
		</s:form>
		<table
			class="table table-bordered table-striped table-condensed flip-content"
			id="sample_3">
			<s:if test="%{tempList != null && !tempList.isEmpty()}">
				<h4 class="bold pageTitle">
					LC Book Settings
				</h4>
				<thead class="flip-content">
					<tr>
						<th>
							Book Number
						</th>
						<th>
							Start Number
						</th>
						<th>
							End Number
						</th>
						<th>
							Classes
						</th>
						<th>
							Delete
						</th>
					</tr>
				</thead>
				<s:iterator value="tempList">
					<tr>
						<td>
							<s:property value="tcBookNumber" />
						</td>
						<td>
							<s:property value="tcStartingNumber" />
						</td>
						<td>
							<s:property value="tcEndingNumber" />
						</td>
						<td>
							<s:property value="bookSettingsClasses" />
						</td>
						<td>
							<s:if test='%{#session.previousYear=="N"}'>
								<s:url id="removeLc" action="ajaxDeleteLCBookSettings"
									includeParams="all" escapeAmp="false" namespace="/admin">
									<s:param name="tempId" value="%{id}"></s:param>
									<s:param name="tempString" value="%{tempString}"></s:param>
								</s:url>
								<s:div cssClass="btn btn-xs red" id='%{removeLc}' theme="simple"
									title="Delete this book settings"
									onclick="javascript:confirmDialogWithTarget(this,'viewLcContent');">
									<i class="fa fa-times"></i>Delete</s:div>
							</s:if>
						</td>
					</tr>
				</s:iterator>
			</s:if>
			<s:else>
				<div class="alert alert-info">
					Currently there are no LC book settings. Please add LC book
					settings.
				</div>
			</s:else>
		</table>
	</s:if>
</s:if>
<s:else>
	<div class="alert alert-info">
		Classes are not available.
	</div>
</s:else>
<script type="text/javascript">
$(document).ready(function() {
	$.destroyTopic('validateLCSettings');
	$.destroyTopic('validateLCBookSettings');
	var LcVar ="<s:property value="tempList.size()"/>";
	if(LcVar > 0){
		TableAdvanced.init();
	}
	changePageTitle("LC Settings");
	$("#tcBookNumber").focus();
	$('.numeric').numeric();
	//$('#tcFontName').val('');
  	var settingClassIds1 = $("span#settingsCreatedClassIds1").attr('class');
    if(isNonEmpty(settingClassIds1)){ 
    	var classIds1 = settingClassIds1.split(',');
	    for(var i=0;i<classIds1.length;i++){
	         $('input:checkbox[name="chkBoxSelectedIds"][value='+classIds1[i]+']').attr('disabled', true);
	          $('input:checkbox[name="chkBoxSelectedIds"][value='+classIds1[i]+']').parent('span').parent('div').addClass('disabled');
		}
  	} 
    $.subscribe('validateLCSettings', function(event, data) {
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
	  var boolVal = generateLCSetingsWithClassIds();
	  if(boolVal==false)
		  event.originalEvent.options.submit=false;
	});
});
	
	$.subscribe('validateLCBookSettings', function(event, data) {
		 if ($('#lcBookSettings').valid()) {
			 event.originalEvent.options.submit= generateLCBookSetingsClassIds();
			} else {
				 event.originalEvent.options.submit=false;
			}
	});
function generateLCBookSetingsClassIds() {
	if ($("input[name=chkBoxSelectedIds]:checked").length > 0) {
		var classIds = $("input[name=chkBoxSelectedIds]:checked");
		var selectedClassNames = [];
		if (classIds.length > 0) {
			for ( var i = 0; i < classIds.length; i++) {
				if (isNonEmpty(classIds[i].value)) {
					selectedClassNames.push(classIds[i].value);
				}
			}
		}
		$("#classNames").val(selectedClassNames);
		return true;
	} else {
		alert("Please select at least one class.");
		return false;
	}
}
function generateLCSetingsWithClassIds() {
	if ($("input[name=chkBoxSelectedIds]:checked").length > 0) {
		var classIds = $("input[name=chkBoxSelectedIds]:checked");
		var selectedClassNames = [];
		if (classIds.length > 0) {
		$(classIds).each(function(){
			if (isNonEmpty($(this).val()) && isNonEmpty($(this).parents('label').text())) {
				selectedClassNames.push($(this).parents('label').text().trim())
			}
		})
		}
		$("#classNames").val(selectedClassNames);
		return true;
	} 
	 else if ($("input[name=chkBoxSelectedIds]:checked").length == 0) {
	    alert("Please select at least one class.");
	    return false;
	}
}

function verifyLcNo() {
	var lcStartNo = $('#slStartNo').val();
	var lcEndNo = $('#slEndNo').val();
	if (isNonEmpty(lcStartNo) && isNonEmpty(lcEndNo)) {
		if (lcEndNo < lcStartNo) {
			$('#slEndNo').val('');
			alert("LC end No should not be less than LC start No.");
		}
	}
}
</script>
