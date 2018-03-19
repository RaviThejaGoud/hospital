<%@ include file="/common/taglibs.jsp"%>
<div data-width="760" class="modal fade modal-overflow in"
	style="display: block; width: 760px; margin-left: -379px; margin-top: 200px;"
	aria-hidden="false">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="modal-title">
			Staff Description
		</h4>
	</div>
	<div class="modal-body">
		<div id="newLeader">
			<s:if test="%{studyClassList != null && !studyClassList.isEmpty()}">
				<table
					class="table table-striped table-bordered table-hover table-full-width"
					id="sample_editable_3">
					<tbody>
						<s:iterator value="studyClassList">
							<tr>
								<td>
									<s:property value="className" />
								</td>
								<td>
									<s:property value="description" />
								</td>
								<td>
									<form id="rate<s:property value="id" />" action=""
										method="post">
										<c:forEach var="i" begin="1" end="5">
											<c:choose>
												<c:when test="${i <= version}">
													<input type="radio" name="rate"
														value='<c:out value="${i}" />' title="" checked='checked' />
												</c:when>
												<c:otherwise>
													<input type="radio" name="rate"
														value='<c:out value="${i}" />' title="" />
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</form>
									<script type="text/javascript">
									if($("#rate"+'<s:property value='id' />'))
									{
									$("#rate"+'<s:property value='id' />').stars({
										starWidth: 18, // only needed in "split" mode
										//split: 2,
										oneVoteOnly: true,
										callback: function(ui, type, value)
										{
											// Hide Stars while AJAX connection is active
											//var kBankId=$("#rat .knowledgeBankId"+'<s:property value='id' />').html();
											//var response = receiveRequest.evalJSON(true);
											ui.select(version);
											$("#loader"+'<s:property value='id' />').hide();
											$("#rate"+'<s:property value='id' />').show();
										}
									});
								}
							</script>
								</td>
							</tr>
							<!--<div id="displayTeacherClassRating<s:property value='id' />"
				style="display: none;" class="load"></div>
						-->
						</s:iterator>
					</tbody>
				</table>
			</s:if>
			<s:else>
				<div class="alert alert-info">
					Any class not alloted to this teacher.
				</div>
			</s:else>
		</div>
	</div>
</div>
<script type="text/javascript"> 
$(document).ready(function() {
TableEditable.init();
});
</script>
