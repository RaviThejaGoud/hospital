<%@ include file="/common/taglibs.jsp"%>
<div class="form-body">
	<s:form action="" id="searchText" theme="simple" cssClass="form-horizontal">
	<div class="form-group">
			<label class="col-md-3 control-label">
				Search Book :
			</label>
		<div class="col-md-4">
				<div class="input-group">
			<sj:textfield name="searchWord" id="searchString" cssClass="form-control required defaultValue"
				maxlength="14" value="Please enter book title." />
		<span class="input-group-btn">
			<s:submit type="submit" cssClass="btn blue" value="Search" 
				onclick="javascript:searchReadingBooks(); return false;" /></span>
		</div>
		<div>&nbsp;</div>
			<div class="col-md-6">
			  <s:select
					list="#{'TitleWords':'Title Words*','Title':'Book Name','Author':'Author/Creator Browse','Subject':'Subject Browse','Publisher':'Publisher'}"
					id="searchBy" name="searchBy" cssClass="form-control input-medium"></s:select> 
			</div>
		</div>
	</div>
</s:form>
<div id="resultsDiv"></div>
</div>
<script type="text/javascript" 
src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript">
function searchReadingBooks() {
	var searchWord = document.getElementById("searchString").value;
	var searchBy = document.getElementById("searchBy").value;
	var url = jQuery.url.getChatURL("/library/ajaxSearchReadingBooks.do");
	if (searchWord.length == 0) {
		alert("!Oops enter search word.");
	} else {
		$("#resultsDiv")
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "searchWord=" + searchWord + "&searchBy=" + searchBy;
		$
				.ajax( {
					url : url,
					cache : false,
					data : pars,
					success : function(html) {
						$("#resultsDiv").html(html);
						document.getElementById('resultsDiv').style.display = "block";
						document.getElementById('readingBooksList').style.display = "none";
					}
				});
	}
}
</script>