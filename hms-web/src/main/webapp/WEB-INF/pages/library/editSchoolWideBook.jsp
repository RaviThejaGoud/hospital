<%@ include file="/common/taglibs.jsp"%>
	<s:form id="schoolEditBooks" action="ajaxEditSchoolWideBook" method="post" theme="simple" cssClass="form-horizontal" namespace="/library">
		<s:hidden name="bookId" value="%{bookTitle.id}"></s:hidden>
		<div class="form-body">
			<h4 class="bold pageTitle">
				Update Books
			</h4>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							<span class="required">*</span>Book Name :
						</label>
						<div class="col-md-8">
							<sj:textfield name="bookTitle.bookName" id="bookName"
								required="true"
								cssClass="form-control input-medium as-input"
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
							<sj:textfield name="bookTitle.publisher" id="publisher"
								required="true" cssClass="form-control input-medium as-input"
								maxlength="60"></sj:textfield>
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
							<sj:textfield name="bookTitle.issueBookCount"
								id="issueBookCount1" required="true"
								cssClass="form-control input-medium as-input" maxlength="3"></sj:textfield>
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
							<sj:textfield name="bookTitle.bookRequestExpareDays"
								id="requested" required="true"
								cssClass="form-control input-medium" maxlength="1"></sj:textfield>
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
				<div class="col-md-7 editBooksDiv">
					<div class="form-group">
						<label class="control-label col-md-5">
							<span class="required">*</span>Select Subject Name :
						</label>
						<div class="col-md-7">
							<div class="radio-list">
								<s:if test='%{bookAndBlockDetails.rackDetails.type=="S"}'>
									<label class="radio-inline">
										<input type="radio" name="type" id="validSubject"
											onclick=subjectChange( 'N'); value="N" checked>
										SubjectName
									</label>
								</s:if>
								<s:if test='%{bookAndBlockDetails.rackDetails.type=="OS"}'>
									<label class="radio-inline">
										<input type="radio" name="type"
											onclick=subjectChange(this.value); value="R"
											id="otherNameSubject">
										Other Books
									</label>
								</s:if>
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
						<div class="col-md-6" id="editSubjectNames" style="display: none">
							<s:select id="subject" list="subjectsList" required="true"
								listKey="id" listValue="name" headerKey=""
								headerValue="- Select -" name="bookTitle.studySubject.id"
								onchange="javascript:onChangeGetEditBlocksByRacks(this);"
								cssClass="form-control input-medium" />
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
								requiredposition="first" headerValue="- Select className -"
								name="classId" headerKey="" />
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
						<div class="col-md-6" id="editOtherSubject" style="display: none">
							<s:select id="otherSubject" list="tempList" required="true"
								listKey="otherSubjects" listValue="otherSubjects" headerKey=""
								headerValue="- Select -"
								name="bookAndBlockDetails.rackDetails.otherSubjects"
								cssClass="form-control input-medium"
								onchange="javascript:onChangeGetEditOtherBlocksByRacks(this);" />
						</div>
					</div>
				</div>
				<div class="col-md-7">
				<div class="form-group" id="editResultsDiv">
					<jsp:include
						page="/WEB-INF/pages/library/block/ajaxEditShowBlocks.jsp" />
				</div>
				</div>
			</div>
			<div>
				&nbsp;
			</div>
			<div class="form-actions fluid">
				<div class="col-md-6">
					<div class="col-md-offset-10 col-md-9">
						<sj:submit   cssClass="submitBt btn blue" value="Submit"
							onBeforeTopics="formValidationForEditBooks" validate="true"
							targets="mainContentDiv" formIds="schoolEditBooks" />
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
changePageTitle("Edit School Wide Books");
$('.numeric').numeric();
$('.numericDot').numeric( {
	allow : "."
});
$
		.subscribe(
				'formValidationForEditBooks',
				function(event, data) {
					if ($('#schoolEditBooks').valid()) {
						var fReadingBookCount = '<s:property value="bookTitle.readingBookCount"/>';
						var sReadingBookCount = $("input#readingBookCount")
								.val();
						var fIssueBookCount = '<s:property value="bookTitle.issueBookCount"/>';
						var sIssueBookCount = $("input#issueBookCount").val();
						if (isNonEmpty(fReadingBookCount)
								&& isNonEmpty(sReadingBookCount)
								&& isNonEmpty(fIssueBookCount)
								&& isNonEmpty(sIssueBookCount)) {
							if (Number(fReadingBookCount) >= Number(sReadingBookCount)
									&& Number(fIssueBookCount) >= Number(sIssueBookCount)) {
								var totalCount = Number(sReadingBookCount)
										+ Number(sIssueBookCount);
								var totalBookCount = Number($(
										"input#totalBookCount").val());
								if (totalBookCount == 0) {
									alert('Please give total count  is more then 0 value.');
									"event.originalEvent.options.submit=false;"
								}
								if ($("div.editSubjectDiv").children().length == 0) {
									alert('Please create blocks for this subject.');
									"event.originalEvent.options.submit=false;"
								}
								if (totalCount == totalBookCount) {
									//alert($('select#blockId>option:selected').val());
									if ($('input#validSubject').is(':checked')) {
										if ($("select#subject").val() == "") {
											alert('Please select subject name.');
											"event.originalEvent.options.submit=false;"
										} else if (isNonEmpty($(
												"select#subject").val())
												&& $(
														'select#blockId>option:selected')
														.val() == "M") {
											alert('Please create block for this subject.');
											"event.originalEvent.options.submit=false;"
										} else if ($(
												'select#addSchoolWideBooks_classId')
												.val() == "") {
											alert('Please  select className.');
											"event.originalEvent.options.submit=false;"
										} else if ($('select#blockId').val() == "M") {
											alert('Please  select block name');
											"event.originalEvent.options.submit=false;"
										} else if ($('select#rackId').val() == "R") {
											alert('Please  select rack name.');
											"event.originalEvent.options.submit=false;"
										}
									} else if ($('input#otherNameSubject').is(
											':checked')) {
										if ($("select#otherSubject").val() == "") {
											alert("Please check otherSubject name.");
											"event.originalEvent.options.submit=false;"
										} else if ($('select#otherBlockId')
												.val() == "M") {
											alert('Please  select block name');
											"event.originalEvent.options.submit=false;"
										} else if ($('select#rackId').val() == "R") {
											alert('Please  select rack name.');
											"event.originalEvent.options.submit=false;"
										}
									}
								} else {
									alert("Please give readingbooks and issuedbooks count equals to total books count.");
									"event.originalEvent.options.submit=false;"
								}

							}
						} else if (fReadingBookCount >= sReadingBookCount
								&& fIssueBookCount >= sIssueBookCount) {
							alert("Please give readingbooks and issuedbooks count equals to total books count.");
							"event.originalEvent.options.submit=false;"
						} else {
							alert("Please give readingbooks and issuedbooks count equals to total books count.");
							"event.originalEvent.options.submit=false;"
						}

					}
				});
