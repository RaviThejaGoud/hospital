<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<s:if test="%{objectList != null && !objectList.isEmpty()}">
<table class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
		<thead>
			<tr>
				
				<th>
					Department Name
				</th>
				<th>
					Department HeadName
				</th>
				<th>
					Edit
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="objectList">
				<tr>
					<td>
						<s:property value="departmentName" />
					</td>
					<td>
						<s:property value="departmentHeadName" />
					</td>
					<td>
						<s:url id="doDepatHead" action="ajaxCreateDepartment"
							includeParams="all" escapeAmp="false" namespace="/admin">
							<s:param name="tempId" value="%{id}" />
						</s:url>
						<sj:a href="%{doDepatHead}" indicator="indicator"
							targets="contentDiv" title="Edit" button="false"
							cssClass="btn btn-xs purple"><i class="fa fa-edit"></i>Edit
							</sj:a>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">Currently there are no departments created.</div>
</s:else>
<script type="text/javascript">
$(document).ready(function() {
	TableAdvanced.init();
});
</script>