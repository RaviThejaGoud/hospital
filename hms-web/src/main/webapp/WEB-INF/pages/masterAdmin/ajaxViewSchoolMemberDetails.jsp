<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{tempList != null && !tempList.isEmpty()}">
<div class="space10"></div>
	<div class="form-group">
		<label class="control-label col-md-3">
			 <span class="required">*</span>Mobile No (or) Email :
		</label>
		<div class="col-md-3">
			<sj:textfield name="mobilenumber" cssClass=" required form-control" 
				 id="mobilenumberId" />
		</div>
	</div>
	<div class="space10"></div>
	<div class="space10"></div>
	<div class="form-group">
		<label class="control-label col-md-3"><span class="required"> * </span> Relationship : </label>
		<div class="col-md-3">
			<s:select id="tempLists" list="tempList" listKey="id" headerKey="" headerValue="- Select -"
				listValue="description" onchange="getSchoolRoleDetailsByRoleId(this.value);"
				name="tempId" theme="simple" cssClass="form-control required roleId"  ></s:select>
		</div>
	</div>
</s:if>
<s:else>
	<div class="alert alert-info">
		Current there is no roles for this customer.
	</div>
</s:else>
<div id="viewSchoolInfoDiv">
	<%-- <jsp:include page="/WEB-INF/pages/masterAdmin/viewSchoolPersonsDetails.jsp"/> --%>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$('.numeric').numeric();
	var roleId=$("select.roleId").val();
	var mobileNum = $("#mobilenumberId").val();
	var email = $("#emailId").val();
	if(isNonEmpty(roleId) && (isNonEmpty(mobileNum) || isNonEmpty(email))){
		//alert('sfsf');
		getSchoolRoleDetailsByRoleId(roleId);
	}else{
		//alert('53222');
		$("div.viewSchoolInfoDiv").hide();
	}
	
});
function getSchoolRoleDetailsByRoleId(roleId)
{
	var pars;
	var mobileNum = $("#mobilenumberId").val();
	//var email = $("#emailId").val();
	//alert('<s:property value="eventId" />');
	//alert(mobileNum);
	//alert(email);
	if(isNonEmpty(mobileNum)){
		 pars="tempId1=" + roleId+"&tempId="+'<s:property value="eventId" />'+"&plTitle="+mobileNum;
			var url = jQuery.url.getChatURL("/masterAdmin/ajaxGetSchoolRoleDetailsByRoleId.do");
			$('#viewSchoolInfoDiv')
					.html('<div align="center" style="margin: 150px;"><img src="../assets/layouts/layout2/img/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			$.ajax({
				url : url,
				cache : false,
				data : pars,
				success : function(html) {
					$("#viewSchoolInfoDiv").html(html);
				}
			});
	}else{
		$("#viewSchoolInfoDiv").hide();
		alert("Please enter Email or Mobile Number.");
	}
	
}

</script>