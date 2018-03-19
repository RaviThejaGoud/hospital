<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{teamList != null && !teamList.isEmpty()}">
	 <div class="spaceDiv"></div>
	<table class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
		<thead>
			<tr>
				<th>Sports Name</th>
				<th>Team Name </th>
				<th>Players </th>
				<th>Captain Name </th>
				<th>Vice-Captain Name </th>
				<s:if test='%{user.schoolAdmin || user.id == staff.account.id && #session.previousYear == "N"}'>
					<th>Edit</th>
					<th>Delete</th>
				</s:if>
			</tr>
		</thead>
 		<tbody>
			<s:iterator value="teamList">
				<tr>
					<td>
						<s:property value="sportName" />
					</td>
					<td>
						<s:property value="teamName" />
					</td>
					<td>
							<ul class="tooltipDiv">
								<li>
									<a href="#">View </a>
									<ul class="tooltipSubDiv">
										<div class="popover bottom " style="display: none;">
											<div class="arrow"></div>
											<h3 class="popover-title">
												Players
											</h3>
											<div class="popover-content">
												<s:if test="%{playersList != null && !playersList.isEmpty()}">
												<s:iterator value="playersList">
													<li>
														<s:if test='%{playerStatus == "C"}'>
															<s:property value="playerName" />
															<font color="green"><b> ( C ) </b></font>
														</s:if> <s:elseif test='%{playerStatus == "V"}'>
															<s:property value="playerName" />
															<font color="orange"><b> ( VC ) </b></font>
														</s:elseif> <s:else>
															<s:property value="playerName" />
														</s:else></li>
												</s:iterator>
											</s:if>
												<s:else>
												Currently there are no Players. 
												</s:else>
											</div>
										</div>
									</ul>
								</li>
							</ul>
						</td>
					<td>
						<s:if test="%{playersList != null && !playersList.isEmpty()}">
							<s:iterator value="playersList">
								<s:if test='%{playerStatus != null && !playerStatus.isEmpty() && playerStatus == "C"}'>
									<s:property value="playerName" />
								</s:if>
							</s:iterator>
						</s:if>
					</td>
					<td>
						<s:if test="%{playersList != null && !playersList.isEmpty()}">
							<s:iterator value="playersList">
								<s:if test='%{playerStatus != null && !playerStatus.isEmpty() && playerStatus == "V"}'>
									<s:property value="playerName" />
								</s:if>
							</s:iterator>
						</s:if>
					</td>
					<s:if test='%{user.schoolAdmin || user.id == staff.account.id  && #session.previousYear == "N"}'> 
					<td>
						<s:url id="editTeam" action="ajaxEditTeam" includeParams="all" escapeAmp="false" namespace="/sports">
							<s:param name="teamVO.id" value="%{id}" />
						</s:url> 
						<sj:a href="%{editTeam}" targets="teamContentDiv" cssClass="btn btn-xs purple" title="Edit"> <i class="fa fa-edit"></i>
							Edit
						</sj:a>
					</td>
					<td>
						<s:url id="removeTeam" action="ajaxRemoveTeam" includeParams="all" escapeAmp="false" namespace="/sports">
							<s:param name="teamVO.id" value="%{id}" />
						</s:url>
						<s:div cssClass="btn btn-xs red" onclick="javascript:confirmDialogWithTarget(this,'mainContentDiv');" id='%{removeTeam}' title="Delete this Team">
							<i class="fa fa-times"></i>
							Delete
						</s:div>
						<%-- </s:if> --%>
					</td>
					</s:if>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no Teams.
		<s:if test='%{user.schoolAdmin || user.id == staff.account.id &&s #session.previousYear == "N"}'>
			Please <a href="#" onclick="javascript:addTeam()"><font color="red">Click here</font></a> to add a Team.
		</s:if>
	</div>
</s:else> 
<div id="responsive2"></div>
  <script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/onload.js">
</script>
<script type="text/javascript">
TableAdvanced.init();
UIExtendedModals.init();
function addTeam(){
	$("a#urlAddTeam").click();
}
</script>
