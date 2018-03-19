<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{achievementList != null && !achievementList.isEmpty()}">
	<div class="spaceDiv"></div>
	<table class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
		<thead>
			<tr>
				<th>Sl.No</th>
				<th>Sports Name</th>
				<th>Tournament </th>
				<th>Team</th>
				<th>Achievement Description</th>
				<th>Photos</th>
			</tr>
		</thead>
	<tbody>
			<%int i = 0; %>
				<s:iterator value="achievementList" >
				<tr>
					<td>
						<%i++; %><%=i %>
					</td>
					<td><s:property value="teamVO.sportsVO.sportName" /></td>
					<td><s:property value="tournamentVO.tournamentName" /></td>
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
												<s:if test="%{achievementList != null && !achievementList.isEmpty()}">
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
					<td><ul class="tooltipDiv">
							<li><a href="#">View </a>
								<ul class="tooltipSubDiv">
									<div class="popover bottom " style="display: none;">
										<div class="arrow"></div>
										<h3 class="popover-title">Description</h3>
										<div class="popover-content">
											<s:if test="%{achievementList != null && !achievementList.isEmpty()}">
												<s:property value="description"/>
											</s:if>
											<s:else>
												Currently there is no Description available. 
												</s:else>
										</div>
									</div>
								</ul>
							</li>
						</ul>
					</td>
					<td><ul class="tooltipDiv">	
						<s:if test='%{user.schoolAdmin || user.id == staff.account.id && #session.previousYear == "N"}'>
							<div class="col-md-6">
								<a data-toggle="modal" href="#uploadPhotosDiv" onclick="javascript:popupUploadPhotosDiv(<s:property value="id"/>);">
								 Add </a>
								 <s:if test="%{attachmentVOs.size() > 0 && !attachmentVOs.isEmpty()}">
										/
										<a data-toggle="modal"  href="<c:url value='${filePath}'/>"  onclick="javascript:popupUploadPhotos(<s:property value="id"/> );">
										 View </a>
									</s:if>
							</s:if><s:else>
									<s:if test="%{attachmentVOs.size() > 0 && !attachmentVOs.isEmpty()}">
										<a data-toggle="modal"  href="<c:url value='${filePath}'/>"  onclick="javascript:popupUploadPhotos(<s:property value="id"/> );">
										 View </a>
									</s:if> 
							</s:else>
						</div>
						</ul>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no Achievements.
		<s:if test='%{user.schoolAdmin || user.id == staff.account.id && #session.previousYear == "N"}'>
			 Please <a href="#" onclick="javascript:ajaxAddAcievements()"><font color="red">Click here</font></a> to add a Achievement.
		 </s:if>
	</div>
</s:else> 
<div id="uploadPhotosDiv"></div>
<div id="filePath" style="display: none;"></div>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/onload.js"> </script>
<script type="text/javascript">
	TableAdvanced.init();
	UIExtendedModals.init();
	$(document)	.ready(	function() {
		FormComponents.init();
		FormAdvanced.init();
		UIExtendedModals.init();
		});
	function ajaxAddAcievements() {
		$('a#addAchievement').click();
	}
	function popupUploadPhotos(achievementId) {
		var url = jQuery.url.getChatURL("/sports/ajaxViewAchievementPhotos.do");
	    var pars = "achievementId="+achievementId; 
		$('#filePath') .html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		 $.ajax( {
			url : url,
			data : pars,
			cache : false,
			success : function(html) {
				$("#filePath").html(html);
			}
		});  
	}
	function popupUploadPhotosDiv(achievementId) {
		var url = jQuery.url.getChatURL("/sports/ajaxDoUploadAchievementPhotos.do?achievementVO.id="+achievementId);
		$('#uploadPhotosDiv') .html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		 $.ajax( {
			url : url,
		//	data : pars,
			cache : false,
			success : function(html) {
				$("#uploadPhotosDiv").html(html);
			}
		});  
	}
</script>

