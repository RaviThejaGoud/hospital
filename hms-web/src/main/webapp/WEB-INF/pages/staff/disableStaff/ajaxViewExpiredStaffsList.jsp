<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<s:if test="%{objectList != null && !objectList.isEmpty()}">
	<table class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
		<thead>
			<tr>
				<th>
					Photo
				</th>
				<th>
					Staff Name
				</th>
				<th>
					Role
				</th>
				<th>
					Join Date
				</th>
				<th>
					Left Date
				</th>
				<th>
					Reason
				</th>
				<th>
					Active
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="objectList">
				<tr>
					<td>
						<s:if test="%{adjustedAttachmentFilePath != null && adjustedAttachmentFilePath != empty}">
						   <img height="50px;" width="50px" src='<c:url value="${adjustedAttachmentFilePath}"/>' border="0" />
					    </s:if>
					</td>
					<td>
					<a data-toggle="modal" href="#showStaffProfileDiv"
									onclick="javascript:showStaffInactiveProfileDetails(<s:property value="staffId" />);">
								<s:property value="personFullName" />
						</a>
					</td>
					<td>
						<s:property value="roleDescription" />
					</td>
					<td>
						<s:property value="staffDateOfJoing" />
					</td>
					<td>
						<s:property value="staffDateOfDiscontune" />
					</td>
					<td>
						<s:property value="description" />
					</td>
					<td>
						<s:if test='%{#session.previousYear == "N"}'>
							<s:url id="enableStaff" action="ajaxEnableStaffDetails"
								includeParams="all" escapeAmp="false" namespace="/staff">
								<s:param name="tempId" value="%{accountId}"></s:param>
							</s:url>
							<s:div id='%{enableStaff}' theme="simple" title="Enable Staff" cssClass="btn btn-xs green"
								onclick="javascript:confirmDialogWithTarget(this,'staffsContent');">
								 Active
							</s:div>
						</s:if>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no inactive staff.
	</div>
</s:else>
<div id="showStaffProfileDiv"></div>
<script type="text/javascript">
$(document).ready(function() {
	TableAdvanced.init();
	changePageTitle("inactive staff");
});
function showStaffInactiveProfileDetails(staffId){
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