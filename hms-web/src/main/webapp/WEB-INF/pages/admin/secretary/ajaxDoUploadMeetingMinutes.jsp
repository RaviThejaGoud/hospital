<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<s:form id="doUploadDocuments" action="ajaxUploadMeetingMinutes" cssClass="form-horizontal"
	method="post" theme="simple" enctype="multipart/form-data" namespace="/admin">
	<div class="form-body">
	   <div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="col-md-4 control-label">
						<span class="required">*</span>Meeting Details :
					</label>
					<div class="col-md-5">
						<s:select list="objectList" listKey="meetingDetailsId" listValue="meetingAgenda" cssClass="required form-control" headerKey="" headerValue="- Select -" name="meetingDetails.id"></s:select>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="col-md-4 control-label">
						<span class="required">*</span>Upload Audio Files :
					</label>
					<div class="col-md-5">
						<input type="file" name="fileUpload" id="uploadAudio" multiple="multiple" cssClass="btn default" onclick="myFunction()" />
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<span class="label label-danger"> NOTE :</span>&nbsp;
			Allowed only for audio,video and doc files.
		</div>
	<div class="form-actions">
		<div class="col-md-offset-3 col-md-6">
			<sj:submit cssClass="submitBt btn blue" value="Submit"
				targets="meetingDetailsDivId" validate="true"
				onCompleteTopics="afterCompleteShowAll" formIds="doUploadDocuments"
				tabindex="2" />
			<s:url id="doCancelForm" action="ajaxMeetingDetailsHome" namespace="/admin" />
			<sj:a href="%{doCancelForm}" targets="mainContentDiv" cssClass="btn default" tabindex="3">
				Cancel </sj:a>
		</div>
	</div>
 </div>
</s:form>
<hr/>
<!-- <div id="getMeetingMinutesFileDivId"></div> -->
<script type="text/javascript">
function myFunction() {
    document.getElementById("uploadAudio").accept = "audio/*,video/*,application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document";
    document.getElementById("demo").innerHTML = "The accepted file types have been set. Click 'Choose File' to see the effect.";
}
	$(document).ready(function(){
			changePageTitle("Add Meeting Minutes");
	});
	$.destroyTopic('afterCompleteShowAll');
	 $.subscribe("afterCompleteShowAll",function(event,data){
	    $('li#uploadDownloadEvent a').click();
	 });
	 /* function getMeetingMinutesFile(meetingDetailsId)
	 {
		 if(isNonEmpty(meetingDetailsId)){
				var url = jQuery.url.getChatURL("/admin/ajaxDoGetMeetingMinutesFiles.do?tempId="+meetingDetailsId);
				$('#getMeetingMinutesFileDivId').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
				$.ajax( {
					url : url,
					success : function(html) {
					    $('#getMeetingMinutesFileDivId').html(html);
						}
					});		
				}
	 } */
</script>