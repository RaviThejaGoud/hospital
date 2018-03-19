<%@ include file="/common/taglibs.jsp"%>
<div class="tabBorder">
	<s:if test="%{classTeacherList != null && !classTeacherList.isEmpty()}">
		<div class="grid_10">
			<s:iterator value="classTeacherList">
				<div class="grid_5">
					<label class="right">
						<b><s:property value="classNameAndSection" /> :</b>
					</label>
				</div>
				<div class="grid_5">
					<s:property value="account.person.personName" />
				</div>
			</s:iterator>
		</div>
	</s:if>
	<s:else>
		No class teacher added.
	</s:else>
</div>