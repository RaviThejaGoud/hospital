<%@ include file="/common/taglibs.jsp"%>
<h4 class="bold pageTitle">
	Requested Books
</h4>
<s:if test="%{objectList != null && !objectList.isEmpty()}">
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_3">
		<thead>
			<tr>
				<th>
					Request No
				</th>
				<th>
					Book Name
				</th>
				<th>
					Author Name
				</th>
				<th>
					Subject
				</th>
				<th>
					Status
				</th>
				<th>
					Request Expiry Date
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="objectList">
				<tr>
					<td>
						<s:property value="bookReservationNo" />
					</td>
					<td>
						<s:property value="bookTitle.bookName" />
					</td>
					<td>
						<s:property value="bookTitle.author" />
					</td>
					<td>
						<s:if test="%{bookTitle.studySubject != null}">
							<s:property value="bookTitle.studySubject.name" />
						</s:if>
						<s:else>
							<s:property value="bookTitle.otherSubjects" />
						</s:else>
					</td>
					<td>
						<s:if test='%{status=="R"}'> 
											pending
										</s:if>
					</td>
					<td>
						<s:property value="expiryDateStr" />
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		There are no requested books by you.
	</div>
</s:else>
