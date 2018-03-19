<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{eventDetailsList != null && !eventDetailsList.isEmpty()}">
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
	<s:iterator value="eventDetailsList">
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
  No events
 </s:else>
	
