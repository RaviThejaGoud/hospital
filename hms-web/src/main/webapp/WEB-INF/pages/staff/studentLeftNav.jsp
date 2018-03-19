<%@ include file="/common/taglibs.jsp"%>
			<div class="grid_4 alpha">
				<div class="block_head">
					<h2>
						Personal
					</h2>

				</div>
				<div class="block_content" id="sideMenu" >
					<ul>
						<li class="active">
							<a href="">My Profile</a>
						</li>
						<li>
							<s:url id="urlGoalsLink" action="ajaxGetGoalsReport" />
							<sj:a id="goalsLink" href="%{urlGoalsLink}"
								targets="metricsContent" indicator="indicator">My proflie</sj:a>
						</li>
						<li>
							<a href="mySubjects.jsp">My Attendance </a>
						</li>
						<li>
							<a href="mySubjects.jsp">My Subjects Teacher</a>
						</li>
						<li>
							<a href="mySubjects.jsp">My School Teacher</a>
						</li>
						<li>
							<a href="schoolAlerts.jsp">My Scheduler</a>
						</li>
						<li>
							<a href="schoolAlerts.jsp">My Health Report</a>
						</li>
						<li>
							<a href="schoolAlerts.jsp">My Leave</a>
						</li>
						<li>
							<a href="schoolAlerts.jsp">Extra Curricular</a>
						</li>
						<li>
							<a href="schoolAlerts.jsp">Feedback</a>
						</li>
						<li>
							<a href="schoolAlerts.jsp">Lost & Found</a>
						</li>
						<li>
							<a href="schoolAlerts.jsp">View Your Reports</a>
						</li>
						<li>
							<s:url id="urlGoalsLink" action="ajaxGetGoalsReport" />
							<sj:a id="goalsLink" href="%{urlGoalsLink}"
								targets="metricsContent" indicator="indicator">Change Password</sj:a>
						</li>
						<li>
							<s:url id="urlGoalsLink" action="ajaxGetGoalsReport" />
							<sj:a id="goalsLink" href="%{urlGoalsLink}"
								targets="metricsContent" indicator="indicator">My Events</sj:a>
						</li>
						<li>
							<s:url id="urlGoalsLink" action="ajaxGetGoalsReport" />
							<sj:a id="goalsLink" href="%{urlGoalsLink}"
								targets="metricsContent" indicator="indicator">My School Events</sj:a>
						</li>
						<li>
							<s:url id="urlGoalsLink" action="ajaxGetGoalsReport" />
							<sj:a id="goalsLink" href="%{urlGoalsLink}"
								targets="metricsContent" indicator="indicator">Transport</sj:a>
						</li>
					</ul>
				</div>
			</div>