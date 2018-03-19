<%@ include file="/common/taglibs.jsp"%>
<table border="0" cellpadding="0" cellspacing="0" width="100%"
	style="background-color: #4aabf9;">
	<tr>
		<td>
			<center>
				<table border="0" cellpadding="0" cellspacing="0"
					style="height: 100%;">
					<tr>
						<td>
							<div class="header-inner container">
								<div class="header-inner">
									<a class="navbar-brand" href="#"> <img
										src='<s:property  value="customer.customerLogo"/>' border="0"
										alt="logo" class="logoHeader"
										style="height: 80px; width: 100px;" />
									</a>
								</div>
							</div>
						</td>
						<td valign="top" style="padding: 20px;">
							<div class="textdark">
								<h1>
									<s:property value="customer.organization" />
								</h1>
								<s:property value="customer.customerFormattedAddress" />
							</div> <br />
						</td>
					</tr>
				</table>
			</center>
		</td>
	</tr>
</table>
<div align="center">
	<s:if
		test="%{admissionSettingsList !=null || !admissionSettingsList.isEmpty}">
		<s:if test="%{admissionSettingsList.size >0}">
			<s:iterator value="admissionSettingsList">
				<h1 style="color: #eeeeeee;">
					Admissions Are Opened For Academic Year <span class="appHeader"><s:property
							value="academicYear.academicYear" /></span>
				</h1>
			</s:iterator>
			<table class="appTable" width="50%" cellspacing="0" cellpadding="0"
				style="border: 1px solid #eee;">
				<s:iterator value="admissionSettingsList">
					<tr>
						<td>Applications Issue Date</td>
						<td><s:property value="applicationStartDateStr" /></td>
					</tr>
					<tr>
						<td>Applications Closed Date</td>
						<td><s:property value="applicationClosedDateStr" /></td>
					</tr>
					
					<s:if test="%{entranceDateStr !=null}">
						<tr>
							<td>Entrance Exam Date</td>
							<td><s:property value="entranceDateStr" />&nbsp;</td>
						</tr>
					</s:if>
					
					<tr>
						<td>Admissions End Date</td>
						<td><s:property value="admissionsEndDateStr" /></td>
					</tr>
					<tr>
						<td>Application Fee</td>
						<td><s:property value="applicationFee" /></td>
					</tr>
				</s:iterator>
			</table>
			<s:iterator value="admissionSettingsList">
				<s:if test='%{admissionOpenOrClose == "Y"}'>
					<h2>
						For Online Application<a
							href="${pageContext.request.contextPath}/signup/onlineApplicationFormByCustomer.do?admissionSettings.id=<s:property value="id"/>"
							target="_new"
							style="color: #16a1f2; font-size: 25px; font-weight: 200; text-decoration: none;">&nbsp;
							Click Here </a>
					</h2>
				</s:if>
				<s:else>
					<strong><div class="alert alert-info">Application
							issue date is over to apply through online.Please Contact Us.</div></strong>
				</s:else>
			</s:iterator>
		</s:if>
		<s:else>
			<div class="spaceDiv">&nbsp;</div>
			<strong><div class="alert alert-info">Admissions are
					not opened keep watching regularly for updates.</div></strong>
		</s:else>
	</s:if>
</div>
<style>
 .appTable td {
    border: 1px solid black;
    border-collapse: collapse;
    padding: 5px;
    text-align: left;
}
.alert-info {
  color: #31708f;
  background-color: #d9edf7;
  border-color: #bce8f1;
}
</style>
