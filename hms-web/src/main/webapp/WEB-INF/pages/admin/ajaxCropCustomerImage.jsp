<%@ include file="/common/taglibs.jsp"%> 
<link href="<c:url value='/plugins/jcrop/css/jquery.Jcrop.min.css'/>" rel="stylesheet" type="text/css" />
<link href="<c:url value='/styles/newCss/pages/image-crop.css'/>" rel="stylesheet" type="text/css">
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="icon-reorder"></i>
					<span class="hidden-480">Edit Customer Image</span>
				</div>
			</div>
			<div class="portlet-body">
				<div class="tab-content" id="corpCustImgageDiv">
					<p>
					<span class="label label-danger">NOTE :</span>
						Drag the box to choose how you want your photo to be cropped.
						Drag the yellow circles to resize the cropping area.
					</p>
					<s:form action="ajaxCropCustomerImage" method="post" cssClass="form-horizontal" namespace="/admin" onsubmit="javascript:return checkCoords();" theme="simple"  id="demo8_form">
						<input type="hidden" id="crop_x" name="x" />
						<input type="hidden" id="crop_y" name="y" />
						<input type="hidden" id="crop_w" name="w" />
						<input type="hidden" id="crop_h" name="h" />
						<sj:submit   value="Crop Image" cssClass="btn btn-large green" title="crop image" id="cropId" targets="mainContentDiv"/>
					</s:form>
					<div class="spaceDiv"></div>	
					<div>
						<img src='<c:url value="${customer.customerOrgImage.cropAttachmentFilePath}"/>' id="demo8" alt="Jcrop Example" style="height: 500px;width: 700px;"/>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/jcrop/js/jquery.color.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/jcrop/js/jquery.Jcrop.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/newScripts/form-image-crop.js"></script>
<script language="Javascript">
	FormImageCrop.init();
	$('input#cropId').click(function(){
	window.location.hash="target=ES.ajaxify AMCS";
	window.location.reload();
});
</script>
