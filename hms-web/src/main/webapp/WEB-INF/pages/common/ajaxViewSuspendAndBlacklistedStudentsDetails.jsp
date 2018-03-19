<%@ include file="/common/taglibs.jsp"%>
<div id="changeStudenstStatusDiv"></div>
<jsp:include page="/common/messages.jsp" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<s:if test="%{suspendBlackstudentList != null && !suspendBlackstudentList.isEmpty()}">
	<div class="panel-body col-md-12">
		<div class="col-md-1">
			<span class="label label-danger"> NOTE : </span>
		</div>
		<div class="col-md-10">
			You cannot find edit and delete options for past records
		</div>
	</div>
	<table
		class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
		<thead>
			<tr>
				<th>
					Status
				</th>
				<th>
					Start Date
				</th>
				<th>
					End Date
				</th>
				<th>
					Edit
				</th>
				<s:if test='%{user.isSchoolAdmin=="Y"}'>
					<th>
						Delete 
					</th>
				</s:if>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="suspendBlackstudentList">
				<tr>
					<td>
						<s:if test='%{status=="S"}'>
							Suspend
						</s:if>
						<s:else>
							Blacklist
						</s:else>
					</td>
					<td>
						<s:property value="blackedOrSuspendFromDateStr" />
					</td>
					<td>
						<s:property value="blackedOrSuspendToDateStr" />
					</td>
					<td>
					<s:if test='%{todayDate.compareTo(blackedOrSuspendToDateStr) <= 0}'>
						<a data-toggle="modal" href="#changeStudenstStatusDiv"
							class="btn btn-xs purple"
							onclick="javascript:disableStudentAccount1(<s:property value="tempId" />,<s:property value="id" />,'<s:property value="status" />');"><i class="fa fa-edit"></i>Edit
						</a>
					</s:if>
					<s:else>
						&nbsp;&nbsp; -
					</s:else>	
					</td>
					<s:if test='%{user.isSchoolAdmin=="Y"}'>
						<td>
						<s:if test='%{todayDate.compareTo(blackedOrSuspendFromDateStr) <= 0}'>
							<s:if test='%{#session.previousYear=="N"}'>
								<s:url id="removeStudentStatus" action="ajaxDeleteSuspendAndBlackListedStudent"
									includeParams="all" escapeAmp="false" namespace="/student">
									<s:param name="suspendAndBlacklistStudents.id" value="id"></s:param>
									<s:param name="tempId" value="tempId"></s:param>
									<s:param name="todayDate" value="todayDate"></s:param>
									<s:param name="plTitle" value="blackedOrSuspendFromDateStr" ></s:param>
									<s:param name="plSubTopic" value="blackedOrSuspendToDateStr" ></s:param>					
								</s:url>
								<s:div cssClass="btn btn-xs red" id='%{removeStudentStatus}'
									theme="simple" title="Delete this Alert"
									onclick="javascript:confirmDialogWithTarget(this,'studentEditContentDiv');">
									<i class="fa fa-times"></i>Delete</s:div>
							</s:if>
							</s:if>
							<s:else>
								&nbsp;&nbsp; -
							</s:else>
						</td>
					</s:if>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		Student status is active.
	</div>
</s:else>
<script type="text/javascript">
TableAdvanced.init();
function disableStudentAccount1(studentId,id,status){
	if(isNonEmpty(studentId) && isNonEmpty(id)){
	  var pars = "tempId=" + studentId+"&suspendAndBlacklistStudents.id="+id+"&student.status="+status;
        $.ajax( {
	        url : jQuery.url.getChatURL("/student/ajaxEditStudentsStatus.do"),
			cache : true,
			data : pars,
			success : function(html) {
				$("#changeStudenstStatusDiv").html(html);
			}
		});
	}
 	}
</script>
