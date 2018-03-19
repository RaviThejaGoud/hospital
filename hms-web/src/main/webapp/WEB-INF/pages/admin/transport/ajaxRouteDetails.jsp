<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/onload.js">
</script>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div id="alertMsgDivId"></div>

<%--  <div id="assignStudentsToVehiclesDivId" style="display: none;">
<s:url id="doAssignStudentsId"
		action="ajaxDoAssignStudent" namespace="/admin" /> <sj:a
		id="doTransportAssignStudents" href="%{doAssignStudentsId}"
		targets="mainContentDiv" cssClass="ajaxify AMTP">
 	Assign Students To Vehicles</sj:a>
									 
</div>	 --%>				 
										 
<s:if test="%{routeList != null && !routeList.isEmpty()}">
	<div class="form-group">
		<div class="panel-body col-md-12">
			<div class="col-md-1">
				<span class="label label-danger"> NOTE : </span>
			</div>
			<div class="col-md-10">
				<ul>
					<li>
						You can view all vehicle routes from the below.
					</li>
					<li>
						You can update the routes information by clicking on the edit button on respective route.
					</li>
				</ul>
			</div>
		</div>
	</div>
	<div id="routesStudsCnt"></div>
<div class="spaceDiv"></div>
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th>
					Route Name
				</th>
				<th>
					Departure Time
				</th>
				<th>
					Arrival Time
				</th>
				<th>
					Boarding Points
				</th>
				<th>
					Current Students Count
				</th>
				<th>
					Overall Students Count
				</th>
				<s:if test='%{#session.previousYear == "N"}'>
					<th>
						Edit
					</th>
					<th>
						Delete
					</th>
				</s:if>
		</thead>
		<tbody>
			<s:iterator value="routeList">
				<tr>
					<td>
						<s:property value="routeName" />
					</td>
					<td>
						<s:property value="routePointStartTime" />
					</td>
					<td>
						<s:property value="routePointEndTime" />
					</td>
					<td>
						<ul class="tooltipDiv">
							<li>
								<a href="#">View</a>
								<ul class="tooltipSubDiv">
									<div class="popover bottom " style="display: none;">
										<div class="arrow"></div>
										<h3 class="popover-title">
											View Boarding Points
										</h3>
										<div class="popover-content">
											<s:if
												test="%{routeBoardingPointList != null && !routeBoardingPointList.isEmpty()}">
												<s:iterator value="routeBoardingPointList">
													<li>
														<s:property value="boardingPointName" />
														&nbsp;
													</li>
												</s:iterator>
											</s:if>
											<s:else>
												<li>
													Boarding points not assigned
												</li>
											</s:else>
										</div>
									</div>
								</ul>
							</li>
						</ul>
					</td>
					<td>
						<ul class="tooltipDiv">
							<li>
								<s:if test="routeCapacity > 0">
									<a href="#" class="routeCapacityCount"><s:property value="routeCapacity" /> </a>
									<ul class="tooltipSubDiv">
										<div class="popover bottom " style="display: none;">
											<div class="arrow"></div>
											<h3 class="popover-title">
												Route Capacity
											</h3>
											<div class="popover-content">
												<s:if
													test="%{routeWiseStudents != null && !routeWiseStudents.isEmpty()}">
													<s:iterator value="routeWiseStudents">
														<li>
															<s:property value="boardingPointName" />
															:
															<s:property value="boardingPointWiseStudsCount" />
														</li>
													</s:iterator>
												</s:if>
												<s:else>
													<li>
														Boarding points not assigned
													</li>
												</s:else>
											</div>
										</div>
									</ul>
								</s:if>
								<s:else>
								<a href="#" class="zeroRouteValues"><s:property value="routeCapacity" /></a>
								</s:else>
							</li>
						</ul>
					</td>
					<td>
						<ul class="tooltipDiv">
							<li>
								<s:if test="overAllCount > 0">
									<a href="#" class="overAllRouteCapacityCount"><s:property value="overAllCount" /> </a>
								</s:if>
								<s:else>
								<a href="#" class="OverAllZeroRouteValues"><s:property value="overAllCount" /></a>
								</s:else>
							</li>
						</ul>
					</td>
					<s:if test='%{#session.previousYear == "N"}'>
						<td>
							<s:url id="doEditRoute" action="ajaxDoAddRoutes"
								includeParams="all" escapeAmp="false" namespace="/admin">
								<s:param name="tempId" value="%{id}" />
							</s:url>
							<sj:a href="%{doEditRoute}" indicator="indicator"
								targets="routeContent" cssClass="btn btn-xs purple" title="Edit">
								<i class="fa fa-edit"></i>Edit
							</sj:a>
						</td>
						<td>
							<s:url id="removeRoute" action="ajaxGetStudentsCountByRoute"
								escapeAmp="false" namespace="/admin">
								<s:param name="route.id" value="%{id}"></s:param>
								<s:param name="vehicleId" value="%{vehicleId}"></s:param>
							</s:url>
							<s:div cssClass="btn btn-xs red" id='%{removeRoute}'
								onclick="javascript:confirmDeleteRoute(this);" theme="simple"
								title="Delete this group">
								<i class="fa fa-times"></i>Delete</s:div>
						</td>
					</s:if>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no Route.
	</div>
