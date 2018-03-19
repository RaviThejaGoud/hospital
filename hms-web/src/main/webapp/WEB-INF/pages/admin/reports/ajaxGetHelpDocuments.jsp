<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Help Documents and Videos
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<li class="active"><a data-toggle="tab" href="#tab_2_2">Help
								Documents</a></li>
						<li><a data-toggle="tab" href="#tab_1_3">videos</a></li>
					</ul>
					<div id="studentLibraryContentDiv" class="tab-content">
						<div id="tab_2_2" class="tab-pane active">
							<div class="spaceDiv"></div>
							<a
								href="${pageContext.request.contextPath}/userFiles/Help Documents/ID Cards Help Document.pdf"
								target="_new" title="Download">1. <b>Students Id Cards
									Help Document</b></a>
							<div class="spaceDiv"></div>
							<a
								href="${pageContext.request.contextPath}/userFiles/Help Documents/Promotion Students Help Document.pdf"
								target="_new1" title="Download">2. <b>Promotion Students
									Help Document</b></a>
							<div class="spaceDiv"></div>
							<a
								href="${pageContext.request.contextPath}/userFiles/Help Documents/Academics Help Document.pdf"
								target="_new2" title="Download">3. <b>Academics Help
									Documents</b></a>
							<div class="spaceDiv"></div>
							<a
								href="${pageContext.request.contextPath}/userFiles/Help Documents/Examination Section Help Document.pdf"
								target="_new3" title="Download">4. <b>Examination
									Section Help Document</b></a>
							<div class="spaceDiv"></div>
							<a
								href="${pageContext.request.contextPath}/userFiles/Help Documents/TimeTable Help Document.pdf"
								target="_new4" title="Download">5. <b>TimeTable Help Document</b></a>
							<div class="spaceDiv"></div>
						</div>
						<div id="tab_1_3" class="tab-pane">
							 <a data-toggle="modal"  href="#responsive"  onclick="javascript:PopupDayBookDetials();">1. Library Videos
							</a> 
							<!--  1. <a href="http://www.screencast.com/t/RDz6n7Vv" target="_new" >Library
								Videos</a> -->
							 <%-- <jsp:include
									page="/WEB-INF/pages/admin/secretary/ajaxFpsErrorLog.jsp"></jsp:include>  --%>
									
									
									
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="responsive"></div>
<script type="text/javascript">
function PopupDayBookDetials() {
		var url = jQuery.url.getChatURL("/admin/ajaxLibraryVideos.do");
		$('#responsive').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
				url : url,
				cache : false,
				success : function(html) {
					$("#responsive").html(html);
				}
			});
		}
$('html, body').animate({ scrollTop: $(document).height() - $(window).height() }, 10, function() {
	    $(this).animate({ scrollTop: 0 }, 10);
	});
</script>	