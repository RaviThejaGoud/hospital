<%@ include file="/common/taglibs.jsp"%>
<div class="form-body">
		<s:if test="%{objectList != null && !objectList.isEmpty()}">
			<table
				class="table table-striped table-bordered table-hover table-full-width"
				id="sample_2">
				<thead>
					<tr><th>Actual Staff </th>
						<th>Substitute Staff</th>
						<th>Period</th>
						<th>Class & Section</th>
						<th>Subject</th>
						<%-- <s:if test='%{#session.previousYear == "N"}'>
							<th>Edit</th>
						</s:if> --%>
					</tr>
				</thead>
				<tbody>
					<!--<s:set var="roleName" value=""/> -->
					<s:iterator value="objectList" status="stat">
						<tr>
							<td><s:property value="actualStaff.fullPersonName" /></td>
							<td><s:property value="substituteStaff.fullPersonName" /></td>
							<td><s:property value="periodName" /></td>
							<td><s:property value="classSection.classAndSection" /></td>
							<td><s:property value="subject.name" /></td>
							<%-- <s:if test='%{#session.previousYear == "N"}'>
								<td><s:url id="editStaff" action="ajaxDoGetStaffByRole"
										includeParams="all" escapeAmp="false" namespace="/staff">
										<s:param name="tempString"
											value="%{objectList[#stat.count-1][6]}" />
										<s:param name="tempId" value="%{objectList[#stat.count-1][0]}" />
									</s:url> <sj:a href="%{editStaff}" targets="staffsContent"
										cssClass="btn btn-xs purple" title="Edit">
										<i class="fa fa-edit"></i>Edit
									</sj:a></td>
							</s:if> --%>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</s:if>
		<s:else>
			<div class="alert alert-info">Currently there are no substitution staff.</div>
		</s:else>
</div>
<div id="inactiveStaffDiv"></div>
<div id="showStaffProfileDiv"></div>
<script type="text/javascript">
	$(document).ready(function(){
	TableAdvanced.init();
	})

 	
</script>