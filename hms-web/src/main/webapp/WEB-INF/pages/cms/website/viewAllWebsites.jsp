<%@ include file="/common/taglibs.jsp"%>
<head>
    <sj:head jqueryui="true"/>
    <title>EMS | Dashboard</title>
  </head>

<body />
	<div class="wrapper container_16">
		<!-- wrapper begins -->
		<div class="block grid_4" id="blockgrid">
			<jsp:include page="/WEB-INF/pages/cms/website/websiteLeftNav.jsp" />
		</div>
		<img id="indicator" src="<c:url value='images/indicator.gif' />" alt="Loading..." style="display:none"/>
		<div class="block grid_12" >
			<div class="block_head">
				<h2>
					Content
				</h2>

				<ul>
					<li>
						<a href="#">View Templates</a>
					</li>
					<li>
						<a href="doCreateWebsite.do">Create website</a>
					</li>
				</ul>
			</div>
			<!-- .block_head ends -->
			<div class="block_content">
				<s:if test="%{websiteList != null && !websiteList.isEmpty()}">
					<div class="grid_9">

						<div class="grid_2">
							<h3>
								Title
							</h3>
						</div>
						<div class="grid_3">
							<h3>
								Website Url
							</h3>
						</div>
						<div class="grid_2">
							<h3>
								Delete
							</h3>
						</div>
					</div>

					<div class="grid_11" id="webSites">
						<jsp:include page="/WEB-INF/pages/cms/website/webSites.jsp"/>
					</div>

				</s:if>
				<s:else>There are no websites.
					</s:else>
			</div>

			<!-- .block_content ends -->
		</div>
</div>