<%@ include file="/common/taglibs.jsp"%>
<div id="classFeeDetails">
		<table class="striped" width="100%">
		<s:if test="%{classFeeList != null && !classFeeList.isEmpty()}">
			<thead>
				<tr>
					<th width="30%">
						Fee Type
					</th>
					<th width="30%">
						Total Fee
					</th>
					<th width="30%">
						Fee Due Date
					</th>
					<th width="10%">
						Edit
					</th>
				</tr>
			</thead>
			<s:iterator value="classFeeList">
				<tr class='loaded'>
					<td>
							<s:property value="feeType" />
					</td>
					<td>
						<s:property value="feeAmount" />
					</td>
					<td>
						<s:property value="feeDueDateStr"/>
					</td>
					<td>

						<s:url id="editFee" action="ajaxDoEditSchoolFee"
							includeParams="all" escapeAmp="false">
							<s:param name="feeId" value="id" />
						</s:url>
						<sj:a href="%{editFee}" targets="feeDetails%{id}"
							onBeforeTopics="cleanOpenRows" onCompleteTopics="doDisplayFee"
							indicator="indicator" button="false" buttonIcon="ui-icon-pencil"  cssClass="normalEdit" title="Edit">
						</sj:a>
					</td>
				</tr>
				<tr id="feeDetails<s:property value="id"/>" class='load'></tr>
			</s:iterator>
			</s:if>
			<s:if test="%{feeTypeList != null && !feeTypeList.isEmpty()}">
			<s:set name="classNameId" value="classId"/>
					<s:iterator value="feeTypeList">
						<tr class='loaded'>
							<td>
									<s:property value="feeType" />
							</td>
							<td>
								<s:property value="feeAmount" />
							</td>
							<td>
								<s:property value="feeDueDateStr"/>
							</td>
							<td>
								<s:url id="newFee" action="ajaxNewSchoolFee"
									includeParams="all" escapeAmp="false" >
									<s:param name="classId" value="%{classNameId}"></s:param>
									<s:param name="feeTypeId" value="id" />
									
								</s:url>
								<sj:a href="%{newFee}" targets="newFeeDetails%{id}"
									onBeforeTopics="cleanOpenRows" onCompleteTopics="doDisplayFee"
									indicator="indicator" button="false" buttonIcon="ui-icon-pencil">
									<a href="#" title="Edit" class="normalEdit"></a>
								</sj:a>
							</td>
						</tr>
						<tr id="newFeeDetails<s:property value="id"/>" class='load'></tr>
					</s:iterator>
			</s:if>
		</table>
	<!--<s:else>
		There are no class fee details.
	</s:else>
--></div>