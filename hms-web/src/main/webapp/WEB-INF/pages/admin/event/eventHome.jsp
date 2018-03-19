<%@ include file="/common/taglibs.jsp"%>
<head>
	<title>Admin | Event Management</title>    
</head>
<body />
<div class="wrapper container_16">
		<!-- wrapper begins -->
		<div class="grid_16 block grid_16MarginLeft">
			<jsp:include page="/WEB-INF/pages/admin/event/eventLeftNav.jsp"></jsp:include>			
			<div class="grid_12 omega" id="eventsContent">
				<!--<div class="block_head">
					<h2>
						Event Details
					</h2>
					<ul>
					 	<li>
						    <s:url id="urlDoAddEvent2" action="ajaxDoAddEvent" />
							<sj:a id="doAddEvent2" href="%{urlDoAddEvent2}"
								targets="eventsContent" indicator="indicator">Create Event</sj:a>							
						</li>
						<li class="active"> 
							<s:url id="urlViewEvents2" action="ajaxViewEvents" />
							<sj:a id="viewEvent2" href="%{urlViewEvents2}"
								targets="eventsContent" indicator="indicator">View Events</sj:a>
						</li>			
					</ul>
				</div>
				--><div class="" >
		          <jsp:include page="/WEB-INF/pages/admin/event/viewEvents.jsp"></jsp:include>	
		       </div>
		</div>
		   
		</div>	
	</div>
	<script Language="Javascript1.2" type="text/javascript">
          $(document).ready(function() {
          changePageTitle("Events Details");
			     $('#adminCalendar').addClass('current');
			});
	</script>