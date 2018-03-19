<%@ include file="/common/taglibs.jsp"%>
<div class="form-body">
	<s:form action="" id="searchText" theme="simple"
		cssClass="form-horizontal">
		<div class="form-group">
			<div class="col-md-2"></div>
			<div class="col-md-2">
				<label class="radio-inline">
					<input type="radio" id="checkAll" value="IB" name="bookIssueType"
						checked>
					Issue Books
				</label>
			</div>
			<div class="col-md-2">
				<label class="radio-inline">
					<input type="radio" value="RB" name="bookIssueType" id="checkClass">
					Reference books
				</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">
				<span class="required">*</span>Search Text :
			</label>
			<div class="col-md-3">
				<div class="input-group">
					<sj:textfield name="searchWord" id="searchWord"
						cssClass="form-control required"
						placeholder="Please enter book title" cssStyle="width:240px;"></sj:textfield>
					<span class="input-group-btn"> <s:submit type="submit"
							cssClass="btn blue" value="Search"
							onclick="javascript:searchKeyWords(); return false;" />
					</span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">
				<span class="required">*</span>Filter By :
			</label>
			<div class="col-md-6">
				<s:select
					list="#{'TitleWords':'Title Words*','Title':'Book Name','Author':'Author/Creator Browse','Subject':'Subject Browse','Publisher':'Publisher'}"
					id="searchBy" name="searchBy" cssClass="form-control input-medium"></s:select>
			</div>
		</div>

	</s:form>
	<div id="resultsDiv"></div>
</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<script type="text/javascript">
$("input:checkbox, input:radio").uniform();
$('input[name="bookIssueType"]').click(function() {
	if ($('input[name="bookIssueType"]:checked').val() == "IB")
		$("a#stockMaitenance").click();
	else
		issueAndReadingBooksAction();
});

function issueAndReadingBooksAction() {
	$("#resultsDiv").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	document.getElementById('schoolBooksList').style.display = "none";
	//if($("input#searchString").val()!="Please enter book title"){
		//$("input#searchString").val("Please enter book title");
	//}
	$('select#searchBy').find('option:first').attr('selected', 'selected');
	var url=jQuery.url.getChatURL("/library/ajaxDoReadingDetails.do");
	var pars='';
	$.ajax( {
		url : url,
		cache : false,
		data : pars,
		success : function(html) {
			$("#resultsDiv").html(html);
			document.getElementById('resultsDiv').style.display = "block";
		}
	});
}
function searchKeyWords() {
	var searchWord = $("#searchWord").val();
	var searchBy = $("#searchBy").val();
	var selectedType = $('input[name="bookIssueType"]:checked').val();
	var url = '';
	if ($('input[name="bookIssueType"]:checked').val() == "IB")
		url = jQuery.url.getChatURL("/library/ajaxSearchKeyWords.do");
	else
		url = jQuery.url.getChatURL("/library/ajaxSearchReadingBooks.do");
	
	if (searchWord.length == 0 || searchWord=='Please enter book title') {
		alert("!Oops enter search word.");
	} else {
		$("#resultsDiv").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "searchWord=" + searchWord + "&searchBy=" + searchBy +"&selectedType=" +selectedType;
		$ .ajax( {
					url : url,
					cache : false,
					data : pars,
					success : function(html) {
						$("#resultsDiv").html(html);
						document.getElementById('resultsDiv').style.display = "block";
						document.getElementById('schoolBooksList').style.display = "none";
					}
				});
	}
}
</script>