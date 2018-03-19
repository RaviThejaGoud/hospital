<%@ include file="/common/taglibs.jsp"%>
<div class="block grid_17">
	<div class="block_head">
		<h2>
			Absent Students
		</h2>
	</div>
	<div class="block_content">
		<s:if test="%{classList != null && !classList.isEmpty()}">
			<s:iterator value="classList">
			<s:set name="classNameId" value="%{id}"></s:set>
					<table style="margin: 0px;width: 100%" class="grid_11">
						<tr class="loaded">
							<td style="width: 360px;">
								<s:property value="className" />-<s:property value="section" />
							</td>
						</tr>
					</table>
					
					<s:if test="%{allStudentsList != null && !allStudentsList.isEmpty()}">
					<table class="striped grid_11" width="100%" cellpadding="1"
						cellspacing="1" style="width: 905px;">
						<thead id="notAvailableStudents<s:property value="id"/>">
							<tr>
								<th width="20%" class="head">
									Roll No
								</th>
								<th class="head">
									Student Name
								</th>
								<th class="head" width="20%">
									Father Name
								</th>
								 <th class="head" width="20%">
									Mobile Number
								</th>
								 <th class="head" width="20%">
									Parent Email
								</th>
							</tr>
						</thead>
						<s:iterator value="allStudentsList">
						<s:if test='%{classId == #classNameId}'>
						<tr class="loaded">
							<td width="25%">
								<s:property value="rollNumber" />
							</td>
							<td width="20%">
								<s:property value="firstName" />
							</td>
							<td width="20%">
								<s:property value="fatherName" />
							</td>
							<td width="20%">
								<s:property value="mobileNumber" />
							</td>
							<td width="20%">
								<s:property value="parentEmail" />
							</td>
						</tr>
						</s:if>
						</s:iterator>
						<s:else>
						 <script type="text/javascript">
						   $("#notAvailableStudents"+'<s:property value="id"/>').text("");
						  </script>
						   Currently there are no absent students.
						</s:else>
						</table>
						</s:if>
						</s:iterator>
						</s:if>
	            </div> 
	          </div>
<script type="text/javascript">
	$.subscribe('doInitClassDetails', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});
	$.subscribe('doInitEntranceDetails', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});
</script>
