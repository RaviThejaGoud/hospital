<%@ include file="/common/taglibs.jsp"%>
 <div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
				<i class="fa fa-globe"></i>
					Manage Groups
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<s:if test='%{#session.previousYear=="N"}'>
							<li>
							 	<s:url id="urlgroup" action="ajaxDoGroupVoucher" namespace="/admin"/>
								<sj:a href="%{urlgroup}"
								targets="groupsContentDiv" indicator="indicator" data-toggle="tab">Add Group</sj:a>
							</li>
						</s:if>
						<li class="active">
							<s:url id="urlViewgroup" action="ajaxViewGroupVoucher" namespace="/admin"/>
						<sj:a href="%{urlViewgroup}"
							targets="mainContentDiv" indicator="indicator" data-toggle="tab">View Group</sj:a>
						</li>
					</ul>
					<div id="groupsContentDiv" class="tab-content">
						<jsp:include page="/WEB-INF/pages/admin/academic/voucher/ajaxViewGroupVoucher.jsp"/>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>  
<script type="text/javascript">
$(document).ready(function() {
		changePageTitle("Manage Groups");
		 $('.js-activated').dropdownHover().dropdown();
	});
$('html, body').animate({ scrollTop: $(document).height() - $(window).height() }, 10, function() {
    $(this).animate({ scrollTop: 0 }, 10);
});

</script>
 