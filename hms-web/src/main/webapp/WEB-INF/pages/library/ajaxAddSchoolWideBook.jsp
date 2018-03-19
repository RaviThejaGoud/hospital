<%@ include file="/common/taglibs.jsp"%>
<s:form id="addSchoolWideBooks" action="ajaxAddSchoolWideBooks" method="post" theme="simple" cssClass="form-horizontal" namespace="/library">
	<div class="form-body">
		<h4 class="bold pageTitle">
			Create Book
		</h4>
		<div class="row">
			<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-4">
					<span class="required">*</span>Book Name :
				</label>
				<div class="col-md-8">
					<sj:textfield name="bookTitle.bookName" id="bookName" 
						cssClass="required form-control input-medium as-input"
						maxlength="40"></sj:textfield>
				</div>
			</div>
			</div>
			<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-4">
					Author :
				</label>
				<div class="col-md-8">
					<sj:textfield name="bookTitle.author" id="author" 
						cssClass="form-control input-medium as-input " maxlength="40"></sj:textfield>
				</div>
			</div>
			</div>
		</div>
		<div>
			&nbsp;
		</div>
		<div class="row">
			<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-4">
				<span class="required">*</span>Publisher :
				</label>
				<div class="col-md-8">
					<sj:textfield name="bookTitle.publisher" id="publisher" maxlength="60"
						cssClass="required form-control input-medium as-input" ></sj:textfield>
				</div>
			</div>
			</div>
			<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-4">
					One Book Cost :
				</label>
				<div class="col-md-8">
					<sj:textfield name="bookTitle.cost" id="cost" 
						cssClass="form-control input-medium as-input" maxlength="40"></sj:textfield>
				</div>
			</div>
			</div>
		</div>
		<div>
			&nbsp;
		</div>
		<div class="row">
			<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-4">
					Total Books :
				</label>
				<div class="col-md-8">
					<sj:textfield name="bookTitle.noOfCopies" id="totalBookCount1" 
						cssClass="form-control input-medium as-input" maxlength="3"></sj:textfield>
				</div>
				</div>
			</div>
			<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-4">
					No.of Reading Books :
				</label>
				<div class="col-md-8">
					<sj:textfield name="bookTitle.readingBookCount"
						id="readingBookCount1" 
						cssClass="form-control input-medium as-input" maxlength="3"></sj:textfield>
				</div>
				</div>
			</div>
		</div>
		<div>
			&nbsp;
		</div>
		<div class="row">
			<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-4">
					<span class="required">*</span>No.of Issuing Books :
				</label>
				<div class="col-md-8">
					<sj:textfield name="bookTitle.issueBookCount" id="issueBookCount1" 
						cssClass="required form-control input-medium as-input" maxlength="3"></sj:textfield>
				</div>
				</div>
			</div>
			<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-4">
					Issue-Days :
				</label>
				<div class="col-md-8">
					<sj:textfield name="bookTitle.issueDays" id="issueDays" 
						cssClass="form-control input-medium as-input" maxlength="11"></sj:textfield>
				</div>
				</div>
			</div>
		</div>
		<div>
			&nbsp;
		</div>
		<div class="row">
			<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-4">
					<span class="required">*</span>Book Requested-Days :
				</label>
				<div class="col-md-8">
					<sj:textfield name="bookTitle.bookRequestExpareDays" id="requested" 
						cssClass="required form-control input-medium as-input" maxlength="1"></sj:textfield>
				</div>
				</div>
			</div>
			<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-4">
					Book Label :
				</label>
				<div class="col-md-8">
					<sj:textfield name="bookTitle.bookKeyWord" id="bookLable"
						cssClass="form-control input-medium as-input" maxlength="4" />
						<span class="hintMessage">(EX:CSS,GD,XXX,Short cuts...)</span>
				</div>
				</div>
			</div>
		</div>
		<div>
			&nbsp;
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						<span class="required">*</span>Select Subject Name :
					</label>
					<div class="col-md-7">
						<div class="radio-list">
							<label class="radio-inline">
								<input type="radio" name="type" id="validSubject" onclick=subjectChange('N'); value="N" checked>
								SubjectName
							</label>
							<label class="radio-inline">
								<input type="radio" name="type" onclick=subjectChange(this.value); value="R" id="otherNameSubject">
								Other Books
							</label>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div>
			&nbsp;
		</div>
		<div class="row">
			<div class="col-md-6">
			<div class="form-group" id="subjectNames">
				<label class="control-label col-md-4">
					<span class="required">*</span>Subject Name :
				</label>
				<div class="col-md-8">
					<s:select id="subject" list="subjectsList" 
						listKey="id" listValue="name" headerKey=""
						headerValue="- Select -" name="subjectId"
						onchange="javascript:onChangeGetBlocksByRacks(this);"
						cssClass="required form-control input-medium as-input" />
				</div>
				</div>
			</div>
			<div class="col-md-6">
			<div class="form-group" id="className">
				<label class="control-label col-md-4">
					Class Name :
				</label>
				<div class="col-md-8">
					<s:select list="classList" listKey="id" listValue="className"
						cssClass="required form-control input-medium"
						headerValue="- Select className -" name="classId" headerKey="" />
				</div>
				</div>
			</div>
		</div>
		<div>
			&nbsp;
		</div>
		<div class="row">
			<div class="col-md-6">
			<div class="form-group" id="otherSubject" style="display: none">
				<label class="control-label col-md-4">
					Other Books :
				</label>
				<div class="col-md-8">
					<s:select id="otherSubject" list="objectList" 
						listKey="otherSubjects" listValue="otherSubjects" headerKey=""
						headerValue="- Select -" name="otherSubject"
						cssClass="required form-control input-medium as-input"
						onchange="javascript:onChangeGetOtherBlocksByRacks(this.value);" />
				</div>
				</div>
			</div>
			<div class="col-md-7">
				<div id="resultsDiv" style="display: none; padding-top: 5px;">
					<jsp:include
						page="/WEB-INF/pages/library/block/ajaxShowBlocks.jsp" />
				</div>
			</div>
		</div>
		<div>
			&nbsp;
		</div>
		<div class="form-actions fluid">
			<div class="col-md-6">
				<div class="col-md-offset-4 col-md-5">
					<sj:submit   cssClass="submitBt btn blue" value="Submit"
						onBeforeTopics="addingBooksLabels" validate="true"
						targets="mainContentDiv" formIds="addSchoolWideBooks" />
					<s:url id="doCancelBook" action="ajaxLibraryHome"
						includeParams="all" escapeAmp="false" namespace="/library">
					</s:url>
					<sj:a href="%{doCancelBook}" cssClass="btn default"
						indicator="indicator" targets="mainContentDiv">Cancel</sj:a>
				</div>
			</div>
		</div>
	</div>
