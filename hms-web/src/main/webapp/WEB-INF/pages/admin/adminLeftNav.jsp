<%@ include file="/common/taglibs.jsp"%>
			<div class="grid_4 alpha">
				<div class="block_head">
					<h2>
						School Settings
					</h2>

				</div>
				<div class="block_content" id="sideMenu" >
					<ul>
						<li  class="active">
							<a href="studentProfile.do">School Timetable</a>
						</li>
						<li>
							<s:url id="urlGoalsLink" action="ajaxGetGoalsReport" />
							<sj:a id="goalsLink" href="%{urlGoalsLink}"
								targets="metricsContent" indicator="indicator">Change Password</sj:a>
						</li>
					</ul>
				</div>
			</div>