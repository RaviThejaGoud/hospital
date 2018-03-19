<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/onload.js"></script>
<style>
.break-word {
  word-wrap: break-word;
}
</style>
<div id="deleteEveContentDiv">
	<div class="row">
		<div class="col-md-12">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-globe"></i>Event Details
					</div>
				</div>
				<div class="portlet-body">
					<div class="tabbable tabbable-custom tabbable-full-width">
						<ul class="nav nav-tabs">
							<s:if
								test='%{user.isOnlySchoolAdmin=="Y" || user.isSchoolAdmin=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y"}'>
								<li class="dropdown" id="albumULId"><a data-hover="dropdown"
									data-toggle="dropdown" class="dropdown-toggle js-activated"
									href="#"> View Photos <b class="caret"></b>
								</a>
									<ul role="menu" class="dropdown-menu pull-right">
										<li><s:url id="viewEventPhotos"
												action="ajaxViewEventPhotos" namespace="/admin">
											</s:url> <sj:a href="%{viewEventPhotos}" targets="eventsContentDiv"
												data-toggle="tab">View Photos</sj:a></li>

										<li id="addAlbumLiId"><s:url id="addAlbum" action="ajaxDoAddAlbum"
												namespace="/admin">
											</s:url> <sj:a href="%{addAlbum}" targets="eventsContentDiv"
												data-toggle="tab">Add Album</sj:a></li>

									</ul></li>
							</s:if>
							<s:elseif test='%{user.isParent=="Y"}'>
								<li><s:url id="viewEventPhotos"
										action="ajaxViewEventPhotos" namespace="/admin">
									</s:url> <sj:a href="%{viewEventPhotos}" targets="eventsContentDiv"
										data-toggle="tab">View Photos</sj:a></li>
							</s:elseif>
							<s:if
								test='%{user.isOnlySchoolAdmin=="Y" || user.isSchoolAdmin=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y"}'>
								<li class="dropdown active"  id="eventULId"><a data-hover="dropdown"
									data-toggle="dropdown" class="dropdown-toggle js-activated"
									href="#"> View Events <b class="caret"></b>
								</a>
									<ul role="menu" class="dropdown-menu pull-right">
										<s:if test='%{#session.previousYear == "N"}'>
										
											<li><s:url id="viewCompletedEvent" action="ajaxViewCompletedEvent"
													includeParams="all" escapeAmp="false" namespace="/admin">
													<s:param name="tempId" value="0" />
												</s:url> <sj:a href="%{viewCompletedEvent}" targets="eventsContentDiv"
													data-toggle="tab">View Completed Events</sj:a></li>
													
											<li><s:url id="doAddEvent" action="ajaxDoAddEvent"
													includeParams="all" escapeAmp="false" namespace="/admin">
													<s:param name="tempId" value="0" />
												</s:url> <sj:a href="%{doAddEvent}" targets="eventsContentDiv"
													data-toggle="tab">Add Event</sj:a></li>
										</s:if>
										<li class="active"><s:url id="viewEvents"
												action="ajaxViewEvents" namespace="/admin">
											</s:url> <sj:a href="%{viewEvents}" targets="mainContentDiv"
												data-toggle="tab">View Events</sj:a></li>
									</ul></li>
							</s:if>
							<s:elseif test='%{user.isParent=="Y"}'>
								<li class="active"><s:url id="viewEvents"
										action="ajaxViewEvents" namespace="/admin">
									</s:url> <sj:a href="%{viewEvents}" targets="mainContentDiv"
										data-toggle="tab">View Events</sj:a></li>
							</s:elseif>
						</ul>
						<div id="eventsContentDiv" class="tab-content">
							<jsp:include page="/common/messages.jsp" />
							<s:if test='%{user.isOnlySchoolAdmin=="Y" || user.isSchoolAdmin=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y"}'>
									<span class="label label-danger"> NOTE : </span>&nbsp;
										 You cannot find 'Edit' or 'Delete' icons for current and past events details.
										 <div class="spaceDiv"></div>
								</s:if>
							<h3></h3>
							<h4 class="pageTitle bold">Active Events</h4>
							<s:if test="%{academicYear != null && academicYear != empty}">
								<s:if test="%{objectList != null && !objectList.isEmpty()}">
								
									<table
										class="table table-striped table-bordered table-hover table-full-width"
										id="sample_5">
										<thead>
											<tr>
												<th style="display: none;">
													Start Date
												</th>
												<th>Event Name</th>
												<th>Event for </th>
												<th>Event Date & Time</th>
												<!-- <th>End Date &Time</th> -->
												<s:if test='%{user.isOnlySchoolAdmin=="Y" || user.isSchoolAdmin=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y"}'>
													<th>Edit</th>
													<th>Delete</th>
												</s:if>
												<th>Photos</th>
											</tr>
										</thead>
										<tbody>
											<s:iterator value="objectList">
												<tr class="eventTableTR">
													<td style="display: none;">
														<s:property value="eventStartDate" />
													</td>
													<td><a data-toggle="modal" href="#viewEventDetials"
														onclick="javascript:PopupViewEventDetials(<s:property value="id" />);"><s:property
																value="eventName" /> </a></td>
																
														<td class="eventTableTD"><s:property value="viewClassesStaff" /></td>		
																
													<%-- <td>
														<ul class="tooltipDiv">
															<li>
																<a href="#">view</a>
																<ul class="tooltipSubDiv">
																	<div class="popover bottom " style="display: none;">
																		<div class="arrow"></div>
																		<h3 class="popover-title">
																			Assigned Classes
																		</h3>
																		<div class="popover-content">
																			<s:if test='%{studyClass != null && studyClass != empty}'>
																				<s:iterator value="studyClass">
																					<s:property value="ClassAndSection" /><br/>
																				</s:iterator>
																			</s:if>
																			<s:else>
																				No assigned classes
																			</s:else>
																		</div>
																	</div>
																</ul>
															</li>
														</ul>
													</td> --%>
													
													<td><s:property value="eventStartDateStr" /> - <s:property value="eventEndDateStr" /> <br/> <s:property value="startTime" /> - <s:property value="endTime" /></td>
													
													<%-- <td><s:property value="eventStartDateStr" /> <br/> <s:property value="startTime" /></td>
													<td><s:property value="eventEndDateStr" /> <br/> <s:property value="endTime" /></td> --%>
													<s:if test='%{user.isOnlySchoolAdmin=="Y" || user.isSchoolAdmin=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y"}'>
														<td>
															<%-- <a data-toggle="modal" href="#editEventDetials"
																class="btn btn-xs purple"
																onclick="javascript:PopupEditEventDetials(<s:property value="id" />);">
																<i class="fa fa-edit"></i>Edit
															</a> --%>
															
															<s:url id="editAlbumPhotos" action="ajaxDoAddEvent"
																	includeParams="all" escapeAmp="false" namespace="/admin">
																	<s:param name="tempId" value="%{id}"></s:param>
																</s:url>
																<sj:a href="%{editAlbumPhotos}" cssClass="btn btn-xs purple"
																	targets="eventsContentDiv">
																	<i class="fa fa-edit"></i> Edit
																	</sj:a>
																	
																	
														</td>
														<td>
															<s:url id="removeEvent" action="ajaxDeleteEvent"
																includeParams="all" escapeAmp="false"
																namespace="/admin">
																<s:param name="tempId" value="id"></s:param>
																<s:param name="tempString" value="%{eventName}"></s:param>
															</s:url>
															<s:div cssClass="btn btn-xs red" id='%{removeEvent}'
																theme="simple" title="Delete this Event"
																onclick="javascript:confirmDialogWithTarget(this,'deleteEveContentDiv');">
																<i class="fa fa-times"></i>Delete</s:div>
														</td>
													</s:if>
													<%-- <td><s:if test='%{#session.previousYear=="N"}'>
															<s:if test="%{eventsAlbum != null && !eventsAlbum.isEmpty()}">
																<s:url id="editAlbumPhotos" action="ajaxViewEventPhotos"
																	includeParams="all" escapeAmp="false" namespace="/admin">
																	<s:param name="eventsAlbum.eventId" value="%{id}"></s:param>
																	<s:param name="tempString2">Event</s:param>
																</s:url>
																<sj:a href="%{editAlbumPhotos}" cssClass="btn btn-xs purple"
																	targets="eventsContentDiv">
																	<i class="fa fa-edit"></i> Add Photos
																	</sj:a>
															</s:if>
															<s:else>
																<s:url id="editAlbumPhotos" action="ajaxDoAddAlbum"
																	includeParams="all" escapeAmp="false" namespace="/admin">
																	<s:param name="eventsAlbum.eventId" value="%{id}"></s:param>
																	<s:param name="tempString2">Event</s:param>
																</s:url>
																<sj:a href="%{editAlbumPhotos}" cssClass="btn btn-xs purple"
																	targets="eventsContentDiv">
																	<i class="fa fa-edit"></i> Add Album
																	</sj:a>
															</s:else>
														</s:if>
													</td> --%>
													
													<td><s:if test='%{#session.previousYear=="N"}'>
														<div class="col-md-6">
															<a data-toggle="modal" href="#uploadPhotosDiv" onclick="javascript:popupUploadPhotos(<s:property value="id"/>);">Add</a>
															<s:if test="%{eventsAlbum != null && !eventsAlbum.isEmpty()}">
																 /
																<s:url id="editAlbumPhotos" action="ajaxViewEventPhotos"
																	includeParams="all" escapeAmp="false" namespace="/admin">
																	<s:param name="eventsAlbum.eventId" value="%{id}"></s:param>
																	<s:param name="tempString2">Event</s:param>
																</s:url>
																<sj:a href="%{editAlbumPhotos}" 
																	targets="eventsContentDiv">
																	View
																	</sj:a>
															</s:if>
															<%-- <s:else>
																<s:url id="editAlbumPhotos" action="ajaxDoAddAlbum"
																	includeParams="all" escapeAmp="false" namespace="/admin">
																	<s:param name="eventsAlbum.eventId" value="%{id}"></s:param>
																	<s:param name="tempString2">Event</s:param>
																</s:url>
																<sj:a href="%{editAlbumPhotos}" cssClass="btn btn-xs purple"
																	targets="eventsContentDiv">
																	<i class="fa fa-edit"></i> Add Album
																	</sj:a>
															</s:else> --%>
														</s:if>
														</div> 
													</td>
													
															
												</tr>
											</s:iterator>
										</tbody>
									</table>
								</s:if>
								<s:else>
									<div class="alert alert-info">Currently there are no events.</div>
								</s:else>
							</s:if>
							<s:else>
								<div class="alert alert-info">
									Please add academic planner details. Then only you can add or
									view school events.
									<s:url id="settingEvents" action="ajaxAcademicSchoolSettings"
										namespace="/admin">
									</s:url>
									<sj:a href="%{settingEvents}" targets="mainContentDiv"
										cssClass="academicPlannerId"><font color="red">Click
											here</font></sj:a>
									to add academic planner details.
									<!--<a
											href='${pageContext.request.contextPath}/admin/ajaxDoSchoolInformation.do'>Click
											here</a> to add academic planner details.
								-->
								</div>
							</s:else>
							
							
							
							
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="uploadPhotosDiv"></div>
<div id="viewEventDetials"></div>
<div id="editEventDetials"></div>
<script type="text/javascript">
changePageTitle('View Events');
$('.js-activated').dropdownHover().dropdown();
TableAdvanced.init();

