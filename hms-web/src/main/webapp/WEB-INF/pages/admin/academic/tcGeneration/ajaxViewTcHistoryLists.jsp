<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{objectList != null && !objectList.isEmpty()}">
	<table
		class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
		<thead>
			<tr>
				<th>
					Admission Number
				</th>
				<th>
					Name
				</th>
				<th>
					Class Name
				</th>
				<th>
					View Tc History
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="objectList">
				<tr>
					<td>
						<s:property value="admissionNumber"/>
					</td>
					<td>
						<s:property value="personName" />
					</td>
					<td>
						<s:property value="className" />
					</td>
					<td>
						<ul class="tooltipDiv">
							<li>
								<a href="#">View(<span id="historyCount"></span>)</a>
								<ul class="tooltipSubDiv">
									<div class="popover bottom " style="display: none;">
										<div class="arrow"></div>
										<h3 class="popover-title">
											Generated Date -  Generated By
										</h3>
										<div class="popover-content">
											<s:if
												test="%{tempList != null && !tempList.isEmpty()}">
												<s:set var="laccountId" value="accountId" />
												<s:iterator value="tempList">
												<s:if test="%{accountId == #laccountId}">
													<li class="countList">
													<b><s:property value="tcIssuedDate" /></b>
													-
													<b><s:property value="generatedBy" /></b>
													</li>
													</s:if>
												</s:iterator>
											</s:if>
										</div>
									</div>
								</ul>
							</li>
						</ul>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no Tc Histories.
	</div>
</s:else>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/onload.js">
</script>
<script language="JavaScript" type="text/javascript">
TableAdvanced.init();
var totalCount=0;
    $('ul.tooltipDiv').each(function(){
		 totalCount=$(this).find('li.countList').size();
		 $(this).find("span#historyCount").html(totalCount);
	});
</script>
