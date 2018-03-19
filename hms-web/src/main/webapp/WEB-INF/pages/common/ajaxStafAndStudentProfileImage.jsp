<%@ include file="/common/taglibs.jsp"%>
<s:form action="ajaxStaffAndStudentUploadImage" theme="css_xhtml" namespace="/common"
			id="editStaffDetails" method="post" enctype="multipart/form-data">
			<s:hidden name="user.id" />
			<div class="grid_4" align="center" style="width: 244px">
				<div class="grid_4">
					<s:if
						test="%{user.profileImage.adjustedAttachmentFilePath != null &&  user.profileImage.adjustedAttachmentFilePath != ''}">
						<img
							src='<c:url value="${user.profileImage.adjustedAttachmentFilePath}"/>'
							alt='<s:property  value="user.fullPersonName" />' height="150px"
							width="150px" border="0" />&nbsp;
							</s:if>
					<s:else>
						<img src='../images/nophoto.jpg' />
					</s:else>
				</div>
			</div>
			<div class="grid_4" align="center">
				<s:url id="signupStep1" action="../user/ajaxDoEditProfile"
							includeParams="all">
						</s:url> <sj:a href="%{signupStep1}" targets="staffEditProfile"
							indicator="indicator">
							<s:if test='%{user.isParent=="Y"}'>
								<s:property value="user.person.fatherName" />
							</s:if>
							<s:else>
								<s:property value="user.fullPersonName" />
							</s:else>
						</sj:a>
			</div>
			<div class="grid_4" align="center"  style="width: 232px">
				<div class="links1" style="width: 188px;">
					<a class="cancelButton grid_3">Change Photo</a>
				</div>
			</div>
			<div id="changePhoto" style="display: none;">
				<div class="grid_4">
					Upload Image:
					<s:file name="upload" id="photoURL1" tabindex="8" cssClass="required"></s:file>
				</div>
				<div class="grid_4">
					&nbsp;
				</div>
				<div class="grid_4">
					<sj:submit   targets="profileAttachment" cssClass="submit small" validate="true" onClickTopics="addParentProfileImg" 
						value="Upload" indicator="indicator2" />
				</div>
			</div>
		</s:form>
<script type="text/javascript">
$.subscribe('addParentProfileImg', function(event, data) {
	if ($('#editStaffDetails').valid()) {
	
		return true;
	} else {
		return false;
	}
});
$(".links1").click(function() {
	if($("#changePhoto").is(":hidden"))
		$("#changePhoto").show();
	else
		$("#changePhoto").hide();
});
</script>