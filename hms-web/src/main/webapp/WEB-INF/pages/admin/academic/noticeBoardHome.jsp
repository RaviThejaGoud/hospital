<%@ include file="/common/taglibs.jsp"%>
<div class="grid_14" id="gridCenter">
	<div class="block_head">
		<h2>
			Notifications
		</h2>
		<s:if test='%{(#session.academicYear == null || #session.academicYear.isEmpty()) || (#session.previousYear=="N")}'>
		<ul>
			<li>
				<s:url id="urlPostNoticeBoard" action="ajaxDoPostNoticeBoard" />
				<sj:a id="postNoticeBoard" href="%{urlPostNoticeBoard}"
					targets="noticeBoardContent" indicator="indicator">Create Notifications</sj:a>	
			</li>
		</ul>
		</s:if>
	</div>
	<div class="block_content" id="noticeBoardContent">
		<div>
		 </div>
	</div>
</div>
<script type="text/javascript">
changePageTitle('Notifications');
</script>
		
	