</s:form>
<script type="text/javascript">
$("input:checkbox, input:radio").uniform();
changePageTitle("Add School Wide Book");
$('#bookName').focus();
$('.numeric').numeric();
$('.alphabet').alpha();
function subjectChange(clickButton) {
	var frequency = clickButton;
	if (frequency == 'N') {
		$('select#subject').val("");
		$("#otherSubject").hide();
		$("#subjectNames").show();
		$("#resultsDiv").hide();
		$("#className").show();
	} else {
		if (frequency == 'R') {
			$('select#otherSubject').val("");
			$("#otherSubject").show();
			$("#subjectNames").hide();
			$("#resultsDiv").hide();
			$("#className").hide();
		}
	}
}

$
		.subscribe(
				'addingBooksLabels',
				function(event, data) {
					if ($('#addSchoolWideBooks').valid()) {
						var sReadingBookCount = $("input#readingBookCount1")
								.val();
						var sIssueBookCount = $("input#issueBookCount1").val();
						if (isNonEmpty(sReadingBookCount)
								&& isNonEmpty(sIssueBookCount)) {
							var totalCount = Number(sReadingBookCount)
									+ Number(sIssueBookCount)
							var totalBookCount = $("input#totalBookCount1")
									.val();
							if (totalBookCount == 0) {
								alert('Please give total count  is more then 0 value.');
								event.originalEvent.options.submit=false;
							}
							if ($("div.subjectDiv").children().length == 0) {
								alert('Please create blocks for this subject.');
								event.originalEvent.options.submit=false;
							}
							if (totalCount == totalBookCount) {
								if ($('input#validSubject').is(':checked')) {
									if ($("select#subject").val() == "") {
										alert('Please select subject name.');
										event.originalEvent.options.submit=false;
									} else if (isNonEmpty($("select#subject")
											.val())
											&& $(
													'select#blockId>option:selected')
													.val() == "M") {
										alert('Please create block for this subject.');
										event.originalEvent.options.submit=false;
									} else if ($(
											'select#addSchoolWideBooks_classId')
											.val() == "") {
										alert('Please  select className.');
										event.originalEvent.options.submit=false;
									} else if ($('select#blockId').val() == "M") {
										alert('Please  select block name');
										event.originalEvent.options.submit=false;
									} else if ($('select#tempId').val() == "R") {
										alert('Please  select rack name.');
										event.originalEvent.options.submit=false;
									}

								} else if ($('input#otherNameSubject').is(
										':checked')) {
									if ($("select#otherSubject").val() == "") {
										alert("Please select otherSubject name.");
										event.originalEvent.options.submit=false;
									} else if ($('select#otherBlockId').val() == "M") {
										alert('Please  select block name');
										event.originalEvent.options.submit=false;
									} else if ($('select#tempId').val() == "R") {
										alert('Please  select rack name.');
										event.originalEvent.options.submit=false;
									}
								}
							} else {
								alert("Please give readingbooks and issuedbooks count equals to total count.");
								event.originalEvent.options.submit=false;
							}
						}
					}
				});

