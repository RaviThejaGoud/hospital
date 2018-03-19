<%@ include file="/common/taglibs.jsp"%> 
<jsp:include page="/common/messages.jsp"></jsp:include>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<s:if test='%{user.isSchoolAsstStaff=="N"}'>
<s:if test='%{anyId =="M"}'>
		<!-- <p>
			<span class="label label-danger"> NOTE : </span>
			<b>&nbsp;Delivered sms count will update in 5min.</b>
		</p> -->
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
						Purpose
					</th>
					<s:if test='%{anyId != "E"}'>
						<th>
							Sent Sms Count
						</th>
					</s:if>
					<th>
						Message Date
					</th>
				</tr>
			</thead>
			<tbody>
			<s:if test='%{user.isOnlySchoolTeacher =="Y" || user.isOnlySchoolHod=="Y" || user.isChairMan == "Y" || user.isAdminCoordinator=="Y" }'>
					<s:iterator value="noticeBoardMessagesList">
						<tr>
						<td style="display: none;">
						 <s:property value="sortingMessageDate"/>
						</td>
							<%-- <td>
								<s:if test="%{purposeType != null && !purposeType.isEmpty()}">
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
							</td> --%>
							<td>
								<s:property value="messageDescription" />
							</td>
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
						</tr>
					</s:iterator>
				</s:if>
			</tbody>
		</table>
	</s:if>
	<s:elseif test='%{empId == "smsHistory"}'>
		<div class="alert alert-info">
			No results found in between given dates.
			</div>
	</s:elseif>
	<s:else>
	<br><br><br><br>
		<div class="alert alert-info">
		No results found for past 30 days.
		</div>
	</s:else>
</s:if>
<script type="text/javascript">
//alert('remove='+$('li#viewEmail').addClass('active'));
	changePageTitle('School Wide Messages ');
	TableAdvanced.init();
	$('li#adminSmsDiv').removeClass('active');
	$('li#sendSchoolwideEmailDiv').removeClass('active');
	$('li#classTeacherSmsDiv').removeClass('active');
	$('li#smsDiv').addClass('active');
	$('li#viewEmail').addClass('active');
	$("a#smsHistoryClick").click(function(){
		$("a.smsHistoryId").click();
		});
</script>
