<%@ include file="/common/taglibs.jsp"%>
	<s:if test="%{routeList != null && !routeList.isEmpty()}">
		<div class="form-group">
			<label class="control-label col-md-2">
				<span class="required">*</span>Select Route :
			</label>
			<div class="col-md-3">
				<s:select id="routeId" list="routeList" 
					listKey="id" listValue="routeName" headerKey=""
					headerValue="- Select -"
					name="studentVo.routeBoardingPointsVO.routeVo.id" theme="simple"
					cssClass="required form-control input-medium"
					onchange="javascript:getBoardingPointByRoute();" />
			</div>
		</div>
		<div id="routeBoardingPoints">
			<jsp:include
				page="/WEB-INF/pages/admin/student/ajaxBoardingPointsByRoute.jsp" />
		</div>
		<div id="vehicleBoardingPoints">
			<jsp:include
				page="/WEB-INF/pages/admin/student/ajaxVehiclesByBoardingPointId.jsp" />
		</div>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			Currently there are no routes.
		</div>
	</s:else>
<script type="text/javascript">
	$(document).ready(function() {
		getBoardingPointByRoute();
	});
	function getBoardingPointByRoute() {
		var routeId = $("select#routeId").val();
		var studentId = $("#studentName").val();
		var boardingPointId= $('span.studBoardingPoint').attr('id');
		var url = jQuery.url.getChatURL("/admin/ajaxGetBoardingPointsByRoute.do");
		if(isNonEmpty(routeId) && routeId > 0){
			$("#routeBoardingPoints").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			var pars = "student.categoryId=" + routeId+"&anyId="+boardingPointId+ "&student.id=" + studentId;
		 	$.ajax( {
				url : url,
				cache : false,
				data : pars,
				success : function(html) {
					$("#routeBoardingPoints").html(html);
				}
			});
		}else{
			$('#routeBoardingPoints').html('<div class="alert alert-info col-md-12">Please select Route.</div>');
		} 
		$("#routeBoardingPoints").show();
		$("#vehicleBoardingPoints").hide();
	}
	
</script>