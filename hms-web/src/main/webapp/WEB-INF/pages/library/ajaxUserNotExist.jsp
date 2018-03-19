<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />
<s:if test="%{bookTitleList != null && !bookTitleList.isEmpty()}">
	<div id="commonTabContent" class="grid_12">
		<div id="commonTabWrapper">
			<div id="commonStep">
				<fieldset>
					<div class="grid_12 th">
						<div class="grid_3">
							Book Number
						</div>
						<div class="grid_3">
							Book Name
						</div>
						<div class="grid_3">
							issued Date
						</div>
						<div class="grid_3">
							due Date
						</div>
					</div>
					<div class="grid_12 row">
						<s:iterator value="bookTitleList">
							<div class="grid_3">
								<s:property value="bookLable.lableCode" />
							</div>
							<div class="grid_3">
								<s:property value="bookTitle.bookName" />
							</div>
							<div class="grid_3">
								<s:property value="issuedDateStr" />
							</div>
							<div class="grid_3">
								<s:property value="dueDateStr" />
							</div>
						</s:iterator>
					</div>

				</fieldset>
			</div>
		</div>
	</div>
</s:if>