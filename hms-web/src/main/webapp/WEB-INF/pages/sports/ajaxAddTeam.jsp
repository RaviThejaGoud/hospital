<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/subscription/textcounter.js"> </script>
<style type="text/css">
	@import url("${pageContext.request.contextPath}/styles/newCss/autocompleteStyles.css");
</style>
<div id="autoCompleterRollText">
<s:form action="ajaxAddTeam" theme="simple" id="addTeam" method="post" cssClass="form-horizontal" namespace="/sports">
		<!-- <input type="hidden" name="selectedId" id="roleIds" /> -->
		<!-- <input type="hidden" name="anyId" id="staffAccountIds" /> -->
		<s:hidden name="teamVO.studentIds" cssClass='accountIds' />
	<s:hidden name="teamVO.id" value="%{teamVO.id}" id="teamVOId" />
	<div class="form-body">
		<h4 class="pageTitle bold form-section">TEAM INFORMATION</h4>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="col-md-4 control-label"> <span
						class="required">*</span> Select Sports :
					</label>
					<div class="col-md-8">
						<s:select list="sportsList" listKey="id" id="TeamId" listValue="sportName" theme="simple" cssClass="required form-control input-medium"
							name="teamVO.sportsId" headerKey="" headerValue="- Select Sports -" onchange="showAdmissionNumber(this.value);">
						</s:select>
					</div>
				</div>
			</div>

			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> <span
						class="required">*</span>Team Name :
					</label>
					<div class="col-md-7">
						<sj:textfield name="teamVO.teamName" id="TeamName"
							cssClass="required form-control input-medium" maxlength="40" />
					</div>
				</div>
			</div>
		</div>

			<div class="row">
			<s:if test="%{teamVO.playersList != null && !teamVO.playersList.isEmpty()}">
				<s:iterator value="teamVO.playersList">
					<script>
						var studentId = '<s:property value="playerId"/>';
						var fullName = '<s:property value="playerName"/>';
						var playerStatus = '<s:property value="playerStatus"/>'
						var friend = fullName, span = $(
								"<span class='staffStudentsId color-Type-" + fullName + "'  id='" +  studentId + "'>")
								.text(friend), a = $("<a>").addClass("remove")
								.attr({
									href : "javascript:",
									title : "Remove " + friend
								}).text("x").appendTo(span);
						span.insertBefore("#search_role");
						var selectedSchAccountIds = [];
						$('.staffStudentsId').each(function() {
							selectedAccountIds = $(this).attr('id');
							if (isNonEmpty(selectedAccountIds)) {
								selectedSchAccountIds.push(selectedAccountIds);
							}
						});
						$(".accountIds").val(selectedSchAccountIds);
						if (playerStatus == "C")
							$('#captain').append($('<option>').text(fullName).attr('value', studentId).attr('selected', 'selected'));
						else
							$('#captain').append($('<option>').text(fullName).attr('value', studentId));
						if (playerStatus == "V")
							$('#vicecaptain').append($('<option>').text(fullName).attr('value', studentId).attr('selected', 'selected'));
						else
							$('#vicecaptain').append($('<option>').text(fullName).attr('value', studentId));
					</script>
				</s:iterator>
				</s:if>
				<div class="form-group" style="float:right;width:1273px;">
					<label class="control-label col-md-2"> <span
						class="required">*</span>Select Players :
					</label>
					<div id="friends" class="col-md-9">
						<input id="search_role" type="text" name="teamVO.playersList" class="ui-autocomplete-input form-control" style="border: 1px solid #FFFFFF;" />
					</div>
				</div>
				<div  class="col-md-2"></div><span class="help-block">(Enter at least 3 chars to get closer match.)</span>
			</div>
			<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="col-md-4 control-label"> <span
						class="required">*</span> Select Captain :
					</label>
					<div class="col-md-8">
						<s:select list="objectList" listKey="id" id="captain" listValue="fullName" theme="simple" cssClass="required form-control input-medium"
							name="teamVO.captainId" headerKey="" headerValue="- Select Captain -">
						</s:select>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="col-md-4 control-label"> <span
						class="required">*</span> Select Vice-Captain :
					</label>
					<div class="col-md-8">
						<s:select list="objectList" listKey="id" id="vicecaptain" listValue="fullName" theme="simple" cssClass="required form-control input-medium"
							name="teamVO.viceCaptainId" headerKey="" headerValue="- Select Vice-Captain -">
						</s:select>
					</div>
				</div>
			</div>
		</div>
		
		
		<div class="form-actions fluid">
			<div class="col-md-6">
				<div class="col-md-offset-4 col-md-9">
					<sj:submit cssClass="submitBt btn blue" value="Submit" id="clickSubmit" validate="true" indicator="indicator" targets="mainContentDiv" formIds="addTeam" />
					<!--  onBeforeTopics="sportsFormValidation" -->
					<s:url id="doViewSports" action="ajaxTeamInformationHome"
						includeParams="all" escapeAmp="false" namespace="/sports">
					</s:url>
					<sj:a href="%{doViewSports}" cssClass="btn default"
						indicator="indicator" targets="mainContentDiv">
								Cancel</sj:a>
				</div>
			</div>
		</div>
	</div>
