<%@ include file="/common/taglibs.jsp"%>
<style>
	@import url("${pageContext.request.contextPath}/plugins/jquery-mixitup/image-picker.css");
</style>
<jsp:include page="/common/messages.jsp" />
<div class="row">
	<div class="col-md-6">
		<h4 class="pageTitle bold">Update Album</h4>
	</div>
	<div class="col-md-6">
		<s:url id="viewEventPhotos" action="ajaxViewEventPhotos"
			namespace="/admin">
		</s:url>
		<sj:a href="%{viewEventPhotos}" targets="eventsContentDiv"
			cssClass="btn default" style="float:right;margin-right:20px;">
			<i class="m-icon-swapleft"></i>&nbsp;Back To Album</sj:a>
	</div>
</div>
<s:form id="updateAlbum" action="ajaxUpdateAlbum" method="post"
		theme="simple" cssClass="form-horizontal" namespace="/admin" enctype="multipart/form-data">
		<s:hidden name="tempId2" value="%{tempId2}" />
		<s:hidden name="anyTitle" value="UpdateAlbumView" />
		<div class="form-body">
			<div class="row">
				<div class="col-md-6">
				<div class="form-group ">
						<label class="control-label col-md-4"><span class="required">*</span>Album Name :</label>
						<div class="col-md-6">
							<sj:textfield name="eventsAlbum.albumName" id="staffName"  
								cssClass="required form-control" maxlength="100"></sj:textfield>
					</div>
				</div>
			</div>
			</div>
			<div class="row">
				<div class="col-md-6">
				<div class="form-group ">
						<label class="control-label col-md-4">Event Name :</label>
						<div class="col-md-6">
							<s:select list="objectList" listKey="id" listValue="eventName"
								cssClass="form-control" name="eventsAlbum.eventId" headerKey=""
								headerValue="- Select -">
							</s:select>
					</div>
				</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
				<div class="form-group ">
						<label class="control-label col-md-4">Add New Photos :</label>
						<div class="col-md-6">
							 <a data-toggle="modal" class="btn green" href="#uploadPhotosEditDiv" onclick="javascript:popupUploadPhotos(<s:property value="tempId2"/>,'<s:property value="eventsAlbum.albumName"/>',<s:property value="eventsAlbum.eventId"/>);">Add Photos </a>
					</div>
				</div>
				</div>
			</div>
		<div id="selectPhotosDiv">
				<jsp:include page="/WEB-INF/pages/admin/event/ajaxSelectPhotos.jsp"></jsp:include>
		</div>
	   <div class="form-actions fluid">
			<div class="col-md-offset-2 col-md-9">
				<sj:submit value="Submit" cssClass="submitBt btn blue" formIds="updateAlbum" targets="eventsContentDiv" onBeforeTopics="editPrepareSelectedPhotoIds" indicator="indicator" validate="true"/>
				<s:url id="updateAlbumForm" action="ajaxViewEventPhotos"  namespace="/admin"> </s:url>
				<sj:a href="%{updateAlbumForm}" cssClass="btn default" targets="eventsContentDiv">Cancel</sj:a>
			</div>
		</div>
	</div>
	</s:form>
<div id="uploadPhotosEditDiv"></div>
<script type="text/javascript">
$(document).ready(function() {
	$('.image-picker').hide();
});

jQuery("select.image-picker").imagepicker({
    hide_select:  false,
  });
$('#inner-content-div').slimScroll({
    height: '650px'
}); 
/* $.subscribe('editPrepareSelectedPhotoIds', function(event, data) {
	var selectedPhotoIds = [];
	$('li.selected').each( function() {
		photoIds = $(this).attr('id');
		if(photoIds >0){
			selectedPhotoIds.push(photoIds);
		}
	});
	$('#photoIds').val(selectedPhotoIds);
}); */
function popupUploadPhotos(evntAlbumId,albumName,eventId) {
	var url = jQuery.url.getChatURL("/admin/ajaxDoUploadPhotos.do");
	var pars = "tempId2="+evntAlbumId+"&eventsAlbum.albumName="+albumName+"&eventsAlbum.eventId="+eventId;
	$('#uploadPhotosEditDiv') .html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
		url : url,
		data : pars,
		cache : false,
		success : function(html) {
			$("#uploadPhotosEditDiv").html(html);
		}
	});
}
</script> 
