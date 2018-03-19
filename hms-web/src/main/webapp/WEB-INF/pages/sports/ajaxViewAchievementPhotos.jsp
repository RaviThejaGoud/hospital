<%@ include file="/common/taglibs.jsp"%>
<style>
	@import url("${pageContext.request.contextPath}/plugins/jquery-mixitup/image-picker.css");
</style>
<jsp:include page="/common/messages.jsp" />
   <s:if test="%{!attachmentVOList.isEmpty()}"> 
		    <div class="picker mix-grid" style="padding: 10px;"  id="inner-content-div">
				<s:iterator value="attachmentVOList">
					<div class="col-md-3 col-sm-4 mix category_2 category_1">
						<div class="mix-inner"   style="height:140px;">
							<a rel="gallery"  class="fancybox" href="<c:url value='${filePath}'/>"> <img src='<c:url value="${filePath}"/>' class="img-responsive" style="height: 150px;width:290px;"/></a>
						</div>
					</div>
			    </s:iterator>
		    </div>
   </s:if>
 <s:else>
	<div class="alert alert-info">
		Currently there are no Photos. Please add photos.
	</div>
</s:else>
<script type="text/javascript">
$(document).ready(function() {
	$('.image-picker').hide(); 
	Portfolio.init();
	$('.fancybox').click();
});

jQuery("select.image-picker").imagepicker({
    hide_select:  false,
  });
$('#inner-content-div').slimScroll({
    height: '650px',
    background:'#ddd'
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