<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<s:if test="%{schoolTermsList != null && !schoolTermsList.isEmpty()}">
	<div class="grid_11">
		<br />
		<table class="striped" width="100%" cellpadding="1"
			style="margin-bottom: 0;" cellspacing="1" id="results">
			<thead>
				<tr>
					<th width="25%" class="head">
								S.No
							</th>
					<th width="25%" class="head">
						Term Name
					</th>
					<th class="head" width="25%">
						Start Month
					</th>
					<th class="head" width="25%">
						End Month
					</th>
					<th class="head" width="25%">
						Due Date
					</th>
					<th class="head">
						Edit
					</th>
				</tr>
			</thead>
		</table>
		<div id="resultsPage">
			<%int i = 0; %>
			<s:iterator value="schoolTermsList">
				<table class="striped" width="100%" cellpadding="1"
					style="margin-bottom: 0; border-width: 1px 1px 1px;"
					cellspacing="1" id="results">
					<tr class="loaded">
						<td  width="25%">
									<%i++; %><%=i %>
								</td>
						<td width="25%">
							<s:property value="termName" />
						</td>
						<td width="25%">
							<s:property value="fromMonthName" />
						</td>
						<td width="25%">
							<s:property value="toMonthName" />
						</td>
						<td width="25%">
							<s:property value="dueDateStr" />
						</td>
						<td  width="25%">
							<s:url id="doEditSchooTerms" action="ajaxEditManageSchooTerms"
	                            includeParams="all" escapeAmp="false">
	                            <s:param name="schoolTermId" value="id" />
	                    </s:url>
	                    <sj:a href="%{doEditSchooTerms}"
	                           targets="editSchoolTerms%{id}" onBeforeTopics="cleanOpenRows"
							onCompleteTopics="doInitSchoolTermsDetails" indicator="indicator"  cssClass="normalEdit" title="Edit"> 
	                    </sj:a>
						</td>
					</tr>
					<tr id="editSchoolTerms<s:property value='id'/>" style="display:none;" class="load"></tr>
				</table>
			</s:iterator>
		</div>
	</div>
</s:if>
<s:else>
	There are no school terms defiend.
</s:else>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/subscription/paginator.js">
</script>
<script type="text/javascript">
$(function() {
	$("#resultsPage").pagination();
});
</script>
<script type="text/javascript">
$(document).ready(function() {
	$.subscribe('doInitSchoolTermsDetails', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});
	
	});
</script>
