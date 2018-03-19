<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<jsp:include page="/common/messages.jsp"></jsp:include>

<!--<table class="striped" width="100%" style="margin-bottom:0;" cellpadding="1" cellspacing="1">
  <tr>
    <th>Type</th>
    <th>Total</th>
  </tr>
  <tr>
    <td>Sick</td>
    <td><s:property value="sickLeave" /></td>
  </tr>
  <tr>
    <td>Casual</td>
    <td><s:property value="casualLeave" /></td>
  </tr>
  <tr>
    <td>Total</td>
    <td><s:property value="anyId" /> </td>
  </tr>
</table>

<div>
&nbsp;<br/>
</div>-->

<div >
	<b>Status:</b>
</div>
<br/>
<div class="pendingLeaves" style="width: 50px;">
	<h6><a href="#">Pending(<s:property value="leavesList.size()" />):</a></h6>
</div>
<div class="pendingLeavesBody">
<s:if test="%{leavesList != null && !leavesList.isEmpty()}">
	<div style="padding-top: 1px">
		<table class="striped" width="100%" style="margin-bottom:0;" cellpadding="1" cellspacing="1" id="results">
				<thead>
					<tr>
					
					<th width="22%" class="head">
						Type
					</th>
					<th width="20%" class="head">
						Roll No
					</th>
					<th width="20%" class="head">
						Start Date
					</th>
					<th width="20%" class="head">
						End Date
					</th> 
					<th width="20%" class="head">
						Total Days
					</th>
					<th width="10%" class="head">
						Edit
					</th>
				</tr>
				</thead>
				</table>
			<div id="resultsPage">
			<s:iterator value="leavesList">
			<table class="striped" width="100%" style="margin-bottom:0;" cellpadding="1" cellspacing="1">
				<tr class='loaded'>
						
							<td width="22%" class="head">
							<s:url id="removeGroup" action="ajaxDeleteLeave"
								includeParams="all" escapeAmp="false" namespace="/parent">
								<s:param name="id" value="id"></s:param>
							</s:url>
							<s:div cssClass="close emsRemove" id='%{removeGroup}'
								theme="simple" title="Delete this group"></s:div>
							<s:property value="leaveStatusDesc" />
						</td>
						<td width="20%" class="head">
							<s:property value="childRollNo" />
						</td>
						<td width="20%" class="head">
							<s:property value="startDateStr" />
						</td>
						<td width="20%" class="head">
							<s:property value="endDateStr" />
						</td>
						<td width="20%" class="head">
							<s:property value="days" />
						</td>
						
				
					<td  width="10%" class="head">
						<s:url id="editGroup" action="ajaxDoEditleaveReport" includeParams="all"
							escapeAmp="false">
							<s:param name="id" value="{id}" />
							<s:param name="childAccountId" value="{childAccountId}"></s:param>
						</s:url>
						<sj:a href="%{editGroup}" targets="applyLeave"
							indicator="indicator" button="false" buttonIcon="ui-icon-pencil"  cssClass="normalEdit" title="Edit">
						</sj:a>
					</td>
				</tr>
				<tr id="addLeader<s:property value='id' />" style="display: none;"
					class='load'>
				</tr>
				</table>
			</s:iterator>
			</div>
	
	</div>
</s:if>
<s:else>
<br/>
	Currently there are no Pending Leaves.
</s:else>
</div>
<br/>


<div class="approvedLeaves" style="width: 50px;">
	<h6><a href="#">Approval(<s:property value="approvedLeavesList.size" />):</a></h6>
