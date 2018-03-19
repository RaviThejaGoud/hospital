<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<s:if test="%{objectList != null && !objectList.isEmpty()}">
	<table
		class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
		<thead>
			<tr>
				<th>
					Admission Number
				</th>
				<th>
					Student Name
				</th>
				<th>
					Intra Transfer School
				</th>
				<th>
					Intra Transfer School
				</th>
			</tr>
		</thead>
		<tbody>
			<s:set name="studyClassId" value=""></s:set>
			<s:iterator value="objectList">
				<tr>
					<td>
						<s:property value="account.admissionNumber" />
					</td>
					<td>
						<s:property value="account.person.firstName" />
						&nbsp;
						<s:property value="account.person.lastName" />
					</td>
					<td>
						<s:property value="customer.organization" />
					</td>
					<td>
						<a data-toggle="modal" href="#popupStudentTransferDiv"
							class="btn btn-xs purple"
							onclick="javascript:popupTransferEnable(<s:property value="%{id}" />);"><i class="fa fa-edit"></i>Enable Student</a>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		There is no student transfer from intra branch.
	</div>
</s:else>
 <div id="popupStudentTransferDiv"></div>
<script type="text/javascript">
   $(document).ready(function() {
   	TableAdvanced.init();
   });
   	function popupTransferEnable(id) {
   		var url = jQuery.url.getChatURL("/student/ajaxDoTransferEnableStudent.do");
   		$('#popupStudentTransferDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
   		$.ajax( {
   				url : url,
   				cache : false,
   				data : "tempId=" + id,
   				success : function(html) {
   					$("#popupStudentTransferDiv").html(html);
   				}
   			});
   		}
   </script>

