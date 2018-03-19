<%@ include file="/common/taglibs.jsp"%>
<style>
	@import url("${pageContext.request.contextPath}/plugins/jquery-mixitup/image-picker.css");
</style>
<jsp:include page="/common/messages.jsp" />

<s:hidden name="tempString" id="tempString"></s:hidden>
 <s:if test="%{tempList != null && !tempList.isEmpty()}"> 
	<!-- <div class="row">
		<div class="col-md-6">
				<div class="form-group ">
			<label class="control-label col-md-4">Select Photos :</label><p>
				<span class="label label-danger"> NOTE : </span>&nbsp; Please click on image to select and submit.</p>
			</div>
		</div>
	</div> -->
    <div class="picker" style="background: #eee;padding: 10px;"  id="inner-content-div">
      <select multiple="multiple" class="image-picker show-html">
   			 <s:iterator value="tempList">
					<div class="mix-inner">
						 <option data-img-src='<c:url value="${hrefAttachmentFilePath}"/>' value='<s:property value="%{id}"/>' class="photoId"></option>
					</div>
			</s:iterator>
	    </select>
    </div>
   </s:if>
   <s:elseif test="%{!eventsAlbum.albumAttachment.isEmpty()}"> 
		    <div class="picker mix-grid" style="padding: 10px;"  id="inner-content-div">
				<s:iterator value="eventsAlbum.albumAttachment">
					<div class="col-md-3 col-sm-4 mix category_2 category_1">
						<div class="mix-inner"   style="height:140px;">
							<a rel="gallery"  class="fancybox" href="<c:url value='${hrefAttachmentFilePath}'/>"> <img src='<c:url value="${hrefAttachmentFilePath}"/>' class="img-responsive" style="height: 150px;width:290px;"/></a>
							<div class="mix-details">
								<h4> <s:property value="eventsAlbum.albumName" /></h4>
								<s:url id="removePhotos" action="ajaxDeleteEditViewPhotos"
								includeParams="all" escapeAmp="false" namespace="/admin">
								<s:param name="anyId" value="%{id}"></s:param>
								<s:param name="anyTitle">deleteUpdateAlbum</s:param>
							</s:url> 
							<s:div class="mix-link" cssStyle=" margin-right: 5px; right: 50%;background: none repeat scroll 0 0 red;color: #fff;cursor: pointer;display: block;margin-top: 10px;padding: 10px 15px;position: absolute;"
								onclick="javascript:confirmDialogWithTarget(this,'selectPhotosDiv');"
								id='%{removePhotos}' theme="simple"
								title="Delete this Photo">
								<i class="fa fa-trash-o"></i> Delete
							</s:div>
								<a rel="gallery" class="mix-preview fancybox-button fancybox" href="<c:url value='${hrefAttachmentFilePath}'/>"> <i class="fa fa-search"></i>&nbsp;View</a>
							</div>
						</div>
					</div>
			    </s:iterator>
		    </div>
   </s:elseif>
 <s:else>
	<div class="alert alert-info">
		Currently there are no photos. Please add photos and create album.
	</div>
</s:else>
<script type="text/javascript">
$(document).ready(function() {
	$('.image-picker').hide();
	Portfolio.init();
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