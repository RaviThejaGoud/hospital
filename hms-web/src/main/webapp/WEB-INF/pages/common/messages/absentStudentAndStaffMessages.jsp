<%@ include file="/common/taglibs.jsp"%>
  <s:if
	test="%{customer.checkMobileService == false}">
	<div class="grid_13 noteFont">
		<span class="label label-danger"> ALERT ! </span>&nbsp;
		Your SMS service is disabled 
		<s:if test='%{user.isSchoolAdmin == "Y"}'>
			 <s:url	id="urlManageSchool" action="ajaxDoSchoolInformation"
				namespace="/admin" />
			 <sj:a href="%{urlManageSchool}" cssClass="enableEmailService"
				targets="mainContentDiv">Click Here</sj:a>to enable services. 
		</s:if>
	</div>
</s:if>
<s:else> 
	<s:form action="ajaxSendSchoolWideMessages" theme="simple" method="post"
	id="formForAbsenteesMessages" cssClass="form-horizontal" enctype="multipart/form-data" namespace="/common">
	<input type="hidden" name="chkBoxSelectedAccountIds" id="selectAllIds" />
	<s:if test='%{tempString == "stepTRMessage"}'>
		<input type="hidden" value="AM" name="status" />
	</s:if>
	<input type="hidden" value="AM" name="trStatus" />
	<div id="toDivId" style="display: none;">
		<div class="form-group">
			<label class="control-label col-md-2">
				To :
			</label>
			<div class="col-md-3">
				<s:select name="messages.receiverType"
				cssClass="form-control required input-medium"
				id="msgReceiverTypeId"
				list="#{'':'-Select-','P':'Students','S':'Staff'}"
				onchange="javascript:enableSalunationTextBox(this.value);"></s:select>
			</div>
		</div>
	</div>	
	<div id="messageSalutationId" style="display: none;">
		<div class="form-group">
			<label class="control-label col-md-2">
				<span class="required">*</span>Message Salutation :
			</label>
			<div class="col-md-3">
				<div id="staffSalutationText">
					<sj:textfield name="" value="Dear Staff" id="messageDescription"
						rows="3" readonly="true"
						cssClass="form-control input-medium required"></sj:textfield>
				</div>
				<div id="parentSalutationText" style="display: none;">
					<sj:textfield name="" value="Dear Parent"
						id="messageDescription" readonly="true"
						cssClass="form-control required input-medium"></sj:textfield>
				</div>
			</div>
		</div>
	</div>
	<div id="absenteesList" style="display: none;"></div>
</s:form>
</s:else>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<script  type="text/javascript">
$(document).ready(function() {
	/* TableAdvanced.init();	
	UIExtendedModals.init(); */
	var type= '<s:property value="anyTitle"/>';
	if(type == 'P'){
	//	enableSalunationTextBox(type);
	}
});
function enableSalunationTextBox(val) {
	var pars = "";
	if(isNonEmpty(val)){
		$("div#absenteesList")
		.html(
				'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var url = jQuery.url.getChatURL("/common/ajaxGetAbsenteesListByType.do?anyTitle="+val);
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("div#absenteesList").html(html);
				$("div#absenteesList").show();
			}
		});
	}
	if ('P' == val) {
		$('#messageSalutationId').show();
		$('#parentSalutationText').show();
		$('#staffSalutationText').hide();

	} else if ('S' == val) {
		$('#messageSalutationId').show();
		$('#parentSalutationText').hide();
		$('#staffSalutationText').show();
		$('#absenteesDiv1').hide();
	}
	else{
		$('#messageSalutationId').hide();
		$('#parentSalutationText').hide();
		$('#staffSalutationText').hide();
		$('#absenteesList').hide();
	}
}
</script>
