<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{sportsList != null && !sportsList.isEmpty()}">
	<div class="spaceDiv"></div>
	<table class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
		<thead>
			<tr>
				<th>Sl.No</th>
				<th>Sports Name</th>
				<th>No.Of Players </th>
				<th>Coach Name</th>
				<s:if test='%{user.schoolAdmin}'>
					<th>Edit</th>
					<th>Delete</th>
				</s:if>
			</tr>
		</thead>
 		<tbody>
			<%int i = 0; %>
			<s:iterator value="sportsList" >
				<tr>
					<td>
						<%i++; %><%=i %>
					</td>
					<td>
						<s:property value="sportName" />
					</td>
					<td>
						<s:property value="numberOfPlayers" />
					</td>
					<td>
						<s:if test="%{coachList != null && !coachList.isEmpty()}">
							<s:iterator value= "coachList" >
								<s:property value="staffAccountVo.fullName" /><br/>
							</s:iterator>
						</s:if>
					</td>
					<s:if test='%{user.schoolAdmin}'>
						<td>
							<s:url id="editSports" action="ajaxEditSports" includeParams="all" escapeAmp="false" namespace="/sports">
							<s:param name="sportsVO.id" value="%{id}" />
							</s:url> <sj:a href="%{editSports}" targets="sportsContentDiv" cssClass="btn btn-xs purple" title="Edit">
								<i class="fa fa-edit"></i>Edit
							</sj:a>
						</td> 
						<td>
							<s:url id="removeSports" action="ajaxRemoveSports" includeParams="all" escapeAmp="false" namespace="/sports">
								<s:param name="sportsVO.id" value="%{id}" />
							</s:url>
							<s:div cssClass="btn btn-xs red" onclick="javascript:confirmDialogWithTarget(this,'mainContentDiv');"
								id='%{removeSports}' title="Delete this sports" >
								<i class="fa fa-times"></i>Delete
							</s:div>
						</td>
					</s:if>
				</tr>
			</s:iterator>
		</tbody>
	</table>
 </s:if>  
 <s:else>
	<div class="alert alert-info">
		Currently there are no Sports. 
		<s:if test='%{user.schoolAdmin}'>
			Please <a href="#" onclick="javascript:ajaxAddSports()"><font color="red">Click here</font></a> to add a Sports.
		</s:if>
	</div>
</s:else>
<script type="text/javascript">
	TableAdvanced.init();
	UIExtendedModals.init();
	function formateSportsNumber(event) {
		var sportsNum = $(event).val();
		if (isNonEmpty(sportsNum)) {
			if (sportsNum.length < 3) {
				for ( var i = 0; i < (4 - sportsNum.length); i++) {
					sportsNum = '0' + sportsNum;
				}
			}
			$(event).val(sportsNum);
		}
	}
	function EditSportsDetials(id) {
		var url = jQuery.url.getChatURL("/sports/ajaxAddSports.do");
		$('#responsive2').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
			url : url,
			cache : false,
			data : "SportsVO.id=" + id,
			success : function(html) {
				$("#responsive2").html(html);
			}
		});
	}
	function ajaxAddSports() {
		$('a#addSprts').click();
	} 
</script>