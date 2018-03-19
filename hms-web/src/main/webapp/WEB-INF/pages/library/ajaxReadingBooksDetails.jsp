<%@ include file="/common/taglibs.jsp"%>
<div class="row" id="studentLibraryContent">
	<div class="col-md-12 ">
		<div class="portlet-body">
			<div class="block_content" id="contentDiv">
				<div id="resultsDiv">
					<div id="readingBooksList" class="tab-content">
						<%--<jsp:include page="/WEB-INF/pages/library/readBooks/ajaxReadingBooksList.jsp"></jsp:include>
				 --%>
						<s:if test="%{objectList != null && !objectList.isEmpty()}">
							<table
								class="table table-striped table-bordered table-hover table-full-width"
								id="sample_2">
								<thead>
									<tr>
										<th>
											Acquisition Number
										</th>
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
											Available / Total Books
										</th>
										<th>
											Block & Rack Details
										</th>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="objectList" status="stat">
										<tr>
											<td>
												<s:property value="objectList[#stat.count-1][8]" />
											</td>
											<td>

												<s:property value="objectList[#stat.count-1][0]" />
											</td>
											<td>
												<s:property value="objectList[#stat.count-1][9]" />
											</td>
											<td>
												<s:property value="objectList[#stat.count-1][3]" />
											</td>
											<td>
												<s:property value="objectList[#stat.count-1][4]" />
											</td>

											<td>
												<s:if test='%{objectList[#stat.count-1][1] != null}'>
													<s:property value="objectList[#stat.count-1][1]" />
												</s:if>
												<s:else>
													<s:property value="objectList[#stat.count-1][2]" />
												</s:else>
											</td>
											<td>
												(
												<s:property value="objectList[#stat.count-1][6]" />
												&nbsp; /
												<s:property value="objectList[#stat.count-1][5]" />
												&nbsp; )
											</td>
											<td>
												<s:if test='%{objectList[#stat.count-1][10] != null}'>
													<s:property value="objectList[#stat.count-1][10]" />
												</s:if>
												<s:if test='%{objectList[#stat.count-1][11] != null}'>
													<s:property value="objectList[#stat.count-1][11]" />
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
								Currently there are no reading books.
							</div>
						</s:else>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
var BooksVar ="<s:property value="objectList.size()"/>";
if(BooksVar > 0){
	TableAdvanced.init();
}
changePageTitle("Reading Books");
});
</script>
 
