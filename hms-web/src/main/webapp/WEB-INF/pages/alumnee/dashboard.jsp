<%@ include file="/common/taglibs.jsp"%>
<div class="row">
    <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12" id="eventsDiv">
		<div class="dashboard-stat green">
			<div class="visual">
				<i class="fa fa-calendar"></i>
			</div>
			<div class="details">
				<div class="boxTemplate">
					<s:url id="urlStudentAcademics" action="ajaxManageStudentExamSchedulesAndResults" namespace="/exam" />
					<sj:a  href="%{urlStudentAcademics}" targets="mainContentDiv" cssClass="ajaxify ESR">Upcoming Events</sj:a></div>
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12" id="postDiv">
		<div class="dashboard-stat purple">
			<div class="visual">
				<i class="fa fa-twitter-square"></i>
			</div>
			<div class="details">
				<div class="boxTemplate">
				   <s:url id="urlclassMatesLink" action="ajaxGetClassMatesList" namespace="/student" />
				   <sj:a  href="%{urlclassMatesLink}" targets="mainContentDiv" cssClass="ajaxify CL"> New Posts</sj:a>
				</div>
			</div>
		</div>
	</div>
	 <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12" id="discussionDiv">
		<div class="dashboard-stat mediumPurple">
			<div class="visual">
				<i class="fa fa-dribbble"></i>
			</div>
			<div class="details">
				<div class="boxTemplate">	
					<s:url id="urlFeeDetails" action="ajaxViewMyRecommendations" namespace="/student" />
					<sj:a href="%{urlFeeDetails}" targets="mainContentDiv" cssClass="ajaxify PE">Discussions</sj:a></div>
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12" id="photosDiv">
		<div class="dashboard-stat red-inteBlue">
			<div class="visual">
				<i class="fa fa-camera"></i>
			</div>
			<div class="details">
				<div class="boxTemplate">
					<s:url id="urlInboxesDetails" action="ajaxDoInboxGetScrapMessagesList" namespace="/common"></s:url>
					<sj:a href="%{urlInboxesDetails}" targets="mainContentDiv" cssClass='ajaxify'>New Photos</sj:a>
				</div>
			</div>
		</div>
	</div>
</div> 
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Alumnee
				</div>
			</div>
			<div class="portlet-body">
					<div id="socialFriendsDiv" class="tab-content">
				 		<div> 
					  		<jsp:include page="/WEB-INF/pages/alumnee/ajaxViewAllSocialFriendsStatus.jsp"></jsp:include>
					     </div>
					    <%--  <div>
					  			<jsp:include page="/WEB-INF/pages/alumnee/ajaxDashboardViewAllVideos.jsp" />
					     </div> --%>
					     <div>
					  			<jsp:include page="/WEB-INF/pages/alumnee/ajaxDashboardTopAlumneeFriends.jsp" />
					     </div>
					</div>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">
$('div#eventsDiv').click(function() {
	window.location.hash = "target=USTM.ajaxify EVENT";
	$('a#dashboard').parent('li').removeClass('start active');
	$('a#SOD').parent('li').addClass('start active');
	$('a#eventsHome').click();
});
$('div#photosDiv').click(function() {
	window.location.hash = "target=USTM.ajaxify PHOTO";
	$('a#dashboard').parent('li').removeClass('start active');
	$('a#SOD').parent('li').addClass('start active');
	$('a#galleryHome').click();
});

$('div#discussionDiv').click(function() {
	window.location.hash = "target=USTM.ajaxify DIS";
	$('a#dashboard').parent('li').removeClass('start active');
	$('a#SOD').parent('li').addClass('start active');
	$('a#discussionsHome').click();
});

$('div#postDiv').click(function() {
	window.location.hash = "target=USTM.ajaxify SOCI";
	$('a#dashboard').parent('li').removeClass('start active');
	$('a#SOD').parent('li').addClass('start active');
	$('a#socialHome').click();
});
</script>