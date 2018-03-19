<%@ include file="/common/taglibs.jsp"%>
			<div class="grid_4 alpha">
				<div class="block_head">
					<h2>
						Manage Events
					</h2>

				</div>
				<div class="block_content" id="sideMenu" style="padding: 0px;">
					<ul>
					  <li class="active"> 
							<s:url id="urlViewEvents" action="ajaxViewEvents" namespace="/admin"/>
							<sj:a id="viewEvent" href="%{urlViewEvents}"
								targets="eventsContent" indicator="indicator">View Events</sj:a>
						</li>	
						<s:if test='%{(#session.academicYear == null || #session.academicYear.isEmpty()) || (#session.previousYear=="N")}'>
						<li>
						    <s:url id="urlDoAddEvent" action="ajaxDoAddEvent" namespace="/admin" />
							<sj:a id="doAddEvent" href="%{urlDoAddEvent}"
								targets="eventsContent" indicator="indicator">Create Event</sj:a>							
						</li>
						</s:if>
						<li> 
							<s:url id="urlViewExpiredEvents" action="ajaxViewExpiredEvents" namespace="/admin"/>
							<sj:a id="viewExpiredEvent" href="%{urlViewExpiredEvents}"
								targets="eventsContent" indicator="indicator">View Expired Events</sj:a>
						</li> 			
					</ul>
				</div>
			</div>