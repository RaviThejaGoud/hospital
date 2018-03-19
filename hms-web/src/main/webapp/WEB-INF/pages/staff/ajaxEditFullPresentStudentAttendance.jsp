<%@ include file="/common/taglibs.jsp"%>
<td colspan="6">
	 <s:if test="%{attendanceList != null && !attendanceList.isEmpty()}">
			  <s:form id="editStudentAttendance" action="ajaxUpdateAttendance" theme="css_xhtml" name="myform" method="post">
			  <s:hidden name="selectedId"></s:hidden>
			  <s:hidden name="section"></s:hidden>
			  <s:hidden name="selectedDate"></s:hidden>
			  <s:hidden name="id" value="%{anyId}"></s:hidden>
				  <table class="striped" width="100%" cellpadding="1" cellspacing="1" id="results">
					<thead>
						<tr>
						   <th width="5%" class="head">
								S.No
							</th>
							<th width="25%" class="head">
								Roll Number
							</th>
							<th class="head">
								Student Name
							</th>
							<th class="head">
							Status:(Absent-Uncheck)
								<!--<input type="button" name="CheckAll" value="Check" onClick="checkAll(document.myform.chkBoxSelectedIds)">
								<input type="button" name="UnCheckAll" value="Uncheck" onClick="uncheckAll(document.myform.chkBoxSelectedIds)">
							--></th>
							
						</tr>
					</thead>
					<%int i = 0; %>
					<s:iterator value="attendanceList">
						<tr class="loaded" > 
							<td>
								<%i++; %><%=i %>
							</td>
							<td>
								<s:property value="rollNumber" />
							</td>
							<td>
							    <s:property value="firstName" />
							</td>
							<td>
							<s:property value="%{anyString}"/>
							 <s:if test='%{anyString == "A" || anyString == "L"}'>
							<input type="checkbox" name="chkBoxSelectedIds" value="<s:property value="id" />"  />
							</s:if>
							 <s:else>
							<input type="checkbox" name="chkBoxSelectedIds" value="<s:property value="id" />" checked="checked"/>
							</s:else>
							</td>
						</tr>
					 </s:iterator>
					 </table> 
					<div class="type-button">
						<sj:submit   targets="studentAttendanceResults1"  cssClass="submit small" value="Submit"
						indicator="indicator"
						 onClickTopics="updateStudentAttendance"/>
				        <s:url id="updatingStudentAttendance" action="ajaxCancelAttendance"
							includeParams="all" escapeAmp="false">
						</s:url>
						<s:param  name="id" value="%{anyId}"></s:param>
						<sj:a href="%{updatingStudentAttendance}" cssClass="cancelButton"
							 indicator="indicator"
							targets="studentAttendanceResults"> Cancel
						</sj:a>
				  </div>
			</s:form>
		  </s:if>
		<s:else>
	Currently there are no Students
</s:else>
</td>
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

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/scripts/subscription/paginator.js"></script>
	<script type="text/javascript">
	 $(function(){ $("#resultsPage").pagination(); });
	</script>

<script type="text/javascript">
function checkAll(field)
{
for (i = 0; i < field.length; i++)
	field[i].checked = true ;
}

function uncheckAll(field)
{
for (i = 0; i < field.length; i++)
	field[i].checked = false ;
}
  
 	 $.subscribe('updateStudentAttendance', function(event, data) {
         if ($('#editStudentAttendance').valid())
         var catId = document.getElementsByName("chkBoxSelectedIds");
         var j=0;
         var k=0;
         var q=catId.length;
         for (i = 0; i < catId.length; i++) {
           if (catId[i].checked == true) {
               j=j+1;
           }
           else if(catId[i].checked == false){
	               k=k+1;
	        }
         }
        var url= confirm("Total No.Of Students :"+q+"\n"+"\n"+"No.Of Students Present :" +j+"\n"+"\n"+"No.Of Students Absent :" +k);
        if(url==true){
        return true;
        }
        return false;
   });
</script>