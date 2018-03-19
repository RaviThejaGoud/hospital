<%@ include file="/common/taglibs.jsp"%>
<br/>
<s:if test="%{customer.checkMobileService == true || customer.checkEmailService == true}">
	<div class="form-group">
		<label class="col-md-2 control-label">
			<span class="required">*</span>Alert Type :
		</label>
		<div class="col-md-9">
			<div class="checkbox-list">
				<s:if test="%{customer.checkMobileService == true}">
					<s:if test='%{user.isSchoolAsstStaff =="Y"}'>
						<div id="MobileEmail">
							<label class="checkbox-inline col-md-2">
								 <input type="checkbox" name="chkBoxSelectedIds"
											id="chkBoxMobileIds chkBoxMobileIds" value="M"
											 class="mbc checkByValue" disabled="true"
											onchange="javascript:checkMessageType('M');"> 
								Mobile
							</label>
						 </div>
					 </s:if>
					<s:else>
					<div id="MobileEmail">
						<s:if test="%{(smsAlloted == 0) || (smsAlloted <= smsCnt)}">
							<label class="checkbox-inline col-md-2">
								 <input type="checkbox" name="chkBoxSelectedIds"
									id="chkBoxMobileIds" value="M"
									 class="mbc checkByValue chkBoxMobileIds" disabled="true"
									onchange="javascript:checkMessageType('M');"> 
								Mobile 
							</label>
						 </s:if>
						 <s:else>
						 	<label class="checkbox-inline col-md-2">
								 <input type="checkbox" name="chkBoxSelectedIds"
										id="chkBoxMobileIds"   value="M" checked="checked"
										 class="mbc checkByValue chkBoxMobileIds"
										onchange="javascript:checkMessageType('M');"> 
								Mobile
							</label>
						 </s:else>
					 </div>
				</s:else>
				</s:if>
				<s:if test="%{customer.checkEmailService == true}">
					<s:if test='%{user.isSchoolAdmin == "Y" || #session.isClassTeacher || user.isOnlySchoolHod=="Y" || user.isSchoolAsstStaff=="Y" || user.isChairMan =="Y" || user.isSchoolDirector == "Y" || user.isReceptionist == "Y"}'>
						<div  id="MobileEmail">
							<label class="checkbox-inline col-md-2">
								 <input type="checkbox" name="chkBoxSelectedIds" id="chkBoxClassIds"
										    value="E"  checked="checked"  
											class="mec checkByValue"  onchange="javascript:checkMessageType('E');" />  
								
								E-Mail
							</label>
						</div>
					</s:if>
				</s:if>
			</div>
		</div>
	</div>
	<div class="col-md-2"></div><p id="chkMobileEmailMsg" style="margin-left: 130px;color: red;"></p>
</s:if>
<div class="spaceDiv"></div>
<s:if test='%{user.isSchoolAdmin == "Y"}'>
	<s:if
		test="%{customer.checkMobileService == false && customer.checkEmailService == false}">
		<p>
			<span class="label label-danger"> ALERT : </span>&nbsp;Email or SMS
			 services disabled, enable services.  
				<s:url id="urlManageSchool" action="ajaxDoSchoolInformation" namespace="/admin" />
				<sj:a  href="%{urlManageSchool}" targets="mainContentDiv" cssClass="enableEmailService">
					Enable Service</sj:a> 
		</p>
	</s:if>
	<s:elseif test="%{customer.checkMobileService == false}">
		<p>
			<span class="label label-danger"> ALERT : </span>&nbsp;
			SMS service disabled, enable service.
				<s:url id="urlManageSchool" action="ajaxDoSchoolInformation" namespace="/admin" />
				<sj:a  href="%{urlManageSchool}" targets="mainContentDiv" cssClass="enableEmailService">
					Enable Service</sj:a> 
		</p>
	</s:elseif>
	<s:elseif test="%{customer.checkEmailService == false}">
		<p>
			<span class="label label-danger"> ALERT : </span>&nbsp;
		Email service disabled, enable service.
			<s:url id="urlManageSchool" action="ajaxDoSchoolInformation" namespace="/admin" />
				<sj:a  href="%{urlManageSchool}" targets="mainContentDiv">
					Enable Service</sj:a>   
		</p>
	</s:elseif>
</s:if>
<script type="text/javascript">
 $("input:checkbox, input:radio").uniform();
 $('a.enableEmailService').click(function(){
	window.location.hash="target=ES.ajaxify AMCS";
	window.location.reload();
});
</script>