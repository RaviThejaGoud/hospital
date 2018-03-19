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
			<s:form id="addNewPhotos" action="ajaxUploadAchievementPhotos" method="post"
					theme="simple" cssClass="form-horizontal" namespace="/sports"
					enctype="multipart/form-data">
					
					<s:hidden name="achievementVO.id"/>
					
					<div class="form-body">
					
						<div class="row">
							<div class="col-md-9">
								<div class="form-group ">
									<label class="control-label col-md-4"> <span
										class="required">*</span>Upload Multiple Photos :
									</label>
									<div class="col-md-6">
										<s:file name="achievementVO.fileUpload" id="uploadphotos" onchange="validateImage(this);"
											cssClass="required btn default" accept="image/*"
											multiple="multiple"></s:file>
									</div>
								</div>
							</div>
						</div>
						<div class="form-actions fluid">
							<div class="col-md-offset-3 col-md-6">
								<sj:submit value="Submit" cssClass="submitBt btn blue"
									formIds="addNewPhotos" targets="mainContentDiv" onBeforeTopics="uploadPhotosValidation" 
									indicator="indicator" validate="true" />
								<button type="button" data-dismiss="modal" class="btn default">
									Cancel
								</button>
							</div>
						</div>
					</div>
				</s:form>
		</div>
	</div>
</div>

<script type="text/javascript">
$.subscribe('uploadPhotosValidation', function(achievement, data) {
	if ($('#addNewPhotos').valid()) {
		$('button.close').click();
		return true;
	} else {
		achievement.originalAchievement.options.submit=false;
	}
});
</script>