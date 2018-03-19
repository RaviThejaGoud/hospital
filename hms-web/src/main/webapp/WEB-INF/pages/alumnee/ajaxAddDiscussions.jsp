<%@ include file="/common/taglibs.jsp"%>
<div class="form-body">
	<s:form id="addNewDiscussion" action="ajaxSaveSocialDiscussions" method="post" theme="simple" cssClass="form-horizontal" namespace="/alumnee" enctype="multipart/form-data">
			<div class="form-group">
				<label class="control-label col-md-2">Category :</label>
				<div class="col-md-3">
					 <s:select id="bGroup" headerKey=""   headerValue="- Select -" cssClass="form-control" name="socialDiscussionsVO.category"
								list="#{'G':'General','B':'Business'}" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-2"><span class="required">*</span>Subject :</label>
				<div class="col-md-3">
				<sj:textfield name="socialDiscussionsVO.subject" id="subject"
					 tabindex="2" cssClass="form-control required"
					 maxlength="40"></sj:textfield>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-2"> Upload Attachment : </label>
				<div class="fileupload fileupload-new" data-provides="fileupload">
					<div class="input-append">
						<span class="btn default"> 
							<input type="file" name="fileUpload" value="" tabindex="1" id="uploadDocs"  multiple="multiple">
						</span>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-2">Description:</label>
				<div class="col-md-8">
					<sj:textarea name="socialDiscussionsVO.description"  placeholder="This textarea has a limit of 1020 chars." cols="20" maxlength="1020"  id="maxlength_textarea4" 
						rows="5" cssClass="form-control required" ></sj:textarea>
				</div>
			</div>
		   <div class="form-actions fluid">
			<div class="col-md-offset-2 col-md-9">
				<sj:submit value="Submit" cssClass="submitBt btn blue" onBeforeTopics="addNewDiscussionForm"
					formIds="addNewDiscussion" targets="mainContentDiv" indicator="indicator" validate="true"/>
				<s:url id="viewDiscussions" action="ajaxDiscussionsHome" includeParams="all" escapeAmp="false" namespace="/alumnee">
				</s:url>
					<sj:a href="%{viewDiscussions}" cssClass="btn default" targets="mainContentDiv">Cancel</sj:a>
			</div>
		</div>
	</s:form>
 </div>
<script>
$(document).ready( function() {
	FormComponents.init();
	changePageTitle("View Friends");
}); 
</script>