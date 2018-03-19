<%@ include file="/common/taglibs.jsp"%>
<s:form action="ajaxSendSchoolWideMessages" theme="simple"
	cssClass="form-horizontal" id="addTransportWideMessage" method="post"
	name="addTransportWideMessages">
	<s:if test='%{tempString == "stepTRMessage"}'>
		<input type="hidden" value="TR" name="status" />
	</s:if>
	<input type="hidden" value="TR" name="trStatus" />

	<!--<h4 class="pageTitle bold">
				Create Transport Message
			</h4>
			<div class="row">
					<div class="form-group">
						<label class="col-md-2 control-label">
							Select Type :
						</label>
						<div class="col-md-9">
						   <div class="radio-list">
							<s:if test='%{plTitle != "TRMessages"}'>
								<s:if
									test='%{user.isOnlySchoolTeacher!="Y" && user.isSchoolTransport=="N"}'>
									<label class="radio-inline">
										<input type="radio" value="SchoolWideMessages" id="schoolWideId"
											onclick="changeQualification(this.value);"
											name="checkMessages" class="radio">
										School Wide Messages
									</label>
								</s:if>
								<s:if test='%{user.isSchoolTransport=="N"}'>
									<label class="radio-inline">
										<input type="radio" value="ClassWideMessages" id="classWideId"
											onclick="changeQualification(this.value);"
											name="checkMessages" class="radio">
										Class Wide Messages
									</label>
								</s:if>
							</s:if>
							<s:if test='%{user.isSchoolTransport=="Y"}'>
								<label class="radio-inline">
									<input type="radio" value="ClassWideMessages" id="classWideId"
										onclick="changeQualification(this.value);"
										name="checkMessages" class="radio">
									Class Wide Messages
								</label>
							</s:if>
							<s:if test="%{customer.transportModuleStatus == true}">
								<label class="radio-inline">
									<input type="radio" value="TransportMessages"
										id="transportWideId" onclick="changeQualification(this.value);"
										name="checkMessages" class="radio">
									Transport Messages
								</label>
							</s:if>
							<s:else>
								<label class="radio-inline">
									<input type="radio" value="TransportMessages" checked="checked"
										id="transportWideId" onclick="changeQualification(this.value);"
										name="checkMessages" class="radio">
									Transport Messages
								</label>
							</s:else>
						</div>
						</div>
					</div>
				</div>
				-->
	<s:if test="%{customer.checkMobileService == true}">
		<div class="form-group">
			<label class="control-label col-md-2">
				<span class="required">*</span> Select Transport Type :
			</label>
			<div class="col-md-2">
				<s:select name="transportType" id="tansportTypeId"
					cssClass="form-control input-medium required"
					list="#{'':' --- Select ---','Route':'Route Wide','Vehicle':'Vehicle Wide'}"
					onchange="javascript:transportListByType(this.value);"></s:select>
			</div>
		</div>
		<div id="transportList">
		</div>
		<div id="vehicletransportList">
		</div>
	</s:if>
	<s:else>
		<span class="label label-danger"> ALERT ! </span>&nbsp;
				 Your SMS service is disabled  
			<s:url id="urlManageSchool" action="ajaxDoSchoolInformation"
			namespace="/admin" />
		<sj:a href="%{urlManageSchool}" targets="mainContentDiv"
			cssClass="enableEmailService"> Click Here</sj:a>to enable services.
	</s:else>
</s:form>
<script type="text/javascript">
$('a.enableEmailService').click(function(){
	window.location.hash="target=ES.ajaxify AMCS";
	window.location.reload();
});
</script>
		