<%@ include file="/common/taglibs.jsp"%>
	<%@ include file="/common/messages.jsp"%>
	<s:if test="%{objectList != null && !objectList.isEmpty()}">
		<table class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
				<thead>
					<tr>
						<th>
							Sibling Name
						</th>
						<th>
							Class & Section
						</th>
						<th>
							Academic Year
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
							<s:property value="siblingAccount.person.personFullName" />
						</td>
						<td>
							<s:property value="studyClass.classAndSection" />
						</td>
						<td>
							<s:property value="academicYear"/>
						</td>
						<td>
							<s:if test='%{#session.previousYear == "N"}'>
								<s:url id="removeLeaveSetting" action="ajaxRemoveStudentSiblings"
									includeParams="all" escapeAmp="false" namespace="/student">
									<s:param name="tempId" value="%{tempId}" />
									<s:param name="tempId1" value="%{tempId1}" />
									<s:param name="tempId2" value="%{id}" />
								</s:url>
								<s:div cssClass="btn btn-xs red"
									onclick="javascript:confirmDialogWithTarget(this,'deleteContentDiv');"
									id='%{removeLeaveSetting}' title="Delete sibling.">
									<i class="fa fa-times"></i>Delete
								</s:div>
							</s:if>
						</td>
					</s:iterator>
				</tbody>
			</table>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			Currently there are no Siblings.
		</div>
	</s:else>
