<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{tempList!= null && !tempList.isEmpty()}">
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
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th>
					Type Of The SMS
				</th>
				<th>
					Count
				</th>
				<th>
					Sent Date
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="tempList">
				<tr>
					<td>
						<s:if test='%{status=="FR"}'>
							Fee Reminder
						</s:if>
						<s:elseif test='%{status=="LP"}'>
							Fee Defaulters
						</s:elseif>
						<s:elseif test='%{status=="A"}'>
							Absents (Both students and staff)
						</s:elseif>
						<s:elseif test='%{status=="EX"}'>
							Exam schedules reminder
						</s:elseif>
						<s:elseif test='%{status=="M"}'>
							Manual
						</s:elseif>
						<s:elseif test='%{status=="BA"}'>
							Birthday Alerts
						</s:elseif>
						<s:elseif test='%{status=="FPWD"}'>
							Forgot Password
						</s:elseif>
						<s:elseif test='%{status=="TR"}'>
							Transport
						</s:elseif>
						<s:else>
						  &nbsp;
						</s:else>
					</td>
					<td>
						<s:property value="smsCount" />
					</td>
					<td>
						<s:property value="messageDescription" />
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		You have not used any SMS.
	</div>
</s:else>
<script  type="text/javascript">
changePageTitle('Total Sms');
TableAdvanced.init();
</script>
