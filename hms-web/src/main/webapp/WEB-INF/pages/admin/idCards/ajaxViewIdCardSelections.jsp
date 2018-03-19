<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<style type="text/css">
	@import url("${pageContext.request.contextPath}/styles/newCss/autocompleteStyles.css");
</style>
<s:if test='%{customer != null}'>
	<s:form name="myformIdCards" namespace="/reports" id="idCardGenForm"
		action="ajaxAllStudentIdCardGeneration" theme="simple"
		cssClass="form-horizontal"
		onsubmit="javascript: return getClassIdsAndAdmissionNumber();">
		<input type="hidden" name="pdfId" value="pdf" />
		<input type="hidden" name="selectedId" id="classNameIds" />
		<input type="hidden" name="anyId" id="adminNumbers" />
		<%--<s:form id="printIdCards" action="ajaxAllStudentIdCardGeneration"
			method="post" theme="css_xhtml">
			--%>
		<!-- <span class="label label-danger"> NOTE : </span>&nbsp; You can select the id card type.
		<div>&nbsp;</div> -->
		<div class="form-body">
			<b><label>
					Select ID Card Type :
				</label> </b>
			<br />
			<div class="row">
				<div class="col-md-4">
					<div class="radio-list">
						<label class="radio-inline">
							<input type="radio" id="verticalCard" value="V" class="radio"
								name="cardType">
							Vertical Card Type
						</label>
					</div>
					<table
						class="table table-striped table-bordered table-hover table-full-width"
						id="sample_4">
						<thead>
							<tr>
								<td>
									<div class="col-md-15" align="center">
										<div class="col-md-5" style="width: 134px;">
											<img src='<s:property  value="#session.custImage"/>' border="0" alt="logo" class="logoHeader" id="customerLogoDiv"/> 
										</div>
										<div class="col-md-7">
											<b style="color: MediumVioletRed;"><s:property value="customer.organization" /> </b>
											<br />
											<s:property value="customer.organizationFullAddress" />
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td class="col-md-6" style="border-bottom: none;  text-align: center;">
									<img src="<c:url value='../images/nophoto.jpg'/>"
												height="150px" width="122px;"></img>
								</td>
							</tr>
							<tr>
								<td align="center" style="height:220px">
									<div>&nbsp;</div>
										<div class="col-md-10" align="left" style="font-size:110%">
											<span class="text"><b> Name :</b></span> <span class="text">Jonnie
												Wheeler. C </span>
										</div>
									<div>&nbsp;</div>
										<div class="col-md-10" align="left" style="font-size:110%">
											<span class="text"><b> Class & Section :</b></span> <span
												class="text">X-A </span>
										</div>
									<div>&nbsp;</div>
										<div class="col-md-10" align="left" style="font-size:110%">
											<span class="text"><b> Admission No :</b></span> <span
												class="text">89 </span>
										</div>
									<div>&nbsp;</div>
										<div class="col-md-10" align="left" style="font-size:110%">
											<span class="text"><b> Blood Group :</b></span> <span
												class="text">B+ve </span>
										</div>
									<div>&nbsp;</div>
										<div class="col-md-12" align="left" style="font-size:110%">
											<span class="text"><b> TransportMode :</b></span> <span
												class="text">Own / School Transport </span>
										</div>
									<div>&nbsp;</div><div>&nbsp;</div><div>&nbsp;</div>
									<div class="col-md-15">&nbsp;</div>
									<div class="col-md-11">
										<div align="right"><b>Principal</b></div>
									</div>
								</td>
							</tr>
							<tr>
								<td style="border-top: 1px solid #D6D6D6;" align="center">
									80 ft Main Road, HBR Layout, Bengalore - 560043
								</td>
							</tr>
						</thead>
					</table>
				</div>
				<div class="col-md-6">
					<div class="radio-list">
						<label class="radio-inline">
							<input type="radio" id="horizentalCard" value="H" class="radio"
								name="cardType" checked>
							Horizontal Card Type
						</label>
					</div>
					<table
						class="table table-striped table-bordered table-hover table-full-width"
						id="sample_4">
						<thead>
							<tr>
								<td>
									<div class="col-md-12">
										<div class="col-md-5 left" style="width: 134px;">
											<img src='<s:property  value="#session.custImage"/>' border="0" alt="logo" class="logoHeader" id="customerLogoDiv"/> 
										</div>
										<div align="center">
										<b style="color: MediumVioletRed;"><s:property value="customer.organization"/> </b>
										<br />
										<s:property value="customer.organizationFullAddress" /></div>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<div class="col-md-7 left" style="width: 150px;">
										<div>
											<img src="<c:url value='../images/nophoto.jpg'/>"
												height="150px" width="122px;"></img>
										</div>
										<div>
											&nbsp;
										</div>
										<div align="center">
											<b>Principal</b>
										</div>
									</div>
									<div class="col-md-5">
										<label>
											<b>Name :</b>
										</label>
										Jonnie Wheeler. C
									</div><div>&nbsp;</div>
									<div class="col-md-5">

										<label>
											<b>Class & Section :</b>
										</label>
										X-B
									</div>
									<div class="col-md-5">
										<label>
											<b>Admission No :</b>
										</label>
										89
									</div>
									<div class="col-md-5">
										<label>
											<b>Blood Group :</b>
										</label>
										B+
									</div>
									<div class="col-md-5" style="width: 200px;">
										<label>
											<b>Transport Mode :</b>
										</label>
										<span>Own / School Transport </span>
									</div>
								</td>
							</tr>
							<tr>
								<td style="border-top: 1px solid #D6D6D6;" align="center">
									80 ft Main Road, HBR Layout, Bengalore - 560043
								</td>
							</tr>
						</thead>
					</table>
				</div>
			</div>
			<s:if test="%{studyClassList != null && !studyClassList.isEmpty()}">
			<div class="form-group">
				<label class="col-md-1 control-label">
					<strong>Select : </strong>
				</label>
				<div class="radio-list">
					<label class="radio-inline">
						<input type="radio" name="SelectType" value="classSectionWise"
							onclick="getSelectedValue(this.value);" checked="checked">
						Class & Section Wise
					</label>
					<label class="radio-inline">
						<input type="radio" name="SelectType" value="admissionNumberWise"
							onclick="getSelectedValue(this.value);">
						Search By Admission Number Wise
					</label>
				</div>
			</div>
			<div id="classAndSection">
				<p>
					<span class="label label-danger">NOTE :</span>&nbsp;&nbsp; Please
					select class and id card type.
				</p>
				<hr />
					<div class="form-group">
						<div class="col-md-12">
							<div class="checkbox-list">
								<label class="checkbox-inline">
									<input type="checkbox" name="" value=""
										onClick="checkAllClasses()" class="checkbox allClasses">
									All Class & Sections
								</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="conLable col-md-3 control-label">
							Class With Students :
						</label>
						<div class="col-md-12">
							<div class="checkbox-list">
								<s:checkboxlist name="chkBoxSelectedIds" list="studyClassList"
									listKey="id" listValue="classAndSection" theme="ems"
									cssClass="small" />
							</div>
						</div>
					</div>
			</div>
			<div id="searchAdmissionNumber" style="display: none;">
				<div class="form-group">
						<label class="control-label col-md-3">
							<span class="required">*</span>Search Student Admission Number :
						</label>
						<div id="friends" class="col-md-6">
							<input id="search_role" type="text" class="ui-autocomplete-input form-control"  style="border: 1px solid #FFFFFF;"/>
						</div> 
					</div>
					<div  class="col-md-3"></div><span class="help-block">(Enter at least 3 chars to get closer match.)</span>
			</div>
			<div id="generateId">
				<div class="form-actions fluid">
					<div class="col-md-6">
						<div class="col-md-offset-3 col-md-12">
							<s:submit value="Generate ID Cards"
								cssClass="submitBt btn blue long" id="idCardGenForm"></s:submit>
						</div>
					</div>
				</div>
			</div>
			</s:if>
			<s:else>
				<div class="alert alert-info">Currently there are no classes assigned.
				</div>
			</s:else>
		</div>
	</s:form>
