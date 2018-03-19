<%@ include file="/common/taglibs.jsp"%>
<div class="wrapper container_18">
	<div class="wrapper">
		<div class="grid_18 block grid_18MarginLeft">
			<div class="grid_4 alpha">
				<div class="block_head">
					<h2>
						Class Assignment
					</h2>
				</div>
				<div class="block_content" id="sideMenu">
					<ul>
						<li class="active">
							<s:url id="urlmanageClassAssignment" action="ajaxViewAllClassAssignment" namespace="/admin"/>
							<sj:a id="manageClassAssignment" href="%{urlmanageClassAssignment}"
								targets="staffContent" indicator="indicator">Manage Class Assignment</sj:a>
						</li>
					   <li >
							<s:url id="urlsendAlerts" action="ajaxGetStudyClassList" namespace="/student"/>
							<sj:a id="sendAlerts" href="%{urlsendAlerts}"
								targets="staffContent" indicator="indicator">Send Alerts</sj:a>
						</li>
						  
					</ul>
				</div>
			</div>
			<div id="staffContent" class="grid_14 alpha">
			
				<jsp:include page="/WEB-INF/pages/common/classAssignment/ajaxViewClassAssignmentList.jsp"></jsp:include>
			</div>
		</div>
	</div>
</div> 