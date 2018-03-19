<%@ include file="/common/taglibs.jsp"%>
<head>
    <sj:head jqueryui="true"/>
    <title>EMS | Dashboard</title>
  </head>
<script type="text/javascript">
$(document).ready(function() {
	$("a.addMenu").fancybox( {
		'zoomSpeedIn' : 300,
		'zoomSpeedOut' : 300,
		'frameWidth' : 620,
		'frameHeight' : 480,
		'hideOnContentClick' : false,
		'overlayShow' : true
	});
});
</script>
<body />
	<div class="wrapper container_16">
		<!-- wrapper begins -->
		<div class="block grid_4" id="blockgrid">
			<div class="block_head">
				<h2>
					Left Nav
				</h2>
			</div>
			<div class="block_content" id="sideMenu"
				style="padding-left: 0px; padding-right: 0px; padding-top: 0px;">
			<ul style="padding-left: 0px;">
			<li>
			<div class="block_content" id="sideMenu"
				style="padding-left: 0px; padding-right: 0px; padding-top: 0px;">
			<jsp:include page="/WEB-INF/pages/cms/website/websiteTreeLeftNav.jsp" />
			</div>
			</li>
			<li>
			<a href="popupDoAddMenu.do?websiteId=<s:property value="website.id" />" class="addMenu" title="add Menu"><img src="/images/btns.gif" style="border-style:none;" />add Menu</a>
			</li>	
			</ul>
			
			</div>
		</div>
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
				
			</div>

			<!-- .block_content ends -->
		</div>
</div>