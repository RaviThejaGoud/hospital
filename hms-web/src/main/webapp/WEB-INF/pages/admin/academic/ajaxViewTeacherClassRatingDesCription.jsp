<%@ include file="/common/taglibs.jsp"%>
<div data-width="760" class="modal fade modal-overflow in"
	style="display: block; width: 760px; margin-left: -379px; margin-top: 150px;"
	aria-hidden="false">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="modal-title">
			Feedback Description 
		</h4>
	</div>
	<div class="modal-body">
		<div id="newLeader" class="form-body">
			<s:if test="%{tempList != null && !tempList.isEmpty()}">
				<table
					class="table table-striped table-bordered table-hover table-full-width"
					id="sample_editable_3">
					<thead>
						<tr>
							<th>
								Feedback Question
							</th>
							<th>
								Description
							</th>
							<th>
								Rating View
							</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="tempList">
							<tr>
								<td>
									<s:property value="roleDescription" />
								</td>
								<td>
									<s:property value="description" />
								</td>
								<td>
									<span class="innerStarsVal"><s:property value="totalRatingVal"/><span style=""></span></span>
								</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</s:if>
			<s:else>
				<div class="alert alert-info">
					Class student(s) are not provided the rating to this teacher.
				</div>
			</s:else>
		</div>
	</div>
</div>
<script>
$(document).ready(function() {
TableEditable.init();
	return $('span.innerStarsVal').each(function() {
	var text = $(this).text();
		$(this).html($('<span />').width(Math.max(0, (Math.min(5, parseFloat(text)))) * 16));
	});
});
</script>