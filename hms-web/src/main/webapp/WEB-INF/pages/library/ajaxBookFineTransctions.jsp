<%@ include file="/common/taglibs.jsp"%>
<div id="studentLibraryContent">
	<div class="block grid_11">
		<div class="block_head">
			<div class="header">
				Fine Details
				<ul>
					<li>
						<s:url id="fineDetails" action="ajaxDoAddFineDetails" namespace="/library"/>
						<sj:a href="%{fineDetails}" targets="studentLibraryContent" indicator="indicator">+ Fine</sj:a>
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/library/libraryHome.do" id="libraryHome">Back To Book Details</a>
					</li>
				</ul>
			</div>
		</div>
		<div class="block_content">
			<s:if test="%{objectList != null && !objectList.isEmpty()}">
				<table class="striped" width="100%" cellpadding="1" cellspacing="1"
					style="font-size: 12px;">
					<thead>
						<tr>
							<th style="width: 10%">
								Book Name
							</th>
							 
							<th style="width: 5%">
								No. of Books
							</th>
							<th style="width: 8%">
								Books.Amount
							</th>
							<th style="width: 8%">
								Total Amount
							</th>
						</tr>
					</thead>
					<s:iterator value="objectList1">
						<tr class="loaded">
							<td style="width: 10%">
								<s:property value="cityName" />
							</td>
							<td style="width: 15%">
								<s:property value="theaterName" />
							</td>
							<td style="width: 15%">
								<s:property value="movieName" />
							</td>
							<td style="width: 17%">
								<s:property value="showDate" />
							</td>
						</tr>
					</s:iterator>
				</table>
			</s:if>
			<s:else>Currently there are no transactions.</s:else>
		</div>
	</div>
</div>