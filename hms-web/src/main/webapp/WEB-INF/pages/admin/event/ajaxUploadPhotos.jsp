<%@ include file="/common/taglibs.jsp"%>
<style>
	@import url("${pageContext.request.contextPath}/plugins/jquery-mixitup/image-picker.css");
</style>
<div class="form-body">
	<div data-width="760" class="modal fade modal-overflow in"
		style="display: block; width: 860px; margin-left: -379px; margin-top: 100px;"
		aria-hidden="false">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true"></button>
			<h4 class="modal-title">Upload Photos </h4>
		</div>
		<div class="modal-body">
			<s:if test="%{tempId2>0}"> 
				<s:form id="updateNewPhotos" action="ajaxUpdateSchoolPhotos" method="post"
					theme="simple" cssClass="form-horizontal" namespace="/admin" enctype="multipart/form-data">
					<s:hidden name="tempId2" value="%{tempId2}" />
					<s:hidden name="eventsAlbum.albumName" value="%{eventsAlbum.albumName}"></s:hidden>
					<s:hidden name="eventsAlbum.eventId" value="%{eventsAlbum.eventId}"></s:hidden>
					<div class="form-body">
					<div class="row">
						<div class="col-md-9">
							<div class="form-group ">
								<label class="control-label col-md-4"> <span
									class="required">*</span>Upload Multiple Photos :
								</label>
								<div class="col-md-6">
									<s:file name="fileUpload" id="uploadphoto" onchange="validateImage(this);"
										cssClass="required btn default" accept="image/*"
										multiple="multiple"></s:file>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-4"> Chose from Eazy School photos :
								</label>
								<div class="col-md-6">
								 <a data-toggle="modal" class="btn blue" href="#chooseFromPhotosDiv" onclick="javascript:popupUploadChoseFromPhotos(<s:property value="tempId2"/>,'<s:property value="eventsAlbum.albumName"/>',<s:property value="eventsAlbum.eventId"/>);">Your Eazy School photos </a>
								</div>
							</div>
						</div>
					</div>
					<div class="form-actions fluid">
						<div class="col-md-offset-3 col-md-6">
							<sj:submit value="Submit" cssClass="submitBt btn blue"
								formIds="updateNewPhotos" targets="selectPhotosDiv" onBeforeTopics="updatePhotosValidation" 
								indicator="indicator" validate="true" />
							<button type="button" data-dismiss="modal" class="btn default">
								Cancel
							</button>
						</div>
					</div>
					</div>
				</s:form>
			</s:if>
			<s:else>
				<s:form id="addNewPhotosAlb" action="ajaxAddPhotos" method="post"
					theme="simple" cssClass="form-horizontal" namespace="/admin"
					enctype="multipart/form-data">
					<div class="form-body">
					<div class="row">
						<div class="col-md-9">
							<div class="form-group ">
								<label class="control-label col-md-4"> <span
									class="required">*</span>Upload Multiple Photos :
								</label>
								<div class="col-md-6">
									<s:file name="fileUpload" id="uploadphotos" onchange="validateImage(this);"
										cssClass="required btn default" accept="image/*"
										multiple="multiple"></s:file>
								</div>
							</div>
						</div>
					</div>
					<div class="form-actions fluid">
						<div class="col-md-offset-3 col-md-6">
							<sj:submit value="Submit" cssClass="submitBt btn blue"
								formIds="addNewPhotosAlb" targets="selectPhotosDiv" onBeforeTopics="uploadAlbPhotosValidation" 
								indicator="indicator" validate="true" />
							<button type="button" data-dismiss="modal" class="btn default">
								Cancel
							</button>
						</div>
					</div>
					</div>
				</s:form>
			</s:else>
		</div>
	</div>
</div>
<div id="chooseFromPhotosDiv"></div>
<script type="text/javascript">
$.subscribe('uploadAlbPhotosValidation', function(event, data) {
	if ($('#addNewPhotosAlb').valid()) {
		$('button.close').click();
		return true;
	} else {
		event.originalEvent.options.submit=false;
	}
});
$.subscribe('updatePhotosValidation', function(event, data) {
	if ($('#updateNewPhotos').valid()) {
		$('button.close').click();
		return true;
	} else {
		event.originalEvent.options.submit=false;
	}
});
function popupUploadChoseFromPhotos(evntAlbumId,albumName,eventId) {
	var url = jQuery.url.getChatURL("/admin/ajaxChooseFromSmsPhotos.do");
	var pars = "tempId2="+evntAlbumId+"&eventsAlbum.albumName="+albumName+"&eventsAlbum.eventId="+eventId;
	$('#chooseFromPhotosDiv') .html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
		url : url,
		cache : false,
		data : pars,
		success : function(html) {
			$("#chooseFromPhotosDiv").html(html);
		}
	});
}
</script>
