<%@ include file="/common/taglibs.jsp"%>
			<div class="grid_3 omega">
				<div class="block_head">
					<h2>
						Personal
					</h2>

				</div>
				<div class="block_content" id="sideMenu">
					<ul>
						<li>
							<a href="">New Mails(0)</a>
						</li>
						<li>
							<s:url id="urlGoalsLink" action="ajaxGetGoalsReport" />
							<sj:a id="goalsLink" href="%{urlGoalsLink}"
								targets="metricsContent" indicator="indicator">Circular</sj:a>
						</li>
						<li>
							<a href="mySubjects.jsp">Bulletin</a>
						</li>						
					</ul>
				</div>
			</div>