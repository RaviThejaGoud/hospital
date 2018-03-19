<%@ include file="/common/taglibs.jsp"%>
<div class="wrapper container_18">
	<div class="wrapper">
		<div class="grid_18 block grid_18MarginLeft">
		<div class="grid_4 alpha">
			<div class="block_head">
				<h2>
					Examination 
				</h2>
			</div>
			<div class="block_content" id="sideMenu">
				<ul>
					<s:if test='%{#session.previousYear == "N"}'>
						<li>
								<s:url id="urlStudentMarks" action="ajaxManageStudentMarks" namespace="/exam"/>
								<sj:a id="manageStudentMarks" href="%{urlStudentMarks}" title="You can download and upload students marks sheet."
									targets="examinationSecContent" indicator="indicator"> Download / Upload Marks Sheet</sj:a>
						</li>
					</s:if>
					<li>
						 <s:url id="urlProgressReportActivities" action="ajaxManageStudentActivities" namespace="/exam"/>
						<sj:a id="doStudActivitiesSettings" href="%{urlProgressReportActivities}" title="You can manage activities, download and upload students activities grades."
							targets="examinationSecContent" indicator="indicator">Manage Students Activities</sj:a>
					</li>
					<li>
						<s:url id="urlDosendMarksToMobile" action="ajaxDoViewClassesHaveMarks" namespace="/exam"/>
						<sj:a id="dosendMarksToMobile" href="%{urlDosendMarksToMobile}"
							targets="examinationSecContent" indicator="indicator">Send Marks To Mobile</sj:a>
					</li>
					<li>
							 <s:url id="urlDoScoreCard" action="ajaxManageScorecardGeneration" namespace="/exam"/>
							<sj:a id="doScoreCardGen" href="%{urlDoScoreCard}" title="It generates score card based on templates."
								targets="examinationSecContent" indicator="indicator">Score Card Generation</sj:a>
					</li>
					<li>
						<s:url id="mngPromotionReports"
							action="ajaxManagePromotionReports" namespace="/exam"/>
						<sj:a href="%{mngPromotionReports}" targets="examinationSecContent" id="promotionReportNav">
							Manage Promotion Reports
						</sj:a>
					</li>
				</ul>
			</div>
		</div>
		<div id="examinationSecContent" class="grid_14 alpha">
			<jsp:include page="/WEB-INF/pages/exam/admin/ajaxManageStudentMarks.jsp"></jsp:include>
		</div>
		</div>
	</div>
</div>
<script type="text/javascript">
changePageTitle("Examination Section");
$(document).ready(function(){
	 $('#adminStaffAndStudent').addClass('current');
	 $('div#sideMenu ul li:first-child').addClass('active');
	 if($('div#sideMenu ul li:first-child').hasClass("selected").toString()){
		 $('div#sideMenu ul ').find('li:first-child a').click();
	}
});
</script>