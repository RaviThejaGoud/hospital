<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<div id="eventsContent">
	<div class="grid_12 omega">
		<div class="block_head">
			<h2>
				Expired Events
			</h2>
			<s:if test='%{(#session.academicYear == null || #session.academicYear.isEmpty()) || (#session.previousYear=="N")}'>
			<ul>
			 	<li>
				    <s:url id="urlDoAddEvent2" action="ajaxDoAddEvent" namespace="/admin"/>
					<sj:a id="doAddEvent2" href="%{urlDoAddEvent2}"
						targets="eventsContent" indicator="indicator">Create Event</sj:a>							
				</li>
			</ul>
			</s:if>
		</div>
		<div class="block_content">
			<s:if test="%{eventDetailsList != null && !eventDetailsList.isEmpty()}">
			<table class="striped" width="100%" cellpadding="1" cellspacing="1"
							style="margin-bottom: 0; border-width: 1px 1px 1px;" id="results">
				<thead>
						<tr>
							<th width="16%">
								Title
							</th>
							<th width="24%">
								Description
							</th>
							<th width="15%">
								Type
							</th>
							<th width="15%">
								Date
							</th>
							<th width="15%">
								Organizer
							</th>
							<!--<th> To </th>	-->
							
			
						</tr>
					</thead>
				 </table>
				 <div id="busResultsPage">
				<s:iterator value="eventDetailsList">
				<table class="striped" width="100%" cellpadding="1" cellspacing="1"
							style="margin-bottom: 0; border-width: 1px 1px 1px;" id="results">
					<tr class='loaded'>										
					  <td  style="width:150px">
					  <s:url id="removeEvent" action="ajaxDeleteEvent"
								escapeAmp="false" namespace="/admin">
								<s:param name="id" value="id"></s:param>
							</s:url>
							<s:div cssStyle="margin-top:-1px;" cssClass="close emsRemove" id='%{removeEvent}' theme="simple" title="Delete this Event"></s:div>
					      
					  <s:property value="title" /></td>
					  <td style="width:150px"><s:property value="message" /></td> 
					  <td style="width:100px"><s:property value="eventTypeDescription" /> </td>		  
					  <td style="width:125px"><s:property value="eventStartDateStr" /></td>
					  <td style="width:100px"><s:property value="organizerId" /> </td>
					  <!--  <td style="width:100px"><s:property value="classesString" /> </td>-->
					  <!--<td style="width: 30px">
							<s:url id="doEditAdminEvent"
								action="ajaxDoEditEvent" includeParams="all"
								escapeAmp="false">
								<s:param name="id" value="{id}" />
							</s:url>
							<sj:a href="%{doEditAdminEvent}"
								onBeforeTopics="cleanOpenRows"
								onCompleteTopics="doEditEvent" indicator="indicator"
								targets="editEvent%{id}">
								<a href="#" title="Edit" class="normalEdit"></a>
							</sj:a>
						</td>
						--></tr>
						<tr id="editEvent<s:property value='id' />"
							style="display: none;" class='load'>
					
						</tr>
					  </table>
				  </s:iterator>
				  </table>
			</s:if>
			<s:else>
			  No Expired events
			</s:else>
		</div>
	</div>
</div>
<script type="text/javascript">

changePageTitle('View Expired Events');
	$.subscribe('doEditEvent', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});
</script>
<style type="text/css">
.active {
	color: #0033CC;
	text-decoration: none;
}

.inactive {
	font-weight: bold;
	text-decoration: underline;
	cursor: default;
}

.buspaginator {
	text-align: right;
	color: #FFF;
}
</style>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/transport/buspaginator_dev.js">
</script>
<script type="text/javascript">
$(function() {
	$("#busResultsPage").buspagination();
});
</script>










	
