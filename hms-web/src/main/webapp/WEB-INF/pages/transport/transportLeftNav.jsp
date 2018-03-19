<%@ include file="/common/taglibs.jsp"%>
			<div class="grid_4 alpha">
				<div class="block_head">
					<h2>
						Transports
					</h2>

				</div>
				<div class="block_content" id="sideMenu" >
					<ul>
						<li class="active">
							<s:url id="urlManageTransports" action="ajaxManageTransports" />
							<sj:a href="%{urlManageTransports}"
								targets="transportContent">Manage Transports</sj:a>
						</li>
						<li>
							<s:url id="urlGoalsLink" action="ajaxGetGoalsReport" />
							<sj:a id="goalsLink" href="%{urlGoalsLink}"
								targets="transportContent" indicator="indicator">Change Password</sj:a>
						</li>
						<li>
							<s:url id="urlGoalsLink" action="ajaxGetGoalsReport" />
							<sj:a id="goalsLink" href="%{urlGoalsLink}"
								targets="transportContent" indicator="indicator">My Events</sj:a>
						</li>
						<li>
							<s:url id="urlGoalsLink" action="ajaxGetGoalsReport" />
							<sj:a id="goalsLink" href="%{urlGoalsLink}"
								targets="transportContent" indicator="indicator">My School Events</sj:a>
						</li>
						<li>
							<s:url id="urlGoalsLink" action="ajaxGetGoalsReport" />
							<sj:a id="goalsLink" href="%{urlGoalsLink}"
								targets="transportContent" indicator="indicator">Transport</sj:a>
						</li>
					</ul>
				</div>
				</div>