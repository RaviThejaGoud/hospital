<%@ include file="/common/taglibs.jsp"%>
<body />
  <div class="wrapper container_14">
  <div class="wrapper">
		<!-- wrapper begins -->
		<div class="grid_14 block">
			<!--<div class="grid_4 alpha">
				<div class="block_head">
					<h2>
						Students
					</h2>

				</div>
				<div class="block_content" id="sideMenu" style="padding: 0px;">
					<ul>
					   <li style="line-height: 0px">&nbsp;</li>
					 	<li class="active">
							<s:url id="urlmanageStudents" action="ajaxManageStudents" />
							<sj:a id="manageStudents" href="%{urlmanageStudents}"
								targets="studentsContent" indicator="indicator">Manage Students</sj:a>
						</li>
						<li style="line-height: 0px">&nbsp;</li>
					</ul>
				</div>
			 </div>	
			--><div class="grid_14 omega" id="studentsContent">
			<jsp:include page="/WEB-INF/pages/admin/student/ajaxManageStudents.jsp"></jsp:include>
			</div>
		</div>
	</div>
	</div>
	<script Language="Javascript1.2" type="text/javascript">
          $(document).ready(function() {
          changePageTitle("Student Details");
			     $('#manageStaff').addClass('current');
			});
	</script>