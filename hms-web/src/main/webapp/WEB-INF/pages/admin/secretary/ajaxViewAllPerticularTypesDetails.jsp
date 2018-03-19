<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<s:if test="%{objectList!= null && !objectList.isEmpty()}">
		<h4 class="bold pageTitle">
			Particular Types
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
						Particular Type Name
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
							<s:property value="particularTypeName" />
						</td>
						
						<td>
							<s:url id="urlAcceptOrRejectLinks" action="ajaxDoCreateParticularType"
								namespace="/admin" escapeAmp="false" includeParams="all">
								<s:param name="particularType.id" value="%{id}" />
							</s:url>
							<sj:a href="%{urlAcceptOrRejectLinks}" targets="perticularDetailsDivId"
								cssClass="ajaxify btn btn-xs red"><i class="fa fa-edit"></i>Edit</sj:a>
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
			You have not created any particulars types.
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
