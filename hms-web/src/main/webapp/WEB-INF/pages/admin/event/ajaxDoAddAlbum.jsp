<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />
<div>
	<h4 class="pageTitle bold"> Add Album </h4>
	<s:form id="addNewEventAlbum" action="ajaxAddAlbum" method="post"
		theme="simple" cssClass="form-horizontal" namespace="/admin" enctype="multipart/form-data">
		<s:hidden name="selectedId" id="photoIds"></s:hidden>
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
						<label class="control-label col-md-4"><span class="required">*</span>Event Name :</label>
						<div class="col-md-6">
							<s:select list="objectList" listKey="id" listValue="eventName"
								cssClass="required form-control" name="eventsAlbum.eventId" headerKey=""
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
							 <a data-toggle="modal" class="btn green" href="#uploadEventPhotosDiv" onclick="javascript:popupUploadPhotosAlb();">Add Photos </a>
					</div>
				</div>
			</div>
			</div>
			<div id="selectPhotosDiv">
				<jsp:include page="/WEB-INF/pages/admin/event/ajaxSelectPhotos.jsp"></jsp:include>
			</div>
		   <div class="form-actions fluid">
			<div class="col-md-offset-2 col-md-9">
				<sj:submit value="Submit" cssClass="submitBt btn blue" formIds="addNewEventAlbum" targets="eventsContentDiv" onBeforeTopics="prepareSelectedAlbPhotoIds" indicator="indicator" validate="true"/>
				<s:url id="addNewEventAlbumForm" action="ajaxViewEventPhotos"  namespace="/admin"> </s:url>
				<sj:a href="%{addNewEventAlbumForm}" cssClass="btn default" targets="eventsContentDiv">Cancel</sj:a>
			</div>
		</div>
	</div>
	</s:form>
 </div>
 <div id="uploadEventPhotosDiv"></div>
<script type="text/javascript">
function popupUploadPhotosAlb() {
		var url = jQuery.url.getChatURL("/admin/ajaxDoUploadPhotos.do");
		$('#uploadEventPhotosDiv') .html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
			url : url,
			cache : false,
			success : function(html) {
				$("#uploadEventPhotosDiv").html(html);
			}
		});
	}
$.subscribe('prepareSelectedAlbPhotoIds', function(event, data) {
	var selectedPhotoIds = [];
	$('li.selected').each( function() {
		photoIds = $(this).attr('id');
		if(photoIds >0){
			selectedPhotoIds.push(photoIds);
		}
	});
	$('#photoIds').val(selectedPhotoIds);
});

var tempString2 = '<s:property value="tempString2"/>';
if(isNonEmpty(tempString2))
{
	$("#eventULId").removeClass("active");
	$( "#albumULId" ).addClass( "active" );
	$( "#addAlbumLiId" ).addClass( "active" );
	
}
</script> 
