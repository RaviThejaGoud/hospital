<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/group/paginator.js"></script>
<script type="text/javascript">
	changePageTitle('View Teacher Messages');
	$(function() {
		$("#resultsPage").pagination();
	});
</script>
<style>
div.odd {
	background-color: #F5F7F7;
	border-bottom: 1px solid #CCCCCC;
	border-top: 1px solid #CCCCCC;
	height: auto;
	padding: 10px 0px 0px 20px;
}

div.even {
	padding: 10px 0px 0px 20px;
}
</style>
<div class="grid_5 alpha" style="margin-top: 0px;width: 215px;">

	<div class="block_head">
		<h2>
			News
		</h2>
	</div>
	<div class="block_content" style="padding: 0px;">
		<s:if test="%{objectList == null || objectList.isEmpty()}">
			<div style="padding: 20px">
				Currently there are no News.
			</div>
		</s:if>
		<s:elseif test="%{objectList != null && !objectList.isEmpty()}">
			<div id="resultsPage" style="padding: 0px;">
				<s:iterator value="objectList" status="status">
					<s:if test="#status.index % 2 == 1 ">
						<div class="odd">
					</s:if>
					<s:else>
						<div class="even">
					</s:else>
					<div style="margin-bottom: 15px">
						
							<u><a href="<s:property value="link"/>" target="_new"> <s:property
										value="title"/> </a> </u>
							<br />
							<!--<div>
								<s:property value="pubDate" />
							</div>
						-->

					</div>
					<!--<div >
			Email&nbsp;&nbsp;&nbsp;:&nbsp;<s:property value="emailAddress"/><br/>
			Phone&nbsp;:&nbsp;<s:property value="phoneNumber"/>
			</div><br/>
			-->
			<!--<div>
						<s:property value="description" />
						</div>
			--></div>
			</s:iterator>
	</div>
	</s:elseif>
</div>
</div>

<style type="text/css">
.active {
	color: #0033CC;
	text-decoration: none;
}

.inactive {
	font-weight: bold;
	text-decoration: underline;
	cursor: default;
}

.paginator {
	text-align: center;
	color: #FFF;
}
</style>



