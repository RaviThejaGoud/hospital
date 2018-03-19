<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="tab-content">
		<div class="row mix-grid">
			<s:if test="%{tempList != null && !tempList.isEmpty()}">
			 	<s:iterator value="tempList">
		 				<div class="col-md-3 col-sm-4 mix category_2 category_1">
							<div class="mix-inner" style="height:140px;">
								<a rel="gallery" class="fancybox" href="#"><img class="img-responsive" src="${pageContext.request.contextPath}/<s:property value="imagePath"/><s:property value="imageName"/>" alt="" class="img-responsive" style="height: 150px;width:290px;"></a>
								<div class="mix-details">
									<h4> <s:property value="imageName" /></h4>
									<a rel="gallery" class="mix-preview fancybox-button fancybox" style="left:33%;"  href='${pageContext.request.contextPath}/<s:property value="imagePath" /><s:property value="imageName"/>'> <i class="fa fa-search"></i>&nbsp;View</a>
								</div>
							</div>
						</div>
					</s:iterator>
				</s:if>
			</div>
		</div>
	</div>
</div>
	<script type="text/javascript">
		$(document).ready(function() {
			Portfolio.init();
			changePageTitle("View photos");
		});
		$(".fancybox").fancybox({
		    helpers : {
		        title: {
		            type: 'inside',
		            position: 'top'
		        }
		    },
		    nextEffect: 'fade',
		    prevEffect: 'fade'
		}); 
	</script>