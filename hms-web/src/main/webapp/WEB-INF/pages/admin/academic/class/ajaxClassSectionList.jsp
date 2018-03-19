<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<s:if test="%{classList != null && !classList.isEmpty()}">
	<s:if test='%{#session.previousYear == "N"}'>
		<div class="panel-body col-md-8">
			<div class="col-md-1">
				<span class="label label-danger">NOTE :</span>
			</div>
			<div class="col-md-9">
				<ul>
					<li>
						You can edit class settings by accessing edit pen icon in each row
						at right side.
					</li>
					<li>
						You can remove class by clicking on delete(x) icon.
					</li>
				</ul>
			</div>
		</div>
		<div class="panel-body col-md-4">
			<p class="text-primary">
				Total Active Students :
				<b><s:property value="tempId1" />
				</b>
			</p>
			<p class="text-primary">
				Total Active Staff :
				<b><s:property value="tempId2" />
				</b>
			</p>
		</div>
	</s:if>
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th style="display: none;">
				</th>
				<th>
					Class Name
				</th>
				<th>
					Section
				</th>
				<th>
					Capacity
				</th>
				<th>
					Available Seats
				</th>
				<th>
					Class Teacher
				</th>
				<th>
					Add Section
				</th>
				<th style="text-align: center;">
					Edit
				</th>
				<s:if test='%{#session.previousYear == "N"}'>
					<th style="text-align: center;">
						Delete
					</th>
				</s:if>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="classList">
				<tr>
					<td style="display: none;">
						<s:property value="sortingOrder" />
					</td>
					<td>
						<s:property value="className" />
					</td>
					<td style="text-align: center;">
						<s:property value="noOfSections" />
					</td>
					<s:if test='%{classCapacity > 0}'>
						<td>
							<ul class="tooltipDiv">
								<li>
									<a href="#"><s:property value="classCapacity" /> </a>
									<ul class="tooltipSubDiv">
										<div class="popover bottom " style="display: none;">
											<div class="arrow"></div>
											<h3 class="popover-title">
												View Capacity
											</h3>
											<div class="popover-content">
												<s:if test="%{classSectionDetails != null && !classSectionDetails.isEmpty()}">
													<s:iterator value="classSectionDetails">
															<li>
																<s:property value="ClassAndSection" />
																:
																<s:property value="sectionCapacity" />
															</li>
													</s:iterator>
												</s:if>
											</div>
										</div>
									</ul>
								</li>
							</ul>
						</td>
					</s:if>
					<s:else>
						<td style="text-align: center;">
							<s:property value="classCapacity" />
						</td>
					</s:else>
					<s:if test='%{classSectionAvailableSeats > 0}'>
						<td>
							<s:property value="availableSeates" />
							<ul class="tooltipDiv">
								<li>
									<a href="#"><s:property value="classSectionAvailableSeats" />
									</a>
									<ul class="tooltipSubDiv">
										<div class="popover bottom " style="display: none;">
											<div class="arrow"></div>
											<h3 class="popover-title">
												(Available Seats / Filled Seats)
											</h3>
											<div class="popover-content">
												<s:if test="%{classSectionDetails != null && !classSectionDetails.isEmpty()}">
													<s:iterator value="classSectionDetails">
														<li>
															<s:property value="ClassAndSection" />
															: (
															<font color="green"><b><s:property value="availableSeats" />
															</b>
															</font> /
															<font color="red"><b><s:property value="filledSeats" />
															</b>
															</font>)
														</li>
													</s:iterator>
												</s:if>
											</div>
										</div>
									</ul>
								</li>
							</ul>
						</td>
					</s:if>
					<s:else>
						<td style="padding-left: 48px;">
							<%-- <s:property value="classSectionAvailableSeats" /> --%>
							0
						</td>
					</s:else>
					<td>
						<ul class="tooltipDiv">
							<li>
								<a href="#">View</a>
								<ul class="tooltipSubDiv">
									<div class="popover bottom " style="display: none;">
										<div class="arrow"></div>
										<h3 class="popover-title">
											View Class Teacher
										</h3>
										<div class="popover-content">
											<s:if
												test="%{classTeacher != null && !classTeacher.isEmpty()}">
												<s:iterator value="classTeacher">
													<li>
														<s:property />
													</li>
												</s:iterator>
											</s:if>
											<s:else>
												<li>
													Class Teacher not assigned
												</li>
											</s:else>
										</div>
									</div>
								</ul>
							</li>
						</ul>
					</td>
					<td style="text-align: center;">
						<s:if test='%{#session.previousYear == "N"}'>
							<s:url id="doAddAnotherSection" action="ajaxDoAddClass"
								includeParams="all" escapeAmp="false" namespace="/admin">
								<s:param name="classId" value="%{id}" />
							</s:url>
							<sj:a href="%{doAddAnotherSection}" targets="classContentDiv"
								cssClass="btn btn-xs green" title="Add Sections">
								<i class="fa fa-plus"></i>
							</sj:a>
						</s:if>
						<s:else>
						</s:else>
					</td>
					<td>
						<ul class="tooltipDiv">
							<li>
								<a href="#" class="btn btn-xs purple"> <i class="fa fa-edit"></i>Edit</a>
								<ul class="tooltipSubDiv">
									<div class="popover bottom " style="display: none;">
										<div class="arrow"></div>
										<h3 class="popover-title">
											Edit Class & Sections
										</h3>
										<div class="popover-content">
											<s:if test="%{classSectionDetails != null && !classSectionDetails.isEmpty()}">
												<s:iterator value="classSectionDetails">
														<li>
															<s:url id="doEditClassDetails"
																action="ajaxDoEditClassDetails" includeParams="all"
																escapeAmp="false" namespace="/admin">
																<s:param name="classId" value="%{classSectionId}" />
															</s:url>
															<sj:a href="%{doEditClassDetails}"
																targets="classContentDiv">
																<s:property value="ClassAndSection" />
															</sj:a>
														</li>
												</s:iterator>
											</s:if>
											<s:else>
												<li>
													No sections found.
												</li>
											</s:else>
										</div>
									</div>
								</ul>
							</li>
						</ul>
					</td>
					<s:if test='%{#session.previousYear == "N"}'>
						<td>
							<ul class="tooltipDiv">
								<li>
									<a href="#" class="btn btn-xs red"> <i class="fa fa-times"></i>
										Delete</a>
									<ul class="tooltipSubDiv">
										<div class="popover bottom " style="display: none;">
											<div class="arrow"></div>
											<h3 class="popover-title">
												Delete Class & Sections
											</h3>
											<div class="popover-content">
												<s:if test="%{classSectionDetails != null && !classSectionDetails.isEmpty()}">
													<s:set name="lsection" value="" />
													<s:iterator value="classSectionDetails">
														<li>
															<s:url id="removeClassSection"
																action="ajaxRemoveStudyClass" includeParams="all"
																escapeAmp="false" namespace="/admin">
																<s:param name="classId" value="%{classSectionId}" />
															</s:url>
															<s:div
																onclick="javascript:confirmDialogWithTarget(this,'mainContentDiv');"
																id='%{removeClassSection}' theme="simple"
																title="Delete Class & Section"
																cssStyle="width:20px;float:left;cursor:pointer;">
																<i class="fa fa-trash-o"></i>
															</s:div>
															<s:property value="ClassAndSection" />
														</li>
													</s:iterator>
												</s:if>
												<s:else>
													<li>
														No sections found.
													</li>
												</s:else>
											</div>
										</div>
									</ul>
								</li>
							</ul>
							<s:else>
							</s:else>
						</td>
					</s:if>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		<!-- You have not created any Classes. You can able to create Class &amp;
		Section through Add Class link <a href="#" onclick="addClassClick();">Click Here</a> -->
		
		You have not created any Classes. Please <a href="#" onclick="javascript:addClassClick()"> <b>Click here</b> </a> to create Class.
		
	</div>
</s:else>
  <script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/onload.js">
</script>
<script type="text/javascript">
$(document).ready(function() {
	TableAdvanced.init();
	changePageTitle("Class And Sections");
});
var refress = '<s:property value="%{tempString}"/>';
if(refress == 'R'){
	$("a#viewSub").click();
}else{
	$('#pagemessage').fadeIn(2000);
}

function addClassClick()
{
	$("a#addClass").click();
}
</script>
