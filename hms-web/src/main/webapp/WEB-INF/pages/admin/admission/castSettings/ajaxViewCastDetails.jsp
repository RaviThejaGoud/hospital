<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<s:if test="%{castSettingList != null && !castSettingList.isEmpty()}">
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th>
					Community
				</th>
				<th>
					Caste Name
				</th>
				<th>
					Edit
				</th>
				<!--<th style="display: none;" class="load">
				
				</th>
			--></tr>
		</thead>
		<tbody>
			<s:iterator value="castSettingList">
				<tr>
					<td>
						<s:property value="castName" />
					</td>
					<td>
						<s:url id="addActitypes" action="ajaxViewAllSubCaste" namespace="/admin"
							includeParams="all" escapeAmp="false">
							<s:param name="quizId" value="%{id}" />
						</s:url>
						<sj:a href="%{addActitypes}"
							targets="academicsContent">Manage Castes </sj:a>
					</td>
					<td>
					<s:if test='%{#session.previousYear=="N"}'>
							<a data-toggle="modal"  href="#responsive2"  class="btn btn-xs purple"
								onclick="javascript:PopupViewCastDetails(<s:property value="%{id}" />);"><i class="fa fa-edit"></i>Edit
							</a>
						</s:if>
						<!--<s:if test='%{#session.previousYear=="N"}'>
							<s:url id="editCaste" action="ajaxDoAddCastDetails"
								includeParams="all" escapeAmp="false">
								<s:param name="quizId" value="%{id}" />
							</s:url>
							<sj:a href="%{editCaste}" indicator="indicator"
								targets="academicsContent" cssClass="btn btn-xs purple"
								title="Edit">
								<i class="fa fa-edit"></i>Edit
								</sj:a>
						</s:if>
					--></td>
				</tr>
				<!--<div id="editCastSettings<s:property value='id' />"
							style="display: none;" class="load">
						</div>
						<div id="viewAllRacks<s:property value='id' />"
							style="display: none;" class='load'>
						</div>
			--></s:iterator>
		</tbody>
	</table>
			</s:if>
			<s:else>
				<div class="alert alert-info">
					Currently there are no caste details.
				</div>
			</s:else>
			<div id="responsive2"></div>
	<script type="text/javascript">
	TableAdvanced.init();
TableEditable.init();
UIExtendedModals.init();
function PopupViewCastDetails(id) {
		var url = jQuery.url.getChatURL("/admin/ajaxDoAddCastDetails.do");
		$('#responsive2').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
				url : url,
				cache : false,
				data : "quizId=" + id,
				success : function(html) {
					$("#responsive2").html(html);
				}
			});
		} 
</script>