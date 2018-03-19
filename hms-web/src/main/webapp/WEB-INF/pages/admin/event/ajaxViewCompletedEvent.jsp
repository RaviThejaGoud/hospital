<%@ include file="/common/taglibs.jsp"%>
<h4 class="pageTitle bold">Completed Events</h4>
	<s:if test="%{tempList != null && !tempList.isEmpty()}">
		<table
			class="table table-striped table-bordered table-hover table-full-width"
			id="sample_5">
			<thead>
				<tr>
				<th style="display: none;">
					Start Date
				</th>
					<th>Event Name</th>
					<th>Event for</th>
					<!-- <th>Start Date &Time</th>
					<th>End Date &Time</th> -->
					<th>Event Date & Time</th>
					
					<th>Photos</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="tempList">
					<tr>
						<td style="display: none;">
							<s:property value="eventStartDate" />
						</td>
						<td><a data-toggle="modal" href="#viewEventDetials"
							onclick="javascript:PopupViewEventDetials(<s:property value="id" />);"><s:property
									value="eventName" /> </a></td>
									
						<td style="width: 400px;"><s:property value="viewClassesStaff" /></td>		
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
							<div class="col-md-5">
								<a data-toggle="modal" href="#uploadCompletedPhotosDiv" onclick="javascript:popupCompletedUploadPhotos(<s:property value="id"/>);">Add</a>
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
							</div> 
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
						</td>
								
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</s:if>
	<s:else>
		<div class="alert alert-info">Currently there are no completed
			events.</div>
	</s:else>
<div id="uploadCompletedPhotosDiv"></div>
<script type="text/javascript">
changePageTitle('View Completed Events');
$('.js-activated').dropdownHover().dropdown();
TableAdvanced.init();
	function popupCompletedUploadPhotos(eventId) {
		$('#uploadPhotosDiv').remove();
		var url = jQuery.url.getChatURL("/admin/ajaxDoUploadEventPhotos.do?events.id="+eventId);
		$('#uploadCompletedPhotosDiv') .html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
			url : url,
			cache : false,
			success : function(html) {
				$("#uploadCompletedPhotosDiv").html(html);
			}
		});
	}
	
</script>
