<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="jquery.watermarkinput.js"></script>
<!--<div class="block_head">
	<h2>
		New Message:
	</h2>
</div>

-->
<div id="autoCompleterRollText">
<fieldset>
	<s:form id="doSendScrapMessage" action="ajaxSendScrapMessage"
		method="post" theme="css_xhtml">
		<!--<sj:autocompleter id="allUsersSet" name="accountId"
			onfocus="clearTextField(this.value)" list="%{allUsersSet}" listValue="person.fullPersonName"
			listKey="id" label="To"
			value="Type the name of the friend" selectBox="true" />
			-->
		<div id="displayPersonName">
			<div class="commonLable">
				<label class="label" for="title">
					<span class="required">*</span>To:
				</label>
			</div>
			<s:textfield name="fullPersonName" id="searchbox" label="To:"
				size="100px" labelposition="top"
				cssClass="searchUser small required" required="true" maxlength="80"
				requiredposition="first" theme="simple"></s:textfield>
			<br />
			<div id="displaySearchResult"
				style="display: none; overflow: auto; height: 200px; width: 310px;"></div>
		</div>

		<s:textfield name="scrapMessage.title" id="title" label="Subject"
			size="100px" labelposition="top" cssClass="small required"
			required="true" maxlength="80" requiredposition="first"></s:textfield>
		<br />
		<sj:textarea name="scrapMessage.scrapDescription"
			cssStyle="height: 95px;width : 50%" id="scrapDescription"
			label="Message:" cssClass="required" required="true" rows="5"
			cols="40"></sj:textarea>
		<div class="grid_11">
			<div class="grid_4" style="float: right;">
				<sj:submit   targets="myMessagesContent" formIds="doSendScrapMessage" clearForm="true"
					cssClass="submit small" value="Send" indicator="indicator"
					onClickTopics="doSendScrapMessageFormValidation" />
				<s:url id="doCancelScrapMessage" 
					action="ajaxDoInboxGetScrapMessagesList" includeParams="all"></s:url>
				<sj:a href="%{doCancelScrapMessage}" cssClass="cancelButton"
					indicator="indicator" targets="inboxHome">Cancel</sj:a>
			</div>
		</div>
	</s:form>
	</fieldset>
</div>
<div id="findStudent"></div>
		<style type="text/css" media="screen">
			.form_tags { margin-bottom: 10px; }
			
			/* Setting widget width example */
			.textboxlist { width: 400px; }
			
			/* Preloader for autocomplete */
			.textboxlist-loading { background: url('images/spinner.gif') no-repeat 380px center; }
			
			/* Autocomplete results styling */
			.form_friends .textboxlist-autocomplete-result { overflow: hidden; zoom: 1; }
			.form_friends .textboxlist-autocomplete-result img { float: left; padding-right: 10px; }
			
			.note { color: #666; font-size: 90%; }
			#footer { margin: 50px; text-align: center; }
		</style>

<script type="text/javascript">
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