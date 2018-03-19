<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />
<s:if test="%{objectList != null && !objectList.isEmpty()}">
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th>
					Subject Name
				</th>
				<th>
					Combined Classes
				</th>
				<th>
					Staff Name
				</th>
				<th>
					Edit
				</th>
				<th>
					Delete
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="objectList">
				<tr>
					<td>
						<s:property value="studySubject.name" />
					</td>
					<td>
						<s:property value="combinedClassNames" />
					</td>
					<td>
						<s:property value="staffFullNames" />
					</td>
					<td>
						<s:if test='%{#session.previousYear=="N"}'>
							<s:url id="doEditCombSub" action="ajaxDoAddCombinedClassSubjects"
								includeParams="all" escapeAmp="false" namespace="/admin">
								<s:param name="tempId" value="%{id}" />
							</s:url>
							<sj:a href="%{doEditCombSub}" 
								targets="sclCombinedClasCont" cssClass="btn btn-xs purple" title="Edit"><i class="fa fa-edit"></i>Edit
							</sj:a>
						</s:if>
					</td>
					<td>
						<s:if test='%{#session.previousYear=="N"}'>
							<s:url id="removeCombinedPeriods"
								action="ajaxRemoveCombinedPeriods" includeParams="all"
								escapeAmp="false" namespace="/admin">
								<s:param name="tempId" value="%{id}" />
							</s:url>
							<s:div cssClass="btn btn-xs red"
								onclick="javascript:confirmDialogWithTarget(this,'sclCombinedClasCont');"
								id='%{removeCombinedPeriods}'
								title="Delete these combined periods"><i class="fa fa-times"></i> Delete
							</s:div>
						</s:if>
					</td>

				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		You have not created combined class details.
	</div>
</s:else>
<script type="text/javascript">
changePageTitle('Periods');
TableAdvanced.init();
</script>
