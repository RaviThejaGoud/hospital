<%@ include file="/common/taglibs.jsp"%> 
<s:if test="%{studyClassList != null && !studyClassList.isEmpty()}">
		<s:form action="ajaxDoUploadDocuments" theme="simple" method="post" namespace="/staff"
				id="doUploadDownloads" cssClass="form-horizontal" enctype="multipart/form-data">
		<s:hidden name="anyTitle" id="jsonResponseData" value="{}"></s:hidden>	
			<s:hidden name="tempString"   value="%{tempString}"></s:hidden>
		<div class="panel-body col-md-12">
			<div class="col-md-10">
				&nbsp;
			</div>
			<div class="col-md-2">
				<sj:submit cssClass="submitBt btn blue" value="Submit"
					targets="staffsContent" indicator="indicator"
					onclick="doValidateUpload();" validate="false"
					formIds="doUploadDownloads" />
			</div>
		</div>
		<table class="table table-striped table-hover table-bordered" id="sample_2">
			<thead>
				<tr>
					<th>
						Class And Section
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
				<s:iterator value="studyClassList">
					<tr class="classDetailsList">
						<td>
							<s:property value="classAndSection" />
						</td>
						<s:if test='%{tempString == "uploadClassWiseTimetable"}'>
							<td class="uploadedNames" id='<s:property value="id" />'>
								<s:file name="fileUpload" id="uploadDocs%{id}">
								</s:file>
							</td>
							<td>
							<%-- <s:if test='%{isClassTimetableUploaded == "Y" && !anyId.isEmpty()}'>
									<a id="noPrint"  class="btn btn-xs purple" title="Download" href="../<s:property value='anyId'/><s:property value='id'/>.html";
                                       target="_new">View Class Timetable  </a>
						     </s:if> --%>
						     <s:if test='%{classTimetableUploadedFilePath != null}'>
									<a id="noPrint"  class="btn btn-xs purple" title="Download" href="<s:property value='classTimetableUploadedFilePath'/>"
                                       target="_new">View Class Timetable  </a>
						     </s:if>
						     <s:else>No Documents.</s:else> 
						</td>
						</s:if>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		<div class="panel-body col-md-12">
			<div class="col-md-10">
				&nbsp;
			</div>
			<div class="col-md-2">
				<sj:submit cssClass="submitBt btn blue" value="Submit"
					targets="staffsContent" indicator="indicator"
					onclick="doValidateUpload();" validate="false"
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
function doValidateUpload(){
 var jsonObj = new Array();
	var html=new Array();
	var jsonRes='';
	var fileNames1=new Array();
	$('tr.classDetailsList').each(function(){
		$(this).find('td.uploadedNames').each(function(){
			var inp =document.getElementById('uploadDocs'+$(this).attr('id'));
		   if(inp.files.length !=0){
			fileNames1=new Array();
			for (var i = 0; i < inp.files.length; i++) {
			     fileNames1.push(inp.files.item(i).name);
			 }
		 	 html.push({"CLASSID" :  $(this).attr('id'),
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