</s:else>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Manage Route");
	var totalCapacity=0;
	$('a.overAllRouteCapacityCount').each(function(){
		 totalCapacity+=Number($(this).html());
	});
	$('div#routesStudsCnt').html("<label> Total students assigned to routes : "+totalCapacity);
	$('a.OverAllZeroRouteValues').each(function(){
		if($(this).html()=="")
		 $(this).html('0');
	});
	TableAdvanced.init();
	});
function confirmDeleteRoute(event) {
	thishref = $(event).attr('id');
	var routeId = thishref.split("=");
	if ($(event).next('.question').length <= 0) {
		$(event)
				.after(
						'<div class="question">Are You Sure?<br/> <span class="yes">Yes</span><span class="cancel">Cancel</span></div>');
	}
	$(event).next('.question').animate( {
		opacity : 1
	}, 300);
	$('.yes').unbind('click');
	$('.yes')
			.bind(
					'click',
					function() {
						var prdDiv = $(this).parents('.question');
						prdDiv.html('Processing...');
						$
								.ajax( {
									url : thishref,
									cache : false,
									dataType : 'json',
									success : function(response) {
										var routeDetails = response.studActivities;
										if (routeDetails > 0) {
											prdDiv.remove();
											$('#driverAccount').html(
													routeDetails);
											$('#alertMsgDivId').html('<div class="alert alert-danger"><button data-dismiss="alert" class="close"></button><strong>students are assigned to the selected route so you cannot remove this route. <a style="cursor:pointer" class="title" id="clickHereLinkId" onclick="return assignStudentsdFun();"> Click here</a> to reassign students to another route</strong></div>');
											//alert('This route has associated with one or more vehicles.\n Please unassign vehicle(s) from this route before removing this route.');
										} else {
											deleteRoute(routeId[1]);
											prdDiv.parent().parent().remove();
										}
									}
								});
					});
	$('.cancel').unbind('click');
	$('.cancel').bind('click', function() {
		$(this).parents('.question').fadeOut(300, function() {
			$(this).remove();
		});
		return false;
	});
}
function deleteRoute(routeId) {
	$.ajax( {
		url : jQuery.url.getChatURL("/admin/ajaxRemoveRoute.do?route.id="
				+ routeId),
		cache : false,
		success : function(html) {
			$('#routeContent').html(html);
		}
	});
}

/*  $('#clickHereLinkId').click(function() {
	alert('test');
	$('#doAssignStudents').click();
}); */


function assignStudentsdFun()
{
	   window.location.hash = "#target=AMTP.ajaxify AMTP";
		$('a#dashboard').parent('li').removeClass('start active');
		$('li#transDiv').addClass('open active');
		$('li#transDiv ul.sub-menu').addClass('open active');
		$('li#assignStudentsDiv a').click();
}

</script>
