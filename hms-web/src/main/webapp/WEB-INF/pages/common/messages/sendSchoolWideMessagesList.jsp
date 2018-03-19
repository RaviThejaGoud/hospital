
<%@page import="com.churchgroup.util.string.StringFunctions"%><%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/onload.js">
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<%-- <s:if test="%{customer.allowedTotalSms}">
		<p class="text-success">
			Total Allotted SMS :
			<s:property value='customer.allowedTotalSms' />
			(per academic year)
		</p>
		<s:if test="%{(customer.getAllowedTotalSms())-(smsCnt) >= 0}">
		  Available SMS :
		   <s:property value="(customer.getAllowedTotalSms())-(smsCnt)" /> (per academic year)
		 </s:if>
		 <s:else>
		 Exceeded SMS :
		   <FONT color="red"><s:property value="(smsCnt)-(customer.getAllowedTotalSms())" /></FONT> (per academic year)
		 </s:else>
		 <div class="spaceDiv"></div>
	</s:if> --%>
<%-- <s:if test='%{user.isSchoolAsstStaff=="N"}'>	 --%>
<s:if test='%{anyId =="M"}'>
	<div class="panel-body col-md-12">
		<div class="col-md-1">
			<span class="label label-danger"> NOTE : </span>
		</div>
		<div class="col-md-10">
			<ul>
				<li>
					You can see past 30 days SMS details from the below table. To view all
					<a href="#" id="smsHistoryClick">click here</a>.
				</li>
				<!-- <li>
					Delivered SMS count, delivery status will be updated in 15 min.
				</li> -->
				<li>
					Sent SMS Count depend on No.of characters present in the message content.160 Characters of message is treated as 1SMS.
				</li>	
			</ul>
		</div>
	</div>
	</s:if>
	<s:if test="%{noticeBoardMessagesList != null && !noticeBoardMessagesList.isEmpty()}">
	<table class="table table-striped table-bordered table-hover table-full-width"
			id="sample_5">
			<thead>
				<tr>
				<th style="display: none;">
			    </th>
					<th style="white-space:normal">
						SMS Content
					</th>
					<th>
						SMS Type
					</th>
					<th>
						Sent By
					</th>
					<s:if test='%{anyId != "E"}'>
						<!-- <th>
							#Delivered
						</th> -->
						<th>
							Sent
						</th>
					</s:if>
					<th>
						Sent Date
					</th>
					<th>
						Failed No's
					</th>
					<th>
						Invalid No's
					</th>
					<th>
						SMS Details
					</th>	
				</tr>
			</thead>
			<tbody>
				<s:if test='%{(user.isOnlySchoolTeacher=="Y" || user.isSchoolAsstStaff=="Y") && user.isSchoolTransport=="N"}'>
					<s:iterator value="noticeBoardMessagesList" status="itStatus">
						<tr>
						<td style="display: none;">
						 <s:property value="sortingMessageDate"/>
						</td>
							<td>
								<!--<s:if test="%{purposeType != null && !purposeType.isEmpty()}">
									<s:property value="purposeType" />
								</s:if>
								<s:elseif test='%{status == "M"}'>
									<s:if
										test="%{messageDescription != null && !messageDescription.isEmpty()}">
										<s:property value="messageDescription" />
									</s:if>
								</s:elseif>
								<s:elseif test='%{status == "TR"}'>
									<s:if test="%{purposeType != null && !purposeType.isEmpty()}">
										<s:property value="purposeType" />
									</s:if>
								</s:elseif>
							-->
							<s:property value="messageDescription" />
							</td>
							<td>
								<s:property value="senderNameStr" escape="false"/>
							</td>
							<td>
								<s:if test='%{status == "A"}'>
									Automated
								</s:if>
								<s:else>
								  Manual
								</s:else>
							</td>
							<%-- <td>
								<s:if test="%{smsCount > 0}">
									<s:property value="smsCount" />
								</s:if>
								<s:elseif test='%{anyId == "E"}'>
								</s:elseif>
								<s:else>
								 0
								</s:else>
							</td> --%>
							<td>
								<s:if test="%{sentSms > 0}">
									<s:property value="sentSms" />
								</s:if>
								<s:elseif test='%{anyId == "E"}'>
								</s:elseif>
								<s:else>
									 0
								</s:else>
							</td>
							<td>
								<s:property value="messageDateStr" />
							</td>
							<td>
							<ul class="tooltipDiv">
								<li>
									<s:set scope="request" var="failedMobileNos" value="failedMobileNos"/>
									<%
										int count = 0;
										String failedMobileNosArr[] = null ;
										String failedMobileNos= (String) request.getAttribute("failedMobileNos");
										if(!StringFunctions.isNullOrEmpty(failedMobileNos))
										{
											failedMobileNosArr = failedMobileNos.split(",");
											count = failedMobileNosArr.length;
										}
									%>
									<a href="#">view (<%=count %>)</a>
									<ul class="tooltipSubDiv">
										<div class="popover bottom " style="display: none;">
											<div class="arrow"></div>
											<h3 class="popover-title">
												Failed No's
											</h3>
											<div class="popover-content">
												<s:if test='%{failedMobileNos != null && failedMobileNos != empty}'>
												<%
													if(count > 0)
													{
														for(int i=0;i<failedMobileNosArr.length;i++)
														{
															out.println(failedMobileNosArr[i]+"<br/>");
														}
													}
													else
														out.println("No failed mobile numbers.");
												 %>
												</s:if>
												<s:else>
													No failed mobile numbers.
												</s:else>
											</div>
										</div>
									</ul>
								</li>
							</ul>
						</td>
						<td>
							<ul class="tooltipDiv">
								<li>
									<s:set scope="request" var="invalidMobileNos" value="invalidMobileNos"/>
									<%
										count = 0;
										String invalidMobileNosArr[] = null ;
										String invalidMobileNos= (String) request.getAttribute("invalidMobileNos");
										if(!StringFunctions.isNullOrEmpty(invalidMobileNos))
										{
											invalidMobileNosArr = invalidMobileNos.split(",");
											count = invalidMobileNosArr.length;
										}
									%>
									<a href="#">view (<%=count %>)</a>
									<ul class="tooltipSubDiv">
										<div class="popover bottom " style="display: none;">
											<div class="arrow"></div>
											<h3 class="popover-title">
												Invalid No's
											</h3>
											<div class="popover-content">
												<s:if test='%{invalidMobileNos != null && invalidMobileNos != empty}'>
												<%
													if(count > 0)
													{
														for(int i=0;i<invalidMobileNosArr.length;i++)
														{
															out.println(invalidMobileNosArr[i]+"<br/>");
														}
													}
													else
														out.println("No invalid mobile numbers.");
												 %>
												</s:if>
												<s:else>
													No invalid mobile numbers.
												</s:else>
											</div>
										</div>
									</ul>
								</li>
							</ul>
						</td>
						<td align="center">												
							<a data-toggle="modal"  href="#popupMessageDetailsDiv"  
									onclick="javascript:popupViewMessageDetails(<s:property value="messageId" />);"> View
							</a>
						</td>
						</tr>
					</s:iterator>
				</s:if>
				<s:elseif
					test='%{user.isSchoolTransport=="Y" || user.isOnlySchoolAdmin=="Y" || user.isSchoolPrincipal=="Y" ||  user.isSchoolAsstStaff=="Y" || user.isSchoolDirector == "Y" || user.isReceptionist == "Y"}'>
						<s:iterator value="noticeBoardMessagesList" status="itStatus1">
					<tr>
					<td style="display: none;">
						 <s:property value="sortingMessageDate"/>
						</td>
						<td>
							<!--<s:if test='%{status == "TR"}'>
								<s:if test="%{purposeType != null && !purposeType.isEmpty()}">
									<s:property value="purposeType" />
								</s:if>
							</s:if>
							<s:elseif test='%{status == "M"}'>
								<s:if
									test="%{messageDescription != null && !messageDescription.isEmpty()}">
									<span style="white-space:normal"><s:property value="messageDescription" /></span>
								</s:if>
							</s:elseif>
							<s:else>
								<s:if test="%{purposeType != null && !purposeType.isEmpty()}">
									<s:property value="purposeType" />
								</s:if>
							</s:else>
						-->
						<span style="white-space:normal"><s:property value="messageDescription" /></span>
						</td>
						<td>
								<s:if test='%{status == "A"}'>
									Automated
								</s:if>
								<s:else>
								  Manual
								</s:else>
							</td>
							<td>
								<s:property value="senderNameStr" escape="false"/>
							</td>
						<%-- <td>
							<s:if test="%{smsCount > 0}">
								<s:property value="smsCount" />
							</s:if>
							<s:else>
							 0
							</s:else>
						</td> --%>
						<td>
							<s:if test="%{sentSms > 0}">
								<s:property value="sentSms" />
							</s:if>
							<s:else>
								 0
							</s:else>
						</td>
						<td>
							<s:property value="messageDateStr" />
						</td>
						<td>
							<ul class="tooltipDiv">
								<li>
									<s:set scope="request" var="failedMobileNos" value="failedMobileNos"/>
									<%
										int count = 0;
										String failedMobileNosArr[] = null ;
										String failedMobileNos= (String) request.getAttribute("failedMobileNos");
										if(!StringFunctions.isNullOrEmpty(failedMobileNos))
										{
											failedMobileNosArr = failedMobileNos.split(",");
											count = failedMobileNosArr.length;
										}
									%>
									<a href="#">view (<%=count %>)</a>
									<ul class="tooltipSubDiv">
										<div class="popover bottom " style="display: none;">
											<div class="arrow"></div>
											<h3 class="popover-title">
												Failed No's
											</h3>
											<div class="popover-content">
												<s:if test='%{failedMobileNos != null && failedMobileNos != empty}'>
												<%
													if(count > 0)
													{
														for(int i=0;i<failedMobileNosArr.length;i++)
														{
															out.println(failedMobileNosArr[i]+"<br/>");
														}
													}
													else
														out.println("No failed mobile numbers.");
												 %>
												</s:if>
												<s:else>
													No failed mobile numbers.
												</s:else>
											</div>
										</div>
									</ul>
								</li>
							</ul>
						</td>
						<td>
							<ul class="tooltipDiv">
								<li>
									<s:set scope="request" var="invalidMobileNos" value="invalidMobileNos"/>
									<%
										count = 0;
										String invalidMobileNosArr[] = null ;
										String invalidMobileNos= (String) request.getAttribute("invalidMobileNos");
										if(!StringFunctions.isNullOrEmpty(invalidMobileNos))
										{
											invalidMobileNosArr = invalidMobileNos.split(",");
											count = invalidMobileNosArr.length;
										}
									%>
									<a href="#">view (<%=count %>)</a>
									<ul class="tooltipSubDiv">
										<div class="popover bottom " style="display: none;">
											<div class="arrow"></div>
											<h3 class="popover-title">
												Invalid No's
											</h3>
											<div class="popover-content">
												<s:if test='%{invalidMobileNos != null && invalidMobileNos != empty}'>
												<%
													if(count > 0)
													{
														for(int i=0;i<invalidMobileNosArr.length;i++)
														{
															out.println(invalidMobileNosArr[i]+"<br/>");
														}
													}
													else
														out.println("No invalid mobile numbers.");
												 %>
												</s:if>
												<s:else>
													No invalid mobile numbers.
												</s:else>
											</div>
										</div>
									</ul>
								</li>
							</ul>
						</td>
						<td align="center">												
							<a data-toggle="modal"  href="#popupMessageDetailsDiv"  
									onclick="javascript:popupViewMessageDetails(<s:property value="messageId" />);"> View
							</a>
						</td>
						</tr>
					</s:iterator>
				</s:elseif>
			</tbody>
		</table>
	</s:if>
	<s:elseif test='%{empId == "smsHistory"}'>
		<div class="alert alert-info">
			No results found in between given dates.
			</div>
	</s:elseif>
	<s:else>
	<br><br><br><br><br>
		<div class="alert alert-info">
		No results found for past 30 days.
		</div>
	</s:else>
<%-- </s:if> --%>
<div id="popupMessageDetailsDiv"></div>
<script type="text/javascript">
	changePageTitle('School Wide Messages ');
	$(document).ready(function(){
	TableAdvanced.init();
		$('li#adminSmsDiv').removeClass('active');
		$('li#sendSchoolwideEmailDiv').removeClass('active');
		$('li#smsDiv').addClass('active');
		$('li#CreateSMSEmail').removeClass('active');
		$('li#loginCredentials').removeClass('active');
	});
	$("a#smsHistoryClick").click(function(){
	$("a.smsHistoryId").click();
	});
	
	function popupViewMessageDetails(messageId) {
		var url = jQuery.url.getChatURL("/common/ajaxViewMessageDetails.do");
		var pars = "tempId=" +messageId;
		$('#popupMessageDetailsDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
				url : url,
				cache : false,
				data : pars,
				success : function(html) {
					$("#popupMessageDetailsDiv").html(html);
				}
			});
	}
</script>
 