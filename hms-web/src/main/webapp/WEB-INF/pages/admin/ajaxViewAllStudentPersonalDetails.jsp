<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{tempList != null && !tempList.isEmpty()}">
	<s:if test="%{tempList.size == 1 }">
		<jsp:include page="/WEB-INF/pages/admin/ajaxViewStudentPersonalDetails.jsp" />
	</s:if>
	<s:else>
		<table
			class="table table-striped table-bordered table-hover table-full-width"
			id="sample_2">
			<thead>
				<tr>
					<th>
						 Image
					</th>
					<th>
						 Name
					</th>
					<th>
						Class & Section
					</th>
					<th>
						Admission Number 
					</th>
					<th>
						Father Name 
					</th>
					<th>
						Mobile Number 
					</th>
					<th>
						Parent Email
					</th>
					<th>
						View
					</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="tempList">
					<tr>
						<td>
							<s:if test="%{profileImage != null && !profileImage.isEmpty()}">
								<img src='${pageContext.request.contextPath}<s:property value='profileImage'/>'
									border="0"  style="height: 50px;width: 50px;" id="studentProfileDiv" />
							</s:if> 
							<s:else>
								<img height="50px;" width="50px"alt="" src="../images/common/photo_notAvailable.jpg">
							</s:else>
						</td>
						<td>
							<s:property value="firstName" />&nbsp;<s:property value="lastName"/>
						</td>
						<td>
							<s:property value="classAndSection" />
						</td>
						<td>
							<s:if test="%{admissionNumber != null && !admissionNumber.isEmpty()}">
								<s:property value="admissionNumber" />
							</s:if>
							<s:else>
							  NA
							</s:else>
						</td>
						<td>
							<s:if
								test="%{fatherName != null}">
								<s:property value="fatherName" />
							</s:if>
							<s:else>
							   NA
							</s:else>
						</td>
						<td>
							<s:if test="%{mobileNumber != null && !mobileNumber.isEmpty()}">
								<s:property value="mobileNumber" />
							</s:if>
						 	<s:else>
							  NA
							</s:else>
						</td>
						<td>
							<s:if test="%{parentEmail != null && !parentEmail.isEmpty()}">
								<s:property value="parentEmail" />
							</s:if>
						 	<s:else>
							  NA
							</s:else>
						</td>
						<td>
							<s:url id="doviewPersonInfo" action="ajaxViewAllCommonDetails"
								includeParams="all" escapeAmp="false" namespace="/admin">
								<s:param name="tempId" value="%{accountId}" />
							</s:url>
							<sj:a href="%{doviewPersonInfo}" indicator="indicator"
								targets="viewMyPerformance" title="View" button="false"
								cssClass="btn btn-xs green">View
							</sj:a>
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</s:else>
</s:if>