$("tr.eventTableTR").each(function(){
$(this).find("td.eventTableTD").css("white-space","normal");	
});

function PopupViewEventDetials(id) {
	var url = jQuery.url.getChatURL("/admin/ajaxViewSingleEvent.do");
	$('#viewEventDetials').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
			url : url,
			cache : false,
			data : "eventId=" + id,
			success : function(html) {
				$("#viewEventDetials").html(html);
			}
		});
	}
	function PopupEditEventDetials(id) {
	var url = jQuery.url.getChatURL("/admin/ajaxDoAddEvent.do");
	$('#editEventDetials').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
			url : url,
			cache : false,
			data : "tempId=" + id,
			success : function(html) {
				$("#editEventDetials").html(html);
			}
		});
	}
	$('a.academicPlannerId').click(function(){
		window.location.hash="target=ES.ajaxify AAP";
		window.location.reload();
	});
	
	function popupUploadPhotos(eventId) {
		
		$('#uploadCompletedPhotosDiv').remove();
		var url = jQuery.url.getChatURL("/admin/ajaxDoUploadEventPhotos.do?events.id="+eventId);
		$('#uploadPhotosDiv') .html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
			url : url,
			cache : false,
			success : function(html) {
				$("#uploadPhotosDiv").html(html);
			}
		});
	}
	
</script>
