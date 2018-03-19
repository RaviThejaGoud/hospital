<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<div class="grid_11">
<table class="striped" width="100%" style="margin-bottom: 0;"
	cellpadding="1" cellspacing="1">
	<thead>
		<tr style="text-align: left;">
			<th width="20%">
				No
			</th>
			<th width="20%">
				Current ClassName
			</th>
			<th width="20%">
				Promote ClassName
			</th>
			<!--<th style="width: 80px; text-align: left;">
				Edit
			</th>
		--></tr>
	</thead>
	<s:if test="%{promoteClassList != null && !promoteClassList.isEmpty()}">
		<%int i = 0; %>
		<s:iterator value="promoteClassList">
			<tr class='loaded'>
				<td >
					<%i++; %><%=i %>
				</td>
				<td >
					<s:property value="currentClassName" />
				</td>
				<td >
					<s:property value="promoteClassName" />
				</td>

				<!--<td >
					
				</td>
			--></tr>
		</s:iterator>
	</s:if>
	<s:else>
			  	Currently there are no promote class
		   </s:else>

</table>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$('div#viewClassresults').click(function() {
		var data = $(this).attr('class');
		if ($('#' + data).is(":hidden")) {
			$('#' + data).show();
		} else {
			$('#' + data).hide();
		}
	});

	$('div#viewClassSchedules').click(function() {
		var data = $(this).attr('class');
		if ($('#' + data).is(":hidden")) {
			$('#' + data).show();
		} else {
			$('#' + data).hide();
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