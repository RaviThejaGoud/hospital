<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<jsp:include page="/common/messages.jsp"></jsp:include>
		<s:if test="%{tempList != null && !tempList.isEmpty()}">
			<table
				class="table table-striped table-bordered table-hover table-full-width"
				id="sample_2">
				<thead>
					<tr>
						<th>
							Activity Type
						</th>
						<th>
							Activity Name
						</th>
						<th>
							Description
						</th>
						<th>
							Documents
						</th>
						<!--<th>
							Edit
						</th>
						<th>
							Delete
						</th>
					--></tr>
				</thead>
				<tbody>
					<s:iterator value="tempList">
						<tr>
							<td>
								<s:property value="activityTypeName" />
							</td>
							<td>
								<s:property value="activityName" />
							</td>
							<td>
								<s:property value="description" />
							</td>
							<td>
								<s:if test='%{isDocsUploaded == "Y"}'>
									<a
										href="${pageContext.request.contextPath}/student/ajaxDownloadStudentCurricularDocs.do?tempId=<s:property value="tempId" />&tempId1=<s:property value="id" />"
										class="btn btn-xs purple" title="Download All"><i
										class="fa fa-edit"></i><span>Download All</span> </a>
								</s:if>
								<s:else>
										--
								</s:else>
							</td>
							

						</tr>
					</s:iterator>
				</tbody>
			</table>
		</s:if>
		<s:else>
			<div class="alert alert-info">
				No Curricular / Co-Curricular Activities found.
			</div>
		</s:else>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	TableAdvanced.init();
});
</script>
