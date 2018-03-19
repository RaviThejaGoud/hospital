<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<div class="block grid_12" id="stepFees">
	<div class="block_head" id="topMenu">
		<h2>
			School Terms Settings
		</h2>
		<div id="topMenu">
			<ul>
				<li>
					<s:url id="urlCreateSchoolTermsLink"
						action="ajaxSchoolFeeSetting" escapeAmp="false"
						includeParams="all">
					</s:url>
					<b><sj:a id="createSchoolTermsLink" href="%{urlCreateSchoolTermsLink}"
						targets="stepFees" indicator="indicator">Create Fee Term</sj:a></b>
				</li>
				<s:if test='%{user.isSchoolFinance=="Y"}'>
				<li>
					<s:url id="urlManageSchoolBackLink"
						action="ajaxBackAdminGetSchoolFee" escapeAmp="false"
						includeParams="all">
					</s:url>
					<b><sj:a id="manageSchoolBackLink" href="%{urlManageSchoolBackLink}"
						targets="schoolTermsContent" indicator="indicator">Back</sj:a></b>
				</li>
				</s:if>
			</ul>
		</div>
	</div>
	<div class="block_content" id="stepFees">
		<jsp:include page="/WEB-INF/pages/admin/academic/ajaxViewSchoolTerms.jsp" />
	</div>
</div>
