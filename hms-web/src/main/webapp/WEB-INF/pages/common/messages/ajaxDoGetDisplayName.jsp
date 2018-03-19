<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="jquery.js">
</script>
<script type="text/javascript" src="jquery.watermarkinput.js">
</script>

<div id="autoCompleterRollText">
<s:if test="%{viewAllUsers != null}">
	<s:hidden value="%{viewAllUsers.accountId}" name="accountId"
		theme="simple"></s:hidden>
	<div class="commonLable">
		<label class="label" for="title">
			<span class="required">*</span>To:
		</label>
	</div>
	<s:textfield name="viewAllUsers.personFullName" id="searchbox"
		label="To:" size="100px" labelposition="top"
		cssClass="searchUser small required" required="true" maxlength="80"
		requiredposition="first" theme="simple" />
	<br>
	<div id="displaySearchResult"
		style="display: none; overflow: auto; height: 200px; width: 310px;"></div>
		</s:if>

</div>





<script type="text/javascript">
changePageTitle("Compose Message")
$(document).ready(function() {

	$(".searchUser").keyup(function() {
		var searchbox = $(this).val();
		var dataString = 'searchword=' + searchbox;
		if (searchbox == '') {
		} else {
			$.ajax( {
				type : "POST",
				url : "ajaxSearchFirstNameAndLastName.do",
				data : dataString,
				cache : false,
				success : function(html) {

					$("#displaySearchResult").html(html).show();

				}

			});
		}
		return false;

	});
});

jQuery(function($) {
	$("#searchbox").Watermark("searchUser");
});
</script>
<style type="text/css">
body {
	font-family: "Lucida Sans";
}

* {
	margin: 0px
}

#searchbox {
	width: 250px;
	border: solid 1px #000;
	padding: 3px;
}

#display {
	width: 250px;
	display: none;
	float: right;
	margin-right: 30px;
	border-left: solid 1px #dedede;
	border-right: solid 1px #dedede;
	border-bottom: solid 1px #dedede;
	overflow: hidden;
}

.display_box {
	padding: 4px;
	border-top: solid 1px #dedede;
	font-size: 12px;
	height: 30px;
}

.display_box:hover {
	background: #3b5998;
	color: #FFFFFF;
}

#shade {
	background-color: #00CCFF;
}
</style>

<script type="text/javascript">
function clearTextField(field) {
	if (field.defaultValue == field.value)
		field.value = '';
	else if (field.value == '')
		field.value = field.defaultValue;

}
$(document).ready(function() {

	$.subscribe('doSendScrapMessageFormValidation', function(event, data) {
		if ($('#doSendScrapMessage').valid())
			return true;
		else
			return false;
	});
	$.subscribe('findStudentUsingNameFormValidation', function(event, data) {
		if ($('#findStudentUsingName').valid())
			return true;
		else
			return false;
	});

	$.subscribe('cancelRegistration', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});

});

function frequencyChange(clickButton) {
	var frequency = clickButton;
	if (frequency == 'R') {
		$("#autoCompleterRollText").show();
		$("#autoCompleterNameText").hide();
	} else if (frequency == 'N') {
		$("#autoCompleterNameText").show();
		$("#autoCompleterRollText").hide();
	}
}
</script>