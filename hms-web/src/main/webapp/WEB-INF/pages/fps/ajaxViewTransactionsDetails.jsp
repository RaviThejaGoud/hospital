<%@ include file="/common/taglibs.jsp"%>
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th>
					Transaction Name
				</th>
				<th>
					Issue Date
				</th>
				<th>
					Mobile Number
				</th>
				<th>
					Shop No
				</th>
				<th>
					Family Number
				</th>
				<th>
					Total Price
				</th>
				<th>
					SMS Pin Number
				</th>
				
			</tr>
		</thead>
		<tbody>
			<s:iterator value="objectList">
				<tr>
					<td>
						<s:property value="name" />
					</td>
					<td>
						<s:property value="subjectNumber" />
					</td>
					<td>
						<s:property value="subjectNumber" />
					</td>
					<td>
						<s:property value="subjectNumber" />
					</td>
					<td>
						<s:property value="subjectNumber" />
					</td>
					<td>
						<s:property value="subjectNumber" />
					</td><td>
						<s:property value="subjectNumber" />
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
<script type="text/javascript">
	TableAdvanced.init();
</script>
