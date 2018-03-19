<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Academic InBox :
					<s:property value="#session.academicYearName" />
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<s:if
							test='%{user.isSchoolStudent=="Y" || user.isParent=="Y" || (user.isSchoolTeacher=="Y" && !tempBoolean)}'>
							<li>
								<s:url id="urlInboxesDetails"
									action="ajaxDoInboxGetScrapMessagesList" namespace="/common">
								</s:url>
								<sj:a href="%{urlInboxesDetails}" targets="mainContentDiv"
									class='academicYearId=<s:property value="#session.academicYear" />'
									cssClass='ajaxify' data-toggle="tab">
										My Inbox</sj:a>
							</li>
							<!--<li class="active">
								<a href="#" id='/common/ajaxDoInboxGetScrapMessagesList.do'
									class='academicYearId=<s:property value="#session.academicYear" />'
									type='stepInbox' data-toggle="tab">My Inbox</a>
							</li>
							-->
							<li class="active">
								<s:url id="viewAlert" action="ajaxDoGetSchoolWideAlertsList"
									namespace="/common">
								</s:url>
								<sj:a id="viewAlert" href="%{viewAlert}"
									targets="mainContentDiv" data-toggle="tab">Dashboard Messages</sj:a>
							</li>
							<!--<li>
									<s:url id="urlInboxesDetails"
										action="ajaxDoGetSchoolWideAlertsList" namespace="/common">
									</s:url>
									<s:param value="#session.academicYear" name="academicYearId" />
									<sj:a href="%{urlInboxesDetails}" targets="mainContentDiv"
										cssClass='ajaxify HDMS'>
									Dashboard Messages</sj:a>
								</li>
							-->
							<!--<li class="active">
								<s:url id="viewAlert" action="ajaxDoGetSchoolWideAlertsList"
									namespace="/common">
								</s:url>
								<sj:a href="%{viewAlert}" targets="mainContentDiv"
									class='academicYearId=<s:property value="#session.academicYear" />'
									cssClass='ajaxify' data-toggle="tab">
										Dashboard Messages</sj:a>
							</li>
							<li >
								<a href='#' id='/common/ajaxDoGetSchoolWideAlertsList.do'
									class='academicYearId=<s:property value="#session.academicYear" />'
									type='stepDBMsg' data-toggle="tab">Dashboard Messages</a>
							</li>
						-->
						</s:if>
						<s:else>
							<!--<s:if test='%{user.isSchoolTransport=="N" && user.isTransportFinance!="Y"}'>
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
										-->
							<s:if test='%{user.isSchoolAdmin == "Y"}'>
								<li class="selected grid_4">
							</s:if>
							<s:else>
								<li>
									<s:url id="urlInboxesDetails"
										action="ajaxDoInboxGetScrapMessagesList" namespace="/common">
									</s:url>
									<sj:a href="%{urlInboxesDetails}" targets="mainContentDiv"
										class='academicYearId=<s:property value="#session.academicYear" />'
										cssClass='ajaxify' data-toggle="tab">
										My Inbox</sj:a>
								</li>
								<!--<li>
										
											<a href="#" id='/common/ajaxDoInboxGetScrapMessagesList.do' class='academicYearId=<s:property value="#session.academicYear" />'
												type='stepInbox'>My Inbox eeeeee</a>
										</li>
										-->
							</s:else>
							<li class="active">
								<s:url id="viewAlert" action="ajaxDoGetSchoolWideAlertsList"
									namespace="/common">
								</s:url>
								<sj:a id="viewAlert" href="%{viewAlert}"
									targets="mainContentDiv" data-toggle="tab">Dashboard Messages</sj:a>
							</li>
							<!--<li  class="active">
										<s:url id="urlDashboardDetails"
											action="ajaxDoGetSchoolWideAlertsList" namespace="/common">
										</s:url>
										<sj:a href="%{urlDashboardDetails}" targets="mainContentDiv"
										class='academicYearId=<s:property value="#session.academicYear" />'
											cssClass='ajaxify' data-toggle="tab">
										Dashboard Messages</sj:a>
									</li>
										-->
							<!--<li>
										
											<a href='#' id='/common/ajaxDoGetSchoolWideAlertsList.do' class='academicYearId=<s:property value="#session.academicYear" />'
												type='stepDBMsg'>Dashboard Messages</a>
										</li>
									-->
						</s:else>
					</ul>
					<div id="subjectsContentDiv" class="tab-content">
						<%@ include file="/common/messages.jsp"%>
						<s:if
							test='%{user.isSchoolStudent=="Y" || user.isParent=="Y" || (user.isSchoolTeacher=="Y" && !tempBoolean)}'>
							<fieldset class="step" id='stepInbox'>
							</fieldset>
							<fieldset class="step" id='stepDBMsg'>
							</fieldset>
						</s:if>
						<s:else>
							<!--
								   <s:if test='%{user.isSchoolTransport=="N" && user.isTransportFinance!="Y"}'>
								   	<fieldset class="step" id='stepSWMessage'>
									</fieldset>
									</s:if>
									<s:if test='%{customer.transportModuleStatus &&(user.isSchoolTransport=="Y" || user.isSchoolAdmin=="Y")}'>
									<fieldset class="step" id='stepTRMessage'>
									</fieldset>
									</s:if>
									-->
							<fieldset class="step" id='stepInbox'>
							</fieldset>
							<fieldset class="step" id='stepDBMsg'>
							</fieldset>
						</s:else>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!--<div class="wrapper container_16">
	<div class="wrapper">
		<div class="grid_16 block grid_16MarginLeft">
			<div id="academicsContent">
				<div class="grid_16">
					<div class="block_head">
						<h2>
							Academic InBox :
							<s:property value="#session.academicYearName" />
						</h2>
					</div>
					<div class="block_content" id="academicsBlockContent">
						<div id="tabContent">
							<div id="tabWrapper">
								<div id="tabNavigation" style="display: none;">
									<ul>
									<s:if test='%{user.isSchoolStudent=="Y" || user.isParent=="Y" || (user.isSchoolTeacher=="Y" && !tempBoolean)}'>
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
										
										<s:if test='%{user.isSchoolAdmin == "Y"}'>
											<li class="selected grid_4">
										</s:if>
										<s:else>
											<li class="grid_4">
										</s:else>
											<a href="#" id='/common/ajaxDoInboxGetScrapMessagesList.do' class='academicYearId=<s:property value="#session.academicYear" />'
												type='stepInbox'>My Inbox eeeeee</a>
										</li>
										<li class="grid_4">
											<a href='#' id='/common/ajaxDoGetSchoolWideAlertsList.do' class='academicYearId=<s:property value="#session.academicYear" />'
												type='stepDBMsg'>Dashboard Messages</a>
										</li>
									</s:else>
										<li>
											<a href='#' id='/common/ajaxGetSenderMessages.do' class='academicYearId=<s:property value="#session.academicYear" />'
												type='stepSendMsg'>Sent Messages</a>
										</li>
									</ul>
								</div>
								<div id="steps" class='manageAcademicBlockContent'>
								   <s:if test='%{user.isSchoolStudent=="Y" || user.isParent=="Y" || (user.isSchoolTeacher=="Y" && !tempBoolean)}'>
								   	<fieldset class="step" id='stepInbox'>
									</fieldset>
									<fieldset class="step" id='stepDBMsg'>
									</fieldset>
								   </s:if>
								   <s:else>
								   <s:if test='%{user.isSchoolTransport=="N" && user.isTransportFinance!="Y"}'>
								   	<fieldset class="step" id='stepSWMessage'>
									</fieldset>
									</s:if>
									<s:if test='%{customer.transportModuleStatus &&(user.isSchoolTransport=="Y" || user.isSchoolAdmin=="Y")}'>
									<fieldset class="step" id='stepTRMessage'>
									</fieldset>
									</s:if>
									<fieldset class="step" id='stepInbox'>
									</fieldset>
									<fieldset class="step" id='stepDBMsg'>
									</fieldset>
								   </s:else>
								   
								    
									
									<fieldset class="step" id='stepSendMsg'>
									</fieldset>
								</div>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
--><script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<script Language="Javascript1.2" type="text/javascript">
$(document).ready(
		function() {
			changePageTitle("Manage Academics");
			$('#commonInbox').addClass('current');
			commonLoadTab();
			function getUrlVars()
			 {
			    var vars = [], hash;
			    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
			    for(var i = 0; i < hashes.length; i++)
			    {
			        hash = hashes[i].split('=');
			        vars.push(hash[0]);
			        vars[hash[0]] = hash[1];
			    }
			    return vars;
			}
			if(getUrlVars()["value"]=="dashBoard"){
					$('li.DashBoardLink a').click();
			}
		});
</script>	