</s:if>
<script type="text/javascript">
$(document).ready(function() {
	$.destroyTopic('searchStudentAdmissionForm');
		var admissionNumber = "";
		$("input:checkbox, input:radio").uniform();
		/* $('li#idCardsDiv').addClass('active');
		$('.page-sidebar-menu li.active').addClass('open'); */
		var staffName = "";
		var str = $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim();
		if(isNonEmpty(str)){
			$('span.hidden-481').html($('.page-sidebar-menu li.active').find('li.active').children('a').children('span.title').text().trim() + "-->"
					+ $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim())
		}else{
			$('span.hidden-481').html($('.page-sidebar-menu li.active').find('li.active').children('a').children('span.title').text().trim())
		}
		var title = $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim();
		changePageTitle(title);
		$('#searchAdmissionNumber').hide();
		var selected = $('input[name=SelectType]:radio:checked').val();
		getSelectedValue(selected);
		$.subscribe('searchStudentAdmissionForm', function(event, data) {
			admissionNumber = $('#admissionNumber').val();
			//alert('admissionNumber : '+admissionNumber)
				if (admissionNumber == null || admissionNumber == ''
						|| admissionNumber == 'Student Admission Number') {
					alert("Please enter student admission number.");
					event.originalEvent.options.submit = false;

				} else if (admissionNumber.length < 3) {
					alert("Please enter minimum 3 characters.");
					event.originalEvent.options.submit = false;
				} else {
					$("#adminNumber").val(admissionNumber);
					$('#searchStudentsList').show();
					$("#generateId").show();
				}

			});
	});
