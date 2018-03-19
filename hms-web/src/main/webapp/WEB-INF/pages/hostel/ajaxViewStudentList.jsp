<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<s:if test="%{studentsList != null && !studentsList.isEmpty()}">
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th>
					Roll Number
				</th>
				<th>
					Class
				</th>
				<th>
					Student Name
				</th>
				<th>
					Hostel Name - Room Number
				</th>
				<th>
					Action
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="studentsList">
				<tr>
					<td>
						<s:property value="rollNumber" />
					</td>
					<td>
						<s:property value="className" />
						&nbsp;
						<s:property value="section" />
					</td>
					<td>
						<s:property value="firstName" />
						&nbsp;
						<s:property value="lastName" />
					</td>
					<s:if test='roomId!=0'>
						<td>
							<s:property value="hostelName" />
							- &nbsp;
							<s:property value="roomName" />
						</td>
						<td>
							<a data-toggle="modal" href="#popupHostelDiv"
								class="btn btn-xs green"
								onclick="javascript:PopupEditHostel(<s:property value="%{accountId}" />,<s:property value="%{studentId}" />);"><i
								class="fa fa-edit"></i>Edit to Details </a>
						</td>
					</s:if>
					<s:else>
						<td>
							<b> --- </b>
						</td>
						<td>
							<a data-toggle="modal" href="#popupHostelDiv"
								class="btn btn-xs purple"
								onclick="javascript:PopupEditHostel(<s:property value="%{accountId}" />,<s:property value="%{studentId}" />);"><i
								class="fa fa-edit"></i>Assign to Room </a>
						</td>
					</s:else>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:elseif test="%{staffsList != null && !staffsList.isEmpty()}">
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_3">
		<thead>
			<tr>
				<th>
					First Name
				</th>
				<th>
					Last Name
				</th>
				<th>
					RoleName
				</th>
				<th>
					Action
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="staffsList">
				<tr>
					<td>
						<s:property value="firstName" />
					</td>
					<td>
						<s:property value="lastName" />
					</td>
					<td>
						<s:property value="roleDescription" />
					</td>
					<td>
						<s:if test='bedId!=0'>
							<s:url id="editStuOrStaffDetails"
								action="ajaxDoEditStuOrStaffDetails" includeParams="all"
								escapeAmp="false" namespace="/hostel">
								<s:param name="EditStudentStaff">Edit</s:param>
								<s:param name="accountId" value='%{accountId}' />
							</s:url>
							<sj:a href="%{editStuOrStaffDetails}"
								targets="editStuOrStaffDetails%{accountId}"
								onCompleteTopics="doInitEditStuOrStaffDetails"
								onBeforeTopics="cleanOpenDivs" indicator="indicator"
								button="false">Edit Details
									</sj:a>
						</s:if>
						<s:else>
								<a data-toggle="modal" href="#stuOrStaffDetailsDiv"
									class="btn btn-xs purple"
									onclick="javascript:PopupEditStaffDetails(<s:property value="%{accountId}" />);"><i
									class="fa fa-edit"></i>Assign to Room </a>
						</s:else>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:elseif>
<s:else>
	<div class="alert alert-info">
		Oops! system couldn't find any match with keyword. Try by correcting
		the word
	</div>
</s:else>
<div id="stuOrStaffDetailsDiv"></div>
<div id="popupHostelDiv"></div>
<script type="text/javascript">
UIExtendedModals.init();
$(document).ready(function() {
$('#searchStudentsList').show();
var accountId='<s:property value="bankId"/>';
if(accountId != 0){
	$('div.alert-info').hide();
}
});

function PopupEditHostel(accountId,studentId) {
	var url = jQuery.url.getChatURL("/common/ajaxViewStudentHostelInfo.do");
	$('#popupHostelDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
			url : url,
			cache : false,
			data : "bankId=" + accountId+"&student.id="+studentId,
			success : function(html) {
				$("#popupHostelDiv").html(html);
			}
		});
	}
	function PopupEditStaffDetails(accountId) {
	var url = jQuery.url.getChatURL("/hostel/ajaxDoEditStuOrStaffDetails.do");
	$('#stuOrStaffDetailsDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
			url : url,
			cache : false,
			data : "accountId=" + accountId,
			success : function(html) {
				$("#stuOrStaffDetailsDiv").html(html);
				
			}
		});
	}
	
</script> 