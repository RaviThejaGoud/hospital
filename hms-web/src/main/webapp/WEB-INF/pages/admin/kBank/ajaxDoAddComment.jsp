<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/common/textcounter.js">
</script>
<div data-width="760" class="modal fade modal-overflow in"
	style="display: block; width: 760px; margin-left: -379px; margin-top: 100px;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="modal-title">
			Add / View Comments
		</h4>
	</div>
	<div class="modal-body">
		<s:if test="%{knowledgeBank != null}">
			<h5>
				<s:property value="knowledgeBank.title" />
			</h5>
			<div style="margin-bottom: 15px">
				Posted on
				<s:property value="knowledgeBank.createdDateStr" />
				by
				<a class="tooltip1" href="#"><span></span> <s:property
						value="knowledgeBank.createdBy" /> </a>
			</div>
			<s:property value="knowledgeBank.description" />
			<!--<div id="unFormattedMsg" style="display: none">
				<s:property value="knowledgeBank.description" />
			</div>
			<div id="formattedMsg"></div>
			-->
			<div>
				<s:form action="ajaxAddKBankComments" theme="simple"
					id="addPostKBankComments" method="post" cssClass="form-horizontal" namespace="/admin">
					<s:hidden name="knowledgeBank.id"></s:hidden>
					<div class="chat-form">
						<div class="input-cont">
							<input type="text" placeholder="Enter comments here..."
								class="form-control word_count required defaultValue"
								name="kBankComments.description">
							<span class="hintMessage"><div class="counter"></div> </span>
						</div>
						<div class="btn-cont" style="margin-top: -57px;">
							<span class="arrow"> </span>
							<sj:submit   cssClass="btn blue icn-only" value="Save"
								targets="viewComents" formIds="addPostKBankComments"
								validate="true" indicator="indicator">
							</sj:submit>
						</div>
					</div>
					<div class="spaceDiv"></div>
				</s:form>
			</div>
			<div id="viewComents" style="height: 200px;overflow-y: scroll;">
				<s:if test="%{tempList != null && !tempList.isEmpty()}">
					<s:iterator value="tempList">
						<div style="width: 55px; float: left">
							<img
								src='<c:url value="${commentAccount.personThumbnailImage}"/>'
								alt='<s:property  value="createdBy" />' border="0"
								id="userThumbpreview" width="50px" height="50px" />
						</div>
						<div>
							<s:property value="createdBy" />
							<br />
							<s:property value="createdDateStr" />
						</div>
						<div>
							<div id="shortDescEvents<s:property value="id"/>">
								<b>Description :</b>&nbsp;<s:property value="description" />
							</div>
							<p style="display: none;">
								<script language="JavaScript" type="text/javascript">
									shortDescEvents("#shortDescEvents<s:property value="id"/>","javascript:viewTotalDiscussion(<s:property value="id"/>);",300);
						         </script>
							</p>
						</div>
					</s:iterator>
				</s:if>
				<s:else>
					<div class="alert alert-info">
						No comments have been posted for this post.
					</div>
				</s:else>
			</div>
		</s:if>

		<s:else>
			<div class="alert alert-info">
				Currently there are no Minutes.
			</div>
		</s:else>
	</div>
</div>

<script type="text/javascript">
changePageTitle("Add  <s:property value="kBankTypeName"/>");
$("#description").focus();
</script>