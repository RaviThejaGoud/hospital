<%@ include file="/common/taglibs.jsp"%>
<jsp:useBean id="copyRightDate" class="java.util.Date" />
<fmt:formatDate var="copyRightYear" value="${copyRightDate}" pattern="yyyy" />

<div id="fb-root"></div>
<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.8";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

</script>

<div class="footer">
	<div class="col-md-5"></div>
	<div class="footer-inner">
		 ${copyRightYear} &copy; HYNIVA Consulting Services PVT Ltd. All Rights Reserved.
	</div>
	
	<div class="fb-like" data-href="https://www.facebook.com/EazySchool/" data-layout="button_count" data-action="like" data-size="small" data-show-faces="true" data-share="false"></div>
	<div class="fb-share-button" data-href="https://www.facebook.com/EazySchool/" data-layout="button_count" data-size="small" data-mobile-iframe="true">
	<a class="fb-xfbml-parse-ignore" target="_blank" href="https://www.facebook.com/sharer/sharer.php?u=https%3A%2F%2Fwww.facebook.com%2FEazySchool%2F&amp;src=sdkpreparse"></a></div>
	
	
	<div class="footer-tools">
		<span class="go-top"> <i class="fa fa-angle-up"></i> </span>
	</div>
</div>
	