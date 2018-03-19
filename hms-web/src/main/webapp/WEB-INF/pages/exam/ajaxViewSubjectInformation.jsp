<%@ include file="/common/taglibs.jsp"%>
<h1 >Study Classes</h1>
	<div  class="studyClassesInfo">
		<s:if test="%{subjectsList != null && !subjectsList.isEmpty()}">
		<div class="grid_5 th" id='examScheduleData'>
			<div class="grid_2">
				Classes
			</div>
		</div>
		<div id="subjectClasseContent">
			<s:iterator value="%{subjectsList}"> 
			<div class="grid_5 row">
				<s:property value="classSection"/>
				</div> 
			</s:iterator>
		</div>
		</s:if>
		<s:else>
		<div class="grid_5 th thb">
			No Classes found for this subject.
		</div>
		</s:else>
	</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/subscription/paginator.js">
</script>
<script type="text/javascript">
$(document).ready(function() {
					$("#subjectClasseContent").pagination();
					});
	</script>