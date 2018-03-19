<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<div class="wrapper container_16">
	<div class="block grid_16">
		<div class="block_head" id="topMenu">
			<h2>
				Payment Defaulters
			</h2>
			<div id="topMenu">
				<ul>
					<li>
						<a
							href='${pageContext.request.contextPath}/common/printViewDefaulters.do?'>Print Defaulters</a>
					</li>
					<!--<li>
						<s:url id="urlManageFourteenFeeLink"
							action="ajaxAdminGetFourteenSchoolFee" escapeAmp="false"
							includeParams="all">
							<s:param name="dayParam" value="14"></s:param>
						</s:url>
						<b><sj:a id="manageFeeFourteenLink"
								href="%{urlManageFourteenFeeLink}" targets="pendingStudentsList"
								indicator="indicator">0-14 days(<s:property
									value="studentFee14List.size" />)</sj:a>
						</b>
					</li>
					<li>
						<s:url id="urlManageFifteenFeeLink"
							action="ajaxAdminGetFifteenSchoolFee" escapeAmp="false"
							includeParams="all">
							<s:param name="dayParam" value="15"></s:param>
						</s:url>
						<sj:a id="manageFifteenFeeLink" href="%{urlManageFifteenFeeLink}"
							targets="pendingStudentsList" indicator="indicator">15-30 days(<s:property
								value="studentFeeAbove15List.size" />)</sj:a>
					</li>
					<li>
						<s:url id="urlManageThirtyFeeLink"
							action="ajaxAdminGetThirtySchoolFee" escapeAmp="false"
							includeParams="all">
							<s:param name="dayParam" value="30"></s:param>
						</s:url>
						<sj:a id="manageThirtyFeeLink" href="%{urlManageThirtyFeeLink}"
							targets="pendingStudentsList" indicator="indicator">30-60 days(<s:property
								value="studentFeeAbove30List.size" />)</sj:a>
					</li>
					<li>
						<s:url id="urlManageSixtyFeeLink" action="ajaxAdminSixtySchoolFee"
							escapeAmp="false" includeParams="all">
							<s:param name="dayParam" value="60"></s:param>
						</s:url>
						<sj:a id="manageSixtyFeeLink" href="%{urlManageSixtyFeeLink}"
							targets="pendingStudentsList" indicator="indicator">>60 days(<s:property
								value="studentFeeAbove60List.size" />)</sj:a>
					</li>
					<li>
						<s:url id="urlManageUpComingFeeLink"
							action="ajaxAdminUpcomingSchoolFee" escapeAmp="false"
							includeParams="all">
							<s:param name="dayParam" value="10"></s:param>
						</s:url>
						<sj:a id="manageUpComingFeeLink"
							href="%{urlManageUpComingFeeLink}" targets="pendingStudentsList"
							indicator="indicator">UpComing(<s:property
								value="studentFeeUpcomeingList.size" />)</sj:a>
					</li>
				--></ul>
			</div>
		</div>
		<div class="block_content" id="pendingStudentsList">
			<jsp:include
				page="/WEB-INF/pages/hostel/fee/ajaxStudentFeeDetails.jsp" />
		</div>
	</div>
</div>
<script type="text/javascript">
$('#paymentDefaulters').addClass('current');
</script>