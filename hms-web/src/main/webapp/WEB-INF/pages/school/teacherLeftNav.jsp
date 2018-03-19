<%@ include file="/common/taglibs.jsp"%>
			<div class="grid_4 alpha">
				<div class="block_head">
					<h2>
						Personal
					</h2>

				</div>
				<div class="block_content" id="sideMenu">
					<ul>
						<li class="active">
							<a href="">My Profile</a>
						</li>
						
						<li>
							<a href="mySubjects.jsp">My Subjects</a>
						</li>
						
						<li>
							<a href="schoolAlerts.jsp">My Scheduler</a>
						</li>
						<li>
							<s:url id="urlGoalsLink" action="ajaxGetGoalsReport" />
							<sj:a id="goalsLink" href="%{urlGoalsLink}"
								targets="metricsContent" indicator="indicator">My Task Status</sj:a>
						</li>
						<li>
							<s:url id="urlGoalsLink" action="ajaxGetGoalsReport" />
							<sj:a id="goalsLink" href="%{urlGoalsLink}"
								targets="metricsContent" indicator="indicator">Attandance </sj:a>
						</li>
						<li>
							<a href="mySubjects.jsp">Attandance Edit</a>
						</li>
						<li>
							<s:url id="urlGoalsLink" action="ajaxGetGoalsReport" />
							<sj:a id="goalsLink" href="%{urlGoalsLink}"
								targets="metricsContent" indicator="indicator">Leave</sj:a>
						</li>
						<li>
							<s:url id="urlGoalsLink" action="ajaxGetGoalsReport" />
							<sj:a id="goalsLink" href="%{urlGoalsLink}"
								targets="metricsContent" indicator="indicator"> Marks</sj:a>
						</li>
						<li >
						<s:url id="doCreateResource" action="ajaxDoCreateResource" />
						<sj:a targets="campaignResponce" id="campaignsList"
							href="%{doCreateResource}" label="Campaigns">Reports</sj:a>
					</li>
						
						<li>
							<a href="mySubjects.jsp">Study Material</a>
						</li>
						<li>
							<a href="mySubjects.jsp">My Events</a>
						</li>
						<li>
							<a href="mySubjects.jsp">Calendar</a>
						</li>
						<li>
							<a href="schoolAlerts.jsp">Change Password</a>
						</li>
						<li>
							<a href="schoolAlerts.jsp">Transport </a>
						</li>
					</ul>
				</div>
			</div>