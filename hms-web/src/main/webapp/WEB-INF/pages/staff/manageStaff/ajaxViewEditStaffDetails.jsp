<%@ include file="/common/taglibs.jsp"%>
<div class="form-body">
	<%@ include file="/common/messages.jsp"%>
	<s:form id="selectStudentForm" cssClass="form-horizontal"
		theme="simple">
		<s:if test="%{objectList != null && !objectList.isEmpty()}">
			<p class="text-primary">
				<b>Total Active Staff : <s:property value="objectList.size" /></b>
			</p>
			<table
				class="table table-striped table-bordered table-hover table-full-width"
				id="sample_2">
				<thead>
					<tr><th>Photo </th>
						<th>Staff Name</th>
						<th>Mobile Number</th>
						<th>Role</th>
						<s:if test='%{#session.previousYear == "N" && user.isReceptionist !="Y" }'>
							<th>Edit</th>
							<th>Inactive</th>
						</s:if>
					</tr>
				</thead>
				<tbody>
					<!--<s:set var="roleName" value=""/> -->
					<s:iterator value="objectList" status="stat">
						<!--<s:if test="%{objectList[#stat.count-1][5] != #roleName}">									
							<tr>
								<td colspan="5">
									<center>
										<strong>Role Name : <s:property value="objectList[#stat.count-1][5]" /></strong>
									</center>
								</td>
							</tr>
						</s:if>	 -->
						<tr>
							<td><s:if
									test="%{objectList[#stat.count-1][7] != null &&  objectList[#stat.count-1][7] != ''}">
									<img src='<s:property value='objectList[#stat.count-1][7]'/>?<%= (Math.random() * 10) %>'
										border="0"  style="height: 50px;width: 50px;" id="studentProfileDiv" />
								</s:if> 
								<s:else>
									<img height="50px;" width="50px"alt="" src="../images/common/photo_notAvailable.jpg">
								</s:else>
							</td>
							<td><a data-toggle="modal" href="#showStaffProfileDiv"
								onclick="javascript:showStaffProfileDetails(<s:property value="objectList[#stat.count-1][0]" />);">
									<s:property value="objectList[#stat.count-1][1]" />&nbsp;<s:property
										value="objectList[#stat.count-1][2]" />
							</a></td>
							<td><s:property value="objectList[#stat.count-1][4]" />&nbsp;
							</td>
							<td><s:property value="objectList[#stat.count-1][5]" />&nbsp;
							</td>
							<!--<s:if test='%{#session.previousYear == "N"}'>
							<td>
						<s:if test="%{adjustedAttachmentFilePath != null && adjustedAttachmentFilePath != empty}">
						   <img height="50px;" width="50px" src='<c:url value="${adjustedAttachmentFilePath}"/>' border="0" />
					    </s:if>
					    </s:if>
					 </td>-->
							<s:if test='%{#session.previousYear == "N" && user.isReceptionist !="Y"}'>
								<td><s:url id="editStaff" action="ajaxDoGetStaffByRole"
										includeParams="all" escapeAmp="false" namespace="/staff">
										<s:param name="tempString"
											value="%{objectList[#stat.count-1][6]}" />
										<s:param name="tempId" value="%{objectList[#stat.count-1][0]}" />
									</s:url> <sj:a href="%{editStaff}" targets="staffsContent"
										cssClass="btn btn-xs purple" title="Edit">
										<i class="fa fa-edit"></i>Edit
									</sj:a></td>
								<td>
								<s:if test='%{objectList[#stat.count-1][5] != "Administrator"}'>
								<a data-toggle="modal" href="#inactiveStaffDiv"
									class="btn btn-xs red"
									onclick="javascript:popupDisableStaffAccount(<s:property value="objectList[#stat.count-1][0]" />);"><i
										class="fa fa-times"></i> Inactive </a>
										</s:if>
								</td>
							</s:if>
						</tr>
						<s:set var="roleName" value="objectList[#stat.count-1][5]" />
					</s:iterator>
				</tbody>
			</table>
		</s:if>
		<s:else>
			<div class="alert alert-info">Currently there are no staff.</div>
		</s:else>
	</s:form>
</div>
<div id="inactiveStaffDiv"></div>
<div id="showStaffProfileDiv"></div>
<script type="text/javascript">
	$(document).ready(function(){
	$('').addClass('active');
	$('ul.nav-tabs li').removeClass('active');
	$('a#viewSub').parent('li').addClass('active');
	TableAdvanced.init();
	})
function popupDisableStaffAccount(staffId){
	if(isNonEmpty(staffId)){
	  var pars = "tempId=" + staffId;
        $.ajax( {
	        url : jQuery.url.getChatURL("/staff/ajaxDoDisableStaff.do"),
			cache : true,
			data : pars,
			success : function(html) {
			$("#inactiveStaffDiv").html(html);
			}
		});
	}
 	}	
	function showStaffProfileDetails(staffId){
		if(isNonEmpty(staffId)){
		  var pars = "tempId=" + staffId;
	        $.ajax( {
		        url : jQuery.url.getChatURL("/staff/ajaxShowStaffProfileDetails.do"),
				cache : true,
				data : pars,
				success : function(html) {
				$("#showStaffProfileDiv").html(html);
				// $('#'+dataDiv).html(data);
				}
			});
		}
	 	}
 	
</script>