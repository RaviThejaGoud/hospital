<%@ include file="/common/taglibs.jsp"%>
<div class="grid_9 omega block">
	<div class="block_head">
		<h2>
			Teacher Profile
		</h2>
	</div>
	
	<div id="popupBodyWhite" class="grid_6">
		<s:if test="{viewStaffPersonAccountDetails != null}">
		<div>
			<b>Personal Details</b>
		</div>
			<table width="85%">
			<tr>
				<td style="text-align: right">
					First Name :
				</td>
				<td style="text-align: left;">
					<s:property value="viewStaffPersonAccountDetails.firstName" />
				</td>
			</tr>
			<tr>
				<td style="text-align: right">
					Last Name :
				</td>
				<td style="text-align: left;">
					<s:property value="viewStaffPersonAccountDetails.lastName" />
				</td>
			</tr>
			<tr>
				<td style="text-align: right">
					Employee ID :
				</td>
				<td style="text-align: left;">
					<s:property value="viewStaffPersonAccountDetails.username" />
				</td>
			</tr>
			<tr>
				<td style="text-align: right">
					Blood Group :
				</td>
				<td style="text-align: left;">
					<s:property value="viewStaffPersonAccountDetails.bloodGroup" />
				</td>
			</tr>
			<tr>
				<td style="text-align: right">
					Marital Status :
				</td>
				<td style="text-align: left;">
					<s:if test="%{viewStaffPersonAccountDetails.maritalStatus != null}">
						<s:if test='%{viewStaffPersonAccountDetails.maritalStatus != "M"}'>UnMarried</s:if>
						<s:else>Married</s:else>
				</s:if>
				<s:else>
						---
				</s:else>
				</td>
			</tr>
			<tr>
				<td style="text-align: right">
					Date of Birth :
				</td>
				<td style="text-align: left;">
					<s:property value="viewStaffPersonAccountDetails.dateOfBirth" />
				</td>
			</tr>
		</table>
		<div>
			<b>Education Details</b>
		</div>
			<table width="59%">
			<tr>
				<td style="text-align: right">
					UG :
				</td>
				<td style="text-align: left;">
					<s:property value="viewStaffPersonAccountDetails.qualification1" />
				</td>
			</tr>
			<tr>
				<td style="text-align: right">
					PG :
				</td>
				<td style="text-align: left;">
					<s:if
						test="%{viewStaffPersonAccountDetails.qualification2 != null || viewStaffPersonAccountDetails.qualification2 != ''}">
							<s:property value="viewStaffPersonAccountDetails.qualification2" />
					</s:if>
				<s:else>
						----
				</s:else>
				</td>
			</tr>
			<tr>
				<td style="text-align: right">
					Experience :
				</td>
				<td style="text-align: left;">
					<s:property value="viewStaffPersonAccountDetails.experience" />
				</td>
			</tr>
		</table>	
			
		<div>
			<b>Contact Details</b>
		</div>
			<table width="100%">
			<tr>
				<td style="text-align: right">
					Adress Line1 :
				</td>
				<td style="text-align: left;">
					<s:if test="%{viewStaffPersonAccountDetails.addressLine1 != null}">
						<s:property value="viewStaffPersonAccountDetails.addressLine1" />
					</s:if>
				<s:else>
				</s:else>
				</td>
			</tr>
			<tr>
				<td style="text-align: right">
					Adress Line2 :
				</td>
				<td style="text-align: left;">
					<s:if test="%{viewStaffPersonAccountDetails.addressLine2 == ''}">
					</s:if>
				<s:else>
						<s:property value="viewStaffPersonAccountDetails.addressLine2" />
				</s:else>
				</td>
			</tr>
			<tr>
				<td style="text-align: right">
						City :
				</td>
				<td style="text-align: left;">
					<s:property value="viewStaffPersonAccountDetails.city" />
				</td>
			</tr>
			<tr>
				<td style="text-align: right">
					State :
				</td>
				<td style="text-align: left;">
					<s:property value="viewStaffPersonAccountDetails.state" />
				</td>
			</tr>
			<tr>
				<td style="text-align: right">
					Zipcode :
				</td>
				<td style="text-align: left;">
					<s:property value="viewStaffPersonAccountDetails.postalCode" />
				</td>
			</tr>
			<tr>
				<td style="text-align: right">
					Home Number :
				</td>
				<td style="text-align: left;">
					<s:if test="%{viewStaffPersonAccountDetails.phoneNumber == ''}">
					
					</s:if>
					<s:else>
						<s:property value="viewStaffPersonAccountDetails.phoneNumber" />
					</s:else>
				</td>
			</tr>
			<tr>
				<td style="text-align: right">
					Mobile Number :
				</td>
				<td style="text-align: left;">
					<s:property value="viewStaffPersonAccountDetails.mobileNumber" />
				</td>
			</tr>
		</table>	
		</s:if>	
	</div>
	<div class="grid_3" style="margin: 11px 0 0 54px;">
		<img
			src='<c:url value="${user.profileImage.adjustedAttachmentFilePath}"/>'
			alt='<s:property  value="viewStaffPersonAccountDetails.personFullName" />'
			 height="150px" width="150px" border="0" />
	</div>
</div>