function onChangeGetBlocksByRacks(selectBox) {
	if (selectBox.value == '') {
		$('#otherSubject').show();
		$('#resultsDiv').hide();
	} else {
		$('#otherSubject').hide();
		var subjectId = selectBox.value;
		var url = jQuery.url.getChatURL("/library/ajaxGetBlockBySuibject.do");
		if (subjectId.length == 0) {
			alert("!Oops subjectName.");
		} else {
			$("#resultsDiv")
					.html(
							'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			var pars = "subjectId=" + subjectId;
			$
					.ajax( {
						url : url,
						cache : false,
						data : pars,
						success : function(html) {
							$("#resultsDiv").html(html);
							document.getElementById('resultsDiv').style.display = "block";
						}
					});
		}
	}
}

function onChangeGetOtherBlocksByRacks(selectBox) {
	if (selectBox == '') {
		$('#subjectNames').show();
		$('#resultsDiv1').hide();
	} else {
		$('#subjectNames').hide();
		var subjectName = selectBox;
		var url = jQuery.url
				.getChatURL("/library/ajaxGetBlockByOtherSuibject.do");
		if (subjectName.length == 0) {
			alert("!Oops subjectName.");
		} else {
			$("#resultsDiv")
					.html(
							'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			var pars = "plSubjectName=" + subjectName;
			$
					.ajax( {
						url : url,
						cache : false,
						data : pars,
						success : function(html) {
							$("#resultsDiv").html(html);
							document.getElementById('resultsDiv').style.display = "block";
						}
					});
		}
	}
}
$("#issueDays").change(function() {
	if ($(this).val() > 0) {
		return true;
	} else {
		alert("Please give issue-days  more then 0");
		$('#issueDays').val('');
		event.originalEvent.options.submit=false;
	}
});
/*this is used in chrome and IE*/
$("#cost").change(function() {
	var cost = ($(this).val()).replace('.', '');
	if (Math.floor(cost) != cost) {
		alert("Please enter numbers.");
		$("#cost").val('');
		event.originalEvent.options.submit=false;
	}
});
$("#totalBookCount1").change(function() {
	var totalBookCount1 = ($(this).val());
	if (Math.floor(totalBookCount1) != totalBookCount1) {
		alert("Please enter numbers.");
		$("#totalBookCount1").val('');
		event.originalEvent.options.submit=false;
	}
});
/*this is used in chrome and IE*/
$("#readingBookCount1").change(function() {
	var readingBookCount1 = ($(this).val());
	if (Math.floor(readingBookCount1) != readingBookCount1) {
		alert("Please enter numbers.");
		$("#readingBookCount1").val('');
		event.originalEvent.options.submit=false;
	}
});
$("#issueBookCount1").change(function() {
	var issueBookCount1 = ($(this).val());
	if (Math.floor(issueBookCount1) != issueBookCount1) {
		alert("Please enter numbers.");
		$("#issueBookCount1").val('');
		event.originalEvent.options.submit=false;
	}
});
$("#issueDays").change(function() {
	var issueDays = ($(this).val());
	if (Math.floor(issueDays) != issueDays) {
		alert("Please enter numbers.");
		$("#issueDays").val('');
		event.originalEvent.options.submit=false;
	}
});
$("#requested").change(function() {
	var requested = ($(this).val());
	if (Math.floor(requested) != requested) {
		alert("Please enter numbers.");
		$("#requested").val('');
		event.originalEvent.options.submit=false;
	}
});
</script>

