<%@ include file="/common/taglibs.jsp"%>
<div class="grid_9 omega block">
	<div class="block_head">
		<h2>
			My Subjects
		</h2>
	</div>
	<div class="block_content" id="studentContent">
		<div class="grid_8 alpha">
			<s:if test="%{classTeacherList != null && !classTeacherList.isEmpty()}">
		<table class="striped" width="400px" cellpadding="1" cellspacing="1" style="margin-bottom:0;" id="results">
			<thead>
			<tr>
				<th width="150px" class="head">
					Subject Name
				</th>
				<th width="150px" class="head">
					Teacher 
				</th>
				<th width="100px" class="head">
					Qualification
				</th>
			</tr>
			</thead>
			</table>
			<div id="tripResultsPage">
			<s:iterator value="classTeacherList">
			<table class="striped" width="100%" cellpadding="1" cellspacing="1" style="margin-bottom:0;" id="results">
				<tr class="loaded">
					<!--<td width="18%">
						<s:url id="removeTrip" action="ajaxRemoveTrip"
							escapeAmp="false">
							<s:param name="id" value="%{id}"></s:param>
							<s:param name="accountId" value="%{accountId}"></s:param>
							<s:param name="vehicleId" value="%{vehicleId}"></s:param>
						</s:url>
						<s:div cssClass="close emsRemove" id='%{removeTrip}'
							theme="simple" title="Delete this group"></s:div>
						<s:property value="routeNumber" />
					</td>
					-->
					<td width="150px">
						<s:property value="studySubject.name"/>
					</td>
					<td width="150px">
					<a href="<c:url value='/student/ajaxViewStaffDetails.do' />?id=<s:property value="staff.account.id"/>"
						class="student" title="Teacher Views"><s:property value="staff.account.person.firstName" />&nbsp;<s:property value="staff.account.person.lastName" /></a>
					</td>
					<td width="100px">
						<s:if test="%{staff.qualification1 =='UG' && staff.qualification2 == 'PG'}">
							<s:property value="staff.qualification2"/>
						</s:if>
						<s:else>
							<s:property value="staff.qualification1"/>
						</s:else>
					</td>
				</tr>
				</table>
			</s:iterator>
			</div>
		</s:if>
		<s:else>Currently there are no Subjects.</s:else>
			<!--<table class="subjects">
				<tr>
					<th style="width: 150px">
						Subject Name
					</th>
					<th style="width: 150px">
						Teacher Details
					</th>
				</tr>
				<s:if test="%{classTeacherList != null && !classTeacherList.isEmpty()}">
				<s:iterator value="classTeacherList">
				<tr>
					<td class="first">
						<s:property value="studySubject.name"/>
					</td>
					<td>
						<div><img src="<c:url value='/images/ramThumb.png'/>"
							style="border: 1px solid #FF7300; vertical-align: middle" />
						<b><s:property value="staff.account.person.firstName"/></b></div>
						<div><s:property value="staff.qualification1"/>,<s:property value="staff.qualification2"/></div>
					</td>
				</tr>				
				</s:iterator>
				</s:if>				
			</table>
		--></div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
 $("a.student").fancybox({
	  		 'width'  : '45%',
	         'height' : '90%',
	         'autoScale' : false,
	         'transitionIn' : 'none',
	         'transitionOut' : 'none',
	         'autoDimensions' : false,
	         'showCloseButton' : true
	         
	 });
});
$.subscribe('doIViewStaffDetails', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});
</script>
