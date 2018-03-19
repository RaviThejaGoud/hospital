<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
	 <div class="portlet-body">
			<div class="tabbable-custom ">
				<ul class="nav nav-tabs ">
					<li class="active" style="float: left;"><a href="#tab_5_1" data-toggle="tab">Alumnee Videos</a></li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane active" id="tab_5_1">
						 <div class="row mix-grid">
							<div class="col-md-3 col-sm-4 mix category_2 category_1">
								<video width="300" controls
									poster="https://archive.org/download/WebmVp8Vorbis/webmvp8.gif">
									<source
										src="https://archive.org/download/WebmVp8Vorbis/webmvp8_512kb.mp4"
										type="video/mp4">
									Your browser doesn't support this video tag.
								</video>
							</div>
							<div class="col-md-3 col-sm-4 mix category_2 category_1">
								<video  width="300" controls src="http://media.w3.org/2010/05/bunny/movie.ogv">
									Your user agent does not support the this Video element.
								</video>
							</div>
							<div class="col-md-3 col-sm-4 mix category_2 category_1">
								<video  width="300" controls src="http://media.w3.org/2010/05/bunny/movie.ogv">
									Your user agent does not support the this Video element.
								</video>
							</div>
							<div class="col-md-3 col-sm-4 mix category_2 category_1">
								<video width="300" controls src="http://media.w3.org/2010/05/bunny/movie.ogv">
									Your user agent does not support the this Video element.
								</video>
							</div>
						</div>
						<div id="viewMoreLink" >
							<a href="#" id="viewMoreLink" class="btn green btn-xs">View More <i class="m-icon-swapright m-icon-white"></i></a>
						</div>
					</div>
				</div>
			</div>
							
	</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready( function() {
	Portfolio.init();
});
$('a#viewMoreLink').click(function() {
	var url = jQuery.url.getChatURL("/alumnee/photosHome.do?id=galleryHome");
		window.location.href = url;
	}); 
</script>