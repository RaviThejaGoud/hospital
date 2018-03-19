<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<s:if
	test="%{packageDetailsList != null && !packageDetailsList.isEmpty()}">
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th>
					Range
				</th>
				<th>
					Cost per student
				</th>
				<th>
					Max no.of students allowed
				</th>
				<th>
					Edit
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="packageDetailsList">
				<tr>
					<td>
					Upto 
						<s:property value="studentsRange" />
					</td>
					<td>
						<s:property value="costPerStudent" />
					</td>
					<td>
						<s:property value="maxAllowableStudents" />
					</td>
					<td>
					<!--<a data-toggle="modal"  href="#viewPackages"  class="btn btn-xs purple"
								onclick="javascript:PopupViewPackages(<s:property value="%{id}" />);"><i class="fa fa-edit"></i>Edit
							</a>
					-->
					<s:url id="editPackageInfo" action="ajaxDoAddPackage"
							includeParams="all" escapeAmp="false" namespace="/masterAdmin">
							<s:param name="id" value="%{id}"></s:param>
						</s:url>
						<sj:a href="%{editPackageInfo}"
							onCompleteTopics="doEditPackageDetails"
							onBeforeTopics="cleanOpenRows" indicator="indicator%{id}"
							targets="packageDetailsDiv" cssClass="btn btn-xs purple"
							title="Edit">
							<i class="fa fa-edit"></i>Edit
								</sj:a></td>
				</tr>
			</s:iterator>
			<s:else>
				<div class="alert alert-info">
					Currently there are no Customer details.
				</div>
			</s:else>
		</tbody>
	</table>
</s:if>
<div id="viewPackages"></div>
<script type="text/javascript">
/*function PopupViewPackages(id) {
		var url = jQuery.url.getChatURL("/masterAdmin/ajaxDoAddPackage.do");
		$('#viewPackages').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
				url : url,
				cache : false,
				data : "id=" + id,
				success : function(html) {
					$("#viewPackages").html(html);
				}
			});
		} */
</script>
