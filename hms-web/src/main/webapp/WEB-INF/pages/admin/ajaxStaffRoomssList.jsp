<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<%@ include file="/common/messages.jsp"%>
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th>
					No Of Rooms For Headmaster
				</th>
				<th>
					No Of Rooms For Teachers
				</th>
				<th>
					No Of Rooms For Non Teaching Staff
				</th>
				<th>
					Edit
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
					<s:property value="staffRoom.noofRoomsForHeadMasters" />
				</td>
				<td>
					<s:property value="staffRoom.noofRoomsForTeachers" />
				</td>
				<td>
					<s:property value="staffRoom.noofRoomsForNonTeachers" />
				</td>
				<td>
					<s:if test='%{#session.previousYear == "N"}'>
						<s:url id="editStaffRoom" namespace="/admin"
							action="ajaxDoAddStaffRoomDetails" includeParams="all"
							escapeAmp="false">
							<s:param name="anyTitle" value="staffRoom.id" />
						</s:url> 
						<sj:a href="%{editStaffRoom}" targets="changeSchoolInfoContent"
							cssClass="btn btn-xs purple" title="Edit" indicator="indicator">
							<i class="fa fa-edit"></i>Edit
						</sj:a>
					</s:if>
				</td>
			</tr>
		</tbody>
	</table>