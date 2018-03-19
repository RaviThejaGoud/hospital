<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/FusionCharts.js">
</script>
<div class="block_content">
	<s:if test="%{objectList != null && !objectList.isEmpty()}">
		<s:if test="%{objectList.size>1}">
			<div class="grid_6" style="text-align: left;">
				<b> Child Name: </b>
				<br />
				<s:select id="sectionId" list="objectList" listKey="id"
					onchange="compareSubjectswithPreviousResults(this.value);"
					listValue="idAndName" label="Child Name"
					cssClass="textfield required" theme="simple" required="true"
					name="anyId" />
			</div>
			<div>
				&nbsp;
			</div>
			<br />
			<br />
			<div id="chart1div"></div>
			<div id="myStudentsMarks"></div>
		</s:if>
		<s:else>
			<div style="chart1div"><div id="myStudentsMarks"></div>
			</div>
		</s:else>
	</s:if>
	<div id="chart1div"></div>
	<div id="myStudentsMarks"></div>
</div>
<script LANGUAGE="JavaScript">
function compareSubjectswithPreviousResults(studAccountId) {
	var studentAccountId = studAccountId;
	var str = "";
	var url = jQuery.url.getChatURL("/student/ajaxGetStudentMarks.do?accountId="+ studentAccountId);
	$.ajax( {
				url : url,
				cache : false,
				data : 'json',
				success : function(response) {
					var str = response.wishDescription;
					if (str != null) {
						var chart1 = new FusionCharts("${pageContext.request.contextPath}/FusionCharts/FCF_MSColumn3D.swf","chart1Id", "650", "450", "0", "0");
						chart1.setDataXML(str);
						chart1.render("chart1div");
					}
					else{
			                $("#chart1").text('');
                             $("#myStudentsMarks").text('There are no up loaded marks.');
                           }
				}
			});
}
 $(document).ready(function() {
         	 var strCode="";
         		var url = jQuery.url.getChatURL("/student/ajaxGetStudentMarks.do");
				$.ajax( {
				url : url,
				cache : false,
				data : 'json',
				success : function(response) {
					var str = response.wishDescription;
					if (str != null) {
						var chart1 = new FusionCharts("${pageContext.request.contextPath}/FusionCharts/FCF_MSColumn3D.swf","chart1Id", "650", "450", "0", "0");
						chart1.setDataXML(str);
						chart1.render("chart1div");
					}
					else{
			                $("#chart1Id").text('');
                             $("#myStudentsMarks").text('There are no up loaded marks.');
                           }
				}
			});
         });
</script>
