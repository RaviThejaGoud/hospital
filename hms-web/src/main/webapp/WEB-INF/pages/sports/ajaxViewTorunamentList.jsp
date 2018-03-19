<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{tourList != null && !tourList.isEmpty()}">
	<div class="spaceDiv"></div>
	<table class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
		<thead>
			<tr>
				<th>Sl.No</th>
				<th>Tournament Name</th>
				<th>Sports Name</th>
				<th>Location </th>
				<th>Start Date (MM/DD/YYYY)</th>
				<th>End Date (MM/DD/YYYY)</th>
				<th> Team</th>
				<th>Schedule</th>
				<s:if test='%{user.schoolAdmin || user.id == staff.account.id  && #session.previousYear == "N"}'>
				<th>Edit<br></th>
				<th>Delete<br></th>
				</s:if>
			</tr>
		</thead>
		<tbody>
			<%int i = 0; %>
			<s:iterator value="tourList">
				<tr>
					<td>
						<%i++; %><%=i %>
					</td>
					<td><s:property value="tournamentName" /></td>
					<td><s:property value="teamVO.sportsVO.sportName" /></td>
					<td>
						<ul class="tooltipDiv">
							<li><a href="#">View </a>
								<ul class="tooltipSubDiv">
									<div class="popover bottom " style="display: none;">
										<div class="arrow"></div>
										<h3 class="popover-title">Location </h3>
										<div class="popover-content">
											<s:if test="%{tourLocationAddressDesc != null && !tourLocationAddressDesc.isEmpty()}">
											<s:property value="tourLocationAddressDesc" />
											</s:if>
											<s:else>
												Currently there is no address available. 
												</s:else>
										</div>
									</div>
								</ul></li>
						</ul>
					</td>
					<td><s:property value="startDateStr" /></td>
					<td><s:property value="endDateStr" /></td>
					<td>
							<ul class="tooltipDiv">
								<li>
									<a href="#">View </a>
									<ul class="tooltipSubDiv">
										<div class="popover bottom " style="display: none;">
											<div class="arrow"></div>
											<h3 class="popover-title">
													<s:property value="teamVO.teamName"/>
											</h3>
											<div class="popover-content">
												<s:if test="%{tourList != null && !tourList.isEmpty()}">
												<s:iterator value="teamVO.playersList">
													<li>
														<s:if test='%{playerStatus == "C"}'>
															<s:property value="playerName" />
															<font color="green"><b> ( C ) </b></font>
														</s:if> <s:elseif test='%{playerStatus == "V"}'>
															<s:property value="playerName" />
															<font color="orange"><b> ( VC ) </b></font>
														</s:elseif> <s:else>
															<s:property value="playerName" />
														</s:else>
													</li>
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
							<s:if test="%{attachmentsVO.size() > 0  && !attachmentsVO.isEmpty()}">
								<a href="${pageContext.request.contextPath}/sports/downloadTournamentAttachments.do?tempId=<s:property value="tournamentId" />"
									class="btn btn-xs purple" title="Download"><i class="fa fa-edit"></i>Download </a>
							</s:if>	<s:else><a href="${pageContext.request.contextPath}/sports/downloadTournamentAttachments.do?tempId=<s:property value="tournamentId" />"
									class="btn btn-xs purple disabled " title="Download" ><i class="fa fa-edit" ></i>Download </a></s:else>	
						</td>
						<s:if test='%{user.schoolAdmin || user.id == staff.account.id  && #session.previousYear == "N"}'>
							<td>
								<s:url id="editTournament" action="ajaxEditTournament" includeParams="all" escapeAmp="false" namespace="/sports">
									<s:param name="tournamentVO.tournamentId" value="%{tournamentId}" />
								</s:url> 
								<sj:a href="%{editTournament}" targets="tournamentContentDiv" cssClass="btn btn-xs purple" title="Edit" >
									<i class="fa fa-edit"></i>
									Edit 
								</sj:a>
							</td>
							<td>
								<s:if test='%{tourDeleteStatus == "D"}'>  
									<s:url id="removeTournament" action="ajaxRemoveTournament" includeParams="all" escapeAmp="false" namespace="/sports">
										<s:param name="tournamentVO.tournamentId" value="%{tournamentId}" />
									</s:url> <s:div cssClass="btn btn-xs red disabled" onclick="javascript:confirmDialogWithTarget(this,'mainContentDiv');" id='%{removeTournament}' title="Delete this tournament" >
										<i class="fa fa-times"></i>Delete </s:div>
								 </s:if>  
								 <s:else>
									<s:url id="removeTournament" action="ajaxRemoveTournament" includeParams="all" escapeAmp="false" namespace="/sports">
										<s:param name="tournamentVO.tournamentId" value="%{tournamentId}" />
									</s:url> <s:div cssClass="btn btn-xs red" onclick="javascript:confirmDialogWithTarget(this,'mainContentDiv');" id='%{removeTournament}' title="Delete this tournament">
										<i class="fa fa-times"></i>Delete </s:div>
								</s:else>
							</td>
						</s:if>
					</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no Tournaments. 
		<s:if test='%{user.schoolAdmin || user.id == staff.account.id  && #session.previousYear == "N"}'>
			Please <a href="#" onclick="javascript:addTournament()"><font color="red">Click here</font></a> to add a Tournament.
		</s:if>
	</div>
</s:else>
<div id="responsive2"></div>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/onload.js">
</script>
<script type="text/javascript">
		TableAdvanced.init();
		UIExtendedModals.init();
		function addTournament() {
			$('a#addTournaments').click();
		}
		/* $('%{removeTournament} *').prop('disabled', true);		 */
</script>
					
