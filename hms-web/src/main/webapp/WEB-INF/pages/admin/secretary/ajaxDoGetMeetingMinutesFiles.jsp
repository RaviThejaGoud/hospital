<%@ include file="/common/taglibs.jsp"%>

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
									href='<c:url value='/admin/ajaxDownloadMeetingMinutesFiles.do'/>?tempId1=<s:property value="tempId1" />&tempId=<s:property value="tempId" />'><s:property
											value="fileName" />
								</a>
								</span>
							</td>
							<td class="cancel">
								<s:url id="removeDirectoryFile" action="ajaxRemoveMeetingMinutesDirectory"
									includeParams="all" escapeAmp="false" namespace="/staff">
									<s:param name="tempId" value="%{tempId}" />
									<s:param name="tempId1" value="%{tempId1}" />
									<s:param name="tempString">DELETEONE</s:param>
								</s:url>
								<s:div cssClass="btn btn-xs red deleteIndividual"
									onclick="javascript:confirmDialogWithTarget(this,'rowTodisplay%{#stat.count}');"
									id='%{removeDirectoryFile}' theme="simple"
									title="Delete this file">
									<i class="fa fa-times"></i>
									<span>Delete</span>
								</s:div>
							</td>
						</tr>
					</s:iterator>
			</tbody>
		</table>
		<div class="row-fluid fileupload-buttonbar">
			<div class="span7">
				<a
					href="${pageContext.request.contextPath}/admin/ajaxDownloadMeetingMinutesFiles.do?tempId1=<s:property value="tempId1" />&tempId=<s:property value="tempId" />"
					class="btn btn-xs purple" title="Download All"><i
					class="fa fa-edit"></i><span>Download All</span> </a>
				<s:url id="removeDirectoryFile" action="ajaxRemoveMeetingMinutesDirectory"
					includeParams="all" escapeAmp="false" namespace="/staff">
					<s:param name="tempId" value="%{tempId}" />
									<s:param name="tempId1" value="%{tempId1}" />
					<s:param name="tempString">DELETEALL</s:param>
				</s:url>
				<s:div cssClass="btn btn-xs red"
					onclick="javascript:confirmDialogWithTarget(this,'getMeetingMinutesFileDivId');"
					id='%{removeDirectoryFile}' theme="simple" title="Delete this file">
					<i class="fa fa-times"></i>
					<span>Delete All</span>
				</s:div>
			</div>
		</div>
	</div>
</s:if>
<script type="text/javascript">
	$(document).ready(function(){
			changePageTitle("Upload / Download staff documents");
	});
	 $.subscribe("afterCompleteShowAll",function(event,data){
	    $('li#uploadDownloadEvent a').click();
	 });
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