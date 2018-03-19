<%@ include file="/common/taglibs.jsp"%>
<div class="block grid_12">
	<div class="block_head">
		<h2>
			Financial 
		</h2>
		<!--<div id="topMenu">
			<ul>
				<li>
					<s:url id="doAddCampus" action="ajaxSchoolFee" includeParams="all"
						escapeAmp="false">
					</s:url>
					<sj:a href="%{doAddCampus}" targets="schoolContent">
										Add Fee
								</sj:a>
				</li>
			</ul>
		</div>
	--></div>
	<div class="block_content" id="schoolContent">
		<jsp:include page="/WEB-INF/pages/admin/fee/ajaxStudentPaymentReports.jsp" />
	</div>
</div>
