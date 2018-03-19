<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<div class="row">
	<div class="col-md-12">
		<s:form id="paySlipId" action="ajaxSendEmailsPaySlips" theme="simple" method="post"	namespace="/staff" onsubmit="return getAjaxPaySlipsSendEmailToStaff();" cssClass="form-horizontal">
		<s:hidden name="tempId1" value="%{tempId1}"></s:hidden>
		<s:hidden name="tempId2" value="%{tempId2}"></s:hidden>
		<s:hidden name="plTitle"  cssClass="roleIds" value=""/>
		<s:hidden name="anyId" cssClass="anyId" value=""></s:hidden>
		<s:hidden name="tempString" value="%{tempString}"></s:hidden>
		<s:if test="%{tempList1 != null && !tempList1.isEmpty()}">
			<table	class="table table-striped table-bordered table-hover table-full-width"	id="sample_2">
			<thead>
					<tr>
						<th>Employee Name</th>
						<th>Role</th>
						<th>Mobile Number</th>
						<th>Email Address</th>
						<th>
							<div class="checkbox">
								 Select All
								<label>
									<input type="checkbox" name="selectAll" value=''
										class="allClasses" id="selectAllIds" onclick="checkAll();" />
								</label>
							</div>
				     </th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="tempList1">
						<tr>
							<td><s:property value="fullName" /></td>
							<td><s:property value="roleDescription" /></td>
							<td>
								<s:if test="%{mobileNumber != null && !mobileNumber.isEmpty()}">
								<s:property value="mobileNumber" />
								</s:if>
								<s:else>
								    -
								</s:else>
							</td>
							<td>
								<s:if test="%{email != null && !email.isEmpty()}">
									<s:property value="email" />
								</s:if>
								<s:else>
								    -
								</s:else>
							<td>
								<div class="checkbox">
									<label>
										<input type="checkbox" name="chkBoxSelectedAccountIds"
											value='<s:property value="accountId" />' id="checkbox">
									</label>
								</div>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
			<div class="col-md-8" style="float: right;">
			<s:submit type="submit small" value="Download" id="download" onclick="reportFormate(this.value)"
				cssClass="submitBt btn blue" >
			</s:submit>
			<s:if test="%{customer.checkEmailService}">
				<s:submit type="submit" value="Send Email" id="emailIds" onclick="reportType(this.value)"
					cssClass="submitBt btn blue">
				</s:submit>
			</s:if>
			</div>
		</s:if>
		<s:else>
			<div class="alert alert-info">Currently there are no data.</div>
		</s:else>
		</s:form>
	</div>
</div>
<script type="text/javascript">
changePageTitle("Send Class Wide Message");
$(document).ready(function() {
	$.destroyTopic('paySlipFormValidation'); 
	$("input:checkbox, input:radio").uniform();
});
function checkAll() {
	if ($(".allClasses").is(':checked')) {
		$("[name='chkBoxSelectedAccountIds']").each(function() {
			$(this).parent('span').addClass('checked');
			$(this).attr("checked", "true");
		});
	} else {
		$("[name='chkBoxSelectedAccountIds']").each(function() {
			$(this).parent('span').removeClass('checked');
			$(this).removeAttr("checked");
		});
	}
}

$("input[name=chkBoxSelectedAccountIds]").click(function() {
	if ($("input[name=chkBoxSelectedAccountIds]:unchecked").length > 0) {
		$(".allClasses").parent('span').removeClass("checked");
		$(".allClasses").attr("checked", false);
	} else {
		$(".allClasses").parent('span').addClass("checked");
		$(".allClasses").attr("checked", true);
	}
});
function reportFormate(value) {
	 getAjaxPaySlipsSendEmailToStaff(value);
}
function reportType(value) {
	 getAjaxPaySlipsSendEmailToStaff(value);
}

function getAjaxPaySlipsSendEmailToStaff(value) {
	if("Download"==value){
		if ($("input[name=chkBoxSelectedAccountIds]:checked").length > 0) {
	        var typeIds = $("input[name=chkBoxSelectedAccountIds]:checked");
	        var selectedAccountsIds = '';
	        if (typeIds.length > 0) {
	           for ( var i = 0; i < typeIds.length; i++) {
	        	   selectedAccountsIds += typeIds[i].value + ',';
	           }
	        } 
           $(".roleIds").val(selectedAccountsIds);
           $('.anyId').val('D');
			return true;
		} else if ($("input[name=chkBoxSelectedAccountIds]:checked").length == 0) {
	    	 alert("Please select at least one staff");
	    	 return false;
		} else {
			 return false;
		}
	}else if("Send Email"== value){
		if ($("input[name=chkBoxSelectedAccountIds]:checked").length > 0) {
	        var typeIds = $("input[name=chkBoxSelectedAccountIds]:checked");
	        var monthId='<s:property value="tempId1" />';
	        var yearName = '<s:property value="tempId2" />';
	        var tempString = '<s:property value="tempString" />';
	        var selectedAccountsIds = '';
	        var anyId='E';
	        if (typeIds.length > 0) {
	           for ( var i = 0; i < typeIds.length; i++) {
	        	   selectedAccountsIds += typeIds[i].value + ',';
	           }
	           $("#religionNameIds").val(selectedAccountsIds);
	           var roleIds = selectedAccountsIds;
	      	  if(isNonEmpty(yearName) && monthId >0 && isNonEmpty(roleIds)){
	      		var pars="tempId1=" + monthId +"&tempId2="+yearName+"&plTitle="+roleIds+"&anyId="+anyId+"&tempString="+tempString;
	      		var url = jQuery.url.getChatURL("/staff/ajaxSendEmailsPaySlips.do");
	      		 $("#staffInfoContentDiv").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	   				$.ajax( {
	   					url : url,
	   					cache : false,
	   					data : pars,
	   					success : function(html) {
	   					$("#staffInfoContentDiv").html(html);
	   						$("#staffInfoContentDiv").show();
	   					}
	   				});
	      		  }
	        }
	       }else if ($("input[name=chkBoxSelectedAccountIds]:checked").length == 0) {
	     	 alert("Please select at least one staff");
	       return false;
	      }
	}else if($("input[name=chkBoxSelectedAccountIds]:checked").length == 0){
		return false;
	}
	  
}
</script>