</s:form>
<div id="findStudent" class="findStudents"></div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle('Compose Messages');		 
		$.subscribe('sendMsgFormValidation',function(event, data) {
			if(isNonEmpty($('.accountIds').val())){
				return true;
			}else{
				alert("Please select at least one user.");
				 event.originalEvent.options.submit=false;	
			}
		});
	});
var nonIds = '0,';
$(".remove", document.getElementById("friends")).live("click", function () {
     //remove current friend  
     $(this).parent().remove();
     nonIds = nonIds.replace($(this).parent().attr('id') + ',', '').replace(/^\,/, "").replace(/,$/, "");
     //correct 'search_role' field position  
     if ($("#friends div").length === 0) {
         $("#search_role").css("top", 0);
     }
      var selectedSchAccountIds = [];
      $('.staffStudentsId').each( function () {
    	  selectedAccountIds = $(this).attr('id');
    	  if (isNonEmpty(selectedAccountIds)) {
              selectedSchAccountIds.push(selectedAccountIds);
    	  }
      });
      $('.accountIds').val(selectedSchAccountIds);
      nonIds=selectedSchAccountIds+",";
		if(nonIds==","){
			nonIds=selectedSchAccountIds+"0,";
		}
		$("#captain").find("option[value="+$(this).parent().attr('id')+"]").remove();
		$("#vicecaptain").find("option[value="+$(this).parent().attr('id')+"]").remove();
});
$("input#search_role").autocomplete({
     minLength: 3,
     source: function (req, response1) {
         //pass request to server
         var searchword = $('#search_role').val();
         $('#search_role').addClass('spinner');
         $.ajax({
             dataType: 'json',
             type: "POST",
             data: "searchword=" + searchword + "&anyTitle=" + nonIds,
             url: "../sports/ajaxSearchStudentName.do",
             cache: false,
             success : function(response) {
            	
            	 //$("#studentId").html('<a href="">'+response.data+"</a>");
			if (response.data) {
				var data = response.data;
				var studentId;
				var fullName;
				var className;
				var section;
				var inputObj;
				if (data.length >= 1) {
					for ( var i = 0; i < data.length; i++) {
						 
						studentId = data[i].studentId;
						fullName = data[i].fullName;
						className = data[i].className;
						section = data[i].section;
					}
				}
				response1($.map(data, function (item) {
                         if (item.studentId != 0) {
	                          if(isNonEmpty(item.studentId)){
	                        	  //$('#studentId').append($('<option>').text(item.fullName).attr('value', item.studentId));
								return {
									label: extractLast(item.fullName + '(' + item.className + '-' + item.section+ ')'),
									id: item.studentId,
									value: item.fullName 
							}
							}else{
								//$('#studentId').append($('<option>').text(item.fullName).attr('value', item.studentId));
                           		  return {
	                                 label: extractLast(item.fullName + '(' + item.className + '-' + item.section+ ')'),
	                                 id: item.studentId,
	                                 value: item.fullName
	                             }
                             }
                              
                         } else {
                        	// $('#studentId').append($('<option>').text(item.fullName).attr('value', item.studentId));
                             return {
                                 value: item.fullName,
                                 id: item.studentId
                             }
                         }
                     }))
			        }
                $("#displaySearchResult").data(data).show(); 
             }
         });
     },
     select: function (event, ui) {
         //create formatted friend  
         if (ui.item.id == 0) {
             return false;
         } else {
             var valueName = ui.item.label;
             nonIds += ui.item.id + ',';
             var friend = valueName,
                 span = $("<span class='staffStudentsId color-Type-" + ui.item.value + "'  id='" + ui.item.id + "'>").text(friend),
                 a = $("<a>").addClass("remove").attr({
                     href: "javascript:",
                     title: "Remove " + friend
                 }).text("x").appendTo(span);
             span.insertBefore("#search_role");
             
             var selectedSchAccountIds = [];
	 				$('.staffStudentsId').each(function() {
	 					selectedAccountIds = $(this).attr('id');
	 					if (isNonEmpty(selectedAccountIds)) {
	 						selectedSchAccountIds.push(selectedAccountIds);
	 					}
	 				});
	 				$('.staffStudentsId').each(function() {
	 					selectedAccountIds = $(this).attr('id');
	 					if (isNonEmpty(selectedAccountIds)) {
	 						selectedSchAccountIds.push(selectedAccountIds);
	 					}
	 				});
	 				$('#vicecaptain').append($('<option>').text(ui.item.value).attr('value', ui.item.id));
	 				$('#captain').append($('<option>').text(ui.item.value).attr('value', ui.item.id));
	 				
	 			$(".accountIds").val(selectedSchAccountIds);
	             $("#search_role").val("");
	             $('#search_role').removeClass('spinner');
	             return false;
         }
     },
     //define select handler  
     change: function () {
         //prevent 'search_role' field being updated and correct position  
         $("#search_role").val("").css("top", 2);
     }
 });
 $("#friends").click(function () {
     //focus 'search_role' field  
     $("#search_role").focus();
 });
 function split(val) {
     return val.split(/,\s*/);
 }
 function extractLast(term) {
     return split(term).pop();
 }
</script>
