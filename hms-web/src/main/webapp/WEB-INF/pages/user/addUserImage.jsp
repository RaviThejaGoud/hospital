<%@ include file="/common/taglibs.jsp"%>
<div id="changePhotoDiv">
<s:form action="ajaxAddUserImage" method="post" id="addProfileImge" enctype="multipart/form-data" theme="simple" cssClass="form-horizontal" namespace="/user">
	<s:hidden name="user.id" />
	<s:hidden name="userImageId" id="userImageId" />
	<div class="form-group">
		<div class="thumbnail" style="width: 310px;">
			<s:if test="%{user.profileImage.adjustedAttachmentFilePath != null &&  user.profileImage.adjustedAttachmentFilePath != ''}">
				<img
					src='<c:url value="${user.profileImage.adjustedAttachmentFilePath}"/>'
					alt='<s:property  value="user.fullPersonName" />' border="0" id="userImageDiv" class='imagePathNotFound'/>
			</s:if>
			<s:elseif test="%{student.profileImage.adjustedAttachmentFilePath != null &&  student.profileImage.adjustedAttachmentFilePath != ''}">
				<img
					src='<c:url value="${student.profileImage.adjustedAttachmentFilePath}"/>'
					alt='<s:property  value="user.fullPersonName" />' border="0" id="studentImageDiv"/>
			</s:elseif>
			<s:else>
				<img alt=""  src="../img/nophoto.jpg" style="width: 180px;100px;">
			</s:else>
		</div>
		<div class="margin-top-10 fileupload fileupload-new"
			data-provides="fileupload">
			<div class="input-group input-group-fixed">
				<span class="input-group-btn"> 
					<span class="uneditable-input">
							<i class="fa fa-file fileupload-exists"></i> 
							<span class="fileupload-preview"> </span> 
					</span> 
				</span>
				<span class="btn default btn-file">
				<span class="fileupload-new"> 
				 	<i class="fa fa-paper-clip"></i> Select file 
				</span>
				<span class="fileupload-exists"> 
					<i class="fa fa-undo"></i> Change 
				</span>
				 <s:file name="upload"
					id="photoURL1" size="21" cssClass="required default fileName" onchange="displayPreview(this.files);">
				</s:file>
			     </span>
				<a href="#" class="btn red fileupload-exists"
					data-dismiss="fileupload"><i class="fa fa-trash-o"></i> Remove</a>
			</div>
		</div>
		<span class="label label-danger"> NOTE! </span>
		<span> Maximum size of 100kb. </span>
	</div>
	<div class="margin-top-10">
		<sj:submit   value="Upload" 
			targets="mainContentDiv" formIds="addProfileImge" validate="true"
			cssClass="btn green" onBeforeTopics="addProfileImgFormValid" />
		<s:url id="EditProfileSettings" action="ajaxDoEditProfile"
			namespace="/user">
		</s:url>
		<sj:a href="%{EditProfileSettings}" targets="mainContentDiv"
			cssClass='btn default'>
		Cancel</sj:a>
	</div>
</s:form>
</div>

<script type="text/javascript">
$(document).ready(function() {
	$(".imagePathNotFound").error(function(){
	    $(this).attr('src', '../images/common/photo_notAvailable.jpg');
	});
});
$.subscribe('addProfileImgFormValid', function(event, data) {
	var filename = $('input.fileName').val().toLowerCase();
	if (isNonEmpty($("input.fileName").val())){
		if(filename.lastIndexOf(".jpg") == -1 && filename.lastIndexOf(".png") == -1  &&  filename.lastIndexOf(".jpeg") == -1){
			alert("File not acceped. Please upload your file in jpg or jpeg or  png");
			event.originalEvent.options.submit = false;
		}
		var imageSize = $('#userImageId').val();
		checkImageSize(imageSize);
	}
});
function displayPreview(files) {
    var imageSize = Math.round(files[0].size/1024);
    $('#userImageId').val(imageSize);
    checkImageSize(imageSize);
}
function checkImageSize(imageSize){
	if(imageSize > 1024){
		alert("maximum Image size is 100k.");
		$('#userImageId').val(imageSize);
		event.originalEvent.option.submit = false;
	} 
}
</script>
	