<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/FusionCharts.js"></script>
<div id="commonTabContent" class="grid_11">
	<div id="commonTabWrapper">
		<div id="commonStep">
			<fieldset>
				<div class="grid_11 left">
					<s:if test="%{objectList != null && !objectList.isEmpty()}">
						<s:if test="%{objectList.size>1}">
							<div class="grid_6" style="text-align: left;">
								<b> Child Name: </b>
								<br />
								<s:select id="sectionId" list="objectList" listKey="id"
									onchange="javascript:getMyChildView(this.value);"
									listValue="idAndName" label="Child Name"
									cssClass="textfield required" theme="simple" required="true"
									name="anyId" />
							</div>
						</s:if>
						<s:else>
							<div style="padding-top: 1px; margin-left: -23px;">
								<div id="myChildrensAttendancediv">
								</div>
							</div>
						</s:else>
					</s:if>
					<div id="myChildrensAttendancediv">
					</div>
				</div>
			</fieldset>
		</div>
	</div>
</div>

<script LANGUAGE="JavaScript">
    function getMyChildView(studAccountId){
        var studentAccountId=studAccountId;
        var str="";
       	var url = jQuery.url.getChatURL("/student/ajaxGetViewStudentAttendance.do?accountId="+studentAccountId);
		$.ajax( {
			url : url,
			cache : false,
			data : 'json',
			success : function(response) {
					var str=response.anyId;
					if(str!=null){
					var chartChildren1 = new FusionCharts("${pageContext.request.contextPath}/FusionCharts/FCF_Column3D.swf", "chartChildrenId", "280", "185", "0", "0"); 
			        chartChildren1.setDataXML(str);
		            chartChildren1.render("myChildrensAttendancediv");
	               }
	          }
       });
 }
         $(document).ready(function() {
         	 var strCode="";
         		var url = jQuery.url.getChatURL("/student/ajaxGetViewMyChildrensAttendance.do");
				$.ajax( {
					url : url,
					cache : false,
					data : 'json',
					success : function(response) {
							var strCode=response.anyId;
							if(strCode!=null){
							var myChildrenAttendance = new FusionCharts("${pageContext.request.contextPath}/FusionCharts/FCF_Column3D.swf", "myChildrensAttendanceId", "280", "185", "0", "0"); 
					        myChildrenAttendance.setDataXML(strCode);
				            myChildrenAttendance.render("myChildrensAttendancediv");
			               }
			          }
         });
         });
 </script>