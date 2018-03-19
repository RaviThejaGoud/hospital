<%@ include file="/common/taglibs.jsp"%>
<div class="wrapper container_16">
	<div class="wrapper">
		<div class="grid_16 block grid_16MarginLeft">
			<div id="academicsContent">
				<div class="grid_16">
					<div class="block_head">
						<h2>
							Academic SMS(S) :
							<s:property value="#session.academicYearName" />
						</h2>
					</div>
					<div class="block_content" id="academicsBlockContent">
						<div id="tabContent">
							<div id="tabWrapper">
								<div id="tabNavigation" style="display: none">
									<ul>
									<!--<s:if test='%{user.isSchoolStudent=="Y" || user.isParent=="Y" || (user.isSchoolTeacher=="Y" && !tempBoolean)}'>
										<li class="selected grid_5">
											<a href="#" id='/common/ajaxDoInboxGetScrapMessagesList.do' class='academicYearId=<s:property value="#session.academicYear" />'
												type='stepInbox'>My Inbox</a>
										</li>
										<li class="DashBoardLink grid_5">
											<a href='#' id='/common/ajaxDoGetSchoolWideAlertsList.do' class='academicYearId=<s:property value="#session.academicYear" />'
												type='stepDBMsg'>Dashboard Messages</a>
										</li>
									</s:if>
									<s:else>
									-->
									<s:if test='%{user.isSchoolTransport=="N" && user.isTransportFinance!="Y"}'>
									<s:if test='%{user.isSchoolTeacher=="Y" && tempBoolean}'>
									<li class="selected grid_5">
											<a href="#" id='/common/ajaxDoGetClassWideMessagesList.do' class='' type='stepSWMessage'>Class Wide SMS</a>
										</li>
									</s:if>
									<s:elseif test='%{user.isSchoolAdmin=="Y"}'>
										<li class="selected grid_5">
											<a href="#" id='/common/ajaxDoGetSchoolWideMessagesList.do' class='' type='stepSWMessage'>Admin SMS or E-Mail <s:if test='%{smsCnt}'>(Count=<s:property value="smsCnt" />)</s:if></a>
										</li>
									</s:elseif>
									</s:if>
									<s:if test='%{customer.transportModuleStatus &&(user.isSchoolTransport=="Y" || user.isSchoolAdmin=="Y")}'>
										<s:if test='%{x > 0}'>
											<s:if test='%{user.isSchoolAdmin == "N"}'>
												<li class="selected grid_4">
											</s:if>
											<s:else>
												<li class="grid_4">
											</s:else>
												<a href="#" id='/common/ajaxDoGetTransportMessagesList.do' class='status=TR' type='stepTRMessage'>Transport SMS (Count=<s:property value="x" />)</a>
											</li>
										</s:if>
										<s:else>
										<s:if test='%{user.isSchoolAdmin == "N"}'>
											<li class="selected grid_4">
										</s:if>
										<s:else>
											<li class="grid_4">
										</s:else>
											<a href="#" id='/common/ajaxDoGetTransportMessagesList.do' class='status=TR' type='stepTRMessage'>Transport SMS</a>
										</li>
										</s:else>
									</s:if>
										<!--<li class="grid_2">
											<a href="#" id='/common/ajaxDoInboxGetScrapMessagesList.do' class='academicYearId=<s:property value="#session.academicYear" />'
												type='stepInbox'>My Inbox</a>
										</li>
										<li class="grid_4">
											<a href='#' id='/common/ajaxDoGetSchoolWideAlertsList.do' class='academicYearId=<s:property value="#session.academicYear" />'
												type='stepDBMsg'>Dashboard Messages</a>
										</li>
									</s:else>-->
										<!--<li>
											<a href='#' id='/common/ajaxGetSenderMessages.do' class='academicYearId=<s:property value="#session.academicYear" />'
												type='stepSendMsg'>Sent Messages</a>
										</li>
									--></ul>
								</div>
								<div id="steps" class='manageAcademicBlockContent'><!--
								   <s:if test='%{user.isSchoolStudent=="Y" || user.isParent=="Y" || (user.isSchoolTeacher=="Y" && !tempBoolean)}'>
								   	<fieldset class="step" id='stepInbox'>
									</fieldset>
									<fieldset class="step" id='stepDBMsg'>
									</fieldset>
								   </s:if>
								   <s:else>
								   -->
								   <s:if test='%{user.isSchoolTransport=="N" && user.isTransportFinance!="Y"}'>
								   	<fieldset class="step" id='stepSWMessage'>
									</fieldset>
									</s:if>
									<s:if test='%{customer.transportModuleStatus &&(user.isSchoolTransport=="Y" || user.isSchoolAdmin=="Y")}'>
									<fieldset class="step" id='stepTRMessage'>
									</fieldset>
									</s:if>
									<!--<fieldset class="step" id='stepInbox'>
									</fieldset>
									<fieldset class="step" id='stepDBMsg'>
									</fieldset>
								   	</s:else>-->
									<!--<fieldset class="step" id='stepSendMsg'>
									</fieldset>
								--></div>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<script Language="Javascript1.2" type="text/javascript">
$(document).ready(
		function() {
			changePageTitle("Manage Academics");
			$('#commonSms').addClass('current');
			commonLoadTab();
			if ($('#tabNavigation ul li').hasClass("selected").toString()) {
				genericAjaxCall($('#tabNavigation ul li a').attr('id'), $(
						'#tabNavigation ul li a').attr('type'), $(
						'#tabNavigation ul li a').attr('class'));
			}
		});
</script>