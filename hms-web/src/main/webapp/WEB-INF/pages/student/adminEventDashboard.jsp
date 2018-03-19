<%@ include file="/common/taglibs.jsp"%>
<head>
	<title>URT Apps | Events Dashboard</title>
</head>
<body />
	<div class="wrapper container_16">
		<!-- wrapper begins -->
		<div class="block grid_4">
			<div class="block_head">
				<h2>
					Events
				</h2>
				
			</div>
			<div class="block_content" id="sideMenu"
				style="padding-left: 0px; padding-right: 0px; padding-top: 0px;">
				<ul style="padding-left: 0px;">
					<li class="active">
					    <s:url id="urlEventDetailsLink" action="ajaxViewAllEvents"
							includeParams="all" />
						<sj:a  id="eventsLink" href="%{urlEventDetailsLink}" targets="categoryContent"
							indicator="indicator">Manage Events</sj:a>
					</li>
					<li>
						<s:url id="urlManageCategoriesLink" action="ajaxManageCategories"
							includeParams="all" />
						<sj:a  id="categoriesLink" href="%{urlManageCategoriesLink}" targets="categoryContent"
							indicator="indicator">Manage Categories</sj:a>
					</li>
				</ul>
			</div>
			
		</div>
		<div id="categoryContent">
			 <jsp:include page="/WEB-INF/pages/student/ajaxManageEvents.jsp"></jsp:include>
		</div>
	</div>
