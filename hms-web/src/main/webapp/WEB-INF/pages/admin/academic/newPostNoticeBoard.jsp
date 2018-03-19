<%@ include file="/common/taglibs.jsp"%>
<div class="grid_12" >
	<div class="block_content">
	<s:form id="addNewEvent" action="ajaxPostNoticeBoard" method="post"
		theme="css_xhtml" namespace="/admin">
		<table width="628px">
			<!--<tr>
				<td>
					<sj:textfield name="messages.title" id="eventName"
						label="Title" cssClass="text small required"
						required="true" maxlength="40"></sj:textfield>
				</td>
			</tr>
			-->
			<!--<tr>
				<td >
					<sj:datepicker id="date1" label="Notice Board Date" name="messages.messageDate"
						cssClass="text small required" required="true"
						cssStyle="width:167px;height:20px;" minDate="0" />
				</td>
			</tr>			
			-->
			<!--<tr><td ><label class="block"><span class="required">*</span>&nbsp;Create Notifications:</label></td></tr>
			--><tr>
				<td style="padding-bottom: 5px;">			
				 <input type="radio" value="ToALL" onclick="frequencyChangeClass('ToALL')" name="eventBelongs" style="vertical-align: top;" checked>To All 
	             <input type="radio" value="Class" onclick="frequencyChangeClass('Class')" name="eventBelongs" style="vertical-align: top;" >Classes
				
				</td>
			</tr>
			
			<tr>
				<td align="left" style="display:none;" id="clickClass">
				<div id="checkBoxFieldErrors"></div>
					<s:checkboxlist name="chkBoxSelectedIds" id="chkBoxClassIds" list="studyClassList" listKey="classAndSection" listValue="classAndSection"
						 />
				</td>
			</tr>
			
			<tr>
				<td>
				<sj:textarea name="messages.messageDescription" label="Description" id="eventDescription" cssClass="text small required" required="true" ></sj:textarea>
				</td>
			</tr>
			<tr>
				<td style="float: right;" class="grid_4">
					<sj:submit   targets="noticeBoardContent" value="Submit"
						cssClass="submit small" formIds="addNewEvent" validate="true"
						indicator="indicator" onClickTopics="eventFormValidation"/>
					<s:url id="cancelNoticeBoardUrl" action="ajaxCancelNoticeBoard"
						includeParams="all" escapeAmp="false" namespace="/admin">
					</s:url>
					<sj:a href="%{cancelNoticeBoardUrl}" cssClass="cancelButton"
						indicator="indicator" targets="noticeBoardContent"
						button="false" buttonIcon="ui-icon-plus">Cancel</sj:a>
				</td>
			</tr>
		</table>
	</s:form>
	</div>
	</div>
<script type="text/javascript">
changePageTitle('Create Notifications');
   function  frequencyChangeClass(clickButton) { 
		var frequency=clickButton;
		if(frequency=='ToALL'){
		$("#clickClass").hide();
		}else{
		if(frequency=='Class'){
		$("#clickClass").show();
		}
		}
	}
	function frequencyChange(clickButton) {
		var frequency=clickButton;
		if (frequency=='N') {
		$("#frequencyhideText").hide();
		}
		else {
		if(frequency=='R') {
		$("#frequencyhideText").show();
		}
		}
	}
    $.subscribe('eventFormValidation', function(event, data) {
    var catId = document.getElementsByName("chkBoxSelectedIds");
	var fieldErrorString ='';
	var frequencyStatus = $("input[name='eventBelongs']:checked").val();
		if(frequencyStatus!='ToALL')
		{
			var isSelected = false;
			for (i = 0; i < catId.length; i++) {
				if (catId[i].checked == true) {
				    isSelected = true;
				}
			}
			if (!isSelected) {
               fieldErrorString = fieldErrorString + "<font style=\"color:red\">Please select at least one Class.<br /></font>";
				document.getElementById('checkBoxFieldErrors').innerHTML= fieldErrorString;
                document.getElementById('checkBoxFieldErrors').style.display = "block";
				return false;
				}
				else{
				return true;
				}
			}
         if ($('#addNewEvent').valid())
         	 return true;
          else
          	 return false;
    });
</script>
