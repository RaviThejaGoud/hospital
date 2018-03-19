<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Manage Hostel 
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<li>
							<s:url id="doAddHostel" action="ajaxDoAddHostelDetails"
								includeParams="all" escapeAmp="false" namespace="/admin">
								<s:param name="tempId" value="" />
							</s:url>
							<s:if test='%{#session.previousYear == "N"}'>
								<sj:a href="%{doAddHostel}" indicator="indicator"
									targets="hostelDiv" data-toggle="tab">Add Hostel</sj:a>
							</s:if>
						</li>
						<li class="active">
							<s:url id="hostelDetails" action="ajaxViewHostelDetails"
								namespace="/admin">
							</s:url>
							<sj:a id="hostelDetails" href="%{hostelDetails}"
								targets="mainContentDiv" data-toggle="tab">View Hostels</sj:a>
						</li>
					</ul>
					<div id="hostelDiv" class="tab-content">
						<%@ include file="/common/messages.jsp"%>
						<s:if test="%{objectList != null && !objectList.isEmpty()}">
							<table
								class="table table-striped table-bordered table-hover table-full-width"
								id="sample_2">
								<thead>
									<tr>
										<th>
											Hostel Name
										</th>
										<th>
											E-Mail Address
										</th>
										<th>
											Phone Number
										</th>
										<th>
											Mobile Number
										</th>
										<th>
											Edit
										</th>
										<th>
											Delete
										</th>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="objectList">
										<tr>
											<td>
												<s:property value="hostelName" />
											</td>
											<td>
												<s:property value="custEmail" />
											</td>
											<td>
												<s:property value="contactNumber" />
											</td>
											<td>
												<s:property value="mobileNumber" />
											</td>
											<td>
												<s:if
													test='%{(#session.academicYear == null || #session.academicYear.isEmpty()) || (#session.previousYear=="N")}'>
													<a data-toggle="modal" href="#popupHostelDetialsDiv"
														class="btn btn-xs purple"
														onclick="javascript:popupHostelDetials(<s:property value="%{id}" />);"><i
														class="fa fa-edit"></i>Edit</a>
												</s:if>
											</td>
											<td>
												<s:if
													test='%{(#session.academicYear == null || #session.academicYear.isEmpty()) || (#session.previousYear=="N")}'>
													<s:url id="removeHostel" action="ajaxDeleteHostel"
														includeParams="all" escapeAmp="false" namespace="/admin">
														<s:param name="tempId" value="id"></s:param>
													</s:url>
													<s:div cssClass="btn btn-xs red" id='%{removeHostel}'
														theme="simple"
														onclick="javascript:confirmDialogWithTarget(this,'mainContentDiv');"
														title="Delete this Hostel">
														<i class="fa fa-times"></i>Delete</s:div>
												</s:if>
											</td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
						</s:if>
						<s:else>
							<div class="alert alert-info">
								You have not created School Hostel Details,you can create hostel details by clicking on Add Hostel button.
							</div>
						</s:else>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="popupHostelDetialsDiv"></div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Hostel Settings");
	TableAdvanced.init();
});
	function popupHostelDetials(id) {
		var url = jQuery.url.getChatURL("/admin/ajaxDoAddHostelDetails.do");
		$('#popupHostelDetialsDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
				url : url,
				cache : false,
				data : "tempId=" + id,
				success : function(html) {
					$("#popupHostelDetialsDiv").html(html);
				}
			});
		}
</script>


