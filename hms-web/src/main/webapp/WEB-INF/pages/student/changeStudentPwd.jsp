<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/common/jquery.autoCheck.js"></script>
<head>
	<title>My Subjects</title>

</head>
<body />

	<div class="wrapper">
	<style type="text/css">
.subjects {
	border: 1px solid #ccc;
}

.subjects th {
	width: 150px;
	padding: 5px;
	background-color: #ccc;
	border-right: 1px solid #fff;
	text-align: left;
}

.subjects th.last {
	width: 150px;
	padding: 5px;
	background-color: #ccc;
	text-align: left;
}

.subjects td {
	width: 150px;
	padding: 5px;
	text-align: left;
	border: 1px solid #ccc;
}
</style>
		<!-- wrapper begins -->
			<div class="grid_9 omega">
				<div class="block_head">
					<h2>
						Change Password
					</h2>
				</div>
				<div class="block_content">
				<s:if test="hasActionErrors()">
				   <div class="block"
						style="margin-bottom: 0px; margin-top: -15px; padding-bottom: 0px;">
						<div style="display: block" class="message errormsg">
							<s:iterator value="actionErrors">
								<s:property /><br/>
							</s:iterator>
						</div>
				   </div>
				</s:if>
					<s:form id="changeStudentPwd" action="ajaxChangeStudentPwd" method="post"
							theme="css_xhtml" namespace="/student">
						
							<table width="300px">
								<tr>
									<td>
									    <s:password name="currentPwd" 
											label="Current Password" cssClass="text small required"
											required="true" maxlength="40"></s:password>
								    </td>
								</tr>
								
								<tr>
									<td>
										<s:password name="newPwd" 
											label="New Password" cssClass="text small required"
											required="true" maxlength="40"></s:password>
									</td>
								</tr>
								<tr>
									<td>
										<s:password name="confirmPwd" 
											label="Confirm PassWord" cssClass="text small required"
											required="true" maxlength="40"></s:password>
									</td>
								</tr>
								<tr>
									<td>
										<sj:submit   targets="studentContent" value="Submit"
											cssClass="submit small" formIds="changeStudentPwd"
											indicator="indicator" onClickTopics="changePwdFormValidation"/>
										<s:url id="studentPwdUrl" action="ajaxCancelChangeStudentPwd"
											includeParams="all" escapeAmp="false">
										</s:url>
										<sj:a href="%{studentPwdUrl}" cssClass="cancelButton"
											indicator="indicator" targets="studentContent"
											button="false" buttonIcon="ui-icon-plus">Cancel  sfd dsf dsfgf</sj:a>
									</td>
								</tr>
							</table>
						</s:form>
			 	</div>
			</div>
		</div>			
	
<script type="text/javascript">

    $.subscribe('changePwdFormValidation', function(event, data) {
         if ($('#changeStudentPwd').valid())
         	 return true;
          else
          	 return false;
    });

</script>