<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/common/jquery.autoCheck.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/rowHighlight.js"></script>
 <script Language="Javascript1.2" type="text/javascript">
          $(document).ready(function() {
			     $('#adminCalendar').addClass('on');
			});
	    </script>
	<div class="block grid_12">
	<div class="block_head">
		<h2>
			Manage Events
		</h2>
		<ul>
		    <li>
		         <s:url id="urlallViewEventsLink" action="ajaxViewAllEvents"
							includeParams="all" namespace="/admin" />
				 <sj:a  id="eventsLink2" href="%{urlallViewEventsLink}" targets="categoryContent"
							indicator="indicator">view events</sj:a>
		    </li>
		     <li>Export
			    <a href='${pageContext.request.contextPath}/calendar/ajaxICal.do?custid=<s:property value="userCustId"/>' target="_new"> ICal </a>
				|
				<a href='${pageContext.request.contextPath}/calendar/ajaxxmlres.do?custid=<s:property value="userCustId"/>' target="_new"> Xml </a>
				|
				<a href="#"> Json </a>		
			</li>
			<li>
					<a href="${pageContext.request.contextPath}/calendar/adminCalendar.do" id="categories">Calendar View</a>
			</li>
		</ul>
	
	</div>    
	<div class="block_content">
	  <s:if test="%{categories != null && !categories.isEmpty()}">
				<table  width="100%" cellpadding="1" cellspacing="1">
				 <s:iterator value="categories">
					    <tr>
							<td colspan="5" style="padding-bottom:10px;">
							<h3><b><s:property value="name" /></b>  </h3>
							</td>
						</tr>
						<tr>					
						<td> &nbsp;</td>
						 <td colspan="3" style="padding-bottom: 10px"> 
							 <s:if test="%{events != null && !events.isEmpty()}">
							 <table  class="striped">
							        <thead>
									    <tr>
									        <th> Title </th>
									        <th> Description </th>
									        <th> Type </th>
									        <th> StartDate </th>
									        <th> EndDate </th>
									    </tr>
									 </thead>
									<s:iterator value="sortedEvents">
										<tr class='loaded'>										
										  <td  style="width:150px">
										  <s:url id="removeEvent" action="ajaxDeleteEvent"
													escapeAmp="false" namespace="/admin">
													<s:param name="id" value="id"></s:param>
												</s:url>
												<s:div cssStyle="margin-top:-1px;" cssClass="close emsRemove" id='%{removeEvent}' theme="simple" title="Delete this Event"></s:div>
										  <s:property value="title" /></td>
										  <td style="width:125px"><s:property value="message" /></td> 
										  <td style="width:100px"><s:property value="recTypeDescription" /> </td>
										  <td style="width:125px"><s:property value="eventStartDateStr" /></td>
										  <td style="width:125px"><s:property value="eventEndDateStr" /></td>
										</tr>
									  </s:iterator>
									  </table>
								</s:if>
								<s:else>
								   Currently there are no events for this category.   
								 </s:else>
					    </td>
					   </tr>
					</s:iterator>
				</table>
		  </s:if>
		<s:else>
	  Currently there are no categories.
    </s:else>
  </div>
</div>
<script type="text/javascript">
    $.subscribe('removeCategory', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});
	$.subscribe('removeEvent', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});
	$(document).ready(function() {
		$('table.striped tr:odd').addClass('odd');
		$('table.striped tr:even').addClass('even');
		$('table.striped th').addClass('head');

	});
	$("tr").hover(function() {
		$(this).addClass("hover");
	}, function() {
		$(this).removeClass("hover");
	});
</script>

