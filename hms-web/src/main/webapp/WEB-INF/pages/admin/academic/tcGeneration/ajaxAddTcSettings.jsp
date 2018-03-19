<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />
<s:if test="%{(classList != null && !classList.isEmpty())}">
	<s:if test='%{tempString != null && tempString == "templateSettings"}'>
		<s:form action="ajaxAddTcSettings" theme="simple" id="uploadTcInfo"
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
							If you are not find any certificate or need to prepare the certificate please contact eazyschool supporting team at (support@eazyschool.com).
						</li>
						<li>
							Eazyschool supporting will prepare the certificate with respect to your certificate design and upload into the respective place after getting the approval from the school management
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
						<sj:submit targets="viewTcContent" value="Submit"
							formIds="uploadTcInfo" indicator="indicator" cssClass="btn blue"
							validate="true" onBeforeTopics="validateTCSettings" />
					</div>
				</div>
			</div>
			<br />
		</s:form>
		<div style="margin-left: 0px;">
			<table
				class="table table-bordered table-striped table-condensed flip-content" id="sample_2">
				<s:if test="%{objectList != null && !objectList.isEmpty()}">
					<h4 class="bold pageTitle">
						Uploaded 'TC' templates
					</h4>

					<!--<h1>
								TC Settings
							</h1>
							-->
					<thead class="flip-content">
						<tr>
							<th>
								Class Names
							</th> 
							<th>
								TC Template
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
									<s:url id="removeTc" action="ajaxDeleteTcTemplate"
										includeParams="all" escapeAmp="false" namespace="/admin">
										<s:param name="tempId" value="id"></s:param>
										<s:param name="tempString" value="%{tempString}"></s:param>
									</s:url>
									<s:div cssClass="btn btn-xs red"
										onclick="javascript:confirmDialogWithTarget(this,'viewTcContent');"
										id='%{removeTc}' theme="simple" title="Delete this TC">
										<i class="fa fa-times"></i>Delete
								</s:div>
								</td>
							</tr>
						</s:iterator>
					</div>
				</s:if>
				<s:else>
					<div class="alert alert-info">
						Currently there are no TC settings. Please add TC settings.
					</div>
				</s:else>
			</table>
		</div>
	</s:if>
	<s:if test='%{tempString != null && tempString == "bookSettings"}'>
		<span id='settingsCreatedClassIds'
			class='<s:property value="anyTitle"/>'></span>
		<s:form action="ajaxAddTcBookSettings" theme="simple"
			id="tcBookSettings" method="post" cssClass="form-horizontal" namespace="/admin">
			<s:hidden name="tempString" value="%{tempString}" />
			<s:hidden id="classNames" name="anyId" />
			<h4 class="bold pageTitle">
				Book Number Settings
			</h4>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-6">
							<span class="required">*</span>TC Book Number :
						</label>
						<div class="col-md-5">
							<sj:textfield name="tempId" id="tcBookNumber"
								cssClass="required form-control input-medium numeric" value=""
								maxlength="7"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-3">
							<span class="required">*</span>TC Start No :
						</label>
						<div class="col-md-5">
							<sj:textfield name="tempId1" id="slStartNo" 
								cssClass="required form-control input-medium numeric" value="" onchange="verifyTcNo();"
								maxlength="7"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-6">
							<span class="required">*</span>TC End No :
						</label>
						<div class="col-md-5">
							<sj:textfield name="tempId2" id="slEndNo"
								cssClass="required form-control input-medium numeric" value="" onchange="verifyTcNo();"
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
						<sj:submit   targets="viewTcContent" value="Submit"
							formIds="tcBookSettings" indicator="indicator"
							cssClass="btn submit blue" validate="true"
							onBeforeTopics="validateTCBookSettings" />

					</div>
				</div>
			</div>
			<br />
			<br />
		</s:form>
		<table
			class="table table-bordered table-striped table-condensed flip-content" id="sample_3">
			<s:if test="%{tempList != null && !tempList.isEmpty()}">
				<h4 class="bold pageTitle">
					TC Book Settings
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
						<!--<th>
							    Edit
							</th>
							-->
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
									<s:url id="removeTc" action="ajaxDeleteTCBookSettings"
										includeParams="all" escapeAmp="false" namespace="/admin">
										<s:param name="tempId" value="%{id}"></s:param>
										<s:param name="tempString" value="%{tempString}"></s:param>
									</s:url>
									<s:div cssClass="btn btn-xs red" id='%{removeTc}'
										theme="simple" title="Delete this book settings"
										onclick="javascript:confirmDialogWithTarget(this,'viewTcContent');">
										<i class="fa fa-times"></i>Delete</s:div>
								</s:if>
							</td>
							<!--<td>
								<s:property value="tcBookNumber" />
								</td>
								-->
						</tr>
					</s:iterator>
			</s:if>
			<s:else>
				<div class="alert alert-info">
					Currently there are no TC book settings. Please add TC book
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
var TcVar ="<s:property value="tempList.size()"/>";
if(TcVar > 0){
	TableAdvanced.init();
}
	changePageTitle("TC Settings");
	$("#tcBookNumber").focus();
	$('.numeric').numeric();
	//$('#tcFontName').val('');
	$.destroyTopic('validateTCSettings');
	$.destroyTopic('validateTCBookSettings');
	var settingClassIds = $("span#settingsCreatedClassIds").attr('class');
    if(isNonEmpty(settingClassIds)){ 
    	var classIds = settingClassIds.split(',');
	    for(var i=0;i<classIds.length;i++){
	         $('input:checkbox[name="chkBoxSelectedIds"][value='+classIds[i]+']').attr('disabled', true);
	          $('input:checkbox[name="chkBoxSelectedIds"][value='+classIds[i]+']').parent('span').parent('div').addClass('disabled');
		}
  	} 
});
	$.subscribe('validateTCSettings', function(event, data) {
		 if(isNonEmpty($("input.fileNames").val())){ 
			var allowedExtensions = ['docx'];
			var res_field = $("input.fileNames").val();   
	        var extension = res_field.substr(res_field.lastIndexOf('.') + 1).toLowerCase();
			if (res_field.length > 0){
	       		 if (allowedExtensions.indexOf(extension) === -1){ 
	              	alert('Invalid file Format. Only ' + allowedExtensions.join(', ') + ' files are allowed.');
	              	event.originalEvent.options.submit=false;
	           	}
	  		}
		}
		var boolVal=generateTCSetingsWithClassIds();
		if(boolVal==false)
		  event.originalEvent.options.submit=false;
	});
	$.subscribe('validateTCBookSettings', function(event, data) {
		if ($('#tcBookSettings').valid()) {
			 event.originalEvent.options.submit= generateTCBookSetingsClassIds();
		} else {
			 event.originalEvent.options.submit=false;
		}
	});

