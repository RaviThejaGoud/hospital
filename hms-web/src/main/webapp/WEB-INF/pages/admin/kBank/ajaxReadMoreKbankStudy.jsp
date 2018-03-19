<%@ include file="/common/taglibs.jsp"%>
<div data-width="760" class="modal fade modal-overflow in"
	style="display: block; width: 760px; margin-left: -379px; margin-top: 150px;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="modal-title">
			View Post Details
		</h4>
	</div>
	<div class="modal-body">
		<s:if test="%{knowledgeBank != null }">
			<h4>
				<s:property value="knowledgeBank.title" />
			</h4>
			<div>
				<a rel="nofollow"
					href='<c:url value='/admin/ajaxDownloadFiles.do'/>?id=<s:property value="id"/>'><s:property
						value="attachment.fileName" />
				</a>
			</div>
			<div style="margin-bottom: 15px">
				Posted on
				<s:property value="knowledgeBank.createdDateStr" />
				by
				<a class="tooltip1" href="#"> <s:property
						value="knowledgeBank.createdBy" /> </a>
			</div>
			<div>
			 <b>Description :</b> <s:property value="knowledgeBank.description" />
			 </div>
			<!--<div id="unFormattedMsg" style="display: none">
				<s:property value="knowledgeBank.description" />
			</div>
			<div id="formattedMsg"></div> -->
		</s:if>
	</div>
</div>


