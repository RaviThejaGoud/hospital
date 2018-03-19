<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />
<s:if test="%{objectList != null && !objectList.isEmpty()}">
	<div class="panel-body col-md-12">
		<p class="text-primary">
			<b>Total Active Staff :<s:property value="objectList.size" />
			</b>
		</p>
		<div class="row">
				<div class="col-md-2" style="float:right;">
					<sj:submit cssClass="submitBt btn blue" value="Submit"
						targets="staffsContent" indicator="indicator"
						onclick="doValidateUpload();" validate="true"
						onBeforeTopics="validateTimeTableForm" formIds="doUploadDownloads" />
				</div>
				<div class="col-md-5">
					<s:url id="doViewStaffBackLink" action="ajaxDoManageStaff"
						includeParams="all" escapeAmp="false" namespace="/staff">
					</s:url>
					<sj:a href="%{doViewStaffBackLink}" cssClass="btn default"
						targets="mainContentDiv" indicator="indicator">  Back</sj:a>
				</div>
			</div>
	</div>
	<s:form action="ajaxDoUploadDocuments" theme="simple" method="post"
		namespace="/staff" id="doUploadDownloads" cssClass="form-horizontal"
		enctype="multipart/form-data">
		<s:hidden name="anyTitle" id="jsonResponseData" value="{}"></s:hidden>
		<s:hidden name="tempString" value="%{tempString}" id="uploadIds"></s:hidden>
		<table class="table table-striped table-hover table-bordered"
			id="sample_2">
			<thead>
				<tr>
					<th>
						Staff Name
					</th>
					<th>
						Mobile Number
					</th>
					<th>
						Role
					</th>
					<th>
						Qualification
					</th>
					<th>
						Upload
					</th>
					<th>
						Download
					</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="objectList" status="stat">
					<tr class="staffDetailsList">
						<td>
							<s:property value="objectList[#stat.count-1][1]" />
							&nbsp;
							<s:property value="objectList[#stat.count-1][2]" />
						</td>
						<td>
							<s:property value="objectList[#stat.count-1][3]" />
						</td>
						<td>
							<s:property value="objectList[#stat.count-1][4]" />
							&nbsp;
						</td>
						<td>
							<s:property value="objectList[#stat.count-1][6]" />
							&nbsp;
						</td>
						<s:if test='%{tempString == "uploadTimetable"}'>
							<td class="uploadedNames"
								id='<s:property value="objectList[#stat.count-1][0]" />'
								data='<s:property value="objectList[#stat.count-1][7]" />'>
								<s:file name="fileUpload" cssClass="fileNames"
									id="uploadDocs%{objectList[#stat.count-1][0]}">
								</s:file>
							</td>
							<td>
								<s:if
									test="%{objectList[#stat.count-1][8] == 'Y' && !anyTitle.isEmpty()}">
									<a id="noPrint" class="btn btn-xs purple" title="Download"
										href="../<s:property value='anyTitle'/><s:property value='objectList[#stat.count-1][7]'/>.html"
										;
                                       target="_new">View Staff Timetable </a>
								</s:if>
								<s:else>No Documents.</s:else>
							</td>
						</s:if>
						<s:else>
							<td class="uploadedNames"
								id='<s:property value="objectList[#stat.count-1][0]" />'
								data='<s:property value="objectList[#stat.count-1][7]" />'>
								<s:file name="fileUpload"
									id="uploadDocs%{objectList[#stat.count-1][0]}"
									multiple="multiple">
								</s:file>
							</td>
							<td>
								<s:if test="%{objectList[#stat.count-1][8] == 'Y'}">
									<a
										href="${pageContext.request.contextPath}/staff/ajaxDownloadStaffDocs.do?tempId=<s:property value="objectList[#stat.count-1][7]" />&tempId1=<s:property value="objectList[#stat.count-1][0]" />&anyTitle=<s:property value="objectList[#stat.count-1][1]" />&tempString=<s:property value="tempString"/>"
										class="btn btn-xs purple" title="Download"><i
										class="fa fa-edit"></i>Download </a>
								</s:if>
								<s:else>No Documents.</s:else>
							</td>
						</s:else>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		<div class="panel-body col-md-12">
			<div class="col-md-10">
			<s:url id="doViewStaffBackLink" action="ajaxDoManageStaff"
							includeParams="all" escapeAmp="false" namespace="/staff">
						</s:url>
						<sj:a href="%{doViewStaffBackLink}" cssClass="btn default"
							targets="mainContentDiv" indicator="indicator">  Back</sj:a>
			</div>
			<div class="col-md-2">
				<sj:submit cssClass="submitBt btn blue" value="Submit"
					targets="staffsContent" indicator="indicator" 
					onclick="doValidateUpload();" validate="true" onBeforeTopics="validateTimeTableForm"
					formIds="doUploadDownloads" />
			</div>
		</div>
	</s:form>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no staff.
	</div>
</s:else>
<script type="text/javascript">
changePageTitle("Upload / Download staff documents");
$(document).ready(function() {
	var uploadTimeTable = $("input#uploadIds").val();
	$.destroyTopic('validateTimeTableForm');
	if("uploadTimetable"== uploadTimeTable){
		$.subscribe('validateTimeTableForm',function(event, data) {
			/* if (!isNonEmpty($("input.fileNames").val())) {
				alert($("input.fileNames").val());
				alert("Please browse your files to upload.");
				event.originalEvent.options.submit = false;
			}else { */
				/* if (isNonEmpty($("input.fileNames").val()) && ($("input.fileNames").val().lastIndexOf(".html") == -1)) {
					alert("File not acceped. Please upload your file in .html");
					event.originalEvent.options.submit = false;
				} */
			//}
		});
	}
});
function doValidateUpload(){
 var jsonObj = new Array();
	var html=new Array();
	var jsonRes='';
	var fileNames1=new Array();
	$('tr.staffDetailsList').each(function(){
		$(this).find('td.uploadedNames').each(function(){
			var inp =document.getElementById('uploadDocs'+$(this).attr('id'));
		   if(inp.files.length !=0){
			fileNames1=new Array();
			for (var i = 0; i < inp.files.length; i++) {
			     fileNames1.push(inp.files.item(i).name);
			 }
		 	 html.push({"STAFFID" :  $(this).attr('id'),
			       		"ACCOUNTID" :  $(this).attr('data'),
			  		    "BROWSEURL" : fileNames1
			  		});
		 	 jsonObj.push(html);
		 	 if(jsonObj!='' && jsonObj!=','){
		 	   jsonRes={"JSONOBJ": jsonObj}; 
		 	 }
		 	 html=[];
		 }	 
	 	 });
		});
		if(jsonRes==''){
		 jsonRes=({});
		}
	    $('input#jsonResponseData').val(JSON.stringify(jsonRes));
}
</script>