</div>
<div class="approvedLeavesBody">
	<s:if test="%{approvedLeavesList != null && !approvedLeavesList.isEmpty()}">
	
		<div style="padding-top: 1px">
			<table class="striped" width="100%" style="margin-bottom:0;" cellpadding="1" cellspacing="1" id="results">
				<thead>
					<tr>
					
					<th width="4%" class="head">
						Type
					</th>
					<th width="4%" class="head">
						Roll No
					</th>
					<th width="5%" class="head">
						Start Date
					</th>
					<th width="5%" class="head">
						End Date
					</th> 
					<th width="5%" class="head">
						Total Days
					</th>
				</tr>
				</thead>
				</table>
				<div id="resultsPage">
				<s:iterator value="approvedLeavesList">
				<table class="striped" width="100%" style="margin-bottom:0;" cellpadding="1" cellspacing="1"  id="results">
					<tr class='loaded'>
						<td style="width:85px;" class="head">
							<s:url id="removeGroup" action="ajaxDeleteLeave"
								includeParams="all" escapeAmp="false" namespace="/parent">
								<s:param name="id" value="id"></s:param>
							</s:url>
							<s:div cssClass="close emsRemove" id='%{removeGroup}'
								theme="simple" title="Delete this group"></s:div>
							<s:property value="subject" />
							<s:property value="leaveStatusDesc" />
						</td>
						<!--<td width="18%">
							<s:property value="leaveStatusDesc" />
						</td>
						-->
						<td style="width:110px;" class="head">
							<s:property value="childRollNo" />
						</td>
						<td style="width:100px;" class="head">
							<s:property value="startDateStr" />
						</td>
						<td style="width:100px;" class="head">
							<s:property value="endDateStr" />
						</td>
						<td style="width:100px;" class="head">
							<s:property value="days" />
						</td>
						<!--<td style="padding-left: 5px;">
							<s:url id="editGroup" action="ajaxDoEditGroup" includeParams="all"
								escapeAmp="false">
								<s:param name="id" value="{id}" />
							</s:url>
							<sj:a href="%{editGroup}" targets="applyLeave"
								indicator="indicator" button="false" buttonIcon="ui-icon-pencil">
								<a href="#" title="Edit" class="normalEdit"></a>
							</sj:a>
						</td>
					-->
					</tr>
					<tr id="addLeader<s:property value='id' />" style="display: none;"
						class='load'>
					</tr>
					</table>
				</s:iterator>
				</div>
		
		</div>
	</s:if>
	<s:else>
	<br/>
		Currently there are no Apporoved Leaves.
	</s:else>
</div>
<br/>
<div class="rejectedLeaves" style="width: 50px;">
	<h6><a href="#">Rejected(<s:property value="rejectedLeavesList.size" />):</a></h6>
</div>
<div class="rejectedLeavesBody">
	<s:if test="%{rejectedLeavesList != null && !rejectedLeavesList.isEmpty()}">
	
		<div style="padding-top: 1px">
			<table class="striped" width="100%" style="margin-bottom:0;" cellpadding="1" cellspacing="1" id="results">
				<thead>
					<tr>
					
					<th width="4%" class="head">
						Type
					</th>
					<th width="4%" class="head">
						Roll No
					</th>
					<th width="5%" class="head">
						Start Date
					</th>
					<th width="5%" class="head">
						End Date
					</th> 
					<th width="5%" class="head">
						Total Days
					</th>
				</tr>
				</thead>
				</table>
				<div id="resultsPage">
				<s:iterator value="rejectedLeavesList">
				<table class="striped" width="100%" style="margin-bottom:0;" cellpadding="1" cellspacing="1">
					<tr class='loaded'>
						
							<td style="width:85px;" class="head">
							<s:url id="removeGroup" action="ajaxDeleteLeave"
								includeParams="all" escapeAmp="false" namespace="/parent">
								<s:param name="id" value="id"></s:param>
							</s:url>
							<s:div cssClass="close emsRemove" id='%{removeGroup}'
								theme="simple" title="Delete this group"></s:div>
							<s:property value="leaveStatusDesc" />
						</td>
						<td style="width:110px;" class="head">
							<s:property value="childRollNo" />
						</td>
						<td width="18%">
						<s:property value="startDateStr" />
					</td>
					<td width="22%">
						<s:property value="endDateStr" />
					</td>
					<td width="15%">
						<s:property value="days" />
					</td>
					</tr>
					<tr id="addLeader<s:property value='id' />" style="display: none;"
						class='load'>
					</tr>
					</table>
				</s:iterator>
				</div>
		
		</div>
	</s:if>
	<s:else>
	<br/>
		Currently there are no Rejected Leaves.
	</s:else>
</div>
<style type="text/css">
.active
{
color:#0033CC;
text-decoration:none;
}
.inactive
{
font-weight: bold;
text-decoration: underline; 
cursor:default;
}
.paginator
{
text-align: center;
color: #FFF;
}
</style>
<script language="JavaScript" type="text/javascript">

$(document).ready(function() {

        $(".pendingLeaves").click(function()

        {

          $(".pendingLeavesBody").slideToggle(600);

        });
   $(".pendingLeavesBody").hide();

  });

$(document).ready(function() {

       $(".approvedLeaves").click(function()

       {

         $(".approvedLeavesBody").slideToggle(600);

       });
  $(".approvedLeavesBody").hide();

 });
 $(document).ready(function() {

       $(".rejectedLeaves").click(function()

       {

         $(".rejectedLeavesBody").slideToggle(600);

       });
  $(".approvedLeavesBody").hide();

 });

</script>
