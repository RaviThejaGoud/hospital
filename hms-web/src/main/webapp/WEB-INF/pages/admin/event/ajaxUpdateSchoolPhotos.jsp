<%@ include file="/common/taglibs.jsp"%>
<style>
@import
	url("${pageContext.request.contextPath}/plugins/jquery-mixitup/image-picker.css")
	;
</style>
<div data-width="760" class="modal fade modal-overflow in"
	style="display: block; width: 1000px; margin-left: -455px; margin-top: 100px;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="modal-title">Choose from eazy school photos :</h4>
	</div>
	<div class="modal-body">
		<s:form id="editNewAlbum" action="ajaxUpdateSchoolPhotos"
			method="post" theme="simple" cssClass="form-horizontal"
			namespace="/admin" enctype="multipart/form-data">
			<s:hidden name="selectedId" id="UpdatePhotoIds"></s:hidden>
			<s:hidden name="tempId2" value="%{tempId2}"></s:hidden>
			<s:hidden name="eventsAlbum.albumName" value="%{eventsAlbum.albumName}"></s:hidden>
			<s:hidden name="eventsAlbum.eventId" value="%{eventsAlbum.eventId}"></s:hidden>
			<div class="form-body">
				<s:if test="%{tempList != null && !tempList.isEmpty()}">
					<div class="row">
						<div class="col-md-6">
							<div class="form-group ">
								<label class="control-label col-md-6">Select Multiple Photos :</label>
							</div>
						</div>
					</div>
					<div class="picker" style="background: #ccd0b8; padding: 10px;"
						id="inner-content-div">
						<select multiple="multiple" class="image-picker show-html">
							<s:iterator value="tempList">
								<div class="mix-inner">
									<option data-img-src='<c:url value="${hrefAttachmentFilePath}"/>' value='<s:property value="%{id}"/>' class="photoId"></option>
								</div>
							</s:iterator>
						</select>
					</div>
				</s:if>
				<s:else>
					<div class="alert alert-info">Currently there are no photos.
						Please add photos and create album.</div>
				</s:else>
			</div>
			<div class="form-body modal-footer">
				<sj:submit value="Add" cssClass="submitBt btn blue"
					formIds="editNewAlbum" targets="selectPhotosDiv"
					onBeforeTopics="UpdatePrepareSelectedPhotoIds"
					indicator="indicator" validate="true" />
				<button type="button" data-dismiss="modal" class="btn default">Cancel</button>
			</div>
		</s:form>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$('.image-picker').hide();
	Portfolio.init();
});

jQuery("select.image-picker").imagepicker({
    hide_select:  false,
  });
$('#inner-content-div').slimScroll({
    height: '650px'
});
 $.subscribe('UpdatePrepareSelectedPhotoIds', function(event, data) {
	var UpdateSelectedPhotoIds = [];
	$('li.selected').each( function() {
		photoIds = $(this).attr('id');
		if(photoIds >0){
			UpdateSelectedPhotoIds.push(photoIds);
		}
	});
	$('#UpdatePhotoIds').val(UpdateSelectedPhotoIds);
	$('button.close').click();
});

</script>