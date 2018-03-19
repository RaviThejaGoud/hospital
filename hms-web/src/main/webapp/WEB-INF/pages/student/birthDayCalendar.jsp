<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>
<div class="grid_4 alpha">
<div class="grid_4 alpha omega" style="margin-top: 10px;">
	<div class="block_head">
		<h2>
			Birthdays
		</h2>
	</div>
	<div class="block_content" id="sideMenu" style="padding: 10px;">
		<s:if test="%{birthDaysListSet != null && !birthDaysListSet.isEmpty()}">
			<marquee DIRECTION="up" scrollamount="2">
				<div style="padding-top: 1px">
					<s:iterator value="birthDaysListSet">
						<s:property value="firstName" />&nbsp;<s:property value="lastName" />
						has 
						birthDay On
						<s:property value="dateOfBirthMonthName"/>
						<br/>
						 <img src='<c:url value="${adjustedAttachmentFilePath}"/>'
								alt='<s:property  value="viewStudentPersonAccountDetails.personFullName" />'
								align="left" height="50px" width="50px" border="0" />
						 <br/>		
			             <s:url id="doWishBirthday"
							action="ajaxBirthDayWishes" includeParams="all"
							escapeAmp="false" namespace="/student">
							<s:param name="id" value="{id}" />
							<s:param name="accountId" value="{accountId}" />
							<s:param name="rollNumber" value="{rollNumber}" />
							<s:param name="personName" value="{personFullName}" />
							<s:param name="firstName" value="{user.person.firstName}" />
							<s:param name="lastName" value="{user.person.lastName}" />
						</s:url>
						<sj:a href="%{doWishBirthday}" indicator="indicator"
							targets="studentContent">
				          <p>  Wish him.</p>
						</sj:a>
					</s:iterator>
				</div>
			</marquee>
		</s:if>
		<s:else>
		Currently there are no UpComing Birthdays for Students.
		</s:else>
		</div>
	 </div>
	</div>
	<script type="text/javascript">
if(CKEDITOR.instances.editor)
					CKEDITOR.remove(CKEDITOR.instances.editor);	
				CKEDITOR.replace( 'editor',{charcount_limit : 1000});
	</script>			