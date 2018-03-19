<%@ include file="/common/taglibs.jsp"%>
<div class="grid_13 commomnTabs">
<jsp:include page="/common/messages.jsp" />
	<s:form action="ajaxUploadMarksSheetForSendingMarksToParents" id="uploadMarkSheet" 
		method="post" theme="css_xhtml" enctype="multipart/form-data">
		<h1>
			Upload Marks Excel Sheet 
		</h1>
		<fieldset>
			<div class="grid_12">
				<div class="grid_5">
					<label>
						<span class="required">*</span>Upload an Excel Sheet:
					</label>
					<s:file name="upload" id="photoURL" required="true"
						requiredposition="first" cssClass="textfield required">
					</s:file>
				</div>
				<div class="grid_2">
					<div class="grid_2">
						&nbsp;
					</div>
					<sj:submit   targets="studentMarksContent" value="Submit"
						indicator="indicator" cssClass="submit small" validate="true"/>
				</div>
				<div class="grid_2">
					&nbsp;
				</div>
			</div>
		</fieldset>
	</s:form>
</div>
