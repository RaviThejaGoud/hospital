<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<div class="grid_14 commomnTabs">
	<jsp:include page="/common/messages.jsp" />
	<div class="grid_14 omega">
		<s:if test="%{academicYearId != null && academicYearId != empty}">
		     <s:url id="doAddHostelEvent" action="ajaxGetAdminCalendar"
				includeParams="all" escapeAmp="false" namespace="/hostel">
			</s:url>
			<sj:a href="%{doAddHostelEvent}" indicator="indicator"
				targets="stepEvents" button="false" cssClass="linkRight">Calendar View</sj:a>
		   
			<s:url id="doAddHostelEvent" action="ajaxDoAddHostelEvent"
				includeParams="all" escapeAmp="false" namespace="/hostel">
				<s:param name="tempId" value="0" />
			</s:url>
			<sj:a href="%{doAddHostelEvent}" indicator="indicator"
				targets="stepEvents" button="false" cssClass="linkRight">Add Events&nbsp;|&nbsp;</sj:a>
			<s:if test="%{objectList != null && !objectList.isEmpty()}">
				<h1>
					Event Details
				</h1>
				<div class="grid_14" align="right" data-target="viewHostelEvents">
					<jsp:include
						page="/WEB-INF/pages/common/ajaxViewPaginationOptions.jsp"></jsp:include>
				</div>
				<div class="grid_14 th">
					<div class="grid_3 eventNameDiv sortHeader divArrow">
						Event Name
					</div>
					<div class="grid_3 startDiv sortHeader divArrow">
						Start Date
					</div>
					<div class="grid_3 endDiv sortHeader divArrow">
						End Date
					</div>
					<div class="grid_3">
						Edit
					</div>
					<div class="grid_2">
						Delete
					</div>
				</div>
				<div id="viewHostelEvents">
					<s:iterator value="objectList">
					<div eventStartDate="<s:property value='eventStartDate' />" eventEndDate="<s:property value='eventEndDate' />" eventName="<s:property value='eventName' />" class="item">
						<div class="grid_14 row">
							<div class="grid_3">
								<a
									href="${pageContext.request.contextPath}/hostel/ajaxViewSingleHostelEvent.do?eventId=<s:property value="id"/>"
									class="viewEvent" title="View hostelEvents"><s:property
										value="eventName" /> </a>
							</div>
							<div class="grid_3">
								<s:property value="eventStartDateStr" />
							</div>

							<div class="grid_3">
								<s:property value="eventEndDateStr" />
							</div>

							<div class="grid_3">
								<s:if test='%{endDate.compareTo(toDate) > 0}'>
									<s:url id="doEditAdminEvent" action="ajaxDoAddHostelEvent"
										includeParams="all" escapeAmp="false" namespace="/hostel">
										<s:param name="tempId" value="{id}" />
									</s:url>
									<sj:a href="%{doEditAdminEvent}" indicator="indicator"
										targets="stepEvents" cssClass="normalEdit" title="Edit">
									</sj:a>
								</s:if>
								&nbsp;
							</div>
							<div class="grid_2">
								<s:if test='%{toDate.compareTo(startDate) < 0}'>
									<s:url id="removeEvent" action="ajaxDeleteHostelEvent"
										includeParams="all" escapeAmp="false" namespace="/hostel">
										<s:param name="tempId" value="id"></s:param>
									</s:url>
									<s:div cssStyle="margin-top:3px;" cssClass="close emsRemove"
										id='%{removeEvent}' theme="simple" title="Delete this Event"></s:div>
								</s:if>
								&nbsp;
							</div>
						</div>
						</div>
					</s:iterator>
				</div>
			</s:if>
			<s:else>
				<div class="grid_14 thb">
					Currently there are no events.
				</div>
			</s:else>
		</s:if>
		<s:else>
			<div class="grid_14 thb">
				Currently there are no events.
			</div>
		</s:else>
	</div>
</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/subscription/paginator_dev.js">
</script>
<script type="text/javascript">
$(document).ready(function() {
changePageTitle("Hostel Events");
	$("a.viewEvent").fancybox( {
		'width' : '43%',
		'height' : '51%',
		'autoScale' : false,
		'transitionIn' : 'none',
		'transitionOut' : 'none',
		'autoDimensions' : false,
		'showCloseButton' : true
	});
});
var rollDes=1;
$('div.eventNameDiv').click(function () {
     $('div#viewHostelEvents>div.item').sortElements(function (a, b) {return ($(a).attr('eventName').toLowerCase() > $(b).attr('eventName').toLowerCase() ? 1 : -1) * rollDes;});
    updateDirectionArrows($(this), rollDes);
    $("#viewHostelEvents").pagination();
    rollDes= rollDes* -1;
    return false;
});


var statrtDate=1;
$('div.startDiv').click(function () {
	$('div#viewHostelEvents>div.item').sortElements(function (a, b) {return ($(a).attr('eventStartDate') > $(b).attr('eventStartDate') ? 1 : -1) * statrtDate; });
	updateDirectionArrows($(this), statrtDate);
    $("#viewHostelEvents").pagination();
    statrtDate = statrtDate * -1;
    return false;
});

var endDate=1;
$('div.endDiv').click(function () {
	$('div#viewHostelEvents>div.item').sortElements(function (a, b) {return ($(a).attr('eventEndDate') > $(b).attr('eventEndDate') ? 1 : -1) * endDate; });
	updateDirectionArrows($(this), endDate);
    $("#viewHostelEvents").pagination();
    endDate = endDate * -1;
    return false;
});
</script>

