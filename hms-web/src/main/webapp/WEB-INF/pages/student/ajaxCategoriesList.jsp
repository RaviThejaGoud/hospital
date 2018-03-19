<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp" %>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/validation/jquery.validate.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
	<div class="block grid_12">
	<div class="block_head">
		<h2>
			Manage Events
		</h2>
		<div id="topMenu">
		<ul>
		    <li>
                <s:url id="doSearchCategory" action="ajaxSearchCategory" />
				<sj:a href="%{doSearchCategory}" targets="categoryEvents"> Search </sj:a>
            </li>
			<li>
				<s:url id="addCategory" action="ajaxDoAddCategory" />
				<sj:a href="%{addCategory}" targets="categoryEvents"> New Category</sj:a>
			</li>
			
			 <li>
					<a href="${pageContext.request.contextPath}/calendar/adminCalendar.do" id="categories">Switch To Calendar View</a>
			</li>
		</ul>
		</div>
	</div>
	<!--<div class="block_content" id="categoryEvents">
<jsp:include page="categoriesList.jsp" />
</div>
--></div>
