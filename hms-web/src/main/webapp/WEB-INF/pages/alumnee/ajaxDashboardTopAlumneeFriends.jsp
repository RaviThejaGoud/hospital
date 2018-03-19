<%@ include file="/common/taglibs.jsp"%>
<style type="text/css">
	@import url("${pageContext.request.contextPath}/styles/newCss/pages/todo.css");
</style>
<div class="row">
	<div class="col-md-12">
		 <div class="portlet-body">
			<div class="tabbable-custom ">
				<ul class="nav nav-tabs ">
					<li class="active" style="float: left;"><a href="#tab_5_1" data-toggle="tab">Top Alumnee</a></li>
				</ul>
				<div class="tab-content"> 

    				<script src="${pageContext.request.contextPath}/scripts/newScripts/jssor.slider-20.mini.js"  type="text/javascript"></script>
				    <script>
				        jQuery(document).ready(function ($) {
				            
				            var jssor_1_options = {
				              $AutoPlay: true,
				              $ArrowNavigatorOptions: {
				                $Class: $JssorArrowNavigator$
				              },
				              $ThumbnailNavigatorOptions: {
				                $Class: $JssorThumbnailNavigator$,
				                $Cols: 4,
				                $SpacingX: 4,
				                $SpacingY: 4,
				                $Orientation: 2,
				                $Align: 0
				              }
				            };
				            
				            var jssor_1_slider = new $JssorSlider$("jssor_1", jssor_1_options);
				            
				            //responsive code begin
				            //you can remove responsive code if you don't want the slider scales while window resizes
				            function ScaleSlider() {
				                var refSize = jssor_1_slider.$Elmt.parentNode.clientWidth;
				                if (refSize) {
				                    refSize = Math.min(refSize, 810);
				                    jssor_1_slider.$ScaleWidth(refSize);
				                }
				                else {
				                    window.setTimeout(ScaleSlider, 30);
				                }
				            }
				            ScaleSlider();
				            $(window).bind("load", ScaleSlider);
				            $(window).bind("resize", ScaleSlider);
				            $(window).bind("orientationchange", ScaleSlider);
				            //responsive code end
				        });
				    </script>

				    <div id="jssor_1" style="position: relative; margin: 0 auto; top: 0px; left: 0px; width: 810px; height: 300px; overflow: hidden; visibility: hidden; background-color: #000000;">
				        <!-- Loading Screen -->
				        <div data-u="loading" style="position: absolute; top: 0px; left: 0px;">
				            <div style="filter: alpha(opacity=70); opacity: 0.7; position: absolute; display: block; top: 0px; left: 0px; width: 100%; height: 100%;"></div>
				            <div style="position:absolute;display:block;background:url('img/loading.gif') no-repeat center center;top:0px;left:0px;width:100%;height:100%;"></div>
				        </div>
				        <div data-u="slides" style="cursor: default; position: relative; top: 0px; left: 0px; width: 308px; height: 300px; overflow: hidden;">
				            <div style="display: none;">
				                <img data-u="image" src="../img/blog/12.jpg" />
				                <div data-u="thumb">
				                    <img class="i" src="../img/blog/12.jpg" />
				                    <div class="t">Chinni Venkat</div>
				                    <div class="c">Icebreakers, books, parent letters and more that are perfect for the first day and beyond. | See more about First Day Of School,</div>
				                </div>
				            </div>
				            <div style="display: none;">
				                <img data-u="image" src="../img/blog/13.jpg" />
				                <div data-u="thumb">
				                    <img class="i" src="../img/blog/13.jpg" />
				                    <div class="t">Jon's smith</div>
				                    <div class="c">Education.com's team has put together an awesome collection of creative school activities and educational games for high school students,</div>
				                </div>
				            </div>
				            <div style="display: none;">
				                <img data-u="image" src="../img/blog/14.jpg" />
				                <div data-u="thumb">
				                    <img class="i" src="../img/blog/14.jpg" />
				                    <div class="t">Rajesh</div>
				                    <div class="c">An after-school activity is any organized program which invites youth to participate outside of the traditional school day.</div>
				                </div>
				            </div>
				            <div style="display: none;">
				                <img data-u="image" src="../img/blog/15.jpg" />
				                <div data-u="thumb">
				                    <img class="i" src="../img/blog/15.jpg" />
				                    <div class="t">Ramaiha</div>
				                    <div class="c">30+ professional themems + growing</div>
				                </div>
				            </div>
				            <div style="display: none;">
				                <img data-u="image" src="../img/blog/18.jpg" />
				                <div data-u="thumb">
				                    <img class="i" src="../img/blog/18.jpg" />
				                    <div class="t">Siva Raju</div>
				                    <div class="c">Extracurricular activities or Extra Academic Activity (EAA) are those that fall outside the realm of the normal curriculum of school</div>
				                </div>
				            </div>
				        </div>
				        <!-- Thumbnail Navigator -->
				        <div data-u="thumbnavigator" class="jssort11" style="position:absolute;left:308px;top:0px;font-family:Arial, Helvetica, sans-serif;-moz-user-select:none;-webkit-user-select:none;-ms-user-select:none;user-select:none;right:5px;width:500px;height:300px;" data-autocenter="2">
				            <!-- Thumbnail Item Skin Begin -->
				            <div data-u="slides" style="cursor: default;">
				                <div data-u="prototype" class="p">
				                    <div data-u="thumbnailtemplate" class="tp"></div>
				                </div>
				            </div>
				            <!-- Thumbnail Item Skin End -->
				        </div>
				        <!-- Arrow Navigator -->
				        <span data-u="arrowleft" class="jssora02l" style="top:123px;left:8px;width:55px;height:55px;" data-autocenter="2"></span>
				        <span data-u="arrowright" class="jssora02r" style="top:123px;right:218px;width:55px;height:55px;" data-autocenter="2"></span>
				        <a href="http://www.jssor.com" style="display:none">Jssor Slider</a>
				    </div>
				</div>
			</div>
	</div>
</div>
</div> 
