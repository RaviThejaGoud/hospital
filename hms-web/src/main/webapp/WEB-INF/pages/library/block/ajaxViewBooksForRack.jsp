<%@ include file="/common/taglibs.jsp"%>
<div id="racksWithSubjectDiv">
<div data-width="760" class="modal fade modal-overflow in"
	style="display: block; width: 760px; margin-left: -379px; margin-top: 100px;"
	aria-hidden="false">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="modal-title">
			Manage Books
		</h4>
	</div>
	<div class="modal-body">
	<jsp:include page="/common/messages.jsp"></jsp:include>
		<div class="form-body" id="racksWithSubjectDiv">
			<s:if test="%{objectList != null && !objectList.isEmpty()}">
				<table class="table table-striped table-hover table-bordered dataTable" id="sample_editable_1">
					<thead>
						<tr>
							<th>
								Rack Name
							</th>
							<th>
								Subject Name
							</th>
							<th>
								Books Count
							</th>
							<th>
								Delete
							</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="objectList" status="stat">
							<tr>
								<td>
									<s:property value="objectList[#stat.count-1][1]" />
								</td>
								<td>
									<s:property value="objectList[#stat.count-1][2]" />
								</td>
								<td class="eachBooksCount">
									<s:property value="objectList[#stat.count-1][5]" />
								</td>
								<td>
									<s:if test='%{#session.previousYear == "N"}'>
										<s:url id="removeLibrarySubjects" action="ajaxRemoveLibrarySubject" includeParams="all"
											escapeAmp="false" namespace="/library">
											<s:param name="selectedId" value="%{objectList[#stat.count-1][6]}"></s:param>
											<s:param name="anyId" value="%{objectList[#stat.count-1][5]}"></s:param>
											<s:param name="tempId1" value="%{objectList[#stat.count-1][4]}"></s:param>
										</s:url>
										<s:div  cssClass="btn btn-xs red"
											onclick="javascript:confirmDialogWithTarget(this,'racksWithSubjectDiv');"
											id='%{removeLibrarySubjects}' title="Delete this rack"><i class="fa fa-times"></i>Delete
										</s:div>
									</s:if>
								</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</s:if>
			<s:else>
			<div class="alert alert-info">Currently there are no books for this rack.</div>
			</s:else>
		</div>
	</div>
</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	TableEditable.init();
	var booksCount=0;
	$('td.eachBooksCount').each(function(){
		booksCount+=Number($(this).html());
	});
	//$('td#availableBooksCount').html(booksCount);
})
</script>