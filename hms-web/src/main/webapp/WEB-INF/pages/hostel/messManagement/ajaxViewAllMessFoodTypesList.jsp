<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<s:if test="%{objectList!= null && !objectList.isEmpty()}">
	<p>
		<span class="label label-danger"> NOTE : </span>&nbsp;You can
		edit/update existing mess food type settings by clicking on edit pen icon in
		each row at right side.
	</p>
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th>
					Mess Name
				</th>
				<th>
					Food Type Name
				</th>
				<th>
					Edit
				</th>	
				<th>
					Inactive
				</th>			
			</tr>
		</thead>
		<tbody>
			<s:iterator value="objectList" status="stat">
				<tr>
					<td>
						<s:property value="objectList[#stat.count-1][0]" />
					</td>
					<td>
					<s:property value="objectList[#stat.count-1][1]" />
					</td>	
					<td>
						<s:url id="editMessFoodType" action="ajaxDoAddMessFoodTypes" includeParams="all" escapeAmp="false" namespace="/hostel">
								<s:param name="messFoodType.id" value="%{objectList[#stat.count-1][2]}" />										
							</s:url>
							<sj:a href="%{editMessFoodType}" targets="messSettingContent"  cssClass="btn btn-xs purple" title="Edit"><i class="fa fa-edit"></i>Edit
							</sj:a>
					</td>
								
						<td>
						<s:if
							test='%{(#session.previousYear == null || #session.previousYear.isEmpty()) || (#session.previousYear == "N")}'>
							<s:url id="inactiveMessFoodType" action="ajaxDeleteMessFoodType"
								includeParams="all" escapeAmp="false" namespace="/hostel">
								<s:param name="messFoodType.id" value="%{objectList[#stat.count-1][2]}" />								
							</s:url>
							<s:div id='%{inactiveMessFoodType}' theme="simple"
								title="Inactive this messFoodType" cssClass="btn btn-xs red"
								onclick="javascript:confirmDialogWithTarget(this,'messSettingContent');">
								Inactive
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
		Currently there are no mess food types.
	</div>
</s:else>

