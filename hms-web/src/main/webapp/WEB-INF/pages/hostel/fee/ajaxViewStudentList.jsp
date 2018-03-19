<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<div id="commonTabContent" class="normalContent">
	<div id="commonTabWrapper">
		<div id="commonStep">
			<fieldset>
				<s:if test="%{studentsList != null && !studentsList.isEmpty()}">
				<div class="grid_15" align="right" data-target="studentResultsPage">
					<jsp:include page="/WEB-INF/pages/common/ajaxViewPaginationOptions.jsp"></jsp:include>				
				</div>
					<div class="grid_15 th" >
						<div class="grid_3">
							Roll Number
						</div>
						<div class="grid_3">
							Class
						</div>
						<div class="grid_5">
							Student Name
						</div>
						<div class="grid_4">
							Action
						</div>
					</div>
					<!--  Dont add class attribute for this div-->
					<div id="studentResultsPage" >
						<s:iterator value="studentsList">
						<s:set var="studentDetailsId" value="studentId"></s:set>
							<div class="grid_15 row" id="results">
								<div class="grid_3">
									<s:property value="rollNumber" />
								</div>
								<div class="grid_3">
									<s:property value="className" />
									&nbsp;
									<s:property value="section" />
								</div>
								<div class="grid_5">
									<s:property value="firstName" />
									&nbsp;
									<s:property value="lastName" />
								</div>
							<div class="grid_4">
									<s:if test="%{objectList != null && !objectList.isEmpty()}">
										<s:iterator value="objectList">
											<s:if test="%{#studentDetailsId == studentId}">
												<s:if test='%{paymentStatus == "N"}'>
													<s:url id="editPayment" namespace="/hostel"
															action="ajaxLoadStudentForInvoice" includeParams="all"
															escapeAmp="false">
															<s:param name="studentId" value="%{#studentDetailsId}" />
															<s:param name="paymentStatus" value="%{paymentStatus}" />
														</s:url>
														<sj:a href="%{editPayment}" targets="searchStudentsList"
															onClickTopics="hideShowDivs"
															onCompleteTopics="hilightTermValue" indicator="indicator">
																	Make Payment
													</sj:a>
												</s:if>
												<s:else>
													<s:url id="editPayment" namespace="/hostel"
															action="ajaxLoadStudentForInvoice" includeParams="all"
															escapeAmp="false">
															<s:param name="studentId" value="%{#studentDetailsId}" />
															<s:param name="paymentStatus">P</s:param>
														</s:url>
														<sj:a href="%{editPayment}" targets="searchStudentsList"
															onClickTopics="hideShowDivs"
															onCompleteTopics="hilightTermValue" indicator="indicator">
																View 
													</sj:a>
												</s:else>
											</s:if>
										</s:iterator>
									</s:if>
									<!--<s:else>
										<s:url id="editPayment" namespace="/hostel"
												action="ajaxLoadStudentForInvoice" includeParams="all"
												escapeAmp="false">
												<s:param name="id" value="%{#studentDetailsId}" />
												<s:param name="paymentStatus" value="%{paymentStatus}" />
											</s:url>
											<sj:a href="%{editPayment}" targets="searchStudentsList"
												onClickTopics="hideShowDivs"
												onCompleteTopics="hilightTermValue" indicator="indicator">
														Make Payment
										</sj:a>
									</s:else>
								--></div>
							</div>
						</s:iterator>
					</div>
				</s:if> 
				<s:elseif test="%{staffsList != null && !staffsList.isEmpty()}">
				<div class="grid_15" align="right" data-target="staffResultsPage">
					<jsp:include page="/WEB-INF/pages/common/ajaxViewPaginationOptions.jsp"></jsp:include>				
				</div>
					<div class="grid_15 th" >
						<div class="grid_3">
							Role Description
						</div>
						<div class="grid_5">
							Staff Name
						</div>
						<div class="grid_4">
							Action
						</div>
					</div>
					<!--  Dont add class attribute for this div-->
					<div id="staffResultsPage" >
						<s:iterator value="staffsList">
							<s:set var="staffDetailsId" value="staffId"></s:set>
							<div class="grid_15 row" id="results">
								<div class="grid_3">
									<s:property value="roleDescription" />----<s:property value="staffId"/>
								</div>
								<div class="grid_5">
									<s:property value="firstName" />
									&nbsp;
									<s:property value="lastName" />
								</div>
								<div class="grid_4">
									<s:if test="%{objectList != null && !objectList.isEmpty()}">
										<s:iterator value="objectList">
											<s:if test="%{#staffDetailsId == staffId}">
												<s:if test='%{paymentStatus == "N"}'>
													<s:url id="editStaffPayment" namespace="/hostel"
														action="ajaxLoadStaffForInvoice" includeParams="all"
														escapeAmp="false">
														<s:param name="staffId" value="%{#staffDetailsId}" />
														<s:param name="paymentStatus" value="%{paymentStatus}" />
													</s:url>
													<sj:a href="%{editStaffPayment}" targets="searchStaffList"
														onClickTopics="hideShowDivs"
														onCompleteTopics="hilightTermValue" indicator="indicator">Make Payment</sj:a>
												</s:if>
												<s:else>
													<s:url id="editStaffPayment" namespace="/hostel"
														action="ajaxLoadStaffForInvoice" includeParams="all"
														escapeAmp="false">
															<s:param name="staffId" value="%{#staffDetailsId}" />
															<s:param name="paymentStatus">P</s:param>
														</s:url>
														<sj:a href="%{editStaffPayment}" targets="searchStaffList"
														onClickTopics="hideShowDivs"
														onCompleteTopics="hilightTermValue" indicator="indicator">View Payment</sj:a>
												</s:else>
											</s:if>
										</s:iterator>
									</s:if>
									<!--<s:url id="editStaffPayment" namespace="/hostel"
										action="ajaxLoadStaffForInvoice" includeParams="all"
										escapeAmp="false">
										<s:param name="staffId" value="%{staffId}" />
									</s:url>
									<sj:a href="%{editStaffPayment}" targets="searchStaffList" onClickTopics="hideShowDivs" onCompleteTopics="hilightTermValue"
										indicator="indicator">
												Make Payment
									</sj:a>
								--></div>
							</div>
						</s:iterator>
					</div>
				</s:elseif>
				<s:else>
					<div class="grid_14">
						Oops! system couldn't find any match with keyword. Try by
						correcting the word
					</div>
				</s:else>
			</fieldset>
		</div>
	</div>
</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/subscription/paginator_dev.js">
</script>
<script type="text/javascript">
$.subscribe('hideShowDivs', function(event, data) {
		$('.links').show();
	    $(".hideSearchStudentBody").hide()
		return true;
});
$.subscribe('hilightTermValue', function(event, data) {
      var feeTab=$('#tabNavigation ul li a div.ribbon').attr('id');
      $('div#feeTab'+ feeTab.substring(6)).click();
		return true;
});
$.subscribe('doInitEditStudent', function(event, data) {
	if ($('#' + data.id).is(":hidden")) {
		$('#' + data.id).show();
	} else {
		$('#' + data.id).hide();
	}
});
$(document).ready(function() {
	$('#studentResultsPage').pagination();
	$('#staffResultsPage').pagination();
});
</script>
