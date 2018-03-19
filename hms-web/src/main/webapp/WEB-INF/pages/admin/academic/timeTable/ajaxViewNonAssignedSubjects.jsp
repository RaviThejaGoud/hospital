<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-6">
		<h4 class="pageTitle bold">
			Non Assigned Class Subjects
		</h4>
	</div>
</div>
<div class="spaceDiv"></div>
<s:if test="%{objectList != null && !objectList.isEmpty()}">
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th>
					Class Name
				</th>
				<th>
					Subject Name
				</th>
				<th>
					Periods Per Week
				</th>
				<th>
					Assigned Periods
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="objectList" status="stat">
				<tr>
					<td>
						<s:property value="objectList[#stat.count-1][0]" />
					</td>
					<td>
						<s:property value="objectList[#stat.count-1][1]" />
					</td>
					<td>
						<s:property value="objectList[#stat.count-1][2]" />
					</td>
					<td>
						<s:property value="objectList[#stat.count-1][3]" />
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		No alloted subjects available.
	</div>
</s:else>
<script type="text/javascript">
$(document).ready(function() {
	TableAdvanced.init();
});
</script>
