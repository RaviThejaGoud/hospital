<td colspan="6">
<%@ include file="/common/taglibs.jsp"%>
			<jsp:include page="/common/messages.jsp"></jsp:include>
			<s:if test="%{studentAttendanceStatusList != null && !studentAttendanceStatusList.isEmpty()}">
				  <table class="striped" width="100%" cellpadding="1" cellspacing="1">
					<thead>
						<tr>
						 <th class="head">
								Student Name
							</th>
							<th width="25%" class="head">
								Father Name
							</th>
						    <th class="head">
								Parent Email
							</th>
							<th width="10%" class="head">
								 Contact
							</th>
						</tr>
					</thead>
					<s:iterator value="studentAttendanceStatusList">
						<tr class="loaded" > 
						    <td>
								<s:property value="firstName"/>-<s:property value="lastName"/>
							</td>
							<td>
								<s:property value="fatherName" />
							</td>
							<td>
								<s:property value="parentEmail"/>
							</td>
							<td>
								<s:property value="phoneNumber" />
							</td>
						</tr>
						</s:iterator>
				</table>
		</s:if>
		<s:else>
		There is no attendance for student.Please create the attendance.
		</s:else>
		</td>