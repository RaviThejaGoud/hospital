<%@ include file="/common/taglibs.jsp"%>
<td colspan="5" style="background-color: #CCC;">
	<s:if test="%{student != null}">
		<div class="grid_6">
			<div class="grid_3" style="text-align: left;">
				<h2>
					Student Details
				</h2>
			</div>
		</div>
		<div class="grid_8" style="width: 500px;">
			<div class="grid_2"  style="float:right">
				<img
						src='<c:url value="${student.profileImage.adjustedAttachmentFilePath}"/>'
						alt='<s:property  value="student.account.person.personFullName" />'
						 height="100px" width="100px" style="padding: 3px 25px 0px 0px"/>
			</div>
			<div class="grid_6" style="float:left">
				<div class="grid_2" style="width:115px;" >
					Student Id:
				</div>
				<div class="grid_3" style="float:left"> 
					<s:property value="student.rollNumber" />
				</div>
				<br />
				<br />
				<div class="grid_2" style="width:115px;" >
					Student Name:
				</div>
				<div class="grid_3" style="float:left">
					<s:property value="student.account.person.personFullName" />
				</div>
				<br />
				<br />
				<div class="grid_2" style="width:115px;" >
					Class:
				</div>
				<div class="grid_3" style="float:left">
					<s:property value="student.classAndSection" />
				</div>
				<br />
				<br />
				<div class="grid_2" style="width:115px;" >
					Parent Name:
				</div>
				<div class="grid_3" style="float:left">
					<s:property value="student.account.person.fatherName" />
				</div>
				<br />
				<br />
				<div class="grid_2" style="width:115px;" >
					Parent EmailId:
				</div>
				<div class="grid_3" style="float:left">
					<s:property value="student.account.person.parentEmail" />
				</div>
				<s:if test='%{student.account.person.phoneNumber != null }'>
					<br />
					<br />
					<div class="grid_2" style="width:115px;" >
						Parent Phone No:
					</div>
					<div class="grid_3" style="float:left">
						<s:property value="student.account.person.phoneNumber" />
					</div>
				</s:if>
				 <br />
				<br />
				<div class="grid_2" style="width:115px;"  >
					Parent Mobile No:
				</div>
				<div class="grid_3" style="float:left">
					<s:property value="student.account.person.mobileNumber" />
				</div>
			</div>
		</div>
	</s:if>
</td>

