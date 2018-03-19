<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Pay Slips
				</div>
			</div>
			<div class="portlet-body">
				<div class="form-horizontal">
				<s:if test="%{objectList != null && !objectList.isEmpty()}">
					<div class="form-group">
						<label class="control-label col-md-2">
							Select Month : 
						</label>
						<div class="col-md-3">
						<s:select id="yearName" list="objectList" listKey="accountIdAndClassSectionId" headerKey=" " 
							listValue="monthName" onchange="getSchoolMonthDetails(this.value);"
							name="tempId1" theme="simple" cssClass="form-control required monthId" />
						</div>
					</div>
					<div id="roleContentDiv">
					</div>
				</s:if>
				<s:else>
					<div class="alert alert-info">
						Currently there are no payslips generated.
					</div>
				</s:else>
				</div>
			</div>
		</div>
	</div>
</div>		
<script type="text/javascript">	
$(document).ready(function() {
	var monthIdAndYearName = $("#yearName option:first").val();
	if(isNonEmpty(monthIdAndYearName)){
		getSchoolMonthDetails(monthIdAndYearName);
	}
});
function getSchoolMonthDetails(monthIdAndYearName)
{
	if (monthIdAndYearName == "" || monthIdAndYearName == 0) {
		$("#roleContentDiv").hide();
	} else {
		var yearName='';
	    var selectedVal='';
	    if(isNonEmpty(monthIdAndYearName)){
	       selectedVal=monthIdAndYearName.split("_");
	       monthId=selectedVal[0];
	       yearName=selectedVal[1];
		   	if(isNonEmpty(yearName) && monthId >0){
		   		var pars="tempId1=" + monthId +"&tempId2="+yearName;
		   			var url = jQuery.url.getChatURL("/staff/ajaxGetRoleByMonthId.do");
		   			$('#roleContentDiv')
		   					.html('<div align="center" style="margin: 150px;"><img src="../assets/layouts/layout2/img/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		   			$.ajax({
		   				url : url,
		   				cache : false,
		   				data : pars,
		   				success : function(html) {
		   					$("#roleContentDiv").html(html);
		   				}
		   			});
		   	}
	    }
	}
}
</script>