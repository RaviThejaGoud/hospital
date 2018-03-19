<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<div class="block grid_12" id="staffList">
<jsp:include page="/common/messages.jsp"></jsp:include>
	<div>
		<div class="block_content">
			<div class="grid_11">
				
				<div class="grid_10">
					<div class="grid_3">
						<label style="text-align: left;">
							<b>School Feedback:-</b>
						</label>
					</div>

				</div>
				
				<div class="grid_10">
					<div class="grid_3">
						<label style="text-align: left;">
							<b>Staff Performance:-</b>
						</label>
					</div>

				</div>
				
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
  $(document).ready(function() {
  	$('div#viewClassresults').click(function() {
			var data=$(this).attr('class');
			if ($('#' +data).is(":hidden")) {
				  $('#' +data).show();	
			}else{
				  $('#' +data).hide();
			}
		});
  
   	$('div#viewClassSchedules').click(function() {
			var data=$(this).attr('class');
			if ($('#' +data).is(":hidden")) {
				  $('#' +data).show();	
			}else{
				  $('#' +data).hide();
			}
		});
  $.subscribe('doInitTeacherClassRating', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		 }
	});
});
 </script>