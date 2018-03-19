<%@ include file="/common/taglibs.jsp"%>
<div class="form-body">
<s:form action="" id="searchText" theme="simple" cssClass="form-horizontal">
		<div class="form-group">
			<label class="col-md-3 control-label">
				Enter reservation number :
			</label>
			<div class="col-md-4">
				<div class="input-group">
				<sj:textfield name="searchBookNumber" id="searchBookNumber" maxlength="14" placeholder="Please enter reservation number"
					cssClass="form-control required  numeric"/>
			<span class="input-group-btn">
				<s:submit type="submit" cssClass="btn blue" value="Search"
					onclick="javascript:searchRequestedBook(); return false;" /></span>
					</div>
			</div>
		</div>
</s:form>
<div id="resultsDiv"></div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript">
	function searchRequestedBook() {
		var searchBookNumber = document.getElementById("searchBookNumber").value;
		var url = jQuery.url.getChatURL("/library/ajaxSearchRequestedBook.do");
		if (searchBookNumber.length == 0 ||searchBookNumber=='Please enter reservation number' ) {
			alert("!Oops enter reservation number.");
		} else {
			$("#resultsDiv").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			var pars = "searchBookNumber=" + searchBookNumber;
			$.ajax( {
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
	function onlyNumbers(evt) {
		var e = evt; // for trans-browser compatibility	
		var charCode = e.which || e.keyCode;
		if (charCode > 31 && (charCode < 48 || charCode > 57))
			return false;
		return true;
	 }
	 $('.numeric').numeric();
</script>




