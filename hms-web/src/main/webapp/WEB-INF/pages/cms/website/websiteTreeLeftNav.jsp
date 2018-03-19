<%@ include file="/common/taglibs.jsp"%>
<s:property value="website.websiteUrl" />
<s:if test="%{menuList != null && !menuList()}">
	<s:iterator value="menuList">
	<div class="grid_8">
		<div class="grid_2">
				<s:property value="name" />
		</div>
			
		<div class="grid_3">
			<a href="getSingleWebsite.do?websiteId=<s:property value="id" />">
				 <s:property value="websiteUrl" /> </a>
		</div>
		<div class="grid_2">	
				<s:url id="removeWebsite" action="ajaxRemoveWebsite" includeParams="all">
					<s:param name="id" value="{id}"/>
				</s:url>
				<sj:a href="%{removeWebsite}" targets="webSites" indicator="indicator" button="true" buttonIcon="ui-icon-refresh">Delete</sj:a>
		</div>
		</div>
	</s:iterator>
	</s:if>
	<img id="indicator" src="images/indicator.gif" alt="Loading..." style="display:none"/>
