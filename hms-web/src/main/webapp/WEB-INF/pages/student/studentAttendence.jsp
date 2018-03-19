<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/FusionCharts.js"></script>
<div id="commonTabContent" class="grid_11">
	<div id="commonTabWrapper">
		<div id="commonStep">
			<fieldset>
				<div class="grid_11 left">
					<div id="chartAttendancediv">
					</div>
				</div>
			</fieldset>
		</div>
	</div>
</div>
<script LANGUAGE="JavaScript">
         $(document).ready(function() {
         	 var str="";
         		var url = jQuery.url.getChatURL("/student/ajaxGetViewStudentAttendance.do");
				$.ajax( {
					url : url,
					cache : false,
					data : 'json',
					success : function(response) {
							var str=response.anyId;
							if(str!=null){
							var chartAttendance = new FusionCharts("${pageContext.request.contextPath}/FusionCharts/FCF_Column3D.swf", "chartAttendanceId", "280", "185", "0", "0"); 
					        chartAttendance.setDataXML(str);
				            chartAttendance.render("chartAttendancediv");
			               }else{
			               $("#viewMyPerformance").hide();
			               var chartAttendance = new FusionCharts("${pageContext.request.contextPath}/FusionCharts/FCF_Column3D.swf", "chartAttendanceId", "280", "210", "0", "0"); 
					         chartAttendance.setDataXML("<graph></graph>");
				             chartAttendance.render("chartAttendancediv");
			               }
			          }
         });
         });
 </script>