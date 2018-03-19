<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
				<i class="fa fa-globe"></i>
					Class &amp; Sections
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						 <li>
							<s:url id="chgClassOrder" action="ajaxDoChangeClassSectionsOrder" namespace="/admin">
							</s:url>
							<sj:a id="chgClasOrder" href="%{chgClassOrder}" targets="classContentDiv" data-toggle="tab">Change Classes Order</sj:a> 	
						 </li>
						 <s:if test='%{#session.previousYear == "N"}'>
							 <li>
								<s:url id="urlAddSection" action="ajaxDoAddSection" namespace="/admin">
									<s:param name="classId" value="0" />
								</s:url>
								<sj:a id="addSub" href="%{urlAddSection}" targets="classContentDiv" data-toggle="tab">Add Section</sj:a> 	
							 </li>
							 <li>
								<s:url id="urlAddClass" action="ajaxDoAddClass" namespace="/admin">
									<s:param name="classId" value="0" />
								</s:url>
								<sj:a id="addClass" href="%{urlAddClass}" targets="classContentDiv" data-toggle="tab">Add Class</sj:a> 	
							 </li>	
						 </s:if>
						 <li class="active">
							<s:url id="urlviewSub" action="ajaxDoManageClassSections" namespace="/admin">
							</s:url>
							<sj:a id="viewSub" href="%{urlviewSub}" targets="mainContentDiv" data-toggle="tab">View Class &amp; Sections</sj:a> 	
						 </li>
					</ul>
					<div id="classContentDiv" class="tab-content">
					<%@ include file="/common/taglibs.jsp"%>	
						<jsp:include page="/WEB-INF/pages/admin/academic/class/ajaxClassSectionList.jsp"/>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Classes & Sections");
});
</script>