<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/onload.js"></script>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Events
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
				
				<div id="staffEventsDivId">
				
				<s:if test='%{user.isParent=="Y"}'>
				  <s:if test='%{viewStudentPersonAccountDetailsList != null && !viewStudentPersonAccountDetailsList.isEmpty()}'>
								<div class="row">
									<div class="form-group form-horizontal" align="center">
										<label class="control-label col-md-3">
											Student Name :
										</label>
										<div class="col-md-3">
											<s:select id="classSectionId" list="viewStudentPersonAccountDetailsList"
												listKey="classSectionId" label="Student Name" cssClass="form-control"
												listValue="idAndName" name="tempId1" theme="simple"
												onchange="javascript:getAjaxDoGetStudyClassEvents(this.value);" />
										</div>
									</div>
								</div>
							</s:if>
				</s:if>
				<h4 class="pageTitle bold">Active Events</h4>
					<s:if test="%{objectList != null && !objectList.isEmpty()}">
						<table class="table table-striped table-bordered table-hover table-full-width" id="sample_5">
							<thead>
								<tr>
									<th style="display: none;">
										Start Date
									</th>
									
									<th>
										Event Name
									</th>
									
									<th>Event Date & Time</th>
									<!-- <th>Start Date &Time</th>
									<th>End Date &Time</th> -->
									<th>
										Photos
									</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="objectList">
									<tr>
									
										<td style="display: none;">
											<s:property value="eventStartDate" />
										</td>
										
										<td>
											<a data-toggle="modal" href="#viewEventDetials"
												onclick="javascript:PopupViewEventDetials(<s:property value="id" />);"
												<s:property value="eventName" />
												class="viewEvent tabClass" title="View Events"><s:property
													value="eventName" /> </a>
										</td>
										<%-- <td ><s:property value="viewClassesStaff" /></td> --%>
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
													
										<%-- <td><s:property value="eventStartDateStr" /> / <s:property value="startTime" /></td>
										<td><s:property value="eventEndDateStr" /> / <s:property value="endTime" /></td> --%>
										
										<td><s:property value="eventStartDateStr" /> - <s:property value="eventEndDateStr" /> <br/> <s:property value="startTime" /> - <s:property value="endTime" /></td>
										
										<td><s:if test='%{#session.previousYear=="N"}'>
												<s:if test="%{eventsAlbum != null && !eventsAlbum.isEmpty()}">
													<s:url id="editAlbumPhotos" action="ajaxViewEventPhotos"
														includeParams="all" escapeAmp="false" namespace="/admin">
														<s:param name="eventsAlbum.eventId" value="%{id}"></s:param>
													</s:url>
													<sj:a href="%{editAlbumPhotos}" cssClass="btn btn-xs purple"
														targets="staffEventsDivId">
														<i class="fa fa-edit"></i> View Photos
														</sj:a>
												</s:if>
												<s:else>
													--
												</s:else>
												<%-- <s:else>
													<s:url id="editAlbumPhotos" action="ajaxDoAddAlbum"
														includeParams="all" escapeAmp="false" namespace="/admin">
														<s:param name="eventsAlbum.eventId" value="%{id}"></s:param>
													</s:url>
													<sj:a href="%{editAlbumPhotos}" cssClass="btn btn-xs purple"
														targets="eventsContentDiv">
														<i class="fa fa-edit"></i> Add Album
														</sj:a>
												</s:else> --%>
											</s:if>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</s:if>
					<s:else>
						<div class="alert alert-info">
							Currently there are no active events.
						</div>
					</s:else>
					
					<h4 class="pageTitle bold">Completed Events</h4>
					<s:if test="%{tempList != null && !tempList.isEmpty()}">
						<table class="table table-striped table-bordered table-hover table-full-width" id="sample_4">
							<thead>
								<tr>
									<th style="display: none;">
										Start Date
									</th>
									
									<th>
										Event Name
									</th>
									<!-- <th>Class Name</th> -->
									<!-- <th>Start Date &Time</th>
									<th>End Date &Time</th> -->
									
									<th>Event Date & Time</th>
									
									<th>
										Photos
									</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="tempList">
									<tr>
									
										<td style="display: none;">
											<s:property value="eventStartDate" />
										</td>
										
										<td>
											<a data-toggle="modal" href="#viewEventDetials"
												onclick="javascript:PopupViewEventDetials(<s:property value="id" />);"
												<s:property value="eventName" />
												class="viewEvent tabClass" title="View Events"><s:property
													value="eventName" /> </a>
										</td>
										<%-- <td ><s:property value="viewClassesStaff" /></td> --%>
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
													
										<%-- <td><s:property value="eventStartDateStr" /> / <s:property value="startTime" /></td>
										<td><s:property value="eventEndDateStr" /> / <s:property value="endTime" /></td> --%>
										
										<td><s:property value="eventStartDateStr" /> - <s:property value="eventEndDateStr" /> <br/> <s:property value="startTime" /> - <s:property value="endTime" /></td>
										
										<td><s:if test='%{#session.previousYear=="N"}'>
												<s:if test="%{eventsAlbum != null && !eventsAlbum.isEmpty()}">
													<s:url id="editAlbumPhotos" action="ajaxViewEventPhotos"
														includeParams="all" escapeAmp="false" namespace="/admin">
														<s:param name="eventsAlbum.eventId" value="%{id}"></s:param>
													</s:url>
													<sj:a href="%{editAlbumPhotos}" cssClass="btn btn-xs purple"
														targets="staffEventsDivId">
														<i class="fa fa-edit"></i> View Photos
														</sj:a>
												</s:if>
												<s:else>
													--
												</s:else>
												<%-- <s:else>
													<s:url id="editAlbumPhotos" action="ajaxDoAddAlbum"
														includeParams="all" escapeAmp="false" namespace="/admin">
														<s:param name="eventsAlbum.eventId" value="%{id}"></s:param>
													</s:url>
													<sj:a href="%{editAlbumPhotos}" cssClass="btn btn-xs purple"
														targets="eventsContentDiv">
														<i class="fa fa-edit"></i> Add Album
														</sj:a>
												</s:else> --%>
											</s:if>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</s:if>
					<s:else>
						<div class="alert alert-info">
							Currently there are no completed events.
						</div>
					</s:else>
					
					
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!--<div class="block grid_14" id="commomnTabs">
	<div class="block_head" id="topMenu">
		<h4 class="pageTitle bold"> Events </h4>
	</div>
	<div class="block_content" id="eventsResults">
		<jsp:include page="/common/messages.jsp"></jsp:include>
		<div id="resultsPage">
					<div id="commonStep" class="commonFormTabs">
						<fieldset style="padding: 0px 0 5px">
							<s:if test="%{objectList != null && !objectList.isEmpty()}">
								<strong>
								    Event Details
								</strong>
								<div class="grid_12 th">
								<div class="grid_4">
									<div class="grid_2 eventNameDiv sortHeader divArrow">
										Event Name
									</div>
									</div>
									<div class="grid_4">
									<div class="grid_2 startDateDiv sortHeader divArrow">
										Start Date
									</div>
									</div>
									<div class="grid_4">
									<div class="grid_2 endDateDiv sortHeader divArrow">
										End Date
									</div>
									</div>
								</div>
								<div id="eventsDiv">
									<s:iterator value="objectList">
									 <div eventName="<s:property value='eventName' />" eventStartDateStr="<s:property value='eventStartDate' />" eventEndDateStr="<s:property value='eventEndDate' />" class="item">
										<div class="grid_12 row">
											<div class="grid_4">
												<s:url id="removeEvent" action="ajaxDeleteEvent"
													includeParams="all" escapeAmp="false">
													<s:param name="id" value="id"></s:param>
												</s:url>
												<s:div cssStyle="margin-top:3px;" cssClass="close emsRemove"
													id='%{removeEvent}' theme="simple"
													title="Delete this Event"></s:div>
												<img style="display: none; padding-top: 18px;"
													alt="Loading..."
													src="${pageContext.request.contextPath}/images/indicator.gif"
													id="indicator">
												
												<a
													href="${pageContext.request.contextPath}/admin/ajaxViewSingleEvent.do?eventId=<s:property value="id"/>"
													class="viewEvent tabClass" title="View Events"><s:property
														value="eventName" /> </a>
												<%-- <s:url id="viewAdminEvent" action="ajaxViewSingleEvent"
													includeParams="all" escapeAmp="false">
													<s:param name="id" value="id" />
												</s:url>
												<sj:a href="%{viewAdminEvent}" onBeforeTopics="cleanOpenRows"
													onCompleteTopics="doInitViewEvent" indicator="indicator"
													targets="editEvent%{id}">
												<s:property value="eventName" />
												</sj:a>
											--%>
											</div>
											<div class="grid_4">
												<s:property value="eventStartDateStr" />
											</div>

											<div class="grid_4">
												<s:property value="eventEndDateStr" />
											</div>
										</div>
									 </div>
									</s:iterator>
								</div>
							</s:if>
							<s:else>
								<div class="grid_12">
									Currently there are no events.
								</div>
							</s:else>
						</fieldset>
					</div>
				</div>
			</div>
		</div>-->
		<div id="viewEventDetials"></div>
<script type="text/javascript">
TableAdvanced.init();
function PopupViewEventDetials(id) {
	var url = jQuery.url.getChatURL("/admin/ajaxViewSingleEvent.do");
	$('#viewEventDetials')
			.html(
					'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
		url : url,
		cache : false,
		data : "eventId=" + id,
		success : function(html) {
			$("#viewEventDetials").html(html);
		}
	});
}
changePageTitle('All Teacher Events List');

function getAjaxDoGetStudyClassEvents(studyClassId) {
  	if (studyClassId == "") {
		alert("Please select the student.")
	} else {
		var pars = "studyClassId=" + studyClassId;
		$("#mainContentDiv")
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var url = jQuery.url.getChatURL("/student/ajaxStudentEvents.do");
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#mainContentDiv").html(html);
			}
		});
	}
}

</script>