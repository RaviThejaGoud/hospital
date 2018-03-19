<%@ include file="/common/taglibs.jsp"%>
<div class="form-horizontal">
<div class="row form-group">
		<label class="col-md-2 control-label" style="width: 12.667%">
			Upload : </label>
		<div class="col-md-10">
			<div class="radio-list">
				<label class="radio-inline"> <input type="radio"
					checked="checked" value="schoolImg"
					onclick="changeUploadBtn(this.value);" name="uploadImages">
					School Logo
				</label> <label class="radio-inline"> <input type="radio"
					value="principalSign" onclick="changeUploadBtn(this.value);"
					name="uploadImages">Principal digital signature
				</label>
			</div>
		</div>
	</div>
<div id="custImgUpload"  style="display: block;">
<s:form action="ajaxAddCustomerImage" method="post" id="addImage" namespace="/admin"
	enctype="multipart/form-data" theme="simple">
	
	
									
	<div class="col-md-12">
	<div class="col-md-2"></div>
		<s:if test='%{customer.customerOrgImage != null}'>
			<img src='<c:url value="${customer.customerOrgImage.path}"/>' alt='' border="0" height="102px;" width="110px;"/> <br><br>
		</s:if>
	
		<div class="form-group">
			<div class="col-md-6">
				<s:file name="upload" id="photoURL1" size="21" accept="image/*"
					cssClass="required btn default">
				</s:file>
			</div>
			<div class="col-md-2">
				<sj:submit   value="Upload" src="../images/my_account_upload.png" targets="mainContentDiv"
					formIds="addImage" validate="true" cssClass="btn blue"
					onBeforeTopics="addCropImg" />
			</div>
		</div>
	</div>
	<div class="col-md-12">
	 <div class="col-md-2"></div>
		<p>
			<span class="label label-danger"> NOTE : </span>&nbsp;
			<strong>Maximum size of 100kb.</strong>
		</p>
	</div>
</s:form>
</div>
<div id="digitalSign"  style="display: none;">
	<s:form action="ajaxUploadPrincipalsignature" method="post" id="penicipalsignature" namespace="/admin"
		enctype="multipart/form-data" theme="simple">
		<div class="col-md-12">
		<div class="col-md-2"></div>
		
		<s:if test='%{customer.principalDigitalSignature != null}'>
			<img src='<c:url value="${customer.principalDigitalSignature.path}"/>' alt='' border="0" height="102px;" width="110px;"/> <br><br>
		</s:if>
		
			<div class="form-group">
				<div class="col-md-6">
					<s:file name="fileUpload" id="sign" size="21" accept="image/*"
						cssClass="required btn default">
					</s:file>
				</div>
				<div class="col-md-2">
					<sj:submit value="Upload" src="../images/my_account_upload.png" targets="mainContentDiv"
						formIds="penicipalsignature" validate="true" cssClass="btn blue"
						onBeforeTopics="penicipalsignatureImg" />
				</div>
			</div>
		</div>
		<div class="panel-body col-md-12">
				<div class="col-md-1">
					<span class="label label-danger"> NOTE : </span>
				</div>
				<div class="col-md-10">
					<ul>
						<li>
							 Maximum size of 100kb. 
						</li>
						<li>
							Principal digital signature used for student ID cards  
						</li>
					</ul>
				</div>
			</div>
	</s:form>
 </div>
</div>
<script type="text/javascript">
function changeUploadBtn(uploadType) {
	if (uploadType == 'schoolImg') {
		$('#custImgUpload').show();
		$('#digitalSign').hide();
	} else {
		$('#custImgUpload').hide();
		$('#digitalSign').show();
	}
}
$.subscribe('penicipalsignatureImg', function(event, data) {
	if ($('#penicipalsignature').valid()){
		return true;
	}else{
		event.originalEvent.options.submit=false;
	}
});
</script>
