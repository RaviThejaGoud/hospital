<%@ include file="/common/taglibs.jsp"%>
<div class="grid_4 alpha omega">
	<div>
		<strong> Notice Board </strong>
	</div>
	<s:if test="%{noticeBoardMessagesList != null && !noticeBoardMessagesList.isEmpty()}">
		<marquee DIRECTION="up" scrollamount="2" vspace="0px"
			onmouseover="stop();" onmouseout="start();">
			<div style="padding: 1px 15px 0px 5px;">

				<s:iterator value="noticeBoardMessagesList">
					<!--<b>Title:</b>
						<br />
						<s:property value="title" />
						<br />
						<b>Message:</b><br/>
			-->
					<s:property value="messageDescription" />
					<br />
			===============<br />
				</s:iterator>

			</div>
		</marquee>
	</s:if>
	<s:else>
		Currently there are no Notice Board Messages.
	</s:else>
</div>
<style>
.noticeboard {
	height: 50px;
	padding: 0 20px;
	line-height: 50px;
	background: url(../../images/blackboard.jpg) 0 0 no-repeat;
	overflow: hidden;
	color: #999;
	font-weight: bold;
	margin-bottom: 5px;
}
</style>