function onChangeGetEditBlocksByRacks(selectBox) {
	if (selectBox.value == '') {
		$('#editOtherSubject').show();
		$('#editResultsDiv').hide();
	} else {
		$('#editOtherSubject').hide();
		var subjectId = selectBox.value;
		var url = jQuery.url
				.getChatURL("/library/ajaxGetEditBlockBySuibject.do");
		if (subjectId.length == 0) {
			alert("!Oops subjectName.");
		} else {
			$("#editResultsDiv")
					.html(
							'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			var pars = "subjectId=" + subjectId;
			$
					.ajax( {
						url : url,
						cache : false,
						data : pars,
						success : function(html) {
							$("#editResultsDiv").html(html);
							document.getElementById('editResultsDiv').style.display = "block";
						}
					});
		}
	}
}
function onChangeGetEditOtherBlocksByRacks(selectBox) {
	if (selectBox.value == '') {
		$('#editSubjectNames').show();
		$('#editResultDiv1').hide();
	} else {
		$('#editSubjectNames').hide();
		var otherSubjectName = selectBox.value;
		var url = jQuery.url
				.getChatURL("/library/ajaxGetEditBlockByOtherSuibject.do");
		if (otherSubjectName.length == 0) {
			alert("!Oops otherSubjectName.");
		} else {
			$("#editResultsDiv")
					.html(
							'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			var pars = "plSubjectName=" + otherSubjectName;
			$
					.ajax( {
						url : url,
						cache : false,
						data : pars,
						success : function(html) {
							$("#editResultsDiv").html(html);
							document.getElementById('editResultsDiv').style.display = "block";
						}
					});
		}
	}
}
function subjectChange(clickButton) {
	var frequency = clickButton;
	if (frequency == 'N') {
		$("#editOtherSubject").hide();
		$("#editSubjectNames").show();
		$("#editResultsDiv").hide();
		$("#classNameDiv").show();
	} else {
		if (frequency == 'R') {
			$("#editOtherSubject").show();
			$("#editSubjectNames").hide();
			$("#editResultsDiv").hide();
			$("#classNameDiv").hide();
		}
	}
}

$(document).ready(function() {
	if ('<s:property value="bookAndBlockDetails.rackDetails.type"/>' == "S") {
		$("#editSubjectNames").show();
		$("#editOtherSubject").hide();
		$("#classNameDiv").show();
		$("#editOtherRacksDIV").show();
	} else {
		$("#editSubjectNames").hide();
		$("#editOtherSubject").show();
		$("#classNameDiv").hide();
		$("#editOtherRacksDIV").show();
	}
});
$("#readingBookCount")
		.change(
				function() {
					var fReadingBookCount = '<s:property value="bookTitle.readingBookCount"/>';
					var rebookcout = $(this).val();
					if (Number(fReadingBookCount) <= Number(rebookcout)) {
						return true;
					} else {
						alert("Please give reading book count more then "
								+ fReadingBookCount + " .");
						$('#readingBookCount')
								.val(
										'<s:property value="bookTitle.readingBookCount"/>');
						"event.originalEvent.options.submit=false;"
					}
				});
$("#issueBookCount")
		.change(
				function() {
					var fIssueBookCount = '<s:property value="bookTitle.issueBookCount"/>';
					var rebookcout = $(this).val();
					if (Number(fIssueBookCount) <= Number(rebookcout)) {
					} else {
						alert("Please give issuing book count more then "
								+ fIssueBookCount + " .");
						$('#issueBookCount')
								.val(
										'<s:property value="bookTitle.issueBookCount"/>');
						"event.originalEvent.options.submit=false;"
					}
				});
$("#issueDays").change(function() {
	if ($(this).val() > 0) {
	} else {
		alert("Please give issueDays  more then 0");
		$('#issueDays').val('<s:property value="bookTitle.issueDays"/>');
		"event.originalEvent.options.submit=false;"
	}
});

/*this is used in chrome and IE*/
$("#cost").change(function() {
	var cost = ($(this).val()).replace('.', '');
	if (Math.floor(cost) != cost) {
		alert("Please enter numbers.");
		$("#cost").val('');
		"event.originalEvent.options.submit=false;"
	}
});
$("#totalBookCount1").change(function() {
	var totalBookCount1 = ($(this).val());
	if (Math.floor(totalBookCount1) != totalBookCount1) {
		alert("Please enter numbers.");
		$("#totalBookCount1").val('');
		"event.originalEvent.options.submit=false;"
	}
});
/*this is used in chrome and IE*/
$("#issueDays").change(function() {
	var issueBookCount1 = ($(this).val());
	if (Math.floor(issueDays) != issueDays) {
		alert("Please enter numbers.");
		$("#issueDays").val('');
		"event.originalEvent.options.submit=false;"
	}
});
$("#requested").change(function() {
	var requested = ($(this).val());
	if (Math.floor(requested) != requested) {
		alert("Please enter numbers.");
		$("#requested").val('');
		"event.originalEvent.options.submit=false;"
	}
});
</script>
