<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<s:if
	test="%{categoryQuestionList != null && !categoryQuestionList.isEmpty()}">
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th>
					Q.No
				</th>
				<th>
					Question Name
				</th>
				<th>
					Edit
				</th>
			</tr>
		</thead>
		<tbody>
			<%int i = 0; %>
			<s:iterator value="categoryQuestionList">
				<tr>
					<td>
						<%i++; %><%=i %>
					</td>
					<td>
						<s:property value="questionName" />
					</td>
					<td>
						<s:if test='%{#session.previousYear == "N"}'>
							<a data-toggle="modal" href="#popupEditQuestionAnswersDiv"
								class="btn btn-xs purple"
								onclick="javascript:popupEditQuestionAnswers(<s:property value="id" />);"><i
								class="fa fa-edit"></i>&nbsp;&nbsp;Edit </a>
						</s:if>
					</td>
				</tr>
				<div id="editQuestionAnswersForm<s:property value='id'/>"
					style="display: none;" class='load'>
				</div>

			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		Questions are not created selected category.
	</div>
</s:else>
<div id="popupEditQuestionAnswersDiv">
</div>
<script type="text/javascript">
TableAdvanced.init();
UIExtendedModals.init();
function popupEditQuestionAnswers(id) {
	var url = jQuery.url.getChatURL("/staff/ajaxDoEditQuestionAnswers.do");
	$('#popupEditQuestionAnswersDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
			url : url,
			cache : false,
			data : "tempId=" + id,
			success : function(html) {
				$("#popupEditQuestionAnswersDiv").html(html);
			}
		});
	}
$(document).ready(function() {
	$.subscribe('doInitEditQuestionAnswers', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});
});
</script>