function getSelectedValue(value) {
	if (isNonEmpty(value)) {
		if (value == 'classSectionWise') {
		checkAllClasses();
			$('#searchAdmissionNumber').hide();
			$('#classAndSection').show();
			$("#searchStudentsList").hide();
			$("#generateId").show();
			$("#adminNumbers").val(null);
			$(".studentIdcard").remove();
			searchStaff();
		} else {
			$('input[name="chkBoxSelectedIds"]').removeAttr('checked');
			$('input.allClasses').removeAttr('checked');
			$('#classAndSection').hide();
			$('#searchAdmissionNumber').show();
			$("#searchStudentsList").hide();
			//$("#generateId").hide();
			$("#classNameIds").val(null);
		}
	}
}
function checkAllClasses() {
	if ($(".allClasses").is(':checked')) {
		$("[name='chkBoxSelectedIds']").each(function() {
			$(this).parent('span').addClass('checked');
			$(this).attr("checked", "true");
		});
	} else {
			$(".allClasses").parent('span').removeClass('checked');
			$(".allClasses").removeAttr("checked");
		$("[name='chkBoxSelectedIds']").each(function() {
			$(this).parent('span').removeClass('checked');
			$(this).removeAttr("checked");
		});
	}
}
$("input[name=chkBoxSelectedIds]").click(function() {
	if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
		$(".allClasses").parent('span').removeClass("checked");
		$(".allClasses").attr("checked", false);
	} else {
		$(".allClasses").parent('span').addClass("checked");
		$(".allClasses").attr("checked", true);
	}
});

function getClassIdsAndAdmissionNumber() {
	var selected = $('input[name=SelectType]:radio:checked').val();
	// alert('selected:'+selected)
	if (selected == 'classSectionWise') {
		if ($("input[name=chkBoxSelectedIds]:checked").length > 0) {
			var classIds = $("input[name=chkBoxSelectedIds]:checked");
			var selectedClassNames = [];
			var selectedClassIds = [];
			if (classIds.length > 0) {
				$(classIds).each(function() {
					if (isNonEmpty($(this).val())&& isNonEmpty($(this).parents('label').text())) {
						selectedClassIds.push($(this).val());
						selectedClassNames.push($(this)
								.parents('label').text().trim())
					}
				});
			}
			$("#classNameIds").val("(" + selectedClassIds + ")");
			$("#classNames").val(selectedClassNames);
			return true;
		} else {
			alert("Please select at least one class.");
			return false;
		}
	} else {
		//alert($('div#emptyStudents').text());
		if ($('div#emptyStudents').text()) {
			alert('Please find student');
			return false;
		}
		if(isNonEmpty($('#adminNumbers').val()) && $('#adminNumbers').val()!="()"){
			return true;
		}else{
			alert("Please select at least one student.");
			return false;
		}
	}
}

