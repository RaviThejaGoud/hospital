<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{objectList != null && !objectList.isEmpty()}">
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th>
					Book Name
				</th>
				<th>
					Edition
				</th>
				<th>
					Author Name
				</th>
				<th>
					Publisher
				</th>
				<th>
					Subject
				</th>
				<th>
					Label Code
				</th>
				<th>
					Block & Rack Details
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="objectList">
				<tr>
					<td>
						<s:property value="bookName" />
					</td>
					<td>
						<s:property value="bookEdition" />
					</td>
					<td>
						<s:property value="author" />
					</td>
					<td>
						<s:property value="publisher" />
					</td>
					<td>
						<s:if test="%{subjectName != null}">
							<s:property value="subjectName" /> &nbsp;
						</s:if>
						<s:else>
							<s:property value="otherSubjects" /> &nbsp;
						</s:else>
					</td>
					<td>
						<s:property value="lableCode" />
					</td>
					<td>
						<s:if test='%{blockName!="" && blockName!=null}'>
							<s:property value="blockName" />
						</s:if>
						<s:if test='%{rackName!="" && rackName!=null}'>
										 & <s:property value="rackName" />
						</s:if>
						<s:else>
							 No Rack
						</s:else>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no search results for reading books.
	</div>
</s:else>
<script  type="text/javascript">
var BooksVar ="<s:property value="objectList.size()"/>";
if(BooksVar > 0){
	TableAdvanced.init();
}
changePageTitle('Reading Books'); 
</script>