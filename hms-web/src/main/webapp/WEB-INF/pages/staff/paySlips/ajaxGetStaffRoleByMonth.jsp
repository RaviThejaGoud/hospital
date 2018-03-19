<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<div class="form-body">
<s:if test="%{tempList != null && !tempList.isEmpty()}">
	<div class="form-group">
		<label class="control-label col-md-2">
			Select Role :
		</label>
		<div class="col-md-3">
		<s:select id="roleIds" list="tempList" listKey="roleId" headerKey="A" headerValue="All"
			listValue="roleDescription" 
			name="anyId" theme="simple" cssClass="form-control required" />
		</div>
		</div>
	<div class="form-group">
		<div class="col-md-offset-2 col-md-5">
			<input type="button" class="submitBt btn blue" value="Submit"/>
		</div>
	</div>
</s:if>
<s:else>
	<div class="alert alert-info">
		Current there is no data.
	</div>
</s:else>
<div id="staffInfoContentDiv">
</div>
</div>
<script type="text/javascript">
$('input.blue').click(function(){
	var monthId ='<s:property value="tempId1"/>';
	var yearName ='<s:property value="tempId2"/>';
	var roleIdss=$("select#roleIds").val();
	if(isNonEmpty(yearName) && monthId >0 && isNonEmpty(roleIdss)){
   		var pars="tempId1=" + monthId +"&tempId2="+yearName+"&tempString="+roleIdss;
   			var url = jQuery.url.getChatURL("/staff/ajaxGetRoleByMonthId.do");
   			$('#staffInfoContentDiv')
   					.html('<div align="center" style="margin: 150px;"><img src="../assets/layouts/layout2/img/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
   			$.ajax({
   				url : url,
   				cache : false,
   				data : pars,
   				success : function(html) {
   					$("#staffInfoContentDiv").html(html);
   				}
   			});
   	}else{
   		$("#staffInfoContentDiv").hide();
   	}
});
</script>