function removeTCBookSettings(id) {
alert(dsfdshf);
	var url = jQuery.url.getChatURL("/admin/ajaxCheckTCBookNumberAssignedToStudents.do?anyId="+id);
	$('#indicator'+id).show();
	$.ajax( {
		url : url,
		cache : false,
		dataType : 'json',
			success : function(response) {
			var data= response.bookUsed;
			var answer = '';
			if(isNonEmpty(data)) {
		        answer = confirm(""+data);
		     }else{
		    	   var answer = confirm("Are you sure you want to delete ?");
				 if (answer) {
				 var pars = "tempId="+id+"&tempString=bookSettings";
	    	   		var url = jQuery.url.getChatURL("/admin/ajaxDeleteTCBookSettings.do");
		    	  		$.ajax( {
							url : url,
							cache : false,
							data : pars,
							success : function(responce) {
								$("#certificatesSecContent").html(responce);
							}
						});
				 	return true;
				 } else {
					$('#indicator'+id).hide();
				 	return false;
				 }
	     	}
			if(isNonEmpty(answer)) {
				if (!answer) {
		               $('#removeTCBookSetting'+id).attr("href", "javascript:void(0);");
		               $('#indicator'+id).hide();
		       } else {
		       		var pars = "tempId="+id+"&tempString=bookSettings";
	    	   		var url = jQuery.url.getChatURL("/admin/ajaxDeleteTCBookSettings.do");
		    	   $.ajax({
						url : url,
						cache : false,
						data : pars,
						success : function(responce) {
							$("#certificatesSecContent").html(responce);
							}
					});
		       }
			}else{
				$('#indicator'+id).hide();
			}
		}
	});
	}
 
function generateTCBookSetingsClassIds() {
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
function generateTCSetingsWithClassIds() {
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
	}else{return true;}
}
function verifyTcNo() {
	var tcStartNo = $('#slStartNo').val();
	var tcEndNo = $('#slEndNo').val();
	if (isNonEmpty(tcStartNo) && isNonEmpty(tcEndNo)) {
		if (tcEndNo < tcStartNo) {
			$('#slEndNo').val('');
			alert("TC end No should not be less than TC start No .");
		}
	}
}
</script>
