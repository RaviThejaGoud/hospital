<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>
	<div class="block_head">
		<h2>
			Birthdays
		</h2>
	</div>
	<div class="block_content" id="sideMenu" style="padding: 10px;margin-top: -15px;">
		<s:if test="%{classNameList != null && !classNameList.isEmpty()}">
			<marquee DIRECTION="up" scrollamount="2">
				<div style="padding-top: 1px">
					<s:iterator value="classNameList">
						<s:property value="firstName" />&nbsp;<s:property value="lastName" />
						<s:if test="%{roleName == 'ROLE_STUDENT'}">(Student)</s:if>
						<s:else>(Staff)</s:else>
						has 
						Birthday On
						<s:property value="dateOfBirthMonthName"/>
						<br/>
						 <br/>  
						 <s:url id="doStaffBirthdayWishes"
							action="ajaxStaffBirthDayWishes" includeParams="all"
							escapeAmp="false">
							<s:param name="id" value="{id}" />
							<s:param name="accountId" value="{accountId}" />
							<s:param name="firstName" value="{user.person.firstName}" />
							<s:param name="lastName" value="{user.person.lastName}" />
						</s:url>
						<sj:a href="%{doStaffBirthdayWishes}" indicator="indicator"
							targets="myBirthDayHome" onClickTopics="staffBirthDayDetails">
				          <!--<p>  Wish him.</p>
						-->
					</sj:a></s:iterator>
				</div>
			</marquee>
		</s:if>
		<s:else>
		Currently there are no Upcoming Birthdays for Staff.
		</s:else>
		</div>
	<script type="text/javascript">
	if(CKEDITOR.instances.editor)
						CKEDITOR.remove(CKEDITOR.instances.editor);	
					CKEDITOR.replace( 'editor',{charcount_limit : 1000});
	</script>			