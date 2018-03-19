<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>My Knowledge Videos
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<li>
							<s:url id="urlSearchKVideosLink" action="ajaxDoSearchKVideos" namespace="/admin"/>
							<sj:a href="%{urlSearchKVideosLink}" targets="myKVideosContent" data-toggle="tab" >Search Knowledge Videos</sj:a>
						</li>
						 <li class="active">
							<s:url id="viewPlayList" action="ajaxGetKhanPlayList" namespace="/admin">
							</s:url>
							<sj:a  href="%{viewPlayList}" targets="mainContentDiv" data-toggle="tab">View Knowledge Videos</sj:a> 	
						 </li>
					</ul>
					<div id="myKVideosContent" class="tab-content">
						<p>
							<font size="4" face="verdana" color="blue">Browse our library
								of over 400 educational videos...</font>
						</p>
							<s:if test="%{subjectsList != null && !subjectsList.isEmpty()}">
									<s:iterator value="subjectsList">
									<s:if test='%{subjectName != null && subjectName!=""}'>
										<div class='col-md-3'>
											<s:url id="playSelectedVideo" action="ajaxGetKhanTopics" includeParams="all" escapeAmp="false" namespace="/common">
												<s:param name="subjectName" value="%{subjectName}"></s:param>
											</s:url>
											<sj:a href="%{playSelectedVideo}"  targets="myKVideosContent" >
												<s:property value="subjectName" />
											</sj:a>
										</div>
										</s:if> 
									</s:iterator>
							</s:if>
						<s:else>
							<div class="alert alert-info">
								kVideos not prepared.
							</div>
						</s:else>
						<div class="spaceDiv">&nbsp;</div>
						<div id="myKVideosContentDiv"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle('Knowledge Videos');
	$(".khanTopicsDetails").click(function()
	{
		$(".khanTopicsDetailsBody").slideToggle(600);
	});
	$(".khanTopicsDetailsBody").hide();
});
function getPlayListVideosList(playListId) {
	var pars = "playListId=" + playListId;
	$("#kvideoResponseDiv")
			.html( '<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	var url = jQuery.url.getChatURL("/admin/ajaxGetPlayListVideosList.do");
	$.ajax( {
		url : url,
		cache : false,
		data : pars,
		success : function(html) {
			$("#kvideoResponseDiv").html(html);
		}
	});
}
</script>
 