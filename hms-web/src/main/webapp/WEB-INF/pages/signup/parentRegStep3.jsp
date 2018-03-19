<%@ include file="/common/taglibs.jsp"%>
<body>
		<s:form action="ajaxParentRegStep3" theme="simple" id="parentReg1"
			method="post" cssClass="form-login" enctype="multipart/form-data">
			<s:hidden name="tempId"></s:hidden>
			<div id="loginTraining">
				<div id="formbox-login" class="forgotBox">
					<jsp:include page="/common/messages.jsp"></jsp:include>
					<ul>
						<li>
							&nbsp;
						</li>
						<li>
							<span style="font-size: 16px;">If you want to login Please
							</span><a href="${pageContext.request.contextPath}/login.jsp">Click
								here</a>
						</li>
					</ul>
				</div>
			</div>
		</s:form>
</body>

