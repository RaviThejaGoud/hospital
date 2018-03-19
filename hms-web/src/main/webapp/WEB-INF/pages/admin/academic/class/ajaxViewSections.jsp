<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{objectList != null && !objectList.isEmpty()}">
	<div class="form-body">
		<h4 class="bold pageTitle">
			Available sections
		</h4>
		<div class="form-group">
			<s:iterator value="objectList">
				<div class="col-md-1">
					<s:property value="section" />
				</div>
			</s:iterator>
		</div>
	</div>
</s:if>
<s:else>
	<div class="alert alert-info">
		You have not created any sections. This is critical to operate Classes
		and Performance Evaluation. This is very simple process and system
		would guide you through. Just type subject in above text box and hit
		submit.
	</div>
</s:else>
