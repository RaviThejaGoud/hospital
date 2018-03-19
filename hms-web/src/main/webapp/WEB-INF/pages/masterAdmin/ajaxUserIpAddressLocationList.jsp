<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{objectList != null && !objectList.isEmpty()}">
	<table class="table table-striped table-bordered table-hover table-full-width" id="sample_5">
		<thead>
			<tr>
				<th>	
					Login Date
				</th>
				<th style="white-space:normal">	
					Login Time
				</th>
				<th style="white-space:normal">	
					No. of Logins
				</th>
				<th style="white-space:normal">
					User Details
				</th>
				<th style="white-space:normal">
					Customer Details
				</th>
				<th>	
					IP Address
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="objectList">
				<tr>
					<td>
						<s:property value="lastAccessDateStr" />
					</td>
					<td style="white-space:normal">
						<s:property value="loginTime" />
					</td>
					<td style="white-space:normal">
						<s:property value="loginCount" />
					</td>
					<td style="white-space:normal">
						<s:property value="userId" /> / <s:property value="roleName" /> / <s:property value="username" /> / <s:property value="fullName" />
						<s:if test='%{roleName == "ROLE_STUDENT"}'>
							/ <s:property value="studentId" /> / <s:property value="className" />-<s:property value="section" /> / <s:property value="studyClassId" />
						</s:if>
					</td>
					<td style="white-space:normal">
						<s:property value="custId" /> / <s:property value="organization" /> / <s:property value="custEmail" /> / <s:property value="custFullName" /> / <s:property value="custAddress" /> 
					</td>
					<td>
						<a href="http://whatismyipaddress.com/ip/<s:property value="ipAddress"/>" target="_blank"><s:property value="ipAddress" /></a>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no records.
	</div>
</s:else>
<script type="text/javascript">
	TableAdvanced.init();
</script>



