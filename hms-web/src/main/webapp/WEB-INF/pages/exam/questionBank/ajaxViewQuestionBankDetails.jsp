<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<s:if test="%{questionBankListDetails != null && !questionBankListDetails.isEmpty()}">
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th>
					Question Bank Name
				</th>
				<th>
				   	Lesson Name
				</th>
				<th>
					Description
				</th>
				<th>
					Download
				</th>
				<s:if test='%{user.isSchoolAdmin == "Y"}'>
					<th>
						Edit
					</th>
					<th>
						Delete
					</th>
				</s:if>	
			</tr>
		</thead>
		<tbody>
		<s:iterator value="questionBankListDetails">
			<tr>
				<td>
					<s:property value="materialName" />
				</td>
				<td>
					<s:property value="staffSyllabusPlannerVo.chapterName" />
				</td>
				<td>
					<s:property value="description" />
				</td>
				<td>
					<ul class="tooltipDiv">
							<li>
								<a href="#">Download</a>
								<ul class="tooltipSubDiv">
									<div class="popover bottom " style="display: none;">
										<div class="arrow"></div>
										<h3 class="popover-title">
											Download Question Banks
										</h3>
										<div class="popover-content">
											<s:if test="%{attachmentVOs != null && !attachmentVOs.isEmpty()}">
												<s:iterator value="attachmentVOs">
													<li>
													<span><a rel="nofollow"
														href='<c:url value='/exam/ajaxDownloadQuestionBankDocs.do'/>?classSectionId=<s:property value="classSectionId" />&subjectId=<s:property value="subjectId" />&lessonId=<s:property value="lessonId"/>&fileName=<s:property value="fileName" />&materialId=<s:property value="id" />'><s:property
																value="fileName" />
													</a>
													</span>
													</li>
													<li>
												</li>
												</s:iterator>
											</s:if>
											<s:else>
												<li>
													There is no uploaded  Question Banks.
												</li>
											</s:else>
										</div>
									</div>
								</ul>
							</li>
						</ul>	
				</td>
				<s:if test='%{user.isSchoolAdmin == "Y"}'>
					<td>
						<s:if test='%{#session.previousYear == "N"}'>
							<s:url id="doEditQuestionBank" action="ajaxDoAddQuestionBankMaterial"
								includeParams="all" escapeAmp="false">
									<s:param name="questionPaperBankVO.id" value="%{id}" />
									<s:param name ="classSectionId" value="%{classSectionId}"/>
									<s:param name ="subjectId" value="%{subjectId}"/>
								   <s:param name ="lessonId" value="%{staffSyllabusPlannerVo.id}" />
							</s:url>
							<sj:a href="%{doEditQuestionBank}" indicator="indicator"
								targets="classContentDiv" cssClass="btn btn-xs purple"
								title="Edit">
								<i class="fa fa-edit"></i>Edit
							</sj:a>
						</s:if>
					</td>
					<td>
						<s:if test='%{#session.previousYear == "N"}'>
							<s:url id="removeQuestionBank" action="ajaxRemoveQuestionBank"
								includeParams="all" escapeAmp="false" namespace="/exam">
								<s:param name="questionPaperBankVO.id" value="%{id}" />
							</s:url>
							<s:div cssClass="btn btn-xs red"
								onclick="javascript:confirmDialogWithTarget(this,'mainContentDiv');"
								id='%{removeQuestionBank}' title="Delete this Question Bank">
								<i class="fa fa-times"></i>Delete
							</s:div>
						</s:if>
					</td>
				</s:if>
			</tr>
		</s:iterator>
	</tbody>
</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no records.
	</div>
</s:else>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/onload.js">
</script>
<script type="text/javascript">
$(document).ready(function() {
		TableAdvanced.init();
		UIExtendedModals.init();
	});
</script>
 