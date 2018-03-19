<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<s:if test="%{objectList!= null && !objectList.isEmpty()}">
		<h4 class="bold pageTitle">
			Financial Year
		</h4>
			<!-- <p>
				<span class="label label-danger">NOTE :</span>&nbsp; You can edit/update
				existing exam grades by clicking on edit pen icon in each row at right
				side.
			</p> -->
		<div class="spaceDiv"></div>
		<table
			class="table table-striped table-bordered table-hover table-full-width"
			id="sample_2">
			<!--<div data-target="overAllGradesCont">
				-->
			<thead>
				<tr>
					<th>
						Financial Year
					</th>
					<th>
						Start Date
					</th>
					<th>
						End Date
					</th>
					
					<th>
						Edit
					</th>
					<th>
						Delete
					</th>
				</tr>
			</thead>
			<!--<div id="overAllGradesCont">
				-->
			<tbody>
				<s:iterator value="objectList">
					<!--<div overalGrade="<s:property value='gradeName' />" class="item">
						-->
					<tr>
						<td>
							<s:property value="yearName" />
						</td>
						<td>
							<s:property value="userFormattedStartDate" />
						</td>
						<td>
							<s:property value="userFormattedEndDate" />
						</td>
						<td>
						
						<s:if test='%{status == "Y"}'>
							<s:url id="urlAcceptOrRejectLinks" action="ajaxDoCreateFinancialYear"
								namespace="/admin" escapeAmp="false" includeParams="all">
								<s:param name="financialYear.id" value="%{id}" />
							</s:url>
							<sj:a href="%{urlAcceptOrRejectLinks}" targets="financialYearDivId"
								cssClass="ajaxify btn btn-xs red"><i class="fa fa-edit"></i>Edit</sj:a>
						</s:if>
						<s:else>
							--
						</s:else>
						</td>
						<td>
							<%-- <s:if test='%{#session.previousYear=="N"}'>
								<s:url id="removeOverallGrade" action="ajaxRemoveOverAllGrades"
									includeParams="all" escapeAmp="false" namespace="/exam">
									<s:param name="overAllGrades.id" value="%{id}" />
									<s:param name="tempString">overAllGrades</s:param>
								</s:url>
								<s:div cssClass="btn btn-xs red"
									onclick="javascript:confirmDialogWithTarget(this,'examContentInfo');"
									id='%{removeOverallGrade}' title="Delete this grade">
									<i class="fa fa-times"></i>Delete
									</s:div>
							</s:if> --%>
							--
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			You have not created financial year.
		</div>
	</s:else>

<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	TableAdvanced.init();
	TableEditable.init();
	UIExtendedModals.init();
	
	});
	

</script>
