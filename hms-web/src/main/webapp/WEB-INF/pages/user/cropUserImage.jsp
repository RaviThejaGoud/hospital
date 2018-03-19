<%@ include file="/common/taglibs.jsp"%> 
<link href="<c:url value='/plugins/jcrop/css/jquery.Jcrop.min.css'/>" rel="stylesheet" type="text/css" />
<link href="<c:url value='/styles/newCss/pages/image-crop.css'/>" rel="stylesheet" type="text/css">
<div class="col-md-9">
	<p>
	<span class="label label-danger">NOTE!</span>
		Drag the box to choose how you want your photo to be cropped.
		Drag the yellow circles to resize the cropping area.
	</p>
	<s:form action="ajaxCropUserImage" method="post" namespace="/user" onsubmit="javascript:return checkCoords();" theme="simple"  id="demo8_form">
		<input type="hidden" id="crop_x" name="x" />
		<input type="hidden" id="crop_y" name="y" />
		<input type="hidden" id="crop_w" name="w" />
		<input type="hidden" id="crop_h" name="h" />
		<sj:submit   value="Crop Image" targets="mainContentDiv" cssClass="btn btn-large green" title="crop image" />
	</s:form>	
	<div class="spaceDiv"></div>
	<img src='<c:url value="${user.profileImage.cropAttachmentFilePath}"/>' id="demo8" alt="Jcrop Example" class="col-md-12"/>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/jcrop/js/jquery.color.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/jcrop/js/jquery.Jcrop.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/newScripts/form-image-crop.js"></script>
<script language="Javascript">
$(document).ready(function() {
	FormImageCrop.init();
});
</script>
