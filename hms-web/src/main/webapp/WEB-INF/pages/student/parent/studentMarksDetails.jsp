<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/FusionCharts.js"></script>
<div id="commonTabContent" class="grid_11">
	<div id="commonTabWrapper">
		<div id="commonStep">
			<fieldset>
				<div class="grid_11 left">
					<s:if test="%{objectList != null && !objectList.isEmpty()}">
						<s:if test="%{objectList.size>1}">
							<div class="grid_4" style="text-align: left; margin-top: -15px;">
								<b> Child Name: </b>
								<br />
								<s:select id="sectionId" list="objectList" listKey="id"
									onchange="javascript:getMyChildViewMarks(this.value);"
									listValue="idAndName" label="Child Name"
									cssClass="textfield required" theme="simple" required="true"
									name="anyId" />
							</div>
						</s:if>
						<s:else>
							<div style="padding-top: 1px; margin-left: 5px;">
								<div id="chart1div"></div>
								<div id="myStudentsMarks"></div>
							</div>
						</s:else>
					</s:if>
					<div style="padding-top: 1px; margin-left: 5px;">
						<div id="chart1div"></div>
						<div id="myStudentsMarks"></div>
						<!--<div  style="padding-left: 20px;" id="viewPerformance">
	       						 <a href="viewMyPerformance.do">More Details</a>
	   						  </div>	
	     					-->
					</div>
				</div>
			</fieldset>
		</div>
	</div>
</div>
<script LANGUAGE="JavaScript">
        function getMyChildViewMarks(studAccountId){
         var studentAccountId=studAccountId;
         	 var str="";
         		var url = jQuery.url.getChatURL("/student/ajaxGetLatestMyChildMarks.do?accountId="+studentAccountId);
				$.ajax( {
					url : url,
					cache : false,
					data : 'json',
					success : function(response) {
							var str=response.wishDescription;
							if(str!=null){
							var chart1 = new FusionCharts("${pageContext.request.contextPath}/FusionCharts/FCF_Column3D.swf", "chart1Id", "280", "185", "0", "0"); 
					        chart1.setDataXML(str);
				            chart1.render("chart1div");
			               }
			               else{
			                 $("#chart1div").text('');
                             $("#myStudentsMarks").text('There are no up loaded marks.');
                           }
	          }
       });
 }
  $(document).ready(function() {
         	 var strCode="";
         		var url = jQuery.url.getChatURL("/student/ajaxGetLatestMyChildMarks.do");
				$.ajax( {
					url : url,
					cache : false,
					data : 'json',
					success : function(response) {
							var strCode=response.wishDescription;
							if(strCode!=null){
							var chart1div = new FusionCharts("${pageContext.request.contextPath}/FusionCharts/FCF_Column3D.swf", "chart1Id", "280", "185", "0", "0"); 
					        chart1div.setDataXML(strCode);
				            chart1div.render("chart1div");
			               }
			               else{
			                $("#chart1Id").text('');
                             $("#myStudentsMarks").text('There are no up loaded marks.');
                           }
			          }
         });
         });
      </script>