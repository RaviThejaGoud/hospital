<%@ include file="/common/taglibs.jsp"%>
<div id="newLeader">
	<div>
		<s:url id="doViewTermOneSyllabus" action="ajaxDoAddSyllabus"
			includeParams="all" escapeAmp="false" namespace="/admin">
			<s:param name="scheduleId" value="%{id}" />
		</s:url>
		<sj:a href="%{doViewTermOneSyllabus}" onBeforeTopics="cleanOpenRows"
			onCompleteTopics="doInitViewSyllabus" indicator="indicator"
			targets="displayTermOneSyllabus%{id}">
											Close
		</sj:a>
	</div>
	<s:if test="%{syllabusList != null && !syllabusList.isEmpty()}">
		<div class="nonClassTeacher">
			<b> <a href="#"> Other Class Exam Schedules </a> </b>
		</div>
		<div class="nonClassTeacherBody">
			<div>
				<a href="printMySubjectExamSchedules.do" target="_new"><img
						alt="Print" title="Print" src="../images/common/printer.png">
				</a>
			</div>
			<div class="grid_13 th">
				<div class="grid_3">
					Chapter Name
				</div>
				<div class="grid_2">
					Contents
				</div>
				<div class="grid_2">
					Book Title
				</div>
				<div class="grid_2">
					Publisher Name
				</div>
				<div class="grid_2">
					Author Name
				</div>
				<div class="grid_2">
					Reference Name
				</div>
			</div>
			<s:iterator value="syllabusList">
				<div class="grid_13 row">
					<div class="grid_3">
						<s:property value="chapterName" />
					</div>
					<div class="grid_2">
						<s:property value="contentName" />
					</div>
					<div class="grid_2">
						<s:property value="bookTitle" />
					</div>
					<div class="grid_2">
						<s:property value="publisherName" />
					</div>
					<div class="grid_2">
						<s:property value="autherName" />
					</div>
					<div class="grid_2">
						<s:property value="referenceName" />
					</div>
				</div>
			</s:iterator>
		</div>
	</s:if>
	<s:else>
     No Syllabus Added for this Subject.
    </s:else>
</div>
