<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<s:if test="%{objectList!= null && !objectList.isEmpty()}">
		<h4 class="bold pageTitle">
			Particulars
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
						Particular Type
					</th>
					<th>
						Particular Name
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
				<s:iterator value="objectList" status="stat">
				<tr>
					<td>
						<s:property value="objectList[#stat.count-1][0]" />
					</td>
					<td>
					<s:property value="objectList[#stat.count-1][1]" />
					</td>	
						<td>
							<s:url id="urlAcceptOrRejectLinks" action="ajaxDoCreateParticular"
								namespace="/admin" escapeAmp="false" includeParams="all">
								<s:param name="particular.id" value="%{objectList[#stat.count-1][2]}" />
								<s:param name="particularType.id" value="%{objectList[#stat.count-1][3]}" />
							</s:url>
							<sj:a href="%{urlAcceptOrRejectLinks}" targets="perticularDetailsDivId"
								cssClass="ajaxify btn btn-xs red"><i class="fa fa-edit"></i>Edit</sj:a>
								
								
							<%-- <s:if test='%{#session.previousYear=="N"}'>
								<a data-toggle="modal" href="#responsive3"
									class="btn btn-xs purple"
									onclick="javascript:PopupExamGradesDetials(<s:property value="%{id}" />);"><i
									class="fa fa-edit"></i>Edit </a>
							</s:if> --%>
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
			You have not created any particulars.
		</div>
	</s:else>



<div id="responsive2"></div>
<div id="responsive3"></div>
<div id="responsive4"></div>
<div id="responsive5"></div>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	TableAdvanced.init();
	TableEditable.init();
	UIExtendedModals.init();
	
	});
	

</script>
