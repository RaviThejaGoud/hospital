<%@ include file="/common/taglibs.jsp"%>
<div class="grid_12 omega block">
	<div class="block_head">
		<h2>
			Parent Details
		</h2>
	</div>
	<div class="block_content">
		<div class="grid_11 alpha">
			Today's Timetable
		</div>
		<div class="grid_8 alpha">
			<table class="timeTable">
				<tr>
					<th>
						Period
					</th>
					<th>
						1
					</th>
					<th>
						2
					</th>
					<th>
						3
					</th>
					<th>
						4
					</th>
					<th>
						5
					</th>
					<th>
						6
					</th>
					<th>
						7
					</th>
					<th>
						8
					</th>
				</tr>
				<tr>
					<td>
						Subject
					</td>
					<td>
						Telugu
					</td>
					<td>
						Hindi
					</td>
					<td>
						English
					</td>
					<td>
						Maths
					</td>
					<td>
						PS
					</td>
					<td>
						BS
					</td>
					<td>
						Social
					</td>
					<td>
						Maths
					</td>
				</tr>
				<tr>
					<td style="width: 50px; padding: 5px; vertical-align: middle;">
						Teacher
					</td>
					<td>
						<img src="<c:url value='/images/ramThumb.png'/>"
							style="border: 1px solid #FF7300" />
					</td>
					<td>
						<img src="<c:url value='/images/ramThumb.png'/>"
							style="border: 1px solid #FF7300" />
					</td>
					<td>
						<img src="<c:url value='/images/ramThumb.png'/>"
							style="border: 1px solid #FF7300" />
					</td>
					<td>
						<img src="<c:url value='/images/ramThumb.png'/>"
							style="border: 1px solid #FF7300" />
					</td>
					<td>
						<img src="<c:url value='/images/ramThumb.png'/>"
							style="border: 1px solid #FF7300" />
					</td>
					<td>
						<img src="<c:url value='/images/ramThumb.png'/>"
							style="border: 1px solid #FF7300" />
					</td>
					<td>
						<img src="<c:url value='/images/ramThumb.png'/>"
							style="border: 1px solid #FF7300" />
					</td>
					<td>
						<img src="<c:url value='/images/ramThumb.png'/>"
							style="border: 1px solid #FF7300" />
					</td>
				</tr>

			</table>
		</div>
		<div class="grid_3 omega">
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
</div>