function searchStaff(){
	var nonIds = '0,';
	$(".remove", document.getElementById("friends")).live("click", function () {
	     //remove current friends  
	     $(this).parent().remove();
	     nonIds = nonIds.replace($(this).parent().attr('id') + ',', '').replace(/^\,/, "").replace(/,$/, "");
	     //correct 'search_role' field position  
	     if ($("#friends div").length === 0) {
	         $("#search_role").css("top", 0);
	     }
	     var selectedStudAdmissionNumbers = [];
			$('.studentIdcard').each(function() {
				stuAccounrId = $(this).attr('id');
				if (isNonEmpty(stuAccounrId)) {
					selectedStudAdmissionNumbers.push("'" + stuAccounrId + "'");
				}
			});
		$("#adminNumbers").val("(" + selectedStudAdmissionNumbers + ")");
		nonIds = selectedStudAdmissionNumbers+",";
		if(nonIds==","){
			nonIds=selectedStudAdmissionNumbers+"0,";
		}
	});

	$("input#search_role").autocomplete({
	     minLength: 3,
	     source: function (req, response1) {
	         //pass request to server
	         var searchword = $('#search_role').val();
	        
	         if(isNonEmpty(searchword)){
	        	 $('#search_role').addClass('spinner');
	         }else{
	        	 $('#search_role').removeClass('spinner');
	         }
	         $.ajax({
	             dataType: 'json',
	             type: "POST",
	             data: "searchword=" + searchword + "&anyTitle=" + nonIds,
	             url: "../reports/ajaxSearchStudentByAdmissionNumber.do",
	             cache: false,
	             success : function(response) {
				if (response) {
					var data = response.data;
					var accountId;
					var stuName;
					var admissionNumber;
					var rollNumber;
					//var inputObj;
					if (data.length >= 1) {
						for ( var i = 0; i < data.length; i++) {
							accountId = data[i].accountId;
							stuName = data[i].stuName;
							admissionNumber = data[i].admissionNumber;
							rollNumber  = data[i].rollNumber;
						}
					}
					response1($.map(data, function (item) {
	                         if (item.accountId != 0) {
		                          if(isNonEmpty(item.admissionNumber)){
									var l_LastName = '';
										if(isNonEmpty(item.admissionNumber))
											l_LastName = item.admissionNumber ;
									return {
										label: extractLast(item.stuName + ' ' + item.admissionNumber + '(' + item.rollNumber + ')'),
										id: item.accountId+":"+item.admissionNumber,
										value: item.role
								}
								}else{
	                           		  return {
		                                 label: extractLast(item.stuName + ' ' + '' + '(' + item.rollNumber + ')'),
		                                 id: item.accountId+":"+item.admissionNumber,
		                                 value: item.admissionNumber
		                             }
	                             }
	                              
	                         } else {
	                             return {
	                                 value: item.stuName,
	                                 id: item.accountId
	                             }
	                         }
	                         
	                     }))
				        }
	                $("#displaySearchResult").data(data).show();
	                $('#search_role').removeClass('spinner');
	             }
	         });
	     },
	     select: function (event, ui) {
	    	 var admissionNum = ui.item.id.split(':');
	         //create formatted friend  
	         if (admissionNum[0] == 0) {
	             return false;
	         }else {
	             var valueName = ui.item.label;
	             nonIds += "'"+admissionNum[0] + "'"+',';
	             var friend = valueName,
	                 span = $("<span class='studentIdcard color-Type-" + ui.item.value + "'  id='" + admissionNum[0] + "'>").text(friend),
	                 a = $("<a>").addClass("remove").attr({
	                     href: "javascript:",
	                     title: "Remove " + friend
	                 }).text("x").appendTo(span);
	             //add friend search_role friend div  
	             span.insertBefore("#search_role");
	 			var selectedStudAdmissionNumbers = [];
	 				$('.studentIdcard').each(function() {
	 					stuAccounrId = $(this).attr('id');
	 					if (isNonEmpty(stuAccounrId)) {
	 						selectedStudAdmissionNumbers.push("'" + stuAccounrId + "'");
	 					}
	 				});
	 			$("#adminNumbers").val("(" + selectedStudAdmissionNumbers + ")");
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
} 
 $('html, body').animate({ scrollTop: $(document).height() - $(window).height() }, 10, function() {
	    $(this).animate({ scrollTop: 0 }, 10);
	});
</script>
