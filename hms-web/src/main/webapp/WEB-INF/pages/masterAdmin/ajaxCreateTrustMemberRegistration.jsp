<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<s:form action="ajaxCreateOrganizationMembers" id="signupForm" method="post"
	theme="simple" namespace="/masterAdmin" cssClass="form-horizontal">
	<%@ include file="/common/messages.jsp"%>
	<s:hidden name="packageId" value="%{tempString}"></s:hidden>
	<div class='error' style='display: none;'>
		<span></span>
	</div>
	<h4 class="pageTitle bold form-section">
		Personal Details
	</h4>
	<div class="form-body">
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-5">
					<span class="required">*</span>Select Organization :
				</label>
				<div class="col-md-7">
				<s:if test="%{objectList != null && !objectList.isEmpty()}">
					<s:select id="organizationObjId" list="objectList"
					cssClass="required form-control input-medium required"
					listKey="id" listValue="organizationName" headerKey=""
					headerValue="- Select -" name="organizationObj.id" theme="simple" />
				</s:if>
				<s:elseif test="organizationObj !=null">
					<s:hidden name="organizationObj.id"></s:hidden>
					<sj:textfield id="firstName" name="organizationObj.organizationName" size="50" maxlength="50"  cssClass="form-control input-medium required" disabled="true"/>
				</s:elseif>	
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-4">
					<span class="required">*</span>Select Role :
				</label>
				<div class="col-md-8">
					<s:select id="roleName" list="staffRoles" 
						cssClass="form-control input-medium required" theme="simple"
						name="roleName" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-5">
					<span class="required">*</span>First Name :
				</label>
				<div class="col-md-7">
					<sj:textfield id="firstName" name="person.firstName" size="50"
						maxlength="50"  
						cssClass="form-control input-medium required" />
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-4">
					<span class="required">*</span>Last Name :
				</label>
				<div class="col-md-8">
					<sj:textfield id="lastName" name="person.lastName" size="50"
						maxlength="50"  
						cssClass="form-control input-medium required" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-5">
					<span class="required">*</span>Email Address :
				</label>
				<div class="col-md-7">
					<sj:textfield id="email" size="30" maxlength="120"
						 name="newUser.username" 
						cssClass="form-control input-medium required" />
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-4">
					<span class="required">*</span>Mobile Number :
				</label>
				<div class="col-md-8">
					<sj:textfield id="mobileNumber" size="30" maxlength="14"
						 name="person.mobileNumber"  onchange="javascript:validateMobNumber(this.value)" onkeypress="return formatMobileNumber(this,event);"
						cssClass="form-control input-medium required numeric" />
				</div>
			</div>
		</div>
	</div>
	<div class="form-actions fluid">
		<div class="col-md-6">
			<div class="col-md-offset-5 col-md-9">
				<sj:submit   targets="mainContentDiv" value="Submit"
					cssClass="btn blue"  validate="true"
					onCompleteTopics="clickGovtCustomers" onBeforeTopics="organizationTrustMembers" />
				<s:url id="doCancel" action="ajaxOrganizationMemberDetails"
					includeParams="all" namespace="/masterAdmin"></s:url>
				<sj:a href="%{doCancel}" cssClass="btn default"
					indicator="indicator" targets="mainContentDiv" button="false">Cancel</sj:a>
			</div>
		</div>
	</div>
</div>
</s:form>
<script type="text/javascript">
	$('.numeric').numeric();
	$.destroyTopic('organizationTrustMembers');
	$("input#email").autoCheck("${pageContext.request.contextPath}/masterAdmin/ajaxCheckTrustMemberEmail.do",
	{
		minChars : 3,
		min : "no"
	});
	$(document).ready(function() {
		$.subscribe('organizationTrustMembers', function(event, data) {
					 $('p.word-taken').each(function() {
					  if($(this).html()=='Already taken!!!'){
					     event.originalEvent.options.submit=false;
					   }
					 });
					 $('button.close').click();
		});
	});
</script>
