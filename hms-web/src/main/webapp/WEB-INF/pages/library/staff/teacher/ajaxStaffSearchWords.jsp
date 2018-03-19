<%@ include file="/common/taglibs.jsp"%>
<s:form action="" id="searchText" method="post" theme="simple"
	cssClass="form-horizontal">
	<div class="form-group">
		<label class="col-md-2 control-label">
			Search Text :
		</label>
		<div class="col-md-2">
			<sj:textfield name="searchString" id="searchString1" tabindex="1"
				cssClass="form-control input-medium"
				placeholder="Please enter book title">
			</sj:textfield>
		</div>
	</div>
	<div class="form-group">
		<label class="col-md-2 control-label">
			Filter By :
		</label>
		<div class="col-md-2">
			<s:select
				list="#{'TitleWords':'Title Words*','Title':'Book Name','Author':'Author/Creator Browse','Subject':'Subject Browse','Publisher':'Publisher'}"
				id="searchBy" name="searchBy" tabindex="2"
				cssClass="form-control input-medium"></s:select>
		</div>
	</div>
	<div class="form-group">
		<label class="col-md-2 control-label">
			Classes :
		</label>
		<div class="col-md-2">
			<s:select
				list="#{'OnlyMyClass':'My Class Subjects','All':'All Classes Subjects'}"
				id="selectedClassType" name="selectedClassType" tabindex="3"
				cssClass="form-control input-medium"></s:select>
		</div>
	</div>
	<div class="form-group">
		<label class="col-md-2 control-label">
			Reserve Date :
		</label>
		<div class="col-md-2">
			<div data-date-start-date="+0d" data-date-format="mm/dd/yy"
				class="input-group input-medium date date-picker">
				<input id="reservationDate" name="reservationDate"
					readonly="readonly" type="text" class="form-control">
				<span class="input-group-btn">
					<button type="button" class="btn default">
						<i class="fa fa-calendar"></i>
					</button> </span>
			</div>
			<span class="help-block">(MM/DD/YYYY)</span>
		</div>
	</div>
	<div class="col-md-3">
		<s:submit type="submit" cssClass="btn blue" value="Search"
			tabindex="5" cssStyle="float:right;"
			onclick="javascript:staffSearchKeyWords(); return false;" />
	</div>
	<div class="spaceDiv"></div>
	<br />
</s:form>
<div id="resultsDiv1"></div>
<script type="text/javascript">
$(document) .ready( function() {
	FormComponents.init();
		var fullDate = new Date();
		var dd = fullDate.getDate();
		if (dd < 10) {
			dd = '0' + dd
		}
		var twoDigitMonth = fullDate.getMonth() + 1;
		if( (fullDate.getMonth().length + 1 ) < 10)
			twoDigitMonth = '0' + twoDigitMonth;
			
		//var twoDigitMonth = ((fullDate.getMonth().length + 1) === 1) ? (fullDate.getMonth() + 1): '0' + (fullDate.getMonth() + 1);
		$('input#reservationDate').val(twoDigitMonth + "/" + dd + "/"+ fullDate.getFullYear());
	});
function staffSearchKeyWords() {
	var searchWord = $('#searchString1').val();
	var searchBy = $('#searchBy').val();
	var selectedType = $('#selectedType').val();
	//var searchWord = document.getElementById("searchString").value;
	//var searchBy = document.getElementById("searchBy").value;
	//var selectedType = document.getElementById("selectedClassType").value;
	var url = jQuery.url.getChatURL("/library/ajaxStaffSearchKeyWords.do");
	if (searchWord.length == 0 || searchWord == 'Please enter book title') {
		alert("!Oops enter search word.");
	} else {
		$("#resultsDiv1").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "searchWord=" + searchWord + "&searchBy=" + searchBy+ "&selectedType=" + selectedType + "&selectedDate="+ $('input#reservationDate').val();
		$.ajax( {
					url : url,
					cache : false,
					data : pars,
					success : function(html) {
						$("#resultsDiv1").html(html);
						document.getElementById('resultsDiv1').style.display = "block";
						document.getElementById('schoolBooksList').style.display = "none";
					}
				});
	}
}
</script>
