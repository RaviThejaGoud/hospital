<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Quiz
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<li>
							<s:url id="urlDoStaffQuesdtionResults"
								action="ajaxDoStaffQuestionResults" namespace="/staff"/>
							<sj:a id="urlDoStaffQuesdtionResults"
								href="%{urlDoStaffQuesdtionResults}" targets="quizResultsDiv"
								data-toggle="tab">Results</sj:a>
						</li>
						<s:if test='%{#session.previousYear == "N"}'>
							<li>
								<s:url id="urlDoAddNewQuizQuestion"
									action="ajaxDoAddNewQuizQuestion" namespace="/staff"/>
								<sj:a id="urlDoAddNewQuizQuestion"
									href="%{urlDoAddNewQuizQuestion}" targets="quizResultsDiv"
									data-toggle="tab">Create Question</sj:a>
							</li>
							<li>
								<s:url id="urlDoAddNewQuiz" action="ajaxDoAddNewQuiz"  namespace="/staff"/>
								<sj:a id="urlDoAddNewQuiz" href="%{urlDoAddNewQuiz}"
									targets="quizResultsDiv" data-toggle="tab">Create Quiz</sj:a>
							</li>
						</s:if>
						<li class="active" id="urlDoAddNewQuiz">
							<s:url id="studentAssignment" action="ajaxDoGetCreateQuiz"
								namespace="/staff" />
							<sj:a href="%{studentAssignment}" targets="mainContentDiv"
								data-toggle="tab" cssClass="ajaxify PQZ">
									Quiz</sj:a>
						</li>
					</ul>
					<div id="quizDiv"></div>
					<div id="quizResultsDiv" class="tab-content">
						<jsp:include
							page="/WEB-INF/pages/staff/quiz/viewStaffQuizLists.jsp"></jsp:include>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script Language="Javascript1.2" type="text/javascript">
$(document).ready(function() {
	changePageTitle("Quiz ");
});
</script>