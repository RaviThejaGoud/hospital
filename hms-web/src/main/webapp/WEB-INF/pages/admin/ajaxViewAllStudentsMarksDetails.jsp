<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">Examination Results</div>
					<div class="tools">
						<a href="javascript:;" class="expand"></a>
						<a href="javascript:;" class="remove"></a>
					</div>
				</div>
				<div class="portlet-body">
					<s:if test="%{viewAllUsers.studentMarksList!= null && !viewAllUsers.studentMarksList.isEmpty()}">
						<table
							class="table table-striped table-bordered table-hover table-full-width"
							id="sample_2">
							<thead>
								<tr>
									<th>
										Exam Type
									</th>
									<th>
										Obtained / Total Marks
									</th>
									<th>
										Grade
									</th>
									<th>
										Performance
									</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="viewAllUsers.studentMarksList" status="stat">
									<tr>
										<td>
											<s:property value="viewAllUsers.studentMarksList[#stat.count-1][2]"  />
										</td>
										<td>
											<s:property value="viewAllUsers.studentMarksList[#stat.count-1][0]"  /> / <s:property value="viewAllUsers.studentMarksList[#stat.count-1][1]"  />
											
										</td>
										<td>
											<s:if test="%{viewAllUsers.grade != null}">
												<s:property value="viewAllUsers.grade"  />
											</s:if>
											<s:else>
												-
											</s:else>
										</td>
										<td>
											<s:if test="%{viewAllUsers.description != null}">
												<s:property value="viewAllUsers.description"  />
											</s:if>
											<s:else>
												NA
											</s:else>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					<div class="row">
					<%-- <div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-3">
								Rank info : 
							</label>
							<div class="form-control-static">
								<s:if test="%{viewAllUsers.studentMarksList[0][3] > 0}">
									<s:property value="(viewAllUsers.studentMarksList[0][3]" />
								</s:if>
								<s:else>
								 -
								</s:else>
							</div>
						</div>
					</div> --%>
					<%-- <div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-3">
								Performance :
							</label>
							<div class="form-control-static">
								<s:if test="%{viewAllUsers.description != null}">
									<s:property value="viewAllUsers.description"  />
								</s:if>
								<s:else>
									-
								</s:else>
							</div>
						</div>
					</div> --%>
				</div>
				</s:if>
				<s:else>
					<div class="examTabBorder">
						<div class="alert alert-info">
							Marks data not found for this student.
						</div>
					</div>
				</s:else>
			</div>
		 </div>
	</div>
</div>
				
