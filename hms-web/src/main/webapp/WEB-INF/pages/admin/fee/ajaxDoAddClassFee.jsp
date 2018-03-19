<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div class="grid_9">
	<div class="grid_2">
		<label>
			Select Class
		</label>
	</div>
	<div class="grid_5">
			<s:select list="classList" listKey="id" listValue="className"
				cssClass="required textfield" name="classId" headerKey="" headerValue="- Select -"
				  cssStyle="width:150px;" onchange="javascript:onClassChange(this.value);" theme="simple">
			</s:select>
		</div>
		<div class="grid_9">&nbsp;</div>
		<div class="grid_9" id="classFeeDetails"></div>
</div>
<script type="text/javascript">
	$.subscribe('doDisplayFee', function(event, data) { 
	
			if ($('#' + data.id).is(":hidden")) {
				$('#' + data.id).show();
			} else {
				$('#' + data.id).hide();
			}
		});
	function onClassChange(className) {
	    var id = className;
		var pars = "classId=" + id;
       $.ajax( {
	        url : "ajaxGetClassFeeDetails.do",
			cache : false,
			data: pars,
			
			success : function(response) {
			
			 $('#classFeeDetails').html(response);
			}
		});  
	}
</script>